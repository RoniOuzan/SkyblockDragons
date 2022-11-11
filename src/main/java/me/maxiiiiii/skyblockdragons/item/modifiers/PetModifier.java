package me.maxiiiiii.skyblockdragons.item.modifiers;
//

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.pet.PetSupplier;
import org.bukkit.inventory.ItemStack;

public class PetModifier extends ItemModifier {
    private final PetSupplier pet;

    public PetModifier(PetSupplier pet) {
        super(PetModifier.class);
        this.pet = pet;
    }

    public PetModifier() {
        this(new PetSupplier());
    }

    public PetSupplier get() {
        return pet;
    }

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        if (pet.getRarity() != Rarity.NONE) {
            NBTCompound petNBT = nbt.addCompound("Pet");
            petNBT.setInteger("Level", pet.getLevel());
            petNBT.setDouble("CurrentXp", pet.getCurrentXp());
        }
    }

    public static PetModifier getModifier(ItemStack item) {
        return new PetModifier(PetSupplier.getPetSupplier(item));
    }
}
