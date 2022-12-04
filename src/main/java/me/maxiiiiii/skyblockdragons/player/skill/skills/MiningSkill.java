package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class MiningSkill extends Skill {
    private static final double[] statsAmount = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    public MiningSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Mining",
                "Spelunk island for ores and valuable materials to earn Mining XP!",
                Material.DIAMOND_PICKAXE,
                new SkillRewards(
                        "Spelunker",
                        l -> ChatColor.WHITE + "Grants " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + " " + ChatColor.GOLD + "Mining Fortune" + ChatColor.WHITE + ", which increases your chance for multiple ore drops.",
                        StatTypes.DEFENSE,
                        2,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.getPlayer() != this.getPlayer()) return;

        e.getStats().add(StatTypes.MINING_FORTUNE, this.getLevel() * 4);
    }
}
