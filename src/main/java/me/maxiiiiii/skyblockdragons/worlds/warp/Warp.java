package me.maxiiiiii.skyblockdragons.worlds.warp;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Warp {
    HUB(new Location(Bukkit.getWorld("Hub"), -2.5, 70, -68.5, 180, 0)),
    DEEP_MINES(new Location(Bukkit.getWorld("DeepMines"), 1158.5, 210, 71.5, 180, 0));

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
}