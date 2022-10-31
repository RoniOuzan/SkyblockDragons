package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class UpdateStatsListeners implements Listener {
    @EventHandler
    public void onSlotChange(PlayerItemHeldEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (Functions.cooldown(player, player.updateStatsCooldown, 100, false)) return;

        Functions.Wait(1L, () -> {
            ItemStack itemStack = player.getEquipment().getItemInMainHand();
            ItemMaterial itemMaterial = Functions.getItemMaterial(itemStack);
            if (itemMaterial != Items.NULL && !Functions.nbtHasKey(itemStack, "NOTSD")) {
                Item item = new Item(player, itemMaterial, itemStack);
                Functions.copyNBTStack(item, itemStack);
                if (!item.isSimilar(itemStack) && !Functions.getId(itemStack).contains("_PET") && !Functions.getId(item).equals("SKYBLOCK_MENU")) {
                    player.getEquipment().setItemInMainHand(item);
                }
                player.setSkyblockMenu();
            }
            player.applyStats(false);
        });
    }

    @EventHandler
    public void onArmorEquip(InventoryClickEvent e) {
        if (e.getSlotType() != InventoryType.SlotType.ARMOR) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());

        if (Functions.cooldown(player, player.updateStatsCooldown, 50, false)) return;

        Functions.Wait(1L, () -> player.applyStats(false));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getType() != InventoryType.CHEST) return;

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getPlayer());

        Functions.Wait(1L, () -> player.applyStats(false));
    }
}
