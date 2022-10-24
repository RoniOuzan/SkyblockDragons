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

public class LuminateBoots extends ArmorMaterial {
    public LuminateBoots() {
        super("LUMINATE_BOOTS",
                Material.IRON_BOOTS,
                ItemFamily.LUMINATE,
                "Luminate Boots",
                ItemType.BOOTS,
                Rarity.RARE,
                new Stats(65, 55, 0, 20, 150, 35, 2),
                "",
                new DeeperMinesFullSet(15)
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
