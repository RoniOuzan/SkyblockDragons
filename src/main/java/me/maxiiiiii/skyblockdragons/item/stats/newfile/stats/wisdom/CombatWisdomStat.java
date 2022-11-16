package me.maxiiiiii.skyblockdragons.item.stats.newfile.stats.wisdom;

import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.PercentageStat;
import me.maxiiiiii.skyblockdragons.item.stats.newfile.interfaces.WisdomStat;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CombatWisdomStat extends WisdomStat implements PercentageStat {
    public CombatWisdomStat() {
        super("Combat Wisdom",
                "☯",
                ChatColor.DARK_AQUA,
                "",
                SkillType.COMBAT
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.BOOK_AND_QUILL);
    }
}
