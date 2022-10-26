package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories.enderman;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class EndermanTalismanLegendary extends AccessoryMaterial {
    public EndermanTalismanLegendary() {
        super("ENDERMAN_TALISMAN_LEGENDARY",
                Material.SKULL_ITEM,
                ItemFamily.ENDERMAN,
                "Enderman Talisman",
                Rarity.LEGENDARY,
                new Stats(0, 16, 8, 2, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("771ee983-f259-46ac-936f-38251b1c7c96",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWIzMzBmYmNlMjA4NWY2MGRmMTZjYmM5YTdhMzg5NDgzNjJiYzlmMGFhNDU0YjI5MDU2MWZlNDcxZGFlMDU3OCJ9fX0="
        );
    }
}
