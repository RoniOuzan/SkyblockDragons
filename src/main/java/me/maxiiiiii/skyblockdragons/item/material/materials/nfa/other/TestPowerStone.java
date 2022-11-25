package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.other;

import me.maxiiiiii.skyblockdragons.item.material.types.PowerStoneMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStone;
import org.bukkit.Material;

public class TestPowerStone extends PowerStoneMaterial {
    public TestPowerStone() {
        super("TEST_POWER_STONE",
                Material.STONE,
                ItemFamily.NULL,
                "Test Power Stone",
                Rarity.LEGENDARY,
                PowerStone.TEST
        );
    }
}
