package me.maxiiiiii.skyblockdragons.worlds;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.worlds.mining.Mining;
import me.maxiiiiii.skyblockdragons.worlds.deepmines.DeepMines;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class WorldSD {
    private static final List<WorldSD> worlds = new ArrayList<>();

    private final World world;
    private final String name;
    private final List<WorldType> worldType;

    public static Hub HUB = null;
    public static DeepMines DEEP_MINES = null;
    public static TheEnd THE_END = null;

    protected WorldSD(World world, String name, WorldType... worldType) {
        this.world = world;
        this.name = name;
        this.worldType = Arrays.stream(worldType).collect(Collectors.toList());
    }

    public static void registerWorlds(JavaPlugin plugin) {
        new Mining(plugin);
        HUB = new Hub(plugin);
        DEEP_MINES = new DeepMines(plugin);
        THE_END = new TheEnd(plugin);

        worlds.add(HUB);
        worlds.add(DEEP_MINES);
        worlds.add(THE_END);
    }

    public static List<WorldSD> values() {
        return worlds;
    }
}
