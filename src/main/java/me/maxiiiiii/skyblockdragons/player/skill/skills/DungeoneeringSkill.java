package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
public class DungeoneeringSkill extends AbstractSkill {
    private static final double[] needXps = {50, 75, 110, 160, 230, 330, 470, 670, 950, 1340, 1890, 2665, 3760, 5260, 7380, 10300, 14400, 20000, 27600, 38000, 52500, 71500, 97000, 132000, 180000, 243000, 328000, 445000, 600000, 800000, 1065000, 1410000, 1900000, 2500000, 3300000, 4300000, 5600000, 7200000, 9200000, 12000000, 15000000, 19000000, 24000000, 30000000, 38000000, 48000000, 60000000, 75000000, 93000000, 116250000};
    private static final int[] levelIncrease = {4, 8, 12, 16, 20, 25, 30, 35, 40, 45, 51, 57, 63, 69, 75, 82, 89, 96, 103, 110, 118, 126, 134, 142, 150, 159, 168, 177, 186, 195, 205, 215, 225, 235, 245, 257, 269, 281, 293, 305, 319, 333, 347, 361, 375, 391, 408, 426, 445, 465};

    public DungeoneeringSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Dungeoneering",
                "Complete this dungeon to earn experience and unlock new rewards!",
                Functions.applySkull(Functions.createItem(Material.SKULL_ITEM, 3, ""), "3549f087-6655-4e1b-9b71-ecc1c59e59b7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTI3MzcyODA5YjU2OTQ2NDZmNDRkN2E4MzdkNGZlNjZlNWVmNjJhZTcyNzAxMTcxNjUxYjNhNzgwY2IxZjljIn19fQ=="),
                new SkillRewards(
                        "Catacomber",
                        l -> ChatColor.WHITE + "Increasing the stats of your dungeon items by " + ChatColor.DARK_GRAY + getLevelIncrease(l) + "➡" + ChatColor.GREEN + levelIncrease[l] + "% " + ChatColor.WHITE + "while in " + ChatColor.RED + "The Catacombs" + ChatColor.WHITE + ".",
                        StatTypes.HEALTH,
                        1,
                        coinsAmount
                ),
                level,
                50,
                totalXp,
                needXps);
    }

    private static int getLevelIncrease(int level) {
        if (level < 1) {
            return 0;
        }
        return levelIncrease[level - 1];
    }
}