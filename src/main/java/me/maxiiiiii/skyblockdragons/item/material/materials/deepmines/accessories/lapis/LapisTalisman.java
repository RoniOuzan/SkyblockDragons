package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.lapis;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class LapisTalisman extends AccessoryMaterial {
    public LapisTalisman() {
        super("LAPIS_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.LAPIS,
                "Lapis Talisman",
                Rarity.COMMON,
                new DamageStats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("b87f063d-01af-4519-bc20-372885110df4",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEwMDFiNDI1MTExYmZlMGFjZmY3MTBhOGI0MWVhOTVlM2I5MzZhODVlNWJiNjUxNzE2MGJhYjU4N2U4ODcwZiJ9fX0="
        );
    }
}
