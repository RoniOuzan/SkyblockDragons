package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

public class RunInteraction extends NpcInteraction {
    private final Runnable runnable;

    public RunInteraction(NPC npc, Runnable runnable) {
        super(npc);
        this.runnable = runnable;
    }

    @Override
    public void onInteract(PlayerSD player) {
        this.runnable.run();
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return true;
    }
}
