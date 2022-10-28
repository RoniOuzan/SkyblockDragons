package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.pickaxes;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class GoldPickaxe extends MiningMaterial {
    public GoldPickaxe() {
        super("GOLD_PICKAXE",
                Material.GOLD_PICKAXE,
                ItemFamily.GOLD,
                "Gold Pickaxe",
                ItemType.PICKAXE,
                Rarity.COMMON,
                new Stats(300, 0),
                1,
                ""
        );
    }
}