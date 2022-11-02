package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.reforgestones;

import me.maxiiiiii.skyblockdragons.item.material.types.ReforgeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DragonClaw extends ReforgeMaterial {
    public DragonClaw() {
        super("DRAGON_CLAW",
                Material.SKULL_ITEM,
                ItemFamily.REFORGE_STONE,
                "Dragon Claw",
                Rarity.RARE,
                "Fabled"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("95cc2473-d382-4f60-aa77-549071e339e4",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc0NGM4MTA5OWMxYWViZjIwZjY3N2ZhZWQyNGNhY2U1MjBhMjk0Y2Y0NmJkZWI2YTI1N2Y0MzZhMzIzYTFkOCJ9fX0="
        );
    }
}
