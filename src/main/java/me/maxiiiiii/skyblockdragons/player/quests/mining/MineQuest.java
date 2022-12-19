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
    public MineQuest(PlayerSD player, WorldRegion region, boolean isCompleted, Map<String, Integer> mined, Entry<BlockMaterial, Integer>... materials) {
        super(player, region, isCompleted, getDescription(materials));
        this.required = Entry.toMap(materials);
        this.mined = new HashMap<>();
        if (mined.isEmpty()) {
            for (Entry<BlockMaterial, Integer> material : materials) {
                this.mined.put(material.getA(), 0);
            }
        } else {
            for (Map.Entry<String, Integer> material : mined.entrySet()) {
                this.mined.put(BlockMaterial.get(material.getKey()), material.getValue());
            }
        }
    }

    @SafeVarargs
    public MineQuest(PlayerSD player, WorldRegion region, boolean isCompleted, Entry<BlockMaterial, Integer>... materials) {
        this(player, region, isCompleted, new HashMap<>(), materials);
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
        if (this.required.containsKey(e.getMaterial()) && e.getPlayer().equals(player)) {
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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Integer> mined = new HashMap<>();
        for (Map.Entry<BlockMaterial, Integer> entry : this.mined.entrySet()) {
            mined.put(entry.getKey().name(), entry.getValue());
        }
        map.put("Player", player);
        map.put("IsCompleted", isCompleted());
        map.put("Mined", mined);
        return map;
    }
}
