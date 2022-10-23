package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.Priority;
import org.bukkit.ChatColor;

public class ProtectorDragonFullSet extends ItemFullSetBonus {
    public ProtectorDragonFullSet() {
        super("Protector Blood",
                "Increases " + StatType.DEFENSE.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "30% " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + "."
        );
    }

    @Priority(level = 1)
    @Override
    public void updateStats(PlayerStats stats) {
        if (stats.getPlayer().getHealth() >= stats.getHealth().get() / 2) {
            stats.addMultiplier(StatType.DEFENSE, 30, 0);
        }
    }
}
