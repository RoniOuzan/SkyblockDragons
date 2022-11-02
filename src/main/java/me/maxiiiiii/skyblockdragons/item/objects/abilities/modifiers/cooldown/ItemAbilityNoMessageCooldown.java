package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityNoMessageCooldown extends ItemAbilitySilentCooldown {
    @Override
    default boolean get(PlayerSD player, ItemAbilityPerPlayer ability) {
        return ItemAbilitySilentCooldown.super.get(player, ability);
    }

    static String getLine(ItemAbilityNoMessageCooldown ability, PlayerSD player) {
        return ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getFinalCooldown(player);
    }
}
