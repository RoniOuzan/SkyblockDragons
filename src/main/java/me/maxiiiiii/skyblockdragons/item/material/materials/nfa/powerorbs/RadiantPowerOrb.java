package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.PowerOrbMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class RadiantPowerOrb extends PowerOrbMaterial {
    public RadiantPowerOrb() {
        super("RADIANT_POWER_ORB",
                Material.SKULL_ITEM,
                ItemFamily.RADIANT_POWER_ORB,
                "Radiant Power Orb",
                ItemType.POWER_ORB,
                Rarity.UNCOMMON,
                "Heals " + ChatColor.RED + "1% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second.",
                "Radiant",
                new PowerOrbDeployAbility(PowerOrbDeployAbility.PowerOrb.Type.RADIANT)
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("10a23a36-4f37-47f7-a28f-fc330de3ff5a",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0="
        );
    }
}
