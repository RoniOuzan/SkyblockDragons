package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;

@Getter
public abstract class NpcInteraction implements Cloneable {
    protected final NPC npc;

    public NpcInteraction(NPC npc) {
        this.npc = npc;
    }

    public abstract void onInteract(PlayerSD player);

    public abstract boolean isFinished(PlayerSD  player);

    @Override
    public NpcInteraction clone() {
        try {
            return (NpcInteraction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
