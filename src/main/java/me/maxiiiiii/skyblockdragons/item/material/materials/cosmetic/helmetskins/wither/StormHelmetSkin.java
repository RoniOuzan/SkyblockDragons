package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.wither;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class StormHelmetSkin extends SkinMaterial {
    public StormHelmetSkin() {
        super("STORM_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Psydra Storm Skin",
                Rarity.EPIC,
                "STORM_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("4e279894-281e-4ab1-806e-3b3728f899d0",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5OTM5ZWE1ZTc2NTVlNjE2YmU4NmEzYTQwMDEzMWMyYzljNzQ1ZTdlMzhlZDBkMWVlMjk4N2JiZTc1YTQ0YSJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
