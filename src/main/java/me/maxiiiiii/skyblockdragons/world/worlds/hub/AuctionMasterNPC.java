package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class AuctionMasterNPC extends NPC {
    protected AuctionMasterNPC(Location location) {
        super(ChatColor.GOLD + "Auction Master", location, EntityType.VILLAGER);
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getPlayer().performCommand("ah");
    }
}
