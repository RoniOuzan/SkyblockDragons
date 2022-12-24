package me.maxiiiiii.skyblockdragons.item.enchants;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.modifiers.EnchantModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private void setItem(ItemStack item) {
        this.item = item;
        this.setItem(19, item);
    }

    @Override
    public void update() {
        this.item = this.getItem(19);

        if (Functions.isNotAir(this.getItem(19))) {
            ItemMaterial material = Items.get(this.item);
            List<EnchantType> enchants = EnchantType.enchants.values().stream().filter(e -> e.isInEnchantingTable() && e.getTypes().contains(material.getType())).collect(Collectors.toList());

            for (int i = (this.page - 1) * 15; i < this.page * 15; i++) {
                if (i >= enchants.size()) {
                    this.setItem(convertIndexToSlot(12, 5, 15, i), GLASS);
                    continue;
                }

                EnchantType enchant = enchants.get(i);

                List<String> lores = Functions.loreBuilder(enchant.getDescription(1));
                lores.add("");
                lores.add(ChatColor.YELLOW + "Click to view!");
                this.setItem(convertIndexToSlot(12, 5, 15, i), createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + enchant.getName(), "ENCHANT_" + enchant.name(), lores));
            }

            if (enchants.size() > 15) {
                if (this.page == 1) {
                    this.setItem(26, GLASS);
                    this.setItem(35, createItem(Material.ARROW, ChatColor.GREEN + "Next Page", "NEXT_PAGE", ChatColor.YELLOW + "Click to move page!"));
                } else {
                    this.setItem(26, createItem(Material.ARROW, ChatColor.GREEN + "Previous Page", "PREVIOUS_PAGE", ChatColor.YELLOW + "Click to move page!"));
                    this.setItem(35, GLASS);
                }
            }
        } else {
            this.reset();
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Functions.Wait(1L, this::update);

        if (e.getSlot() == 19 || e.getClickedInventory() instanceof PlayerInventory) e.setCancelled(false);

        if (!Functions.isNotAir(e.getCurrentItem())) return;

        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.contains("ENCHANT_")) {
            EnchantType enchant = EnchantType.valueOf(nbt.replace("ENCHANT_", ""));
            new EnchantViewer(enchant, new Item(player, this.item));
        } else if (nbt.equals("NEXT_PAGE")) {
            this.page = 2;
        } else if (nbt.equals("PREVIOUS_PAGE")) {
            this.page = 1;
        }
    }

    private class EnchantViewer extends Menu {
        private final Item item;
        private final EnchantType enchant;

        protected EnchantViewer(EnchantType enchant, Item item) {
            super(EnchantingTableMenu.this.player,
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
                List<String> lores = Functions.loreBuilder(this.enchant.getDescription(i));
                lores.add("");
                lores.add(ChatColor.GRAY + "Cost: " + ChatColor.DARK_AQUA + (i * 5) + " Exp");
                lores.add("");
                if (!this.enchant.getRequirements().hasRequirements(player))
                    lores.addAll(this.enchant.getRequirements().getRequirements().stream().map(Requirement::toString).collect(Collectors.toList()));
                lores.add(ChatColor.YELLOW + "Click to apply!");

                books.add(createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + this.enchant.getName() + " " + Functions.integerToRoman(i), "LEVEL_" + i, lores));
//                books.add(addNBT(new Item(player, Items.get("ENCHANTED_BOOK"), new EnchantModifier(new Enchant(this.enchant, i))), "LEVEL_" + i));
            }

            this.putItemsOnCenter(4, books);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            String nbt = this.getNBT(e.getCurrentItem());
            if (nbt.contains("LEVEL_")) {
                if (!this.enchant.getRequirements().hasRequirements(player)) {
                    player.sendMessage(ChatColor.RED + "You don't have the requirements to put this enchant!");
                    return;
                }

                short level = Short.parseShort(nbt.replace("LEVEL_", ""));
                int cost = level * 5;
                if (!player.ignoreRequirements()) {
                    if (player.getLevel() < cost) {
                        player.sendMessage(ChatColor.RED + "You don't have enough experience to apply this enchant!");
                        return;
                    }
                    player.setLevel(player.getLevel() - cost);
                }
                player.playSound(Sound.ENTITY_PLAYER_LEVELUP);

                Map<EnchantType, Short> enchants = this.item.getModifiers().getEnchants();

                List<EnchantType> remove = enchants.keySet().stream().filter(en -> this.enchant.getDistractions().contains(en.name())).collect(Collectors.toList());
                remove.forEach(enchants::remove);

                if (enchants.getOrDefault(this.enchant, (short) -1) == level) {
                    enchants.remove(this.enchant);
                } else {
                    enchants.put(this.enchant, level);
                }

                Item result = new Item(player, this.item, new EnchantModifier(enchants));
                EnchantingTableMenu.this.open();
                EnchantingTableMenu.this.setItem(result);
            }
        }
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new EnchantingTableMenu(player);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }
}
