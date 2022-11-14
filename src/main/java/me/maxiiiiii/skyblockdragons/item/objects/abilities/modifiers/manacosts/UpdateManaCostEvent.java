package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateManaCostEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final ItemAbilityManaCost manaCost;
    private final Item item;

    public UpdateManaCostEvent(PlayerSD entity, ItemAbilityManaCost manaCost, Item item) {
        super(entity);
        this.manaCost = manaCost;
        this.item = item;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
