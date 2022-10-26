package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class WoodSpade extends MiningMaterial {
    public WoodSpade() {
        super("WOOD_SPADE",
                Material.WOOD_SPADE,
                ItemFamily.WOOD,
                "Wood Spade",
                ItemType.SPADE,
                Rarity.COMMON,
                new Stats(50, 0),
                1,
                ""
        );
    }
}
