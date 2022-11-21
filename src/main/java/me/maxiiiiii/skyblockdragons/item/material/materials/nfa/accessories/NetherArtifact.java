package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.accessories;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class NetherArtifact extends AccessoryMaterial {
    public NetherArtifact() {
        super("NETHER_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.NETHER_ARTIFACT,
                "Nether Artifact",
                Rarity.EPIC,
                new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "Reduces the damage taken from Zombie Pigmen, Magma Cubes, Ghasts and Blazes by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ". NEW_LINE NEW_LINE While in the " + ChatColor.GOLD + "Blazing Fortress " + ChatColor.GRAY + "you will receive the damage reduction from all mobs."
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("0ef495a4-e5df-41c2-b9a2-b2e647cbb491",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0="
        );
    }
}
