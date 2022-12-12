package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions.group;

import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

import java.util.Arrays;
import java.util.List;

public abstract class NpcGroupInteraction extends NpcInteraction {
    protected final List<NpcInteraction> interactions;

    public NpcGroupInteraction(NPC npc, NpcInteraction... interactions) {
        super(npc);
        this.interactions = Arrays.asList(interactions);
    }
}
