package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.menu.ProfileMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerClickOnPlayerListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            if (e.getPlayer().getEquipment().getItemInMainHand().getType() != Material.BOW)
                ProfileMenu.openProfileMenu(SkyblockDragons.getPlayer(e.getPlayer()), SkyblockDragons.getPlayer(e.getRightClicked().getUniqueId()));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getClickedInventory().getTitle().contains("'s Profile")) e.setCancelled(true);
    }
}
