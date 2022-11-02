package me.maxiiiiii.skyblockdragons.world.worlds.deepmines;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DeepMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeepMines");

    public DeepMines(JavaPlugin plugin) {
        super(world, "Deep Mines", Warp.DEEP_MINES, WorldType.MINING);
    }
}
