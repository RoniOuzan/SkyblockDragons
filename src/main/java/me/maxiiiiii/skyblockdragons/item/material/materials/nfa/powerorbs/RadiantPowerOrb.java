package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class RadiantPowerOrb extends PowerOrbMaterial {
    public RadiantPowerOrb() {
        super("RADIANT_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Radiant Power Orb",
                ItemType.POWER_ORB,
                Rarity.UNCOMMON,
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrbType.RADIANT)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("10a23a36-4f37-47f7-a28f-fc330de3ff5a",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0="
        );
    }
}
