package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AccessoryBagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
//            if (args.length > 0) {
//                if (Functions.isPlayerName(args[0])) {
//                    player = Bukkit.getPlayerExact(args[0]);
//                } else {
//                    sender.sendMessage(ChatColor.RED + "There is no player with name " + args[0] + ".");
//                    return true;
//                }
//            }
            new AccessoryBagMenu(SkyblockDragons.getPlayer(player));
        }
        return true;
    }
}
