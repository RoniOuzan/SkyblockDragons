package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.spade;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class DiamondSpade extends MiningMaterial {
    public DiamondSpade() {
        super("DIAMOND_SPADE",
                Material.DIAMOND_SPADE,
                ItemFamily.DIAMOND,
                "Diamond Spade",
                ItemType.SPADE,
                Rarity.UNCOMMON,
                new Stats(230, 0),
                1,
                ""
        );
    }
}
