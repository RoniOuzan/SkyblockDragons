package me.maxiiiiii.skyblockdragons.entity.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.event.HandlerList;

@Getter
public class EntityDeathEvent extends EntitySDEvent {
    public static final HandlerList handlerList = new HandlerList();

    private final EntitySD killer;
    private final PlayerStats killerStats;
    private final Equipment killerEquipment;

    public EntityDeathEvent(EntitySD entity, EntitySD killer) {
        super(entity);
        this.killer = killer;
        this.killerStats = entity instanceof PlayerSD ? ((PlayerSD) entity).getStats() : null;
        this.killerEquipment = killer.getItems();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
