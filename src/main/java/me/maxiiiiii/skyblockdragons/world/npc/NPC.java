package me.maxiiiiii.skyblockdragons.world.npc;

import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDespawnEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class NPC {
    public static final Map<UUID, NPC> npcs = new HashMap<>();

    protected final net.citizensnpcs.api.npc.NPC npc;
    protected Location location;

    protected NPC(String name, Location location, EntityType type) {
        this.location = location;
        this.npc = CitizensAPI.getNPCRegistry().createNPC(type, name);
        this.npc.setAlwaysUseNameHologram(true);
        this.npc.data().set("SkyblockDragons", true);

//        this.npc.data().set(net.citizensnpcs.api.npc.NPC.PLAYER_SKIN_UUID_METADATA, skin);
//        this.npc.data().set(net.citizensnpcs.api.npc.NPC.PLAYER_SKIN_USE_LATEST, false);

        npcs.put(this.npc.getUniqueId(), this);

        this.spawn();
    }

    protected NPC(String name, Location location) {
        this(name, location, EntityType.PLAYER);
    }

    public String getName() {
        return this.npc.getName();
    }

    public UUID getUniqueId() {
        return this.npc.getUniqueId();
    }

    public void spawn() {
        this.npc.spawn(this.location);
    }

    public void onSpawn(NPCSpawnEvent e) {}

    public void onDespawn(NPCDespawnEvent e) {}

    public void onClick(PlayerClickOnNPCEvent e) {}

    public static void despawnAllNPCS(){
        for(NPC npc : npcs.values()){
            npc.npc.despawn();
            npc.npc.destroy();
        }
    }
}
