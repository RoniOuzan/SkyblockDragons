package me.maxiiiiii.skyblockdragons.item.reforge;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.item.modifiers.ReforgeModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
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

    public ItemStack apply(PlayerSD player) {
        NBTItem nbtItem = new NBTItem(this.item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (!nbt.getString("Reforge").equals(this.reforgeType.toString().trim())) {
            nbt.setString("Reforge", this.reforgeType.toString().trim());
            item = new Item(player, this.item, ItemModifiers.getModifiers(item).getOverrided(new ReforgeModifier(getReforge(nbt.getString("Reforge")))).toArray());
        }
        return item;
    }
}
