package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.player.accessorybag.PowerStoneMenu;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class PowerStoneNPC extends NPC {
    public PowerStoneNPC(Location location) {
        super("Power Stone", location, EntityType.PLAYER, "PowerStone");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        new PowerStoneMenu(e.getPlayer());
    }
}
