package me.maxiiiiii.skyblockdragons.player.slayer.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class SlayerBossSlayedEvent extends SlayerBossEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final PlayerSD killer;

    public SlayerBossSlayedEvent(PlayerSD player, EntitySD boss, PlayerSD killer) {
        super(player, boss);
        this.killer = killer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
