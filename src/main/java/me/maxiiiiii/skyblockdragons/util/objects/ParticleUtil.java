package me.maxiiiiii.skyblockdragons.util.objects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleUtil {
    public Particle particle;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float speed;
    public int amount;

    public ParticleUtil(Particle particle, float xOffset, float yOffset, float zOffset, float speed, int amount) {
        this.particle = particle;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.speed = speed;
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
