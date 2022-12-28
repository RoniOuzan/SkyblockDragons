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
    private final double minDistance;
    private final double maxDistance;
    private final List<Entry<EntityMaterial, Double>> entities;
    private final int amount;
    private final List<Material> allowedBlocks;

    /**
     * @param location the center of the spawns. the y of the location is the max y to spawn a mob
     * @param minY the minimum y level
     * @param minDistance the minimum distance from the location to spawn
     * @param maxDistance the maximum distance from the location to spawn
     * @param entities every entityMaterial with his chances to spawn [0, 1.0]
     * @param amount the amount of spawns to create
     * @param allowedBlocks the materials that the mob can spawn on
     */
    public EntityWorldSpawn(Location location, double minY, double minDistance, double maxDistance, List<Entry<EntityMaterial, Double>> entities, int amount, Material... allowedBlocks) {
        this.location = location;
        this.minY = minY;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.entities = entities;
        this.amount = amount;
        this.allowedBlocks = Arrays.asList(allowedBlocks);
    }

    /**
     * @param location the center of the spawns. the y of the location is the max y to spawn a mob
     * @param minY the minimum y level
     * @param distance the distance from the location to spawn
     * @param entities every entityMaterial with his chances to spawn [0, 1.0]
     * @param amount the amount of spawns to create
     * @param allowedBlocks the materials that the mob can spawn on
     */
    public EntityWorldSpawn(Location location, double minY, double distance, List<Entry<EntityMaterial, Double>> entities, int amount, Material... allowedBlocks) {
        this(location, minY, 0, distance, entities, amount, allowedBlocks);
    }

    public EntityMaterial getEntityMaterial() {
        return Functions.getRandomWithChances(entities);
    }

    public Location generateSpawn() {
        double angle = Functions.randomDouble(-Math.PI, Math.PI);
        double distance = Functions.randomDouble(this.minDistance, this.maxDistance);
        return Functions.getLowestBlock(
                this.getLocation().clone().add(
                        Math.sin(angle) * distance,
                        0,
                        Math.cos(angle) * distance
                )
        ).getLocation();
    }
}
