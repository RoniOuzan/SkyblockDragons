package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class UpdateStatsListeners implements Listener {
    public final Cooldown<PlayerSD> updateStatsCooldown = new Cooldown<>();

    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (Functions.cooldown(player, updateStatsCooldown, 200, false)) return;

        player.updatePlayerInventory();
        player.applyStats(false);
    }

    @EventHandler
    public void onArmorEquip(InventoryClickEvent e) {
        if (e.getSlotType() != InventoryType.SlotType.ARMOR) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());

        if (Functions.cooldown(player, updateStatsCooldown, 200, false)) return;

        Functions.Wait(1L, () -> player.applyStats(false));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getType() != InventoryType.CHEST) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getPlayer());

        Functions.Wait(1L, () -> player.applyStats(false));
    }
}
