package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.redstone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class RedstoneRing extends AccessoryMaterial {
    public RedstoneRing() {
        super("REDSTONE_RING",
                Material.SKULL_ITEM,
                ItemFamily.REDSTONE,
                "Redstone Ring",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("c3bde4ec-3ac7-48b4-b341-a31bdee7d1db",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMzNjFhZjk2YWMyYzk5Nzc0NTA4Mzg0NDY0NTI3OTk4N2FjYTY1Njc5MDQ2ZTVhZGJhNzdmYzVmNGYyODFkMCJ9fX0="
        );
    }
}
