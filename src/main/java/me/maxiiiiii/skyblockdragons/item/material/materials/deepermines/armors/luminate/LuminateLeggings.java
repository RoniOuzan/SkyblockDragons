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

public class LuminateLeggings extends ArmorMaterial {
    public LuminateLeggings() {
        super("LUMINATE_LEGGINGS",
                Material.CHAINMAIL_LEGGINGS,
                ItemFamily.LUMINATE,
                "Luminate Leggings",
                ItemType.LEGGINGS,
                Rarity.RARE,
                new Stats(80, 65, 0, 20, 120, 45, 2),
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
