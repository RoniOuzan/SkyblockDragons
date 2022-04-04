package me.maxiiiiii.skyblockdragons.worlds.mining;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockEvent extends BlockBreakEvent {
    private static final HandlerList handlers = new HandlerList();
    private final BlockMaterial material;

    public PlayerBreakBlockEvent(Player player, Block theBlock, BlockMaterial material) {
        super(theBlock, player);
        this.material = material;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public BlockMaterial getMaterial() {
        return material;
    }
}
