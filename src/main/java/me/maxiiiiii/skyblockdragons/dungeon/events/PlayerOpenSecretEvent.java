package me.maxiiiiii.skyblockdragons.dungeon.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.dungeon.Dungeon;
import me.maxiiiiii.skyblockdragons.dungeon.DungeonPlayer;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerOpenSecretEvent extends DungeonPlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final Location secretLocation;

    public PlayerOpenSecretEvent(Dungeon dungeon, DungeonPlayer player, Location secretLocation) {
        super(dungeon, player);
        this.secretLocation = secretLocation;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
