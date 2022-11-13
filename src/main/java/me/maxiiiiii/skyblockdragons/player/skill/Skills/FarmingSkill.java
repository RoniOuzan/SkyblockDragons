package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class FarmingSkill extends AbstractSkill {
    private static final double[] statAmount = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

    public FarmingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Farming",
                "Harvest crops and shear sheep to earn Farming XP!",
                Material.GOLD_HOE,
                new SkillRewards(
                        "Farmhand",
                        l -> ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + " " + ChatColor.GOLD + "Farming Fortune" + ChatColor.WHITE + ", which increase your chance for multiple crops.",
                        StatType.HEALTH,
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

        e.getStats().add(StatType.FARMING_FORTUNE, this.getLevel() * 4);
    }
}
