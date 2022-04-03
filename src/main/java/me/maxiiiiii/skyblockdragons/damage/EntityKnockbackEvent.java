package me.maxiiiiii.skyblockdragons.damage;

import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.util.Vector;

public class EntityKnockbackEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final EntitySD entitySD;
    private final EntitySD source;
    private Vector velocity;
    private boolean isCancelled;

    public EntityKnockbackEvent(final EntitySD entity, final EntitySD source, Vector velocity) {
        super(entity.entity);
        this.entitySD = entity;
        this.source = source;
        this.velocity = velocity.multiply(1 - (entity.type.getKnockbackResistance()));
        this.isCancelled = false;
    }

    public EntityKnockbackEvent(final EntitySD entity, final EntitySD source) {
        this(entity, source, new Vector(
                entity.entity.getLocation().getX() - source.entity.getLocation().getX(),
                0,
                entity.entity.getLocation().getZ() - source.entity.getLocation().getZ()
        ).normalize().multiply(0.6).multiply((source instanceof Player && ((Player) source.entity).isSprinting()) ? 1.2 : 1).setY(0.3));
        System.out.println(this.velocity);
    }

    public EntitySD getEntitySD() {
        return this.entitySD;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public EntitySD getSource() {
        return this.source;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
