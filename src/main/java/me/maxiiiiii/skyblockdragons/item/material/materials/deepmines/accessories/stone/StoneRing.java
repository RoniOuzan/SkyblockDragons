package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.stone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class StoneRing extends AccessoryMaterial {
    public StoneRing() {
        super("STONE_RING",
                Material.SKULL_ITEM,
                ItemFamily.STONE,
                "Stone Ring",
                Rarity.UNCOMMON,
                new DamageStats(0, 4, 4, 1, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("e775e040-a3f5-444c-a6a5-b9a1d368f4cf",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDNmMDg5YmZlODUyZGNhZTEzZTg3YTExYTNmZTczZTE0ZDkzY2Q2YzA5ZTBjNDUzMjQ0ZWVlZDJjNmRiYzk3In19fQ=="
        );
    }
}
