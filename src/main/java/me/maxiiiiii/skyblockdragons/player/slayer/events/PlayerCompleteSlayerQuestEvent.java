package me.maxiiiiii.skyblockdragons.player.slayer.events;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

public class PlayerCompleteSlayerQuestEvent extends SlayerEvent {
    private static final HandlerList handlerList = new HandlerList();

    public PlayerCompleteSlayerQuestEvent(PlayerSD player) {
        super(player);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
