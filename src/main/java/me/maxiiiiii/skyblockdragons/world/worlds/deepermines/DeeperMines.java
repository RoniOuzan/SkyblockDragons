package me.maxiiiiii.skyblockdragons.world.worlds.deepermines;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.npc.ForgeNPC;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class DeeperMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeeperMines");

    public DeeperMines(JavaPlugin plugin) {
        super(world, "Deeper Mines", Warp.DEEPER_MINES, WorldType.MINING, WorldType.COMBAT);
    }

    public static DeeperMines deserialize(Map<String, Object> args) {
        return WorldSD.DEEPER_MINES;
    }

    @Override
    protected void spawnNPCs() {
        new ForgeNPC(new Location(world, -22.5, 151, -48.5, -90, 0));
        new ForgeNPC(new Location(world, 23.5, 151, -48.5, 90, 0));

        new ForgeNPC(new Location(world, -22.5, 151, -58.5, -90, 0));
        new ForgeNPC(new Location(world, 23.5, 151, -58.5, 90, 0));

        new ForgeNPC(new Location(world, -22.5, 151, -78.5, -90, 0));
        new ForgeNPC(new Location(world, 23.5, 151, -78.5, 90, 0));

        new ForgeNPC(new Location(world, -22.5, 151, -88.5, -90, 0));
        new ForgeNPC(new Location(world, 23.5, 151, -88.5, 90, 0));
    }
}
