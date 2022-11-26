package me.maxiiiiii.skyblockdragons.item.stats.stats.combat;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.CombatStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AbilityDamageStat extends CombatStat implements PercentageStat {
    public AbilityDamageStat() {
        super("Ability Damage",
                "๑",
                ChatColor.RED,
                "Ability Damage increases the damage applied by certain spells and item abilities.",
                0
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.BEACON);
    }
}
