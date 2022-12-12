package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NpcInteractions {
    private final PlayerSD player;
    private final List<NpcInteraction> interacts;
    private int interaction;

    private boolean isInInteraction;

    public NpcInteractions(PlayerSD player, List<NpcInteraction> interacts) {
        this.player = player;
        this.interacts = new ArrayList<>();
        for (NpcInteraction interact : interacts) {
            this.interacts.add(interact.clone());
        }
        this.interaction = 0;
        this.isInInteraction = false;
    }

    public void interact() {
        if (this.isInInteraction || this.interaction >= this.interacts.size()) return;

        NpcInteraction interact = this.interacts.get(this.interaction);
        interact.onInteract(this.player);
        this.isInInteraction = true;

        Functions.While(() -> !interact.isFinished(player), 2L, i -> {}, i -> {
            this.interaction++;
            this.isInInteraction = false;
        });
    }
}
