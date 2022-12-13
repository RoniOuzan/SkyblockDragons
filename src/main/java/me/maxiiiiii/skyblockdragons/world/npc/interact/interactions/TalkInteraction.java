package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.npc.interact.NpcInteraction;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class TalkInteraction extends NpcInteraction {
    private final String message;

    public TalkInteraction(NPC npc, String message) {
        super(npc);
        this.message = message;
    }

    @Override
    public void onInteract(PlayerSD player) {
        player.playSound(Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.2);
        player.sendMessage(ChatColor.YELLOW, "[NPC] ", ChatColor.WHITE, this.npc.getName(), ChatColor.WHITE, ": ", this.message);
    }

    @Override
    public boolean isFinished(PlayerSD player) {
        return true;
    }
}
