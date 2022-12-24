package me.maxiiiiii.skyblockdragons.item.drops;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import me.maxiiiiii.skyblockdragons.item.drops.types.block.BlockDrop;
import me.maxiiiiii.skyblockdragons.mining.material.types.OreMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateDropEvent extends UpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Drop drop;
    private final Object source;

    private final Multiplier chanceMultiplier;
    private final Multiplier amountMultiplier;

    private int amount;

    public UpdateDropEvent(PlayerSD player, Drop drop, Object source) {
        super(player);
        this.drop = drop;
        this.source = source;

        this.chanceMultiplier = new Multiplier();
        this.chanceMultiplier.addPost(player.getStats().getMagicFind().get());
        this.amountMultiplier = new Multiplier();
        if (drop instanceof BlockDrop && ((BlockDrop) drop).getBlockMaterial() instanceof OreMaterial) {
            this.amountMultiplier.addBase(player.getStats().getMiningFortune().get());
        }

        this.amount = drop.getAmount();
    }

    public void applyAmountMultipliers() {
        double amount = this.amountMultiplier.multiply(this.drop.getAmount());
        this.amount = (int) (Math.floor(amount) + (Math.random() < amount % 1 ? 1 : 0));
    }

    public PlayerSD getPlayer() {
        return (PlayerSD) super.getEntity();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
