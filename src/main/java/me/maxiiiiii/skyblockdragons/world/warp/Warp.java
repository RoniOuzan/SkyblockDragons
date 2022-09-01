package me.maxiiiiii.skyblockdragons.world.warp;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Warp {
    HUB(WorldSD.HUB.getSpawn()),

    DEEP_MINES(WorldSD.DEEP_MINES.getSpawn()),
    DEEPER_MINES(WorldSD.DEEPER_MINES.getSpawn()),

    THE_END(WorldSD.THE_END.getSpawn(), "END"),

    GRIFFIN_ISLAND(WorldSD.GRIFFIN_ISLAND.getSpawn(), "griffin"),

    WITHER_ISLAND(WorldSD.WIHTER_ISLAND.getSpawn(), "wither"),
    ;

    private final Location location;
    private final String alias;
    private final List<Requirement> requirements;

    Warp(Location location, String alias, Requirement... requirements) {
        this.location = location;
        this.alias = alias;
        this.requirements = Arrays.asList(requirements);
    }

    Warp(Location location, Requirement... requirements) {
        this(location, null, requirements);
    }

    public void warp(PlayerSD player) {
        PlayerWarpEvent event = new PlayerWarpEvent(player, this);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}