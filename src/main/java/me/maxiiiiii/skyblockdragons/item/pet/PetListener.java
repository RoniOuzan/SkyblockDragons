package me.maxiiiiii.skyblockdragons.item.pet;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.getId;

public class PetListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).contains("_PET")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        PlayerSD player = SkyblockDragons.players.get(e.getPlayer().getUniqueId());

        if (item.getAmount() > 1) {
            player.sendMessage(ChatColor.RED + "You have to hold only 1 pet!");
            return;
        }

        if (player.getPlayerPet().getPets().size() >= 112) {
            player.sendMessage(ChatColor.RED + "You have reached the maximum amount of pets in your menu!");
            return;
        }

        player.getPlayerPet().getPets().add(new Item(player, item));
        player.getEquipment().setItemInMainHand(null);
        player.sendMessage(ChatColor.GREEN + "You have added your " + item.getItemMeta().getDisplayName() + ChatColor.GREEN + " to your pet menu.");
    }
}
