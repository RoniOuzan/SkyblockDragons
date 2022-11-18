package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class BeetRootFood extends AbstractFood {

    public BeetRootFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addCombatMultipliers(25, 0);
    }

    @Override
    public String toString() {
        return "BeetRootFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
