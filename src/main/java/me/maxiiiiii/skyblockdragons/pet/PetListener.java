package me.maxiiiiii.skyblockdragons.pet;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.stat.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.Functions.getId;

public class PetListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).contains("_PET")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        PlayerData playerData = SkyblockDragons.players.get(player.getUniqueId());

        if (playerData.getActivePet() != null && playerData.getActivePet().getArmorStand() != null)
            playerData.getActivePet().getArmorStand().remove();
        else {
            Pet pet = Pet.getPet(item);

            playerData.setActivePet(pet);
        }
    }
}
