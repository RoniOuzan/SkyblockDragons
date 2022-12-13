package me.maxiiiiii.skyblockdragons.world.region;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Location;

public class AllWorldRegion extends WorldRegion {
    public AllWorldRegion(String name, WorldSD world) {
        super(name, world);
    }

    @Override
    protected boolean inRegion(Location location) {
        return location.getWorld().equals(this.world.getWorld());
    }
}
