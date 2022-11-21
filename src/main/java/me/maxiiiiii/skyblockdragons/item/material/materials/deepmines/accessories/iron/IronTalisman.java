package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.iron;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class IronTalisman extends AccessoryMaterial {
    public IronTalisman() {
        super("IRON_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.IRON,
                "Iron Talisman",
                Rarity.COMMON,
                new Stats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("f4fd5204-a7eb-433c-8718-e0600772bacf",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM4NWFhZWRkNzg0ZmFlZjhlOGY2Zjc4MmZhNDhkMDdjMmZjMmJiY2Y2ZmVhMWZiYzliOTg2MmQwNWQyMjhjMSJ9fX0="
        );
    }
}
