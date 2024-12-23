package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.interfaces.ExplosionDamage;
import me.maxiiiiii.skyblockdragons.damage.interfaces.FireDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.PreciseFallEntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.ProjectileEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.entity.types.other.PlayerEntity;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.drops.PlayerGetDropEvent;
import me.maxiiiiii.skyblockdragons.item.drops.UpdateDropChanceEvent;
import me.maxiiiiii.skyblockdragons.item.drops.types.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.ItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.UpdateManaCostEvent;
import me.maxiiiiii.skyblockdragons.item.stats.*;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetCoinsFromEntityEvent;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetExperienceEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static me.maxiiiiii.skyblockdragons.item.enchants.EnchantType.*;

public class EnchantListeners implements Listener {
    private final Map<PlayerSD, CounterStrike> counterStrikes = new HashMap<>();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        PlayerSD attacker = e.getPlayerAttacker();
        Item tool = attacker.getItems().getTool();
        Map<EnchantType, Short> enchants = tool.getModifiers().getEnchants();

        enchant(attacker, enchants, SHARPNESS, s -> e.getDamage().getMultiplier().addBase(s));

        if (e.getDamage() instanceof MeleeEntityDamageEntity || e.getDamage() instanceof ProjectileEntityDamageEntity) {
            enchant(attacker, enchants, CUBISM, s -> {
                if (e.getVictim().isCubism())
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, DRAGON_HUNTER, s -> {
                if (e.getVictim().getMaterial() instanceof EntityDragon)
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, IMPALING, s -> {
                if (e.getVictim().isImpaled())
                    e.getDamage().getMultiplier().addBase(s);
            });
        } else if (e.getDamage() instanceof MeleeEntityDamageEntity) {
            enchant(attacker, enchants, BANE_OF_ARTHROPODS, s -> {
                if (e.getVictim().isBaneOfArthropods())
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, ENDER_SLAYER, s -> {
                if (e.getVictim().isEndMob())
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, EXECUTE, s ->
                    e.getDamage().getMultiplier().addBase(
                            (
                                    (e.getVictim().getMaxHealth() - e.getVictim().getHealth()) / e.getVictim().getMaxHealth()
                            ) * s)
            );

            enchant(attacker, enchants, FIRST_STRIKE, s -> {
                if (e.getVictim().getHealthPercentage() > 0.99)
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, GIANT_KILLER, s -> {
                if (e.getVictim().getHealthPercentage() > attacker.getHealthPercentage())
                    e.getDamage().getMultiplier().addBase(Math.min((e.getVictim().getHealthPercentage() - attacker.getHealthPercentage()) * s, 50));
            });

            enchant(attacker, enchants, PROSECUTE, s -> e.getDamage().getMultiplier().addBase(e.getVictim().getHealthPercentage() * s));

            enchant(attacker, enchants, ENDER_SLAYER, s -> {
                if (e.getVictim().isUndead())
                    e.getDamage().getMultiplier().addBase(s);
            });

            enchant(attacker, enchants, TITAN_KILLER, s -> e.getDamage().getMultiplier().addBase(Math.min(Math.floor(e.getVictim().getDefense() / 100) * s, 60)));

            enchant(attacker, enchants, TRIPLE_STRIKE, s -> {
                if (e.getVictim().getHealthPercentage() > 0.67)
                    e.getDamage().getMultiplier().addBase(s);
            });
        } else if (e.getDamage() instanceof ProjectileEntityDamageEntity) {
            enchant(attacker, enchants, POWER, s -> e.getDamage().getMultiplier().addBase(s));

            enchant(attacker, enchants, SNIPE, s -> e.getDamage().getMultiplier().addBase(Math.floor(attacker.getLocation().distance(e.getVictim().getLocation())) * s));
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateDamage(UpdateEntityDamageEvent e) {
        if (e.getVictim() instanceof PlayerSD) return;

        PlayerSD victim = (PlayerSD) e.getVictim();

        if (e.getDamage() instanceof ExplosionDamage) {
            enchantArmor(victim, BLAST_PROTECTION, s -> e.getDamage().getVictimStats().add(StatTypes.DEFENSE, s));
        } else if (e.getDamage() instanceof FireDamage) {
            enchantArmor(victim, FIRE_PROTECTION, s -> e.getDamage().getVictimStats().add(StatTypes.DEFENSE, s));
        } else if (e.getDamage() instanceof ProjectileEntityDamageEntity) {
            enchantArmor(victim, PROJECTILE_PROTECTION, s -> e.getDamage().getVictimStats().add(StatTypes.DEFENSE, s));
        } else if (e.getDamage() instanceof PreciseFallEntityDamage) {
            enchantArmor(victim, FEATHER_FALLING, s -> e.getDamage().getMultiplier().addBase(-s));
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamage(UpdateEntityDamageEntityEvent e) {
        PlayerSD player = e.getPlayerAttacker();
        Item tool = player.getItems().getTool();
        Map<EnchantType, Short> enchants = tool.getModifiers().getEnchants();

        enchant(player, enchants, LIFE_STEAL, s -> player.heal(player.getMaxHealth() * s));

        enchant(player, enchants, SYPHON, s -> player.heal(player.getMaxHealth() * s * Math.min(Math.floor(player.getStats().getCritDamage().get() / 100), 10)));

        enchant(player, enchants, MANA_STEAL, s -> player.getStats().add(StatTypes.MANA, player.getStats().getIntelligence().get() * (s / 100)));

        enchant(player, enchants, COUNTER_STRIKE, s -> {
            if (!counterStrikes.containsKey(player)) counterStrikes.put(player, new CounterStrike());
            if (!counterStrikes.get(player).entities.contains(e.getVictim())) {
                counterStrikes.put(player, counterStrikes.get(player).add(e.getVictim()));
            }
        });
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateStats(UpdateStatsEvent e) {
        PlayerSD player = e.getPlayer();

        if (counterStrikes.containsKey(player)) {
            if (SkyblockDragons.getCurrentTimeInSeconds() - counterStrikes.get(player).hitAt <= 7)
                counterStrikes.remove(player);
            else
                e.getStats().add(StatTypes.DEFENSE, 10);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onDeath(EntityDeathEvent e) {
        if (!(e.getKiller() instanceof PlayerSD)) return;

        PlayerSD player = (PlayerSD) e.getKiller();
        Map<EnchantType, Short> enchants = e.getKillerEquipment().getTool().getModifiers().getEnchants();

        enchant(player, enchants, VAMPIRISM, s -> player.heal(player.getMaxHealth() * s));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerGetCoinsFromEntity(PlayerGetCoinsFromEntityEvent e) {
        PlayerSD player = e.getPlayer();
        Map<EnchantType, Short> enchants = player.getItems().getTool().getModifiers().getEnchants();

        enchant(player, enchants, SCAVENGER, s -> e.getMultiplier().addBase(s));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateItemStats(UpdateItemStatsEvent e) {
        PlayerSD player = e.getPlayer();
        ItemStats stats = e.getStats();
        Map<EnchantType, Short> enchants = stats.getItem().getModifiers().getEnchants();

        enchant(player, enchants, CRITICAL, s -> stats.add(StatTypes.CRIT_DAMAGE, s));

        enchant(player, enchants, VICIOUS, s -> stats.add(StatTypes.FEROCITY, s));

        enchant(player, enchants, BIG_BRAIN, s -> stats.add(StatTypes.INTELLIGENCE, s));

        enchant(player, enchants, TRUE_PROTECTION, s -> stats.add(StatTypes.TRUE_DEFENSE, s));

        enchant(player, enchants, SMARTY_PANTS, s -> stats.add(StatTypes.INTELLIGENCE, s));

        enchant(player, enchants, SUGAR_RUSH, s -> stats.add(StatTypes.SPEED, s));

        enchant(player, enchants, GROWTH, s -> stats.add(StatTypes.HEALTH, s));

        enchant(player, enchants, PROTECTION, s -> stats.add(StatTypes.DEFENSE, s));

        enchant(player, enchants, REJUVENATE, s -> stats.add(StatTypes.VITALITY, s));

        enchant(player, enchants, FORTUNE, s -> stats.add(StatTypes.MINING_FORTUNE, s));

        enchant(player, enchants, EFFICIENCY, s -> stats.add(StatTypes.MINING_SPEED, s));

        enchant(player, enchants, CHIMERA, s -> {
            if (player.getActivePet() == null) return;

            Stats petStats = new Stats(player.getActivePet().getStats().toList());
            petStats.multiply(s);
            stats.add(petStats);
        });
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateDrop(UpdateDropChanceEvent e) {
        PlayerSD player = e.getPlayer();
        Map<EnchantType, Short> enchants = player.getItems().getTool().getModifiers().getEnchants();

        enchant(player, enchants, LUCK, s -> {
            if (e.getDrop() instanceof ItemDrop && !(e.getDrop() instanceof ItemRareDrop))
                e.getChanceMultiplier().addBase(s);
        });
        enchant(player, enchants, LOOTING, s -> {
            if (e.getDrop() instanceof ItemDrop && !(e.getDrop() instanceof ItemRareDrop))
                e.getAmountMultiplier().addBase(s);
        });
        enchant(player, enchants, CHANCE, s -> {
            if (e.getDrop() instanceof ItemDrop && !(e.getDrop() instanceof ItemRareDrop))
                e.getAmountMultiplier().addBase(s);
        });
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateManaCost(UpdateManaCostEvent e) {
        if (e.getPlayer() == null) return;

        PlayerSD player = e.getPlayer();
        Map<EnchantType, Short> enchants = e.getItem().getModifiers().getEnchants();

        enchant(player, enchants, ULTIMATE_WISE, s -> e.getMultiplier().addPost(-s));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerShoot(ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player)) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getEntity().getShooter());
        Map<EnchantType, Short> enchants = player.getItems().getTool().getModifiers().getEnchants();

        enchant(player, enchants, AIMING, s -> {
            AtomicBoolean hasTarget = new AtomicBoolean(false);
            Functions.While(() -> !e.getEntity().isDead() && !hasTarget.get(), 4L, i -> {
                for (EntitySD entity : Functions.loopEntities(e.getEntity().getLocation(), s)) {
                    if (!(entity.getMaterial() instanceof PlayerEntity)) {
                        hasTarget.set(true);

                        e.getEntity().setVelocity(entity.getEyeLocation().subtract(e.getEntity().getLocation()).toVector().normalize());
                    }
                }
            });
        });
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void updateExperience(PlayerGetExperienceEvent e) {
        PlayerSD player = e.getPlayer();
        Map<EnchantType, Short> enchants = player.getItems().getTool().getModifiers().getEnchants();

        enchant(player, enchants, EXPERIENCE, s -> e.getMultiplier().addBase(s));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPlayerGetDrop(PlayerGetDropEvent e) {
        PlayerSD player = e.getPlayer();
        Map<EnchantType, Short> enchants = player.getItems().getTool().getModifiers().getEnchants();

        enchant(player, enchants, TELEKINESIS, s -> e.setTelekinesis(true));
    }

    private void enchant(PlayerSD player, Map<EnchantType, Short> enchants, EnchantType enchant, Consumer<Double> runnable) {
        if (!enchant.getRequirements().hasRequirements(player)) return;

        if (enchants.getOrDefault(enchant, (short) 0) > 0)
            runnable.accept(enchant.getMultipliers().get(enchants.get(enchant)));
    }

    private void enchantArmor(PlayerSD player, EnchantType enchant, Consumer<Double> runnable) {
        for (Item item : player.getItems().getArmor()) {
            enchant(player, item.getModifiers().getEnchants(), enchant, runnable);
        }
    }

    private static class CounterStrike {
        private final List<EntitySD> entities;
        private final double hitAt;

        private CounterStrike(List<EntitySD> entities, double hitAt) {
            this.entities = entities;
            this.hitAt = hitAt;
        }

        private CounterStrike() {
            this(new ArrayList<>(), 0);
        }

        private CounterStrike add(EntitySD entity) {
            this.entities.add(entity);
            return this;
        }
    }
}
