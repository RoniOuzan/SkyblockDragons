package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.BlockColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@Getter
public abstract class Menu implements InventoryHolder {
    public static final Map<UUID, List<Menu>> menusHistory = new HashMap<>();

    public final ItemStack GLASS = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "", "GLASS");
    public final ItemStack CLOSE = createItem(Material.BARRIER, ChatColor.RED + "Close", "CLOSE", "", ChatColor.YELLOW + "Click to close!");
    public final ItemStack GO_BACK = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", "GO_BACK", "", ChatColor.YELLOW + "Click to go back!");

    protected Inventory inventory;

    protected final PlayerSD player;

    protected final InventoryGlassType inventoryGlassType;

    protected Menu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, boolean update, boolean utilButtons) {
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

        if (utilButtons) {
            this.setItem(this.getRows() * 9 - 5, CLOSE);

            this.setItem(this.getRows() * 9 - 6, GO_BACK);
        }

        Functions.Wait(1, () -> {
            if (update) {
                Functions.While(() -> player.getOpenInventory().getTopInventory().getHolder().getClass() == this.getClass(), 1L, i -> this.update());
            } else
                this.update();
            this.open();
        });
    }

    protected Menu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, boolean update) {
        this(player, title, rows, inventoryGlassType, update, true);
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
        List<Menu> menus = menusHistory.getOrDefault(this.player.getUniqueId(), new ArrayList<>());
        menus.add(this);
        menusHistory.put(this.player.getUniqueId(), menus);

        player.sendMessage(Menu.menusHistory.get(player.getUniqueId()).get(Menu.menusHistory.size() - 1).getTitle());
    }

    public int getRows() {
        return this.inventory.getSize() / 9;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void onInventoryOpen(InventoryOpenEvent e) {}

    public abstract void onInventoryClick(InventoryClickEvent e);

    public void onInventoryClose(InventoryCloseEvent e) {}

    public void onInventoryDrag(InventoryDragEvent e) {}

    protected String getTitle() {
        return this.inventory.getTitle();
    }

    protected static ItemStack createItem(ItemStack item, String name, String nbt, List<String> lores) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        if (!nbt.isEmpty()) {
            NBTItem nbtItem = new NBTItem(item, true);
            nbtItem.setString("GuiButton", nbt);
        }

        return item;
    }

    protected static ItemStack createItem(Material material, int data, String name, String nbt, List<String> lores) {
        ItemStack item = new ItemStack(material, 1, (short) data);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        if (!nbt.isEmpty()) {
            NBTItem nbtItem = new NBTItem(item, true);
            nbtItem.setString("GuiButton", nbt);
        }

        return item;
    }

    protected static ItemStack createItem(Material material, String name, String nbt, List<String> lores) {
        return createItem(material, 0, name, nbt, lores);
    }

    protected static ItemStack createItem(Material material, int data, String name, String nbt, String... lores) {
        return createItem(material, data, name, nbt, Arrays.asList(lores));
    }

    protected static ItemStack createItem(Material material, String name, String nbt, String... lores) {
        return createItem(material, 0, name, nbt, lores);
    }

    protected String getNBT(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey("GuiButton"))
            return nbtItem.getString("GuiButton");
        return "";
    }

    public static ItemStack addNBT(ItemStack item, String nbt) {
        NBTItem nbtItem = new NBTItem(item, true);
        nbtItem.setString("GuiButton", nbt);
        return item;
    }

    protected ItemStack getGlass(BlockColor color) {
        ItemStack item = this.GLASS;
        item.setDurability(color.getData());
        return item;
    }

    public static void removeLastHistory(UUID uuid) {
        List<Menu> history = Menu.menusHistory.get(uuid);

        if (history == null) return;

        history.remove(0);
        Menu.menusHistory.put(uuid, history);
    }
}
