package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.wise;

import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;

public class WiseDragonFullSet extends ItemFullSetBonus {
    public WiseDragonFullSet() {
        super("Wise Blood",
                "Abilities have " + ChatColor.GREEN + "60% " + ChatColor.GRAY + "of the mana cost."
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {
        stats.getPlayer().getAbilityCostMultiplier().addBaseMultiplier(-40);
    }
}
