package me.maxiiiiii.skyblockdragons.world.warp;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FastTravelMenu extends Menu {
    public FastTravelMenu(PlayerSD player) {
        super(player,
                "Fast Travel",
                4,
                InventoryGlassType.ALL,
                false
        );
    }

    @Override
    public void update() {
        List<ItemStack> items = Arrays.stream(Warp.values()).filter(w -> w != Warp.UNWARPABLE).map(w -> createItem(w.getItem(), ChatColor.GREEN + w.getName(), w.name(), ChatColor.DARK_GRAY + "/warp " + w.name().toLowerCase(), "", ChatColor.YELLOW + "Click to warp!")).collect(Collectors.toList());

        this.putItemsOnCenter(2, items);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (Arrays.stream(Warp.values()).map(Enum::name).collect(Collectors.toList()).contains(this.getNBT(e.getCurrentItem()))) {
            Warp warp = Warp.valueOf(this.getNBT(e.getCurrentItem()));
            player.closeInventory();
            warp.warp(player);
        }
    }
}
