package me.maxiiiiii.skyblockdragons.item.craftingtable;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.material.*;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;

import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public class Recipe implements Comparable<Recipe> {
    public static final HashMap<String, Recipe> recipes = new HashMap<>();
    public static List<Recipe> allRecipes = new ArrayList<>();

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
        Inventory inventory = Bukkit.createInventory(player, 54, setTitleCase(this.name().replace("_", " ")) + " Recipe");

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

    private static ItemStack[] getItems(ItemStack leftTop, ItemStack middleTop, ItemStack rightTop, ItemStack leftMiddle, ItemStack middle, ItemStack rightMiddle, ItemStack leftBottom, ItemStack middleBottom, ItemStack rightBottom) {
        return new ItemStack[]{leftTop, middleTop, rightTop, leftMiddle, middle, rightMiddle, leftBottom, middleBottom, rightBottom};
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
        recipes.put("ASPECT_OF_THE_END", new Recipe(new Item(Items.get("ASPECT_OF_THE_END")), getItems(
                null,
                new Item(Items.get("ENCHANTED_EYE_OF_ENDER"), 16),
                null,
                null,
                new Item(Items.get("ENCHANTED_EYE_OF_ENDER"), 16),
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

        recipes.put("ENCHANTED_COAL", new Recipe(new Item(Items.get("ENCHANTED_COAL")), getItems(
                new Item(Items.items.get("COAL"), 32),
                new Item(Items.items.get("COAL"), 32),
                new Item(Items.items.get("COAL"), 32),
                new Item(Items.items.get("COAL"), 32),
                new Item(Items.items.get("COAL"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_IRON_INGOT", new Recipe(new Item(Items.get("ENCHANTED_IRON_INGOT")), getItems(
                new Item(Items.items.get("IRON_INGOT"), 32),
                new Item(Items.items.get("IRON_INGOT"), 32),
                new Item(Items.items.get("IRON_INGOT"), 32),
                new Item(Items.items.get("IRON_INGOT"), 32),
                new Item(Items.items.get("IRON_INGOT"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_IRON_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_IRON_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_GOLD_INGOT", new Recipe(new Item(Items.get("ENCHANTED_GOLD_INGOT")), getItems(
                new Item(Items.items.get("GOLD_INGOT"), 32),
                new Item(Items.items.get("GOLD_INGOT"), 32),
                new Item(Items.items.get("GOLD_INGOT"), 32),
                new Item(Items.items.get("GOLD_INGOT"), 32),
                new Item(Items.items.get("GOLD_INGOT"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_GOLD_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_GOLD_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 32),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_LAPIS", new Recipe(new Item(Items.get("ENCHANTED_LAPIS")), getItems(
                new Item(Items.items.get("LAPIS"), 32),
                new Item(Items.items.get("LAPIS"), 32),
                new Item(Items.items.get("LAPIS"), 32),
                new Item(Items.items.get("LAPIS"), 32),
                new Item(Items.items.get("LAPIS"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_LAPIS_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_LAPIS_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS"), 32),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 32),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 32),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 32),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_REDSTONE", new Recipe(new Item(Items.get("ENCHANTED_REDSTONE")), getItems(
                new Item(Items.items.get("REDSTONE"), 32),
                new Item(Items.items.get("REDSTONE"), 32),
                new Item(Items.items.get("REDSTONE"), 32),
                new Item(Items.items.get("REDSTONE"), 32),
                new Item(Items.items.get("REDSTONE"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_REDSTONE_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_REDSTONE_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 32),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 32),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 32),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 32),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_EMERALD", new Recipe(new Item(Items.get("ENCHANTED_EMERALD")), getItems(
                new Item(Items.items.get("EMERALD"), 32),
                new Item(Items.items.get("EMERALD"), 32),
                new Item(Items.items.get("EMERALD"), 32),
                new Item(Items.items.get("EMERALD"), 32),
                new Item(Items.items.get("EMERALD"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_EMERALD_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_EMERALD_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD"), 32),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 32),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 32),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 32),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("ENCHANTED_DIAMOND", new Recipe(new Item(Items.get("ENCHANTED_DIAMOND")), getItems(
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                new Item(Items.items.get("DIAMOND"), 32),
                null,
                null,
                null,
                null
        ), -1, true));

        recipes.put("ENCHANTED_DIAMOND_BLOCK", new Recipe(new Item(Items.get("ENCHANTED_DIAMOND_BLOCK")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 32),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 32),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 32),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 32),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 32),
                null,
                null,
                null,
                null
        ), -1, true));

        recipes.put("ENCHANTED_OBSIDIAN", new Recipe(new Item(Items.get("ENCHANTED_OBSIDIAN")), getItems(
                new Item(Items.items.get("OBSIDIAN"), 32),
                new Item(Items.items.get("OBSIDIAN"), 32),
                new Item(Items.items.get("OBSIDIAN"), 32),
                new Item(Items.items.get("OBSIDIAN"), 32),
                new Item(Items.items.get("OBSIDIAN"), 32),
                null,
                null,
                null,
                null
        ), -1));

        recipes.put("COAL_TALISMAN", new Recipe(new Item(Items.get("COAL_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8),
                new Item(Items.items.get("ENCHANTED_COAL"), 8)
        ), -1));

        recipes.put("COAL_BLOCK_TALISMAN", new Recipe(new Item(Items.get("COAL_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("COAL_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_COAL_BLOCK"), 1)
        ), 4));

        recipes.put("IRON_TALISMAN", new Recipe(new Item(Items.get("IRON_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_IRON_INGOT"), 8)
        ), -1));

        recipes.put("IRON_BLOCK_TALISMAN", new Recipe(new Item(Items.get("IRON_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("IRON_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_IRON_BLOCK"), 1)
        ), 4));

        recipes.put("GOLD_TALISMAN", new Recipe(new Item(Items.get("GOLD_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8),
                new Item(Items.items.get("ENCHANTED_GOLD_INGOT"), 8)
        ), -1));

        recipes.put("GOLD_BLOCK_TALISMAN", new Recipe(new Item(Items.get("GOLD_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("GOLD_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_GOLD_BLOCK"), 1)
        ), 4));

        recipes.put("LAPIS_TALISMAN", new Recipe(new Item(Items.get("LAPIS_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8),
                new Item(Items.items.get("ENCHANTED_LAPIS"), 8)
        ), -1));

        recipes.put("LAPIS_BLOCK_TALISMAN", new Recipe(new Item(Items.get("LAPIS_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("LAPIS_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_LAPIS_BLOCK"), 1)
        ), 4));

        recipes.put("REDSTONE_TALISMAN", new Recipe(new Item(Items.get("REDSTONE_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8),
                new Item(Items.items.get("ENCHANTED_REDSTONE"), 8)
        ), -1));

        recipes.put("REDSTONE_BLOCK_TALISMAN", new Recipe(new Item(Items.get("REDSTONE_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("REDSTONE_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_REDSTONE_BLOCK"), 1)
        ), 4));

        recipes.put("EMERALD_TALISMAN", new Recipe(new Item(Items.get("EMERALD_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8),
                new Item(Items.items.get("ENCHANTED_EMERALD"), 8)
        ), -1));

        recipes.put("EMERALD_BLOCK_TALISMAN", new Recipe(new Item(Items.get("EMERALD_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("EMERALD_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_EMERALD_BLOCK"), 1)
        ), 4));

        recipes.put("DIAMOND_TALISMAN", new Recipe(new Item(Items.get("DIAMOND_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                null,
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8),
                new Item(Items.items.get("ENCHANTED_DIAMOND"), 8)
        ), -1));

        recipes.put("DIAMOND_BLOCK_TALISMAN", new Recipe(new Item(Items.get("DIAMOND_BLOCK_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("DIAMOND_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1),
                new Item(Items.items.get("ENCHANTED_DIAMOND_BLOCK"), 1)
        ), 4));

        recipes.put("OBSIDIAN_TALISMAN", new Recipe(new Item(Items.get("OBSIDIAN_TALISMAN")), getItems(
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("DIAMOND_TALISMAN")),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16),
                new Item(Items.items.get("ENCHANTED_OBSIDIAN"), 16)
        ), 4));

        for (Material material : Material.values()) {
            if (material.name().contains("_NUGGET")) continue;

            for (org.bukkit.inventory.Recipe recipe : Bukkit.getRecipesFor(new ItemStack(material))) {
                if (recipe instanceof ShapelessRecipe) {
                    ShapelessRecipe less = (ShapelessRecipe) recipe;
                    ItemStack[] items = new ItemStack[]{null, null, null, null, null, null, null, null, null};
                    for (int i = 0; i < less.getIngredientList().size(); i++) {
                        items[i] = new Item(Items.get(less.getIngredientList().get(i).getType().name()));
                    }
                    recipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name()), recipe.getResult().getAmount()), items, 0));
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
                                if (material.toString().contains("HOE"))
                                    i++;
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null)
                                    items[i] = new Item(shaped.getIngredientMap().get(slot.charAt(0)));
                                if (!material.toString().contains("HOE"))
                                    i += 2;
                            }
                            if (!material.toString().contains("HOE"))
                                i--;
                            else
                                i++;
                        } else {
                            if (shaped.getIngredientMap().get(row.charAt(0)) != null)
                                items[i + 1] = new Item(shaped.getIngredientMap().get(row.charAt(0)));
                            i += 3;
                        }
                    }
                    recipes.put(recipe.getResult().getType().name(), new Recipe(new Item(Items.get(recipe.getResult().getType().name()), recipe.getResult().getAmount()), items, -1));
                }
            }
        }

        allRecipes = new ArrayList<>(recipes.values());
        allRecipes.sort(Collections.reverseOrder());
    }

    public Map<ItemMaterial, Integer> getAllItems() {
        Map<ItemMaterial, Integer> items = new HashMap<>();
        for (ItemStack item : this.getItems()) {
            if (item == null) continue;

            items.put(Items.get(item), items.getOrDefault(Items.get(item), 0) + item.getAmount());
        }
        return items;
    }

    public int getSize() {
        int size = 0;
        for (ItemStack itemStack : this.getItems()) {
            if (itemStack == null) continue;
            size += itemStack.getAmount();
        }
        if (getItem() instanceof Item) {
            Item item = (Item) getItem();
            if (item.getMaterial() instanceof NormalMaterial) {
                size += 20;
            }
            if (item.getMaterial() instanceof WeaponMaterial) {
                size += 10;
            }
            if (item.getMaterial() instanceof ArmorMaterial) {
                size += 5;
            }
        }
        return size;
    }

    public String name() {
        for (String key : Recipe.recipes.keySet()) {
            if (Recipe.recipes.get(key) == this) {
                return key;
            }
        }
        return "";
    }

    public static List<Recipe> getRecipesFor(ItemStack item) {
        String itemId = Functions.getId(item);
        if (itemId.isEmpty()) return new ArrayList<>();
        List<Recipe> recipesFor = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (Functions.getId(recipe.getItem()).equals(itemId))
                recipesFor.add(recipe);
        }
        return recipesFor;
    }

    public static List<Recipe> getRecipesWith(ItemStack item) {
        String itemId = Functions.getId(item);
        if (itemId.isEmpty()) return new ArrayList<>();
        List<Recipe> recipesFor = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (Functions.getId(recipe.getItem()).equals(itemId) || Arrays.stream(recipe.getItems().clone()).map(Functions::getId).collect(Collectors.toList()).contains(itemId))
                recipesFor.add(recipe);
        }
        return recipesFor;
    }

    public static List<Recipe> getRecipesCanCraft(PlayerSD player, int maxTimes) {
        List<Recipe> output = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            Map<ItemMaterial, Integer> items = recipe.getAllItems();
            boolean stopped = false;
            for (ItemMaterial material : items.keySet()) {
                if (!player.hasItem(material, items.get(material))) {
                    stopped = true;
                    break;
                }
            }
            if (!stopped) {
                output.add(recipe);
            }
            if (output.size() >= maxTimes)
                break;
        }

        return output;
    }

    @Override
    public int compareTo(Recipe recipe) {
        return this.getSize() - recipe.getSize();
    }
}
