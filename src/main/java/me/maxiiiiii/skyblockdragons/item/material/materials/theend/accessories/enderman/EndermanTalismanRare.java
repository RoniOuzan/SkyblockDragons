package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.enderman;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class EndermanTalismanRare extends AccessoryMaterial {
    public EndermanTalismanRare() {
        super("ENDERMAN_TALISMAN_RARE",
                Material.SKULL_ITEM,
                ItemFamily.ENDERMAN,
                "Enderman Talisman",
                Rarity.RARE,
                new Stats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("8eb28675-93a9-4f4c-8983-fedd8787a6f3",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMzE5N2UyZjMzYzEyNWZkOThmY2E4NmQ1ZDJhZDhmOGIyY2JjMjYxZGZjMGU0MDQ0OGU4ZDFlMzE3N2VlMCJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
