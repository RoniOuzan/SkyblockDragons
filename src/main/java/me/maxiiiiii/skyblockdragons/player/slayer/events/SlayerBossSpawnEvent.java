package me.maxiiiiii.skyblockdragons.player.slayer.events;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

public class SlayerBossSpawnEvent extends SlayerBossEvent {
    private static final HandlerList handlerList = new HandlerList();

    public SlayerBossSpawnEvent(PlayerSD player, EntitySD boss) {
        super(player, boss);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
