package me.maxiiiiii.skyblockdragons.commands;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.util.objects.MessageModifier;
import me.maxiiiiii.skyblockdragons.util.objects.TextMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SkyblockDragonsCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                player.sendMessage(ChatColor.GREEN + "You have reloaded SkyblockDragons plugin.");
                Functions.Wait(5L, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload SkyblockDragons");
                    player.sendMessage(ChatColor.GREEN + "SkyblockDragons has successfully been reloaded.");
                });
            } else if (args[0].equalsIgnoreCase("update")) {
                player.sendMessage(ChatColor.GREEN + "You have been reloaded SkyblockDragons plugin.");
                Functions.Loop(5, 20L, amount -> {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage(ChatColor.RED + String.format("Updating in %d...", 5-amount));
                        onlinePlayer.sendTitle(ChatColor.RED + String.format("Updating in %d...", 5-amount), "Game Updating...", 10, 60, 10);
                    }
                }, amount -> {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        onlinePlayer.sendMessage(ChatColor.RED + "Updating...");
                        onlinePlayer.sendTitle(ChatColor.RED + "Updating...", "Game Updating...", 10, 60, 10);
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload SkyblockDragons");
                    player.sendMessage(ChatColor.GREEN + "Successfully reloaded SkyblockDragons.");
                });
            } else if (args[0].equalsIgnoreCase("sound")) {
                if (args.length > 1) {
                    float pitch = 1f;
                    if (args.length > 2) {
                        pitch = Float.parseFloat(args[2]);
                    }
                    try {
                        player.playSound(player.getLocation(), Sound.valueOf(args[1].toUpperCase()), 1f, pitch);
                    } catch (IllegalArgumentException ex) {
                        List<Sound> sounds = Arrays.stream(Sound.values()).filter(s -> s.name().contains(args[1])).collect(Collectors.toList());
                        if (sounds.size() == 0) return;
                        float finalPitch = pitch;
                        Functions.Loop(sounds.size(), 100L / sounds.size(), i -> {
                                player.sendMessage(sounds.get(i).name());
                                player.playSound(player.getLocation(), sounds.get(i), 1f, finalPitch);
                        });
                    }
                } else {
                    for (Sound sound : Sound.values()) {
                        player.sendMessage(sound.name());
                    }
                }
            } else if (args[0].equalsIgnoreCase("nbt")) {
                NBTItem nbt;
                try {
                    nbt = new NBTItem(player.getEquipment().getItemInMainHand());
                } catch (NullPointerException e) {
                    player.sendMessage(ChatColor.RED + "This item doesn't have nbt!");
                    return;
                }
                if (args.length > 1) {
                    if (args[1].equalsIgnoreCase("console")) {
                        System.out.println(nbt);
                        player.sendMessage(ChatColor.GREEN + "The item's nbt has sent to the console");
                        player.sendMessage(ChatColor.AQUA + "https://panel.sbdragons.ml/server/e937329c");
                    } else if (args[1].equalsIgnoreCase("send")) {
                        player.sendMessage(nbt.asNBTString());
                    } else if (args[1].equalsIgnoreCase("skull")) {
                        player.sendMessage(ChatColor.RED + "Be careful! the value of the texture is not working!");
                        try {
                            NBTCompound compound = nbt.getCompound("SkullOwner");
                            System.out.println(compound.getString("Id"));
                            NBTListCompound texture = compound.addCompound("Properties").getCompoundList("textures").addCompound();
                            System.out.println(texture.getString("Value"));
                            player.sendMessage(ChatColor.GREEN + "The item's skull owner has sent to the console");
                            player.sendMessage(ChatColor.AQUA + "https://panel.sbdragons.ml/server/e937329c");
                        } catch (NullPointerException ignored) {
                            player.sendMessage(ChatColor.RED + "This item doesn't have SkullOwner!");
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("spawn") || args[0].equalsIgnoreCase("dummy")) {
                Entity zombie = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                zombie.setCustomName(ChatColor.GREEN + "Dummy");
                ((LivingEntity) zombie).setAI(false);
                ((LivingEntity) zombie).setMaxHealth(500000d);
                ((LivingEntity) zombie).setHealth(((LivingEntity) zombie).getMaxHealth());
            } else if (args[0].equalsIgnoreCase("kill")) {
                List<Entity> list = player.getWorld().getEntities();
                for (Entity entity : list) {
                    try {
                        if (entity.getCustomName().contains("Dummy")) {
                            entity.remove();
                            player.sendMessage(ChatColor.WHITE + "Killed " + entity.getCustomName());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            } else if (args[0].equalsIgnoreCase("uuid")) {
                PlayerSD target = args.length > 1 ? SkyblockDragons.getPlayer(args[1]) : player;
                player.sendClickableMessage(new MessageModifier(target.getUniqueId().toString(), new Entry<>(TextMessage.Modifier.SUGGEST_MESSAGE, player.getUniqueId().toString())));
            } else if (args[0].equalsIgnoreCase("test")) {
                player.sendMessage(player.getRegion());
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid arguments!");
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "sound", Arrays.stream(Sound.values()).map(Enum::name).sorted().collect(Collectors.toList())));
        return tabs;
    }
}
