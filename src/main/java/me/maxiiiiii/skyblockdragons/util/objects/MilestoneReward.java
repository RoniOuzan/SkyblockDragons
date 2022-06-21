package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.util.objects.reward.Rewards;

@Getter
@Setter
public class MilestoneReward {
    private final Rewards rewards;
    private final int requiredAmount;
    private boolean claimed;

    public MilestoneReward(Rewards rewards, int requiredAmount, boolean claimed) {
        this.rewards = rewards;
        this.requiredAmount = requiredAmount;
        this.claimed = claimed;
    }
}
