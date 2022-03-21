package me.maxiiiiii.skyblockdragons.skill;

import me.maxiiiiii.skyblockdragons.stat.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.Functions.*;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;

public class SkillMenu {
    public static void openSkillsMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Your Skills");

        ItemStack glass = createItem(Material.STAINED_GLASS_PANE, 15, ChatColor.RESET + "");
        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, glass);
        }

        PlayerData playerData = players.get(player.getUniqueId());

        double skillAverage = average(
                playerData.getSkill().getFarmingSkill().getLevel(),
                playerData.getSkill().getMiningSkill().getLevel(),
                playerData.getSkill().getCombatSkill().getLevel(),
                playerData.getSkill().getForagingSkill().getLevel(),
                playerData.getSkill().getFishingSkill().getLevel(),
                playerData.getSkill().getEnchantingSkill().getLevel(),
                playerData.getSkill().getAlchemySkill().getLevel(),
                playerData.getSkill().getTamingSkill().getLevel(),
                playerData.getSkill().getDungeoneeringSkill().getLevel()
        );
        ItemStack yourSkills = createItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Your Skills", ChatColor.GRAY + "View your Skill progression and", ChatColor.GRAY + "rewards", "", ChatColor.GOLD + "" + skillAverage + " Skill Avg. " + ChatColor.DARK_GRAY + "(non-cosmetic)", "", ChatColor.YELLOW + "Click to show ranking!");
        inventory.setItem(4, yourSkills);

        ItemStack farming = createItem(Material.GOLD_HOE, ChatColor.GREEN + "Farming " + playerData.getSkill().getFarmingSkill().getLevel(), getItemLores(playerData.getSkill().getFarmingSkill()));
        inventory.setItem(19, farming);

        ItemStack mining = createItem(Material.STONE_PICKAXE, ChatColor.GREEN + "Mining " + playerData.getSkill().getMiningSkill().getLevel(), getItemLores(playerData.getSkill().getMiningSkill()));
        inventory.setItem(20, mining);

        ItemStack combat = createItem(Material.STONE_SWORD, ChatColor.GREEN + "Combat " + playerData.getSkill().getCombatSkill().getLevel(), getItemLores(playerData.getSkill().getCombatSkill()));
        inventory.setItem(21, combat);

        ItemStack foraging = createItem(Material.SAPLING, 1, 3, ChatColor.GREEN + "Foraging " + playerData.getSkill().getForagingSkill().getLevel(), getItemLores(playerData.getSkill().getForagingSkill()));
        inventory.setItem(22, foraging);

        ItemStack fishing = createItem(Material.FISHING_ROD, ChatColor.GREEN + "Fishing " + playerData.getSkill().getFishingSkill().getLevel(), getItemLores(playerData.getSkill().getFishingSkill()));
        inventory.setItem(23, fishing);

        ItemStack enchanting = createItem(Material.ENCHANTMENT_TABLE, ChatColor.GREEN + "Enchanting " + playerData.getSkill().getEnchantingSkill().getLevel(), getItemLores(playerData.getSkill().getEnchantingSkill()));
        inventory.setItem(24, enchanting);

        ItemStack alchemy = createItem(Material.BREWING_STAND_ITEM, ChatColor.GREEN + "Alchemy " + playerData.getSkill().getAlchemySkill().getLevel(), getItemLores(playerData.getSkill().getAlchemySkill()));
        inventory.setItem(25, alchemy);

        ItemStack taming = createItem(Material.MONSTER_EGG, ChatColor.GREEN + "Taming " + playerData.getSkill().getTamingSkill().getLevel(), getItemLores(playerData.getSkill().getTamingSkill()));
        inventory.setItem(30, taming);

        ItemStack dungeoneering = getSkull(createItem(Material.SKULL_ITEM, ChatColor.GREEN + "Dungeoneering " + playerData.getSkill().getDungeoneeringSkill().getLevel(), getItemLores(playerData.getSkill().getDungeoneeringSkill())), "3549f087-6655-4e1b-9b71-ecc1c59e59b7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTI3MzcyODA5YjU2OTQ2NDZmNDRkN2E4MzdkNGZlNjZlNWVmNjJhZTcyNzAxMTcxNjUxYjNhNzgwY2IxZjljIn19fQ==");
        inventory.setItem(32, dungeoneering);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back");
        inventory.setItem(48, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(49, close);

        player.openInventory(inventory);
    }

    private static ArrayList<String> getItemLores(AbstractSkill skill) {
        ArrayList<String> lores = new ArrayList<>();
        lores.addAll(loreBuilder(skill.getDescription()));
        lores.add("");
        double farmingPercent = (skill.getCurrentXp() / skill.getCurrentNeedXp() * 100);
        lores.add(ChatColor.GRAY + "Progress to Level " + (skill.getLevel() + 1) + ": " + ChatColor.YELLOW + farmingPercent + "%");
        StringBuilder progressBar = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            if (i < farmingPercent / 5) {
                progressBar.append(ChatColor.GREEN);
            } else {
                progressBar.append(ChatColor.WHITE);
            }
            progressBar.append("-");
        }
        lores.add(progressBar + " " + ChatColor.YELLOW + getNumberFormat(skill.getCurrentXp()) + ChatColor.GOLD + "/" + ChatColor.YELLOW + getShortNumber(skill.getCurrentNeedXp()));
        lores.add("");
        lores.add(ChatColor.GRAY + "Level " + (skill.getLevel() + 1) + " Rewards:");
        lores.add("  " + ChatColor.YELLOW + skill.getRewards().getName() + " " + skill.getLevel());
        for (String line : loreBuilder(skill.getRewards().getPassive(), ChatColor.WHITE, 20)) {
            lores.add("    " + line);
        }
        lores.add("  " + ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + getInt(skill.getRewards().getStatAmount() + "") + " " + skill.getRewards().getStat().getIconAndText());
        lores.add("  " + ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + getNumberFormat(skill.getRewards().getCoinsAmount()) + " " + ChatColor.GRAY + "Coins");
        lores.add("");
        lores.add(ChatColor.YELLOW + "Click to view!");
        return lores;
    }
}
