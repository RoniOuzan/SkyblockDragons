package me.maxiiiiii.skyblockdragons.worlds.end.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntitySDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

import java.util.Map;

@Getter
public class DragonKillEvent extends EntitySDEvent {
    public static final HandlerList handlers = new HandlerList();

    protected PlayerSD killer;
    protected Map<PlayerSD, Double> killers;

    public DragonKillEvent(EntitySD entity, Map<PlayerSD, Double> killers) {
        super(entity);
        if (entity.getAttacker() instanceof PlayerSD)
            this.killer = (PlayerSD) entity.getAttacker();
        else
            this.killer = null;
        this.killers = killers;
    }

    @Override
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}
}
