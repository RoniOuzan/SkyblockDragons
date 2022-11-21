package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerUseAbilityEvent extends PlayerSDEvent {
    public static final HandlerList handlers = new HandlerList();

    private final Item item;
    private final ItemAbility ability;

    public PlayerUseAbilityEvent(PlayerSD player, Item item, ItemAbility ability) {
        super(player);
        this.item = item;
        this.ability = ability;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
