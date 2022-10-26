package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.diamond;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DiamondTalisman extends AccessoryMaterial {
    public DiamondTalisman() {
        super("DIAMOND_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.DIAMOND,
                "Diamond Talisman",
                Rarity.COMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("9fa6fe15-2b2e-445e-b45d-40b85115a557",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMzYjZjOTA3ZjFjMmExYWU1NGY5MGFhZmJjOWU1NjFmMmY0ZGQ0ZWM0YjczZTU2ZDU0OTU1YmMxZGZjYzJhMCJ9fX0="
        );
    }
}
