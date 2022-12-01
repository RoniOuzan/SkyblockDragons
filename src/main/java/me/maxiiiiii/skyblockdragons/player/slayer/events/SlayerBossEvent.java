package me.maxiiiiii.skyblockdragons.player.slayer.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

@Getter
public abstract class SlayerBossEvent extends SlayerEvent {
    private final EntitySD boss;

    public SlayerBossEvent(PlayerSD player, EntitySD boss) {
        super(player);
        this.boss = boss;
    }
}
