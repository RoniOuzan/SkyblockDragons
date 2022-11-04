package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class DiamondSword extends SwordMaterial {
    public DiamondSword() {
        super("DIAMOND_SWORD",
                Material.DIAMOND_SWORD,
                ItemFamily.DIAMOND,
                "Diamond Sword",
                Rarity.UNCOMMON,
                new Stats(35, 25, 5, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
