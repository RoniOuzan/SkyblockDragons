package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.bows;

import me.maxiiiiii.skyblockdragons.item.material.types.ShortBowMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityNoMessageCooldown;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class Terminator extends ShortBowMaterial {
    public Terminator() {
        super("TERMINATOR",
                Material.BOW,
                ItemFamily.JUJU,
                "Terminator",
                Rarity.LEGENDARY,
                new Stats(335, 50, 250, 0, 40, 0, 0, 0, 0, 0),
                ChatColor.RED + "Divides your RESET_LENGTH " + StatTypes.CRIT_CHANCE + ChatColor.RED + " by 4!",
                new InstaShoot("TERMINATOR", "Can damage endermen.", 3, 1.3),
                new Salvation()
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        e.getStats().addMultiplier(StatTypes.CRIT_CHANCE, -75, 0);
    }

    private static class Salvation extends ItemAbility implements ItemAbilityNoMessageCooldown {
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
            return e -> {};
        }
    }
}
