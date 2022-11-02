package me.maxiiiiii.skyblockdragons.damage.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.EntityDamage;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateEntityDamageEvent extends UpdateEvent {
    public static final HandlerList handlers = new HandlerList();

    protected final EntityDamage damage;

    public UpdateEntityDamageEvent(EntityDamage damage) {
        super(damage.getVictim());
        this.damage = damage;
    }

    public EntitySD getVictim() {
        return this.damage.getVictim();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
