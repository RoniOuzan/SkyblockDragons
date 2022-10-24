package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class SuperiorDragonHelmetSkin extends SkinMaterial {
    public SuperiorDragonHelmetSkin() {
        super("SUPERIOR_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Superior Dragon Skin",
                Rarity.EPIC,
                "SUPERIOR_DRAGON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("8bac9ff5-6006-3a10-99e4-55799153a1f8",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJhNzcwNDU5ZmE2ZDY1ZTIyYWE5NTY3OWQ5M2EyODcwYWFmZWE3MGY5ZjFjNmEwZjc4ZWI2NDFlOTI4OTAifX19Cg"
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
