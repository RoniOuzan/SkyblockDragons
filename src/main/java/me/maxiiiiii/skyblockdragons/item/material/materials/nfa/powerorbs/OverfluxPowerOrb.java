package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class OverfluxPowerOrb extends PowerOrbMaterial {
    public OverfluxPowerOrb() {
        super("OVERFLUX_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Overflux Power Orb",
                ItemType.POWER_ORB,
                Rarity.EPIC,
                "Grants " + ChatColor.AQUA + "+100% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2.5% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+5%"  + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+25 Strength" + ChatColor.GRAY + ".",
                "Overflux",
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrb.Type.OVERFLUX)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("05624a23-a2f1-46b9-9e26-e463855f05c1",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
