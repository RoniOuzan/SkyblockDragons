package me.maxiiiiii.skyblockdragons.item.material.interfaces;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;

public interface ItemStatsAble extends ItemAble {
    Stats getStats();

    default int getMaxCrystals() {
        return 2;
    }
}
