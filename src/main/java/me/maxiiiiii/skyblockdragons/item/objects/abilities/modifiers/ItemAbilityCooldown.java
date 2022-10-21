package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityCooldown extends ItemAbilitySilentCooldown {
    static String getLine(ItemAbilityCooldown ability, PlayerSD player) {
        return ChatColor.GRAY + "Cooldown: " + ChatColor.GREEN + ability.getFinalCooldown(player);
    }
}
