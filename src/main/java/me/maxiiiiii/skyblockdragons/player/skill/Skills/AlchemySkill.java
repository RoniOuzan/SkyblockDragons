package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
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
                        ChatColor.GRAY + "Potion that you brew have a " + ChatColor.DARK_GRAY + level + "➡" + ChatColor.GREEN + (level + 1) + "% " + ChatColor.WHITE + "longer duration.",
                        StatType.INTELLIGENCE,
                        2,
                        coinsAmount
                ),
                level,
                50,
                totalXp
        );
    }
}
