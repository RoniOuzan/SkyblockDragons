package me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbilityPerPlayer;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public interface ItemAbilityCooldown extends ItemAbilityNoMessageCooldown {
    @Override
    default boolean get(PlayerSD player, Item item, ItemAbilityPerPlayer ability) {
        boolean output = ItemAbilityNoMessageCooldown.super.get(player, item, ability);
        if (!output) {
            double restOfCooldown = Math.ceil((this.getFinalCooldown(player, item) - (SkyblockDragons.getCurrentTimeInSeconds() - ability.getLastTimeUsed())) * 10) / 10;
            player.sendMessage(ChatColor.RED + "This item is on cooldown for " + restOfCooldown + "s!");
        }
        return output;
    }
}
