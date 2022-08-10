package me.maxiiiiii.skyblockdragons.entity;

import org.bukkit.event.Event;

public abstract class EntitySDEvent extends Event {
    protected final EntitySD entity;

    public EntitySDEvent(final EntitySD player) {
        this.entity = player;
    }

    public final EntitySD getEntity() {
        return this.entity;
    }
}
