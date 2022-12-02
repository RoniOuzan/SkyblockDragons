package me.maxiiiiii.skyblockdragons.inventory;

import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import net.minecraft.server.v1_12_R1.ChatMessage;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
public abstract class Menu implements InventoryHolder {
    public final ItemStack GLASS = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "", "GLASS");
    public final ItemStack CLOSE = createItem(Material.BARRIER, ChatColor.RED + "Close", "CLOSE", "", ChatColor.YELLOW + "Click to close!");
    public final ItemStack GO_BACK = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", "GO_BACK", "", ChatColor.YELLOW + "Click to go back!");

    protected Inventory inventory;
    protected String title;

    protected final PlayerSD player;

    protected final InventoryGlassType inventoryGlassType;
    protected final boolean utilButtons;

    protected Menu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, boolean autoUpdate, boolean utilButtons) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
        this.title = title;
        this.inventoryGlassType = inventoryGlassType;
        this.utilButtons = utilButtons;

        this.reset();

        Functions.Wait(1, () -> {
            if (autoUpdate) {
                Functions.While(() -> player.getOpenInventory().getTopInventory().getHolder() == this, 1L, i -> this.update());
            } else
                this.update();
            this.open(true);
        });
    }

    protected Menu(PlayerSD player, String title, int rows, InventoryGlassType inventoryGlassType, boolean autoUpdate) {
        this(player, title, rows, inventoryGlassType, autoUpdate, true);
    }
    public void setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
    }

    public ItemStack getItem(int slot) {
        return this.inventory.getItem(slot);
    }

    public abstract void update();

    protected void reset() {
        if (inventoryGlassType == InventoryGlassType.ALL) {
            Functions.putGlasses(inventory);
        } else if (inventoryGlassType == InventoryGlassType.SURROUND) {
            for (int i = 0; i < 9; i++) {
                this.setItem(i, GLASS);
            }

            int rows = getRows();
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
            if (player.getMenuHistory().size() > 0)
                this.setItem(this.getRows() * 9 - 6, GO_BACK);
        }
    }

    public void onGoBack() {
        if (player.getMenuHistory().size() > 0) {
            player.getMenuHistory().get(player.getMenuHistory().size() - 1).open();
        }
    }

    public void open() {
        this.open(false);
    }

    public void open(boolean addToHistory) {
        player.openInventory(this.inventory);
        List<Menu> menuHistory = player.getMenuHistory();
        if (addToHistory) {
            try {
                Menu previousMenu = menuHistory.get(menuHistory.size() - 1);
                if (!this.getClass().getName().equals(previousMenu.getClass().getName())) {
                    menuHistory.add(this);
                }
            } catch (Exception e) {
                menuHistory.add(this);
            }
        }
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

    protected void setTitle(String title) {
        EntityPlayer ep = ((CraftPlayer) player.getPlayer()).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(ep.activeContainer.windowId, "minecraft:chest", new ChatMessage(title), this.getRows() * 9);
        ep.playerConnection.sendPacket(packet);
        ep.updateInventory(ep.activeContainer);
        this.title = title;
    }

    protected String getTitle() {
        return this.title;
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

    protected static ItemStack createItem(ItemStack item, String name, String nbt, String... lores) {
        return createItem(item, name, nbt, Arrays.asList(lores));
    }

    protected static ItemStack createItem(ItemStack item, String name, int amount, String nbt, String... lores) {
        ItemStack itemStack = createItem(item, name, nbt, Arrays.asList(lores));
        itemStack.setAmount(amount);
        return itemStack;
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

    protected static ItemStack addLine(ItemStack itemStack, String... lores) {
        return addLine(itemStack, Arrays.asList(lores));
    }

    protected static ItemStack addLine(ItemStack itemStack, List<String> lores) {
        ItemStack item = itemStack.clone();
        ItemMeta meta = item.getItemMeta();
        List<String> newLores = meta.getLore();
        if (newLores == null) newLores = new ArrayList<>();
        newLores.addAll(lores);
        meta.setLore(newLores);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addNBT(ItemStack itemStack, String nbt) {
        ItemStack item = itemStack.clone();
        NBTItem nbtItem = new NBTItem(item, true);
        nbtItem.setString("GuiButton", nbt);
        return item;
    }

    protected ItemStack getGlass(int color) {
        ItemStack item = this.GLASS;
        item.setDurability((short) color);
        return item;
    }

    public static void removeLastHistory(UUID uuid) {
        SkyblockDragons.getPlayer(uuid).getMenuHistory().remove(SkyblockDragons.getPlayer(uuid).getMenuHistory().size() - 1);
    }

    private static final int[][] itemsSlots = new int[][]{
            {},
            {4},
            {3, 5},
            {3, 4, 5},
            {2, 3, 5, 6},
            {2, 3, 4, 5, 6},
            {1, 2, 3, 5, 6, 7},
            {1, 2, 3, 4, 5, 6, 7},
            {0, 1, 2, 3, 5, 6, 7, 8},
            {0, 1, 2, 3, 4, 5, 6, 7, 8}
    };

    protected void putItemsOnCenter(int line, List<ItemStack> items) {
        this.putItemsOnCenter(line, items.toArray(new ItemStack[0]));
    }

    protected void putItemsOnCenter(int line, ItemStack... items) {
        for (int i = 0; i < items.length; i++) {
            this.setItem(((line - 1) * 9) + itemsSlots[items.length][i], items[i]);
        }
    }
}
