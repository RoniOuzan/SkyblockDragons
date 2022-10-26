package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.chain;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class ChainLeggings extends ArmorMaterial {
    public ChainLeggings() {
        super("CHAIN_LEGGINGS",
                Material.CHAINMAIL_LEGGINGS,
                ItemFamily.CHAIN,
                "Chain Leggings",
                ItemType.LEGGINGS,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0),
                ""
        );
    }
}
