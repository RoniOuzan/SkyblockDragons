package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.accessories.king;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class KingTalisman extends AccessoryMaterial {
    public KingTalisman() {
        super("KING_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.KING,
                "King of the Kingdom",
                Rarity.RARE,
                new DamageStats(0, 8, 4, 1, 0, 0),
                "You pissed off the King"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("53ad9b08-b403-4241-ab0f-4323326bfb9d",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZkYTQ4OGNjZGViOTdjMzYxYTA4Mzc2MGUxZGUxMDIzMGQwMjQ4NDg1OTdjNWY3YzA3OTUyODYzZjk1YTA5NyJ9fX0="
        );
    }
}
