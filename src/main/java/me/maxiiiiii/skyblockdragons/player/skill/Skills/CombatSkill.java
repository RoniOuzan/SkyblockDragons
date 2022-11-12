package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class CombatSkill extends AbstractSkill {
    public CombatSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Combat",
                "Fight mobs and players to earn Combat XP!",
                Material.STONE_SWORD,
                new SkillRewards(
                        "Warrior",
                        l -> ChatColor.WHITE + "Deal " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + "% " + ChatColor.WHITE + "more damage to mobs",
                        StatType.CRIT_CHANCE,
                        0.5,
                        coinsAmount
                ),
                level,
                60,
                totalXp);
    }
}
