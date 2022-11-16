package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import org.bukkit.Material;

public class ManaFluxPowerOrb extends PowerOrbMaterial {
    public ManaFluxPowerOrb() {
        super("MANA_FLUX_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Mana Flux Power Orb",
                ItemType.POWER_ORB,
                Rarity.RARE,
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrbType.MANA_FLUX)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("2131e1e3-cd0f-4212-b625-8ccb402e895e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJhZGExYzdmY2M4Y2YzNWRlZmViOTQ0YTRmOGZmYTlhOWQyNjA1NjBmYzdmNWY1ODI2ZGU4MDg1NDM1OTY3YyJ9fX0"
        );
    }
}
