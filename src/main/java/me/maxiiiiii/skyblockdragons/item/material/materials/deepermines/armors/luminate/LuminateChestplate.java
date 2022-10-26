package me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.luminate;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.deepermines.armors.DeeperMinesFullSet;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LuminateChestplate extends ArmorMaterial {
    public LuminateChestplate() {
        super("LUMINATE_CHESTPLATE",
                Material.IRON_CHESTPLATE,
                ItemFamily.LUMINATE,
                "Luminate Chestplate",
                ItemType.CHESTPLATE,
                Rarity.RARE,
                new Stats(85, 70, 0, 20, 125, 50, 2),
                "",
                new DeeperMinesFullSet(15)
        );
    }
}
