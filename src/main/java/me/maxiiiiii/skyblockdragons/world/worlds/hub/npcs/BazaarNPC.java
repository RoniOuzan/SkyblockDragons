package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class BazaarNPC extends NPC {
    public BazaarNPC(Location location) {
        super("Shop", location, EntityType.PLAYER, "121754312314piNG");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getPlayer().chat("/shop");
    }
}
