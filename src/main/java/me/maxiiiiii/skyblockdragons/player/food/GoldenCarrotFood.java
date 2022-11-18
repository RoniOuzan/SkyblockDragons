package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class GoldenCarrotFood extends AbstractFood {

    public GoldenCarrotFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().add(StatTypes.VITALITY, 30);
    }

    @Override
    public String toString() {
        return "GoldenCarrotFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
