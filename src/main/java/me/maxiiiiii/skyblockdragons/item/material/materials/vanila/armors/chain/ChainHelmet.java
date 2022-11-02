package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.armors.chain;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;

import org.bukkit.Material;

public class ChainHelmet extends ArmorMaterial {
    public ChainHelmet() {
        super("CHAIN_HELMET",
                Material.CHAINMAIL_HELMET,
                ItemFamily.CHAIN,
                "Chain Helmet",
                ItemType.HELMET,
                Rarity.COMMON,
                new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0),
                ""
        );
    }
}
