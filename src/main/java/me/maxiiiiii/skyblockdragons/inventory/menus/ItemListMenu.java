package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.stream.Collectors;

public class ItemListMenu extends PageMenu {
    public ItemListMenu(PlayerSD player, String search) {
        super(player, "Item List", 6, InventoryGlassType.SURROUND, Items.itemMaterials.values().stream().filter(m -> m.name().toLowerCase().contains(search.trim().toLowerCase())).map(m -> new Item(player, m)).collect(Collectors.toList()), false);
    }

    @Override
    public void update() {
        super.update();

        this.setItem(48, createItem(Material.SIGN, ChatColor.GREEN + "Search Items", "", ChatColor.YELLOW + "Click to search items!"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Search Items") && e.getSlot() == 48) {
            player.openSign("Search Item", lines -> new ItemListMenu(player, lines[0] + lines[1]));
        }

        if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM"))
            player.give(new Item(player, Items.get(e.getCurrentItem())));
    }
}
