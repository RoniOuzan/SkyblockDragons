package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class OverfluxPowerOrb extends PowerOrbMaterial {
    public OverfluxPowerOrb() {
        super("OVERFLUX_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Overflux Power Orb",
                ItemType.POWER_ORB,
                Rarity.EPIC,
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrbType.OVERFLUX)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("05624a23-a2f1-46b9-9e26-e463855f05c1",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0="
        );
    }
}
