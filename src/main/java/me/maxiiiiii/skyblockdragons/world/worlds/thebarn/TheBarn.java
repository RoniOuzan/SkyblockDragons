package me.maxiiiiii.skyblockdragons.world.worlds.thebarn;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class TheBarn extends WorldSD {

    public static final World world = Bukkit.getWorld("thebarn");

    public TheBarn(JavaPlugin plugin) {
        super(world, "The Barn", Warp.THE_BARN, WorldType.COMBAT, WorldType.FARMING);
    }
}
