package me.maxiiiiii.skyblockdragons.skill;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SkillListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getTitle().contains("Skill")) {
                e.setCancelled(true);
            }
        } catch (NullPointerException ignored) {}
    }
}
