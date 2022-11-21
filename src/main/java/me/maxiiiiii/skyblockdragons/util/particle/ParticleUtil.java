package me.maxiiiiii.skyblockdragons.util.particle;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil extends AbstractParticle {
    public ParticleUtil(Particle particle, double xOffset, double yOffset, double zOffset, double speed, int amount) {
        super(particle, xOffset, yOffset, zOffset, speed, amount);
    }

    public ParticleUtil(Particle particle) {
        super(particle);
    }

    @Override
    public void spawn(Location location, Object... objects) {
        if (particle == Particle.REDSTONE)
            location.getWorld().spawnParticle(this.particle, location, 0, xOffset, yOffset, zOffset, speed);
        else
            location.getWorld().spawnParticle(this.particle, location, amount, xOffset, yOffset, zOffset, speed);
    }
}
