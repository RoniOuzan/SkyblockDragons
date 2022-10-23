package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.young;

import me.maxiiiiii.skyblockdragons.util.objects.Priority;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;

public class YoungDragonFullSet extends ItemFullSetBonus {
    public YoungDragonFullSet() {
        super("Young blood",
                "Gain " + ChatColor.WHITE + "+70" + StatType.SPEED.getIconAndText() + " " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + "."
        );
    }

    @Override
    @Priority(level = 1)
    public void updateStats(PlayerStats stats) {
        if (stats.getPlayer().getHealth() >= stats.getHealth().get() / 2) {
            stats.getSpeed().add(70);
        }
    }
}
