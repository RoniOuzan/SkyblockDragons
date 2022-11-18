package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class MelonFood extends AbstractFood {

    public MelonFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addMultiplier(StatTypes.STRENGTH, 30);
    }

    @Override
    public String toString() {
        return "MelonFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
