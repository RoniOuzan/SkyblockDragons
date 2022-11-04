package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystals;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CrystalModifier extends ItemModifier {
    private final Crystals crystals;

    public CrystalModifier(Crystals crystals) {
        super(CrystalModifier.class);
        this.crystals = crystals;
    }

    public CrystalModifier(Crystals crystals, Crystal... crystalsToAdd) {
        this(crystals);
        Arrays.stream(crystalsToAdd).forEach(this.crystals::add);
    }

    public CrystalModifier(Crystal... crystals) {
        this(new Crystals(crystals));
    }

    public CrystalModifier() {
        this(new Crystals());
    }

    public Crystals get() {
        return crystals;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return Functions.getEnchants(item);
    }
}
