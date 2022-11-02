package me.maxiiiiii.skyblockdragons.item.events;

import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

public class UpdateDropChanceEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    public UpdateDropChanceEvent(PlayerSD entity) {
        super(entity);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
