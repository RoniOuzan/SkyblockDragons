package me.maxiiiiii.skyblockdragons.damage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.material.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.material.Items;
import me.maxiiiiii.skyblockdragons.material.ToolMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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

    public double damage(EntitySD player, EntitySD entity, double activeFerocity, DamageType damageType) {
        entity.entity.setNoDamageTicks(0);
        entity.entity.setMaximumNoDamageTicks(0);

        long millisecondsCD = 500;
        if (player instanceof PlayerSD) {
            millisecondsCD -= ((PlayerSD) player).attackSpeed * 2.5;
        }
        if (cooldown(player, player.getDamageCooldown(damageType),  millisecondsCD)) return -3;

        DamageCalculator damageCalculator = this.getDamage(player, entity, damageType);
        double damage = damageCalculator.damage;
        boolean critHit = damageCalculator.critHit;

        // hologram
        String damageDisplay = ChatColor.GRAY + "" + damage;
        if (critHit) {
            damageDisplay = rainbowText(damage + "");
            damageDisplay = Functions.getNumberFormat(damageDisplay);
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        }
        Location hologram = entity.getLocation().clone();
        double random = randomDouble(1, 1.5);
        hologram.add(entity.getLocation().clone().getDirection().multiply(random));
        hologram.setY(entity.getLocation().getY() + random);
        createHologram(hologram, damageDisplay, 20);

        if (player instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) player;
            if (damageType.isNormal() && activeFerocity > 0 && !entity.entity.isDead()) {
                if (Functions.chanceOf(activeFerocity)) {
                    playerSD.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 1f);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            damage(player, entity, activeFerocity - 100, damageType);
                            playerSD.playSound(player.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1f, 10f);
                            playerSD.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 0.25f, 0.25f);
                            Location start = entity.getLocation().clone();
                            Location end = entity.getLocation().clone();

                            if (Math.random() < 0.5) {
                                start.add(1.5, 0.5, 0);
                                end.add(-1.5, -1.5, 0);
                            } else {
                                start.add(-1.5, 0.5, 0);
                                end.add(1.5, -1.5, 0);
                            }

                            EntityDamageEntity playerDamageEntity = new EntityDamageEntity(player.entity, entity.entity, DamageType.NORMAL, damage, true);
                            Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);

                            particleLine(start, end, Particle.REDSTONE, 155, 0, 0);
                        }
                    }.runTaskLater(SkyblockDragons.plugin, 5L);
                }
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

    public DamageCalculator getDamage(EntitySD player, EntitySD entity, DamageType damageType) {
        Item item = new Item(player.getEquipment().getItemInMainHand());
        double damage;
        boolean critHit = false;

        double baseMultiplayer = 1;
        double postMultiplayer = 1;
        double damageReduction = entity.getDefense() / (entity.getDefense() + 100);

        EntitySD.Equipment equipment = player.getItems();
        ItemStack tool = equipment.tool;
        ItemStack helmet = equipment.helmet;
        ItemStack chestplate = equipment.chestplate;
        ItemStack leggings = equipment.leggings;
        ItemStack boots = equipment.boots;

        ToolMaterial toolMaterial = equipment.toolMaterial;
        ArmorMaterial helmetMaterial = equipment.helmetMaterial;
        ArmorMaterial chestplateMaterial = equipment.chestplateMaterial;
        ArmorMaterial leggingsMaterial = equipment.leggingsMaterial;
        ArmorMaterial bootsMaterial = equipment.bootsMaterial;

        String fullSet = equipment.fullSet;

        if (player instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) player;
            if (damageType.isMagic()) {
                damage = playerSD.getBaseAbilityDamage() * (1 + ((playerSD.getIntelligence() * playerSD.getAbilityScaling()) / 100)); // damage formula / calculation
            }
            else if (item.getMaterial().getType() != ItemType.BOW || damageType == DamageType.PROJECTILE) {
                damage = (5 + playerSD.getDamage()) * (1 + (playerSD.getStrength() / 100)); // damage formula / calculation
            }
            else {
                damage = 1;
            }
        } else {
            damage = player.type.getDamage();
        }

        // crit
        if (player instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) player;
            if (Functions.chanceOf(playerSD.getCritChance()) && damageType != DamageType.MAGIC) {
                damage *= (1 + (playerSD.getCritDamage() / 100));
                critHit = true;
            }
            baseMultiplayer += playerSD.getSkill().getCombatSkill().getLevel() * 0.04;

            if (damageType.isMagic()) {
                postMultiplayer += 0.5 * playerSD.getAbilityDamage();
            }
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

        if (helmetMaterial == Items.get("GOLDEN_SKELETON_HELMET") && toolMaterial == Items.get("GOLDEN_SKELETON_BOW")) {
            baseMultiplayer += 0.2;
        }

        // final damage
        damage *= baseMultiplayer;
        damage *= postMultiplayer;
        damage *= 1 - damageReduction;
        damage = Math.floor(damage);

        return new DamageCalculator(damage, critHit);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamageEntity(EntityDamageEntity e) {
        EntitySD attacker = e.getAttacker();
        EntitySD victim = e.getVictim();
        if (attacker instanceof PlayerSD)
            ((PlayerSD) attacker).setScoreboardScores();

        double damage;
        if (e.isFerocity()) {
            damage = e.getDamage();
        } else {
            if (e.getDamager() instanceof Player || (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player))
                damage = damage(attacker, victim, SkyblockDragons.getPlayer(e.getDamager().getUniqueId()).getFerocity(), e.getDamageType());
            else
                damage = damage(attacker, victim, 0, e.getDamageType());
        }
        if (damage == -3) {
            e.setCancelled(true);
            return;
        }
        victim.setAttacker(attacker);
        double health = victim.entity.getHealth();
        victim.entity.damage(0);
        victim.entity.setHealth(Math.max(health - damage, 0));
        EntityKnockbackEvent knockbackEvent = new EntityKnockbackEvent(e.getVictim(), e.getAttacker());
        Bukkit.getServer().getPluginManager().callEvent(knockbackEvent);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() == null) return;

        if (e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand) && !(e instanceof EntityDamageEntity)) {
            EntityDamageEntity playerDamageEntity;
            if (e.getDamager() instanceof Arrow)
                playerDamageEntity = new EntityDamageEntity((Entity) ((Arrow) e.getDamager()).getShooter(), e.getEntity(), DamageType.PROJECTILE, 1, false);
            else
                playerDamageEntity = new EntityDamageEntity(e.getDamager(), e.getEntity(), DamageType.NORMAL, 1, false);
            Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Creature && !(e.getEntity() instanceof Player)) {
            EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());
            e.getDrops().clear();
            e.setDroppedExp(0);

            EntitySD.entities.remove(entity.entity.getUniqueId());
            if (entity.getAttacker() == null) return;

            PlayerSD player = SkyblockDragons.getPlayer((PlayerSD) entity.getAttacker());
            player.getSkill().give(SkillType.COMBAT, entity.type.combatXp);
            if (player.getEnchantLevel(EnchantType.TELEKINESIS) > 0)
                for (ItemDrop drop : entity.type.drops) {
                    player.give(drop);
                }
            else
                for (ItemDrop drop : entity.type.drops) {
                    ItemStack item = drop.generate();
                    if (item != null) {
                        org.bukkit.entity.Item dropped = entity.entity.getWorld().dropItem(entity.entity.getLocation(), item);
                        dropped.addScoreboardTag(player.getName());
                    }
                }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getEntity());

        if (player.getAttacker() == null)
            e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died!");
        else
            if (player.getAttacker() instanceof Player)
                e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + ((Player) player.getAttacker().entity).getDisplayName() + ChatColor.GRAY + "!");
            else
                e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + player.getAttacker().type.getName() + ChatColor.GRAY + "!");

        Functions.Wait(1L, () -> player.spigot().respawn());
    }
}
