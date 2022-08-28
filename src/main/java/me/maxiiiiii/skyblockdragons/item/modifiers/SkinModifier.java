package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;

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
}
