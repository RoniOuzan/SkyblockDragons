package me.maxiiiiii.skyblockdragons.events;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.material.types.NormalMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.getItemMaterial;

public class ClickCanceller implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        try {
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            ItemStack item = e.getItem();
            NormalMaterial material;
            try {
                material = (NormalMaterial) getItemMaterial(item);
                if (material.isEnchanted()) e.setCancelled(true);
                if (material.isShowRecipe()){
                    new RecipesMenu.WithMenu(player, item);
                }
            } catch (ClassCastException ignored) {}
        } catch (NullPointerException ignored) {}
    }
}
