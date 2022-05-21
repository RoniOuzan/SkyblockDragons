package me.maxiiiiii.skyblockdragons.worlds.griffin;

import com.comphenix.protocol.wrappers.EnumWrappers;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.Particles;
import me.maxiiiiii.skyblockdragons.util.objects.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.objects.ParticleUtil;
import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import java.util.Arrays;

@Getter
public class Griffin {
    private final PlayerSD player;
    private Location burrow;

    private boolean showingBlock;

    public Griffin(PlayerSD player) {
        this.player = player;
        this.showingBlock = false;

        Functions.While(() -> true, 2L, i -> {

        });

        this.next();
    }

    public void next() {
        double a = Math.random() * 124;
        double b = Math.random() * 124;

        this.burrow = new Location(
                GriffinIsland.world,
                Math.round(Math.sin(a) * (b)) + 0.5,
                255,
                Math.round(Math.cos(a) * (b)) + 0.5
        );
        this.burrow.setY(Functions.getLowestBlock(this.burrow).getY());
    }

    public void showNext() {
        particleLine(this.player.getLocation(), this.burrow);
    }

    public void revealBlock() {
        if (player.getWorldSD() == WorldSD.GRIFFIN_ISLAND) {
            ParticlePacketUtil particle = new ParticlePacketUtil(EnumWrappers.Particle.FLAME, 0.1f, 0.1f, 0.1f, 0, 1);
            Particles.square(this.burrow, Arrays.asList(player.getPlayer()), particle);
        }
    }

    public boolean isBurrow(Location loc) {
        return loc.getWorld() == burrow.getWorld() &&
                loc.getX() == burrow.getX() &&
                loc.getY() == burrow.getY() &&
                loc.getZ() == burrow.getZ();
    }

    public void particleLine(Location loc1, Location loc2) {
        final int DISTANCE = (int) (loc1.distance(loc2) * 5);
        Location loc = loc1.clone();
        ParticleUtil particle = new ParticleUtil(Particle.FLAME, 0.1, 0.1, 0.1, 0, 1);
        Functions.Loop(DISTANCE, 1L, i -> {
            double t = i * 0.05;
            Vector direction = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
            double x = direction.getX() * t;
            double y = direction.getY() * t + 1.5;
            double z = direction.getZ() * t;
            loc.add(x, y, z);
            particle.spawn(loc);

            loc.subtract(x, y, z);
        });
    }
}
