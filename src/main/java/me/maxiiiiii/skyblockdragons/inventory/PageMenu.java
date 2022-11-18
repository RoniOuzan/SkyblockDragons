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
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Getter
public abstract class PageMenu extends Menu {
    protected Supplier<List<ItemStack>> items;
    protected int page;

    protected int itemsInPage;

    protected PageMenu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, Supplier<List<ItemStack>> items, boolean update) {
        super(player, title, rows, inventoryGlassType, update);
        this.items = () -> items.get().stream().peek(i -> {
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

    protected PageMenu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, List<ItemStack> items, boolean update) {
        this(player, title, rows, inventoryGlassType, () -> items, update);
    }

    protected int getMaxItemsInPage() {
        return this.itemsInPage;
    }

    @Override
    public void update() {
        int maxItems = this.getMaxItemsInPage();
        int adder = maxItems * (page - 1);
        for (int i = 0; i < maxItems; i++) {
            if (i + adder >= items.get().size())
                continue;
//                this.setItem(Functions.intToSlot(i), new ItemStack(Material.AIR));

            this.setItem(Functions.intToSlot(i), changeItem(items.get().get(i + adder)));
        }
    }

    protected ItemStack changeItem(ItemStack item) {
        return item;
    }

    public void nextPage() {
        int maxItems = this.getMaxItemsInPage();
        if (this.page > (items.get().size() / maxItems)) {
            player.sendMessage(ChatColor.RED + "You can't go any further!");
            return;
        }
        this.page++;
        this.update();
    }

    public void lastPage() {
        this.page = (int) Math.ceil(this.items.get().size() / 28d);
        this.update();
    }

    public void previousPage() {
        if (this.page <= 1) {
            player.sendMessage(ChatColor.RED + "You can't go to this page!");
            return;
        }
        this.page--;
        this.open();
    }

    public void firstPage() {
        this.page = 1;
        this.open();
    }

    @Override
    public void open() {
        this.update();
        super.open();
    }
}
