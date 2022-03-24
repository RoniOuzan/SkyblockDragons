package me.maxiiiiii.skyblockdragons.stat;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.bits.BitsUtil;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.skill.Skill;
import me.maxiiiiii.skyblockdragons.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static me.maxiiiiii.skyblockdragons.Functions.*;

public class PlayerFunctions {
    public static void loadPlayerData(Player player) {
        SkyblockDragons.purses.put(player.getUniqueId(), 0d);
        SkyblockDragons.bits.put(player.getUniqueId(), 0L);
        player.setHealthScale(40d);

        ArrayList<WardrobeSlot> wardrobeSlots = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            wardrobeSlots.add(new WardrobeSlot(
                    i,
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 0), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 1), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 2), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 3), "null"), null)
            ));
        }

        SkyblockDragons.players.put(player.getUniqueId(), new PlayerSD(
                player,
                new Skill(
                        new FarmingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Farming", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Farming", 2, "0"))),
                        new MiningSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Mining", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Mining", 2, "0"))),
                        new CombatSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Combat", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Combat", 2, "0"))),
                        new ForagingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Foraging", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Foraging", 2, "0"))),
                        new FishingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Fishing", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Fishing", 2, "0"))),
                        new EnchantingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Enchanting", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Enchanting", 2, "0"))),
                        new AlchemySkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Alchemy", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Alchemy", 2, "0"))),
                        new TamingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Taming", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Taming", 2, "0"))),
                        new DungeoneeringSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Dungeoneering", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Dungeoneering", 2, "0")))
                ), new Wardrobe(wardrobeSlots)
        ));

        ArrayList<ItemStack> accessories = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            try {
                accessories.add((ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariable(player.getUniqueId(), "AccessoryBag", i).getValue()));
            } catch (NullPointerException ex) {
                accessories.add(new ItemStack(Material.AIR));
            }
        }
        SkyblockDragons.players.get(player.getUniqueId()).setAccessoryBag(accessories);

        try {
            SkyblockDragons.purses.put(player.getUniqueId(), Double.parseDouble(Variables.getVariable(player.getUniqueId(), "Purse").getValue()));
        } catch (NullPointerException ex) {
            SkyblockDragons.purses.put(player.getUniqueId(), 0d);
        }

        try {
            SkyblockDragons.bits.put(player.getUniqueId(), Long.parseLong(Variables.getVariable(player.getUniqueId(), "Bits").getValue()));
        } catch (NullPointerException ex) {
            SkyblockDragons.bits.put(player.getUniqueId(), 0L);
        }

        if (!SkyblockDragons.disablePlayTime) {
            try {
                SkyblockDragons.playTime.put(player.getUniqueId(), Long.parseLong(Variables.getVariable(player.getUniqueId(), "PlayTime").getValue()));
            } catch (NullPointerException ex) {
                SkyblockDragons.playTime.put(player.getUniqueId(), 0L);
            }
        }
    }

    public static void setScoreboardScores(Player player) {
        PlayerSD playerSD = SkyblockDragons.players.get(player.getUniqueId());

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(playerSD.getPlayer().getName(), "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");
        ArrayList<Score> scores = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        scores.add(objective.getScore(ChatColor.GRAY + format.format(now) + ChatColor.DARK_GRAY + " m000F"));
        scores.add(objective.getScore(""));
        scores.add(objective.getScore(ChatColor.WHITE + "Player: " + ChatColor.GREEN + playerSD.getPlayer().getName()));
        scores.add(objective.getScore(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(playerSD.getPurse())));
        String bitsAdder = "";
        if (SkyblockDragons.playTime.getOrDefault(playerSD.getPlayer().getUniqueId(), 0L) % 36000L >= 0L && SkyblockDragons.playTime.getOrDefault(playerSD.getPlayer().getUniqueId(), 0L) % 36000L < 20L) {
            bitsAdder = ChatColor.AQUA + "(+250 Bits)";
            if (SkyblockDragons.playTime.getOrDefault(playerSD.getPlayer().getUniqueId(), 0L) % 36000L < 5L) {
                BitsUtil.add(playerSD.getPlayer(), 250L);
            }
        }
        scores.add(objective.getScore(ChatColor.WHITE + "Bits: " + ChatColor.AQUA + getNumberFormat(SkyblockDragons.bits.get(playerSD.getPlayer().getUniqueId())) + " " + bitsAdder));
        scores.add(objective.getScore(" "));
        if (playerSD.getActivePet() >= 0) {
            scores.add(objective.getScore(ChatColor.WHITE + "Active Pet:"));
            scores.add(objective.getScore("  " +playerSD.getPetActive().getRarity().getColor() + playerSD.getPetActive().getPetMaterial().getName()));
            scores.add(objective.getScore("  "));
        }
        scores.add(objective.getScore(ChatColor.YELLOW + "www.error.net"));
        Collections.reverse(scores);

        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).setScore(i);
        }

        player.setScoreboard(scoreboard);
    }
}
