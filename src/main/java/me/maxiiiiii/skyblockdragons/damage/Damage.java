package me.maxiiiiii.skyblockdragons.damage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;
import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Damage implements Listener {
    public enum DamageType {
        NORMAL, PROJECTILE, MAGIC, CRITICAL_MAGIC, TRUE, FALL, FIRE;

        public boolean isNormal() {
            return this == NORMAL || this == TRUE || this == PROJECTILE;
        }

        public boolean isMagic() {
            return this == MAGIC || this == CRITICAL_MAGIC;
        }
    }

    public double damage(PlayerSD player, EntitySD entity, double activeFerocity, DamageType damageType) {
        entity.entity.setNoDamageTicks(0);
        entity.entity.setMaximumNoDamageTicks(0);

        if (cooldown(player, player.getDamageCooldown(damageType),  500 - (long) (player.attackSpeed * 2.5), false)) return -3;

        DamageCalculator damageCalculator = this.getDamage(player, entity, damageType);
        double damage = damageCalculator.damage;
        boolean critHit = damageCalculator.critHit;

        // hologram
        String damageDisplay = ChatColor.GRAY + "" + damage;
        if (critHit) {
            damageDisplay = rainbow(damage + "");
            damageDisplay = Functions.getNumberFormat(damageDisplay);
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        }
        Location hologram = entity.getLocation().clone();
        double random = randomDouble(1, 1.5);
        hologram.add(entity.getLocation().clone().getDirection().multiply(random));
        hologram.setY(entity.getLocation().getY() + random);
        createHologram(hologram, damageDisplay, 20);

        if (damageType.isNormal() && activeFerocity > 0 && !entity.entity.isDead()) {
            double chance = randomDouble(0, 100);
            if (chance <= activeFerocity) {
                player.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 1f);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        damage(player, entity, activeFerocity - 100, damageType);
                        player.playSound(player.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1f, 10f);
                        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 0.25f, 0.25f);
                        Location start = entity.getLocation().clone();
                        Location end = entity.getLocation().clone();

                        if (Math.random() < 0.5) {
                            start.add(1.5, 0.5, 0);
                            end.add(-1.5, -1.5, 0);
                        } else {
                            start.add(-1.5, 0.5, 0);
                            end.add(1.5, -1.5, 0);
                        }

                        PlayerDamageEntity playerDamageEntity = new PlayerDamageEntity(player, entity.entity, DamageType.NORMAL, damage, true);
                        Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);

                        particleLine(start, end, Particle.REDSTONE, 155, 0, 0);
                    }
                }.runTaskLater(SkyblockDragons.getInstance(), 5L);
            }
        }
        return damage;
    }

    private static class DamageCalculator {
        private double damage;
        private boolean critHit;

        private DamageCalculator(double damage, boolean critHit) {
            this.damage = damage;
            this.critHit = critHit;
        }
    }

    public DamageCalculator getDamage(PlayerSD player, EntitySD entity, DamageType damageType) {
        Item item = new Item(player.getEquipment().getItemInMainHand());
        double damage;
        boolean critHit = false;

        double baseMultiplayer = 1;
        double postMultiplayer = 1;
        double damageReduction = entity.getDefense() / (entity.getDefense() + 100);

        if (damageType.isMagic())
            damage = player.getBaseAbilityDamage() * (1 + ((player.getIntelligence() * player.getAbilityScaling()) / 100)); // damage formula / calculation
        else if (item.getMaterial().getType() != ItemType.BOW || damageType == DamageType.PROJECTILE)
            damage = (5 + player.getDamage()) * (1 + (player.getStrength() / 100)); // damage formula / calculation
        else
            damage = 1;

        // crit
        if (randomDouble(1, 100) <= player.getCritChance() && damageType != DamageType.MAGIC) {
            damage *= (1 + (player.getCritDamage() / 100));
            critHit = true;
        }
        baseMultiplayer += player.getSkill().getCombatSkill().getLevel() * 0.04;

        if (damageType.isMagic()) {
            postMultiplayer += 0.5 * player.getAbilityDamage();
        }

        // enchants
        if (damageType == DamageType.PROJECTILE) {
            baseMultiplayer += player.getEnchantLevel(EnchantType.POWER) * 0.08;
            baseMultiplayer += player.getEnchantLevel(EnchantType.SNIPE) * (player.getLocation().distance(entity.getLocation()) / 5) * 0.01;
        } else {
            baseMultiplayer += player.getEnchantLevel(EnchantType.SHARPNESS) * 0.05;
            baseMultiplayer += player.getEnchantLevel(EnchantType.SMITE, entity::isUndead) * 0.08;
            baseMultiplayer += player.getEnchantLevel(EnchantType.BANE_OF_ARTHROPODS, entity::isBaneOfArthropods) * 0.08;
            baseMultiplayer += player.getEnchantLevel(EnchantType.GIANT_KILLER) * (Math.max((entity.getHealth() - player.getHealth()) / player.getMaxHealth(), 0) / 10) * 0.1;
            baseMultiplayer += player.getEnchantLevel(EnchantType.TITAN_KILLER) * (Math.max((entity.getHealth() - player.getHealth()) / player.getMaxHealth(), 0) / 10) * 0.1;
            baseMultiplayer += player.getEnchantLevel(EnchantType.ENDER_SLAYER, entity::isEnderMob) * 0.12;
            baseMultiplayer += player.getEnchantLevel(EnchantType.DRAGON_HUNTER, () -> entity.entity instanceof EnderDragon) * 0.08;
            baseMultiplayer += player.getEnchantLevel(EnchantType.EXECUTE) * (entity.getMaxHealth() - entity.getHealth()) / entity.getMaxHealth() * 0.002;
            baseMultiplayer += player.getEnchantLevel(EnchantType.PROSECUTE) * entity.getHealth() / entity.getMaxHealth() * 0.001;
            baseMultiplayer += player.getEnchantLevel(EnchantType.CUBISM, entity::isCubism) * 0.1;
            baseMultiplayer += player.getEnchantLevel(EnchantType.FIRST_STRIKE, () -> Math.floor((entity.getHealth() / entity.getMaxHealth()) * 100d) >= 99) * 0.25;
            baseMultiplayer += player.getEnchantLevel(EnchantType.TRIPLE_STRIKE, () -> Math.floor((entity.getHealth() / entity.getMaxHealth()) * 100d) >= 67) * 0.1;
            baseMultiplayer += player.getEnchantLevel(EnchantType.IMPALING, entity::isImpaled) * 0.1;
        }

        // final damage
        damage *= baseMultiplayer;
        damage *= postMultiplayer;
        damage *= 1 - damageReduction;
        damage = Math.floor(damage);

        return new DamageCalculator(damage, critHit);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(PlayerDamageEntity e) {
        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getDamager());
        EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());
        player.setScoreboardScores();

        double damage;
        if (e.isFerocity()) {
            damage = e.getDamage();
        } else {
            damage = damage(player, entity, players.get(e.getDamager().getUniqueId()).getFerocity(), e.getDamageType());
        }
        if (damage == -3) {
            e.setCancelled(true);
            return;
        }
        entity.setAttacker(player);
        e.setDamage(damage);
        ((LivingEntity) e.getEntity()).damage(damage);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player || e.getDamager() instanceof Arrow) {
            if (e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand) && !(e instanceof PlayerDamageEntity)) {
                PlayerDamageEntity playerDamageEntity;
                if (e.getDamager() instanceof Arrow)
                    playerDamageEntity = new PlayerDamageEntity((Player) ((Arrow) e.getDamager()).getShooter(), e.getEntity(), DamageType.PROJECTILE, 1, false);
                else
                    playerDamageEntity = new PlayerDamageEntity((Player) e.getDamager(), e.getEntity(), DamageType.NORMAL, 1, false);
                Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Creature) {
            if (e.getEntity() instanceof Player) {
                Player player = (Player) e.getEntity();
                Functions.Wait(1L, () -> player.spigot().respawn());
            }
            EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());
            e.getDrops().clear();
            e.setDroppedExp(0);

            EntitySD.entities.remove(entity.entity.getUniqueId());
            if (entity.getKiller() == null) return;

            PlayerSD player = SkyblockDragons.getPlayer(entity.getKiller());
            player.getSkill().give(SkillType.COMBAT, entity.type.combatXp);
            if (player.getEnchantLevel(EnchantType.TELEKINESIS) > 0)
                for (ItemDrop drop : entity.type.drops) {
                    player.give(drop);
                }
            else
                for (ItemDrop drop : entity.type.drops) {
                    ItemStack item = drop.generate();
                    if (item != null) {
                        entity.entity.getWorld().dropItem(entity.entity.getLocation(), item);
                    }
                }
        }
    }
}
