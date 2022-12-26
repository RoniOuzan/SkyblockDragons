package me.maxiiiiii.skyblockdragons.player;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.quests.Quest;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerQuest;
import me.maxiiiiii.skyblockdragons.player.slayer.SlayerType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.worlds.end.DragonType;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import me.maxiiiiii.skyblockdragons.world.worlds.witherisland.WitherIsland;
import org.bukkit.ChatColor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.getNumberFormat;

public class ScoreboardSD {
    private final JPerPlayerMethodBasedScoreboard scoreboard;
    private final PlayerSD player;

    public ScoreboardSD(PlayerSD player) {
        this.player = player;

        this.scoreboard = new JPerPlayerMethodBasedScoreboard();
        this.scoreboard.addPlayer(player.getPlayer());

        this.update();
    }

    public void update() {
        this.scoreboard.setTitle(player, ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");

        List<String> scores = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        scores.add(ChatColor.GRAY + format.format(now) + ChatColor.DARK_GRAY + " " + player.getWorldSD().getName() + (player.getRegion() != player.getWorldSD().getRegion() ? " - " + player.getRegion().getName() : ""));
        scores.add(""); // 0
        scores.add(ChatColor.WHITE + "Player: " + ChatColor.GREEN + player.getName());
        if (player.getLastCoins() != player.getCoins()) {
            player.setCoins(player.getCoins());
            if (player.getCoins() - player.getLastCoins() > 0)
                scores.add(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(player.getPurse()) + " (+" + Functions.getShortNumber(player.getCoins() - player.getLastCoins()) + ")");
            else
                scores.add(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(player.getPurse()) + " (" + Functions.getShortNumber(player.getCoins() - player.getLastCoins()) + ")");
            player.setLastCoins(player.getCoins());
        } else {
            scores.add(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(player.getPurse()));
        }
        String bitsAdder = "";

        if (player.getPlayTime() % 36000L >= 0L && player.getPlayTime() % 36000L < 20L) {
            bitsAdder = ChatColor.AQUA + "(+250)";
            if (player.getPlayTime() % 36000L < 5L) {
                player.addBits(250);
            }
        }
        scores.add(ChatColor.WHITE + "PCoin: " + ChatColor.DARK_GREEN + getNumberFormat(player.getBits()) + " " + bitsAdder);
        scores.add(" "); // 1

        if (player.playerPet.getActivePetSlot() >= 0) {
            scores.add(ChatColor.WHITE + "Active Pet:");
            scores.add("  " + player.getActivePet().getRarity().getColor() + player.getActivePetMaterial().getName());
            scores.add("  "); // 2
        }


        if (TheEnd.dragon != null && player.getWorldSD() == WorldSD.THE_END) {
            scores.add(DragonType.getDragonType(TheEnd.dragon.material.getName()) + " Dragon");
            scores.add("  " + ChatColor.WHITE + "Dragon's Health: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragon.getHealth()) + StatTypes.HEALTH.getIcon());
            scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragonDamage.getOrDefault(this.player, 0d)));
            scores.add("   "); // 3
        } else if(WitherIsland.wither != null && player.getWorldSD() == WorldSD.WITHER_ISLAND) {
            scores.add(WitherIsland.wither.getMaterial().getName());
            scores.add("  " + ChatColor.WHITE + "Wither's Health: " + ChatColor.GREEN + Functions.getShortNumber(WitherIsland.wither.getHealth()) + StatTypes.HEALTH.getIcon());
            scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(WitherIsland.witherDamage.getOrDefault(player.getUniqueId(), 0d)));
            scores.add("   "); // 3
        } else if (player.getSlayers().getQuest().isActive()) {
            SlayerQuest quest = player.getSlayers().getQuest();
            scores.add(ChatColor.WHITE + "Slayer Quest");
            scores.add(SlayerType.getTiersColors(quest.getTier()) + quest.getType().getName() + " " + quest.getTier());
            if (quest.getState() == SlayerQuest.SlayerQuestState.SPAWNED) {
                scores.add(ChatColor.YELLOW + "Slay the boss!");
            } else if (quest.getState() == SlayerQuest.SlayerQuestState.SLAIN) {
                scores.add(ChatColor.YELLOW + "Boss Slain!");
            } else if (quest.getState() == SlayerQuest.SlayerQuestState.STARTED) {
                scores.add(ChatColor.YELLOW + Functions.getNumberFormat(quest.getCurrentXp()) + ChatColor.GRAY + "/" + ChatColor.RED + Functions.getNumberFormat(quest.getNeedXp()) + ChatColor.GRAY + " Combat XP");
            } else if (quest.getState() == SlayerQuest.SlayerQuestState.FAILED) {
                scores.add(ChatColor.RED + "Boss Failed!");
            }
            scores.add("   "); // 3
        } else if (player.getNotCompletedQuestInRegion().size() > 0) {
            for (Quest quest : player.getNotCompletedQuestInRegion()) {
                scores.addAll(quest.getScoreboardScores());
            }
            scores.add("   "); // 3
        }

        scores.add(ChatColor.YELLOW + "sbdragons.ml");

        for (int i = 0; i < scores.size(); i++) {
            String score = scores.get(i);
            if (score.length() > 32){
                score = score.substring(0, 32);
                scores.set(i, score);
            }
        }
        this.scoreboard.setTitle(player, ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");
        this.scoreboard.setLines(player, scores);
        this.scoreboard.updateScoreboard();
    }
}
