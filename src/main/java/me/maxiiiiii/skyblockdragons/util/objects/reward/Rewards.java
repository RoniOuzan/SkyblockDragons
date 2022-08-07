package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class Rewards extends ArrayList<Reward> {
    public Rewards(Object... rewards) {
        super();
        for (Object reward : rewards) {
            if (reward instanceof Reward)
                this.add((Reward) reward);
            else if (reward instanceof ItemStack)
                this.add(new ItemReward((ItemStack) reward));
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
