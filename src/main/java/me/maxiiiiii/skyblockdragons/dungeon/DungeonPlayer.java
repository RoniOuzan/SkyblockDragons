package me.maxiiiiii.skyblockdragons.dungeon;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.entity.Player;

@Getter
@Setter
public class DungeonPlayer extends PlayerSD {
    private DungeonClass dungeonClass;
    private boolean isReady;

    public DungeonPlayer(Player player) {
        super(player);
        this.dungeonClass = DungeonClass.NULL;
        this.isReady = false;
    }

    public void toggleReady() {
        this.isReady = !isReady;
    }
}
