package me.maxiiiiii.skyblockdragons.damage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUil;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

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

    public double damage(EntitySD player, EntitySD entity, double activeFerocity, DamageType damageType, double baseAbilityDamage, double abilityScaling) {
        if (player == null || entity == null) return 0;

        entity.setNoDamageTicks(0);
        entity.setMaximumNoDamageTicks(0);

        long millisecondsCD = 500;
        if (player instanceof PlayerSD) {
            millisecondsCD -= ((PlayerSD) player).getStats().getAttackSpeed().amount * 2.5;
        }
        if (!damageType.isMagic())
            if (cooldown(player, player.getDamageCooldown(damageType), millisecondsCD, false)) return -3;

        DamageCalculator damageCalculator = this.getDamage(player, entity, damageType, baseAbilityDamage, abilityScaling);
        double damage = damageCalculator.damage;
        boolean critHit = damageCalculator.critHit;

        if (player.getEnchantLevel(EnchantType.LIFE_STEAL) > 0) {
            player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + player.getHealth() * (player.getEnchantLevel(EnchantType.LIFE_STEAL) * 0.5)));
        }
        if (player.getEnchantLevel(EnchantType.SYPHON) > 0) {
            if (player instanceof PlayerSD)
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + player.getHealth() * ((player.getEnchantLevel(EnchantType.SYPHON) * 0.1 + 0.1)) * Math.min(((PlayerSD) player).getStats().critDamage.amount / 100, 10)));
        }
        if (player.getEnchantLevel(EnchantType.MANA_STEAL) > 0) {
            if (player instanceof PlayerSD)
                ((PlayerSD) player).getStats().mana.amount += ((PlayerSD) player).getStats().mana.amount * player.getEnchantLevel(EnchantType.MANA_STEAL) * 0.25;
        }

        // hologram
        String damageDisplay = ChatColor.GRAY + "" + Functions.getNumberFormat(damage);
        if (critHit) {
            damageDisplay = rainbowText(damage + "");
            damageDisplay = Functions.getNumberFormat(damageDisplay);
            damageDisplay = ChatColor.WHITE + "✧" + damageDisplay + ChatColor.WHITE + "✧";
        } else if (damageType.isMagic())
            damageDisplay = ChatColor.DARK_AQUA + "" + Functions.getNumberFormat(damage);

        Location hologram = entity.getLocation();
        double random = randomDouble(1, 1.5);
        hologram.add(entity.getLocation().getDirection().multiply(random));
        hologram.setY(entity.getLocation().getY() + random);
        createHologram(hologram, damageDisplay, 20);

        if (player instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) player;
            if (damageType.isNormal() && activeFerocity > 0 && !entity.isDead()) {
                if (Functions.chanceOf(activeFerocity)) {
                    playerSD.playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1f, 1f);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            damage(player, entity, activeFerocity - 100, damageType, baseAbilityDamage, abilityScaling);
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

                            EntityDamageEntityEvent playerDamageEntity = new EntityDamageEntityEvent(player, entity, DamageType.NORMAL, damage, true);
                            Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);

                            Particles.line(new ParticleUil(Particle.REDSTONE, 155, 0, 0, 0, 1), start, end, 0.05);
                        }
                    }.runTaskLater(SkyblockDragons.plugin, 5L);
                }
            }
        }
        return damage;
    }

    private static class DamageCalculator {
        private final double damage;
        private final boolean critHit;

        private DamageCalculator(double damage, boolean critHit) {
            this.damage = damage;
            this.critHit = critHit;
        }
    }

    public DamageCalculator getDamage(EntitySD attacker, EntitySD victim, DamageType damageType, double baseAbilityDamage, double abilityScaling) {
        Item item = attacker.getItems().getToolItem();
        double damage = 1;
        boolean critHit = false;

        double baseMultiplayer = 1;
        double postMultiplayer = 1;
        double damageReduction = victim.getDefense() / (victim.getDefense() + 100);
        double baseReducer = 1;

        EntitySD.Equipment equipment = attacker.getItems();
        ItemStack tool = equipment.getTool();
        ItemStack helmet = equipment.getHelmet();
        ItemStack chestplate = equipment.getChestplate();
        ItemStack leggings = equipment.getLeggings();
        ItemStack boots = equipment.getBoots();

        ToolMaterial toolMaterial = equipment.getToolMaterial();
        ArmorMaterial helmetMaterial = equipment.getHelmetMaterial();
        ArmorMaterial chestplateMaterial = equipment.getChestplateMaterial();
        ArmorMaterial leggingsMaterial = equipment.getLeggingsMaterial();
        ArmorMaterial bootsMaterial = equipment.getBootsMaterial();

        String fullSet = equipment.getFullSet();

        if (attacker instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) attacker;
            if (damageType.isMagic()) {
                damage = baseAbilityDamage * (1 + (playerSD.getStats().getIntelligence().amount / 100) * (playerSD.getStats().getAbilityScaling().amount + abilityScaling)); // damage formula / calculation
            } else if (item.getMaterial().getType() != ItemType.BOW || damageType == DamageType.PROJECTILE) {
                damage = (5 + playerSD.getStats().getDamage().amount) * (1 + (playerSD.getStats().getStrength().amount / 100)); // damage formula / calculation
            }
        } else {
            damage = attacker.type.getDamage();
        }

        // crit
        if (attacker instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) attacker;
            if (Functions.chanceOf(playerSD.getStats().getCritChance().amount) && damageType != DamageType.MAGIC) {
                damage *= (1 + (playerSD.getStats().getCritDamage().amount / 100));
                critHit = true;
            }
            baseMultiplayer += playerSD.getSkill().getCombatSkill().getLevel() * 0.04;

            if (damageType.isMagic()) {
                postMultiplayer += 0.5 * playerSD.getStats().getAbilityDamage().amount;
            }
        }

        // enchants
        if (damageType == DamageType.PROJECTILE) {
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.POWER) * 0.08;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.SNIPE) * (attacker.getLocation().distance(victim.getLocation()) / 5) * 0.01;
        } else {
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.SHARPNESS) * 0.05;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.SMITE, victim::isUndead) * 0.08;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.BANE_OF_ARTHROPODS, victim::isBaneOfArthropods) * 0.08;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.GIANT_KILLER) * (Math.max((victim.getHealth() - attacker.getHealth()) / attacker.getMaxHealth(), 0) / 10) * 0.1;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.TITAN_KILLER) * (Math.max((victim.getHealth() - attacker.getHealth()) / attacker.getMaxHealth(), 0) / 10) * 0.1;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.ENDER_SLAYER, victim::isEnderMob) * 0.12;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.DRAGON_HUNTER, () -> victim.entity instanceof EnderDragon) * 0.08;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.EXECUTE) * (victim.getMaxHealth() - victim.getHealth()) / victim.getMaxHealth() * 0.002;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.PROSECUTE) * victim.getHealth() / victim.getMaxHealth() * 0.001;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.CUBISM, victim::isCubism) * 0.1;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.FIRST_STRIKE, () -> Math.floor((victim.getHealth() / victim.getMaxHealth()) * 100d) >= 99) * 0.25;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.TRIPLE_STRIKE, () -> Math.floor((victim.getHealth() / victim.getMaxHealth()) * 100d) >= 67) * 0.1;
            baseMultiplayer += attacker.getEnchantLevel(EnchantType.IMPALING, victim::isImpaled) * 0.1;
        }

        if (damageType == DamageType.PROJECTILE && helmetMaterial == Items.get("GOLDEN_SKELETON_HELMET") && toolMaterial == Items.get("GOLDEN_SKELETON_BOW")) {
            baseMultiplayer += 0.2;
        }
        if (damageType == DamageType.MAGIC && fullSet.equals("Burning") && toolMaterial == Items.get("PIGMAN_DAGGER")) {
            baseMultiplayer += 0.2;
        }

        if (toolMaterial == Items.get("ENDER_BOW") && victim.type.getEntityType() == EntityType.ENDER_DRAGON || victim.type.getEntityType() == EntityType.ENDERMAN) {
            postMultiplayer += 1;
        }

        if (fullSet.equals("Strong Blood") && toolMaterial == Items.get("ASPECT_OF_THE_END"))
            baseMultiplayer += 0.2;

        if (attacker instanceof PlayerSD) {
            PlayerSD playerSD = (PlayerSD) attacker;
            if (playerSD.getPlayerPet().getActivePet() >= 0) {
                if (playerSD.getPetActive().getPetMaterial() == PetMaterial.get("ENDER_DRAGON") && (victim.type.getEntityType() == EntityType.ENDERMAN || victim.type.getEntityType() == EntityType.ENDER_DRAGON || victim.type.getEntityType() == EntityType.ENDERMITE)) {
                    baseMultiplayer += playerSD.getPetActive().getLevel() * 0.0025;
                }
            }
        }

        if (victim instanceof PlayerSD) {
            PlayerSD victimSD = (PlayerSD) victim;
            if (victimSD.getPlayerPet().getActivePet() >= 0) {
                if (victimSD.getPetActive().getPetMaterial() == PetMaterial.get("ENDERMAN")) {
                    baseReducer *= 1 - (victimSD.getPetActive().getLevel() * 0.003);
                }
            }
        }

        // final damage
        damage *= baseMultiplayer;
        damage *= postMultiplayer;
        damage *= baseReducer;
        damage *= 1 - damageReduction;
        damage = Math.floor(damage);

        return new DamageCalculator(damage, critHit);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamageEntity(EntityDamageEntityEvent e) {
        EntitySD attacker = e.getAttacker();
        EntitySD victim = e.getVictim();
        if (attacker instanceof PlayerSD)
            ((PlayerSD) attacker).setScoreboardScores();

        double damage;
        if (e.isFerocity()) {
            damage = e.getDamage();
        } else {
            if (e.getDamager() instanceof Player || (e.getDamager() instanceof Arrow && ((Arrow) e.getDamager()).getShooter() instanceof Player))
                damage = damage(attacker, victim, SkyblockDragons.getPlayer(e.getDamager().getUniqueId()).getStats().getFerocity().amount, e.getDamageType(), e.getBaseAbilityDamage(), e.getAbilityScaling());
            else
                damage = damage(attacker, victim, 0, e.getDamageType(), e.getBaseAbilityDamage(), e.getAbilityScaling());
        }
        if (damage == -3) {
            e.setCancelled(true);
            return;
        }
        victim.setAttacker(attacker);
        double health = victim.getHealth();
        e.setDamage(damage);
        victim.damage(0);
        victim.setHealth(Math.max(health - damage, 0));
        ChatColor color = ChatColor.GREEN;
        if (victim.getHealth() <= victim.getMaxHealth() / 4)
            color = ChatColor.RED;
        else if (victim.getHealth() <= victim.getMaxHealth() / 2)
            color = ChatColor.YELLOW;
        if (!(victim instanceof PlayerSD))
            victim.hologram.getStand().setCustomName(victim.getCustomName() + " " + color + victim.getHealth() + StatType.HEALTH.getIcon());
        EntityKnockbackEvent knockbackEvent = new EntityKnockbackEvent(e.getVictim(), e.getAttacker());
        Bukkit.getServer().getPluginManager().callEvent(knockbackEvent);

        victim.type.onSpawn(victim);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() == null) return;

        if (e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand) && !(e instanceof EntityDamageEntityEvent)) {
            EntityDamageEntityEvent playerDamageEntity;
            if (e.getDamager() instanceof Arrow)
                playerDamageEntity = new EntityDamageEntityEvent((Entity) ((Arrow) e.getDamager()).getShooter(), e.getEntity(), DamageType.PROJECTILE, 1, false);
            else
                playerDamageEntity = new EntityDamageEntityEvent(e.getDamager(), e.getEntity(), DamageType.NORMAL, 1, false);
            Bukkit.getServer().getPluginManager().callEvent(playerDamageEntity);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByBlockEvent e) {
        if (e.getDamager() == null) {
            EntitySD entity = EntitySD.get(e.getEntity());
            if (entity != null) {
                entity.kill();
                entity.hologram.remove();
            }
            return;
        }

        if (!e.getDamager().getType().isSolid()) {
            EntitySD entity = new EntitySD((LivingEntity) e.getEntity());

            if (cooldown(entity, entity.damageCooldownLava, 500, false)) {
                e.setCancelled(true);
                return;
            }

            e.setDamage(entity.getMaxHealth() / 20);
        }
    }

    @EventHandler
    public void onSlimeSplit(SlimeSplitEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {
        EntitySD entity = EntitySD.get(e.getEntity());
        entity.hologram.remove();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        e.getDrops().clear();
        if (e.getEntity().getKiller() == null) return;
        if (e.getEntity() instanceof Creature && !(e.getEntity() instanceof Player)) {
            EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());
            e.getDrops().clear();
            e.setDroppedExp(0);

            EntitySD.entities.remove(entity.getUniqueId());
            entity.hologram.remove();
            if (entity.getAttacker() == null) return;

            PlayerSD player = SkyblockDragons.getPlayer((PlayerSD) entity.getAttacker());
            player.giveSkill(SkillType.COMBAT, entity.type.combatXp);
            if (player.getEnchantLevel(EnchantType.TELEKINESIS) > 0) {
                for (Drop drop : entity.type.getDrops()) {
                    player.give(drop, entity);
                }
            } else {
                for (Drop drop : entity.type.getDrops()) {
                    ItemStack item = drop.generate(player, entity);
                    if (item != null) {
                        org.bukkit.entity.Item dropped = entity.getWorld().dropItem(entity.getLocation(), item);
                        dropped.addScoreboardTag(player.getName());
                    }
                }
            }

            if (player.getEnchantLevel(EnchantType.VAMPIRISM) > 0) {
                player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() * (1 + (player.getEnchantLevel(EnchantType.VAMPIRISM) * 0.01))));
            }

            Functions.createHologram(entity.getLocation().add(0, 3, 0), new ArrayList<>(Arrays.asList(ChatColor.GOLD + player.getName(), ChatColor.GOLD + "+" + Functions.getNumberFormat(entity.type.getCoins()))), 40);
            double coins = entity.type.getCoins();
            if (player.getEnchantLevel(EnchantType.SCAVENGER) > 0)
                coins += (player.getEnchantLevel(EnchantType.SCAVENGER) * EnchantType.SCAVENGER.getMultiplayers().get(player.getEnchantLevel(EnchantType.SCAVENGER)));
            player.addCoins(coins);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getEntity());

        if (player.getAttacker() == null)
            e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died!");
        else
            if (player.getAttacker() instanceof Player)
                e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + ((Player) player.getAttacker()).getDisplayName() + ChatColor.GRAY + "!");
            else
                e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + player.getAttacker().type.getName() + ChatColor.GRAY + "!");

        double amount = player.getCoins() / 2;
        player.removeCoins(amount);
        player.sendMessage(ChatColor.RED + "You died and lost " + Functions.getNumberFormat(amount) + " coins.");

        Functions.Wait(1L, () -> player.spigot().respawn());
    }
}
