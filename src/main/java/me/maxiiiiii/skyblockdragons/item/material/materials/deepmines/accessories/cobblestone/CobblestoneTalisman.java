package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.cobblestone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class CobblestoneTalisman extends AccessoryMaterial {
    public CobblestoneTalisman() {
        super("COBBLESTONE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.COBBLESTONE,
                "Cobblestone Talisman",
                Rarity.COMMON,
                new DamageStats(0, 2, 2, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("a3804980-3c5b-4346-a989-f73f9cb422b3",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWU1NGFiYmM2NWIxM2E0MmMyOTU5MGEwY2Y5ZDNlMDA3MDJkMWU2MGQ5NzRmOTI4NmE3YzE3MjY3ZjIyODJjOSJ9fX0="
        );
    }
}
