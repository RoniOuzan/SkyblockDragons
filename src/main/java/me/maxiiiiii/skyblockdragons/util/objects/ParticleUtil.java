package me.maxiiiiii.skyblockdragons.util.objects;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {
    public Particle particle;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float speed;
    public int amount;

    public ParticleUtil(Particle particle, double xOffset, double yOffset, double zOffset, double speed, int amount) {
        this.particle = particle;
        this.xOffset = (float) xOffset;
        this.yOffset = (float) yOffset;
        this.zOffset = (float) zOffset;
        this.speed = (float) speed;
        this.amount = amount;
    }

    public ParticleUtil(Particle particle) {
        this(particle, 0.3f, 0.3f, 0.3f, 0f, 3);
    }

    public void spawn(Location location) {
        if (particle == Particle.REDSTONE)
            location.getWorld().spawnParticle(this.particle, location, 0, xOffset, yOffset, zOffset, speed);
        else
            location.getWorld().spawnParticle(this.particle, location, amount, xOffset, yOffset, zOffset, speed);
    }
}
