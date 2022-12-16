package me.maxiiiiii.skyblockdragons.util.objects.requirements;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.objects.MaterialModifier;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.WorldModifier;
import org.bukkit.ChatColor;

@Getter
@Setter
public abstract class Requirement implements MaterialModifier, WorldModifier {
    protected static final String icon = ChatColor.RED + "❣ Requires ";

    protected final RequirementType type;

    public Requirement(RequirementType type) {
        this.type = type;
    }

    public abstract boolean hasRequirement(PlayerSD player);

    public abstract String toString();
}
