package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityNoMessageCooldown extends ItemAbilitySilentCooldown {
    @Override
    default boolean get(PlayerSD player, Item item, ItemAbilityPerPlayer ability) {
        return ItemAbilitySilentCooldown.super.get(player, item, ability);
    }

    static String getLine(ItemAbilityNoMessageCooldown ability, Item item, PlayerSD player) {
        return ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getFinalCooldown(player, item) + "s";
    }
}
