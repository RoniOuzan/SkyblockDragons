package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.enderman;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class EndermanTalismanCommon extends AccessoryMaterial {
    public EndermanTalismanCommon() {
        super("ENDERMAN_TALISMAN_COMMON",
                Material.SKULL_ITEM,
                ItemFamily.ENDERMAN,
                "Enderman Talisman",
                Rarity.COMMON,
                new DamageStats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("0575cc58-309c-4598-9876-e3df5b8f4315",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA0ZWZlMTU1ZWNhMjFhZDhlZmNiYTlkMThmMTBhMjU5MTA2MTI2NDgwYWY0MzlkYjA4OTVhZjQyOWY5Zjc2NCJ9fX0="
        );
    }
}
