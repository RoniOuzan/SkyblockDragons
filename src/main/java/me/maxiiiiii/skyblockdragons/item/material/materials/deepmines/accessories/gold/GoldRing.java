package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.gold;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldRing extends AccessoryMaterial {
    public GoldRing() {
        super("GOLD_RING",
                Material.SKULL_ITEM,
                ItemFamily.GOLD,
                "Gold Ring",
                Rarity.UNCOMMON,
                new Stats(0, 4, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("0042c4ee-9501-4b88-815b-efe56bab5031",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBjOWM1ZWIxYmZjZjk5YTBkOGM2ZTgyMmM1MDBjOTk3MTAzNjU3NWY0MDExNzVkNGEwNDAwZWMzMmEzYTFhYyJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
