package me.maxiiiiii.skyblockdragons.worlds.deepermines;

import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DeeperMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeeperMines");

    public DeeperMines(JavaPlugin plugin) {
        super(world, "Deeper Mines", WorldType.MINING, WorldType.COMBAT);
    }
}
