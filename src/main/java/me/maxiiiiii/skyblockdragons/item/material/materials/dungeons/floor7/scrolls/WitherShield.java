package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.scrolls;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class WitherShield extends NecronBladeMaterial.NecronBladeScroll {
    public WitherShield() {
        super("WITHER_SHIELD",
                Material.BOOK_AND_QUILL,
                "Wither Shield",
                Rarity.EPIC,
                "",
                NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility()
        );
    }
}
