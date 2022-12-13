package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
public class NpcInteractions {
    private final PlayerSD player;
    private final List<NpcInteraction> interacts;
    private int interaction;

    private boolean isInInteraction;

    public NpcInteractions(PlayerSD player, List<Function<PlayerSD, ? extends NpcInteraction>> interacts) {
        this.player = player;
        this.interacts = new ArrayList<>();
        for (Function<PlayerSD, ? extends NpcInteraction> interact : interacts) {
            this.interacts.add(interact.apply(player).clone());
        }
        this.interaction = 0;
        this.isInInteraction = false;
    }

    public void interact() {
        if (this.isInInteraction || this.interaction >= this.interacts.size())
            return;

        NpcInteraction interact = this.interacts.get(this.interaction);
        interact.onInteract(this.player);
        this.isInInteraction = true;

        Functions.While(() -> !interact.isFinished(player), 2L, i -> {}, i -> {
            if (interact.repeatUntil.apply(player))
                this.interaction++;
            this.isInInteraction = false;
        });
    }
}
