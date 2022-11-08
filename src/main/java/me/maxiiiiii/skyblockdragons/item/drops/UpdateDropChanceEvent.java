package me.maxiiiiii.skyblockdragons.item.drops;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateDropChanceEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Drop drop;
    private final Object source;

    public UpdateDropChanceEvent(PlayerSD player, Drop drop, Object source) {
        super(player);
        this.drop = drop;
        this.source = source;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}