package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

public class ParallelGroupInteraction extends NpcGroupInteraction {
    public ParallelGroupInteraction(NPC npc, NpcInteraction... interactions) {
        super(npc, interactions);
    }

    @Override
    public void onInteract(PlayerSD player) {
        for (NpcInteraction interaction : this.interactions) {
            interaction.onInteract(player);
        }
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return this.interactions.stream().allMatch(i -> i.isFinished(player));
    }
}
