package me.maxiiiiii.skyblockdragons.worlds.deepermines.forge;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.forge.milestone.ForgeMilestoneReward;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class ForgeMilestone {
    public static final List<ForgeMilestoneReward> rewards = new ArrayList<>();

    private final PlayerSD player;
    private int amount;

    public ForgeMilestone(PlayerSD player, int amount) {
        this.player = player;
        this.amount = amount;
    }

    public void finishedForge() {
        this.amount++;
    }

    public class View extends Menu {
        public View() {
            super(ForgeMilestone.this.player, "Forge Milestone", 6, InventoryGlassType.ALL, false);
        }

        @Override
        public void update() {

        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {

        }
    }
}
