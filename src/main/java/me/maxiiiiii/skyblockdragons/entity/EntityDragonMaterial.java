package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

public class EntityDragonMaterial extends EntityMaterial {
    public EntityDragonMaterial(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.ENDER_DRAGON, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, false, 0, 0);
    }
}
