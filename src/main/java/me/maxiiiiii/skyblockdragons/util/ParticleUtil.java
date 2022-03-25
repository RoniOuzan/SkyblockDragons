package me.maxiiiiii.skyblockdragons.util;

import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.Location;

public class ParticleUtil {
    public EnumParticle particle;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float speed;
    public int amount;

    public ParticleUtil(EnumParticle particle, float xOffset, float yOffset, float zOffset, float speed, int amount) {
        this.particle = particle;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.speed = speed;
        this.amount = amount;
    }

    public ParticleUtil(EnumParticle particle) {
        this(particle, 0.1f, 0.1f, 0.1f, 0f, 3);
    }

    public PacketPlayOutWorldParticles getParticle(Location location) {
        return new PacketPlayOutWorldParticles(particle, true, Float.parseFloat(location.getX() + ""), Float.parseFloat((location.getY() + 0.7) + ""), Float.parseFloat(location.getZ() + ""), xOffset, yOffset, zOffset, speed, amount);
    }
}
