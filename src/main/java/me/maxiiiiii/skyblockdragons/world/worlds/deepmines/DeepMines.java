package me.maxiiiiii.skyblockdragons.world.worlds.deepmines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.attributes.ClickableBlock;
import me.maxiiiiii.skyblockdragons.world.attributes.EntityWorldSpawn;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class DeepMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeepMines");

    public DeepMines(JavaPlugin plugin) {
        super(world, "Deep Mines", Warp.DEEP_MINES, WorldType.MINING);

        addAttribute(new EntityWorldSpawn(new Location(world, 1158.5, 205, 64.5),
                181,
                60,
                Collections.singletonList(new Entry<>(EntityMaterial.get("GOLDEN_SKELETON"), 100.0)),
                20,
                Material.STONE, Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE
        ));

        addAttribute(new EntityWorldSpawn(new Location(world, 1158.5, 157, 64.5),
                132,
                60,
                Arrays.asList(new Entry<>(EntityMaterial.get("LAPIS_ZOMBIE"), 40.0), new Entry<>(EntityMaterial.get("REDSTONE_PIGMAN"), 30.0), new Entry<>(EntityMaterial.get("EMERALD_CREEPER"), 30.0)),
                35,
                Material.STONE, Material.LAPIS_ORE, Material.REDSTONE, Material.EMERALD_ORE
        ));

        addAttribute(new EntityWorldSpawn(new Location(world, 1158.5, 110, 64.5),
                84,
                60,
                Arrays.asList(new Entry<>(EntityMaterial.get("DIAMOND_ZOMBIE"), 70.0), new Entry<>(EntityMaterial.get("OBSIDIAN_ZOMBIE"), 30.0)),
                30,
                Material.STONE, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN
        ));

        addAttribute(new ClickableBlock(world.getBlockAt(1158, 162, 70), ClickableBlock.ClickType.RIGHT, p -> {
            if (p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SLIME_BLOCK) {
                p.setVelocity(new Vector(0, 1.2, 0));
            }
        }));

        addAttribute(new ClickableBlock(world.getBlockAt(1158, 113, 70), ClickableBlock.ClickType.RIGHT, p -> {
            if (p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SLIME_BLOCK) {
                p.setVelocity(new Vector(0, 1.2, 0));
            }
        }));
    }

    public static DeepMines deserialize(Map<String, Object> args) {
        return WorldSD.DEEP_MINES;
    }
}
