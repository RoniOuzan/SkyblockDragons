package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class OldDragonFullSet extends ItemFullSetBonus {
    public OldDragonFullSet() {
        super("Old Dragon",
                "Increases the " + StatType.HEALTH.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "20%" + ChatColor.GRAY + "."
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof OldDragonFullSet) {
            e.getStats().addMultiplier(StatType.HEALTH, 20, 0);
        }
    }
}
