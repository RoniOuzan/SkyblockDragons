package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.StatAdderType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class ProtectorDragonFullSet extends ItemFullSetBonus {
    public ProtectorDragonFullSet() {
        super("Protector Blood",
                "Increases " + StatTypes.DEFENSE + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "30% " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatTypes.HEALTH + ChatColor.GRAY + "."
        );
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void updateStats(UpdateStatsEvent e) {
        if (!(e.getPlayer().getItems().getFullSet() instanceof ProtectorDragonFullSet)) return;

        if (e.getPlayer().getHealth() >= e.getStats().getHealth().get() / 2) {
            e.getStats().addBaseMultiplier(StatTypes.DEFENSE, 30, StatAdderType.FULL_SET_BONUS, this);
        }
    }
}
