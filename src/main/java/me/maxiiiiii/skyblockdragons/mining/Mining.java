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
import me.maxiiiiii.skyblockdragons.item.material.types.DrillMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Mining implements Listener {
    public static List<World> miningWorlds = null;

    public static final HashMap<UUID, Block> playerDigging = new HashMap<>();

    public Mining(JavaPlugin plugin) {
        miningWorlds = WorldSD.worlds.stream().filter(w -> w.getWorldType().contains(WorldType.MINING)).map(WorldSD::getWorld).collect(Collectors.toList());

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
        if (!SkyblockDragons.getPlayer(e.getPlayer()).getWorldSD().isType(WorldType.MINING)) return;

        if (playerDigging.getOrDefault(e.getPlayer().getUniqueId(), null) != null) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        BlockMaterial blockMaterial = BlockMaterial.get(block.getType());
        if (blockMaterial == null) return;

        double miningTime = ((blockMaterial.blockStrength * 30) / Math.max(player.getStats().getMiningSpeed().amount, 1)) * 50;
//        player.sendMessage("Trying to break %s at speed %s", blockMaterial, miningTime);

        if (miningTime <= 50) {
            breakBlock(player, block, blockMaterial);
            return;
        }

        if (player.getBreakingPower() >= blockMaterial.breakingPower) {
//            player.sendMessage("Pro Breaking Power %s %s", player.getBreakingPower(), blockMaterial.breakingPower);
            playerDigging.put(player.getUniqueId(), block);

            new MiningThread(player, block, miningTime);
        } else if (player.getItems().getToolItem().getMaterial() instanceof MiningMaterial) {
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
            this.blockMaterial = BlockMaterial.get(block.getType());

            this.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                if (this.cancel()) return;

                BlockAnimationPacket packet = new BlockAnimationPacket(player, block, (byte) i);
                packet.send(player);

                long now = System.currentTimeMillis();
                while (!this.isInterrupted() && System.currentTimeMillis() - now < miningTime / 9) {
                    if (this.cancel()) return;

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

        private boolean cancel() {
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
