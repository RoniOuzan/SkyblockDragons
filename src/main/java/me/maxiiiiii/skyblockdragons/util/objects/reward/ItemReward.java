package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.inventory.ItemStack;

public class ItemReward implements Reward {
    private final ItemStack item;

    public ItemReward(ItemStack item) {
        this.item = item;
    }

    @Override
    public void give(PlayerSD player) {
        player.give(this.item);
    }

    @Override
    public String getLore() {
        return this.item.getItemMeta().getDisplayName();
    }
}
