package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class VanillaRecipes extends RecipeRegister {
    public static void registerRecipes() {
        for (Material material : Material.values()) {
            if (material.name().contains("_NUGGET")) continue;

            for (org.bukkit.inventory.Recipe recipe : Bukkit.getRecipesFor(new ItemStack(material))) {
                if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe less = (ShapelessRecipe) recipe;
                    ItemStack[] items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
                    for (int i = 0; i < less.getIngredientList().size(); i++) {
                        items[i] = new Item(Items.get(less.getIngredientList().get(i).getType().name()));
                    }
                    vanillaRecipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name()), recipe.getResult().getAmount()), items, 0));
                } else if (recipe instanceof ShapedRecipe) {
                    ShapedRecipe shaped = (ShapedRecipe) recipe;

                    ItemStack[] items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
                    int i = shaped.getShape().length == 3 ? 0 : 3;
                    for (String row : shaped.getShape()) {
                        if (row.length() == 3) {
                            for (String slot : row.split("")) {
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null) {
                                    items[i] = new Item(Items.get(shaped.getIngredientMap().get(slot.charAt(0)).getType().name()));
                                }
                                i++;
                            }
                        } else if (row.length() == 2) {
                            for (String slot : row.split("")) {
//                                if (material.toString().contains("HOE"))
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null) {
                                    items[i] = new Item(Items.get(shaped.getIngredientMap().get(slot.charAt(0)).getType().name()));
                                }
//                                if (!material.toString().contains("HOE"))
//                                    i += 2;
                                i++;
                            }
//                            if (!material.toString().contains("HOE"))
//                                i--;
//                            else
                            i++;
                        } else {
                            if (shaped.getIngredientMap().get(row.charAt(0)) != null)
                                items[i + 1] = new Item(Items.get(shaped.getIngredientMap().get(row.charAt(0)).getType().name()));
                            i += 3;
                        }
                    }
                    vanillaRecipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name()), recipe.getResult().getAmount()), items, -1));
                }
            }
        }
    }
}
