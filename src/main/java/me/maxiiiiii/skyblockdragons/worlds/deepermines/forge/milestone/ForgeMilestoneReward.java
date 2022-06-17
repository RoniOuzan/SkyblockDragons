package me.maxiiiiii.skyblockdragons.worlds.deepermines.forge.milestone;

import me.maxiiiiii.skyblockdragons.util.objects.reward.Reward;

import java.util.List;
import java.util.function.BooleanSupplier;

public class ForgeMilestoneReward {
    private final List<Reward> rewards;
    private final BooleanSupplier requirement;
    private boolean claimed;

    public ForgeMilestoneReward(List<Reward> rewards, BooleanSupplier requirement) {
        this.rewards = rewards;
        this.requirement = requirement;
        this.claimed = false;
    }

    public boolean has() {
        return this.requirement.getAsBoolean();
    }
}
