package me.maxiiiiii.skyblockdragons.player.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public class PlayerGetCoinsFromEntityEvent extends PlayerGetCoinsEvent {
    private final EntitySD entity;

    public PlayerGetCoinsFromEntityEvent(PlayerSD player, EntitySD entity, double amount) {
        super(player, amount);
        this.entity = entity;
    }
}
