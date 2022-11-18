package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class BreadFood extends AbstractFood {

    public BreadFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addMultiplier(StatTypes.DEFENSE, 20);
    }

    @Override
    public String toString() {
        return "BreadFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
