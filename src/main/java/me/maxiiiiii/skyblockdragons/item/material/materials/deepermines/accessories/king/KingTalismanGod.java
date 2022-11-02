package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.accessories.king;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class KingTalismanGod extends AccessoryMaterial {
    public KingTalismanGod() {
        super("KING_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.KING,
                "King of the Kingdom",
                Rarity.RARE,
                new Stats(0, 10, 3, 2, 0, 0),
                "You pissed off GOD!"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("ff3fc337-69e0-418e-8f44-aa1ef8fe42ac",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODgzZmJjODZhOGI2Y2E5MTJiM2E5ZDFkMjQwODc2ODA1Mjk5NTY0MWJhODllMjZhMWZmNjAwNjI3ZDgzYjIzOSJ9fX0="
        );
    }
}
