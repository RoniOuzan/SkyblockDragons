package me.maxiiiiii.skyblockdragons.item.material.materials.witherisland.crystals.hermes;

import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.material.types.CrystalMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;

public abstract class HermesCrystal extends CrystalMaterial {
    public HermesCrystal(int level) {
        super(new Crystal(CrystalType.HERMES, level), ItemFamily.HERMES);
    }
}
