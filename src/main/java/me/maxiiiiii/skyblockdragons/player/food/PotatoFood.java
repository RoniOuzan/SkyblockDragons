package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;

public class PotatoFood extends AbstractFood {

    public PotatoFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addCombatMultipliers(10, 0);
    }

    @Override
    public String toString() {
        return "AttackFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
