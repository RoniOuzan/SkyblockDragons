package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;

public class DeeperMinesRecipes extends RecipeRegister {
    public static void registerRecipes() {
        recipes.put("ENCHANTED_COBALT", new Recipe(new Item(Items.get("ENCHANTED_COBALT")), getItems(
                new Item(Items.get("COBALT"), 64),
                new Item(Items.get("COBALT"), 64),
                new Item(Items.get("COBALT"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));
        
        recipes.put("COBALT_DRILL", new Recipe(new Item(Items.get("COBALT_DRILL")), getItems(
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("DIAMOND_PICKAXE")),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16)
        ), 4));

        recipes.put("COBALT_HELMET", new Recipe(new Item(Items.get("COBALT_HELMET")), getItems(
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT"), 16),
                null,
                new Item(Items.get("COBALT"), 16),
                null,
                null,
                null
        ), -1));

        recipes.put("COBALT_CHESTPLATE", new Recipe(new Item(Items.get("COBALT_CHESTPLATE")), getItems(
                new Item(Items.get("COBALT_SHARD")),
                null,
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16)
        ), -1));

        recipes.put("COBALT_LEGGINGS", new Recipe(new Item(Items.get("COBALT_LEGGINGS")), getItems(
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT_SHARD")),
                new Item(Items.get("COBALT"), 16),
                null,
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT"), 16),
                null,
                new Item(Items.get("COBALT"), 16)
        ), -1));

        recipes.put("COBALT_BOOTS", new Recipe(new Item(Items.get("COBALT_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("COBALT"), 16),
                null,
                new Item(Items.get("COBALT"), 16),
                new Item(Items.get("COBALT_SHARD")),
                null,
                new Item(Items.get("COBALT_SHARD"))
        ), -1));




        recipes.put("ENCHANTED_CHLOROPHYTE", new Recipe(new Item(Items.get("ENCHANTED_CHLOROPHYTE")), getItems(
                new Item(Items.get("HEMATITE"), 64),
                new Item(Items.get("HEMATITE"), 64),
                new Item(Items.get("HEMATITE"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("CHLOROPHYTE_DRILL", new Recipe(new Item(Items.get("CHLOROPHYTE_DRILL")), getItems(
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("COBALT_DRILL")),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64)
        ), 4));

        recipes.put("CHLOROPHYTE_HELMET", new Recipe(new Item(Items.get("CHLOROPHYTE_HELMET")), getItems(
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE"), 16),
                new Item(Items.get("COBALT_HELMET")),
                new Item(Items.get("CHLOROPHYTE"), 16),
                null,
                null,
                null
        ), 4));

        recipes.put("CHLOROPHYTE_CHESTPLATE", new Recipe(new Item(Items.get("CHLOROPHYTE_CHESTPLATE")), getItems(
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("COBALT_CHESTPLATE")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64)
        ), 1));

        recipes.put("CHLOROPHYTE_LEGGINGS", new Recipe(new Item(Items.get("CHLOROPHYTE_LEGGINGS")), getItems(
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("COBALT_LEGGINGS")),
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE"), 64),
                null,
                new Item(Items.get("CHLOROPHYTE"), 64)
        ), 4));

        recipes.put("CHLOROPHYTE_BOOTS", new Recipe(new Item(Items.get("CHLOROPHYTE_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("CHLOROPHYTE"), 64),
                null,
                new Item(Items.get("CHLOROPHYTE"), 64),
                new Item(Items.get("CHLOROPHYTE_SHARD")),
                new Item(Items.get("COBALT_BOOTS")),
                new Item(Items.get("CHLOROPHYTE_SHARD"))
        ), 7));




        recipes.put("ENCHANTED_LUMINATE", new Recipe(new Item(Items.get("ENCHANTED_LUMINATE")), getItems(
                new Item(Items.get("LUMINATE"), 64),
                new Item(Items.get("LUMINATE"), 64),
                new Item(Items.get("LUMINATE"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("LUMINATE_DRILL", new Recipe(new Item(Items.get("LUMINATE_DRILL")), getItems(
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("CHLOROPHYTE_DRILL")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1)
        ), 4));

        recipes.put("LUMINATE_HELMET", new Recipe(new Item(Items.get("LUMINATE_HELMET")), getItems(
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("CHLOROPHYTE_HELMET")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                null,
                null,
                null
        ), 4));

        recipes.put("LUMINATE_CHESTPLATE", new Recipe(new Item(Items.get("LUMINATE_CHESTPLATE")), getItems(
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_CHESTPLATE")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1)
        ), 1));

        recipes.put("LUMINATE_LEGGINGS", new Recipe(new Item(Items.get("LUMINATE_LEGGINGS")), getItems(
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("CHLOROPHYTE_LEGGINGS")),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                null,
                new Item(Items.get("ENCHANTED_LUMINATE"), 1)
        ), 4));

        recipes.put("LUMINATE_BOOTS", new Recipe(new Item(Items.get("LUMINATE_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                null,
                new Item(Items.get("ENCHANTED_LUMINATE"), 1),
                new Item(Items.get("LUMINATE_SHARD")),
                new Item(Items.get("CHLOROPHYTE_BOOTS")),
                new Item(Items.get("LUMINATE_SHARD"))
        ), 7));




        recipes.put("ENCHANTED_DERNIC", new Recipe(new Item(Items.get("ENCHANTED_DERNIC")), getItems(
                new Item(Items.get("DERNIC"), 64),
                new Item(Items.get("DERNIC"), 64),
                new Item(Items.get("DERNIC"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("DERNIC_DRILL", new Recipe(new Item(Items.get("DERNIC_DRILL")), getItems(
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("LUMINATE_DRILL")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8)
        ), 4));

        recipes.put("DERNIC_HELMET", new Recipe(new Item(Items.get("DERNIC_HELMET")), getItems(
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("LUMINATE_HELMET")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                null,
                null,
                null
        ), 4));

        recipes.put("DERNIC_CHESTPLATE", new Recipe(new Item(Items.get("DERNIC_CHESTPLATE")), getItems(
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("LUMINATE_CHESTPLATE")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8)
        ), 1));

        recipes.put("DERNIC_LEGGINGS", new Recipe(new Item(Items.get("DERNIC_LEGGINGS")), getItems(
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("LUMINATE_LEGGINGS")),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                null,
                new Item(Items.get("ENCHANTED_DERNIC"), 8)
        ), 4));

        recipes.put("DERNIC_BOOTS", new Recipe(new Item(Items.get("DERNIC_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                null,
                new Item(Items.get("ENCHANTED_DERNIC"), 8),
                new Item(Items.get("DERNIC_SHARD")),
                new Item(Items.get("LUMINATE_BOOTS")),
                new Item(Items.get("DERNIC_SHARD"))
        ), 7));




        recipes.put("ENCHANTED_HEMATITE", new Recipe(new Item(Items.get("ENCHANTED_HEMATITE")), getItems(
                new Item(Items.get("HEMATITE"), 64),
                new Item(Items.get("HEMATITE"), 64),
                new Item(Items.get("HEMATITE"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("HEMATITE_DRILL", new Recipe(new Item(Items.get("HEMATITE_DRILL")), getItems(
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("DERNIC_DRILL")),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16),
                new Item(Items.get("ENCHANTED_DERNIC"), 16)
        ), 4));

        recipes.put("HEMATITE_HELMET", new Recipe(new Item(Items.get("HEMATITE_HELMET")), getItems(
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("DERNIC_HELMET")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                null,
                null,
                null
        ), 4));

        recipes.put("HEMATITE_CHESTPLATE", new Recipe(new Item(Items.get("HEMATITE_CHESTPLATE")), getItems(
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("DERNIC_CHESTPLATE")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16)
        ), 1));

        recipes.put("HEMATITE_LEGGINGS", new Recipe(new Item(Items.get("HEMATITE_LEGGINGS")), getItems(
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("DERNIC_LEGGINGS")),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                null,
                new Item(Items.get("ENCHANTED_HEMATITE"), 16)
        ), 4));

        recipes.put("HEMATITE_BOOTS", new Recipe(new Item(Items.get("HEMATITE_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                null,
                new Item(Items.get("ENCHANTED_HEMATITE"), 16),
                new Item(Items.get("HEMATITE_SHARD")),
                new Item(Items.get("DERNIC_BOOTS")),
                new Item(Items.get("HEMATITE_SHARD"))
        ), 7));




        recipes.put("ENCHANTED_VOID_CRYSTAL", new Recipe(new Item(Items.get("ENCHANTED_VOID_CRYSTAL")), getItems(
                new Item(Items.get("VOID_CRYSTAL"), 64),
                new Item(Items.get("VOID_CRYSTAL"), 64),
                new Item(Items.get("VOID_CRYSTAL"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("VOID_CRYSTAL_DRILL", new Recipe(new Item(Items.get("VOID_CRYSTAL_DRILL")), getItems(
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("HEMATITE_DRILL")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1)
        ), 4));

        recipes.put("VOID_CRYSTAL_HELMET", new Recipe(new Item(Items.get("VOID_CRYSTAL_HELMET")), getItems(
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("HEMATITE_HELMET")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                null,
                null,
                null
        ), 4));

        recipes.put("VOID_CRYSTAL_CHESTPLATE", new Recipe(new Item(Items.get("VOID_CRYSTAL_CHESTPLATE")), getItems(
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("HEMATITE_CHESTPLATE")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1)
        ), 1));

        recipes.put("VOID_CRYSTAL_LEGGINGS", new Recipe(new Item(Items.get("VOID_CRYSTAL_LEGGINGS")), getItems(
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("HEMATITE_LEGGINGS")),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                null,
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1)
        ), 4));

        recipes.put("VOID_CRYSTAL_BOOTS", new Recipe(new Item(Items.get("VOID_CRYSTAL_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                null,
                new Item(Items.get("ENCHANTED_VOID_CRYSTAL"), 1),
                new Item(Items.get("VOID_CRYSTAL_SHARD")),
                new Item(Items.get("HEMATITE_BOOTS")),
                new Item(Items.get("VOID_CRYSTAL_SHARD"))
        ), 7));
    }
}
