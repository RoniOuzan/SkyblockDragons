package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;

public class DeepMinesRecipes extends RecipeRegister {
    public static void registerRecipes () {
        recipes.put("ENCHANTED_COAL", new Recipe(new Item(Items.get("ENCHANTED_COAL")), getItems(
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        registerEnchantedRecipe("ENCHANTED_COAL_BLOCK", "ENCHANTED_COAL");

        recipes.put("ENCHANTED_IRON_INGOT", new Recipe(new Item(Items.get("ENCHANTED_IRON_INGOT")), getItems(
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_IRON_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_IRON_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 64),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 64),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_GOLD_INGOT", new Recipe(new Item(Items.get("ENCHANTED_GOLD_INGOT")), getItems(
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_GOLD_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_GOLD_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 64),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 64),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_LAPIS", new Recipe(new Item(Items.get("ENCHANTED_LAPIS")), getItems(
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_LAPIS_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_LAPIS_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS"), 64),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 64),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_REDSTONE", new Recipe(new Item(Items.get("ENCHANTED_REDSTONE")), getItems(
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_REDSTONE_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_REDSTONE_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 64),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 64),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_EMERALD", new Recipe(new Item(Items.get("ENCHANTED_EMERALD")), getItems(
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_EMERALD_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_EMERALD_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD"), 64),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 64),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_DIAMOND", new Recipe(new Item(Items.get("ENCHANTED_DIAMOND")), getItems(
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1, true));

        recipes.put("ENCHANTED_DIAMOND_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_DIAMOND_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 64),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 64),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1, true));

        recipes.put("ENCHANTED_OBSIDIAN", new Recipe(new Item(Items.get("ENCHANTED_OBSIDIAN")), getItems(
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                null,
                null,
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("COAL_TALISMAN", new Recipe(new Item(Items.get("COAL_TALISMAN")), getItems(
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                null,
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64),
                new Item(Items.items.get("COAL"), 64)
        ), -1));

        recipes.put("COAL_RING", new Recipe(new Item(Items.get("COAL_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("COAL_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1),
                new Item(Items.items.get("ENCHANTED_COAL"), 1)
        ), 4));

        recipes.put("COAL_ARTIFACT", new Recipe(new Item(Items.get("COAL_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("COAL_RING")),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1)
        ), 4));


        recipes.put("IRON_TALISMAN", new Recipe(new Item(Items.get("IRON_TALISMAN")), getItems(
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                null,
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64),
                new Item(Items.items.get("IRON_INGOT"), 64)
        ), -1));

        recipes.put("IRON_RING", new Recipe(new Item(Items.get("IRON_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("IRON_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 1)
        ), 4));

        recipes.put("IRON_ARTIFACT", new Recipe(new Item(Items.get("IRON_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("IRON_RING")),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1)
        ), 4));


        recipes.put("GOLD_TALISMAN", new Recipe(new Item(Items.get("GOLD_TALISMAN")), getItems(
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                null,
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64),
                new Item(Items.items.get("GOLD_INGOT"), 64)
        ), -1));

        recipes.put("GOLD_RING", new Recipe(new Item(Items.get("GOLD_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("GOLD_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 1)
        ), 4));

        recipes.put("GOLD_ARTIFACT", new Recipe(new Item(Items.get("GOLD_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("GOLD_RING")),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1)
        ), 4));


        recipes.put("LAPIS_TALISMAN", new Recipe(new Item(Items.get("LAPIS_TALISMAN")), getItems(
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                null,
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64),
                new Item(Items.items.get("LAPIS"), 64)
        ), -1));

        recipes.put("LAPIS_RING", new Recipe(new Item(Items.get("LAPIS_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("LAPIS_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 1)
        ), 4));

        recipes.put("LAPIS_ARTIFACT", new Recipe(new Item(Items.get("LAPIS_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("LAPIS_RING")),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1)
        ), 4));


        recipes.put("REDSTONE_TALISMAN", new Recipe(new Item(Items.get("REDSTONE_TALISMAN")), getItems(
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                null,
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64),
                new Item(Items.items.get("REDSTONE"), 64)
        ), -1));

        recipes.put("REDSTONE_RING", new Recipe(new Item(Items.get("REDSTONE_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("REDSTONE_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 1)
        ), 4));

        recipes.put("REDSTONE_ARTIFACT", new Recipe(new Item(Items.get("REDSTONE_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("REDSTONE_RING")),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1)
        ), 4));


        recipes.put("EMERALD_TALISMAN", new Recipe(new Item(Items.get("EMERALD_TALISMAN")), getItems(
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                null,
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64),
                new Item(Items.items.get("EMERALD"), 64)
        ), -1));

        recipes.put("EMERALD_RING", new Recipe(new Item(Items.get("EMERALD_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("EMERALD_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 1)
        ), 4));

        recipes.put("EMERALD_ARTIFACT", new Recipe(new Item(Items.get("EMERALD_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("EMERALD_RING")),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1)
        ), 4));


        recipes.put("DIAMOND_TALISMAN", new Recipe(new Item(Items.get("DIAMOND_TALISMAN")), getItems(
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                null,
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64),
                new Item(Items.items.get("DIAMOND"), 64)
        ), -1));

        recipes.put("DIAMOND_RING", new Recipe(new Item(Items.get("DIAMOND_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("DIAMOND_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 1)
        ), 4));

        recipes.put("DIAMOND_ARTIFACT", new Recipe(new Item(Items.get("DIAMOND_ARTIFACT")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("DIAMOND_RING")),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1)
        ), 4));


        recipes.put("OBSIDIAN_TALISMAN", new Recipe(new Item(Items.get("OBSIDIAN_TALISMAN")), getItems(
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                null,
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64),
                new Item(Items.items.get("OBSIDIAN"), 64)
        ), -1));

        recipes.put("OBSIDIAN_RING", new Recipe(new Item(Items.get("OBSIDIAN_RING")), getItems(
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("OBSIDIAN_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 1)
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
    }
}
