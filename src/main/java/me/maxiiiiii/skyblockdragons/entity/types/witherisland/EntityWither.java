package me.maxiiiiii.skyblockdragons.entity.types.witherisland;

import me.maxiiiiii.skyblockdragons.entity.EntityAI;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

public abstract class EntityWither extends EntityMaterial {
    public int moveRate;
    public String color;
    public int tickRate;

    public EntityWither(String name, double health, double damage, double trueDamage, int moveRate, String color, int tickRate) {
        super(EntityType.WITHER, name, -1, health, 100, damage, trueDamage, new Equipment(), 100, 1, true, 0, 0);
        this.moveRate = moveRate;
        this.color = color;
        this.tickRate = tickRate;
    }

    @Override
    public void onSpawn(EntitySD entity) {
        entity.setMaximumNoDamageTicks(0);
        entity.setNoDamageTicks(0);
    }

    @Override
    protected EntityAI getAI(EntitySD entity) {
        return new WitherAI(entity, this.tickRate, this.moveRate);
    }
}

/*
    wither guide:
    Athena = Intelligence = execute more abilities
    Phanes = Health = more health
    Hermes = Speed = moves faster
    Demeter = Crit damage = deals more damage
    Ares = Strength = deals more damage
 */
