package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.function.Function;

public class ItemReward implements Reward {
    private final Function<PlayerSD, Item> item;

    public ItemReward(Function<PlayerSD, Item> item) {
        this.item = item;
    }

    public ItemReward(Item item) {
        this(p -> item);
    }

    @Override
    public void give(PlayerSD player) {
        player.give(this.item.apply(player));
    }

    @Override
    public String getLore(PlayerSD player) {
        return this.item.apply(player).getItemMeta().getDisplayName();
    }
}
