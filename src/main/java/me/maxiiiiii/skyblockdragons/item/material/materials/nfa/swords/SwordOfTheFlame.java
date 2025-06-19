package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SwordOfTheFlame extends SwordMaterial {
    public SwordOfTheFlame() {
        super("SWORD_OF_THE_FLAME",
                Material.GOLD_SWORD,
                ItemFamily.NULL,
                "Sword of the Flame",
                Rarity.SPECIAL,
                new Stats(),
                "",
                new Flamer()
        );
    }

    public static class Flamer extends ItemAbility implements ItemAbilityManaCost, ItemAbilityMagicDamage, ItemAbilityCooldown, Listener {
        public Flamer() {
            super(AbilityAction.RIGHT_CLICK,
                    "Flamer",
                    "Creates a line of flames that damage every mob that goes through, click left-click to shoot."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 150;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 0.5;
        }

        @Override
        public double getBaseAbilityDamage(PlayerSD player) {
            return 10_000;
        }

        @Override
        public double getBaseAbilityScaling(PlayerSD player) {
            return 0.2;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new FlamerRunnable();
        }

        private static class FlamerRunnable implements PlayerAbilityRunnable {
            private static final double VELOCITY = 5;
            private static final long DURATION = 5_000;
            private static final double WIDTH = 4;
            private static final double HEIGHT = 4;
            private static final double DISTANCE_BETWEEN_FLAMES = 0.25;
            private final List<Flame> uses = new ArrayList<>();

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();

                if (uses.size() >= 5) {
                    return;
                }

                List<Location> locations = new ArrayList<>();

                Location baseLocation = player.getEyeLocation(); // ✅ Use eye location for more accurate forward direction
                Vector direction = baseLocation.getDirection().normalize();

                // ✅ Build a 2D plane perpendicular to the look direction (wall facing direction)
                Vector up = new Vector(0, 1, 0);
                Vector right = direction.clone().crossProduct(up).normalize();     // Horizontal axis of wall
                Vector wallUp = right.clone().crossProduct(direction).normalize(); // Vertical axis of wall

                Location wallCenter = baseLocation.clone().add(direction); // 1 block ahead

                for (double x = -WIDTH / 2; x <= WIDTH / 2; x += DISTANCE_BETWEEN_FLAMES) {
                    for (double y = -1; y < HEIGHT - 1; y += DISTANCE_BETWEEN_FLAMES) {
                        Vector offset = right.clone().multiply(x)
                                .add(wallUp.clone().multiply(y));
                        Location particleLoc = wallCenter.clone().add(offset);
                        locations.add(particleLoc);
                    }
                }

                Flame flame = new Flame(locations);
                uses.add(flame);

                Functions.While(() -> uses.contains(flame), 5L, i -> flame.spawn(1));
            }
        }

        @EventHandler
        public void onClick(PlayerInteractEvent e) {
            if (!Functions.getId(e.getItem()).equals("SWORD_OF_THE_FLAME")) return;
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            FlamerRunnable flamer = (FlamerRunnable) getAbilityOfPlayer(player).getRunnable();

            ItemAbility ability = Flamer.this;

            List<Flame> flames = new ArrayList<>(flamer.uses);
            if (flames.size() == 0) return;

            flamer.uses.clear();
            long used = System.currentTimeMillis();
            Functions.While(() -> System.currentTimeMillis() - used <= FlamerRunnable.DURATION, 2L, i -> {
                for (Flame flame : flames) {
                    for (Location location : flame.locations) {
                        location.add(location.getDirection().multiply(FlamerRunnable.VELOCITY * 0.1));
                        for (EntitySD entity : Functions.loopEntities(location, 1.5)) {
                            player.damage(new MagicEntityDamageEntity(player, entity, ability));
                        }
                    }
                    flame.locations.removeAll(flame.locations.stream()
                            .filter(l -> l.getBlock().getType().isOccluding()).collect(Collectors.toList()));
                    flame.spawn(1);
                }
            });
        }

        private static class Flame {
            private final List<Location> locations;

            private Flame(List<Location> locations) {
                this.locations = locations;
            }

            public void spawn(int every) {
                for (int i = 0; i < this.locations.size(); i++) {
                    if (i % every != 0) continue;
                    Location location = this.locations.get(i);
                    location.getWorld().spawnParticle(Particle.FLAME, location, 3, 0, 0, 0, 0);
                }
            }
        }
    }
}
