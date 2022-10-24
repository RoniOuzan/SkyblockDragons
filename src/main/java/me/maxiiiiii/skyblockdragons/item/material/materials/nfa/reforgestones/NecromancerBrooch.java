package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.reforgestones;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ReforgeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class NecromancerBrooch extends ReforgeMaterial {
    public NecromancerBrooch() {
        super("NECROMANCER_BROOCH",
                Material.SKULL_ITEM,
                ItemFamily.REFORGE_STONE,
                "Necromancer's Brooch",
                Rarity.RARE,
                "Necrotic"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("ea3ee289-11c8-32b4-8913-c98703b1ab1c",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViNmJlYWM1NzM2NWNhZjQ2ZjAzN2YzZDJhM2E0NTdmNmNhZmU2NDc1N2JhZjE0ZTg5OTNjZDJkYTE4Y2ZmNyJ9fX0K"
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
