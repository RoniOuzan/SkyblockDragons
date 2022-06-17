package me.maxiiiiii.skyblockdragons.util.particle;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

@Getter
public abstract class AbstractParticle {
    protected Particle particle;
    protected float xOffset;
    protected float yOffset;
    protected float zOffset;
    protected float speed;
    protected int amount;

    public AbstractParticle(Particle particle, double xOffset, double yOffset, double zOffset, double speed, int amount) {
        this.particle = particle;
        this.xOffset = (float) xOffset;
        this.yOffset = (float) yOffset;
        this.zOffset = (float) zOffset;
        this.speed = (float) speed;
        this.amount = amount;
    }

    public AbstractParticle(Particle particle) {
        this(particle, 0.3f, 0.3f, 0.3f, 0f, 3);
    }

    public final void spawn(Location location) {
        this.spawn(location, Bukkit.getOnlinePlayers());
    }

    public abstract void spawn(Location location, Object... player);
}
