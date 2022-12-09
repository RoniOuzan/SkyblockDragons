package me.maxiiiiii.skyblockdragons.world.events;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.attributes.LaunchPad;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;

@Getter
public class PlayerStepOnLaunchPadEvent extends PlayerSDEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final LaunchPad launchPad;
    private final WorldSD world;
    private final Location location;

    public PlayerStepOnLaunchPadEvent(PlayerSD player, LaunchPad launchPad, WorldSD world, Location location) {
        super(player);
        this.launchPad = launchPad;
        this.world = world;
        this.location = location;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
