package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegister {
    public static final HashMap<String, Recipe> recipes = new HashMap<>();
    public static Map<String, Recipe> vanillaRecipes = new HashMap<>();

    public static ItemStack[] getItems(ItemStack leftTop, ItemStack middleTop, ItemStack rightTop, ItemStack leftMiddle, ItemStack middle, ItemStack rightMiddle, ItemStack leftBottom, ItemStack middleBottom, ItemStack rightBottom) {
        return new ItemStack[]{leftTop, middleTop, rightTop, leftMiddle, middle, rightMiddle, leftBottom, middleBottom, rightBottom};
    }

    public static void registerRecipe(String name, Recipe recipe) {
        recipes.put(name, recipe);
    }

    public static void registerEnchantedRecipe(ItemMaterial result, ItemMaterial base) {
        recipes.put(result.name(), new Recipe(new Item(result), getItems(
                new Item(base, 64),
                new Item(base, 64),
                new Item(base, 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));
    }

    public static void registerEnchantedRecipe(String result, String base){
        registerEnchantedRecipe(Items.get(result), Items.get(base));
    }
}
