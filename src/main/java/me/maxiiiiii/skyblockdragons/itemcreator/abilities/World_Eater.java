package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class World_Eater implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();
        if (!getId(item).equals("WORLD_EATER")) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 1000, true)) return;

        recursiveBreakBlocks(e.getBlock().getLocation(), 500, e.getBlock().getType(), 2);
    }
}
