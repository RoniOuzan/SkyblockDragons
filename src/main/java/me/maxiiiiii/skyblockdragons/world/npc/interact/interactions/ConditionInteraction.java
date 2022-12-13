package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

import java.util.function.Supplier;

public class ConditionInteraction extends NpcInteraction {
    private final Supplier<Boolean> condition;
    private final NpcInteraction onTrue;
    private final NpcInteraction onFalse;
    private boolean ran;

    public ConditionInteraction(NPC npc, Supplier<Boolean> condition, NpcInteraction onTrue, NpcInteraction onFalse) {
        super(npc);
        this.condition = condition;
        this.onTrue = onTrue;
        this.onFalse = onFalse;
    }

    public ConditionInteraction(NPC npc, Supplier<Boolean> condition, NpcInteraction onTrue) {
        this(npc, condition, onTrue, null);
    }

    @Override
    public void onInteract(PlayerSD player) {
        this.ran = this.condition.get();
        if (this.ran) {
            this.onTrue.onInteract(player);
        } else if (this.onFalse != null) {
            this.onFalse.onInteract(player);
        }
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return this.ran ? this.onTrue.isFinished(player) : this.onFalse == null || this.onFalse.isFinished(player);
    }
}
