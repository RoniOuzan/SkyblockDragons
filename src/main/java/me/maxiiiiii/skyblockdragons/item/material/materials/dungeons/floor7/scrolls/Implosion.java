package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.scrolls;

import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class Implosion extends NecronBladeMaterial.NecronBladeScroll {
    public Implosion() {
        super("IMPLOSION",
                Material.BOOK_AND_QUILL,
                "Implosion",
                Rarity.EPIC,
                "",
                NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility()
        );
    }
}
