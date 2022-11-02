package me.maxiiiiii.skyblockdragons.item.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateStatsEvent extends UpdateEvent {
    public static final HandlerList handlers = new HandlerList();

    private final PlayerStats stats;

    public UpdateStatsEvent(PlayerStats stats) {
        super(stats.getPlayer());
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
