package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.stream.Collectors;

public abstract class EntityDragon extends EntityMaterial {
    public EntityDragon(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.ENDER_DRAGON, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, false, 0, 0);
    }

    public void strikeAbility(Entity entity) {
        this.strikeAbility(entity, 0.3);
    }

    protected void strikeAbility(Entity entity, double percent) {
        for (PlayerSD player : entity.getWorld().getPlayers().stream().map(SkyblockDragons::getPlayer).collect(Collectors.toList())) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                entity.getWorld().strikeLightningEffect(player.getLocation());
                player.makeDamage(entity, Damage.DamageType.TRUE, player.getHealthStat() * percent);
            }
        }
    }
}
