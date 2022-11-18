package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerGetExperienceEvent extends MultiplierUpdateEvent implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();

    private final double baseAmount;

    private boolean cancelled;

    public PlayerGetExperienceEvent(PlayerSD player, double baseAmount) {
        super(player);
        this.baseAmount = baseAmount;

        this.cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
