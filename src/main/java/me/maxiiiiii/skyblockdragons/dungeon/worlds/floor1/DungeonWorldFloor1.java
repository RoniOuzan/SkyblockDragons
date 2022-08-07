package me.maxiiiiii.skyblockdragons.dungeon.worlds.floor1;

import me.maxiiiiii.skyblockdragons.dungeon.DungeonWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class DungeonWorldFloor1 extends DungeonWorld {
    public static final World world = Bukkit.getWorld("DungeonWorld1");

    protected DungeonWorldFloor1() { // TODO change the spawn location
        super(world, 1, new Location(world, 0, 100, 0));
    }
}
