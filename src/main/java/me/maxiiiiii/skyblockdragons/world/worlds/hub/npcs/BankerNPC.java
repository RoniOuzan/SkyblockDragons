package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.player.bank.BankMenu;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class BankerNPC extends NPC {
    public BankerNPC(Location location) {
        super("Banker", location, EntityType.PLAYER, "Eliteminers_net");
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        BankMenu.openBank(e.getPlayer());
    }
}
