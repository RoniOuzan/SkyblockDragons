package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class StrongDragonHelmetSkin extends SkinMaterial {
    public StrongDragonHelmetSkin() {
        super("STRONG_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Strong Dragon Skin",
                Rarity.EPIC,
                "STRONG_DRAGON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("9110158d-ad70-4ce8-b58e-c5dcec6828c7",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWVjMTBiMGRjZWI1YmIzNGFmMmNiOTUzNzMzZjAxZjIwYmIzYWFmZDVmMjFmZTBlYWRmMWNjNGNlN2Y0ZGFiNCJ9fX0="
        );
    }
}
