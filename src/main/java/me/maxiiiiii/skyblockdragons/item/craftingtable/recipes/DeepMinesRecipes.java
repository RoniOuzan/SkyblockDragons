package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;

public class DeepMinesRecipes extends RecipeRegister {
    public static void registerRecipes () {
        registerEnchantedRecipe("ENCHANTED_COBBLESTONE", "COBBLESTONE");

        registerEnchantedRecipe("ENCHANTED_COAL", "COAL");
        registerEnchantedRecipe("ENCHANTED_COAL_BLOCK", "ENCHANTED_COAL");

        registerEnchantedRecipe("ENCHANTED_IRON_INGOT", "IRON_INGOT");
        registerEnchantedRecipe("ENCHANTED_IRON_BLOCK", "ENCHANTED_IRON_INGOT");

        registerEnchantedRecipe("ENCHANTED_GOLD_INGOT", "GOLD_INGOT");
        registerEnchantedRecipe("ENCHANTED_GOLD_BLOCK", "ENCHANTED_GOLD_INGOT");

        registerEnchantedRecipe("ENCHANTED_LAPIS", "LAPIS");
        registerEnchantedRecipe("ENCHANTED_LAPIS_BLOCK", "ENCHANTED_LAPIS");

        registerEnchantedRecipe("ENCHANTED_REDSTONE", "REDSTONE");
        registerEnchantedRecipe("ENCHANTED_REDSTONE_BLOCK", "ENCHANTED_REDSTONE");

        registerEnchantedRecipe("ENCHANTED_EMERALD", "EMERALD");
        registerEnchantedRecipe("ENCHANTED_EMERALD_BLOCK", "ENCHANTED_EMERALD");

        registerEnchantedRecipe("ENCHANTED_DIAMOND", "DIAMOND");
        registerEnchantedRecipe("ENCHANTED_DIAMOND_BLOCK", "ENCHANTED_DIAMOND");

        registerEnchantedRecipe("ENCHANTED_OBSIDIAN", "OBSIDIAN");

        recipes.put("STONE", new Recipe(new Item(Items.get("STONE")), getItems(
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE")),
                new Item(Items.get("COAL")),
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE")),
                new Item(Items.get("STONE"))
        ), -1));

        recipes.put("STONE_TALISMAN", new Recipe(new Item(Items.get("STONE_TALISMAN")), getItems(
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16),
                null,
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16),
                new Item(Items.get("STONE"), 16)
        ), -1));

        recipes.put("STONE_RING", new Recipe(new Item(Items.get("STONE_RING")), getItems(
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE_TALISMAN")),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64),
                new Item(Items.get("STONE"), 64)
        ), 4));

        recipes.put("STONE_ARTIFACT", new Recipe(new Item(Items.get("STONE_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("STONE_RING")),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1),
                new Item(Items.get("ENCHANTED_STONE"), 1)
        ), 4));

        recipes.put("COAL_TALISMAN", new Recipe(new Item(Items.get("COAL_TALISMAN")), getItems(
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64),
                null,
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64),
                new Item(Items.get("COAL"), 64)
        ), -1));

        recipes.put("COAL_RING", new Recipe(new Item(Items.get("COAL_RING")), getItems(
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("COAL_TALISMAN")),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1),
                new Item(Items.get("ENCHANTED_COAL"), 1)
        ), 4));

        recipes.put("COAL_ARTIFACT", new Recipe(new Item(Items.get("COAL_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("COAL_RING")),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_COAL_BLOCK"), 1)
        ), 4));


        recipes.put("IRON_TALISMAN", new Recipe(new Item(Items.get("IRON_TALISMAN")), getItems(
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64),
                null,
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64),
                new Item(Items.get("IRON_INGOT"), 64)
        ), -1));

        recipes.put("IRON_RING", new Recipe(new Item(Items.get("IRON_RING")), getItems(
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("IRON_TALISMAN")),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.get("ENCHANTED_IRON_INGOT"), 1)
        ), 4));

        recipes.put("IRON_ARTIFACT", new Recipe(new Item(Items.get("IRON_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("IRON_RING")),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_IRON_BLOCK"), 1)
        ), 4));


        recipes.put("GOLD_TALISMAN", new Recipe(new Item(Items.get("GOLD_TALISMAN")), getItems(
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64),
                null,
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64),
                new Item(Items.get("GOLD_INGOT"), 64)
        ), -1));

        recipes.put("GOLD_RING", new Recipe(new Item(Items.get("GOLD_RING")), getItems(
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("GOLD_TALISMAN")),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.get("ENCHANTED_GOLD_INGOT"), 1)
        ), 4));

        recipes.put("GOLD_ARTIFACT", new Recipe(new Item(Items.get("GOLD_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("GOLD_RING")),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_GOLD_BLOCK"), 1)
        ), 4));


        recipes.put("LAPIS_TALISMAN", new Recipe(new Item(Items.get("LAPIS_TALISMAN")), getItems(
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64),
                null,
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64),
                new Item(Items.get("LAPIS"), 64)
        ), -1));

        recipes.put("LAPIS_RING", new Recipe(new Item(Items.get("LAPIS_RING")), getItems(
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("LAPIS_TALISMAN")),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.get("ENCHANTED_LAPIS"), 1)
        ), 4));

        recipes.put("LAPIS_ARTIFACT", new Recipe(new Item(Items.get("LAPIS_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("LAPIS_RING")),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_LAPIS_BLOCK"), 1)
        ), 4));


        recipes.put("REDSTONE_TALISMAN", new Recipe(new Item(Items.get("REDSTONE_TALISMAN")), getItems(
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64),
                null,
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64),
                new Item(Items.get("REDSTONE"), 64)
        ), -1));

        recipes.put("REDSTONE_RING", new Recipe(new Item(Items.get("REDSTONE_RING")), getItems(
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("REDSTONE_TALISMAN")),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE"), 1)
        ), 4));

        recipes.put("REDSTONE_ARTIFACT", new Recipe(new Item(Items.get("REDSTONE_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("REDSTONE_RING")),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_REDSTONE_BLOCK"), 1)
        ), 4));


        recipes.put("EMERALD_TALISMAN", new Recipe(new Item(Items.get("EMERALD_TALISMAN")), getItems(
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64),
                null,
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64),
                new Item(Items.get("EMERALD"), 64)
        ), -1));

        recipes.put("EMERALD_RING", new Recipe(new Item(Items.get("EMERALD_RING")), getItems(
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("EMERALD_TALISMAN")),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.get("ENCHANTED_EMERALD"), 1)
        ), 4));

        recipes.put("EMERALD_ARTIFACT", new Recipe(new Item(Items.get("EMERALD_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("EMERALD_RING")),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_EMERALD_BLOCK"), 1)
        ), 4));


        recipes.put("DIAMOND_TALISMAN", new Recipe(new Item(Items.get("DIAMOND_TALISMAN")), getItems(
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64),
                null,
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64),
                new Item(Items.get("DIAMOND"), 64)
        ), -1));

        recipes.put("DIAMOND_RING", new Recipe(new Item(Items.get("DIAMOND_RING")), getItems(
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("DIAMOND_TALISMAN")),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND"), 1)
        ), 4));

        recipes.put("DIAMOND_ARTIFACT", new Recipe(new Item(Items.get("DIAMOND_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("DIAMOND_RING")),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.get("ENCHANTED_DIAMOND_BLOCK"), 1)
        ), 4));


        recipes.put("OBSIDIAN_TALISMAN", new Recipe(new Item(Items.get("OBSIDIAN_TALISMAN")), getItems(
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64),
                null,
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64),
                new Item(Items.get("OBSIDIAN"), 64)
        ), -1));

        recipes.put("OBSIDIAN_RING", new Recipe(new Item(Items.get("OBSIDIAN_RING")), getItems(
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("OBSIDIAN_TALISMAN")),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.get("ENCHANTED_OBSIDIAN"), 1)
        ), 4));


        recipes.put("COBBLESTONE_TALISMAN", new Recipe(new Item(Items.get("COBBLESTONE_TALISMAN")), getItems(
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32),
                null,
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE"), 32)
        ), -1));

        recipes.put("COBBLESTONE_RING", new Recipe(new Item(Items.get("COBBLESTONE_RING")), getItems(
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("COBBLESTONE_TALISMAN")),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 1)
        ), 4));

        recipes.put("COBBLESTONE_ARTIFACT", new Recipe(new Item(Items.get("COBBLESTONE_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("COBBLESTONE_RING")),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32),
                new Item(Items.get("ENCHANTED_COBBLESTONE"), 32)
        ), 4));


        recipes.put("LAPIS_HELMET", new Recipe(new Item(Items.get("LAPIS_HELMET")), getItems(
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS")),
                null,
                null,
                null
        ), -1));

        recipes.put("LAPIS_CHESTPLATE", new Recipe(new Item(Items.get("LAPIS_CHESTPLATE")), getItems(
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS"))
        ), -1));

        recipes.put("LAPIS_LEGGINGS", new Recipe(new Item(Items.get("LAPIS_LEGGINGS")), getItems(
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS"))
        ), -1));

        recipes.put("LAPIS_BOOTS", new Recipe(new Item(Items.get("LAPIS_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS")),
                new Item(Items.get("LAPIS")),
                null,
                new Item(Items.get("LAPIS"))
        ), -1));

        recipes.put("REDSTONE_HELMET", new Recipe(new Item(Items.get("REDSTONE_HELMET")), getItems(
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE")),
                null,
                null,
                null
        ), -1));

        recipes.put("REDSTONE_CHESTPLATE", new Recipe(new Item(Items.get("REDSTONE_CHESTPLATE")), getItems(
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE"))
        ), -1));

        recipes.put("REDSTONE_LEGGINGS", new Recipe(new Item(Items.get("REDSTONE_LEGGINGS")), getItems(
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE"))
        ), -1));

        recipes.put("REDSTONE_BOOTS", new Recipe(new Item(Items.get("REDSTONE_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE")),
                new Item(Items.get("REDSTONE")),
                null,
                new Item(Items.get("REDSTONE"))
        ), -1));

        recipes.put("EMERALD_HELMET", new Recipe(new Item(Items.get("EMERALD_HELMET")), getItems(
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD")),
                null,
                null,
                null
        ), -1));

        recipes.put("EMERALD_CHESTPLATE", new Recipe(new Item(Items.get("EMERALD_CHESTPLATE")), getItems(
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD"))
        ), -1));

        recipes.put("EMERALD_LEGGINGS", new Recipe(new Item(Items.get("EMERALD_LEGGINGS")), getItems(
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD"))
        ), -1));

        recipes.put("EMERALD_BOOTS", new Recipe(new Item(Items.get("EMERALD_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD")),
                new Item(Items.get("EMERALD")),
                null,
                new Item(Items.get("EMERALD"))
        ), -1));

        recipes.put("OBSIDIAN_SWORD", new Recipe(new Item(Items.get("OBSIDIAN_SWORD")), getItems(
                null,
                new Item(Items.get("OBSIDIAN"), 16),
                null,
                null,
                new Item(Items.get("OBSIDIAN"), 16),
                null,
                null,
                new Item(Items.get("DIAMOND_SWORD"), 1),
                null
        ), -1));
    }
}
