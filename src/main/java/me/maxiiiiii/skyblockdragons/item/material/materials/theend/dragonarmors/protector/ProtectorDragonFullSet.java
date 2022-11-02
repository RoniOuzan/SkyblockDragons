package me.maxiiiiii.skyblockdragons.item.material.materials.theend.dragonarmors.protector;

import me.maxiiiiii.skyblockdragons.events.events.update.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class ProtectorDragonFullSet extends ItemFullSetBonus {
    public ProtectorDragonFullSet() {
        super("Protector Blood",
                "Increases " + StatType.DEFENSE.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "30% " + ChatColor.GRAY + "while you are above " + ChatColor.GREEN + "50% " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY + "."
        );
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer().getItems().getFullSet() instanceof ProtectorDragonFullSet && e.getPlayer().getHealth() >= e.getStats().getHealth().get() / 2) {
            e.getStats().addMultiplier(StatType.DEFENSE, 30, 0);
        }
    }
}
