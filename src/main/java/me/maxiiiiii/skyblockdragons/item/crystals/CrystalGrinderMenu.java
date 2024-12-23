package me.maxiiiiii.skyblockdragons.item.crystals;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.CrystalModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class CrystalGrinderMenu extends Menu {
    private static final int ITEM_SLOT = 13;
    
    public CrystalGrinderMenu(PlayerSD player) {
        super(player, "Crystal Grinder", 6, InventoryGlassType.ALL, false);
    }
    
    @Override
    public void update() {
        ItemMaterial itemMaterial = Items.get(this.getItem(ITEM_SLOT));
        if (itemMaterial == Items.NULL) {
            this.setItem(ITEM_SLOT, new ItemStack(Material.AIR));

            for (int i = 27; i < 45; i++) {
                this.setItem(i, GLASS);
            }
        } else if (itemMaterial instanceof ItemStatsAble) {
            ItemStatsAble material = (ItemStatsAble) itemMaterial;
            Item item = new Item(player, this.getItem(ITEM_SLOT));

            Crystals crystals = item.getModifiers().getCrystals();
            List<ItemStack> items = new ArrayList<>();
            for (int i = 0; i < material.getMaxCrystals(); i++) {
                if (i >= crystals.size()) {
                    items.add(getEmptyCrystalSlot(i));
                } else {
                    items.add(addNBT(crystals.getCrystalsAsItems(player).get(i), "FILLED_CRYSTAL_SLOT_" + i));
                }
            }

            this.putItemsOnCenter(4, items);
        }
    }

    private ItemStack getEmptyCrystalSlot(int slot) {
        return createItem(Material.STONE_BUTTON, ChatColor.DARK_GRAY + "None!", "EMPTY_CRYSTAL_SLOT_" + slot, ChatColor.GRAY + "This item doesn't have any crystal on", ChatColor.GRAY + "this slot. Click on a crystal in your", ChatColor.GRAY + "inventory to add to this item.");
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        ItemMaterial material = Items.get(e.getCurrentItem());

        if (this.getNBT(e.getCurrentItem()).contains("FILLED_CRYSTAL_SLOT") && e.getClickedInventory().getType() == InventoryType.CHEST) {
            int slot = Integer.parseInt(this.getNBT(e.getCurrentItem()).split("SLOT_")[1]);
            Item item = new Item(player, this.getItem(ITEM_SLOT));
            Crystals crystals = item.getModifiers().getCrystals();

            player.give(crystals.get(slot).getItem());
            crystals.remove(slot);

            this.setItem(ITEM_SLOT, new Item(player, item, new CrystalModifier(crystals)));
            this.update();
        } else if (Items.get(this.getItem(ITEM_SLOT)) instanceof ItemStatsAble && material instanceof CrystalMaterial && e.getClickedInventory().getType() == InventoryType.PLAYER) {
            Item item = new Item(player, this.getItem(ITEM_SLOT));

            if (item.getModifiers().getCrystals().size() >= ((ItemStatsAble) item.getMaterial()).getMaxCrystals()) {
                player.sendMessage(ChatColor.RED + "This item is full of crystals. you have to remove one to put this crystal!");
                return;
            }

            CrystalMaterial crystalMaterial = (CrystalMaterial) material;
            Crystal crystal = crystalMaterial.getCrystal();
            this.setItem(ITEM_SLOT, new Item(player, this.getItem(ITEM_SLOT), new CrystalModifier(item.getModifiers().getCrystals(), crystal)));
            e.setCurrentItem(new ItemStack(Material.AIR));
            this.update();
        } else if (e.getSlot() == ITEM_SLOT && material instanceof ItemStatsAble && e.getClickedInventory().getType() == InventoryType.CHEST) {
            player.give(this.getItem(ITEM_SLOT));
            this.setItem(ITEM_SLOT, new ItemStack(Material.AIR));
            this.update();
        } else if (material instanceof ItemStatsAble && e.getClickedInventory() instanceof PlayerInventory && e.getCurrentItem().getAmount() == 1) {
            if (Items.get(this.getItem(ITEM_SLOT)) instanceof ItemStatsAble) {
                player.give(this.getItem(ITEM_SLOT));
            }

            this.setItem(ITEM_SLOT, e.getCurrentItem());
            e.setCurrentItem(new ItemStack(Material.AIR));
            this.update();
        }
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent e) {
        player.give(this.getItem(ITEM_SLOT));
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new CrystalGrinderMenu(player);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}
