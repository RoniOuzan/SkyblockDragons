package me.maxiiiiii.skyblockdragons.item.stats.stats.wisdom;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.WisdomStat;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TamingWisdomStat extends WisdomStat implements PercentageStat {
    public TamingWisdomStat() {
        super("Taming Wisdom",
                "☯",
                ChatColor.DARK_AQUA,
                SkillType.TAMING
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.BOOK_AND_QUILL);
    }
}
