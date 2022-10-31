package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.menus.SkyblockMenu;
import me.maxiiiiii.skyblockdragons.item.anvil.AnvilGui;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantingTableMenu;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClickBlock(PlayerInteractEvent event) {
        PlayerSD playerSD = SkyblockDragons.getPlayer(event.getPlayer());
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock.getType() == Material.ENCHANTMENT_TABLE){
                event.setCancelled(true);
                EnchantingTableMenu.openEnchantingTable(playerSD, null);
            }
            else if (clickedBlock.getType() == Material.ANVIL){
                event.setCancelled(true);
                AnvilGui.openAnvil(playerSD, true);
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (Functions.getId(item).contains("SKYBLOCK_MENU")) {
            e.setCancelled(true);
            new SkyblockMenu(SkyblockDragons.getPlayer(e.getPlayer()));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

//        if (e.getInventory().getType() != InventoryType.PLAYER) return;
        Player player = (Player) e.getWhoClicked();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        if (Functions.getId(e.getCurrentItem()).contains("SKYBLOCK_MENU")) {
            e.setCancelled(true);
            e.getWhoClicked().closeInventory();

            new SkyblockMenu(SkyblockDragons.getPlayer(player));
        }
    }
}
