package me.maxiiiiii.skyblockdragons.worlds.deepmines;

import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DeepMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeepMines");

    public DeepMines(JavaPlugin plugin) {
        super(world, "Deep Mines", WorldType.MINING);
    }
}
