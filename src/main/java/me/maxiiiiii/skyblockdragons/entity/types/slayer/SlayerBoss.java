package me.maxiiiiii.skyblockdragons.entity.types.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerType;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.entity.EntityType;

@Getter
public abstract class SlayerBoss extends EntityMaterial {
    private final SlayerType slayer;
    private final int tier;

    public SlayerBoss(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, double coins, SlayerType slayer, int tier, EntityDrop... drops) {
        super(entityType, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, combatXp, coins, drops);
        this.slayer = slayer;
        this.tier = tier;
    }
}
