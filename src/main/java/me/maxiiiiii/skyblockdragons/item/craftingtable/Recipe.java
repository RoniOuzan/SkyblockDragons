package me.maxiiiiii.skyblockdragons.item.craftingtable;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.craftingtable.recipes.*;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.NormalMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.WeaponMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.setTitleCase;

@Getter
public class Recipe extends RecipeRegister implements Comparable<Recipe> {
    public static List<Recipe> sorted = new ArrayList<>();
    public static List<Recipe> abcSorted = new ArrayList<>();

    private final ItemStack item;
    private final ItemStack[] items;
    private final int slotToUpgrade;
    private final boolean viewable;
    private final boolean shapeless;

    public Recipe(ItemStack item, ItemStack[] items, int slotToUpgrade, boolean viewable, boolean shapeless) {
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

    public boolean isVanilla() {
        return vanillaRecipes.containsValue(this);
    }

    public void view(Player player) {
        new View(SkyblockDragons.getPlayer(player));
    }

    public class View extends Menu {
        public View(PlayerSD player) {
            super(player, setTitleCase(name().replace("_", " ")) + " Recipe", 6, InventoryGlassType.ALL, false);
        }

        @Override
        public void update() {
            this.setItem(23, createItem(Material.WORKBENCH, ChatColor.GREEN + "Crafting Table", "CRAFTING_TABLE", ChatColor.GRAY + "Craft this recipe by using a", ChatColor.GRAY + "crafting table."));

            this.setItem(25, item);

            for (int i = 0; i < 9; i++) {
                if (items[i] != null)
                    inventory.setItem(Functions.numToSlot(i), items[i]);
                else
                    inventory.setItem(Functions.numToSlot(i), new ItemStack(Material.AIR));
            }
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (e.getSlot() % 9 == 1 || e.getSlot() % 9 == 2 || e.getSlot() % 9 == 3 &&
                    e.getSlot() / 9 == 1 || e.getSlot() / 9 == 2 || e.getSlot() / 9 == 3) {
                Recipe recipe = Recipe.get(Items.get(e.getCurrentItem()).name());
                if (recipe != null)
                    recipe.view((Player) e.getWhoClicked());
            }
        }

    }
    public static class ViewCommand extends CommandSD {
        @Override
        public void command(PlayerSD player, String[] args) {
            if (args.length > 0) {
                if (Recipe.recipes.containsKey(args[0].toUpperCase())) {
                    Recipe recipe = Recipe.get(args[0].toUpperCase());
                    if (recipe.isViewable()) {
                        recipe.view(player);
                        return;
                    }
                }
                player.sendMessage(ChatColor.RED + "Could not find a recipe with that name!");
                return;
            }
            player.sendMessage(ChatColor.WHITE + "/viewrecipe <RECIPE_NAME>");
        }

        @Override
        public List<Argument> tabComplete(List<Argument> tabs) {
            tabs.add(new Argument(0, "", Recipe.recipes.keySet()));
            return tabs;
        }
    }

    public static Recipe get(String name) {
        return Recipe.recipes.get(name);
    }

    public static void registerRecipes() {
        DeepMinesRecipes.registerRecipes();
        TheEndRecipes.registerRecipes();
        DragonRecipes.registerRecipes();
        DeeperMinesRecipes.registerRecipes();

        VanillaRecipes.registerRecipes();

        recipes.putAll(vanillaRecipes);

        sorted = new ArrayList<>(recipes.values());
        sorted.sort(Collections.reverseOrder());

        abcSorted = recipes.values().stream().filter(r -> !r.isVanilla()).sorted(Comparator.comparing(Recipe::name)).collect(Collectors.toList());
        abcSorted.addAll(recipes.values().stream().filter(Recipe::isVanilla).sorted(Comparator.comparing(Recipe::name)).collect(Collectors.toList()));
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

        if (!this.isVanilla()) {
            size += 100_000;
        }

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
        for (Recipe recipe : sorted) {
            if (Functions.getId(recipe.getItem()).equals(itemId))
                recipesFor.add(recipe);
        }
        return recipesFor;
    }

    public static List<Recipe> getRecipesWith(ItemStack item) {
        String itemId = Functions.getId(item);
        if (itemId.isEmpty()) return new ArrayList<>();
        List<Recipe> recipesFor = new ArrayList<>();
        for (Recipe recipe : sorted) {
            if (Functions.getId(recipe.getItem()).equals(itemId) || Arrays.stream(recipe.getItems().clone()).map(Functions::getId).collect(Collectors.toList()).contains(itemId))
                recipesFor.add(recipe);
        }
        return recipesFor;
    }

    public static List<Recipe> getRecipesCanCraft(PlayerSD player, int maxTimes) {
        List<Recipe> output = new ArrayList<>();
        for (Recipe recipe : sorted.stream().filter(r -> r.getSlotToUpgrade() >= 0).collect(Collectors.toList())) {
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
