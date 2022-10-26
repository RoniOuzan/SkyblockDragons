package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

@Getter
public class PlayerGetItemEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final ItemStack itemStack;

    public PlayerGetItemEvent(PlayerSD player, ItemStack itemStack) {
        super(player);
        this.itemStack = itemStack;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
