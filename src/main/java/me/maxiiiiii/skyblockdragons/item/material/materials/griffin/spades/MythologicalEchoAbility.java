package me.maxiiiiii.skyblockdragons.item.material.materials.griffin.spades;

import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public class MythologicalEchoAbility extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
    public static final String mythologsSpadeDescription = "Hold in your hand to reveal and dig out " + ChatColor.YELLOW + "Griffin Burrows " + ChatColor.GRAY + "in the griffin's island, which hold both " + ChatColor.GOLD + "treasure " + ChatColor.GRAY + "and " + ChatColor.RED + "dangers" + ChatColor.GRAY + "." + " NEW_LINE " + "Each level of the " + ChatColor.DARK_GREEN + "Mytholog's Spade " + ChatColor.GRAY + "you can find better " + ChatColor.GOLD + "treasures " + ChatColor.GRAY + "and drops.";

    public MythologicalEchoAbility() {
        super(AbilityAction.RIGHT_CLICK,
                "Echo",
                "Show the way to the next or nearby Griffin Burrow."
        );
    }

    @Override
    public double getBaseManaCost(PlayerSD player) {
        return 50;
    }

    @Override
    public double getBaseCooldown(PlayerSD player) {
        return 2;
    }

    @Override
    public PlayerAbilityRunnable setupAbility() {
        return e -> {
            e.getPlayer().getGriffin().showNext();
            e.getPlayer().sendMessage(e.getPlayer().getGriffin().getBurrow().toString());
        };
    }
}
