package me.maxiiiiii.skyblockdragons.events.events.update;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damagenew.EntityDamage;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateEntityDamageEvent extends UpdateEvent { // TODO: Fix this
    public static final HandlerList handlers = new HandlerList();

    private final EntityDamage entityDamage;

    public UpdateEntityDamageEvent(EntityDamage entityDamage) {
        super(entityDamage.getVictim());
        this.entityDamage = entityDamage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
