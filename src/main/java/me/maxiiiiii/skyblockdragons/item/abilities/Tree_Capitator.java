package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class Tree_Capitator implements Listener {
    private final Cooldown<PlayerSD> cooldown = new Cooldown<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer() == null) return;

        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

        if (!getId(item).equals("TREE_CAPITATOR")) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (cooldown(player, cooldown, 2000, true)) return;

        if (e.getBlock().getType().toString().contains("LOG") || e.getBlock().getType().toString().contains("OAK") || e.getBlock().getType().toString().contains("WOOD") || e.getBlock().getType().toString().contains("BIRCH") || e.getBlock().getType().toString().contains("SPRUCE") || e.getBlock().getType().toString().contains("JUNGLE") || e.getBlock().getType().toString().contains("ACACIA")) {
            recursiveBreakBlocks(e.getBlock().getLocation(), 12, e.getBlock().getType(), 2);
        }
    }
}
