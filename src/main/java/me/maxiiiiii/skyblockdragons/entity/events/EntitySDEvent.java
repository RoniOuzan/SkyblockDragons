package me.maxiiiiii.skyblockdragons.entity.events;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.event.Event;

public abstract class EntitySDEvent extends Event {
    protected final EntitySD entity;

    public EntitySDEvent(final EntitySD entity) {
        this.entity = entity;
    }

    public final EntitySD getEntity() {
        return this.entity;
    }
}
