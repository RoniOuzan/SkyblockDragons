package me.maxiiiiii.skyblockdragons.player.quests;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.world.region.WorldRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.function.Function;

public abstract class Quest implements Listener {
    protected final PlayerSD player;
    private final Function<PlayerSD, String> description;
    private final WorldRegion region;

    private boolean isCompleted;

    public Quest(PlayerSD player, WorldRegion region, Function<PlayerSD, String> description) {
        this.player = player;
        this.description = description;
        this.region = region;

        this.isCompleted = false;

        Bukkit.getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public Quest(PlayerSD player, WorldRegion region, String description) {
        this(player, region, p -> description);
    }

    public void start() {
        this.player.startQuest(this);
    }

    public void complete() {
        this.isCompleted = true;
        player.getActiveQuests().remove(this);

        player.playSound(Sound.ENTITY_PLAYER_LEVELUP);
        player.sendMessage(ChatColor.GREEN + "You have completed the quest!");
    }

    public abstract List<String> getScoreboardScores();

    public WorldRegion getRegion() {
        return region;
    }

    public String getDescription(PlayerSD player) {
        return description.apply(player);
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}
