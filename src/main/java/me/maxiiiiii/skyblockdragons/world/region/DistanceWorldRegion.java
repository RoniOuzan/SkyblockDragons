package me.maxiiiiii.skyblockdragons.world.region;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Location;

public class DistanceWorldRegion extends WorldRegion {
    private final Location location;
    private final double distanceThreshold;

    public DistanceWorldRegion(String name, WorldSD world, Location location, double distanceThreshold, WorldType... types) {
        super(name, world, types);
        this.location = location;
        this.distanceThreshold = distanceThreshold;
    }

    @Override
    protected boolean inRegion(Location location) {
        if (location.getWorld() != this.location.getWorld())
            return false;

        return this.location.distance(location) <= this.distanceThreshold;
    }
}
