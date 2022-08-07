package me.maxiiiiii.skyblockdragons.dungeon;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.Location;

import java.util.Map;

public abstract class DungeonFloor {
    private final DungeonType dungeonType;
    private final Map<Location, EntityMaterial> entitySpawns;

    protected DungeonFloor(DungeonType dungeonType, Map<Location, EntityMaterial> entitySpawns) {
        this.dungeonType = dungeonType;
        this.entitySpawns = entitySpawns;
    }

    public void spawnEntities() {
        for (Location location : this.entitySpawns.keySet()) {
            new EntitySD(location, this.entitySpawns.get(location));
        }
    }
}
