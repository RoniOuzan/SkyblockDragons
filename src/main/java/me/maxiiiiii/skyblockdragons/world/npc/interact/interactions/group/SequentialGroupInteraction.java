package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

import java.util.ArrayList;
import java.util.List;

public class SequentialGroupInteraction extends NpcGroupInteraction {
    private List<Integer> interacted;
    private int currentInteraction;

    public SequentialGroupInteraction(NPC npc, NpcInteraction... interactions) {
        super(npc, interactions);
    }

    @Override
    public void onInteract(PlayerSD player) {
        this.interacted = new ArrayList<>();
        this.currentInteraction = 0;
        Functions.While(() -> !this.isFinished(player), 2L, i -> {
            NpcInteraction interaction = this.interactions.get(currentInteraction);

            if (!this.interacted.contains(this.currentInteraction)) {
                interaction.onInteract(player);
                this.interacted.add(this.currentInteraction);
            }

            if (interaction.isFinished(player)) {
                this.currentInteraction++;
            }
        });
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return this.currentInteraction >= this.interactions.size();
    }
}
