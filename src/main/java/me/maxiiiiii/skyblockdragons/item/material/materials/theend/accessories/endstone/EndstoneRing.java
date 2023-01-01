package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.endstone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class EndstoneRing extends AccessoryMaterial {
    public EndstoneRing() {
        super("ENDSTONE_RING",
                Material.SKULL_ITEM,
                ItemFamily.ENDSTONE,
                "Endstone Ring",
                Rarity.COMMON,
                new DamageStats(0, 5, 3, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("7ab52afd-eb69-4775-895b-6c094f912e1b",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTJkZmI0YjBmZWQxODI3YWRhMjkyZmU5MTdmY2E2YWM4MDBhZmNmNTZmOGU0YTI5MGIyYmQ5Y2FhM2ExMmMzNiJ9fX0="
        );
    }
}
