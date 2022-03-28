package me.maxiiiiii.skyblockdragons.item.reforge;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.item.Item;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class Reforge {
    private ItemStack item;
    private ReforgeType reforgeType;

    public Reforge(ItemStack item, ReforgeType reforgeType) {
        if (!Functions.getId(item).equals("")) {
            this.item = item;
        }
        this.reforgeType = reforgeType;
    }

    public ItemStack apply() {
        NBTItem nbtItem = new NBTItem(this.item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (!nbt.getString("Reforge").equals(this.reforgeType.toString().trim())) {
            nbt.setString("Reforge", this.reforgeType.toString().trim());
            item = new Item(Functions.getItemMaterial(this.item), getHotPotato(this.item), getReforge(nbt.getString("Reforge")), isRecombed(this.item), getSkin(this.item), getEnchants(this.item), getNecronScrolls(this.item));
        }
        return item;
    }
}
