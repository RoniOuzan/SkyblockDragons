package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!Functions.isNotAir(e.getCurrentItem())) return;

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
            if (nbt.getString("GuiButton").equals("CLOSE")) {
                menu.getPlayer().closeInventory();
                return;
            }
            if (nbt.getString("GuiButton").equals("GO_BACK")) {
                Menu.removeLastHistory(e.getWhoClicked().getUniqueId());
                if (Menu.menusHistory.getOrDefault(e.getWhoClicked().getUniqueId(), new ArrayList<>()).size() > 0) {
                    Menu.menusHistory.get(e.getWhoClicked().getUniqueId()).get(0).open();
                    e.getWhoClicked().sendMessage(Menu.menusHistory.get(e.getWhoClicked().getUniqueId()).get(0).getTitle());
                }
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

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent   e) {
        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            menu.onInventoryDrag(e);
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            menu.onInventoryOpen(e);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            Functions.Wait(1L, () -> {
                if (e.getPlayer().getOpenInventory().getTopInventory().getType() == InventoryType.CRAFTING) {
                    e.getPlayer().sendMessage("cleared");
                    Menu.menusHistory.put(e.getPlayer().getUniqueId(), new ArrayList<>());
                }
            });

            menu.onInventoryClose(e);
        }
    }
}
