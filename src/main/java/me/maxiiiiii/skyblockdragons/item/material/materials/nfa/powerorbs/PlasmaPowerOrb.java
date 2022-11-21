package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class PlasmaPowerOrb extends PowerOrbMaterial {
    public PlasmaPowerOrb() {
        super("PLASMA_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Plasma Power Orb",
                ItemType.POWER_ORB,
                Rarity.LEGENDARY,
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrbType.PLASMA)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("6de57aa8-ffd6-414d-ad9b-85563a6dc417",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0="
        );
    }
}
