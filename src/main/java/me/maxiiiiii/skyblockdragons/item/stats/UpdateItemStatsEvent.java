package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateItemStatsEvent extends UpdateEvent {
    public static final HandlerList handlers = new HandlerList();

    private final ItemStats stats;

    public UpdateItemStatsEvent(PlayerSD player, ItemStats stats) {
        super(player);
        this.stats = stats;
    }

    public PlayerSD getPlayer() {
        return (PlayerSD) super.getEntity();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
