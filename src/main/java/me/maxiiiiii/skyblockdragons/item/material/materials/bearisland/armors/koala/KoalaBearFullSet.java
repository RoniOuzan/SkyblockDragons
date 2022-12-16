package me.maxiiiiii.skyblockdragons.item.material.materials.bearisland.armors.koala;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class KoalaBearFullSet  extends ItemFullSetBonus {
    public KoalaBearFullSet() {
        super("Koala Bear",
                "When " + ChatColor.RED + StatTypes.FEROCITY.getIcon() + " Ferociting " + ChatColor.GRAY + "regen your mama by " + ChatColor.GREEN + "1%" + "."
        );
    }
    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof KoalaBearFullSet) {
            e.getStats().addMultiplier(StatTypes.MANA, 0, 1);
        }
    }
}