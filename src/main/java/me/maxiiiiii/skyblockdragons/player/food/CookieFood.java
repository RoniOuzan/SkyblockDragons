package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class CookieFood extends AbstractFood {

    public CookieFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addMultiplier(StatTypes.MAGIC_FIND, 20);
        event.getStats().addMultiplier(StatTypes.PET_LUCK, 20);
    }

    @Override
    public String toString() {
        return "CookieFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
