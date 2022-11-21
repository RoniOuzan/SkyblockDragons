package me.maxiiiiii.skyblockdragons.item.material.materials.griffin.spades;

import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class MythologsSpadeRare extends ToolMaterial {
    public MythologsSpadeRare() {
        super("MYTHOLOGS_SPADE_RARE",
                Material.STONE_SPADE,
                ItemFamily.MYTHOLOGS_SPADE,
                "Mytholog's Spade",
                ItemType.ITEM,
                Rarity.RARE,
                MythologicalEchoAbility.mythologsSpadeDescription,
                new MythologicalEchoAbility()
        );
    }
}
