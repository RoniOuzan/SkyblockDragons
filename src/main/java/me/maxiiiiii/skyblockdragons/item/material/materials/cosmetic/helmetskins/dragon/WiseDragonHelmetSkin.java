package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class WiseDragonHelmetSkin extends SkinMaterial {
    public WiseDragonHelmetSkin() {
        super("WISE_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Wise Dragon Skin",
                Rarity.EPIC,
                "WISE_DRAGON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("2e3221d0-df79-438a-812a-d060e57ee980",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRjNjgzNGM4YmYwYjgxZjkyY2YwZGE0YmQ5OWFkNzY0MTU0ZDUzNjhhMzhlMDNjODVhZGRhMTBlNTE4ODkwYSJ9fX0="
        );
    }
}
