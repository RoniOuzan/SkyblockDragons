package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.demeter;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class DemeterCrystal extends CrystalMaterial {
    public DemeterCrystal(int level) {
        super(new Crystal(CrystalType.DEMETER, level), ItemFamily.DEMETER);
    }
}
