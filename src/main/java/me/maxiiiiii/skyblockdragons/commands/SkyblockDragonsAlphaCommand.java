package me.maxiiiiii.skyblockdragons.commands;

import me.maxiiiiii.skyblockdragons.commands.manager.QuickCommand;
import me.maxiiiiii.skyblockdragons.commands.manager.QuickSubCommand;
import me.maxiiiiii.skyblockdragons.events.listeners.JoinQuitListener;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.ChatColor;

public class SkyblockDragonsAlphaCommand extends QuickCommand {
    public SkyblockDragonsAlphaCommand() {
        addSubCommand(new QuickSubCommand("starter", (player, args) -> {
            player.sendMessage("§c[SBD ALPHA] You got Starter kit");
            JoinQuitListener.starterKit(player);
        }));
        addSubCommand(new QuickSubCommand("breakPiece", (player, args) -> {
            Item toolItem = player.getItems().getTool();
            Item item = TheEnd.breakArmorPiece(toolItem);
            player.getInventory().setItemInMainHand(item);
            player.sendMessageFormat(ChatColor.RED + "[SBD ALPHA] You BROKE ", toolItem.getItemMeta().getDisplayName(), " -> ",item.getAmount() , "x ", item.getItemMeta().getDisplayName(), "!");
        }));
        addSubCommand(new QuickSubCommand("setPet", (player, args) -> {
            if (args.length < 2){
                player.sendMessage(ChatColor.RED + "[SBD ALPHA] You need to specify a pet number");
                return;
            }
            int petNumber = Integer.parseInt(args[1]);
            int size = player.getPlayerPet().getPets().size();
            if (size <= petNumber){
                player.sendMessageFormat("§c[SBD ALPHA] you have %s pets", size);
                return;
            }
            player.setActivePet(petNumber);
            player.sendMessage("§c[SBD ALPHA] You set the pet number to " + petNumber);
        }));
    }
}
