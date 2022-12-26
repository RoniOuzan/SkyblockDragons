package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.drops;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class PigmanTalisman extends AccessoryMaterial {
    public PigmanTalisman() {
        super("PIGMAN_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.PIGMAN,
                "Pigman Talisman",
                Rarity.UNCOMMON,
                new DamageStats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("b9d5b95a-237c-461e-a316-e1ede14ab301",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTVmYjJkZjc1NGM5OGI3NDJkMzVlN2I4MWExZWVhYzlkMzdjNjlmYzhjZmVjZDNlOTFjNjc5ODM1MTZmIn19fQ"
        );
    }
}
