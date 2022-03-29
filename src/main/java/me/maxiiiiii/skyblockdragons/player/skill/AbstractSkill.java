package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public abstract class AbstractSkill {
    private static final double[] needXps = {50, 125, 200, 300, 500, 750, 1000, 1500, 2000, 3500, 5000, 7500, 100000, 15000, 20000, 30000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 1800000, 1900000, 2000000, 2100000, 2200000, 2300000, 2400000, 2500000, 2600000, 2750000, 2900000, 3100000, 3400000, 3700000, 4000000, 4300000, 4600000, 4900000, 5200000, 5500000, 5800000, 6100000, 6400000, 6700000, 7000000};
    protected static final int[] coinsAmount = {25, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3500, 4000, 5000, 6000, 7500, 10000, 12500, 15000, 17500, 20000, 25000, 30000, 35000, 40000, 45000, 50000, 60000, 70000, 80000, 90000, 100000, 125000, 150000, 175000, 200000};

    private final String name;
    private final String description;
    private final SkillRewards rewards;
    private int level;
    private final int maxLevel;
    private double currentXp;
    private double totalXp;
    private final double[] needXp;

    public AbstractSkill(String name, String description, SkillRewards rewards, int level, int maxLevel, double totalXp, double[] needXps) {
        this.name = name;
        this.description = description;
        this.rewards = rewards;
        this.level = level;
        this.maxLevel = maxLevel;
        double currentXp = totalXp;
        for (int i = 0; i < level; i++) {
            currentXp -= needXps[i];
        }
        this.currentXp = currentXp;
        this.totalXp = totalXp;
        this.needXp = needXps;
    }

    public AbstractSkill(String name, String description, SkillRewards rewards, int level, int maxLevel, double totalXp) {
        this(name, description, rewards, level, maxLevel, totalXp, needXps);
    }

    public double getCurrentNeedXp() {
        return this.needXp[this.level];
    }

    public void giveXp(double amount, Player player) {
        this.totalXp += amount;
        this.currentXp += amount;
        while (this.currentXp >= needXps[level] && this.level < this.maxLevel) {
            this.currentXp -= needXps[level];
            this.level++;
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "--------------------------------------------------");
            player.sendMessage("    " + ChatColor.AQUA + "" + ChatColor.BOLD + "SKILL LEVEL UP " + ChatColor.RESET + "" + ChatColor.DARK_AQUA + this.name + " " + ChatColor.DARK_GRAY + integerToRoman(this.level - 1) + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "➡" + ChatColor.DARK_AQUA + integerToRoman(this.level));
            player.sendMessage("");
            player.sendMessage("    " + ChatColor.GREEN + "" + ChatColor.BOLD + "REWARDS");
            player.sendMessage("      " + ChatColor.YELLOW + this.rewards.getName() + integerToRoman(this.level));
            player.sendMessage("         " + this.rewards.getPassive());
            player.sendMessage("       " + ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + getInt(this.rewards.getStatAmount() + "") + this.rewards.getStat().getIconAndText());
            player.sendMessage("       " + ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + getNumberFormat(this.rewards.getCoinsAmount()) + " " + ChatColor.GRAY + "Coins");
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "--------------------------------------------------");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        }
        Variables.set(player.getUniqueId(), this.name, 1, this.level);
        Variables.set(player.getUniqueId(), this.name, 2, this.totalXp);
    }

    public void setXp(double amount, Player player) {
        this.totalXp = 0;
        this.currentXp = 0;
        this.level = 0;
        this.giveXp(amount, player);
    }

    public void setLevel(int level, Player player) {
        this.setXp(0, player);
        this.giveXp(getTotalXpForLevel(level), player);
    }

    public static double getTotalXpForLevel(int level) {
        int sum = 0;
        for (int i = 0; i < level; i++) {
            sum += needXps[i];
        }
        return sum;
    }
}
