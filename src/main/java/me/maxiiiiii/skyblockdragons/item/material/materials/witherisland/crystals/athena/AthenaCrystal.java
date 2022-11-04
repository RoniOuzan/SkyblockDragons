package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.athena;

import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class AthenaCrystal extends CrystalMaterial {
    public AthenaCrystal(int level) {
        super(CrystalType.ATHENA, ItemFamily.ATHENA, level);
    }
}
