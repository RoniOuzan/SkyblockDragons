package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.stream.Collectors;

public abstract class EntityDragon extends EntityMaterial {
    public EntityDragon(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.ENDER_DRAGON, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, 0, 0);
    }

    public void strikeAbility(Entity entity) {
        this.strikeAbility(entity, 0.3);
    }

    protected void strikeAbility(Entity entity, double percent) {
        for (PlayerSD player : entity.getWorld().getPlayers().stream().map(SkyblockDragons::getPlayer).collect(Collectors.toList())) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                entity.getWorld().strikeLightningEffect(player.getLocation());
                player.getPlayer().damage((player.getHealthStat() * percent)*10, entity);
            }
        }
    }

    @Override
    public void onDamage(EntitySD attacker, EntitySD entity, Double damage) {
        if (entity.getHealth() >= 50) {
            entity.entity.setNoDamageTicks(0);
            entity.entity.setMaximumNoDamageTicks(0);
            if (attacker instanceof PlayerSD) {
                PlayerSD player = (PlayerSD) attacker;
                TheEnd.dragonDamage.put(player, TheEnd.dragonDamage.getOrDefault(player, 0d) + damage);
            }
        }
    }

    @Override
    public void onDeath(EntitySD attacker, EntitySD entity) {
        deadDragon(entity);
    }

    private void deadDragon(EntitySD entity) {
        EnderDragon dragon = (EnderDragon) entity.entity;
        dragon.setPhase(EnderDragon.Phase.DYING);
        dragon.setAI(false);
        dragon.remove();
        entity.setHealth(0);
        entity.setMaximumNoDamageTicks(Integer.MAX_VALUE);
        entity.setNoDamageTicks(Integer.MAX_VALUE);
    }

    @Override
    public void onTick(EntitySD entity) {
        if (entity.getHealth() >= 10) {
            EnderDragon dragon = (EnderDragon) entity.entity;
            dragon.setPhase(EnderDragon.Phase.CIRCLING);
        }
        else{
            deadDragon(entity);
        }
    }
}
