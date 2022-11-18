package me.maxiiiiii.skyblockdragons.player.food;

import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SugarCaneFood extends AbstractFood {

    public SugarCaneFood(double duration, int level) {
        super(duration, level);
    }

    @Override
    public void apply(UpdateStatsEvent event) {
        event.getStats().addMultiplier(StatTypes.SPEED, 20);
        event.getStats().addMultiplier(StatTypes.ATTACK_SPEED, 10);
        event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int) duration, level));
    }

    @Override
    public String toString() {
        return "SugarCaneFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
