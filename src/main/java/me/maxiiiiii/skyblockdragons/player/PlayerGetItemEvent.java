package me.maxiiiiii.skyblockdragons.player;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.PlayerSDEvent;
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
