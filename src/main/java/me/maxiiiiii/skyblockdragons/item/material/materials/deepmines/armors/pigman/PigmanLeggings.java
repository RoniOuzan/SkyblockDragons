package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class PigmanLeggings extends ArmorMaterial {
    public PigmanLeggings() {
        super("PIGMAN_LEGGINGS",
                Material.GOLD_LEGGINGS,
                ItemFamily.PIGMAN,
                "Pigman Leggings",
                ItemType.LEGGINGS,
                Rarity.UNCOMMON,
                new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10),
                "",
                new PigmanFullSetBonus()
        );
    }
}
