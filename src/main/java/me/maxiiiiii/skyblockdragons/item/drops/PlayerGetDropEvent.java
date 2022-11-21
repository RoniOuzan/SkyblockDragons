package me.maxiiiiii.skyblockdragons.item.drops;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class PlayerGetDropEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Drop drop;
    private final Object source;
    private boolean isTelekinesis;

    public PlayerGetDropEvent(PlayerSD player, Drop drop, Object source) {
        super(player);
        this.drop = drop;
        this.source = source;

        this.isTelekinesis = false;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
