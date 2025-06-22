package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class Spinjistu extends ToolMaterial {
    public Spinjistu() {
        super("SPINJISTU",
                Material.HOPPER,
                ItemFamily.NULL,
                "Spinjistu",
                ItemType.ITEM,
                Rarity.LEGENDARY,
                "",
                new SpinjistuAbility()
        );
    }

    public static class SpinjistuAbility extends ItemAbility {
        public SpinjistuAbility() {
            super(AbilityAction.RIGHT_CLICK,
                    "Spinjistu",
                    ChatColor.GRAY + "Spinjistu!!!"
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new AbilityRunnable();
        }

        private static class AbilityRunnable implements PlayerAbilityRunnable {
            private static final long PERIOD = 3L;
            private static final double OMEGA = (Math.PI / 4) * (PERIOD / 20.0);
            private static final double OMEGA_PER_HEIGHT = (Math.PI / 4);
            private static final double HEIGHT = 2;
            private static final double Y_DX = 0.3;
            private static final double MIN_RADIUS = 0.4;
            private static final double MAX_RADIUS = 1.2;
            private static final double RADIUS_DX = 0.2;
            private static final double ANGLE_DX = (2 * Math.PI) / 8;

            private boolean isUsing = false;

            @Override
            public void run(PlayerAbilityUsage e) {
                if (this.isUsing) {
                    this.isUsing = false;
                    return;
                }

                this.isUsing = true;
                PlayerSD player = e.getPlayer();
                Functions.While(() -> this.isUsing, PERIOD, i -> {
                    for (double y = 0; y <= HEIGHT; y += Y_DX) {
                        double radius = (y / HEIGHT) * (MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
                        for (double r = 0; r <= radius; r += RADIUS_DX) {
                            int index = 0;
                            for (double a = 0; a < 2 * Math.PI; a += ANGLE_DX) {
                                double angle = a + (i * OMEGA) + (y * OMEGA_PER_HEIGHT);
                                Location location = player.getLocation();
                                location.add(new Vector(Math.cos(angle), 0, Math.sin(angle)).multiply(r));
                                location.add(new Vector(0, y, 0));
                                if (index % 2 == 0) {
                                    location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 0.000001, Math.random() * 0.5 + 0.5, 0.000001);
                                } else {
                                    location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 0.8, 1, 0.8);
                                }

                                index++;
                            }
                        }
                    }
                });
            }
        }
    }
}
