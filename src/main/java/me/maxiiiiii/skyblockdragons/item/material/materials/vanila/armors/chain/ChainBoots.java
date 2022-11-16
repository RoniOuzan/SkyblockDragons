package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.chain;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class ChainBoots extends ArmorMaterial {
    public ChainBoots() {
        super("CHAIN_BOOTS",
                Material.CHAINMAIL_BOOTS,
                ItemFamily.CHAIN,
                "Chain Boots",
                ItemType.BOOTS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0),
                ""
        );
    }
}
