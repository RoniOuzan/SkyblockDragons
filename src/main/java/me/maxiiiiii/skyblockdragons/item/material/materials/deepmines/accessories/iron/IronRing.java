package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class IronRing extends AccessoryMaterial {
    public IronRing() {
        super("IRON_RING",
                Material.SKULL_ITEM,
                ItemFamily.IRON,
                "Iron Ring",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("cc7b245c-78cb-481e-bd07-4e0b7cec1198",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUxMTNhMzEyODhlNGFjZGU0ZTdkZDc0MDMxYjY0NDdjYzZmZDBiNjE0YTc4YTE0YjNhMzMzNGE0ZTk2ZTU1NSJ9fX0="
        );
    }
}
