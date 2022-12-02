package me.maxiiiiii.skyblockdragons.mining;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.MultiplierUpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateVoidCrystalChanceEvent extends MultiplierUpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final double baseChance;
    private final Block block;
    private final BlockMaterial material;

    public UpdateVoidCrystalChanceEvent(PlayerSD player, double baseChance, Block block, BlockMaterial material) {
        super(player);
        this.baseChance = baseChance;
        this.block = block;
        this.material = material;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
