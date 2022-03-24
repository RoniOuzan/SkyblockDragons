package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryChecker {
    public static void checkInventory(Player player) {
        for (int i = 0; i < 36; i++) {
            try {
                player.sendMessage(i + " " + player.getName());
                ItemStack item = player.getInventory().getItem(i);
                ItemMeta meta = item.getItemMeta();
                player.sendMessage(meta.getLore().size() + " | " + item.getType());
                if (meta.getLore().size() <= 0) {
                    player.getInventory().setItem(i, new Item(ItemMaterial.Items.get(item.getType().name())));
                }
            } catch (NullPointerException ignored) {}
        }
    }
}
