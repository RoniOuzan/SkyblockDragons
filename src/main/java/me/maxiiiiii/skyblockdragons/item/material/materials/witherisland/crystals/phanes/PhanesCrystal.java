package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.phanes;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class PhanesCrystal extends CrystalMaterial {
    public PhanesCrystal(int level) {
        super(new Crystal(CrystalType.PHANES, level), ItemFamily.PHANES);
    }
}
