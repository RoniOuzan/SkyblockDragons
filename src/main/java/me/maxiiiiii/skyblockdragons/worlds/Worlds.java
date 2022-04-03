package me.maxiiiiii.skyblockdragons.worlds;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Worlds {
    HUB(Bukkit.getWorld("Hub"), "Hub", WorldType.HUB),
    DEEP_MINES(Bukkit.getWorld("DeepMines"), "Deep Mines", WorldType.MINING)
    ;

    private final World world;
    private final String name;
    private final List<WorldType> worldType;

    Worlds(World world, String name, WorldType... worldType) {
        this.world = world;
        this.name = name;
        this.worldType = Arrays.stream(worldType).collect(Collectors.toList());
    }
}
