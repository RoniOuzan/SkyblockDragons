package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.bows;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ShortBowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Terminator extends ShortBowMaterial {
    public Terminator() {
        super("TERMINATOR",
                Material.BOW,
                ItemFamily.JUJU,
                "Terminator",
                Rarity.LEGENDARY,
                new Stats(335, 50, 250, 0, 40, 0, 0, 0, 0, 0),
                ChatColor.GOLD + "Shortbow: Instantly Shoots! NEW_LINE " + ChatColor.GRAY + "Shoots " + ChatColor.AQUA + "3 " + ChatColor.GRAY + "arrows at once. " + ChatColor.GRAY + "Can damage endermen. NEW_LINE NEW_LINE " + ChatColor.RED + "Divides your RESET_LENGTH " + StatType.CRIT_CHANCE.getIconAndText() + ChatColor.RED + " by 4!",
                new Salvation()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Salvation extends ItemAbility implements ItemAbilityCooldown {
        public Salvation() {
            super(AbilityAction.RIGHT_CLICK,
                    "Salvation",
                    ChatColor.GRAY + "Can be casted after landing RESET_LENGTH " + ChatColor.GOLD + "3 " + ChatColor.GRAY + "hits. NEW_LINE Shoot a beam, penetrating up NEW_LINE to " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "foes and dealing " + ChatColor.RED + "2x " + ChatColor.GRAY + "the damage an arrow would. NEW_LINE The beam always crits."
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 2;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                // TODO: someday
            };
        }
    }
}
