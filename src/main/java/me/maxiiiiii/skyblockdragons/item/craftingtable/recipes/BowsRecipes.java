package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;

public class BowsRecipes extends RecipeRegister {

    public static void registerRecipes() {
        registerEnchantedRecipe("ENCHANTED_BONE", "BONE");
        registerEnchantedRecipe("ENCHANTED_GUNPOWDER", "GUNPOWDER");

        registerRecipe("HURRICANE_BOW", new Recipe(new Item(Items.get("HURRICANE_BOW")), getItems(
                null,
                new Item(Items.get("ENCHANTED_BONE"), 1),
                new Item(Items.get("STRING"), 1),
                new Item(Items.get("ENCHANTED_BONE"), 1),
                null,
                new Item(Items.get("STRING"), 1),
                null,
                new Item(Items.get("ENCHANTED_BONE")),
                new Item(Items.get("STRING"), 1)
        ), -1));
        registerRecipe("RUNAANS_BOW", new Recipe(new Item(Items.get("RUNAANS_BOW")), getItems(
                null,
                new Item(Items.get("ENCHANTED_BONE"), 1),
                new Item(Items.get("STRING"), 1),
                new Item(Items.get("ENCHANTED_BONE"), 1),
                new Item(Items.get("HURRICANE_BOW"), 1),
                new Item(Items.get("STRING"), 1),
                null,
                new Item(Items.get("ENCHANTED_BONE")),
                new Item(Items.get("STRING"), 1)
        ), 4));
        registerRecipe("YOUNG_BOW", new Recipe(new Item(Items.get("YOUNG_BOW")), getItems(
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("RUNAANS_BOW"), 1),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16),
                new Item(Items.get("YOUNG_DRAGON_FRAGMENT"), 16)
        ), 4));
        registerRecipe("EXPLOSIVE_BOW", new Recipe(new Item(Items.get("EXPLOSIVE_BOW")), getItems(
                null,
                new Item(Items.get("ENCHANTED_GUNPOWDER"), 1),
                new Item(Items.get("STRING"), 1),
                new Item(Items.get("ENCHANTED_GUNPOWDER"), 1),
                null,
                new Item(Items.get("STRING"), 1),
                null,
                new Item(Items.get("ENCHANTED_GUNPOWDER")),
                new Item(Items.get("STRING"), 1)
        ), 4));

    }
}
