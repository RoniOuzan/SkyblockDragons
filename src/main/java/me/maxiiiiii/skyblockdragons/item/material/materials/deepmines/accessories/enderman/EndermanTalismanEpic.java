package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.enderman;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class EndermanTalismanEpic extends AccessoryMaterial {
    public EndermanTalismanEpic() {
        super("ENDERMAN_TALISMAN_EPIC",
                Material.SKULL_ITEM,
                ItemFamily.ENDERMAN,
                "Enderman Talisman",
                Rarity.EPIC,
                new Stats(0, 12, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("28308167-5368-4b40-92b0-7c97858acb4f",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzgxOTNkOWExOTY1ZjBkNGM5NDI2NzdmMWZjNjAwYjQxYzFkZmNjZGU1YmI2OGVlZGY2ODQ2NTA3MzFjY2RjNyJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
