package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.obsidian;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class ObsidianTalisman extends AccessoryMaterial {
    public ObsidianTalisman() {
        super("OBSIDIAN_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.OBSIDIAN,
                "Obsidian Talisman",
                Rarity.COMMON,
                new Stats(0, 4, 4, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("4871fc40-b2c7-431d-9eb8-b54cd666dca7",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MGI4N2Q1MjI3MWQyYTc1NWRlZGM4Mjg3N2UwZWQzZGY2N2RjYzQyZWE0NzllYzE0NjE3NmIwMjc3OWE1In19fQ=="
        );
    }
}
