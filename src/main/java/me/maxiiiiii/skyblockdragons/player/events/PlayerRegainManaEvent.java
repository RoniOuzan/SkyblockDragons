package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerRegainManaEvent extends UpdateEvent implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();

    private final double amount;

    private boolean cancelled;

    public PlayerRegainManaEvent(PlayerSD player, double amount) {
        super(player);
        this.amount = amount;

        this.cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
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
