package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

@Getter
public class ForagingSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public ForagingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Foraging",
                "Cut trees and forage for other plants to earn Foraging XP!",
                new ItemStack(Material.SAPLING, 1, (short) 3),
                new SkillRewards(
                        "Logger,",
                        l -> ChatColor.WHITE + "Grants " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + " " + ChatColor.GOLD + "Foraging Fortune " + ChatColor.WHITE + ", which increase your chance for multiple logs.",
                        StatTypes.STRENGTH,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer() != this.getPlayer()) return;

        e.getStats().add(StatTypes.FORAGING_FORTUNE, this.getLevel() * 4);
    }
}
