package me.maxiiiiii.skyblockdragons.damage.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateEntityDamageEntityEvent extends UpdateEntityDamageEvent {
    public static final HandlerList handlers = new HandlerList();

    public UpdateEntityDamageEntityEvent(EntityDamageEntity damage) {
        super(damage);
    }

    public PlayerSD getAttacker() {
        if (this.getDamage().getAttacker() instanceof PlayerSD)
            return (PlayerSD) this.getDamage().getAttacker();
        return null;
    }

    @Override
    public EntityDamageEntity getDamage() {
        return (EntityDamageEntity) super.getDamage();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
