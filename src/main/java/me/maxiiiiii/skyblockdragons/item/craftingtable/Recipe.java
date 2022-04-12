package me.maxiiiiii.skyblockdragons.item.craftingtable;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.recipes.DeepMinesRecipes;
import me.maxiiiiii.skyblockdragons.item.craftingtable.recipes.DragonRecipes;
import me.maxiiiiii.skyblockdragons.item.craftingtable.recipes.TheEndRecipes;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.NormalMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.WeaponMaterial;
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

    public Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade, boolean viewable) {
        this(item, items, slotToUpgrade, viewable, false);
    }

    public Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade) {
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

    public static ItemStack[] getItems(ItemStack leftTop, ItemStack middleTop, ItemStack rightTop, ItemStack leftMiddle, ItemStack middle, ItemStack rightMiddle, ItemStack leftBottom, ItemStack middleBottom, ItemStack rightBottom) {
        return new ItemStack[]{leftTop, middleTop, rightTop, leftMiddle, middle, rightMiddle, leftBottom, middleBottom, rightBottom};
    }

    public static Recipe get(String name) {
        return Recipe.recipes.get(name);
    }

    public static void registerRecipes() {
        DeepMinesRecipes.registerRecipes();

        TheEndRecipes.registerRecipes();
        DragonRecipes.registerRecipes();

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
                    int i = shaped.getShape().length == 3 ? 0 : 3;
                    for (String row : shaped.getShape()) {
                        if (row.length() == 3) {
                            for (String slot : row.split("")) {
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null) {
                                    items[i] = new Item(null, shaped.getIngredientMap().get(slot.charAt(0)));
                                }
                                i++;
                            }
                        } else if (row.length() == 2) {
                            for (String slot : row.split("")) {
                                if (material.toString().contains("HOE"))
                                    i++;
                                if (shaped.getIngredientMap().get(slot.charAt(0)) != null)
                                    items[i] = new Item(null, shaped.getIngredientMap().get(slot.charAt(0)));
                                if (!material.toString().contains("HOE"))
                                    i += 2;
                            }
                            if (!material.toString().contains("HOE"))
                                i--;
                            else
                                i++;
                        } else {
                            if (shaped.getIngredientMap().get(row.charAt(0)) != null)
                                items[i + 1] = new Item(null, shaped.getIngredientMap().get(row.charAt(0)));
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
