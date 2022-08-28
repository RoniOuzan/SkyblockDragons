package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

public class HotPotatoModifier extends ItemModifier {
    private final int amount;

    public HotPotatoModifier(int amount) {
        super(HotPotatoModifier.class);
        this.amount = amount;
    }

    public HotPotatoModifier() {
        this(0);
    }

    public static ItemModifier getModifier(ItemStack item) {
        return Functions.getHotPotato(item);
    }

    public int get() {
        return amount;
    }
}
