package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class DailyNPC extends NPC {
    protected DailyNPC(Location location) {
        super("Daily", location, EntityType.VILLAGER, "");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getPlayer().performCommand("dm open rewards");
    }
}
