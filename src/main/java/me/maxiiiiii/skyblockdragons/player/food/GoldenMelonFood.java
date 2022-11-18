package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class GoldenMelonFood extends AbstractFood {

    public GoldenMelonFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().add(StatTypes.CRIT_CHANCE, 20);
        event.getStats().addMultiplier(StatTypes.CRIT_DAMAGE, 30);
    }

    @Override
    public String toString() {
        return "GoldenMelonFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
