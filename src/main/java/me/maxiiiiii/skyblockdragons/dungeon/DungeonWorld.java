package me.maxiiiiii.skyblockdragons.dungeon;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Location;
import org.bukkit.World;

public abstract class DungeonWorld extends WorldSD {
    public static final String WORLD_NAME = "Dungeon ";

    protected final Location location;

    protected DungeonWorld(World world, int worldNumber, Location location) {
        super(world, WORLD_NAME + worldNumber, Warp.UNWARPABLE, WorldType.COMBAT);
        this.location = location;
    }
}
