package me.maxiiiiii.skyblockdragons.item.craftingtable.menus;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.abilities.PowerOrb;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.material.*;
import me.maxiiiiii.skyblockdragons.player.pet.Pet;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipesMenu {
    private static final ArrayList<String> lores = new ArrayList<>(Arrays.asList(ChatColor.GRAY + "View all of the REPLACE Recipes.", "", ChatColor.YELLOW + "Click to view!"));

    public static void openRecipes(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, "Recipe Book");

        Functions.putGlasses(inventory);

//        ItemStack book = Functions.createItem(Material.BOOK, ChatColor.GREEN + "Recipe Book", ChatColor.GRAY + "Through your adventure, you will", ChatColor.GRAY + "unlock recipes for all kinds of", ChatColor.GRAY + "special items! You can view how", ChatColor.GRAY + "to craft these items here.");
//        inventory.setItem(4, book);

        ItemStack weapons = Functions.createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Weapons Recipes", getLores("Weapons"));
        inventory.setItem(10, weapons);

        ItemStack mining = Functions.createItem(Material.STONE_PICKAXE, ChatColor.GREEN + "Mining Recipes", getLores("Mining"));
        inventory.setItem(11, mining);

        ItemStack farming = Functions.createItem(Material.GOLD_HOE, ChatColor.GREEN + "Farming Recipes", getLores("Farming"));
        inventory.setItem(12, farming);

        ItemStack fishing = Functions.createItem(Material.FISHING_ROD, ChatColor.GREEN + "Fishing Recipes", getLores("Fishing"));
        inventory.setItem(13, fishing);

        ItemStack armor = Functions.createItem(Material.IRON_CHESTPLATE, ChatColor.GREEN + "Armor Recipes", getLores("Armor"));
        inventory.setItem(19, armor);

        ItemStack accessories = Functions.createItem(Material.SKULL_ITEM, 1, 3, ChatColor.GREEN + "Accessories Recipes", getLores("Accessories"));
        Functions.applySkull(accessories, "c3ffd9cc-db06-4eea-ab09-571aa5454092", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0=");
        inventory.setItem(20, accessories);

        ItemStack pets = Functions.createItem(Material.BONE, ChatColor.GREEN + "Pets Recipes", getLores("Pets"));
        inventory.setItem(21, pets);

        ItemStack enchantedItems = Functions.createItem(Material.DIAMOND, ChatColor.GREEN + "Enchanted Items Recipes", getLores("Enchanted Items"));
        enchantedItems.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        ItemMeta enchantedItemsMeta = enchantedItems.getItemMeta();
        enchantedItemsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchantedItems.setItemMeta(enchantedItemsMeta);
        inventory.setItem(22, enchantedItems);

        ItemStack enchants = Functions.createItem(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchants Recipes", getLores("Enchants"));
        inventory.setItem(28, enchants);

        ItemStack powerOrbs = Functions.createItem(Material.SKULL_ITEM, 1, 3, ChatColor.GREEN + "Power Orbs Recipes", getLores("Power Orbs"));
        Functions.applySkull(powerOrbs, "05624a23-a2f1-46b9-9e26-e463855f05c1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0=");
        inventory.setItem(29, powerOrbs);

        ItemStack wands = Functions.createItem(Material.STICK, ChatColor.GREEN + "Wands Recipes", getLores("Wands"));
        inventory.setItem(30, wands);

        ItemStack tools = Functions.createItem(Material.GRASS, ChatColor.GREEN + "Vanilla Recipes", getLores("Vanilla"));
        inventory.setItem(31, tools);

        ItemStack allRecipes = Functions.createItem(Material.BOOK, ChatColor.GREEN + "All Recipes", getLores("Every "));
        inventory.setItem(24, allRecipes);

        ItemStack goBack = Functions.createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }

    private static ArrayList<String> getLores(String replacer) {
        ArrayList<String> list = lores;
        for (int i = 0; i < list.size(); i++) {
            if (replacer.equalsIgnoreCase("every"))
                list.set(i, lores.get(i).replace("REPLACE Recipes", replacer + " Every"));
            else
                list.set(i, lores.get(i).replace("REPLACE", replacer));
        }
        return list;
    }

    public enum Type {
        WEAPONS, MINING, FARMING, FISHING, ARMOR, ACCESSORIES, PETS, ENCHANTED_ITEMS, ENCHANTS, POWER_ORBS, WANDS, VANILLA, ALL;

        @Override
        public String toString() {
            return Functions.setTitleCase(this.name().replace("_", ""));
        }
    }

    public static void openRecipesType(Player player, Type type, int page) {
        page = Math.max(page, 0);
        Inventory inventory = Bukkit.createInventory(player, 54, type.toString() + " Recipe Book - Page " + page);

        Functions.putGlasses(inventory);

        ArrayList<String> keys = new ArrayList<>();

        int a = 0;
        for (String key : Recipe.recipes.keySet()) {
            if (Recipe.recipes.get(key).getItem() instanceof Item) {
                Item item = (Item) Recipe.recipes.get(key).getItem();
                if (type == Type.WEAPONS && !(item.getMaterial() instanceof WeaponMaterial))
                    continue;
                else if (type == Type.MINING && item.getMaterial().getType() != ItemType.PICKAXE)
                    continue;
                else if (type == Type.FARMING && item.getMaterial().getType() != ItemType.HOE)
                    continue;
                else if (type == Type.FISHING && item.getMaterial().getType() != ItemType.ROD)
                    continue;
                else if (type == Type.ARMOR && !(item.getMaterial() instanceof ArmorMaterial))
                    continue;
                else if (type == Type.ACCESSORIES && !(item.getMaterial() instanceof AccessoryMaterial))
                    continue;
                else if (type == Type.ENCHANTED_ITEMS && !(item.getMaterial() instanceof NormalMaterial))
                    continue;
                else if (type == Type.ENCHANTS && !(item.getMaterial() instanceof BookMaterial))
                    continue;
                else if (type == Type.POWER_ORBS && !(item.getMaterial() instanceof PowerOrbMaterial))
                    continue;
                else if (type == Type.WANDS && item.getMaterial().getType() != ItemType.WAND)
                    continue;
                else if (type == Type.VANILLA && !(Items.vanillaMaterials.containsKey(item.getMaterial().name())))
                    continue;
                else if (type == Type.PETS)
                    continue;
            }
            keys.add(key);
            a++;
        }

        String[] strings = keys.toArray(new String[0]);
        Arrays.sort(strings);

        Recipe[] recipes = new Recipe[a];
        a = 0;
        for (String string : strings) {
            recipes[a] = Recipe.recipes.get(string);
            a++;
        }

        int length = 28 * (page - 1);
        for (int i = 0; i < 28; i++) {
            int slot = Functions.intToSlot(i);
            if (i + length < recipes.length) {
                inventory.setItem(slot, recipes[i + length].getItem());
            } else {
                inventory.setItem(slot, new ItemStack(Material.AIR));
            }
        }

        ItemStack nextPage = new ItemStack(Material.ARROW);
        Functions.setName(nextPage, ChatColor.GREEN + "Next Page");
        Functions.setLore(nextPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go next page!")));
        inventory.setItem(53, nextPage);

        ItemStack previousPage = new ItemStack(Material.ARROW);
        Functions.setName(previousPage, ChatColor.GREEN + "Previous Page");
        Functions.setLore(previousPage, new ArrayList<>(Arrays.asList("", ChatColor.YELLOW + "Click to go previous page!")));
        inventory.setItem(45, previousPage);

        ItemStack goBack = Functions.createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        ItemStack close = Functions.createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.YELLOW + "Click to close!");
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }
}
