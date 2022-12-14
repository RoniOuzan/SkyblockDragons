package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
public class NpcInteractions {
    private final PlayerSD player;
    private final InteractableNPC npc;
    private final List<NpcInteraction> interacts;
    private int interaction;

    private boolean isInInteraction;

    public NpcInteractions(PlayerSD player, InteractableNPC npc, List<Function<PlayerSD, ? extends NpcInteraction>> interacts) {
        this.player = player;
        this.npc = npc;
        this.interacts = new ArrayList<>();
        for (Function<PlayerSD, ? extends NpcInteraction> interact : interacts) {
            this.interacts.add(interact.apply(player).clone());
        }
        this.interaction = Variables.getInt(this.player.getUniqueId(), "NPCInteractions", this.npc.getName(), 0);
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

    public void save() {
        Variables.set(this.player.getUniqueId(), "NPCInteractions", this.npc.getName(), this.interaction);
    }
}
