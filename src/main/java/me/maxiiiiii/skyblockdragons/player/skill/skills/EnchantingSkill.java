package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetExperienceEvent;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class EnchantingSkill extends Skill {
    public EnchantingSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Enchanting",
                "Enchant items to earn Enchanting XP!",
                Material.ENCHANTMENT_TABLE,
                new SkillRewards(
                        "Conjurer",
                        l -> ChatColor.WHITE + "Gain " + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + " " + ChatColor.WHITE + "more experience orbs from any source.",
                        StatTypes.INTELLIGENCE,
                        2,
                        coinsAmount
                ),
                level,
                60,
                totalXp
        );
    }

    @EventHandler
    public void updateExp(PlayerGetExperienceEvent e) {
        e.getMultiplier().addPost(this.getLevel() * 4);
    }
}
