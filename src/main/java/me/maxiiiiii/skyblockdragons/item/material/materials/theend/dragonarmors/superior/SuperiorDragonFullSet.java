package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.superior;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;

public class SuperiorDragonFullSet extends ItemFullSetBonus {
    public SuperiorDragonFullSet() {
        super("Superior blood",
                "All of your stats are increased by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the " + ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "more damage."
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {
        stats.addAllStatsMultipliers(5, 0);
    }
}
