package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class AlchemySkill extends AbstractSkill {
    public AlchemySkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Alchemy",
                "Brew potions to earn Alchemy XP!",
                Material.BREWING_STAND_ITEM,
                new SkillRewards(
                        "Brewer",
                        l -> ChatColor.GRAY + "Potion that you brew have a " + ChatColor.DARK_GRAY + l + "➡" + ChatColor.GREEN + (l + 1) + "% " + ChatColor.WHITE + "longer duration.",
                        StatTypes.INTELLIGENCE,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }

    // TODO: passive
}
