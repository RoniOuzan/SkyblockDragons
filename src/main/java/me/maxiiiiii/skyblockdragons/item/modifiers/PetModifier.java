package me.maxiiiiii.skyblockdragons.item.modifiers;
//
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

    public static ItemModifier getModifier(ItemStack item) {
        return new PetModifier(PetSupplier.getPetSupplier(item));
    }
}
