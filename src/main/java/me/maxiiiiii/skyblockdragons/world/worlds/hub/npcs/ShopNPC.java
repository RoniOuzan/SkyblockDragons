package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class ShopNPC extends NPC {
    public ShopNPC(Location location) {
        super("Shop", location, EntityType.VILLAGER, "virusing");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getPlayer().chat("/shop");
    }
}
