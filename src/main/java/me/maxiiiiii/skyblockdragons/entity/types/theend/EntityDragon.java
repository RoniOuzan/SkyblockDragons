package me.maxiiiiii.skyblockdragons.entity.types.theend;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.PercentEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.stream.Collectors;

public abstract class EntityDragon extends EntityMaterial {
    public EntityDragon(String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance) {
        super(EntityType.ENDER_DRAGON, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, false, 0, 0);
    }

    public void strikeAbility(EntitySD entity) {
        this.strikeAbility(entity, 30);
    }

    protected void strikeAbility(EntitySD entity, double percent) {
        for (PlayerSD player : entity.getWorld().getPlayers().stream().map(SkyblockDragons::getPlayer).collect(Collectors.toList())) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                entity.getWorld().strikeLightningEffect(player.getLocation());
                player.damage(new PercentEntityDamageEntity(entity, player, percent));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageEvent e) {
        if (e.getVictim().getMaterial() == this && e.getAttacker() instanceof PlayerSD) {
            PlayerSD attacker = (PlayerSD) e.getAttacker();
            TheEnd.dragonDamage.put(attacker, TheEnd.dragonDamage.getOrDefault(attacker, 0d) + e.getFinalDamage());
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().material instanceof EntityDragon) {
            deadDragon(e.getEntity());
        }
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

//    @Override
//    public void onTick(EntitySD entity) {
//        if (entity.getHealth() >= 10) {
//            EnderDragon dragon = (EnderDragon) entity.entity;
//            dragon.setPhase(EnderDragon.Phase.CIRCLING);
//        } else {
//            deadDragon(entity);
//        }
//    }
}
