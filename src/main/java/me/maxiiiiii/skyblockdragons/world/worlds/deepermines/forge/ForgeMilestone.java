package me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge;

import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Milestone;
import me.maxiiiiii.skyblockdragons.util.objects.MilestoneReward;
import me.maxiiiiii.skyblockdragons.util.objects.reward.Rewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ForgeMilestone extends Milestone {
    public ForgeMilestone(PlayerSD player) {
        super(player, "ForgeMilestone",
                new MilestoneReward(new Rewards(), 10, false),
                new MilestoneReward(new Rewards(), 50, false),
                new MilestoneReward(new Rewards(), 250, false),
                new MilestoneReward(new Rewards(), 500, false)
        );
    }

    @Override
    public void view() {
        new View(this.player);
    }

    public class View extends Menu {
        protected View(PlayerSD player) {
            super(player, "Forge Milestone", 6, InventoryGlassType.ALL, false);
        }

        @Override
        public void update() {
            this.setItem(20, createItem(Material.STAINED_GLASS_PANE, getData(0), ChatColor.GREEN + "Milestone 1 Rewards", "REWARD_1", getLores(0)));
            this.setItem(21, createItem(Material.STAINED_GLASS_PANE, getData(1), ChatColor.GREEN + "Milestone 2 Rewards", "REWARD_2", getLores(1)));
            this.setItem(23, createItem(Material.STAINED_GLASS_PANE, getData(2), ChatColor.GREEN + "Milestone 3 Rewards", "REWARD_3", getLores(2)));
            this.setItem(24, createItem(Material.STAINED_GLASS_PANE, getData(3), ChatColor.GREEN + "Milestone 4 Rewards", "REWARD_4", getLores(3)));
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).contains("REWARD_")) {
                int reward = Integer.parseInt(this.getNBT(e.getCurrentItem()).split("_")[1]);
                if (!ForgeMilestone.this.rewards.get(reward).isClaimed()) {
                    ForgeMilestone.this.rewards.get(reward).setClaimed(true);
                    ForgeMilestone.this.rewards.get(reward).getRewards().give(this.player);
                    this.player.sendMessage(ChatColor.GREEN + "You have claimed your milestone " + reward + " reward.");
                    this.player.closeInventory();
                }
            }
        }
    }
}
