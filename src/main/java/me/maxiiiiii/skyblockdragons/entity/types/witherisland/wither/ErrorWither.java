package me.maxiiiiii.skyblockdragons.entity.types.witherisland.wither;

import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import org.bukkit.ChatColor;

public class ErrorWither extends EntityWither {
    public ErrorWither() {
        super("ERROR Wither", 5_000_000, 300, 20, 20, ChatColor.RED.toString(), 5);
    }
}
