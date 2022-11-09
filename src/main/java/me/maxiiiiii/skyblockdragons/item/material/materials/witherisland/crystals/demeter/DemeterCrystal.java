package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.demeter;

import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class DemeterCrystal extends CrystalMaterial {
    public DemeterCrystal(int level) {
        super(CrystalType.DEMETER, ItemFamily.DERNIC, level);
    }
}
