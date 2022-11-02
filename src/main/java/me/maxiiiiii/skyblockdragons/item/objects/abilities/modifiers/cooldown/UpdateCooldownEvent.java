package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateCooldownEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final ItemAbilitySilentCooldown cooldown;

    public UpdateCooldownEvent(PlayerSD entity, ItemAbilitySilentCooldown cooldown) {
        super(entity);
        this.cooldown = cooldown;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
