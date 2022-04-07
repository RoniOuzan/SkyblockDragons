package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class UpdateStatsListeners implements Listener {
    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (Functions.cooldown(player, player.updateStatsCooldown, 50, false)) return;

        Functions.Wait(1L, () -> player.applyStats(false));
    }

    @EventHandler
    public void onArmorEquip(InventoryClickEvent e) {
        if (e.getSlotType() != InventoryType.SlotType.ARMOR) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());

        if (Functions.cooldown(player, player.updateStatsCooldown, 50, false)) return;

        Functions.Wait(1L, () -> player.applyStats(false));
    }
}
