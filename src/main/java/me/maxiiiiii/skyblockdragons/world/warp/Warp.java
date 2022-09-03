package me.maxiiiiii.skyblockdragons.world.warp;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.worlds.InfintyIsland.InfintyIsland;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.DeeperMines;
import me.maxiiiiii.skyblockdragons.worlds.deepmines.DeepMines;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.worlds.griffin.GriffinIsland;
import me.maxiiiiii.skyblockdragons.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.worlds.witherisland.WitherIsland;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Warp {
    HUB(new Location(Hub.world, -2.5, 70, -68.5, 180, 0)),

    DEEP_MINES(new Location(DeepMines.world, 1158.5, 210, 71.5, 180, 0)),
    DEEPER_MINES(new Location(DeeperMines.world, -58.5, 200, -121.5, -90, 0)),

    THE_END(new Location(TheEnd.world, 119.5, 54, 1.5, 90, 0)),

    GRIFFIN_ISLAND(new Location(GriffinIsland.world, 0, 100, 0), "griffin"),

    WITHER_ISLAND(new Location(WitherIsland.world, -109.500,65.00000, 139.500), "wither"),

    INFINTY_ISLAND(new Location(InfintyIsland.world, 0, 0, 0, 90, 0), "infinty"),

    UNWARPABLE(HUB.getLocation())
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
        if (this == UNWARPABLE) return;

        PlayerWarpEvent event = new PlayerWarpEvent(player, this);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public String getName() {
        return Functions.setTitleCase(this.name().replace("_", " "));
    }
}