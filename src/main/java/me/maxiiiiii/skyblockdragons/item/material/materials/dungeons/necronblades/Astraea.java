package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.necronblades;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class Astraea extends NecronBladeMaterial {
    public Astraea() {
        super("ASTRAEA",
                Material.IRON_SWORD,
                ItemFamily.NECRON_BLADE,
                "Astraea",
                Rarity.LEGENDARY,
                new Stats(270, 150, 0, 0, 0, 30, 0, 250, 0, 50),
                NecronBladeMaterial.NecronBladeType.ASTRAEA
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
