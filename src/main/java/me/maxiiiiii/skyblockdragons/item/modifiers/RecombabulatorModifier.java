package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.item.Item;
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

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        nbt.setBoolean("RarityUpgraded", this.isRecombabulated);
    }

    public static RecombabulatorModifier getModifier(ItemStack item) {
        return Functions.isRecombed(item);
    }
}
