package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerGetCoinsEvent extends PlayerSDEvent implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();

    private final double amount;
    private final Multiplier multiplier;

    private boolean cancelled;

    public PlayerGetCoinsEvent(PlayerSD player, double amount) {
        super(player);
        this.amount = amount;
        this.multiplier = new Multiplier();

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
