package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MagicEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
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
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.Loop;
import static me.maxiiiiii.skyblockdragons.util.Functions.loopEntities;

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
            return 3;
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
            private final double SPEED = 0.5;
            private final long DURATION = 5_000; // 5s
            private final Map<PlayerSD, List<Flame>> uses = new HashMap<>();
            private final List<PlayerSD> pushed = new ArrayList<>();

            @Override
            public void run(PlayerAbilityUsage e) {
                final int AMOUNT_OF_FLAMES = 20;
                final int HEIGHT = 5;
                final double DISTANCE_BETWEEN_FLAMES = 0.2;

                PlayerSD player = e.getPlayer();
                if (uses.containsKey(player)) return;

                List<Flame> flames = new ArrayList<>();
                for (int i = AMOUNT_OF_FLAMES / -2; i <= AMOUNT_OF_FLAMES / 2; i++) {
                    for (double j = 0; j <= HEIGHT; j += DISTANCE_BETWEEN_FLAMES) {
                        Location location = player.getLocation().add(player.getLocation().getDirection().setY(0))
                                .add(Functions.rotateVector(90, player.getLocation().getDirection()).multiply(i * DISTANCE_BETWEEN_FLAMES).setY(0));
                        location.add(0, j, 0);
                        flames.add(new Flame(location, System.currentTimeMillis()));
                    }
                }
                uses.put(player, flames);

                Functions.While(() -> uses.containsKey(player) && !pushed.contains(player), 5L, i -> uses.get(player).forEach(Flame::spawn));
            }
        }

        @EventHandler
        public void onClick(PlayerInteractEvent e) {
            if (!Functions.getId(e.getItem()).equals("SWORD_OF_THE_FLAME")) return;
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

            FlamerRunnable flamer = (FlamerRunnable) getAbilityOfPlayer(player).getRunnable();
            if (!flamer.uses.containsKey(player) || flamer.pushed.contains(player)) return;

            flamer.pushed.add(player);

            ItemAbility ability = ((ToolMaterial) Items.get(e.getItem())).getAbilities().get(0);
            List<Flame> flames = flamer.uses.get(player);
            Functions.While(() -> System.currentTimeMillis() - flames.get(0).usedAt < flamer.DURATION, 1L, i -> {
                for (Flame flame : flames) {
                    flame.location.add(flame.location.getDirection().multiply(flamer.SPEED));
                    flame.spawn();
                    for (EntitySD entity : Functions.loopEntities(flame.location, 1.5)) {
                        player.damage(new MagicEntityDamageEntity(player, entity, ability));
                    }
                }
                flamer.uses.put(player, flames);
            }, i -> {
                flamer.uses.remove(player);
                flamer.pushed.remove(player);
            });
        }

        private static class Flame {
            private final Location location;
            private final long usedAt;

            public Flame(Location location, long usedAt) {
                this.location = location.setDirection(location.getDirection().setY(0));
                this.usedAt = usedAt;
            }

            public void spawn() {
                location.getWorld().spawnParticle(Particle.FLAME, location, 3, 0, 0, 0, 0);
            }
        }
    }
}
