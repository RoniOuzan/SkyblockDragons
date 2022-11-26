package me.maxiiiiii.skyblockdragons.world.worlds.hub;

import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeMenu;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class ReforgeNPC extends NPC {
    protected ReforgeNPC(Location location) {
        super("Reforge", location, EntityType.VILLAGER, "Cioalse");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        ReforgeMenu.openReforgeMenu(e.getPlayer(), false);
    }
}
