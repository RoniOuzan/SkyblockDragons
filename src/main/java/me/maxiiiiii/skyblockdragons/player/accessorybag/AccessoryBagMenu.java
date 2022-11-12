package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;

public class AccessoryBagMenu extends Menu {
    public AccessoryBagMenu(PlayerSD player) {
        super(player, "Accessory Bag", 6, InventoryGlassType.NOTHING, false);
    }

    @Override
    public void update() {
        for (int i = 45; i < 54; i++) {
            if (i == 48 || i == 49) continue;
            this.setItem(i, getGLASS());
        }

        List<Item> accessories = player.getPlayerItems().getAccessoryBag().getItems();
        for (int i = 0; i < 45; i++) {
            if (i < accessories.size())
                this.setItem(i, accessories.get(i));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() < 45) e.setCancelled(false);
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {
        List<Item> accessories = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            if (!Functions.isNotAir(this.getItem(i))) continue;

            Item item = new Item(e.getInventory().getItem(i));
            if (Functions.isNotAir(item) && Functions.getItemMaterial(item) instanceof AccessoryMaterial) {
                accessories.add(item);
            }
        }
        player.getPlayerItems().setAccessoryBag(accessories);
    }
}
