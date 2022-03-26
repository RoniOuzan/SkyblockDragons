package me.maxiiiiii.skyblockdragons.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import org.bukkit.Location;
import org.bukkit.Material;

public class ParticleUtil {
    public EnumWrappers.Particle particle;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float speed;
    public int amount;
    public Material blockData;

    public ParticleUtil(EnumWrappers.Particle particle, float xOffset, float yOffset, float zOffset, float speed, int amount, Material blockData) {
        this.particle = particle;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.speed = speed;
        this.amount = amount;
        this.blockData = blockData;
    }

    public ParticleUtil(EnumWrappers.Particle particle, float xOffset, float yOffset, float zOffset, float speed, int amount) {
        this(particle, xOffset, yOffset, zOffset, speed, amount, null);
    }

    public ParticleUtil(EnumWrappers.Particle particle) {
        this(particle, 0.3f, 0.3f, 0.3f, 0f, 3, null);
    }

    public ParticleUtil(EnumWrappers.Particle particle, Material blockData) {
        this(particle, 0.3f, 0.3f, 0.3f, 0f, 3, blockData);
    }

    public PacketContainer getParticle(Location location) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, particle);
        packet.getFloat().write(0, (float) location.getX()).write(1, (float) location.getY()).write(2, (float) location.getZ()).write(3, xOffset).write(4, yOffset).write(5, zOffset).write(6, speed);
        packet.getIntegers().write(0, amount);
        if (this.blockData != null)
            packet.getBlocks().write(0, this.blockData);
        return packet;
    }
}
