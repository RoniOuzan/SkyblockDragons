package me.maxiiiiii.skyblockdragons.events.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.craftingtable.menus.RecipesMenu;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
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
                ItemMaterial itemMaterial = getItemMaterial(item);
                if (itemMaterial instanceof NormalMaterial) {
                    material = (NormalMaterial) itemMaterial;
                    if (material.isEnchanted()) e.setCancelled(true);
                    if (material.isShowRecipe() && item.getItemMeta().hasDisplayName()){
//                        player.sendMessage("showing recipe for " + item.getItemMeta().getDisplayName() + " material: " + material);
                        new RecipesMenu.WithMenu(player, item);
                    }
                }
            } catch (ClassCastException ignored) {}
        } catch (NullPointerException ignored) {}
    }
}
