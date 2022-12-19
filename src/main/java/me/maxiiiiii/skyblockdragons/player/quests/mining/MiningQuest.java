package me.maxiiiiii.skyblockdragons.player.quests.mining;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.quests.Quest;
import me.maxiiiiii.skyblockdragons.world.region.WorldRegion;

import java.util.function.Function;

public abstract class MiningQuest extends Quest {
    public MiningQuest(PlayerSD player, WorldRegion region, boolean isCompleted, Function<PlayerSD, String> description) {
        super(player, region, isCompleted, description);
    }

    public MiningQuest(PlayerSD player, WorldRegion region, boolean isCompleted, String description) {
        super(player, region, isCompleted, description);
    }
}
