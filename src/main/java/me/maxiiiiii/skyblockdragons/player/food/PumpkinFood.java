package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class PumpkinFood extends AbstractFood {

    public PumpkinFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addWisdomMultipliers(10, 0);
    }

    @Override
    public String toString() {
        return "PumpkinFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
