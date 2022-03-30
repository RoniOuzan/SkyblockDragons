package me.maxiiiiii.skyblockdragons.storage;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class VariableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) return false;

            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("send")) {
                for (Variable<?> variable : Variables.variables) {
                    sender.sendMessage(variable.id + ":");
                    sender.sendMessage("  uuid - " + variable.uuid);
                    sender.sendMessage("  data - " + variable.data);
                    sender.sendMessage("  value - " + variable.value);
                    sender.sendMessage("  class - " + variable.value.getClass());
                }
            } else if (args[0].equalsIgnoreCase("set")) {
                String id = "";
                if (args.length > 1) {
                    id = args[1];
                }
                HashMap<String, Integer> value = new HashMap<>();
                for (int i = 2; i < args.length; i++) {
                    value.put(i + "", Integer.valueOf(args[i]));
                }
                Variables.set(player.getUniqueId(), id, value);
            }
        }
        return true;
    }
}
