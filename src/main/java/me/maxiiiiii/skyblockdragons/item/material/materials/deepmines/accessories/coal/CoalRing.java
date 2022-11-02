package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.coal;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class CoalRing extends AccessoryMaterial {
    public CoalRing() {
        super("COAL_RING",
                Material.SKULL_ITEM,
                ItemFamily.COAL,
                "Coal Ring",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("e993c0e0-e185-4015-a955-36cb402c56bf",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWNmOTQ3ODRkN2MxZGM2YTkzYjQxM2JlNTJiNTRmYTc3YjdhNzI1ODY3NWU4NzAyMWIzNGVkMmJmNzUxZGIyMSJ9fX0="
        );
    }
}
