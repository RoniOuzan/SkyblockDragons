package me.maxiiiiii.skyblockdragons.player.food;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class AbstractFood implements Listener {

    protected @Getter double duration;
    protected @Getter int level;

    public AbstractFood(double duration, int level) {
        this.duration = duration;
        this.level = level;
        SkyblockDragons.plugin.registerEvents(this);
    }

    public void second(){
        duration -= 1;
    }

    @EventHandler(ignoreCancelled = true)
    public void onUpdateStats(UpdateStatsEvent event) {
        if (event.getPlayer().getFood() == this){
            apply(event);
        }
    }

    public abstract void apply(UpdateStatsEvent event);

    @Override
    public String toString() {
        return "AbstractFood{" +
                "duration=" + duration +
                ", level=" + level +
                '}';
    }
}
