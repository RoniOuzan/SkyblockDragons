package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SoulWhip extends SwordMaterial {
    public SoulWhip() {
        super("SOUL_WHIP",
                Material.FISHING_ROD,
                ItemFamily.SOUL_WHIP,
                "Soul Whip",
                Rarity.LEGENDARY,
                new Stats(145, 175, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new Flay()
        );
    }

    public static class Flay extends ItemAbility implements Listener {
        private final int AMOUNT = 15;

        public Flay() {
            super(AbilityAction.RIGHT_CLICK,
                    "Flay",
                    "Flay your whip in an arc, dealing your melee damage to all enemies in its path."
            );
        }

        @EventHandler
        public void onFish(PlayerFishEvent e) {
            ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

            if (!Functions.getId(item).equals("SOUL_WHIP")) return;

            e.setCancelled(true);
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                Player player = e.getPlayer();
                Location location = player.getEyeLocation().add(0, -0.5, 0);

                new BukkitRunnable() {
                    double i = 0;
                    @Override
                    public void run() {
                        if (i >= 180) cancel();
                        for (double j = i; j < i + AMOUNT; j++) {
                            double rad = Math.toRadians(j);
                            Location newLocation = location.clone().add(location.getDirection().multiply(j / 15));
                            newLocation.add(
                                    0,
                                    Math.sin(rad) * 1.5,
                                    0
                            );

                            location.getWorld().spawnParticle(Particle.REDSTONE, newLocation, 0, 0.3, 0, 0);
                            location.getWorld().spawnParticle(Particle.REDSTONE, newLocation, 0, 0.000001, 0.000001, 0.000001);
                            for (Entity entity : newLocation.getWorld().getNearbyEntities(newLocation, 1.5, 1.5, 1.5)) {
                                if (entity instanceof Creature) {
                                    ((Creature) entity).damage(1, player);
                                }
                            }
                        }
                        i += AMOUNT;
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
            };
        }
    }
}
