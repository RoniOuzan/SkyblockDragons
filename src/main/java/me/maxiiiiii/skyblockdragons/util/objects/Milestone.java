package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Milestone {
    protected final PlayerSD player;
    protected final String variableName;
    protected final List<MilestoneReward> rewards;
    protected int amount;

    protected Milestone(PlayerSD player, String variableName, MilestoneReward... rewards) {
        this.player = player;
        this.variableName = variableName;
        this.rewards = Arrays.asList(rewards);

        this.amount = Variables.get(player.getUniqueId(), variableName, 0, 0);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount() {
        this.amount++;
    }

    public abstract void view();

    public int getData(int reward) {
        if (rewards.get(reward).isClaimed())
            return 5;
        if (this.amount >= rewards.get(reward).getRequiredAmount())
            return 4;
        return 14;
    }

    public List<String> getLores(int reward) {
        List<String> lores = Arrays.asList(
                "",
                ChatColor.GRAY + "Progress: " + ChatColor.GREEN + Math.min(100, (amount / rewards.get(reward).getRequiredAmount()) * 100) + "%",
                Functions.progressBar(amount, rewards.get(reward).getRequiredAmount(), 20) + ChatColor.YELLOW + amount + ChatColor.GOLD + "/" + ChatColor.YELLOW + rewards.get(reward).getRequiredAmount(),
                "",
                ChatColor.GRAY + "Rewards:"
        );
        lores.addAll(rewards.get(reward).getRewards().stream().map(r -> ChatColor.DARK_GRAY + "+ " + r.getLore()).collect(Collectors.toList()));

        if (rewards.get(reward).isClaimed()) {
            lores.add("");
            lores.add(ChatColor.GREEN + "Claimed");
        }
        return lores;
    }
}
