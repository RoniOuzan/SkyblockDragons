package me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge;

import lombok.Getter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Time;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public enum ForgeRecipe {
    COBALT_DRILL(Items.get("COBALT_DRILL"), Arrays.asList(new Item(Items.get("DIAMOND_PICKAXE")), new Item(Items.get("COBALT"), 128)), Time.Unit.SECOND.from(30));

    private final Item item;
    private final List<Item> items;
    private final double duration; // ms
    private final List<Requirement> requirements;

    ForgeRecipe(Item item, List<Item> items, double duration, Requirement... requirements) {
        this.item = item;
        this.items = items;
        this.duration = duration;
        this.requirements = Arrays.asList(requirements);
    }

    ForgeRecipe(ItemMaterial material, List<Item> items, double duration, Requirement... requirements) {
        this(new Item(material), items, duration, requirements);
    }

    public static class View extends PageMenu {
        private final int slot;

        public View(PlayerSD player, int slot) {
            super(player, "Item Forging (Slot #" + slot + ")", 6, InventoryGlassType.ALL, getRecipes(player), false);
            this.slot = slot;
        }

        public static List<ItemStack> getRecipes(PlayerSD player) {
            return Arrays.stream(ForgeRecipe.values()).map(r -> {
                if (r.requirements.stream().allMatch(req -> req.hasRequirement(player)))
                    return Functions.addLine(addNBT(r.item.clone(), "FORGE_" + r.name()), "", ChatColor.YELLOW + "Items Required", r.items.stream().map(i -> "  " + i.getItemMeta().getDisplayName() + " " + ChatColor.GRAY + "x" + i.getAmount()).collect(Collectors.toList()), "", ChatColor.YELLOW + "Duration: " + Time.toString(r.duration), "", ChatColor.YELLOW + "Click to start!");
                return createItem(Material.STAINED_GLASS_PANE, 0, ChatColor.RED + "???", "NO_REQUIREMENT", r.requirements.stream().map(Requirement::toString).collect(Collectors.toList()));
            }).collect(Collectors.toList());
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).contains("FORGE_")) {
                ForgeRecipe recipe = ForgeRecipe.valueOf(getNBT(e.getCurrentItem()).replace("FORGE_", ""));

                if (recipe.getItems().stream().allMatch(i -> player.hasItem(i.getMaterial(), i.getAmount()))) {
                    this.player.getForge().newSlot(slot, recipe, player.getItemStack(recipe.getItems().get(0).getMaterial(), recipe.getItems().get(0).getAmount()));
                    for (Item item : recipe.getItems()) {
                        this.player.removeItems(item.getMaterial(), item.getAmount());
                    }
                    this.player.getForge().view();
                } else {
                    this.player.sendMessage(ChatColor.RED + "You don't have the items to forge this item!");
                }
            }
        }
    }
}
