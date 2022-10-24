package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.emerald;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class EmeraldTalisman extends AccessoryMaterial {
    public EmeraldTalisman() {
        super("EMERALD_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.EMERALD,
                "Emerald Talisman",
                Rarity.COMMON,
                new Stats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("1ae4ec85-0f5b-41c5-bd47-bbd8dd8a0cd5",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE0MGJhZWI5NmZlYTFiZDZlZTA2NDY5NmNkYjc0ZmZkMDhhNmY3YzQwNjE3ZDQ2MmU0ZTJkYThmYWFmNzNlNSJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
