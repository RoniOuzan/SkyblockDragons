package me.maxiiiiii.skyblockdragons.inventory;

import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class UpdatingPageMenu extends PageMenu {
    private final List<Supplier<ItemStack>> items;

    protected UpdatingPageMenu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, List<Supplier<ItemStack>> items, boolean update) {
        super(player, title, rows, inventoryGlassType, items.stream().map(Supplier::get).collect(Collectors.toList()), update);
        this.items = items;
    }

    @Override
    public void update() {
        int maxItems = this.getMaxItemsInPage();
        int adder = maxItems * (page - 1);
        for (int i = 0; i < maxItems; i++) {
            if (i + adder >= items.size()) {
                this.setItem(Functions.intToSlot(i), inventoryGlassType == InventoryGlassType.ALL ? getGLASS() : new ItemStack(Material.AIR));
            } else {
                this.setItem(Functions.intToSlot(i), changeItem(items.get(i + adder).get()));
            }
        }
    }

    public void nextPage() {
        int maxItems = this.getMaxItemsInPage();
        if (this.page > (items.size() / maxItems)) {
            player.sendMessage(ChatColor.RED + "You can't go any further!");
            return;
        }
        this.page++;
        this.update();
    }

    public void lastPage() {
        this.page = (int) Math.ceil(this.items.size() / 28d);
        this.update();
    }
}
