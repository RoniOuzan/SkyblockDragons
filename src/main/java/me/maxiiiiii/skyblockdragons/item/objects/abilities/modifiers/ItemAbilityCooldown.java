package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityCooldown extends ItemAbilitySilentCooldown {
    @Override
    default boolean get(PlayerSD player, ItemAbilityPerPlayer ability) {
        boolean output = ItemAbilitySilentCooldown.super.get(player, ability);
        if (!output) {
            double restOfCooldown = Math.ceil((this.getFinalCooldown(player) - (SkyblockDragons.getCurrentTimeInSeconds() - ability.getLastTimeUsed())) * 10) / 10;
            player.sendMessage(ChatColor.RED + "This item is on cooldown for " + restOfCooldown + "s!");
        }
        return output;
    }

    static String getLine(ItemAbilityCooldown ability, PlayerSD player) {
        return ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getFinalCooldown(player);
    }
}
