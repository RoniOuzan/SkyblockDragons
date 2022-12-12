package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class Rewards extends ArrayList<Reward> {
    public Rewards(Object... rewards) {
        super();
        for (Object reward : rewards) {
            if (reward instanceof Reward)
                this.add((Reward) reward);
            else if (reward instanceof Item)
                this.add(new ItemReward(p -> (Item) reward));
            else if (reward instanceof Function<?, ?>)
                this.add(new ItemReward((Function<PlayerSD, Item>) reward));
            else if (reward instanceof Collection<?>)
                this.addAll((Collection<? extends Reward>) reward);
        }
    }

    public void give(PlayerSD player) {
        for (Reward reward : this) {
            reward.give(player);
        }
    }
}
