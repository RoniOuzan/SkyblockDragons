package me.maxiiiiii.skyblockdragons.item.objects;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum DropRarity {
    RARE(ChatColor.BLUE),
    VERY_RARE(ChatColor.DARK_PURPLE),
    CRAZY_RARE(ChatColor.LIGHT_PURPLE),
    INSANE_DROP(ChatColor.RED);

    public ChatColor color;

    DropRarity(ChatColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name().replaceAll("_", " ").replace("Rngesus", "RNGesus");
    }
}
