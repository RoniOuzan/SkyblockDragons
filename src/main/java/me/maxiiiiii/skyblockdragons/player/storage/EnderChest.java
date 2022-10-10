package me.maxiiiiii.skyblockdragons.player.storage;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variable;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.serialization.Serializer;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EnderChest {
    private final Map<Integer, ItemStack> items;
    private final PlayerSD player;

    public EnderChest(PlayerSD player) {
        this.items = new HashMap<>();
        this.player = player;

        for (Variable variable : Variables.getVariableAll(player.getUniqueId(), "EnderChest")) {
            items.put(variable.data, Serializer.deserialize(variable.value));
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
