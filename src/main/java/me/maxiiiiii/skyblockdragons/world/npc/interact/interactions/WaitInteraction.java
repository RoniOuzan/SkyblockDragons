package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

@Getter
public class WaitInteraction extends NpcInteraction {
    private final double duration;
    private double startTime;

    public WaitInteraction(NPC npc, double duration) {
        super(npc);
        this.duration = duration;
    }

    @Override
    public void onInteract(PlayerSD player) {
        this.startTime = SkyblockDragons.getCurrentTimeInSeconds();
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return SkyblockDragons.getCurrentTimeInSeconds() - this.startTime >= this.duration;
    }
}
