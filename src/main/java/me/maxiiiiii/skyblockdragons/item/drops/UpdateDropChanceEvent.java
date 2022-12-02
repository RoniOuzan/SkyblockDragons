package me.maxiiiiii.skyblockdragons.item.drops;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.UpdateEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import org.bukkit.event.HandlerList;

@Getter
public class UpdateDropChanceEvent extends UpdateEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Drop drop;
    private final Object source;

    private final Multiplier chanceMultiplier;
    private final Multiplier amountMultiplier;

    public UpdateDropChanceEvent(PlayerSD player, Drop drop, Object source) {
        super(player);
        this.drop = drop;
        this.source = source;

        this.chanceMultiplier = new Multiplier();
        this.chanceMultiplier.addPost(player.getStats().getMagicFind().get());
        this.amountMultiplier = new Multiplier();
    }

    public void applyAmountMultipliers() {
        this.drop.setAmount((int) this.amountMultiplier.multiply(this.drop.getAmount()));
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
