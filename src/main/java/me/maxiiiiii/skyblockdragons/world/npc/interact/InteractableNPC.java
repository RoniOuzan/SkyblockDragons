package me.maxiiiiii.skyblockdragons.world.npc.interact;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
public class InteractableNPC extends NPC {
    public static final Map<String, InteractableNPC> NPCS = new HashMap<>();

    private final String id;
    private final List<Function<PlayerSD, ? extends NpcInteraction>> interactions;
    private final Map<PlayerSD, NpcInteractions> playerInteracts;

    protected InteractableNPC(String id, String name, Location location, EntityType type, String skin) {
        super(name, location, type, skin);
        this.id = id;
        this.interactions = new ArrayList<>();
        this.playerInteracts = new HashMap<>();

        NPCS.put(id, this);
    }

    protected InteractableNPC(String id, String name, Location location, EntityType type) {
        this(id, name, location, type, null);
    }

    protected InteractableNPC(String id, String name, Location location, String skin) {
        this(id, name, location, EntityType.PLAYER, skin);
    }

    protected InteractableNPC(String id, String name, Location location) {
        this(id, name, location, EntityType.PLAYER, null);
    }

    public void addInteraction(Function<PlayerSD,  ? extends NpcInteraction> interaction) {
        this.interactions.add(interaction);
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        if (!this.playerInteracts.containsKey(e.getPlayer()))
            this.playerInteracts.put(e.getPlayer(), new NpcInteractions(e.getPlayer(), this, this.interactions));

        this.playerInteracts.get(e.getPlayer()).interact();
    }

    @Override
    public void save() {
    }

    public static InteractableNPC get(String id) {
        return NPCS.get(id);
    }
}
