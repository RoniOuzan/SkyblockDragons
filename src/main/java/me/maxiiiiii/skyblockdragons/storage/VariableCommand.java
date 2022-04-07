package me.maxiiiiii.skyblockdragons.storage;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.util.serialization.Serializer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VariableCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Invalid arguments!");
                return true;
            }

            if (args[0].equalsIgnoreCase("setU")) {
                Variables.set(player.getUniqueId(), "test", 0, player.getEquipment().getItemInMainHand());
            } else if (args[0].equalsIgnoreCase("set")) {
                Variables.set("test", 0, player.getEquipment().getItemInMainHand());
            } else if (args[0].equalsIgnoreCase("test")) {
                String serialized = Serializer.serialize(player.getWardrobe());
                player.sendMessage(serialized);
                player.sendMessage("-----------------------------------------");
                Wardrobe wardrobe = Serializer.deserialize(serialized);
                player.sendMessage(wardrobe + "");
                player.sendMessage("-----------------------------------------");
                player.sendMessage(Serializer.serialize(wardrobe));
            }
        }
        return true;
    }
}
