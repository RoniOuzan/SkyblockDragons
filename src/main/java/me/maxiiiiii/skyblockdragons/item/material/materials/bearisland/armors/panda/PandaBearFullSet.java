package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.panda;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class PandaBearFullSet extends ItemFullSetBonus {
    public PandaBearFullSet() {
        super("Panda Bear",
                "Increases " + ChatColor.WHITE + StatTypes.SPEED.getIcon() + " Speed " + ChatColor.GRAY + "by " + ChatColor.GREEN + "10%" + "."
        );
    }
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof PandaBearFullSet) {
            e.getStats().addMultiplier(StatTypes.SPEED, 10, 0);
        }
    }
}