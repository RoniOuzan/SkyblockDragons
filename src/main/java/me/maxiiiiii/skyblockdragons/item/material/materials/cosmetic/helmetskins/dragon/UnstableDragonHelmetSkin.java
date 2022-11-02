package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class UnstableDragonHelmetSkin extends SkinMaterial {
    public UnstableDragonHelmetSkin() {
        super("UNSTABLE_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Unstable Dragon Skin",
                Rarity.EPIC,
                "ec1229d1-f6cc-4734-974a-7eaa2a7262b6",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhMDIzNjM2YTdmMDMwMGE3NGNjNTBkM2U0ZGJiODFhN2JjMTQ4NWYwZGFmNmU5YTdjMGY0ZDhhMmUzYTE4ZiJ9fX0="
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("ec1229d1-f6cc-4734-974a-7eaa2a7262b6",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhMDIzNjM2YTdmMDMwMGE3NGNjNTBkM2U0ZGJiODFhN2JjMTQ4NWYwZGFmNmU5YTdjMGY0ZDhhMmUzYTE4ZiJ9fX0="
        );
    }
}
