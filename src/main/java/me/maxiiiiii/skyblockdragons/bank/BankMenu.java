package me.maxiiiiii.skyblockdragons.bank;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.Functions.createItem;
import static me.maxiiiiii.skyblockdragons.Functions.setName;
import static me.maxiiiiii.skyblockdragons.SkyblockDragons.purses;

public class BankMenu {
    public static void openBank(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Bank");

        ItemStack coopBank = createItem(Material.ENDER_CHEST, ChatColor.GREEN + "Co-op Bank Account", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "This account is shared with", ChatColor.GRAY + "all the members of your", ChatColor.AQUA + "co-op" + ChatColor.GRAY + "!", "", ChatColor.GRAY + "Balance: " + ChatColor.GOLD + 0, "", ChatColor.YELLOW + "Click to open account!")));
        inventory.setItem(11, coopBank);

        ItemStack personalBank = createItem(Material.CHEST, ChatColor.GREEN + "Personal Bank Account", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Your very own account!", "", ChatColor.GRAY + "Stored up to " + ChatColor.GOLD + "20% " + ChatColor.GRAY + "of", ChatColor.GRAY + "your " + ChatColor.AQUA + "co-op " + ChatColor.GRAY + "account.", "", ChatColor.GRAY + "Both account are accrue", ChatColor.GRAY + "interest, but you will only earn " + ChatColor.GOLD + "20% " + ChatColor.GRAY + "of it on your", ChatColor.GRAY + "personal account.", "", ChatColor.GRAY + "Balance: " + ChatColor.GOLD + 0, "", ChatColor.YELLOW + "Click to open account!")));
        inventory.setItem(15, personalBank);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBankPersonal(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Personal Bank Account");

        ItemStack deposit = createItem(Material.CHEST, ChatColor.GREEN + "Deposit Coins", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + 0, "", ChatColor.GRAY + "Store coins in the bank to keep", ChatColor.GRAY + "them safe while you go on", ChatColor.GRAY + "adventures!", "", ChatColor.GRAY + "You will earn " + ChatColor.AQUA + "2% " + ChatColor.GRAY + "interest every day", ChatColor.GRAY + "season for your first " + ChatColor.GOLD + "2 million", ChatColor.GRAY + "banked coins.", "", ChatColor.GRAY + "Until interest: " + ChatColor.AQUA + "0h", "", ChatColor.YELLOW + "Click to make a deposit!")));
        inventory.setItem(11, deposit);

        ItemStack withdraw = createItem(Material.DROPPER, ChatColor.GREEN + "Withdraw Coins", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + 0, "", ChatColor.GRAY + "Take your coins out of the bank", ChatColor.GRAY + "in order to spend them.", "", ChatColor.YELLOW + "Click to withdraw coins!")));
        inventory.setItem(13, withdraw);

        ItemStack recentTrans = createItem(Material.MAP, ChatColor.GREEN + "Recent Transactions");
        inventory.setItem(15, recentTrans);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "To Bank")));
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBankPersonalDeposit(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Personal Bank Deposit");

        ItemStack depositAll = createItem(Material.CHEST, 64, ChatColor.GREEN + "Fill the account", ChatColor.DARK_GRAY + "Bank deposit", "", ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + 0, ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + purses.get(player.getUniqueId()), "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(11, depositAll);

        ItemStack depositHalf = depositAll.clone();
        depositHalf.setAmount(32);
        inventory.setItem(13, depositHalf);

        ItemStack specificAmount = createItem(Material.SIGN, ChatColor.GREEN + "Specific Amount", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + 0, "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(15, specificAmount);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Personal Bank Account");
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBankPersonalWithdraw(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Personal Bank Withdraw");

        ItemStack withdrawAll = createItem(Material.DROPPER, 64, ChatColor.GREEN + "Everything in the account", ChatColor.DARK_GRAY + "Bank Withdrawal", "", ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + 0, ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + 0, "", ChatColor.YELLOW + "Click to withdraw coins!");
        inventory.setItem(10, withdrawAll);

        ItemStack withdrawHalf = withdrawAll.clone();
        withdrawHalf.setAmount(32);
        setName(withdrawHalf, ChatColor.GREEN + "Half the account");
        inventory.setItem(12, withdrawHalf);

        ItemStack withdraw20 = withdrawAll.clone();
        withdraw20.setAmount(32);
        setName(withdraw20, ChatColor.GREEN + "Withdraw 20%");
        inventory.setItem(12, withdraw20);

        ItemStack specificAmount = createItem(Material.SIGN, ChatColor.GREEN + "Specific Amount", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + 0, "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(15, specificAmount);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Personal Bank Account");
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }
}
