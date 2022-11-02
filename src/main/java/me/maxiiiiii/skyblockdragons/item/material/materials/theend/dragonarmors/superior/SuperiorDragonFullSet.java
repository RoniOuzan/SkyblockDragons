package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.events.events.update.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SuperiorDragonFullSet extends ItemFullSetBonus {
    public SuperiorDragonFullSet() {
        super("Superior blood",
                "All of your stats are increased by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the " + ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "more damage."
        );
    }

    @EventHandler()
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof SuperiorDragonFullSet) {
            e.getStats().addAllStatsMultipliers(5, 0);
        }
    }
}
