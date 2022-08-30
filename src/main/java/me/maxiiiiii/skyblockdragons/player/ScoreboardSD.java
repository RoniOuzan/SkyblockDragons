package me.maxiiiiii.skyblockdragons.player;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.end.DragonType;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.getNumberFormat;

public class ScoreboardSD {
    private final PlayerSD player;

    private final Scoreboard scoreboard;
    private final Objective objective;
    private final List<Score> scores;

    public ScoreboardSD(PlayerSD player) {
        this.player = player;

        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        this.objective = this.scoreboard.registerNewObjective(this.player.getName(), "dummy");
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");

        this.scores = new ArrayList<>();

        this.update();
    }

    public void setScore(int num, String text) {
        for (Score score : scores) {
            if (score.getScore() == num) {
                this.scoreboard.resetScores(score.getEntry());
            }
        }
        this.scores.removeIf(s -> s.getScore() == num);
        this.objective.getScore(text).setScore(num);
        this.scores.add(objective.getScore(text));
    }

    public void update() {
        List<String> scores = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        scores.add(ChatColor.GRAY + format.format(now) + ChatColor.DARK_GRAY + " " + player.getWorldSD().getName());
        scores.add("");
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
            bitsAdder = ChatColor.AQUA + "(+250 PCoins)";
            if (player.getPlayTime() % 36000L < 5L) {
                player.addBits(250);
            }
        }
        scores.add(ChatColor.WHITE + "PCoin: " + ChatColor.DARK_GREEN + getNumberFormat(player.getBits()) + " " + bitsAdder);
        scores.add(" ");
        if (TheEnd.dragon != null) {
            DragonType dragonType = DragonType.getDragonType(TheEnd.dragon.type.getName());
            if (player.getWorldSD() == WorldSD.THE_END && dragonType != null) {
                scores.add(dragonType + " Dragon");
                scores.add("  " + ChatColor.WHITE + "Dragon's Health: " + ChatColor.GREEN + Functions.getNumberFormat(TheEnd.dragon.getHealth()) + StatType.HEALTH.getIcon());
                scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(TheEnd.dragonDamage.getOrDefault(this.player, 0d)));
                scores.add("  ");
            }
        }
        if (player.playerPet.activePet >= 0) {
            scores.add(ChatColor.WHITE + "Active Pet:");
            scores.add("  " + player.getPetActive().getRarity().getColor() + player.getPetActive().getPetMaterial().getName());
            scores.add("   ");
        }
        scores.add(ChatColor.YELLOW + "sbdragons.revivesmc.net");

        Collections.reverse(scores);

        player.sendMessage(scores.toString());
        for (int i = 0; i < scores.size(); i++) {
            setScore(i, scores.get(i));
        }

        this.player.setScoreboard(this.scoreboard);
    }
}
