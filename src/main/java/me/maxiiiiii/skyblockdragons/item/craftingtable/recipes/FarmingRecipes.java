package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

public class FarmingRecipes extends RecipeRegister {
    public static void registerRecipes() {
        registerEnchantedRecipe("ENCHANTED_WHEAT", "WHEAT");
        registerEnchantedRecipe("ENCHANTED_CARROT_ITEM", "CARROT_ITEM");
        registerEnchantedRecipe("ENCHANTED_POTATO_ITEM", "POTATO_ITEM");
        registerEnchantedRecipe("ENCHANTED_NETHER_WART", "NETHER_WART");

        registerEnchantedRecipe("ENCHANTED_HAY_BLOCK", "ENCHANTED_WHEAT");
        registerEnchantedRecipe("ENCHANTED_GOLDEN_CARROT", "ENCHANTED_CARROT_ITEM");
        registerEnchantedRecipe("ENCHANTED_BAKED_POTATO", "ENCHANTED_POTATO_ITEM");
        registerEnchantedRecipe("ENCHANTED_NETHER_WART_BLOCK", "ENCHANTED_NETHER_WART");
    }
}
