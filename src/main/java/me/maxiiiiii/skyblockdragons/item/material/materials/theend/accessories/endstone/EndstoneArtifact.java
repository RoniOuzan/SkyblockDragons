package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.endstone;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class EndstoneArtifact extends AccessoryMaterial {
    public EndstoneArtifact() {
        super("ENDSTONE_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.ENDSTONE,
                "Endstone Talisman",
                Rarity.COMMON,
                new DamageStats(0, 6, 6, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("68c5a269-fec3-4080-b5f9-926ac0e7026e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTYzODhmNzQ2ZTZjNTMxNWYzNDQ3MWM2ZTVmMWNmOTRhZDc2OTY3ZGIzNjAwYWUxMjEyZmYzMDVmODI0MmE3MiJ9fX0="
        );
    }
}
