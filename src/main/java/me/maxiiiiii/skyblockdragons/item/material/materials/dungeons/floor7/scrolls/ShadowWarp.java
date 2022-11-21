package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.scrolls;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class ShadowWarp extends NecronBladeMaterial.NecronBladeScroll {
    public ShadowWarp() {
        super("SHADOW_WARP",
                Material.BOOK_AND_QUILL,
                "Shadow Warp",
                Rarity.EPIC,
                "",
                NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()
        );
    }
}
