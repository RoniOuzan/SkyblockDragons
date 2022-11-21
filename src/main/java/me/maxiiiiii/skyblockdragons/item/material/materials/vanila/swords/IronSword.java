package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class IronSword extends SwordMaterial {
    public IronSword() {
        super("IRON_SWORD",
                Material.IRON_SWORD,
                ItemFamily.IRON,
                "Iron Sword",
                Rarity.COMMON,
                new Stats(10, 5, 0, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
