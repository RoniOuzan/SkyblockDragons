package me.maxiiiiii.skyblockdragons.world.npc.interact.interactions;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

@Getter
public class TalkInteraction extends WaitInteraction {
    private final String message;

    public TalkInteraction(NPC npc, String message, double delay) {
        super(npc, delay);
        this.message = message;
    }

    public TalkInteraction(NPC npc, String message) {
        this(npc, message, 0);
    }

    @Override
    public void onInteract(PlayerSD player) {
        super.onInteract(player);
        player.playSound(Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.2);
        player.sendMessage(ChatColor.YELLOW, "[NPC] ", ChatColor.WHITE, this.npc.getName(), ChatColor.WHITE, ": ", this.message);
    }
}
