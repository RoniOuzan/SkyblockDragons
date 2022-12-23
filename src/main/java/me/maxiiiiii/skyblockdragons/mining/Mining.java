package me.maxiiiiii.skyblockdragons.mining;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.mining.events.PlayerBreakBlockEvent;
import me.maxiiiiii.skyblockdragons.mining.listeners.PlayerBreakBlockListener;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

public class Mining implements Listener {
    private static final HashMap<UUID, Block> playerDigging = new HashMap<>();

    public Mining(JavaPlugin plugin) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new PacketAdapter(SkyblockDragons.plugin, PacketType.Play.Client.BLOCK_DIG) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                PacketContainer packet = e.getPacket();
                Player player = e.getPlayer();
                if (packet.getPlayerDigTypes().read(0) == EnumWrappers.PlayerDigType.ABORT_DESTROY_BLOCK && playerDigging.getOrDefault(player.getUniqueId(), null) != null) {
                    playerDigging.put(player.getUniqueId(), player.getWorld().getBlockAt(player.getLocation().getBlockX(), -5, player.getLocation().getBlockZ()));
                }
            }
        });

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerBreakBlockListener(), plugin);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        if (!player.getWorldSD().isType(player, WorldType.MINING) || e.getPlayer().getGameMode() != GameMode.SURVIVAL) return;

        if (playerDigging.getOrDefault(player.getUniqueId(), null) != null) return;

        Block block = e.getBlock();
        BlockMaterial blockMaterial = BlockMaterial.get(player.getWorldSD(), block.getType(), block.getData());
        if (blockMaterial == null) return;

        if (player.getBreakingPower() >= blockMaterial.getBreakingPower() && blockMaterial.getRequiredTools().contains(player.getItems().getToolMaterial().getType())) {
            playerDigging.put(player.getUniqueId(), block);

            double miningTime = (1 / ((Math.max(player.getStats().getMiningSpeed().get(), 5) / blockMaterial.getBlockStrength()) / 30)) * 1000;

            if (miningTime <= 50) {
                stopMining(player, block);
                breakBlock(player, block, blockMaterial);
                return;
            }

            new MiningThread(player, block, miningTime);
        } else if (player.getItems().getToolMaterial() instanceof MiningMaterial) {
            player.sendMessage(ChatColor.RED + "Your tool is not strong enough to mine this block!");
        }
    }

    public static void breakBlock(PlayerSD player, Block block, BlockMaterial blockMaterial) {
        Bukkit.getScheduler().runTask(SkyblockDragons.plugin, () -> {
            PlayerBreakBlockEvent event = new PlayerBreakBlockEvent(player, block, blockMaterial);
            Bukkit.getServer().getPluginManager().callEvent(event);
        });
    }

    private static class MiningThread extends Thread {
        private final PlayerSD player;
        private final Block block;
        private final double miningTime;
        private final BlockMaterial blockMaterial;

        private MiningThread(PlayerSD player, Block block, double miningTime) {
            super(player.getUniqueId().toString() + "_MINING");
            this.player = player;
            this.block = block;
            this.miningTime = miningTime;
            this.blockMaterial = BlockMaterial.get(player.getWorldSD(), block.getType(), block.getData());

            this.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                if (this.isCancelled()) return;

                BlockAnimationPacket packet = new BlockAnimationPacket(player, block, (byte) i);
                packet.send(player);

                long now = System.currentTimeMillis();
                while (!this.isInterrupted() && System.currentTimeMillis() - now < miningTime / 9) {
                    if (this.isCancelled()) return;

                    packet.send(player);
                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                i++;
            }

            stopMining(player, block);

            breakBlock();

            this.interrupt();
        }

        private void breakBlock() {
            Mining.breakBlock(player, block, blockMaterial);
        }

        private boolean isCancelled() {
            if (playerDigging.get(player.getUniqueId()).getY() < 0) {
                playerDigging.put(player.getUniqueId(), null);
                BlockAnimationPacket packet = new BlockAnimationPacket(player, block, (byte) -1);
                packet.send(player);
                return true;
            }
            return false;
        }
    }

    private static void stopMining(PlayerSD player, Block block) {
        BlockAnimationPacket packet = new BlockAnimationPacket(player, block, (byte) -1);
        packet.send(player);

        playerDigging.put(player.getUniqueId(), null);
    }

    private static class BlockAnimationPacket {
        private final PacketContainer packet;

        private BlockAnimationPacket(PlayerSD player, Block block, int stage) {
            packet = new PacketContainer(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
            packet.getIntegers().write(0, player.getEntityId());
            packet.getIntegers().write(1, stage);
            packet.getBlockPositionModifier().write(0, new BlockPosition(block.getX(), block.getY(), block.getZ()));
        }

        private void send(Player player) {
            ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
            try {
                protocolManager.sendServerPacket(player, packet);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
