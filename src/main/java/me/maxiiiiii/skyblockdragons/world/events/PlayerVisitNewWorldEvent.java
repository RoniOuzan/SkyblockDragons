package me.maxiiiiii.skyblockdragons.world.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerVisitNewWorldEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final WorldSD world;

    public PlayerVisitNewWorldEvent(PlayerSD player, WorldSD world) {
        super(player);
        this.world = world;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
