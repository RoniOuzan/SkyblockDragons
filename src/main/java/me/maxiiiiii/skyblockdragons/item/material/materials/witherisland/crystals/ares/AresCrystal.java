package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.ares;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class AresCrystal extends CrystalMaterial {
    public AresCrystal(int level) {
        super(new Crystal(CrystalType.ARES, level), ItemFamily.ARES);
    }
}
