package me.maxiiiiii.skyblockdragons.item.material.materials.griffin;

import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class GriffinFeather extends ItemMaterial {
    public GriffinFeather() {
        super("GRIFFIN_FEATHER",
                Material.FEATHER,
                ItemFamily.NULL,
                "Griffin Feather",
                ItemType.ITEM,
                Rarity.RARE);
    }
}
