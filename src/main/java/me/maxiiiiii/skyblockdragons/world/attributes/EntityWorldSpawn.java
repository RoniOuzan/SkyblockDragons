package me.maxiiiiii.skyblockdragons.world.attributes;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Getter
public class EntityWorldSpawn implements WorldAttribute {
    private final Location location;
    private final double minY;
    private final double distance;
    private final List<Entry<EntityMaterial, Double>> entities;
    private final int amount;
    private final List<Material> allowedBlocks;

    /**
     * @param location the center of the spawns. the y of the location is the max y to spawn a mob
     * @param minY the minimum y level
     * @param distance the distance from the location to spawn
     * @param entities every entityMaterial with his chances to spawn [0, 1.0]
     * @param amount the amount of spawns to create
     * @param allowedBlocks the materials that the mob can spawn on
     */
    public EntityWorldSpawn(Location location, double minY, double distance, List<Entry<EntityMaterial, Double>> entities, int amount, Material... allowedBlocks) {
        this.location = location;
        this.minY = minY;
        this.distance = distance;
        this.entities = entities;
        this.amount = amount;
        this.allowedBlocks = Arrays.asList(allowedBlocks);
    }

    public EntityMaterial getEntityMaterial() {
        return Functions.getRandomWithChances(entities);
    }
}
