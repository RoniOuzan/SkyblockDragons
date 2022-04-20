package me.maxiiiiii.skyblockdragons.item.abilities;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class Backpack implements Listener {
    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (!item.getMaterial().name().contains("BACKPACK")) return;

        Backpack.openBackpack(e.getPlayer(), item);
    }

    public static void openBackpack(Player player, ItemStack item) {
        int size = 9;
        switch (Functions.getId(item).split("_")[0]) {
            case "MEDIUM":
                size *= 2;
                break;
            case "LARGE":
                size *= 3;
                break;
            case "GREATER":
                size *= 4;
                break;
            case "JUMBO":
                size *= 5;
                break;
        }
        size += 9;

        Inventory inventory = Bukkit.createInventory(player, size, ChatColor.stripColor(item.getItemMeta().getDisplayName()));

        ItemStack glass = Functions.createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 2; i < 9; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(0, close);

        ItemStack goBack = Functions.createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(1, goBack);

        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound nbt = nbtItem.getCompound("Item");
        NBTCompound items = nbt.getOrCreateCompound("Backpack");
        for (int i = 9; i < size; i++) {
            if (!items.hasKey(i + "")) continue;

            inventory.setItem(i, items.getItemStack(i + ""));
        }

        InventoryView inventoryView = player.openInventory(inventory);
        BackpackOpenEvent event = new BackpackOpenEvent(inventoryView, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getClickedInventory().getTitle().contains(" Backpack"))
            if (e.getSlot() < 9)
                e.setCancelled(true);
    }

    @EventHandler
    public void onBackpackOpen(BackpackOpenEvent e) {
        Functions.While(() -> e.getInventory().getTitle().equals(e.getPlayer().getOpenInventory().getTitle()), 2L, i -> {}, i -> {
            BackpackCloseEvent event = new BackpackCloseEvent(e.getView(), e.getItem());
            Bukkit.getServer().getPluginManager().callEvent(event);
        });
    }

    @EventHandler
    public void onBackpackClose(BackpackCloseEvent e) {
        NBTItem nbtItem = new NBTItem(e.getItem(), true);
        NBTCompound nbt = nbtItem.getCompound("Item");
        NBTCompound items = nbt.getOrCreateCompound("Backpack");

        for (int i = 9; i < e.getInventory().getSize(); i++) {
            if (Functions.isNotAir(e.getInventory().getItem(i))) {
                items.setItemStack(i + "", e.getInventory().getItem(i));
            } else {
                if (items.hasKey(i + "")) {
                    items.removeKey(i + "");
                }
            }
        }
    }

    @Getter
    public static class BackpackOpenEvent extends InventoryOpenEvent {
        private final ItemStack item;

        public BackpackOpenEvent(InventoryView transaction, ItemStack item) {
            super(transaction);
            this.item = item;
        }
    }

    @Getter
    public static class BackpackCloseEvent extends InventoryCloseEvent {
        private final ItemStack item;

        public BackpackCloseEvent(InventoryView transaction, ItemStack item) {
            super(transaction);
            this.item = item;
        }
    }
}
