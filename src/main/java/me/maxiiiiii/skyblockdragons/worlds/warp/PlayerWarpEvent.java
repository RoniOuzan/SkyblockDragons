package me.maxiiiiii.skyblockdragons.worlds.warp;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

@Getter
public class PlayerWarpEvent extends PlayerEvent {
    public static final HandlerList handler = new HandlerList();

    private final Warp warp;

    public PlayerWarpEvent(Player player, Warp warp) {
        super(player);
        this.warp = warp;
    }

    @Override
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList() {
        return handler;
    }
}
