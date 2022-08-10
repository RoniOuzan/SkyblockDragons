package me.maxiiiiii.skyblockdragons.dungeon;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class ClasserNPC extends NPC {
    private final Dungeon dungeon;

    public ClasserNPC(Dungeon dungeon) {
        super(ChatColor.RED + "Classer", new Location(dungeon.getWorld().getWorld(), 0, 100, 0));
        this.dungeon = dungeon;
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        this.dungeon.view(e.getPlayer());
    }
}
