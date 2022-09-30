package me.maxiiiiii.skyblockdragons.player;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardOptions;
import dev.jcsoftware.jscoreboards.JScoreboardTabHealthStyle;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.worlds.end.DragonType;
import me.maxiiiiii.skyblockdragons.worlds.end.TheEnd;
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

        this.scoreboard = new JPerPlayerMethodBasedScoreboard(new JScoreboardOptions(JScoreboardTabHealthStyle.NUMBER, true));
        this.scoreboard.addPlayer(player.getPlayer());
        this.scoreboard.setTitle(player, ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");

        this.update();
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
            bitsAdder = ChatColor.AQUA + "(+250)";
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
                scores.add("  " + ChatColor.WHITE + "Dragon's Health: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragon.getHealth()) + StatType.HEALTH.getIcon());
                scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragonDamage.getOrDefault(this.player, 0d)));
                scores.add("  ");
            }
        }
        if (player.playerPet.activePet >= 0) {
            scores.add(ChatColor.WHITE + "Active Pet:");
            scores.add("  " + player.getPetActive().getRarity().getColor() + player.getPetActive().getPetMaterial().getName());
            scores.add("   ");
        }
        scores.add(ChatColor.YELLOW + "sbdragons.ml");

        for (int i = 0; i < scores.size(); i++) {
            String score = scores.get(i);
            if (score.length() > 32){
                score = score.substring(0, 32);
                scores.set(i, score);
            }
        }

        this.scoreboard.setLines(player, scores);
        this.scoreboard.updateScoreboard();
    }
}
