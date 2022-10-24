package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.necronblades;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.NecronBladeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class NecronBlade extends NecronBladeMaterial {
    public NecronBlade() {
        super("NECRON_BLADE",
                Material.IRON_SWORD,
                ItemFamily.NECRON_BLADE,
                "Necron's Blade (Unrefined)",
                Rarity.LEGENDARY,
                new Stats(260, 110, 0, 0, 0, 30, 0, 0, 0, 50),
                NecronBladeMaterial.NecronBladeType.NECRON_BLADE
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
