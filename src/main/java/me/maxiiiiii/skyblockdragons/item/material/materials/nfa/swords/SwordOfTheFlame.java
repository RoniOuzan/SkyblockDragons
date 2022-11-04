package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityMagicDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        public PlayerAbilityRunnable setupAbility() { // TODO: with the new system
            final double DISTANCE_BETWEEN_FLAMES = 1;
            final int AMOUNT_OF_FLAMES = 5;
            final int HEIGHT = 6;
            final double SPEED = 1;
            final long DURATION = 10_000; // 10s

            return new PlayerAbilityRunnable() {
                private final Map<PlayerSD, List<Flame>> uses = new HashMap<>();
                private final List<PlayerSD> pushed = new ArrayList<>();

                @Override
                public void run(PlayerAbilityUsage e) {
//                    PlayerSD player = e.getPlayer();
//                    if (uses.containsKey(player)) return;
//
//                    List<Flame> flames = new ArrayList<>();
//                    for (int i = AMOUNT_OF_FLAMES / -2; i <= AMOUNT_OF_FLAMES / 2; i++) {
//                        for (int j = 0; j <= HEIGHT; j++) {
//                            Location location = player.getLocation().add(player.getLocation().getDirection().setY(0))
//                                    .add(Functions.rotateVector(90, player.getLocation().getDirection()).multiply(i * DISTANCE_BETWEEN_FLAMES).setY(0));
//                            location.add(0, j, 0);
//                            flames.add(new Flame(location, System.currentTimeMillis()));
//                        }
//                    }
//                    uses.put(player, flames);
//
//                    Functions.While(() -> uses.containsKey(player) && !pushed.contains(player), 5L, i -> uses.get(player).forEach(Sword_Of_The_Flame.Flame::spawn));
                }
            };
        }

        @EventHandler
        public void onClick(PlayerInteractEvent e) {
//            if (!Functions.getId(e.getItem()).equals("SWORD_OF_THE_FLAME")) return;
//            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) return;
//
//            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
//
//            if (!uses.containsKey(player) || pushed.contains(player)) return;
//
//            pushed.add(player);
//
//            ItemAbility ability = ((ToolMaterial) Items.get(e.getItem())).getAbilities().get(0);
//            List<Sword_Of_The_Flame.Flame> flames = uses.get(player);
//            Functions.While(() -> System.currentTimeMillis() - flames.get(0).usedAt < DURATION, 5L, i -> {
//                for (Sword_Of_The_Flame.Flame flame : flames) {
//                    flame.location.add(flame.location.getDirection().multiply(SPEED));
//                    flame.spawn();
//                    for (Entity entity : Functions.loopEntities(flame.location, 1.5)) {
//                        player.makeDamage(entity, Damage.DamageType.MAGIC, 1, ability.getAbilityDamage(), ability.getAbilityScaling());
//                    }
//                }
//                uses.put(player, flames);
//            }, i -> {
//                uses.remove(player);
//                pushed.remove(player);
//            });
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
