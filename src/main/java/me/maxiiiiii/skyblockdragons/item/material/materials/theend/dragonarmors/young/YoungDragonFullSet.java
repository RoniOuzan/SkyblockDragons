package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.young;

import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class YoungDragonFullSet extends ItemFullSetBonus {
    public YoungDragonFullSet() {
        super("Young blood",
                "Gain " + ChatColor.WHITE + "+70" + StatType.SPEED.getIconAndText() + " " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + "."
        );
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void updateStats(UpdateStatsEvent e) {
        if (!(e.getPlayer().getItems().getFullSet() instanceof YoungDragonFullSet)) return;

        if (e.getPlayer().getHealth() >= e.getStats().getHealth().get() / 2) {
            e.getStats().getSpeed().add(70);
        }
    }
}
