package me.maxiiiiii.skyblockdragons.player.slayer;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.RevenantSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.SvenSlayer;
import me.maxiiiiii.skyblockdragons.player.slayer.slayers.TarantulaSlayer;

@Getter
public class Slayers {
    private final PlayerSD player;

    private final RevenantSlayer revenant;
    private final TarantulaSlayer tarantula;
    private final SvenSlayer sven;

    private final SlayerQuest quest;

    public Slayers(PlayerSD player) {
        this.player = player;
        this.revenant = new RevenantSlayer(player);
        this.tarantula = new TarantulaSlayer(player);
        this.sven = new SvenSlayer(player);

        this.quest = new SlayerQuest(player);
    }

    public Slayer get(SlayerType slayerType) {
        switch (slayerType) {
            case REVENANT:
                return this.revenant;
            case TARANTULA:
                return this.tarantula;
            case SVEN:
                return this.sven;
        }
        return null;
    }
}
