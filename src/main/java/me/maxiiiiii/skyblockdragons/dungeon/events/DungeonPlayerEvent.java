package me.maxiiiiii.skyblockdragons.dungeon.events;

import me.maxiiiiii.skyblockdragons.dungeon.Dungeon;
import me.maxiiiiii.skyblockdragons.dungeon.DungeonPlayer;

public abstract class DungeonPlayerEvent extends DungeonEvent {
    private final DungeonPlayer player;

    protected DungeonPlayerEvent(Dungeon dungeon, DungeonPlayer player) {
        super(dungeon);
        this.player = player;
    }

    public DungeonPlayer getPlayer() {
        return this.player;
    }
}
