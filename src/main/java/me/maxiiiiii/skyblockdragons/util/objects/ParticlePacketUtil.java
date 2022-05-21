package me.maxiiiiii.skyblockdragons.util.objects;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ParticlePacketUtil {
    public EnumWrappers.Particle particle;
    public float xOffset;
    public float yOffset;
    public float zOffset;
    public float speed;
    public int amount;

    public ParticlePacketUtil(EnumWrappers.Particle particle, float xOffset, float yOffset, float zOffset, float speed, int amount) {
        this.particle = particle;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.speed = speed;
        this.amount = amount;
    }

    public ParticlePacketUtil(EnumWrappers.Particle particle) {
        this(particle, 0.2f, 0.2f, 0.2f, 0f, 3);
    }

    public PacketContainer getParticle(Location location) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, particle);
        packet.getFloat().write(0, (float) location.getX()).write(1, (float) location.getY()).write(2, (float) location.getZ()).write(3, xOffset).write(4, yOffset).write(5, zOffset).write(6, speed);
        if (particle == EnumWrappers.Particle.REDSTONE)
            packet.getIntegers().write(0, 0);
        else
            packet.getIntegers().write(0, amount);
        return packet;
    }

    public void spawn(List<Player> players, Location location) {
        PacketContainer packet = getParticle(location);
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        for (Player player : players) {
            if (particle == EnumWrappers.Particle.REDSTONE) {
                for (int i = 0; i < amount; i++) {
                    try {
                        protocolManager.sendServerPacket(player, packet);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    protocolManager.sendServerPacket(player, packet);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
