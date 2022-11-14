package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateCooldownEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Item item;
    private final ItemAbilitySilentCooldown cooldown;

    public UpdateCooldownEvent(PlayerSD entity, Item item, ItemAbilitySilentCooldown cooldown) {
        super(entity);
        this.item = item;
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
