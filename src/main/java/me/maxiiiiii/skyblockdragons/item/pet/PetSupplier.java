package me.maxiiiiii.skyblockdragons.item.pet;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@ToString
public class PetSupplier {
    private Rarity rarity;
    private int level;
    private double currentXp;

    public PetSupplier(Rarity rarity, int level, double currentXp) {
        this.rarity = rarity;
        this.level = level;
        this.currentXp = currentXp;
    }

    public PetSupplier() {
        this(Rarity.NONE, 0, 0);
    }

    public void levelUp() {
        this.setLevel(this.level + 1);
    }

    public void giveXp(double amount) {
        this.currentXp += amount;
    }

    public static PetSupplier getPetSupplier(ItemStack item) {
        if (Items.get(item) instanceof PetMaterial) {
            return new PetSupplier(
                    Rarity.getRarity(item),
                    getLevel(item),
                    getCurrentXp(item)
            );
        }
        return new PetSupplier();
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
