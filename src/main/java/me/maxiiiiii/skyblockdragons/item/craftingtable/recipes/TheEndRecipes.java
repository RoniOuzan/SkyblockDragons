package me.maxiiiiii.skyblockdragons.item.craftingtable.recipes;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.Recipe;
import me.maxiiiiii.skyblockdragons.item.material.Items;

public class TheEndRecipes extends RecipeRegister {
    public static void registerRecipes() {
        recipes.put("ASPECT_OF_THE_END", new Recipe(new Item(Items.get("ASPECT_OF_THE_END")), getItems(
                null,
                new Item(Items.get("EYE_OF_ENDERMAN"), 16),
                null,
                null,
                new Item(Items.get("EYE_OF_ENDERMAN"), 16),
                null,
                null,
                new Item(Items.get("ENCHANTED_DIAMOND")),
                null
        ), -1));

        recipes.put("ASPECT_OF_THE_VOID", new Recipe(new Item(Items.get("ASPECT_OF_THE_VOID")), getItems(
                null,
                new Item(Items.get("NULL_OVOID"), 8),
                null,
                new Item(Items.get("NULL_OVOID"), 8),
                new Item(Items.get("ASPECT_OF_THE_END")),
                new Item(Items.get("NULL_OVOID"), 8),
                null,
                new Item(Items.get("NULL_OVOID"), 8),
                null
        ), 4));

        recipes.put("ENDERMAN_TALISMAN_UNCOMMON", new Recipe(new Item(Items.get("ENDERMAN_TALISMAN_UNCOMMON")), getItems(
                new Item(Items.get("ENDERMAN_TALISMAN_COMMON")),
                new Item(Items.get("ENDERMAN_TALISMAN_COMMON")),
                null,
                new Item(Items.get("ENDERMAN_TALISMAN_COMMON")),
                new Item(Items.get("ENDERMAN_TALISMAN_COMMON")),
                null,
                null,
                null,
                null
        ), 0));

        recipes.put("ENDERMAN_TALISMAN_RARE", new Recipe(new Item(Items.get("ENDERMAN_TALISMAN_RARE")), getItems(
                new Item(Items.get("ENDERMAN_TALISMAN_UNCOMMON")),
                new Item(Items.get("ENDERMAN_TALISMAN_UNCOMMON")),
                null,
                new Item(Items.get("ENDERMAN_TALISMAN_UNCOMMON")),
                new Item(Items.get("ENDERMAN_TALISMAN_UNCOMMON")),
                null,
                null,
                null,
                null
        ), 0));

        recipes.put("ENDERMAN_TALISMAN_EPIC", new Recipe(new Item(Items.get("ENDERMAN_TALISMAN_EPIC")), getItems(
                new Item(Items.get("ENDERMAN_TALISMAN_RARE")),
                new Item(Items.get("ENDERMAN_TALISMAN_RARE")),
                null,
                new Item(Items.get("ENDERMAN_TALISMAN_RARE")),
                new Item(Items.get("ENDERMAN_TALISMAN_RARE")),
                null,
                null,
                null,
                null
        ), 0));

        recipes.put("ENDERMAN_TALISMAN_LEGENDARY", new Recipe(new Item(Items.get("ENDERMAN_TALISMAN_LEGENDARY")), getItems(
                new Item(Items.get("ENDERMAN_TALISMAN_LEGENDARY")),
                new Item(Items.get("ENDERMAN_TALISMAN_LEGENDARY")),
                null,
                new Item(Items.get("ENDERMAN_TALISMAN_LEGENDARY")),
                new Item(Items.get("ENDERMAN_TALISMAN_LEGENDARY")),
                null,
                null,
                null,
                null
        ), 0));

        recipes.put("ENDER_HELMET", new Recipe(new Item(Items.get("ENDER_HELMET")), getItems(
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                null,
                null
        ), -1));

        recipes.put("ENDER_CHESTPLATE", new Recipe(new Item(Items.get("ENDER_CHESTPLATE")), getItems(
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN"))
        ), -1));

        recipes.put("ENDER_LEGGINGS", new Recipe(new Item(Items.get("ENDER_LEGGINGS")), getItems(
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN"))
        ), -1));

        recipes.put("ENDER_BOOTS", new Recipe(new Item(Items.get("ENDER_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN")),
                new Item(Items.get("EYE_OF_ENDERMAN")),
                null,
                new Item(Items.get("EYE_OF_ENDERMAN"))
        ), -1));


        recipes.put("ENDER_GUARD_HELMET", new Recipe(new Item(Items.get("ENDER_GUARD_HELMET")), getItems(
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                null,
                null
        ), -1));

        recipes.put("ENDER_GUARD_CHESTPLATE", new Recipe(new Item(Items.get("ENDER_GUARD_CHESTPLATE")), getItems(
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD"))
        ), -1));

        recipes.put("ENDER_GUARD_LEGGINGS", new Recipe(new Item(Items.get("ENDER_GUARD_LEGGINGS")), getItems(
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD"))
        ), -1));

        recipes.put("ENDER_GUARD_BOOTS", new Recipe(new Item(Items.get("ENDER_GUARD_BOOTS")), getItems(
                null,
                null,
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                new Item(Items.get("EYE_OF_ENDER_GUARD")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD"))
        ), -1));

        recipes.put("ENDER_BOW", new Recipe(new Item(Items.get("ENDER_BOW")), getItems(
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD"), 3),
                new Item(Items.get("STICK")),
                new Item(Items.get("EYE_OF_ENDER_GUARD"), 3),
                null,
                new Item(Items.get("STICK")),
                null,
                new Item(Items.get("EYE_OF_ENDER_GUARD"), 3),
                new Item(Items.get("STICK"))
        ), -1));

        recipes.put("ENDSTONE_TALISMAN", new Recipe(new Item(Items.get("ENDSTONE_TALISMAN")), getItems(
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32),
                null,
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32),
                new Item(Items.get("ENDER_STONE"), 32)
        ), -1));

        recipes.put("ENDSTONE_RING", new Recipe(new Item(Items.get("ENDSTONE_RING")), getItems(
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENDSTONE_TALISMAN")),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 1)
        ), 4));

        recipes.put("ENDSTONE_ARTIFACT", new Recipe(new Item(Items.get("ENDSTONE_ARTIFACT")), getItems(
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENDSTONE_TALISMAN")),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8),
                new Item(Items.get("ENCHANTED_ENDER_STONE"), 8)
        ), 4));
    }
}
