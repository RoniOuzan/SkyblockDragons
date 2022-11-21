package me.maxiiiiii.skyblockdragons.inventory.menus;

import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.openSign;

public class ItemListMenu extends PageMenu {
    private String search;

    public ItemListMenu(PlayerSD player, String search) {
        super(player, "Item List", 6, InventoryGlassType.SURROUND, () -> Items.itemMaterials.values().stream().sorted().filter(m -> m.getName().toLowerCase().contains(search.toLowerCase())).map(m -> new Item(player, m)).collect(Collectors.toList()), true);

        this.search = search;
    }

    @Override
    public void update() {
        this.setItem(48, createItem(Material.SIGN, ChatColor.GREEN + "Search Items", "", ChatColor.YELLOW + "Click to search items!"));

        super.update();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Search Items") && e.getSlot() == 48) {
            openSign(player, lines -> {
                this.search = lines.get(0);
                this.update();
                this.open();
            });
        }

        if (e.getSlot() % 9 != 0 && e.getSlot() % 9 != 8)
            player.give(new Item(player, Items.get(Functions.getId(e.getCurrentItem()))));
    }
}
