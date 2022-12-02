package me.maxiiiiii.skyblockdragons.entity.types.slayer.tarantula;

import me.maxiiiiii.skyblockdragons.entity.types.slayer.SlayerBoss;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerType;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public abstract class TarantulaBroodfather extends SlayerBoss {
    public TarantulaBroodfather(int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, double coins, int tier, EntityDrop... drops) {
        super(EntityType.SPIDER,
                ChatColor.DARK_RED + "Tarantula Broodfather",
                level,
                health,
                defense,
                damage,
                trueDamage,
                equipment,
                speed,
                knockbackResistance,
                combatXp,
                coins,
                SlayerType.TARANTULA,
                tier,
                drops
        );
    }

    @Override
    public EntityType getPassenger() {
        return EntityType.CAVE_SPIDER;
    }
}
