package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

public class RecombabulatorModifier extends ItemModifier {
    private final boolean isRecombabulated;

    public RecombabulatorModifier(boolean isRecombabulated) {
        super(RecombabulatorModifier.class);
        this.isRecombabulated = isRecombabulated;
    }

    public RecombabulatorModifier() {
        this(false);
    }

    public boolean get() {
        return this.isRecombabulated;
    }

    public static RecombabulatorModifier getModifier(ItemStack item) {
        return Functions.isRecombed(item);
    }
}
