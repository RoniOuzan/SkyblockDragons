package me.maxiiiiii.skyblockdragons.item.craftingtable;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.Items;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftShapedRecipe;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;

import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public class Recipe {
    public static final HashMap<String, Recipe> recipes = new HashMap<>();

    private final ItemStack item;
    private final ItemStack[] items;
    private final int slotToUpgrade;
    private final boolean viewable;
    private final boolean shapeless;

    Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade, boolean viewable, boolean shapeless) {
        this.item = item;
        this.items = items;
        this.slotToUpgrade = slotToUpgrade;
        this.viewable = viewable;
        this.shapeless = shapeless;
    }

    Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade, boolean viewable) {
        this(item, items, slotToUpgrade, viewable, false);
    }

    Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade) {
        this(item, items, slotToUpgrade, true);
    }

    public void view(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, setTitleCase(this.name()) + " Recipe");

        ItemStack glass = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(49, close);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        ItemStack craftingTable = createItem(Material.WORKBENCH, ChatColor.GREEN + "Crafting Table", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Craft this recipe by using a", ChatColor.GRAY + "crafting table.")));
        inventory.setItem(23, craftingTable);

        ItemStack item = this.item;
        inventory.setItem(25, item);

        for (int i = 0; i < 9; i++) {
            try {
                inventory.setItem(numToSlot(i), this.items[i]);
            } catch (NullPointerException ex) {
                inventory.setItem(numToSlot(i), new ItemStack(Material.AIR));
            }
        }

        player.openInventory(inventory);
    }

    private static ItemStack[] getItems(ItemStack item1, ItemStack item2, ItemStack item3, ItemStack item4, ItemStack item5, ItemStack item6, ItemStack item7, ItemStack item8, ItemStack item9) {
        return new ItemStack[]{item1, item2, item3, item4, item5, item6, item7, item8, item9};
    }

    private static ItemStack[] getItems(List<ItemMaterial> items) {
        ItemStack[] output = new Item[9];
        for (int i = 0; i < 9; i++) {
            if (i >= items.size() || items.get(i) == Items.NULL) {
                output[i] = null;
            } else {
                output[i] = new Item(items.get(0));
            }
        }
        return output;
    }

    public static Recipe get(String name) {
        return Recipe.recipes.get(name);
    }

    public static void registerRecipes() {
        recipes.put("ASPECT_OF_THE_END", new Recipe(new Item(ItemMaterial.get("ASPECT_OF_THE_END")), getItems(
                null,
                new Item(ItemMaterial.get("ENCHANTED_EYE_OF_ENDER"), 16),
                null,
                null,
                new Item(ItemMaterial.get("ENCHANTED_EYE_OF_ENDER"), 16),
                null,
                null,
                new Item(ItemMaterial.get("ENCHANTED_DIAMOND")),
                null
        ), 0));

        recipes.put("ASPECT_OF_THE_VOID", new Recipe(new Item(ItemMaterial.get("ASPECT_OF_THE_VOID")), getItems(
                null,
                new Item(ItemMaterial.get("NULL_OVOID"), 8),
                null,
                new Item(ItemMaterial.get("NULL_OVOID"), 8),
                new Item(ItemMaterial.get("ASPECT_OF_THE_END")),
                new Item(ItemMaterial.get("NULL_OVOID"), 8),
                null,
                new Item(ItemMaterial.get("NULL_OVOID"), 8),
                null
        ), 4));

        recipes.put("ENCHANTED_DIAMOND", new Recipe(new Item(ItemMaterial.get("ENCHANTED_DIAMOND")), getItems(
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                null,
                null,
                null,
                null
        ), 0, true, true));

        for (Material material : Material.values()) {
            for (org.bukkit.inventory.Recipe recipe : Bukkit.getRecipesFor(new ItemStack(material))) {
                if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe less = (ShapelessRecipe) recipe;
                    ItemStack[] items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
                    for (int i = 0; i < less.getIngredientList().size(); i++) {
                        items[i] = new Item(Items.get(less.getIngredientList().get(i).getType().name()));
                    }
                    recipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name())), items, 0));
                } else if (recipe instanceof ShapedRecipe) {
                    ShapedRecipe shaped = (ShapedRecipe) recipe;

                    ItemStack[] items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
                    int i = 0;
                    for (String row : shaped.getShape()) {
                        if (row.length() == 3) {
                            for (String slot : row.split("")) {
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null) {
                                    items[i] = new Item(shaped.getIngredientMap().get(slot.charAt(0)));
                                }
                                i++;
                            }
                        } else if (row.length() == 2) {
                            for (String slot : row.split("")) {
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null)
                                    items[i] = new Item(shaped.getIngredientMap().get(slot.charAt(0)));
                                i += 2;
                            }
                            i--;
                        } else {
                            if (shaped.getIngredientMap().get(row.charAt(0)) != null)
                                items[i + 1] = new Item(shaped.getIngredientMap().get(row.charAt(0)));
                            i += 3;
                        }
                    }
                    recipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name())), items, 0));
                }
            }
        }
    }

    public String name() {
        for (String key : Recipe.recipes.keySet()) {
            if (Recipe.recipes.get(key) == this) {
                return key;
            }
        }
        return "";
    }
}
