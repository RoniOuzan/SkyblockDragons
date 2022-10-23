package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.old;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;

public class OldDragonFullSet extends ItemFullSetBonus {
    public OldDragonFullSet() {
        super("Old Dragon",
                "Increases the " + StatType.HEALTH.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "20%" + ChatColor.GRAY + "."
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {
        stats.addMultiplier(StatType.HEALTH, 20, 0);
    }
}
