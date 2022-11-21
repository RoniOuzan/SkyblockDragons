package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.scrolls;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class WitherImpact extends NecronBladeMaterial.NecronBladeScroll {
    public WitherImpact() {
        super("WITHER_IMPACT",
                Material.BOOK_AND_QUILL,
                "Wither Impact",
                Rarity.EPIC,
                "",
                NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility(),
                NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility(),
                NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()
        );
    }
}
