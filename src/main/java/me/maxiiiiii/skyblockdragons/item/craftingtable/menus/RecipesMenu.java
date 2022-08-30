package me.maxiiiiii.skyblockdragons.item.craftingtable.menus;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.pet.Pet;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class RecipesMenu extends Menu {
    public RecipesMenu(PlayerSD player) {
        super(player, "Recipe Book", 6, InventoryGlassType.ALL, false);
    }

    @Override
    public void update() {
        Map<String, ItemStack> map = new HashMap<>();
        map.put("Weapons", new ItemStack(Material.STONE_SWORD));
        map.put("Mining", new ItemStack(Material.DIAMOND_PICKAXE));
        map.put("Farming", new ItemStack(Material.GOLD_HOE));
        map.put("Fishing", new ItemStack(Material.FISHING_ROD));
        map.put("Wands", new ItemStack(Material.STICK));
        map.put("Armor", new ItemStack(Material.IRON_CHESTPLATE));
        map.put("Accessories", Functions.applySkull("c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0="));
        map.put("Pets", new ItemStack(Material.BONE));
        map.put("Items", new ItemStack(Material.DIAMOND));

        int i = 0;
        for (String key : map.keySet()) {
            this.setItem(getSlot(i), createItem(map.get(key), ChatColor.GREEN + key, "CATEGORY_" + key.toUpperCase(), getLores(key)));
            i++;
        }

        this.setItem(25, createItem(Material.BOOK, ChatColor.GREEN + "All Recipes", "CATEGORY_ALL", getLores("Every ")));
    }

    private int getSlot(int num) {
        int num1 = 0;
        int num2 = 5;
        int adder = 10;

        while (true) {
            if (num >= num1 && num < num2) {
                return num + adder;
            }
            num1 += 5;
            num2 += 5;
            adder += 4;
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).contains("CATEGORY_")) {
            new RecipesMenu.TypeMenu(this.player, Type.valueOf(this.getNBT(e.getCurrentItem()).split("_")[1]));
        }
    }

    private static List<String> getLores(String replacer) {
        List<String> lores = new ArrayList<>(Arrays.asList(ChatColor.GRAY + "View all of the REPLACE Recipes.", "", ChatColor.YELLOW + "Click to view!"));
        for (int i = 0; i < lores.size(); i++) {
            if (replacer.equalsIgnoreCase("every"))
                lores.set(i, lores.get(i).replace("REPLACE Recipes", replacer + " Every"));
            else
                lores.set(i, lores.get(i).replace("REPLACE", replacer));
        }
        return lores;
    }

    public static class Command extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            new RecipesMenu(player);
        }

        @Override
        public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
            return tabs;
        }
    }

    public enum Type {
        WEAPONS(i -> i instanceof Item && ((Item) i).getMaterial().getType().isWeapon()),
        MINING(i -> i instanceof Item && (Items.get(i).getType().isPickaxe() || Items.get(i).getType().isArmor()) && ((Items.get(i) instanceof ItemStatsAble && (((ItemStatsAble) Items.get(i)).getStats().getMiningSpeed().amount > 0 || ((ItemStatsAble) Items.get(i)).getStats().getMiningFortune().amount > 0)))),
        FARMING(i -> i instanceof Item && ((Item) i).getMaterial().getType() == ItemType.HOE),
        FISHING(i -> i instanceof Item && ((Item) i).getMaterial().getType() == ItemType.ROD),
        WANDS(i -> i instanceof Item && ((Item) i).getMaterial().getType() == ItemType.WAND),
        ARMOR(i -> i instanceof Item && ((Item) i).getMaterial().getType().isArmor()),
        ACCESSORIES(i -> i instanceof Item && ((Item) i).getMaterial().getType() == ItemType.ACCESSORY),
        PETS(i -> i instanceof Pet),
        ITEMS(i -> i instanceof Item && ((Item) i).getMaterial().getType() == ItemType.ITEM),
        ALL(i -> true),
        ;

        private final Checker checker;

        Type(Checker checker) {
            this.checker = checker;
        }

        @FunctionalInterface
        public interface Checker {
            boolean check(ItemStack item);
        }

        public boolean isItem(ItemStack item) {
            return this.checker.check(item);
        }

        @Override
        public String toString() {
            return Functions.setTitleCase(this.name().replace("_", ""));
        }
    }

    public static class TypeMenu extends PageMenu {
        public TypeMenu(PlayerSD player, Type type) {
            super(player, type.toString() + " Recipe Book", 6, InventoryGlassType.SURROUND, getList(type), false);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                getRecipeForItem(e.getCurrentItem()).view((Player) e.getWhoClicked());
            }
        }

        public static List<ItemStack> getList(Type type) {
            return Recipe.abcSorted.stream().map(Recipe::getItem).filter(type::isItem).collect(Collectors.toList());
        }
    }

    public static class ForMenu extends PageMenu {
        public ForMenu(PlayerSD player, ItemStack item) {
            super(player, Functions.getNormalName(item) + " Recipe Book", 6, InventoryGlassType.SURROUND, getList(item), false);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                getRecipeForItem(e.getCurrentItem()).view((Player) e.getWhoClicked());
            }
        }

        public static List<ItemStack> getList(ItemStack item) {
            return Recipe.getRecipesFor(item).stream().map(Recipe::getItem).collect(Collectors.toList());
        }

        public static class Command extends CommandSD {
            @Override
            public void command(PlayerSD player, String[] args) {
                if (!Functions.isNotAir(player.getEquipment().getItemInMainHand())) {
                    player.sendMessage(ChatColor.RED + "You have to hold an item to use this command!");
                    return;
                }
                new ForMenu(player, player.getEquipment().getItemInMainHand());
            }

            @Override
            public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
                return tabs;
            }
        }
    }

    public static class WithMenu extends PageMenu {
        public WithMenu(PlayerSD player, ItemStack item) {
            super(player, Functions.getNormalName(item) + " Recipe Book", 6, InventoryGlassType.SURROUND, getList(item), false);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                getRecipeForItem(e.getCurrentItem()).view((Player) e.getWhoClicked());
            }
        }

        public static List<ItemStack> getList(ItemStack item) {
            return Recipe.getRecipesWith(item).stream().map(Recipe::getItem).collect(Collectors.toList());
        }

        public static class Command extends CommandSD {
            @Override
            public void command(PlayerSD player, String[] args) {
                if (!Functions.isNotAir(player.getEquipment().getItemInMainHand())) {
                    player.sendMessage(ChatColor.RED + "You have to hold an item to use this command!");
                    return;
                }
                new WithMenu(player, player.getEquipment().getItemInMainHand());
            }

            @Override
            public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
                return tabs;
            }
        }
    }

    public static Recipe getRecipeForItem(ItemStack item) {
        return Recipe.get(Functions.getItemMaterial(item).name());
    }
}
