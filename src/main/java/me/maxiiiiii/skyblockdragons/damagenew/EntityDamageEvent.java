package me.maxiiiiii.skyblockdragons.damagenew;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class EntityDamageEvent extends Event {
    public static final HandlerList handlers = new HandlerList();

    private final EntityDamage damage;
    private final EntitySD attacker;
    private final EntitySD victim;

    public EntityDamageEvent(EntityDamage damage) {
        this.damage = damage;
        this.attacker = damage instanceof EntityDamageEntity ? ((EntityDamageEntity) damage).getAttacker() : null;
        this.victim = damage.getVictim();
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
