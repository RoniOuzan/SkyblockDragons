package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeMenu;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class ReforgeNPC extends NPC {
    protected ReforgeNPC(Location location) {
        super(ChatColor.YELLOW + "Reforge", location, "Cioalse");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        ReforgeMenu.openReforgeMenu(e.getPlayer(), false);
    }
}
