package me.maxiiiiii.skyblockdragons.commands.manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class QuickConsoleCommand implements TabExecutor {

    private final ArrayList<SubConsoleCommand> subCommands = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0){
            for (SubConsoleCommand subCommand: subCommands){
                if (args[0].equalsIgnoreCase(subCommand.getName())){
                    subCommand.perform(sender, args);
                    return true;
                }
            }
            sender.sendMessage("§cInvalid arguments!");
        }
        else {
            sender.sendMessage("-------------------------");
            for (SubConsoleCommand subCommand : subCommands) {
                sender.sendMessage(String.format("§6%s - §e%s", subCommand.getSyntax(), subCommand.getDescription()));
            }
            sender.sendMessage("-------------------------");
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1){
            completions = subCommands.stream()
                    .map(SubConsoleCommand::getName)
                    .filter(s -> s.startsWith(args[0]))
                    .collect(Collectors.toList());
        }
        else if (args.length == 2){
            return null;
        }
        return completions;
    }

    public void addSubCommand(SubConsoleCommand subCommand){
        subCommands.add(subCommand);
    }
}
