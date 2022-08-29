package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

public class SkinModifier extends ItemModifier {
    private final SkinMaterial skin;

    public SkinModifier(SkinMaterial skin) {
        super(SkinModifier.class);
        this.skin = skin;
    }

    public SkinModifier() {
        this(SkinMaterial.NULL);
    }

    public SkinMaterial get() {
        return this.skin;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return new SkinModifier(Functions.getSkin(item));
    }
}
