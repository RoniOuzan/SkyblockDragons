package me.maxiiiiii.skyblockdragons.player.wardrobe;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class WardrobeListeners implements Listener {
    @EventHandler
    public void onPlayerArmorChange(InventoryClickEvent e) {
        if (!Functions.isNotAir(e.getCurrentItem())) return;

        if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
            if (player.getWardrobe().getEquippedSlot() > 0) {
                Functions.Wait(1L, () -> {
                    for (int i = 36; i < 40; i++) {
                        player.getWardrobe().getSlot(player.getWardrobe().getEquippedSlot() - 1).setPeace(e.getClickedInventory().getItem(i), 39 - i);
                    }
                });
            }
        }
    }
}
