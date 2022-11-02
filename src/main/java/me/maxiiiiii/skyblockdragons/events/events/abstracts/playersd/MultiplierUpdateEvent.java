package me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;

@Getter
public abstract class MultiplierUpdateEvent extends UpdateEvent {
    protected final Multiplier multiplier;

    public MultiplierUpdateEvent(PlayerSD entity) {
        super(entity);
        this.multiplier = new Multiplier();
    }

    @Override
    public PlayerSD getEntity() {
        return this.getPlayer();
    }

    public PlayerSD getPlayer() {
        return (PlayerSD) super.getEntity();
    }
}
