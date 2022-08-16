package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class QuickCommand implements TabExecutor {

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length > 0){
                for (SubCommand subCommand: subCommands){
                    if (args[0].equalsIgnoreCase(subCommand.getName())){
                        subCommand.perform(player, args);
                        return true;
                    }
                }
                player.sendMessage("§cInvalid arguments!");
            }
            else {
                player.sendMessage("-------------------------");
                for (SubCommand subCommand : subCommands) {
                    player.sendMessage(String.format("§6%s - §e%s", subCommand.getSyntax(), subCommand.getDescription()));
                }
                player.sendMessage("-------------------------");
            }
        }
        else{
            sender.sendMessage("Command must be run by a Player!");
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1){
            completions = subCommands.stream().map(SubCommand::getName).collect(Collectors.toList());
        }
        else if (args.length == 2){
            return null;
        }
        return completions;
    }

    public void addSubCommand(SubCommand subCommand){
        subCommands.add(subCommand);
    }
}
