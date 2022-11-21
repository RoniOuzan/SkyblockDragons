package me.maxiiiiii.skyblockdragons.world;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.mining.Mining;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.DeeperMines;
import me.maxiiiiii.skyblockdragons.world.worlds.deepmines.DeepMines;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.GriffinIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.world.worlds.witherisland.WitherIsland;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class WorldSD implements Listener {
    public static final List<WorldSD> worlds = new ArrayList<>();

    public static Hub HUB = null;
    public static DeepMines DEEP_MINES = null;
    public static TheEnd THE_END = null;
    public static DeeperMines DEEPER_MINES = null;
    public static GriffinIsland GRIFFIN_ISLAND = null;
    public static WitherIsland WITHER_ISLAND = null;

    private final World world;
    private final String name;
    private final Warp warp;
    private final List<WorldType> worldType;

    protected WorldSD(World world, String name, Warp warp, WorldType... worldType) {
        this.world = world;
        this.name = name;
        this.warp = warp;
        this.worldType = Arrays.stream(worldType).collect(Collectors.toList());

        SkyblockDragons.plugin.getServer().getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public boolean isType(WorldType type) {
        return this.worldType.contains(type);
    }

    protected void spawnNPCs() {}

    public static void registerWorlds(JavaPlugin plugin) {
        new Mining(plugin);

        HUB = new Hub(plugin);
        DEEP_MINES = new DeepMines(plugin);
        THE_END = new TheEnd(plugin);
        DEEPER_MINES = new DeeperMines(plugin);
        GRIFFIN_ISLAND = new GriffinIsland(plugin);
        WITHER_ISLAND = new WitherIsland(plugin);

        worlds.add(HUB);
        worlds.add(DEEP_MINES);
        worlds.add(THE_END);
        worlds.add(DEEPER_MINES);
        worlds.add(GRIFFIN_ISLAND);
        worlds.add(WITHER_ISLAND);

        Functions.Wait(5L, () -> {
            for (WorldSD world : worlds) {
                world.spawnNPCs();
            }
        });
    }

    public void sendMessage(Object... messages) {
        for (PlayerSD player : this.getPlayers()) {
            player.sendMessage(messages);
        }
    }

    public static WorldSD get(String worldName) {
        for (WorldSD world : worlds) {
            if (world.getWorld().getName().equals(worldName))
                return world;
        }
        return HUB;
    }

    public static WorldSD get(World world) {
        return get(world.getName());
    }

    public List<PlayerSD> getPlayers() {
        return this.world.getPlayers().stream().map(SkyblockDragons::getPlayer).collect(Collectors.toList());
    }
}
