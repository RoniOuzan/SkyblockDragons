package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class CarrotFood extends AbstractFood {

    public CarrotFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().add(StatTypes.VITALITY, 20);
    }

    @Override
    public String toString() {
        return "CarrotFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
