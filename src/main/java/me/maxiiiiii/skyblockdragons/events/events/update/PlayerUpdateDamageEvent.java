package me.maxiiiiii.skyblockdragons.events.events.update;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerUpdateDamageEvent extends PlayerSDUpdateEvent {
    public static final HandlerList handlers = new HandlerList();

    private final EntityDamage<?, ?> damage;

    public PlayerUpdateDamageEvent(PlayerSD player, EntityDamage<?, ?> damage) {
        super(player);
        this.damage = damage;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
