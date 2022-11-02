package me.maxiiiiii.skyblockdragons.item.material.materials.vanila.swords;

import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class IronSword extends SwordMaterial {
    public IronSword() {
        super("IRON_SWORD",
                Material.IRON_SWORD,
                ItemFamily.IRON,
                "Iron Sword",
                Rarity.COMMON,
                new Stats(10, 5, 0, 0, 0,0 ,0, 0, 0, 0),
                ""
        );
    }
}
