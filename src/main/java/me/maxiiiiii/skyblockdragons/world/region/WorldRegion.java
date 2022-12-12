package me.maxiiiiii.skyblockdragons.world.region;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public abstract class WorldRegion {
    protected final String name;
    protected final WorldSD world;
    protected final List<WorldType> types;

    protected final List<WorldRegion> connectedRegions;

    protected WorldRegion(String name, WorldSD world, WorldType... types) {
        this.name = name;
        this.world = world;
        this.types = Arrays.asList(types);

        this.connectedRegions = new ArrayList<>();
        this.connectedRegions.add(this);
    }

    public boolean isType(WorldType type) {
        return this.types.contains(type);
    }

    protected abstract boolean inRegion(Location location);

    public boolean isInRegion(Location location) {
        return this.connectedRegions.stream().anyMatch(r -> r.inRegion(location));
    }

    public WorldRegion addRegion(WorldRegion region) {
        this.connectedRegions.add(region);
        return this;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
