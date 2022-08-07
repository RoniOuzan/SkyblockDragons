package me.maxiiiiii.skyblockdragons.worlds.end.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

@Getter
public class PlayerPlaceEyeEvent extends PlayerSDEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private static int amountOfEyes = 0;

    protected Block block;
    protected ItemStack item;

    protected boolean cancelled;

    public PlayerPlaceEyeEvent(PlayerSD player, Block block, ItemStack item) {
        super(player);
        this.block = block;
        this.item = item;
        this.cancelled = false;
    }

    public int getAmountOfEyes() {
        return amountOfEyes;
    }

    public void addToAmountOfEyes() {
        amountOfEyes++;
    }

    public static void resetAmountOfEyes() {
        amountOfEyes = 0;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}
}
