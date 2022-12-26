package me.maxiiiiii.skyblockdragons.world;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.mining.Mining;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirements;
import me.maxiiiiii.skyblockdragons.world.attributes.ClickableBlock;
import me.maxiiiiii.skyblockdragons.world.attributes.ItemDisplay;
import me.maxiiiiii.skyblockdragons.world.attributes.LaunchPad;
import me.maxiiiiii.skyblockdragons.world.attributes.WorldAttribute;
import me.maxiiiiii.skyblockdragons.world.events.PlayerStepOnLaunchPadEvent;
import me.maxiiiiii.skyblockdragons.world.region.AllWorldRegion;
import me.maxiiiiii.skyblockdragons.world.region.WorldRegion;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.DeeperMines;
import me.maxiiiiii.skyblockdragons.world.worlds.deepmines.DeepMines;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.GriffinIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.hub.Hub;
import me.maxiiiiii.skyblockdragons.world.worlds.witherisland.WitherIsland;
import org.bukkit.*;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public abstract class WorldSD implements Listener, ConfigurationSerializable {
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
    private final Requirements requirements;

    private final WorldRegion region;
    private final List<WorldRegion> regions;
    private final List<WorldAttribute> attributes;

    private final Cooldown<Player> launchPadCooldown = new Cooldown<>();

    protected WorldSD(World world, String name, Warp warp, WorldModifier... modifiers) {
        this.world = world;
        this.name = name;
        this.warp = warp;
        this.requirements = new Requirements(Functions.splitList("me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement", modifiers));
        this.worldType = Functions.splitList("me.maxiiiiii.skyblockdragons.world.WorldType", modifiers);

        this.region = new AllWorldRegion(name, this);
        this.regions = new ArrayList<>();
        this.attributes = new ArrayList<>();

        SkyblockDragons.plugin.getServer().getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public boolean hasRequirements(PlayerSD player) {
        return this.requirements.hasRequirements(player) && player.getVisitedWorlds().contains(this);
    }

    public void addRegion(WorldRegion region) {
        this.regions.add(region);
    }

    protected void addAttribute(WorldAttribute attribute) {
        this.attributes.add(attribute);
        attribute.onCreate();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() != null) {
            List<ItemDisplay> attributes = this.attributes.stream().filter(a -> a instanceof ItemDisplay).map(a -> (ItemDisplay) a).collect(Collectors.toList());
            attributes.forEach(attribute -> {
                if (e.getClickedBlock().getLocation().equals(attribute.getLocation())) {
                    attribute.display(SkyblockDragons.getPlayer(e.getPlayer()));
                }
            });
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerClickOnBlock(PlayerInteractEvent e) {
        if (WorldSD.get(e.getPlayer().getWorld()) != this) return;

        for (ClickableBlock attribute : this.attributes.stream().filter(a -> a instanceof ClickableBlock).map(a -> (ClickableBlock) a).collect(Collectors.toList())) {
            if (attribute.isClicked(e.getAction()) && e.getClickedBlock().getLocation().equals(attribute.getBlock().getLocation()) && e.getClickedBlock().getType() == attribute.getBlock().getType()) {
                attribute.interact(SkyblockDragons.getPlayer(e.getPlayer()));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerStepOnLaunchPad(PlayerMoveEvent e) {
        Location location = e.getTo();

        if (Functions.cooldown(e.getPlayer(), launchPadCooldown, 500, false)) return;

        for (LaunchPad launchPad : this.attributes.stream().filter(a -> a instanceof LaunchPad).map(a -> (LaunchPad) a).collect(Collectors.toList())) {
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            if (launchPad.isInThreshold(location)) {
                if (launchPad.getWarpTo().getRequirements().hasRequirements(player)) {
                    Bukkit.getPluginManager().callEvent(new PlayerStepOnLaunchPadEvent(player, launchPad, this, location));
                    break;
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have the requirements to use this launch pad!");
                    player.setVelocity(player.getLocation().subtract(launchPad.getLocation()).toVector().normalize().multiply(0.5).setY(0.1));
                }
            }
        }
    }

    public boolean isType(PlayerSD player, WorldType type) {
        return this.worldType.contains(type) || isRegionType(player, type);
    }

    public boolean isRegionType(PlayerSD player, WorldType type) {
        WorldRegion region = this.getRegion(player);
        return region != null && region.isType(type);
    }

    public WorldRegion getRegion(PlayerSD player) {
        for (WorldRegion region : this.regions) {
            if (region.isInRegion(player.getLocation()))
                return region;
        }
        return this.region;
    }

    public WorldRegion getRegion(String name) {
        for (WorldRegion region : this.regions) {
            if (region.getName().equalsIgnoreCase(name))
                return region;
        }
        return this.region;
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

        for (WorldSD world : worlds) {
            world.spawnNPCs();
        }
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

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        return map;
    }
}
