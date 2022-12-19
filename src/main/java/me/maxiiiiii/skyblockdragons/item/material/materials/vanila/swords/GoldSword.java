package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class GoldSword extends SwordMaterial {
    public GoldSword() {
        super("GOLD_SWORD",
                Material.GOLD_SWORD,
                ItemFamily.GOLD,
                "Gold Sword",
                Rarity.COMMON,
                new Stats(25, 0, 0, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
