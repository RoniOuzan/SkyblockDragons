package me.maxiiiiii.skyblockdragons.player.events;

import me.maxiiiiii.skyblockdragons.events.events.abstracts.playersd.PlayerSDEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.event.HandlerList;

public class PlayerClickOnNPCEvent extends PlayerSDEvent {
    public static final HandlerList handlers = new HandlerList();

    private final NPC npc;

    public PlayerClickOnNPCEvent(PlayerSD player, NPC npc) {
        super(player);
        this.npc = npc;
    }

    public NPC getNPC() {
        return this.npc;
    }

    @Override
    public HandlerList getHandlers() {return handlers;}
    public static HandlerList getHandlerList() {return handlers;}
}
