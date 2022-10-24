package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class PlasmaPowerOrb extends PowerOrbMaterial {
    public PlasmaPowerOrb() {
        super("PLASMA_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Plasma Power Orb",
                ItemType.POWER_ORB,
                Rarity.LEGENDARY,
                "Grants " + ChatColor.AQUA + "+125% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "3% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+7.5%" + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+35 Strength" + ChatColor.GRAY + ".",
                "Plasma",
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrb.Type.PLASMA)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("6de57aa8-ffd6-414d-ad9b-85563a6dc417",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0="
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }
}
