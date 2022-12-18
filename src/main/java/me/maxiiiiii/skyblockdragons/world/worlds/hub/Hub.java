package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.attributes.EntityWorldSpawn;
import me.maxiiiiii.skyblockdragons.world.attributes.LaunchPad;
import me.maxiiiiii.skyblockdragons.world.region.CubicWorldRegion;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Map;

public class Hub extends WorldSD {
    public static final World world = Bukkit.getWorld("Hub");

    public Hub(JavaPlugin plugin) {
        super(world, "Hub", Warp.HUB, WorldType.HUB, WorldType.COMBAT);

        addAttribute(new LaunchPad(new Location(world, -9.5, 64, -231), 2, Warp.DEEP_MINES));

        addAttribute(new EntityWorldSpawn(new Location(world, -144.5, 77, -120.5),
                66,
                60,
                Arrays.asList(new Entry<>(EntityMaterial.get("ZOMBIE"), 70d), new Entry<>(EntityMaterial.get("ZOMBIE_VILLAGER"), 30d)),
                50,
                Material.GRASS, Material.DIRT
        ));

        addRegion(new CubicWorldRegion("Mines", this, new Location(world, -94, 54, -114), new Location(world, 10, 82, -235), WorldType.MINING));

        plugin.getCommand("Hub").setExecutor(new HubCommand());
    }

    public static Hub deserialize(Map<String, Object> args) {
        return WorldSD.HUB;
    }

    @Override
    protected void spawnNPCs() {
        new WarpNPC(new Location(world, 4.5, 70, -94.5));
        new DailyNPC(new Location(world, -18.5, 70, -90, -90, 0));
        new ShopNPC(new Location(world, -18.5, 70, -77, -90, 0));
        new ReforgeNPC(new Location(world, -19.5, 71, -123.5, -90, 0));
        new AuctionMasterNPC(new Location(world, 17.5, 71, -78.5, 90, 0));
        new BankerNPC(new Location(world, 20.5, 71, -40.5, 90, 0));

        new MinerNPC(new Location(world, -15.5, 71, -155.5, -45, 0));
    }
}
