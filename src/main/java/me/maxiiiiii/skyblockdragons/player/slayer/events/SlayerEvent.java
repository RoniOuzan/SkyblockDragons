package me.maxiiiiii.skyblockdragons.player.slayer.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public abstract class SlayerEvent extends PlayerSDEvent {
    public SlayerEvent(PlayerSD player) {
        super(player);
    }
}
