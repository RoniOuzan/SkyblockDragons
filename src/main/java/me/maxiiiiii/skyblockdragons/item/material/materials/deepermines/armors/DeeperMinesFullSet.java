package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;

public class DeeperMinesFullSet extends ItemFullSetBonus {
    private final double amount;

    public DeeperMinesFullSet(double amount) {
        super("Deep Miner",
                "Reduces the cooldown of drill abilities by " + ChatColor.GREEN + "" + amount + "%" + ChatColor.GRAY + "."
        );
        this.amount = amount;
    }

    @Override
    public void updateStats(PlayerStats stats) {
        // TODO
    }
}
