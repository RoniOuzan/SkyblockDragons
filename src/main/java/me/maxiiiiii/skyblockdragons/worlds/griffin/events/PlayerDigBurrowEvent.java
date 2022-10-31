package me.maxiiiiii.skyblockdragons.worlds.griffin.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerDigBurrowEvent extends PlayerSDEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final Location location;
    private boolean isCancelled;

    public PlayerDigBurrowEvent(PlayerSD player) {
        super(player);
        this.location = player.getGriffin().getBurrow();

        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
