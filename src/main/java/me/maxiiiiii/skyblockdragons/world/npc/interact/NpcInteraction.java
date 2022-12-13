package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;

import java.util.function.Function;

@Getter
public abstract class NpcInteraction implements Cloneable {
    protected final NPC npc;
    protected Function<PlayerSD, Boolean> repeatUntil;

    public NpcInteraction(NPC npc) {
        this.npc = npc;
        this.repeatUntil = p -> true;
    }

    public NpcInteraction setRepeatUntil(Function<PlayerSD, Boolean> repeatUntil) {
        this.repeatUntil = repeatUntil;
        return this;
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
