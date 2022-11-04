package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.coal;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class CoalTalisman extends AccessoryMaterial {
    public CoalTalisman() {
        super("COAL_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.COAL,
                "Coal Talisman",
                Rarity.COMMON,
                new Stats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("305b2484-1620-44de-85ef-42816b89dfb3",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzExMTA3ZjcwZjhjYTA0NzRmMDIzMjQzYmQzODJiYmQ2YjQxNDlhZWY0ZjQyYjI1ZGRiYmNmZWM4Nzk4YjRkYyJ9fX0="
        );
    }
}
