package me.maxiiiiii.skyblockdragons.entity.types.slayer.revenant;

import me.maxiiiiii.skyblockdragons.entity.types.slayer.SlayerBoss;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerType;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

public abstract class RevenantHorror extends SlayerBoss {
    public RevenantHorror(int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, double coins, int tier, EntityDrop... drops) {
        super(EntityType.ZOMBIE,
                ChatColor.AQUA + "Revenant Horror",
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
                SlayerType.REVENANT,
                tier,
                drops
        );
    }
}
