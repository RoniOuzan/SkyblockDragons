package me.maxiiiiii.skyblockdragons.util.objects.reward;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

public class SkillXpReward implements Reward {
    private final SkillType skill;
    private final double amount;

    public SkillXpReward(SkillType skill, double amount) {
        this.skill = skill;
        this.amount = amount;
    }

    @Override
    public void give(PlayerSD player) {
        player.getSkill().get(this.skill).giveXp(this.amount);
    }

    @Override
    public String getLore() {
        return ChatColor.DARK_GRAY + "+" + ChatColor.DARK_AQUA + Functions.getNumberFormat(this.amount) + " " + ChatColor.GRAY + skill.toString() + " Experience";
    }
}
