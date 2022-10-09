package me.maxiiiiii.skyblockdragons.entity.types.witherisland.wither;

import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import org.bukkit.ChatColor;

public class BaseWither extends EntityWither {
    public BaseWither() {
        super("Base Wither", 2_000_000, 100, 20, 80, ChatColor.YELLOW.toString(), 1);
    }
}
