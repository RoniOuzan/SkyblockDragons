package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.quests.Quest;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;

public class StartQuestInteraction extends NpcInteraction {
    private final Quest quest;

    public StartQuestInteraction(NPC npc, Quest quest) {
        super(npc);
        this.quest = quest;
    }

    @Override
    public void onInteract(PlayerSD player) {
        this.quest.start();
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return true;
    }
}
