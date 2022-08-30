package me.maxiiiiii.skyblockdragons.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import lombok.var;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.entity.Player;

public class PacketListeners {
    public PacketListeners() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(SkyblockDragons.plugin, PacketType.Play.Server.WORLD_PARTICLES) {
            @Override
            public void onPacketSending(PacketEvent e) {
                PacketContainer packet = e.getPacket();
                Player player = e.getPlayer();
                EnumWrappers.Particle particle = packet.getParticles().read(0);
                if (particle == EnumWrappers.Particle.DAMAGE_INDICATOR){
                    e.setCancelled(true);
                }
//                SkyblockDragons.logger.info(String.format("particle ID: %s", particle));
            }

            @Override
            public void onPacketReceiving(PacketEvent e) {

            }
        });
    }
}
