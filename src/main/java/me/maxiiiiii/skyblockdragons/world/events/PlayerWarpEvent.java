package me.maxiiiiii.skyblockdragons.world.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerWarpEvent extends PlayerSDEvent {
    public static final HandlerList handler = new HandlerList();

    private final Warp warp;
    private final Location location;

    public PlayerWarpEvent(PlayerSD player, Warp warp) {
        super(player);
        this.warp = warp;
        this.location = player.getLocation();
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}
