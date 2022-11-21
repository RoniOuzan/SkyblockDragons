package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.item.Item;
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

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        if (this.skin instanceof SkinMaterial) {
            nbt.setString("Skin", this.skin.name());
        }
    }

    public static SkinModifier getModifier(ItemStack item) {
        return new SkinModifier(Functions.getSkin(item));
    }
}
