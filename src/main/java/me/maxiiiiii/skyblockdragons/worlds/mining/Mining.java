package me.maxiiiiii.skyblockdragons.worlds.mining;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.worlds.WorldType;
import me.maxiiiiii.skyblockdragons.worlds.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.mining.deepmines.DeepMines;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutBlockBreakAnimation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Mining implements Listener {
    public static final List<World> miningWorlds = new ArrayList<>(Arrays.asList(TheEnd.world, Bukkit.getWorld("DeepMines")));

    public static final HashMap<Player, Boolean> isDigging = new HashMap<>();

    public Mining(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerBreakBlockListener(), plugin);
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent e) {
        if (!miningWorlds.contains(e.getBlock().getWorld())) return;

        if (isDigging.getOrDefault(e.getPlayer(), false)) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Block block = e.getBlock();
        BlockMaterial blockMaterial = BlockMaterial.get(block.getType().toString());

        double miningTime = (blockMaterial.blockStrength * 50) / Math.max(player.getStats().getMiningSpeed().amount, 1);

        if (miningTime <= 1) {
            BlockBreakEvent event = new BlockBreakEvent(e.getBlock(), player);
            Bukkit.getServer().getPluginManager().callEvent(event);
            return;
        }

        if (player.getBreakingPower() >= blockMaterial.breakingPower) {
            AtomicBoolean isStopped = new AtomicBoolean(false);

            isDigging.put(player, true);

            Functions.Loop(Math.min((int) miningTime, 9), (long) (miningTime / 9), i -> {
                if (miningTime < 9)
                    i = (int) ((i / miningTime) * 9);
                if (!player.getTargetBlock(5).getLocation().equals(block.getLocation())) {
                    isStopped.set(true);
                    stopMining(player, block);
                }

                if (isStopped.get()) return;

                PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(player.getEntityId(), new BlockPosition(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ()), i + 1);
                player.sendPacket(packet);
            }, i -> {
                if (isStopped.get()) return;

                stopMining(player, block);

                PlayerBreakBlockEvent event = new PlayerBreakBlockEvent(player, e.getBlock(), blockMaterial);
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

        isDigging.put(player, false);
    }
}
