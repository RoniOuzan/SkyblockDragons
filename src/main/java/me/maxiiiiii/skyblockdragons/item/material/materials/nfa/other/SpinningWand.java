package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.other;

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
import me.maxiiiiii.skyblockdragons.util.particle.ParticleUtil;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import java.util.function.BiFunction;

public class SpinningWand extends ToolMaterial {
    public SpinningWand() {
        super(
                "SPINNING_WAND",
                Material.LEASH,
                ItemFamily.NULL,
                "Spinning Wand",
                ItemType.WAND,
                Rarity.EPIC,
                "",
                new Ability()
        );
    }

    private static class Ability extends ItemAbility {

        protected Ability() {
            super(AbilityAction.RIGHT_CLICK, "Spin", "SPIN!!!");
        }

        @Override
        protected PlayerAbilityRunnable setupAbility() {
            return new AbilityRunnable();
        }

        private static class AbilityRunnable implements PlayerAbilityRunnable {

            private static final double OMEGA_PER_SECOND = Math.PI;
            private static final double VELOCITY_PER_SECOND = 2 * (1.0 / 20.0);
            private static final double VELOCITY_Y_PER_SECOND = 1 * (1.0 / 20.0);
            private static final int HZ = 20;

            private static final ParticleUtil particle = new ParticleUtil(Particle.SPELL_WITCH , 0, 0, 0, 0, 5);

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();

                Functions.Loop(200, 20 / HZ, i -> {
                    double angle = i * (OMEGA_PER_SECOND / HZ);
                    double v = i * (VELOCITY_PER_SECOND / HZ);
                    Vector velocity = new Vector(Math.cos(angle), 0, Math.sin(angle)).multiply(v)
                            .add(new Vector(0, VELOCITY_Y_PER_SECOND, 0));

                    player.setVelocity(velocity);

                    particle.spawn(player.getLocation());
                });
            }
        }
    }
}
