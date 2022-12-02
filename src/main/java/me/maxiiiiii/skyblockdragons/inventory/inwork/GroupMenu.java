package me.maxiiiiii.skyblockdragons.inventory.inwork;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class GroupMenu extends Menu {
    protected final PlayerSD player;
    protected final List<Menu> menus;
    protected final List<ItemStack> categories;

    protected int currentSlot;
    protected Menu currentMenu;

    protected GroupMenu(PlayerSD player, String title, List<Menu> menus, List<ItemStack> categories) {
        super(player, title, menus.get(0).getRows(), menus.get(0).getInventoryGlassType(), false);
        this.player = player;

        this.menus = menus;
        this.categories = categories;

        this.currentMenu = menus.get(0);
        this.currentSlot = 0;

        if (this.getInventoryGlassType() == InventoryGlassType.SURROUND) {
            for (int i = 9; i < 18; i++) {
                this.setItem(i, GLASS);
            }
        }

        this.update();
    }

    @Override
    public void update() {
        if (this.menus.size() <= 9) {
            int slot = (int) (5 - (this.menus.size() / 2d));
            for (int i = 0; i < this.menus.size(); i++) {
                NBTItem nbt = new NBTItem(categories.get(i), true);
                nbt.setInteger("CategorySlot", i);
                if (this.menus.size() % 2 == 0 && i + slot <= 4)
                    this.setItem(i + slot - 1, categories.get(i));
                else
                    this.setItem(i + slot, categories.get(i));
            }
            for (int i = 0; i < 9; i++) {
                if (!Functions.isNotAir(this.inventory.getItem(i)))
                    this.setItem(i, GLASS);
            }
        }

        for (int i = 9; i < this.currentMenu.getRows() * 9; i++) {
            this.setItem(i, this.currentMenu.getInventory().getItem(i));
        }
    }

    public void setMenu(int index) {
        this.currentMenu = this.menus.get(index);
        this.currentSlot = index;

        this.inventory = Bukkit.createInventory(this, this.currentMenu.getRows() * 9, this.getTitle());

        this.update();

        this.open(true);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        currentMenu.onInventoryClick(e);
    }
}
