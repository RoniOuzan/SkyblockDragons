package me.maxiiiiii.skyblockdragons.item.pet;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

@Getter
public class PetSupplier {
    private final Rarity rarity;
    private final int level;
    private final double currentXp;

    public PetSupplier(Rarity rarity, int level, double currentXp) {
        this.rarity = rarity;
        this.level = level;
        this.currentXp = currentXp;
    }

    public PetSupplier() {
        this(Rarity.NONE, 0, 0);
    }

    public static PetSupplier getPetSupplier(ItemStack item) {
        return new PetSupplier(
                Rarity.getRarity(item),
                getLevel(item),
                getCurrentXp(item)
        );
    }

    public static int getLevel(ItemStack item) {
        if (Functions.isNotAir(item)) {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            if (nbt != null) {
                NBTCompound petNBT = nbt.getCompound("Pet");
                if (petNBT != null) {
                    return petNBT.getInteger("Level");
                }
            }
        }

        return 0;
    }

    public static double getCurrentXp(ItemStack item) {
        if (Functions.isNotAir(item)) {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            if (nbt != null) {
                NBTCompound petNBT = nbt.getCompound("Pet");
                if (petNBT != null) {
                    return petNBT.getDouble("CurrentXp");
                }
            }
        }

        return 0;
    }
}
