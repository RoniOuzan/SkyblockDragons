package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.Event;

public abstract class PlayerSDEvent extends Event {
    protected final PlayerSD player;

    public PlayerSDEvent(final PlayerSD player) {
        this.player = player;
    }

    public final PlayerSD getPlayer() {
        return this.player;
    }
}
