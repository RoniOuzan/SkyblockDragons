package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class ProtectorDragonHelmetSkin extends SkinMaterial {
    public ProtectorDragonHelmetSkin() {
        super("PROTECTOR_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Protector Dragon Skin",
                Rarity.EPIC,
                "PROTECTOR_DRAGON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("46130034-6f49-4dd9-a494-5444d2add6af",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTc5YzkxNDQ0OTZkMjFmMzMzNGIzMjQzN2RlZWI2MzM3OTc2NjM5OWQwYTkyYmMwNjcxMmJhNTkyYmMyMzdlNiJ9fX0="
        );
    }
}
