package me.maxiiiiii.skyblockdragons.player.bank;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class BankMenu {
    public static void openBank(PlayerSD player) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + "Bank");

        putGlasses(inventory);

        ItemStack coopBank = createItem(Material.ENDER_CHEST, ChatColor.GREEN + "Co-op Bank Account", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "This account is shared with", ChatColor.GRAY + "all the members of your", ChatColor.AQUA + "co-op" + ChatColor.GRAY + "!", "", ChatColor.GRAY + "Balance: " + ChatColor.GOLD + player.getCoopBank(), "", ChatColor.YELLOW + "Click to open account!")));
        inventory.setItem(11, coopBank);

        ItemStack personalBank = createItem(Material.CHEST, ChatColor.GREEN + "Personal Bank Account", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Your very own account!", "", ChatColor.GRAY + "Stored up to " + ChatColor.GOLD + "20% " + ChatColor.GRAY + "of", ChatColor.GRAY + "your " + ChatColor.AQUA + "co-op " + ChatColor.GRAY + "account.", "", ChatColor.GRAY + "Both account are accrue", ChatColor.GRAY + "interest, but you will only earn " + ChatColor.GOLD + "20% " + ChatColor.GRAY + "of it on your", ChatColor.GRAY + "personal account.", "", ChatColor.GRAY + "Balance: " + ChatColor.GOLD + player.getPersonalBank(), "", ChatColor.YELLOW + "Click to open account!")));
        inventory.setItem(15, personalBank);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBank(PlayerSD player, BankAccount.Type type) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + type.toString() + " Bank Account");

        putGlasses(inventory);

        ItemStack deposit = createItem(Material.CHEST, ChatColor.GREEN + "Deposit Coins", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + player.getBankBalance(type), "", ChatColor.GRAY + "Store coins in the bank to keep", ChatColor.GRAY + "them safe while you go on", ChatColor.GRAY + "adventures!", "", ChatColor.GRAY + "You will earn " + ChatColor.AQUA + "2% " + ChatColor.GRAY + "interest every day", ChatColor.GRAY + "season for your first " + ChatColor.GOLD + "2 million", ChatColor.GRAY + "banked coins.", "", ChatColor.GRAY + "Until interest: " + ChatColor.AQUA + "0h", "", ChatColor.YELLOW + "Click to make a deposit!")));
        inventory.setItem(11, deposit);

        ItemStack withdraw = createItem(Material.DROPPER, ChatColor.GREEN + "Withdraw Coins", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + player.getBankBalance(type), "", ChatColor.GRAY + "Take your coins out of the bank", ChatColor.GRAY + "in order to spend them.", "", ChatColor.YELLOW + "Click to withdraw coins!")));
        inventory.setItem(13, withdraw);

        ItemStack recentTrans = createItem(Material.MAP, ChatColor.GREEN + "Recent Transactions");
        inventory.setItem(15, recentTrans);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "To Bank")));
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close", ChatColor.GRAY + "To Bank");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBankDeposit(PlayerSD player, BankAccount.Type type) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + type.toString() + " Bank Deposit");

        putGlasses(inventory);

        ItemStack depositAll = createItem(Material.CHEST, 64, ChatColor.GREEN + "Fill the account", ChatColor.DARK_GRAY + "Bank deposit", "", ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + player.getBankBalance(type), ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + player.getPurse(), "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(10, depositAll);

        ItemStack depositHalf = depositAll.clone();
        Functions.setLine(depositHalf, 4, ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + (player.getPurse() / 2));
        depositHalf.setAmount(32);
        setName(depositHalf, ChatColor.GREEN + "Half the account");
        inventory.setItem(12, depositHalf);

        ItemStack deposit20 = depositAll.clone();
        Functions.setLine(deposit20, 4, ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + (player.getPurse() / 5));
        deposit20.setAmount(32);
        setName(deposit20, ChatColor.GREEN + "Deposit 20%");
        inventory.setItem(14, deposit20);

        ItemStack specificAmount = createItem(Material.SIGN, ChatColor.GREEN + "Specific Amount", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankBalance(type), "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(16, specificAmount);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Personal Bank Account");
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }

    public static void openBankWithdraw(PlayerSD player, BankAccount.Type type) {
        Inventory inventory = Bukkit.createInventory(player, 36, ChatColor.DARK_GRAY + type.toString() + " Bank Withdraw");

        putGlasses(inventory);

        ItemStack withdrawAll = createItem(Material.DROPPER, 64, ChatColor.GREEN + "Everything in the account", ChatColor.DARK_GRAY + "Bank Withdrawal", "", ChatColor.GRAY + "Current Balance: " + ChatColor.GOLD + player.getBankBalance(type), ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + player.getBankBalance(type), "", ChatColor.YELLOW + "Click to withdraw coins!");
        inventory.setItem(10, withdrawAll);

        ItemStack withdrawHalf = withdrawAll.clone();
        withdrawHalf.setAmount(32);
        setName(withdrawHalf, ChatColor.GREEN + "Half the account");
        Functions.setLine(withdrawHalf, 4, ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + (player.getBankBalance(type) / 2));
        inventory.setItem(12, withdrawHalf);

        ItemStack withdraw20 = withdrawAll.clone();
        withdraw20.setAmount(32);
        setName(withdraw20, ChatColor.GREEN + "Withdraw 20%");
        Functions.setLine(withdraw20, 4, ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + (player.getBankBalance(type) / 5));
        inventory.setItem(14, withdraw20);

        ItemStack specificAmount = createItem(Material.SIGN, ChatColor.GREEN + "Specific Amount", ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankBalance(type), "", ChatColor.YELLOW + "Click to deposit coins!");
        inventory.setItem(16, specificAmount);

        ItemStack goBack = createItem(Material.ARROW, ChatColor.GREEN + "Go Back", ChatColor.GRAY + "To Personal Bank Account");
        inventory.setItem(30, goBack);

        ItemStack close = createItem(Material.BARRIER, ChatColor.RED + "Close");
        inventory.setItem(31, close);

        player.openInventory(inventory);
    }
}
