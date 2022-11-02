package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

public class UpdateCooldownEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    public UpdateCooldownEvent(PlayerSD entity) {
        super(entity);
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
