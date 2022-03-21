package me.maxiiiiii.skyblockdragons.bank;

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
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("open")) {
                    BankMenu.openBank(player);
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("Bank")) {
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getType() == Material.ENDER_CHEST) {
                BankMenu.openBankPersonal(player);
            } else if (e.getCurrentItem().getType() == Material.CHEST) {
                BankMenu.openBankPersonal(player);
            }
        } else if (e.getInventory().getTitle().contains("Personal Bank Account")) {
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem().getType() == Material.CHEST) {
                BankMenu.openBankPersonalDeposit(player);
            } else if (e.getCurrentItem().getType() == Material.DROPPER) {
                BankMenu.openBankPersonalWithdraw(player);
            }
        }
    }
}
