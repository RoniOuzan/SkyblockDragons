package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.reforgestones;

import me.maxiiiiii.skyblockdragons.item.material.types.ReforgeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class PrecursorGear extends ReforgeMaterial {
    public PrecursorGear() {
        super("PRECURSOR_GEAR",
                Material.SKULL_ITEM,
                ItemFamily.REFORGE_STONE,
                "Precursor Gear",
                Rarity.EPIC,
                "Ancient"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("62ffd058-94c8-3b63-b027-5e9f4d52b78e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRmODkwOTQ5OGMyNWY2ZTc1ZWYxOWUzNzZhN2Y4NGY2MWFmMjM0NTI1ZDYzOWJhNDYzZjk5MWY0YzgyZDAifX19"
        );
    }
}
