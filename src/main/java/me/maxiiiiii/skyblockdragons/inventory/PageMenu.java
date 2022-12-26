package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08
 * ---+---+---+---+---+---+---+---+------------
 *  09 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17
 * ---+---+---+---+---+---+---+---+------------
 *  18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 | 26
 * ---+---+---+---+---+---+---+---+------------
 *  27 | 28 | 29 | 30 | 31 | 32 | 33 | 34 | 35
 * ---+---+---+---+---+---+---+---+------------
 *  36 | 37 | 38 | 39 | 40 | 41 | 42 | 43 | 44
 * ---+---+---+---+---+---+---+---+------------
 *  45 | 46 | 47 | 48 | 49 | 50 | 51 | 52 | 53
 */
@Getter
public abstract class PageMenu extends Menu {
    protected List<ItemStack> items;
    protected int page;

    protected int itemsInPage;

    public PageMenu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, List<? extends ItemStack> items, boolean update) {
        super(player, title, rows, inventoryGlassType, update);
        this.items = items.stream().peek(i -> {
            if (this.getNBT(i).equals("")) {
                NBTItem nbt = new NBTItem(i, true);
                nbt.setString("GuiButton", "PAGE_ITEM");
            }
        }).collect(Collectors.toList());

        this.setItem(rows * 9 - 1, Functions.createItem(Material.ARROW, ChatColor.GREEN + "Next Page", "", ChatColor.AQUA + "Right-Click to go to the last page!", ChatColor.YELLOW + "Click to go to the next page!"));
        this.setItem(rows * 9 - 9, Functions.createItem(Material.ARROW, ChatColor.GREEN + "Previous Page", "", ChatColor.AQUA + "Right-Click to go to the first page!", ChatColor.YELLOW + "Click to go to the previous page!"));

        this.page = 1;

        this.itemsInPage = (this.getRows() - 2) * 7;
    }

    protected int getMaxItemsInPage() {
        return this.itemsInPage;
    }

    @Override
    public void update() {
        int maxItems = this.getMaxItemsInPage();
        int adder = maxItems * (page - 1);
        for (int i = 0; i < maxItems; i++) {
            List<ItemStack> items = this.items;
            if (i + adder >= items.size()) {
                this.setItem(Functions.intToSlot(i), inventoryGlassType == InventoryGlassType.ALL ? getGLASS() : new ItemStack(Material.AIR));
            } else {
                this.setItem(Functions.intToSlot(i), changeItem(items.get(i + adder)));
            }
        }
    }

    protected ItemStack changeItem(ItemStack item) {
        return item;
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

    public void previousPage() {
        if (this.page <= 1) {
            player.sendMessage(ChatColor.RED + "You can't go to this page!");
            return;
        }
        this.page--;
        this.update();
    }

    public void firstPage() {
        this.page = 1;
        this.open(true);
    }

    @Override
    public void open() {
        this.update();
        super.open(true);
    }
}
