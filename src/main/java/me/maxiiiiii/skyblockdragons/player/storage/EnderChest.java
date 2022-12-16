package me.maxiiiiii.skyblockdragons.player.storage;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EnderChest {
    private final Map<Integer, ItemStack> items;
    private final PlayerSD player;

    public EnderChest(PlayerSD player) {
        this.items = new HashMap<>();
        this.player = player;

        for (Map.Entry<String, ?> entry : Variables.getVariablesList(player.getUniqueId(), "EnderChest").entrySet()) {
            if (entry.getValue() instanceof ItemStack)
                items.put(Integer.valueOf(entry.getKey()), (ItemStack) entry.getValue());
        }
    }

    public Map<Integer, ItemStack> getItems() {
        return this.items;
    }

    public void setItem(int key, ItemStack item) {
        this.items.put(key, item);
    }

    public void removeItem(int key) {
        this.items.remove(key);
    }

    public void save() {
        Variables.delete(this.player.getUniqueId(), "EnderChest");
        for (int key : this.items.keySet()) {
//            if (!Functions.isNotAir(this.items.get(key))) continue;
            Variables.set(this.player.getUniqueId(), "EnderChest", key, this.items.get(key));
        }
    }
}
