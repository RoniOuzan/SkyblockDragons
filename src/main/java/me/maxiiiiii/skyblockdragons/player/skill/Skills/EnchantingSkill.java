package me.maxiiiiii.skyblockdragons.player.skill.Skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class EnchantingSkill extends AbstractSkill {
    public EnchantingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Enchanting",
                "Enchant items to earn Enchanting XP!",
                Material.ENCHANTMENT_TABLE,
                new SkillRewards(
                        "Conjurer",
                        l -> ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + " " + ChatColor.WHITE + "more experience orbs from any source.",
                        StatType.INTELLIGENCE,
                        2,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }
}
