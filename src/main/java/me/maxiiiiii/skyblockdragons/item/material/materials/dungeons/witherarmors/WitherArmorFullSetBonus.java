package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.witherarmors;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.fullset.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public class WitherArmorFullSetBonus extends ItemFullSetBonus implements ItemAbilityCooldown {
    public WitherArmorFullSetBonus() {
        super("Witherborn",
                "Spawns a wither minion every " + ChatColor.YELLOW + "30 " + ChatColor.GRAY + "seconds up to a maximum " + ChatColor.GREEN + "1 " + ChatColor.GRAY + "wither. Your withers will travel to and explode on nearby enemies."
        );
    }

    @Override
    public double getBaseCooldown(PlayerSD player) {
        return 30;
    }
}
