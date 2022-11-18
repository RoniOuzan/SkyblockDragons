package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class CocaFood extends AbstractFood {

    public CocaFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addMultiplier(StatTypes.SPEED, -10);
        event.getStats().addMultiplier(StatTypes.DEFENSE, 30);
    }

    @Override
    public String toString() {
        return "CocaFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
