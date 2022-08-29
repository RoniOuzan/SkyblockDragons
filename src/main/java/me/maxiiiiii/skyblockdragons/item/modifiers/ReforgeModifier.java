package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

public class ReforgeModifier extends ItemModifier {
    private final ReforgeType reforge;

    public ReforgeModifier(ReforgeType reforge) {
        super(ReforgeModifier.class);
        this.reforge = reforge;
    }

    public ReforgeModifier() {
        this(ReforgeType.NULL);
    }

    public ReforgeType get() {
        return this.reforge;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return new ReforgeModifier(Functions.getReforge(item));
    }
}
