package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ManaFluxPowerOrb extends PowerOrbMaterial {
    public ManaFluxPowerOrb() {
        super("MANA_FLUX_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Mana Flux Power Orb",
                ItemType.POWER_ORB,
                Rarity.RARE,
                "Grants " + ChatColor.AQUA + "+50% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+10 Strength" + ChatColor.GRAY + ".",
                "Mana Flux",
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrb.Type.MANA_FLUX)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("2131e1e3-cd0f-4212-b625-8ccb402e895e",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJhZGExYzdmY2M4Y2YzNWRlZmViOTQ0YTRmOGZmYTlhOWQyNjA1NjBmYzdmNWY1ODI2ZGU4MDg1NDM1OTY3YyJ9fX0"
        );
    }
}
