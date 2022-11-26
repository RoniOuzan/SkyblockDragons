package me.maxiiiiii.skyblockdragons.world.npc;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import org.bukkit.Location;

public class ForgeNPC extends NPC {
    public ForgeNPC(Location location) {
        super("Forge", location, "999kuo");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getPlayer().getForge().view();
    }
}
