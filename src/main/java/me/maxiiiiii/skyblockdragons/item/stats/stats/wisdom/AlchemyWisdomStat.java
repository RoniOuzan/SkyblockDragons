package me.maxiiiiii.skyblockdragons.item.stats.stats.wisdom;

import me.maxiiiiii.skyblockdragons.item.stats.interfaces.PercentageStat;
import me.maxiiiiii.skyblockdragons.item.stats.interfaces.WisdomStat;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AlchemyWisdomStat extends WisdomStat implements PercentageStat {
    public AlchemyWisdomStat() {
        super("Alchemy Wisdom",
                "☯",
                ChatColor.DARK_AQUA,
                "",
                SkillType.ALCHEMY
        );
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.BOOK_AND_QUILL);
    }
}
