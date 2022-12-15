package me.maxiiiiii.skyblockdragons.player.quests.mining;

import me.maxiiiiii.skyblockdragons.mining.events.PlayerBreakBlockEvent;
import me.maxiiiiii.skyblockdragons.mining.material.BlockMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.world.region.WorldRegion;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MineQuest extends MiningQuest {
    private final Map<BlockMaterial, Integer> required;
    private final Map<BlockMaterial, Integer> mined;

    @SafeVarargs
    public MineQuest(PlayerSD player, WorldRegion region, Entry<BlockMaterial, Integer>... materials) {
        super(player, region, getDescription(materials));
        this.required = new HashMap<>();
        for (Entry<BlockMaterial, Integer> material : materials) {
            this.required.put(material.getA(), material.getB());
        }
        this.mined = new HashMap<>();
        for (Entry<BlockMaterial, Integer> material : materials) {
            this.mined.put(material.getA(), 0);
        }
    }

    @Override
    public List<String> getScoreboardScores() {
        List<String> scores = new ArrayList<>();
        scores.add(ChatColor.WHITE + "Mine Blocks:");
        for (Map.Entry<BlockMaterial, Integer> entry : this.mined.entrySet()) {
            int required = this.required.get(entry.getKey());
            scores.add("  " + ChatColor.GRAY + entry.getKey().toString() + ChatColor.DARK_GRAY + " (" + ChatColor.GREEN + Math.min(entry.getValue(), required) + ChatColor.DARK_GRAY + "/" + ChatColor.GRAY + required + ChatColor.DARK_GRAY + ")");
        }
        return scores;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerBreakBlock(PlayerBreakBlockEvent e) {
        if (this.required.containsKey(e.getMaterial())) {
            this.mined.put(e.getMaterial(), this.mined.getOrDefault(e.getMaterial(), 0) + 1);

            if (this.required.entrySet().stream().allMatch(en -> this.mined.get(en.getKey()) >= en.getValue())) {
                this.complete();
            }
        }
    }

    @SafeVarargs
    private static String getDescription(Entry<BlockMaterial, Integer>... materials) {
        StringBuilder builder = new StringBuilder(ChatColor.WHITE.toString()).append("Mine ");
        for (int i = 0; i < materials.length; i++) {
            Entry<BlockMaterial, Integer> material = materials[i];

            builder.append(ChatColor.GREEN).append(material.getB()).append(" ").append(ChatColor.WHITE).append(material.getA().toString());

            if (i == materials.length - 2) {
                builder.append(" and ");
            } else if (i < materials.length - 2) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
