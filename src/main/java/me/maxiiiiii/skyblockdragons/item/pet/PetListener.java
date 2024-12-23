package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PetListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!(Items.get(item) instanceof PetMaterial)) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

        if (item.getAmount() > 1) {
            player.sendMessage(ChatColor.RED + "You have to hold only 1 pet!");
            return;
        }

        if (player.getPlayerPet().getPets().size() >= 112) {
            player.sendMessage(ChatColor.RED + "You have reached the maximum amount of pets in your menu!");
            return;
        }

        e.setCancelled(true);
        player.getPlayerPet().addPet(new Item(player, item));
        player.getEquipment().setItemInMainHand(null);
        player.sendMessage(ChatColor.GREEN + "You have added your " + item.getItemMeta().getDisplayName() + ChatColor.GREEN + " to your pet menu.");
        player.getPlayerPet().updateActivePet();
    }
}
