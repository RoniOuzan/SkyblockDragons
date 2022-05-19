package me.maxiiiiii.skyblockdragons.worlds.deepermines.forge;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Time;
import org.bukkit.inventory.ItemStack;

@Getter
public class ForgeSlot {
    private final PlayerSD player;
    private final ForgeRecipe recipe;
    private final ItemStack fromItem;
    private final Time time;

    public ForgeSlot(PlayerSD player, ForgeRecipe recipe, ItemStack fromItem, Time time) {
        this.player = player;
        this.recipe = recipe;
        this.fromItem = fromItem;
        this.time = time;
    }

    public ForgeSlot(PlayerSD player, ForgeRecipe recipe, ItemStack fromItem) {
        this(player, recipe, fromItem, new Time());
    }

    public Item getItem() {
        return new Item(player, this.recipe.getItem().getMaterial(), fromItem);
    }
}
