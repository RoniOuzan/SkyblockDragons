package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                        ChatColor.WHITE + "Grants " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Foraging Fortune " + ChatColor.WHITE + ", which increase your chance for multiple logs.",
                        StatType.STRENGTH,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
