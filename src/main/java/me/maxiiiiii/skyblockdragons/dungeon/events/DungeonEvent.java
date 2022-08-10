package me.maxiiiiii.skyblockdragons.dungeon.events;

import me.maxiiiiii.skyblockdragons.dungeon.Dungeon;
import org.bukkit.event.Event;

public abstract class DungeonEvent extends Event {
    protected final Dungeon dungeon;

    protected DungeonEvent(final Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public final Dungeon getDungeon() {
        return this.dungeon;
    }
}
