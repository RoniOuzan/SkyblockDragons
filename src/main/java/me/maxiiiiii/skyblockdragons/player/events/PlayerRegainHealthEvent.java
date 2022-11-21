package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
@Setter
public class PlayerRegainHealthEvent extends PlayerSDEvent implements Cancellable {
    private static final HandlerList handlerList = new HandlerList();

    private final Multiplier multiplier;

    private boolean cancelled;

    public PlayerRegainHealthEvent(PlayerSD player) {
        super(player);
        this.multiplier = new Multiplier();

        this.cancelled = false;
    }

    public double applyMultiplier() {
        return this.multiplier.multiply(player.getMaxHealth() * 0.05);
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
