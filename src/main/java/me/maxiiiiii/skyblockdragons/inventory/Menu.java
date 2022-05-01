package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Menu implements InventoryHolder {
    public static final ItemStack GLASS = Functions.createItem(Material.STAINED_GLASS_PANE, 1, 15, ChatColor.RESET + "");
    
    protected Inventory inventory;

    protected final PlayerSD player;

    protected final InventoryGlassType inventoryGlassType;

    protected Menu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, boolean update) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
        this.inventoryGlassType = inventoryGlassType;

        if (inventoryGlassType == InventoryGlassType.ALL) {
            Functions.putGlasses(inventory);
        } else if (inventoryGlassType == InventoryGlassType.SURROUND) {
            for (int i = 0; i < 9; i++) {
                this.setItem(i, GLASS);
            }

            for (int i = rows * 9 - 9; i < rows * 9; i++) {
                this.setItem(i, GLASS);
            }

            for (int i = 1; i < rows; i++) {
                this.setItem(i * 9, GLASS);
            }

            for (int i = 1; i < rows; i++) {
                this.setItem(i * 9 + 8, GLASS);
            }
        }

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", "", ChatColor.YELLOW + "Click to close!");
        NBTItem nbtClose = new NBTItem(close, true);
        nbtClose.setBoolean("CloseButton", true);
        this.setItem(this.getRows() * 9 - 5, close);

        ItemStack goBack = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", "", ChatColor.YELLOW + "Click to close!");
        NBTItem nbtGoBack = new NBTItem(goBack, true);
        nbtGoBack.setBoolean("GoBackButton", true);
        this.setItem(this.getRows() * 9 - 6, goBack);

        if (update) {
            Functions.While(() -> player.getOpenInventory().getTopInventory().getHolder().getClass() == this.getClass(), 1L, i -> this.update());
        } else
            this.update();
    }

    public void setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
    }

    public ItemStack getItem(int slot) {
        return this.inventory.getItem(slot);
    }

    public abstract void update();

    public void open() {
        player.openInventory(this.inventory);
    }

    public int getRows() {
        return this.inventory.getSize() / 9;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public abstract void onInventoryClick(InventoryClickEvent e);

    protected String getTitle() {
        return this.inventory.getTitle();
    }

    protected ItemStack createItem(Material material, int data, String name, String nbt, List<String> lores) {
        ItemStack item = new ItemStack(material, 1, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item, true);
        nbtItem.setBoolean(nbt, true);

        return item;
    }

    protected ItemStack createItem(Material material, int data, String name, String nbt, String... lores) {
        return createItem(material, data, name, nbt, Arrays.asList(lores));
    }

    protected ItemStack createItem(Material material, String name, String nbt, String... lores) {
        return createItem(material, 0, name, nbt, lores);
    }

    protected static boolean isItem(ItemStack item, String nbt) {
        NBTItem nbtItem = new NBTItem(item);
        return nbtItem.hasKey(nbt);
    }
}
