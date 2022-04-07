package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AccessoryBag {
    private final PlayerSD player;
    private List<ItemStack> items;

    public AccessoryBag(PlayerSD player) {
        this.player = player;
        this.items = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            ItemStack itemStack = Variables.get(player.getUniqueId(), "AccessoryBag", i);
            if (itemStack == null) break;
            this.items.add(itemStack);
        }
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(List<ItemStack> items) {
        this.items = items;
    }

    public void save() {
        Variables.delete(player.getUniqueId(), "AccessoryBag");
        for (int i = 0; i < items.size(); i++) {
            Variables.set(player.getUniqueId(), "AccessoryBag", i, items.get(i));
        }
    }
}
