package me.maxiiiiii.skyblockdragons.util.particle;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ParticlePacketUtil extends AbstractParticle {
    public ParticlePacketUtil(Particle particle, double xOffset, double yOffset, double zOffset, double speed, int amount) {
        super(particle, xOffset, yOffset, zOffset, speed, amount);
    }

    public ParticlePacketUtil(Particle particle) {
        super(particle);
    }

    public PacketContainer getParticle(Location location) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
        packet.getModifier().writeDefaults();
        packet.getParticles().write(0, EnumWrappers.Particle.getByName(particle.name()));
        packet.getFloat().write(0, (float) location.getX()).write(1, (float) location.getY()).write(2, (float) location.getZ()).write(3, xOffset).write(4, yOffset).write(5, zOffset).write(6, speed);
        if (particle == Particle.REDSTONE)
            packet.getIntegers().write(0, 0);
        else
            packet.getIntegers().write(0, amount);
        return packet;
    }

    @Override
    public void spawn(Location location, Object... objects) {
        PacketContainer packet = getParticle(location);
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        Collection<Player> players = Arrays.stream(objects).filter(p -> p instanceof Player).map(p -> (Player) p).collect(Collectors.toList());
        Arrays.stream(objects).filter(p -> p instanceof List).forEach(p -> players.addAll((Collection<? extends Player>) p));
        for (Player player : players) {
            int amount = 1;
            if (particle == Particle.REDSTONE) amount = this.amount;

            for (int i = 0; i < amount; i++) {
                try {
                    protocolManager.sendServerPacket(player, packet);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
