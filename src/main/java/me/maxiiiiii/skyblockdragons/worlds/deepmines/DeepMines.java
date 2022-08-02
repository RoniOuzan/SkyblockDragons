package me.maxiiiiii.skyblockdragons.worlds.deepmines;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DeepMines extends WorldSD {
    public static final World world = Bukkit.getWorld("DeepMines");

    public DeepMines(JavaPlugin plugin) {
        super(world, "Deep Mines", new Location(world, 1158.5, 210, 71.5, 180, 0), WorldType.MINING);
    }
}
