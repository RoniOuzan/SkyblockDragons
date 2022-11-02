package me.maxiiiiii.skyblockdragons.world.worlds.griffin;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.particle.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.particle.Particles;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

@Getter
public class Griffin {
    private final PlayerSD player;
    private Location burrow;

    private static final ParticlePacketUtil particle = new ParticlePacketUtil(Particle.FLAME, 0.1f, 0.1f, 0.1f, 0, 1);

    public Griffin(PlayerSD player) {
        this.player = player;

        Functions.While(() -> true, 5L, i -> {
            if (player.getWorldSD() == WorldSD.GRIFFIN_ISLAND) {
                Particles.square(particle, this.burrow, player);
            }
        });

        this.next();
    }

    public void next() {
        do {
            double a = Math.random() * 124 * 124;
            double b = Math.random() * 124 * 124;

            this.burrow = new Location(
                    GriffinIsland.world,
                    Math.round(Math.sin(a) * Math.sqrt(b)) + 0.5,
                    255,
                    Math.round(Math.cos(a) * Math.sqrt(b)) + 0.5
            );
            this.burrow.setY(Functions.getLowestBlock(this.burrow).getY());
        } while (this.burrow.getBlock().getType() != Material.DIRT && this.burrow.getBlock().getType() != Material.GRASS);
    }

    public void showNext() {
        Particles.line(particle, this.player.getLocation(), this.burrow, 1, player.getPlayer(), (Particles.LineRunnable) (a, l) -> player.playSound(l, Sound.BLOCK_NOTE_HARP, 1f, (float) (a * 0.1f)));
    }

    public boolean isBurrow(Location loc) {
        return loc.getWorld() == burrow.getWorld() &&
                loc.getX() == burrow.getX() &&
                loc.getY() == burrow.getY() &&
                loc.getZ() == burrow.getZ();
    }
}
