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
    private final InteractableNPC npc;
    private final List<NpcInteraction> interacts;

    private boolean isInInteraction;

    public NpcInteractions(PlayerSD player, InteractableNPC npc, List<Function<PlayerSD, ? extends NpcInteraction>> interacts) {
        this.player = player;
        this.npc = npc;
        this.interacts = new ArrayList<>();
        for (Function<PlayerSD, ? extends NpcInteraction> interact : interacts) {
            this.interacts.add(interact.apply(player).clone());
        }
        this.isInInteraction = false;
    }

    public void interact() {
        player.sendMessage(player.getNpcInteraction());
        int interaction = player.getNpcInteraction().get(npc);
        if (this.isInInteraction || interaction >= this.interacts.size())
            return;

        NpcInteraction interact = this.interacts.get(interaction);
        interact.onInteract(this.player);
        this.isInInteraction = true;

        Functions.While(() -> !interact.isFinished(player), 2L, i -> {}, i -> {
            if (interact.repeatUntil.apply(player))
                player.getNpcInteraction().put(npc, interaction + 1);
            this.isInInteraction = false;
        });
    }
}
