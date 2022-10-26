package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.wands;

import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class IceSprayWand extends ToolMaterial {
    public IceSprayWand() {
        super("ICE_SPRAY_WAND",
                Material.STICK,
                ItemFamily.ICE_SPRAY_WAND,
                "Ice Spray Wand",
                ItemType.WAND,
                Rarity.EPIC,
                "",
                new IceSprayAbility()
        );
    }

    private static class IceSprayAbility extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public IceSprayAbility() {
            super(AbilityAction.RIGHT_CLICK,
                    "Ice Spray",
                    "Produces a cone of ice in front of the caster that deals " + ChatColor.RED + "17,000 " + ChatColor.GRAY + "damage to mobs and freezes them in place for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds! Frozen mobs take " + ChatColor.RED + "10% " + ChatColor.GRAY + "increased damage!"
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 50;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 5;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }
    }
}
