package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        ClickType click = e.getClick();
        if (holder instanceof Menu) {
            e.setCancelled(true);

            Menu menu = (Menu) holder;
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());

            if (Functions.isNotAir(e.getCurrentItem())) {
                NBTItem nbt = new NBTItem(e.getCurrentItem());
                if (nbt.getString("GuiButton").equals("CLOSE")) {
                    menu.getPlayer().closeInventory();
                    return;
                }
                if (nbt.getString("GuiButton").equals("GO_BACK")) {
                    Menu.removeLastHistory(e.getWhoClicked().getUniqueId());
                    if (player.getMenuHistory().size() > 0) {
                        player.getMenuHistory().get(player.getMenuHistory().size() - 1).open(false);
                    }
                }

                if (menu instanceof PageMenu) {
                    final PageMenu pageMenu = (PageMenu) menu;
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Next Page")) {
                        if (click.isLeftClick())
                            pageMenu.nextPage();
                        else if (click.isRightClick())
                            pageMenu.lastPage();
                        return;
                    }
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Previous Page")) {
                        if (click.isLeftClick())
                            pageMenu.previousPage();
                        else if (click.isRightClick())
                            pageMenu.firstPage();
                        return;
                    }
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
                    SkyblockDragons.getPlayer((Player) e.getPlayer()).getMenuHistory().clear();
                }
            });

            menu.onInventoryClose(e);
        }
    }
}
