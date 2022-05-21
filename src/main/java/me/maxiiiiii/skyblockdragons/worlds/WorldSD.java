package me.maxiiiiii.skyblockdragons.worlds;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.DeeperMines;
import me.maxiiiiii.skyblockdragons.worlds.deepmines.DeepMines;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.griffin.GriffinIsland;
import me.maxiiiiii.skyblockdragons.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.worlds.mining.Mining;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class WorldSD {
    public static final List<WorldSD> worlds = new ArrayList<>();

    public static Hub HUB = null;
    public static DeepMines DEEP_MINES = null;
    public static TheEnd THE_END = null;
    public static DeeperMines DEEPER_MINES = null;
    public static GriffinIsland GRIFFIN_ISLAND = null;

    private final World world;
    private final String name;
    private final List<WorldType> worldType;

    protected WorldSD(World world, String name, WorldType... worldType) {
        this.world = world;
        this.name = name;
        this.worldType = Arrays.stream(worldType).collect(Collectors.toList());
    }

    public boolean isType(WorldType type) {
        return this.worldType.contains(type);
    }

    public static void registerWorlds(JavaPlugin plugin) {
        new Mining(plugin);
        HUB = new Hub(plugin);
        DEEP_MINES = new DeepMines(plugin);
        THE_END = new TheEnd(plugin);
        DEEPER_MINES = new DeeperMines(plugin);
        GRIFFIN_ISLAND = new GriffinIsland(plugin);

        worlds.add(HUB);
        worlds.add(DEEP_MINES);
        worlds.add(THE_END);
        worlds.add(DEEPER_MINES);
        worlds.add(GRIFFIN_ISLAND);
    }

    public static WorldSD get(String worldName) {
        for (WorldSD world : worlds) {
            if (world.getWorld().getName().equals(worldName))
                return world;
        }
        return null;
    }

    public static WorldSD get(World world) {
        return get(world.getName());
    }
}
