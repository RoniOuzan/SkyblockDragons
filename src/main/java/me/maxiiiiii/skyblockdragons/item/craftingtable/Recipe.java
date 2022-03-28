package me.maxiiiiii.skyblockdragons.item.craftingtable;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public enum Recipe {
    ASPECT_OF_THE_END(new Item(ItemMaterial.get("ASPECT_OF_THE_END")), getItems(null, new Item(ItemMaterial.get("ENCHANTED_EYE_OF_ENDER"), 16), null, null, new Item(ItemMaterial.get("ENCHANTED_EYE_OF_ENDER"), 16), null, null, new Item(ItemMaterial.get("ENCHANTED_DIAMOND")), null), 0),
    ASPECT_OF_THE_VOID(new Item(ItemMaterial.get("ASPECT_OF_THE_VOID")), getItems(null, new Item(ItemMaterial.get("NULL_OVOID"), 8), null, new Item(ItemMaterial.get("NULL_OVOID"), 8), new Item(ItemMaterial.get("ASPECT_OF_THE_END")), new Item(ItemMaterial.get("NULL_OVOID"), 8), null, new Item(ItemMaterial.get("NULL_OVOID"), 8), null), 4),
    ENCHANTED_DIAMOND(new Item(ItemMaterial.get("ENCHANTED_DIAMOND")), getItems(new Item(Items.items.get("DIAMOND"), 32), new Item(Items.items.get("DIAMOND"), 32), new Item(Items.items.get("DIAMOND"), 32), new Item(Items.items.get("DIAMOND"), 32), new Item(Items.items.get("DIAMOND"), 32), null, null, null, null), 0);

    private final Item item;
    private final Item[] items;
    private final int slotToUpgrade;
    private final boolean viewable;

    Recipe(Item item, Item[] items, int slotToUpgrade, boolean viewable) {
        this.item = item;
        this.items = items;
        this.slotToUpgrade = slotToUpgrade;
        this.viewable = viewable;
    }

    Recipe(Item item, Item[] items, int slotToUpgrade) {
        this(item, items, slotToUpgrade, true);
    }

    public void view(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, setTitleCase(this.name()) + " Recipe");

        ItemStack glass = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(49, close);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        ItemStack craftingTable = createItem(Material.WORKBENCH, ChatColor.GREEN + "Crafting Table", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Craft this recipe by using a", ChatColor.GRAY + "crafting table.")));
        inventory.setItem(23, craftingTable);

        ItemStack item = this.item;
        inventory.setItem(25, item);

        for (int i = 0; i < 9; i++) {
            try {
                inventory.setItem(numToSlot(i), this.items[i]);
            } catch (NullPointerException ex) {
                inventory.setItem(numToSlot(i), new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inventory);
    }

    private static Item[] getItems(Item item1, Item item2, Item item3, Item item4, Item item5, Item item6, Item item7, Item item8, Item item9) {
        return new Item[]{item1, item2, item3, item4, item5, item6, item7, item8, item9};
    }
}
