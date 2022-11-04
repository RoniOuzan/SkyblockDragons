package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class WoodSword extends SwordMaterial {
    public WoodSword() {
        super("WOOD_SWORD",
                Material.WOOD_SWORD,
                ItemFamily.WOOD,
                "Wood Sword",
                Rarity.COMMON,
                new Stats(5, 0, 0, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
