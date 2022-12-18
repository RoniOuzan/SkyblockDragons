package me.maxiiiiii.skyblockdragons.world.npc;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListeners implements Listener {
    @EventHandler
    public void onClick(NPCRightClickEvent e) {
        if (e.getNPC().data().has("SkyblockDragons")) {
            NPC npc = NPC.npcs.get(e.getNPC().getUniqueId());
            if (npc != null) {
                Bukkit.getPluginManager().callEvent(new PlayerClickOnNPCEvent(SkyblockDragons.getPlayer(e.getClicker()), npc));
            }
        }
    }

    @EventHandler
    public void onClick(PlayerClickOnNPCEvent e) {
        e.getNPC().onClick(e);
    }

    @EventHandler
    public void onDespawn(NPCDespawnEvent e) {
        if (e.getNPC().data().has("SkyblockDragons")) {
            NPC npc = NPC.npcs.get(e.getNPC().getUniqueId());
            if (npc != null)
                npc.onDespawn(e);
        }
    }

    @EventHandler
    public void onSpawn(NPCSpawnEvent e) {
        if (e.getNPC().data().has("SkyblockDragons")) {
            NPC npc = NPC.npcs.get(e.getNPC().getUniqueId());
            if (npc != null)
                npc.onSpawn(e);
        }
    }
}
