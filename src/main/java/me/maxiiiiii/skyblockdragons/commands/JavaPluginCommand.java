package me.maxiiiiii.skyblockdragons.commands;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.Items;
import me.maxiiiiii.skyblockdragons.material.NormalMaterial;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class JavaPluginCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("variables")) {
                    if (args.length > 1) {
                        if (args[1].equalsIgnoreCase("clear")) {
                            Variables.clearVariables();
                        } else if (args[1].equalsIgnoreCase("send")) {
                            sender.sendMessage(Variables.variables.size() + "");
                            sender.sendMessage(Variables.variables.toString());
                        }
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    sender.sendMessage(ChatColor.GREEN + "You have been reloaded SkyblockDragons plugin.");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload SkyblockDragons");
                    sender.sendMessage(ChatColor.GREEN + "Successfully reloaded SkyblockDragons.");
                } else if (args[0].equalsIgnoreCase("sound")) {
                    if (args.length > 1) {
                        float pitch = 1f;
                        if (args.length > 2) {
                            pitch = Float.parseFloat(args[2]);
                        }
                        try {
                            ((Player) sender).playSound(((Player) sender).getLocation(), Sound.valueOf(args[1].toUpperCase()), 1f, pitch);
                        } catch (IllegalArgumentException ex) {
                            sender.sendMessage(ChatColor.RED + "Can't understand the sound " + args[1].toUpperCase());
                        }
                    } else {
                        for (Sound sound : Sound.values()) {
                            sender.sendMessage(sound.name());
                        }
                    }
                } else if (args[0].equalsIgnoreCase("nbt")) {
                    NBTItem nbt;
                    try {
                        nbt = new NBTItem(((Player) sender).getEquipment().getItemInMainHand());
                    } catch (NullPointerException e) {
                        sender.sendMessage(ChatColor.RED + "This item doesn't have nbt!");
                        return true;
                    }
                    if (args.length > 1) {
                        if (args[1].equalsIgnoreCase("console")) {
                            System.out.println(nbt);
                            sender.sendMessage(ChatColor.GREEN + "The item's nbt has sent to the console");
                            sender.sendMessage(ChatColor.AQUA + "https://panel.sbdragons.ml/server/e937329c");
                        } else if (args[1].equalsIgnoreCase("send")) {
                            sender.sendMessage(nbt.asNBTString());
                        } else if (args[1].equalsIgnoreCase("skull")) {
                            sender.sendMessage(ChatColor.RED + "Be careful! the value of the texture is not working!");
                            try {
                                NBTCompound compound = nbt.getCompound("SkullOwner");
                                System.out.println(compound.getString("Id"));
                                NBTListCompound texture = compound.addCompound("Properties").getCompoundList("textures").addCompound();
                                System.out.println(texture.getString("Value"));
                                sender.sendMessage(ChatColor.GREEN + "The item's skull owner has sent to the console");
                                sender.sendMessage(ChatColor.AQUA + "https://panel.sbdragons.ml/server/e937329c");
                            } catch (NullPointerException ignored) {
                                sender.sendMessage(ChatColor.RED + "This item doesn't have SkullOwner!");
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("dummy")) {
                    Entity zombie = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.ZOMBIE);
                    zombie.setCustomName(ChatColor.GREEN + "Dummy");
                    ((LivingEntity) zombie).setAI(false);
                    ((LivingEntity) zombie).setMaxHealth(500000d);
                    ((LivingEntity) zombie).setHealth(((LivingEntity) zombie).getMaxHealth());
                } else if (args[0].equalsIgnoreCase("kill")) {
                    List<Entity> list = ((Player) sender).getWorld().getEntities();
                    for (Entity entity : list) {
                        try {
                            if (entity.getCustomName().contains("Dummy")) {
                                entity.remove();
                                sender.sendMessage(ChatColor.WHITE + "Killed " + entity.getCustomName());
                            }
                        } catch (NullPointerException ignored) {}
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (args.length > 1 && args[0].equalsIgnoreCase("sound")) {
            for (Sound sound : Sound.values()) {
                if (sound.name().contains(args[1].toUpperCase())) {
                    tabs.add(sound.name());
                }
            }
            Collections.sort(tabs);
            return tabs;
        }
        return null;
    }
}
