package me.maxiiiiii.skyblockdragons.world.attributes;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Location;

@Getter
public class LaunchPad implements WorldAttribute {
    private final Location location;
    private final double distanceThreshold;
    private final Warp warpTo;

    public LaunchPad(Location location, double distanceThreshold, Warp warpTo) {
        this.location = location;
        this.distanceThreshold = distanceThreshold;
        this.warpTo = warpTo;
    }

    public boolean isInThreshold(Location location) {
        return Math.abs(location.distance(this.location)) < this.distanceThreshold;
    }
}
