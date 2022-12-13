package me.maxiiiiii.skyblockdragons.world.region;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Location;

public class CubicWorldRegion extends WorldRegion {
    private final Location location1;
    private final Location location2;

    public CubicWorldRegion(String name, WorldSD world, Location location1, Location location2, WorldType... types) {
        super(name, world, types);
        this.location1 = location1;
        this.location2 = location2;
    }

    @Override
    protected boolean inRegion(Location location) {
        return location.getWorld().equals(this.world.getWorld()) &&
                location.getX() >= Math.min(location1.getX(), location2.getX()) && location.getX() <= Math.max(location1.getX(), location2.getX()) &&
                location.getY() >= Math.min(location1.getY(), location2.getY()) && location.getY() <= Math.max(location1.getY(), location2.getY()) &&
                location.getZ() >= Math.min(location1.getZ(), location2.getZ()) && location.getZ() <= Math.max(location1.getZ(), location2.getZ());
    }
}
