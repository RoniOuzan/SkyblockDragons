package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
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
import org.bukkit.util.Vector;

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
                final double VELOCITY = 10;
                final long DELAY = 1L;
                final double PERIOD = DELAY / 20.0;
                PlayerSD player = e.getPlayer();
                Location location = player.getEyeLocation().add(0, -0.5, 0);
                Vector v = location.getDirection().clone().add(new Vector(0, 0.5, 0)).multiply(VELOCITY);

                long started = System.currentTimeMillis();
                Functions.While(() -> System.currentTimeMillis() - started <= 5000, DELAY, i -> {
                    v.add(new Vector(0, -12 * PERIOD, 0));
                    location.add(v.clone().multiply(PERIOD));

                    location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 0.3, 0, 0);
                    location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 0.000001, 0.000001, 0.000001);
                    for (EntitySD entity : Functions.loopEntities(location, 1.5)) {
                        player.damage(new MeleeEntityDamageEntity(player, entity));
                    }
                });
            };
        }
    }
}
