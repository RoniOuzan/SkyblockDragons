package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class StoneSword extends SwordMaterial {
    public StoneSword() {
        super("STONE_SWORD",
                Material.STONE_SWORD,
                ItemFamily.STONE,
                "Stone Sword",
                Rarity.COMMON,
                new Stats(5, 5, 0, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
