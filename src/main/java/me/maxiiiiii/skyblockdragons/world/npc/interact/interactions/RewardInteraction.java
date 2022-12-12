package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.reward.Reward;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;

import java.util.Arrays;
import java.util.List;

public class RewardInteraction extends WaitInteraction {
    private final List<Reward> rewards;

    public RewardInteraction(NPC npc, int delay, Reward... rewards) {
        super(npc, delay);
        this.rewards = Arrays.asList(rewards);
    }

    public RewardInteraction(NPC npc, Reward... rewards) {
        this(npc, 0, rewards);
    }

    @Override
    public void onInteract(PlayerSD player) {
        for (Reward reward : this.rewards) {
            reward.give(player);
        }
    }
}
