package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.polar;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class PolarBearFullSet  extends ItemFullSetBonus {
    public PolarBearFullSet() {
        super("Brown Bear",
                "Increases " + ChatColor.GREEN + StatTypes.DEFENSE.getIcon() + " Defense " + ChatColor.GRAY + "by " + ChatColor.GREEN + "5%" + "."
        );
    }
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof PolarBearFullSet) {
            e.getStats().addMultiplier(StatTypes.DEFENSE, 5, 0);
        }
    }
}