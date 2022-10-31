package me.maxiiiiii.skyblockdragons.events.events.update;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerUpdateStatsEvent extends PlayerSDUpdateEvent {
    public static final HandlerList handlers = new HandlerList();

    private final PlayerStats stats;

    public PlayerUpdateStatsEvent(PlayerSD player, PlayerStats stats) {
        super(player);
        this.stats = stats;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
