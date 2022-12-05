package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatAdderType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class OldDragonFullSet extends ItemFullSetBonus {
    public OldDragonFullSet() {
        super("Old Dragon",
                "Increases the " + StatTypes.HEALTH + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "20%" + ChatColor.GRAY + "."
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof OldDragonFullSet) {
            e.getStats().addBaseMultiplier(StatTypes.HEALTH, 20, StatAdderType.FULL_SET_BONUS, this);
        }
    }
}
