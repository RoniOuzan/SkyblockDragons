package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.material.NormalMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.getItemMaterial;

public class ClickCanceller implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            ItemStack item = e.getItem();
            NormalMaterial material;
            try {
                material = (NormalMaterial) getItemMaterial(item);
                if (material.isEnchanted()) e.setCancelled(true);
            } catch (ClassCastException ignored) {}
        } catch (NullPointerException ignored) {}
    }
}
