package me.maxiiiiii.skyblockdragons.world.warp;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.BooleanRequirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirements;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.events.PlayerWarpEvent;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.DeeperMines;
import me.maxiiiiii.skyblockdragons.world.worlds.deepmines.DeepMines;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.GriffinIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.world.worlds.witherisland.WitherIsland;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum Warp {
    HUB(new Location(Hub.world, -2.5, 70, -82.5, 180, 0), Functions.applySkull("956d9b65-91bb-417e-a8af-8b47509b8b78", "eyJ0aW1lc3RhbXAiOjE1NTkyMTU0MTY5MDksInByb2ZpbGVJZCI6IjQxZDNhYmMyZDc0OTQwMGM5MDkwZDU0MzRkMDM4MzFiIiwicHJvZmlsZU5hbWUiOiJNZWdha2xvb24iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q3Y2M2Njg3NDIzZDA1NzBkNTU2YWM1M2UwNjc2Y2I1NjNiYmRkOTcxN2NkODI2OWJkZWJlZDZmNmQ0ZTdiZjgifX19")),
    DEEP_MINES(new Location(DeepMines.world, 1158.5, 210, 71.5, 180, 0), "mines", Functions.applySkull("79b29c85-75b6-4e48-801c-cb9b5471ffee", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY5YTFmMTE0MTUxYjQ1MjEzNzNmMzRiYzE0YzI5NjNhNTAxMWNkYzI1YTY1NTRjNDhjNzA4Y2Q5NmViZmMifX19"), new BooleanRequirement(p -> p.isCompletedInteraction("MIKE"))),
    DEEPER_MINES(new Location(DeeperMines.world, -58.5, 200, -121.5, -90, 0), "deeper", new ItemStack(Material.PRISMARINE_SHARD)),
    THE_END(new Location(TheEnd.world, 119.5, 54, 1.5, 90, 0), "end", new ItemStack(Material.EYE_OF_ENDER)),
    GRIFFIN_ISLAND(new Location(GriffinIsland.world, 0, 100, 0), "griffin", Functions.applySkull("357dc70d-fd29-3615-88fb-b465ade33f19", "ewogICJ0aW1lc3RhbXAiIDogMTU5ODQ0Njc0MjE0MSwKICAicHJvZmlsZUlkIiA6ICI0MWQzYWJjMmQ3NDk0MDBjOTA5MGQ1NDM0ZDAzODMxYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNZWdha2xvb24iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGMyN2UzY2I1MmE2NDk2OGU2MGM4NjFlZjFhYjg0ZTBhMGNiNWYwN2JlMTAzYWM3OGRhNjc3NjE3MzFmMDBjOCIKICAgIH0KICB9Cn0=")),
    WITHER_ISLAND(new Location(WitherIsland.world, -109.5,65, 139.5), "wither", Functions.applySkull("d928ce5e-e75e-3cdc-aaf1-0c93d49b5c31", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjVlYzk2NDY0NWE4ZWZhYzc2YmUyZjE2MGQ3Yzk5NTYzNjJmMzJiNjUxNzM5MGM1OWMzMDg1MDM0ZjA1MGNmZiJ9fX0=")),
    BEAR_ISLAND(new Location(BearIsland.world, -51.5, 90, 212.5), "bear", Functions.applySkull("877042bf-3a95-4a20-8c42-aaa234bfea69", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIxMjUwM2Q2MWM0OWY3MDFmZWU4NjdkNzkzZjFkY2M1MjJlNGQ3YzVjNDFhNjhmMjk1MTU3OWYyNGU3Y2IyYSJ9fX0=")),
    UNWARPABLE(HUB.getLocation(), new ItemStack(Material.SKULL_ITEM))
    ;

    private final Location location;
    private final String alias;
    private final ItemStack item;
    private final Requirements requirements;

    Warp(Location location, String alias, ItemStack item, Requirement... requirements) {
        this.location = location;
        this.alias = alias;
        this.item = item;
        this.requirements = new Requirements(requirements);
    }

    Warp(Location location, ItemStack item, Requirement... requirements) {
        this(location, null, item, requirements);
    }

    public void warp(PlayerSD player) {
        if (this == UNWARPABLE) return;

        PlayerWarpEvent event = new PlayerWarpEvent(player, this);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public WorldSD getWorld() {
        return WorldSD.get(this.location.getWorld());
    }

    public boolean hasRequirements(PlayerSD player) {
        return this.requirements.hasRequirements(player) && WorldSD.get(this.location.getWorld()).hasRequirements(player);
    }

    public String getName() {
        return Functions.setTitleCase(this.name().replace("_", " "));
    }
}