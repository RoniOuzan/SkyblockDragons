package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class PigmanHelmet extends ArmorMaterial {
    public PigmanHelmet() {
        super("PIGMAN_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.PIGMAN,
                "Pigman Helmet",
                ItemType.HELMET,
                Rarity.UNCOMMON,
                new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10),
                "",
                new PigmanFullSetBonus()
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("a94086e2-ae98-42c2-a96b-d7b548e3ae2a",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg1Mzg2Zjc4MDMzNDNlNWU2YjdlOGVlMDYxNjc3ZmYxN2U0ZjU2MTAwNTQ3OWQzOTQ3MmIyNjU3ZTA4ODQyZSJ9fX0="
        );
    }
}
