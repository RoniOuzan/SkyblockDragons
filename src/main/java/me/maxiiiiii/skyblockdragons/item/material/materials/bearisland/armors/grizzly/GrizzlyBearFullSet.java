package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.grizzly;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class GrizzlyBearFullSet  extends ItemFullSetBonus {
    public GrizzlyBearFullSet() {
        super("Brown Bear",
                "Increases " + ChatColor.RED + StatTypes.FEROCITY.getIcon() + " Ferocity " + ChatColor.GRAY + "by " + ChatColor.GREEN + "5%" + "."
        );
        }
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof GrizzlyBearFullSet) {
            e.getStats().addMultiplier(StatTypes.FEROCITY, 5, 0);
        }
    }
}