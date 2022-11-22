package me.maxiiiiii.skyblockdragons.player.bank;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BankCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
                if (args[0].equalsIgnoreCase("open")) {
                    BankMenu.openBank(player);
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (e.getClickedInventory().getTitle().contains("Bank")) e.setCancelled(true);

        PlayerSD player = SkyblockDragons.getPlayer((Player) e.getWhoClicked());
        BankAccount.Type type = BankAccount.Type.PERSONAL;
        if (e.getInventory().getTitle().contains("Coop")) {
            type = BankAccount.Type.COOP;
        }

        if (e.getInventory().getTitle().contains("Bank Account")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Deposit")) {
                BankMenu.openBankDeposit(player, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Withdraw")) {
                BankMenu.openBankWithdraw(player, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back")) {
                BankMenu.openBank(player);
            }
        } else if (e.getInventory().getTitle().contains("Bank Deposit")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Fill")) {
                player.bank.deposit(player.getPurse(), type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Half")) {
                player.bank.deposit(player.getPurse() / 2, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("20")) {
                player.bank.deposit(player.getPurse() / 5, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Specific")) {
//                BankAccount.Type finalType = type; // TODO
//                Functions.openSign(player, lines -> {
//                    if (!Functions.isDouble(lines.get(0))) {
//                        player.sendMessage(ChatColor.RED + "This is not a number!");
//                        player.closeInventory();
//                        return;
//                    }
//                    player.bank.deposit(Double.parseDouble(lines.get(0)), finalType);
//                    BankMenu.openBankDeposit(player, finalType);
//                });
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back")) {
                BankMenu.openBank(player, type);
            }
            if (e.getCurrentItem().getType() == Material.CHEST)
                BankMenu.openBank(player, type);
        } else if (e.getInventory().getTitle().contains("Bank Withdraw")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Everything")) {
                player.bank.withdraw(player.getBankBalance(type), type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Half")) {
                player.bank.withdraw(player.getBankBalance(type) / 2, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("20")) {
                player.bank.withdraw(player.getBankBalance(type) / 5, type);
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Specific")) {
//                BankAccount.Type finalType = type; // TODO
//                Functions.openSign(player, lines -> {
//                    if (!Functions.isDouble(lines.get(0))) {
//                        player.sendMessage(ChatColor.RED + "This is not a number!");
//                        player.closeInventory();
//                        return;
//                    }
//                    player.bank.withdraw(Double.parseDouble(lines.get(0)), finalType);
//                    BankMenu.openBankWithdraw(player, finalType);
//                });
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back")) {
                BankMenu.openBank(player, type);
            }
            if (e.getCurrentItem().getType() == Material.DROPPER)
                BankMenu.openBank(player, type);
        } else if (e.getInventory().getTitle().contains("Bank")) {
            if (e.getCurrentItem().getType() == Material.ENDER_CHEST) {
                BankMenu.openBank(player, BankAccount.Type.COOP);
            } else if (e.getCurrentItem().getType() == Material.CHEST) {
                BankMenu.openBank(player, BankAccount.Type.PERSONAL);
            }
        }
    }
}
