package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class MiningSkill extends AbstractSkill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public MiningSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Mining",
                "Spelunk island for ores and valuable materials to earn Mining XP!",
                Material.DIAMOND_PICKAXE,
                new SkillRewards(
                        "Spelunker",
                        ChatColor.WHITE + "Grants " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (level * 4) + "➡" + ChatColor.GREEN + ((level + 1) * 4) + " " + ChatColor.GOLD + "Mining Fortune" + ChatColor.WHITE + ", which increases your chance for multiple ore drops.",
                        StatType.DEFENSE,
                        2,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }
}
