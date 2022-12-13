package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;

@Getter
public class TalkWaitInteraction extends TalkInteraction {
    private final double wait;
    private double startTime;

    public TalkWaitInteraction(NPC npc, String message, double wait) {
        super(npc, message);
        this.wait = wait;
    }

    @Override
    public void onInteract(PlayerSD player) {
        super.onInteract(player);
        this.startTime = SkyblockDragons.getCurrentTimeInSeconds();
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return SkyblockDragons.getCurrentTimeInSeconds() - this.startTime >= this.wait;
    }
}
