package me.maxiiiiii.skyblockdragons.dungeon;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Location;
import org.bukkit.World;

public abstract class DungeonWorld extends WorldSD {
    public static final String WORLD_NAME = "Dungeon ";

    protected DungeonWorld(World world, int worldNumber, Location spawn) {
        super(world, WORLD_NAME + worldNumber, spawn, WorldType.COMBAT);
    }
}
