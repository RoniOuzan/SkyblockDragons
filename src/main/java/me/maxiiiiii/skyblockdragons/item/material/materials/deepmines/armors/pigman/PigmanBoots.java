package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.armors.pigman;

import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.Material;

public class PigmanBoots extends ArmorMaterial {
    public PigmanBoots() {
        super("PIGMAN_BOOTS",
                Material.GOLD_BOOTS,
                ItemFamily.PIGMAN,
                "Pigman Boots",
                ItemType.BOOTS,
                Rarity.UNCOMMON,
                new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10),
                "",
                ItemFullSetBonus.PIGMAN_FULL_SET
        );
    }
}
