package me.maxiiiiii.skyblockdragons.item.material.materials.cosmetic.helmetskins.dragon;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SkinMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class OldDragonHelmetSkin extends SkinMaterial {
    public OldDragonHelmetSkin() {
        super("OLD_DRAGON_HELMET_SKIN",
                Material.SKULL_ITEM,
                ItemFamily.SKIN,
                "Baby Old Dragon Skin",
                Rarity.EPIC,
                "OLD_DRAGON_HELMET"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("5d73f057-312a-4ffb-b2b0-bef5fe120482",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVhZjgzNmI3MTgzNTU0MjY5NjQ3ODI3Mjg5YzY3YTM4NTVlOGQwYjM0NTcxOTMyODE4OGRlZmRiZDA1YTY0YiJ9fX0="
        );
    }
}
