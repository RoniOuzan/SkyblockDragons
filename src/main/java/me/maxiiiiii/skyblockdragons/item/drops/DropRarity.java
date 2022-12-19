package me.maxiiiiii.skyblockdragons.item.drops;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum DropRarity {
    AUTO(""),
    RARE(ChatColor.BLUE + "RARE"),
    VERY_RARE(ChatColor.DARK_PURPLE + "RARE"),
    CRAZY_RARE(ChatColor.LIGHT_PURPLE + "CRAZY RARE"),
    INSANE(ChatColor.RED + "INSANE"),
    ;

    private final String message;

    DropRarity(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ChatColor.BOLD + "" + message + " DROP!";
    }

    public static DropRarity getRarity(double chances) {
        if (chances <= 0.01)
            return DropRarity.INSANE;
        else if (chances <= 0.2)
            return DropRarity.CRAZY_RARE;
        else if (chances <= 1)
            return DropRarity.VERY_RARE;
        return DropRarity.RARE;
    }
}
