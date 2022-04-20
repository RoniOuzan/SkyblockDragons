package me.maxiiiiii.skyblockdragons.player.stats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.*;

public class StatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender);
            if (args.length > 0) {
                if (args[0].equals("add")) {
                    players.get(player.getUniqueId()).addItemStat(player.getEquipment().getItemInMainHand());
                } else {
                    player.sendMessage(players.get(player.getUniqueId()).getStats().getDamage() + "");
                }
            }
        }
        return true;
    }
}
