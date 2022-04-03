package me.maxiiiiii.skyblockdragons.worlds.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import me.maxiiiiii.skyblockdragons.worlds.Worlds;
import me.maxiiiiii.skyblockdragons.worlds.mining.deepmines.DeepMines;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Mining implements Listener {
    public static final List<String> miningWorlds = Arrays.stream(Worlds.values()).filter(w -> w.getWorldType().contains(WorldType.MINING)).map(Enum::name).collect(Collectors.toList());

    public static final HashMap<Player, Integer> amount = new HashMap<>();

    public Mining(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        new DeepMines(plugin);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        if (!miningWorlds.contains(e.getBlock().getWorld().getName())) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        BlockMaterial blockMaterial = BlockMaterial.get(block.getType().toString());

        double miningTime = (blockMaterial.blockStrength * 50) / Math.max(player.miningSpeed, 1);

        if (miningTime <= 1) {
            BlockBreakEvent event = new BlockBreakEvent(e.getBlock(), player);
            Bukkit.getServer().getPluginManager().callEvent(event);
            return;
        }

        e.setCancelled(true);
        if (player.getBreakingPower() >= blockMaterial.breakingPower) {
            CraftPlayer craftPlayer = (CraftPlayer) player.getPlayer();
            EntityPlayer entityPlayer = craftPlayer.getHandle();
            AtomicBoolean isStopped = new AtomicBoolean(false);
            Functions.Loop(Math.min((int) miningTime, 9), (long) (miningTime / 9), i -> {
                if (miningTime < 9)
                    i = (int) ((i / miningTime) * 9);
                if (!player.getTargetBlock(null, 5).getLocation().equals(block.getLocation())) {
                    isStopped.set(true);
                    stopMining(player, block);
                }

                if (isStopped.get()) return;

                PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(player.getEntityId(), new BlockPosition(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()), i + 1);
                entityPlayer.playerConnection.sendPacket(packet);
            }, i -> {
                if (isStopped.get()) return;

                stopMining(player, block);

                BlockBreakEvent event = new BlockBreakEvent(e.getBlock(), player);
                Bukkit.getServer().getPluginManager().callEvent(event);
            });
        } else {
            player.sendMessage(ChatColor.RED + "Your tool is not strong enough to mine this block!");
        }
    }

    private static void stopMining(Player player, Block block) {
        CraftPlayer craftPlayer = (CraftPlayer) player.getPlayer();
        EntityPlayer entityPlayer = craftPlayer.getHandle();

        PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(player.getEntityId(), new BlockPosition(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()), -1);
        entityPlayer.playerConnection.sendPacket(packet);
    }
}
