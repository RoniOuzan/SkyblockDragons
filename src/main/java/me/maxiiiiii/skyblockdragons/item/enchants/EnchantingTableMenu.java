package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.anvil.Anvil;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class EnchantingTableMenu extends Menu {
    private int page;
    private ItemStack item;

    public EnchantingTableMenu(PlayerSD player, ItemStack item) {
        super(player, "Enchanting Table", 6, InventoryGlassType.ALL, false);
        this.item = item;
        this.setItem(19, item);
    }

    public EnchantingTableMenu(PlayerSD player) {
        this(player, null);
    }

    @Override
    protected void reset() {
        super.reset();
        this.page = 1;
        this.setItem(19, new ItemStack(Material.AIR));
        this.setItem(28, createItem(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Enchant Item", "ENCHANT_ITEM", ChatColor.GRAY + "Add an Item to the slot above to", ChatColor.GRAY + "view enchantment options!"));
    }

    @Override
    public void update() {
        this.item = this.getItem(19);

        if (Functions.isNotAir(this.getItem(19))) {
            List<EnchantType> enchants = new ArrayList<>(EnchantType.enchants.values());

            for (int i = (this.page - 1) * 15; i < enchants.size() && i < this.page * 15; i++) {
                EnchantType enchant = enchants.get(i);

                List<String> lores = Functions.loreBuilder(enchant.getDescription(1));
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to view!");
                this.setItem(convertIndexToSlot(12, 5, i), createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + Functions.setTitleCase(enchant.getRealName()), "ENCHANT_" + enchant.name(), lores));
            }

            if (enchants.size() > 15) {
                if (this.page == 1) {
                    this.setItem(35, createItem(Material.ARROW, ChatColor.GREEN + "Next Page", "NEXT_PAGE", ChatColor.YELLOW + "Click to move page!"));
                } else {
                    this.setItem(26, createItem(Material.ARROW, ChatColor.GREEN + "Previous Page", "PREVIOUS_PAGE", ChatColor.YELLOW + "Click to move page!"));
                }
            }
        } else {
            this.reset();
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() == 19 | e.getClickedInventory() instanceof PlayerInventory) e.setCancelled(false);

        if (!Functions.isNotAir(e.getCurrentItem())) return;

        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.contains("ENCHANT_")) {
            EnchantType enchant = EnchantType.valueOf(nbt.replace("ENCHANT_", ""));
            new EnchantViewer(player, enchant, new Item(player, this.item));
        }
        this.update();
    }

    private static class EnchantViewer extends Menu {
        private final Item item;
        private final EnchantType enchant;

        protected EnchantViewer(PlayerSD player, EnchantType enchant, Item item) {
            super(player,
                    "Enchanting Table - " + enchant.getRealName(),
                    6,
                    InventoryGlassType.ALL,
                    false
            );
            this.item = item;
            this.enchant = enchant;
        }

        @Override
        public void update() {
            this.setItem(13, item);

            List<ItemStack> books = new ArrayList<>();
            for (int i = 1; i <= enchant.getMaxLevel(); i++) {
                books.add(addNBT(new Item(player, Items.get("ENCHANTED_BOOK"), new EnchantModifier(new Enchant(this.enchant, i))), "LEVEL_" + i));
            }

            this.putItemsOnCenter(4, books);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            String nbt = this.getNBT(e.getCurrentItem());
            if (nbt.contains("LEVEL_")) {
                new EnchantingTableMenu(player, new Anvil(this.item, e.getCurrentItem()).combine(player));
            }
        }
    }
}
