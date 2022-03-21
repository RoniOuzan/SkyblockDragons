package me.maxiiiiii.skyblockdragons.itemcreator;

import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.SkinMaterial;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BookCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                if (Functions.isEnchant(args[0])) {
                    short level = 1;
                    if (args.length > 1 && Functions.isShort(args[1])) {
                        level = Short.parseShort(args[1]);
                    }
                    Map<EnchantType, Short> enchants = new HashMap<>();
                    enchants.put(EnchantType.valueOf(args[0]), level);
                    ItemStack item = new Item(ItemMaterial.ENCHANTED_BOOK).toItem(0, ReforgeType.NULL, false, SkinMaterial.NULL, enchants);
                    ((Player) sender).getInventory().addItem(item);
                } else if (args[0].equalsIgnoreCase("ALL")) {
                    Map<EnchantType, Short> enchants = new HashMap<>();
                    for (EnchantType enchantType : EnchantType.Enchants.values()) {
                        if (enchantType.name().equals("ONE_FOR_ALL")) continue;
                        enchants.put(enchantType, enchantType.getMaxLevel());
                    }
                    ItemStack item = new Item(ItemMaterial.ENCHANTED_BOOK).toItem(0, ReforgeType.NULL, false, SkinMaterial.NULL, enchants);
                    ((Player) sender).getInventory().addItem(item);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Arguments!");
            }
        }
        return true;
    }
}
