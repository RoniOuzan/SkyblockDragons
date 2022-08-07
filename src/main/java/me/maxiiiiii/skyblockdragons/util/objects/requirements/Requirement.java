package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

@Getter
@Setter
public abstract class Requirement {
    protected static final String icon = ChatColor.RED + "❣ Requires ";

    protected final RequirementType type;
    protected final int level;

    protected Requirement(RequirementType type, int level) {
        this.type = type;
        this.level = level;
    }

    public abstract boolean hasRequirement(PlayerSD player);

    public abstract String toString();
}
