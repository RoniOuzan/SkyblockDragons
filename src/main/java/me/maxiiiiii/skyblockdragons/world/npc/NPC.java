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

    private final net.citizensnpcs.api.npc.NPC npc;
    private final Location location;

    protected NPC(String name, Location location, EntityType type, String skin) {
        this.location = location;
        this.npc = CitizensAPI.getNPCRegistry().createNPC(type, name);
        this.npc.setAlwaysUseNameHologram(true);
        this.npc.data().set("SkyblockDragons", true);

        if (skin != null) {
            this.npc.data().set(net.citizensnpcs.api.npc.NPC.PLAYER_SKIN_UUID_METADATA, skin);
            this.npc.data().set(net.citizensnpcs.api.npc.NPC.PLAYER_SKIN_USE_LATEST, false);
        }

        npcs.put(this.npc.getUniqueId(), this);

        this.spawn();
    }

    protected NPC(String name, Location location, String skin) {
        this(name, location, EntityType.PLAYER, skin);
    }

    protected NPC(String name, Location location) {
        this(name, location, "");
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

    public Location getLocation() {
        return location;
    }

    public static void despawnAllNPCS(){
        for (NPC npc : npcs.values()) {
            npc.npc.despawn();
            npc.npc.destroy();
        }
    }
}
