package me.maxiiiiii.skyblockdragons.entity.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.event.HandlerList;

@Getter
public class EntityDeathEvent extends EntitySDEvent {
    public static final HandlerList handlerList = new HandlerList();

    private final DeathCause cause;
    private final EntitySD killer;

    public EntityDeathEvent(EntitySD entity, EntitySD killer, DeathCause cause) {
        super(entity);
        this.cause = cause;
        this.killer = killer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public enum DeathCause {
        ENTITY, MAGIC
    }
}
