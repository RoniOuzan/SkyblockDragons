package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.wither;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class NecronHelmetSkin extends SkinMaterial {
    public NecronHelmetSkin() {
        super("NECRON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Psydra Necron Skin",
                Rarity.EPIC,
                "NECRON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("95b93eb6-4ed9-4841-81ee-729f6d1516e4",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYzZWEzZmE1NGE4ZDcwMTQ5NjkyMjY2NmNiMjQyNTg5MGNjZGRhYzY1OTkzYjVkNzViMjY0MmJmN2U2YWY4ZCJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
