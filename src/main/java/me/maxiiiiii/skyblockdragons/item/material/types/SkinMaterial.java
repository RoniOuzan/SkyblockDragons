package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class SkinMaterial extends ItemMaterial {
    public static final SkinMaterial NULL = new NullSkinMaterial();

    protected final List<String> canApplyTo;

    public SkinMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, String... canApplyTo) {
        super(itemID, material, family, name, ItemType.SKIN, rarity);
        this.canApplyTo = Arrays.asList(canApplyTo);
    }

    @MaterialUnregisters
    private static class NullSkinMaterial extends SkinMaterial {
        public NullSkinMaterial() {
            super("NULL_SKIN",
                    Material.BARRIER,
                    ItemFamily.NULL,
                    "Null",
                    Rarity.SPECIAL
            );
        }
    }
}
