package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import me.maxiiiiii.skyblockdragons.world.warp.FastTravelMenu;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class WarpNPC extends NPC {
    public WarpNPC(Location location) {
        super(ChatColor.YELLOW + "Warp", location, "SMPEarthPublic");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        new FastTravelMenu(e.getPlayer());
    }
}
