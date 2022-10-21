package me.maxiiiiii.skyblockdragons.item.objects.abilties.modifiers;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityCooldown extends ItemAbilitySilentCooldown, ItemAbilityLoreModifier {
    static String getLine(ItemAbilityCooldown ability, PlayerSD player) {
        return ChatColor.GRAY + "Cooldown: " + ChatColor.GREEN + ability.getFinalCooldown(player);
    }
}
