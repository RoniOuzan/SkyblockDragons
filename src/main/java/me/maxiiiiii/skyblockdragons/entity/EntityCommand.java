package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.TextMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EntityCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            PlayerSD player = SkyblockDragons.getPlayer((Player) sender);
            if (args.length > 1) {
                if (args[0].toLowerCase().startsWith("s")) {
                    new EntitySD(player.getLocation(), EntityMaterial.get(args[1]));
                    player.sendMessage(ChatColor.GREEN + "Summoned new " + Functions.setTitleCase(args[1]) + ".");
                } else if (args[0].toLowerCase().startsWith("a")) {
                    if (!EntityMaterial.entities.containsKey(args[1].toUpperCase())) {
                        player.sendMessage(ChatColor.RED + "Can't understand this entity!");
                        return true;
                    }
                    Location location = Functions.getLowestBlock(player.getLocation()).getLocation().add(0.5, 1, 0.5);
                    EntitySD.entitiesLocations.put(location, EntityMaterial.get(args[1]));
                    EntitySD.saveLocations();
                    player.sendMessage(ChatColor.GREEN + "You have set new spawn to " + Functions.setTitleCase(args[1]) + " in " + Functions.getLocation(location) + ".");
                } else if (args[0].toLowerCase().startsWith("r")) {
                    Location location = Functions.getCenter(player.getLocation());
                    if (!EntitySD.entitiesLocations.containsKey(location)) {
                        player.sendMessage(ChatColor.RED + "This location does not saved!");
                        new TextMessage().append(ChatColor.RED + "If you want to delete the nearest saved location ").save().append(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK HERE!").setClickAsExecuteCmd("/esd delete").save().send(player);
                    }
                    if (!EntityMaterial.entities.containsKey(args[1].toUpperCase())) {
                        player.sendMessage(ChatColor.RED + "Can't understand this entity!");
                        return true;
                    }
                    EntitySD.entitiesLocations.remove(location);
                    EntitySD.saveLocations();
                    player.sendMessage(ChatColor.GREEN + "You have removed the spawn of " + Functions.setTitleCase(args[1]) + ".");
                } else if (args[0].toLowerCase().startsWith("d")) {
                    if (!EntityMaterial.entities.containsKey(args[1].toUpperCase())) {
                        player.sendMessage(ChatColor.RED + "Can't understand this entity!");
                        return true;
                    }
                    Location location = null;
                    for (Location key : EntitySD.entitiesLocations.keySet().stream().filter(l -> l.getWorld().getName().equalsIgnoreCase(player.getWorld().getName())).collect(Collectors.toList())) {
                        if (location == null || player.getLocation().distance(key) < player.getLocation().distance(location)) {
                            location = key.clone();
                        }
                    }
                    EntitySD.entitiesLocations.remove(location);
                    EntitySD.saveLocations();
                } else if (args[0].equalsIgnoreCase("send")) {
                    for (Location location : EntitySD.entitiesLocations.keySet()) {
                        player.sendMessage(EntitySD.entitiesLocations.get(location).name() + ": " + Functions.getLocation(location));
                    }
                }
            } else if (args.length > 0) {
                if (args[0].toLowerCase().startsWith("k")) {
                    Entity target = player.getTargetEntity(20);
                    if (target != null && EntitySD.isEntitySD((LivingEntity) target))
                        EntitySD.get(target).kill();
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1)
            return new ArrayList<>(Arrays.asList("spawn", "kill", "add", "delete", "remove")).stream().sorted().collect(Collectors.toList()).stream().filter(s -> s.startsWith(args[0].toLowerCase().toLowerCase())).collect(Collectors.toList());
        else if (args[0].toLowerCase().startsWith("s") || args[0].toLowerCase().startsWith("a") || args[0].toLowerCase().startsWith("d") || args[0].toLowerCase().startsWith("r"))
            return EntityMaterial.entities.values().stream().map(EntityMaterial::name).filter(s -> s.startsWith(args[1].toUpperCase())).collect(Collectors.toList());
        return null;
    }
}
