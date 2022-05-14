package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegister {
    public static final HashMap<String, Recipe> recipes = new HashMap<>();
    public static Map<String, Recipe> vanillaRecipes = new HashMap<>();

    public static ItemStack[] getItems(ItemStack leftTop, ItemStack middleTop, ItemStack rightTop, ItemStack leftMiddle, ItemStack middle, ItemStack rightMiddle, ItemStack leftBottom, ItemStack middleBottom, ItemStack rightBottom) {
        return new ItemStack[]{leftTop, middleTop, rightTop, leftMiddle, middle, rightMiddle, leftBottom, middleBottom, rightBottom};
    }
}
