package me.maxiiiiii.skyblockdragons.events.events.update;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damagenew.EntityDamageEntity;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateEntityDamageEntityEvent extends UpdateEntityDamageEvent { // TODO
    public static final HandlerList handlers = new HandlerList();

    public UpdateEntityDamageEntityEvent(EntityDamageEntity damage) {
        super(damage);
    }

    public EntityDamageEntity getDamage() {
        return (EntityDamageEntity) this.getEntityDamage();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
