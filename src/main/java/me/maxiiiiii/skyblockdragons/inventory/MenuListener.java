package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        InventoryHolder holder = e.getClickedInventory().getHolder();

        if (holder instanceof Menu) {
            e.setCancelled(true);

            Menu menu = (Menu) holder;

//            if (menu instanceof GroupMenu) {
//                GroupMenu groupMenu = (GroupMenu) menu;
//                if (e.getSlot() < 9) {
//                    NBTItem nbt = new NBTItem(e.getCurrentItem(), true);
//                    int slot = nbt.getInteger("CategorySlot");
//                    groupMenu.setMenu(slot);
//                    return;
//                }
//            }
            NBTItem nbt = new NBTItem(e.getCurrentItem());
            if (nbt.hasKey("CloseButton")) {
                menu.getPlayer().closeInventory();
                return;
            }

            if (menu instanceof PageMenu) {
                final PageMenu pageMenu = (PageMenu) menu;
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
                    if (e.getClick().isLeftClick())
                        pageMenu.nextPage();
                    else if (e.getClick().isRightClick())
                        pageMenu.lastPage();
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
                    if (e.getClick().isLeftClick())
                        pageMenu.previousPage();
                    else if (e.getClick().isRightClick())
                        pageMenu.firstPage();
                    return;
                }
            }

            menu.onInventoryClick(e);
        }
    }
}
