package me.maxiiiiii.skyblockdragons.player;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearIsland;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.BearType;
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
            DragonType dragonType = DragonType.getDragonType(TheEnd.dragon.material.getName());
            if (player.getWorldSD() == WorldSD.THE_END && dragonType != null) {
                scores.add(dragonType + " Dragon");
                scores.add("  " + ChatColor.WHITE + "Dragon's Health: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragon.getHealth()) + StatTypes.HEALTH.getIcon());
                scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(TheEnd.dragonDamage.getOrDefault(this.player, 0d)));
                scores.add("  ");
            }
        }
        if(WitherIsland.wither != null){
            String name = WitherIsland.wither.material.name;
            if (player.getWorldSD() == WorldSD.WITHER_ISLAND && name != null) {
                scores.add(name);
                scores.add("  " + ChatColor.WHITE + "Wither's Health: " + ChatColor.GREEN + Functions.getShortNumber(WitherIsland.wither.getHealth()) + StatTypes.HEALTH.getIcon());
                scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(WitherIsland.witherDamage.getOrDefault(player.getUniqueId(), 0d)));
                scores.add("  ");
            }
        }
        if (BearIsland.bear != null) {
            BearType bearType = BearType.getBearType(BearIsland.bear.material.getName());
            if (player.getWorldSD() == WorldSD.THE_END && bearType != null) {
                scores.add(bearType + " Bear");
                scores.add("  " + ChatColor.WHITE + "Bear's Health: " + ChatColor.GREEN + Functions.getShortNumber(BearIsland.bear.getHealth()) + StatTypes.HEALTH.getIcon());
                scores.add("  " + ChatColor.WHITE + "Your Damage: " + ChatColor.GREEN + Functions.getShortNumber(BearIsland.bearDamage.getOrDefault(this.player, 0d)));
                scores.add("  ");
            }
        }
        if (player.playerPet.getActivePetSlot() >= 0) {
            scores.add(ChatColor.WHITE + "Active Pet:");
            scores.add("  " + player.getActivePet().getRarity().getColor() + player.getActivePetMaterial().getName());
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
        this.scoreboard.setTitle(player, ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");
        this.scoreboard.setLines(player, scores);
        this.scoreboard.updateScoreboard();
    }
}
