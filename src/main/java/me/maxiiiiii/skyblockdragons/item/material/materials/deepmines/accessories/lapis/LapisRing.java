package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.lapis;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LapisRing extends AccessoryMaterial {
    public LapisRing() {
        super("LAPIS_RING",
                Material.SKULL_ITEM,
                ItemFamily.LAPIS,
                "Lapis Ring",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("7b46c83a-0cb3-4aa1-bd71-8154f3654043",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM1YmY0OWZlYmVjOTU1ZWIzYjMyOGNlMTcwOWY3YmI5ZjQ2OWMyN2E3ZmY0MGQxMzA2OTQyYzA4Zjk2MjUwZSJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
