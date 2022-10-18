package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickConsoleCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickConsoleSubCommand;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

public class AccessoryBagAdminCommand extends QuickConsoleCommand {
    public AccessoryBagAdminCommand() {
        addSubCommand(new QuickConsoleSubCommand("copy", (sender, args) -> {
            // acbagadmin copy %from% %to%
            if (args.length < 3){
                sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/acbagadmin copy <from> <to>");
                return;
            }
            PlayerSD from = SkyblockDragons.getPlayer(args[1]);
            PlayerSD to = SkyblockDragons.getPlayer(args[2]);
            if (from == null || to == null){
                sender.sendMessage(ChatColor.RED + "Player not found");
                return;
            }
            to.getAccessoryBag().setItems(from.getAccessoryBag().getItems());
            to.getAccessoryBag().save();
        }));
        addSubCommand(new QuickConsoleSubCommand("edit", (sender, args) -> {
            sender.sendMessage("oops not working!");
        }));
    }
}
