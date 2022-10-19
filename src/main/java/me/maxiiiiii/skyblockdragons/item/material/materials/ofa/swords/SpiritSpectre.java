package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SpiritSpectre extends SwordMaterial {
    public SpiritSpectre() {
        super("SPIRIT_SPECTRE",
                Material.RED_ROSE,
                ItemFamily.SPIRIT_SCEPTRE,
                "Spirit Spectre",
                Rarity.LEGENDARY,
                "2",
                "",
                new Stats(180, 0, 0, 0, 0, 0, 0, 0, 0, 300),
                "",
                new GuidedBat()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class GuidedBat extends ItemAbility {
        public GuidedBat() {
            super(AbilityAction.RIGHT_CLICK,
                    "Guided Bat",
                    "Shoots a guided spirit bat, following your aim and exploding for " + ChatColor.RED + "2,000 " + ChatColor.GRAY + "damage.",
                    250,
                    false,
                    0
            );
        }

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return () -> {
                Player player = e.getPlayer();

                Bat bat = (Bat) player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
                bat.setGravity(false);
                bat.setInvulnerable(true);
                bat.setAwake(true);
                bat.addScoreboardTag("Spirit_Sceptre");

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (bat.isDead()) cancel();

                        Location newLocation = bat.getLocation();
                        newLocation.add(newLocation.getDirection().multiply(0.5));
                        if (newLocation.getBlock().getType().isSolid()) {
                            newLocation.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, bat.getLocation(), 1, 0, 0, 0, 3);

                            List<Entity> entities = Functions.loopEntities(player.getLocation(), 6);

                            for (Entity entity : entities) {
                                if (entity instanceof Creature) {
                                    ((Creature) entity).damage(1, player);
                                }
                            }

                            bat.remove();
                            cancel();
                        }
                        bat.setVelocity(player.getLocation().getDirection().multiply(2));
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
            };
        }
    }
}
