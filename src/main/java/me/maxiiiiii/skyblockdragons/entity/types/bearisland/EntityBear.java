package me.maxiiiiii.skyblockdragons.entity.types.bearisland;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToLocation;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearIsland;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wither;

import java.util.UUID;

public abstract class EntityBear extends EntityMaterial {
    public static final int TICKS_TO_SECONDS = 20;
    public int moveRate = 80;
    public int phase = 0;
    public int i = 0;
    public UUID uuid = null;
    public EntitySD entitySD;
    public FlyToLocation flyToLocation;
    public String color = "§8";
    public ItemMaterial crystal = null;
    public int tickRate = 1;

    public Location middleLoc;

    public EntityBear(String name, double health, double damage, double trueDamage, int moveRate, String color, int tickRate) {
        super(EntityType.WITHER, name, -1, health, 100, damage, trueDamage, new Equipment(), 100, 1, true, 0, 0);
        this.moveRate = moveRate;
        this.color = color;
        this.tickRate = tickRate;
    }

    public EntityBear(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed) {
        this(name, level, health, defense, damage, trueDamage, equipment, speed, 1);

    }

    public EntityBear(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.IRON_GOLEM, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, 0, 0);
    }

    @Override
    public void onSpawn(EntitySD entity) {
        if (entity.entity instanceof Wither){
            BearIsland.iron_golem = entity;
            BearIsland.bearDamage.clear();
            uuid = entity.entity.getUniqueId();
            entitySD = entity;
        }
    }
}
