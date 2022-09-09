package me.maxiiiiii.skyblockdragons.player.party;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.Permissions;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartyCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Party.LINE);
            player.sendMessage(ChatColor.YELLOW + "/party invite <player> - invites a player to your party (must be moderator or higher)");
            player.sendMessage(ChatColor.YELLOW + "/party join <player> - joins a party of player if the party is on PUBLIC");
            if (player.hasPermission(Permissions.Party.FORCE_JOIN))
                player.sendMessage(ChatColor.YELLOW + "/party forcejoin <player> - joins a party of player");
            if (player.hasPermission(Permissions.Party.SILENT_JOIN))
                player.sendMessage(ChatColor.YELLOW + "/party silentjoin <player> - joins a party of player as silent");
            if (player.hasPermission(Permissions.Party.ADMIN_LIST))
                player.sendMessage(ChatColor.YELLOW + "/party list <player> - sends you the list of the party of the argument");
            player.sendMessage(ChatColor.YELLOW + "/party list - sends you the list of players in your party");
            player.sendMessage(ChatColor.YELLOW + "/party public/private - sets your party on PUBLIC/PRIVATE (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party promote <player> - promote a member in your party to moderator and moderator to leader (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party transfer <player> - transfers the leader to a player in your party (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party warp - warps everyone to your world (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party kick/kickOffline [<player>] - kick/s a offline/players from your party (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party delete - deletes your party (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party leave - leaves your current party");
            player.sendMessage(ChatColor.YELLOW + "/party ban/unban <player> - un/ban someone from your party, banned players cannot join your party (must be the leader)");
            player.sendMessage(ChatColor.YELLOW + "/party chat <message...> - sends a message to in the party chat");
            player.sendMessage(ChatColor.YELLOW + "/party mute/muteall <player> - mute/s a player/all the players (except the moderators) from the party chat (must be moderator or higher) ");
            if (player.hasPermission(Permissions.Party.SEE_PARTIES))
                player.sendMessage(ChatColor.YELLOW + "/party parties - sends you every active party");
            player.sendMessage(Party.LINE);
            return;
        }

        try {
            if (args[0].equalsIgnoreCase("create")) {
                player.setParty(new Party(player));
            } else if (args[0].equalsIgnoreCase("accept")) {
                if (nullCheck(player, args))
                    SkyblockDragons.getPlayer(args[1]).getParty().accept(player);
            } else if (args[0].equalsIgnoreCase("join")) {
                if (nullCheck(player, args))
                    SkyblockDragons.getPlayer(args[1]).getParty().join(player);
            } else if (args[0].equalsIgnoreCase("forceJoin")) {
                if (!player.hasPermission(Permissions.Party.FORCE_JOIN)) player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                if (nullCheck(player, args))
                    SkyblockDragons.getPlayer(args[1]).getParty().forceJoin(player);
            } else if (args[0].equalsIgnoreCase("silentJoin") || args[0].equalsIgnoreCase("joinSilent") || args[0].equalsIgnoreCase("sneakyJoin")) {
                if (!player.hasPermission(Permissions.Party.SILENT_JOIN)) player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                if (nullCheck(player, args))
                    SkyblockDragons.getPlayer(args[1]).getParty().joinSilent(player);
            } else if (args[0].equalsIgnoreCase("list") && args.length > 1) {
                if (!player.hasPermission(Permissions.Party.ADMIN_LIST)) player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                if (nullCheck(player, args))
                    SkyblockDragons.getPlayer(args[1]).getParty().sendList(player);
            } else {
                if (player.getParty() == null) {
                    if ((args[0].equalsIgnoreCase("invite") || Bukkit.getPlayer(args[0]) != null)) {
                        player.setParty(new Party(player));
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have a party!");
                        player.sendMessage(ChatColor.RED + "to create a party you have to do /party create");
                        return;
                    }
                }
                if (args[0].equalsIgnoreCase("public")) {
                    player.getParty().setType(PartyType.PUBLIC);
                } else if (args[0].equalsIgnoreCase("private")) {
                    player.getParty().setType(PartyType.PRIVATE);
                } else if (args[0].equalsIgnoreCase("transfer")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().transferLeader(SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("promote")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().promote(SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("warp")) {
                    player.getParty().warp();
                } else if (args[0].equalsIgnoreCase("list")) {
                    player.getParty().sendList(player);
                } else if (args[0].equalsIgnoreCase("kick")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().kick(SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("kickOffline")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().kickOffline();
                } else if (args[0].equalsIgnoreCase("delete")) {
                    if (player.getParty().getLeader() == player) player.getParty().delete();
                } else if (args[0].equalsIgnoreCase("leave")) {
                    player.getParty().leave(player);
                } else if (args[0].equalsIgnoreCase("ban")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().ban(SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("unban")) {
                    if (nullCheck(player, args, PlayerPartyType.LEADER))
                        player.getParty().unban(SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("chat")) {
                    if (args.length <= 1) {
                        player.sendMessage("/party chat <message...>");
                        return;
                    }
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    player.getParty().makePlayerSay(player, message.toString());
                } else if (args[0].equalsIgnoreCase("mute")) {
                    if (!nullCheck(player, args, PlayerPartyType.LEADER, PlayerPartyType.MODERATOR)) {
                        player.sendMessage(Party.LINE);
                        player.sendMessage(ChatColor.RED + "You must be the moderator at least of the party to use this command!");
                        player.sendMessage(Party.LINE);
                        return;
                    }
                    player.getParty().mute(player, SkyblockDragons.getPlayer(args[1]));
                } else if (args[0].equalsIgnoreCase("muteall")) {
                    if (player.getParty().getLeader() != player && player.getParty().getPlayers().get(player) != PlayerPartyType.SILENT) {
                        player.sendMessage(Party.LINE);
                        player.sendMessage(ChatColor.RED + "You must be the leader of the party to use this command!");
                        player.sendMessage(Party.LINE);
                        return;
                    }
                    player.getParty().muteAll();
                } else if (args[0].equalsIgnoreCase("parties")) {
                    if (!player.hasPermission(Permissions.Party.SEE_PARTIES))
                        player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                    Party.sendParties(player);
                } else {
                    if (nullCheck(player, args, PlayerPartyType.LEADER, PlayerPartyType.MODERATOR)) {
                        int i = 0;
                        for (String arg : args) {
                            if (i == (args[0].equalsIgnoreCase("invite") ? 0 : -1)) {
                                i++;
                                continue;
                            }
                            if (Bukkit.getPlayer(arg) != null) {
                                player.getParty().invite(player, SkyblockDragons.getPlayer(arg));
                            } else {
                                player.sendMessage(Party.LINE);
                                player.sendCenteredMessage(ChatColor.RED + "There is no player with the name " + arg + ChatColor.RED + "!");
                                player.sendMessage(Party.LINE);
                            }
                            i++;
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            player.sendMessage(Party.LINE);
            player.sendMessage(ChatColor.RED + "Something went wrong!");
            player.sendMessage(Party.LINE);
            ex.printStackTrace();
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", true,
                "public",
                "private",
                "invite",
                "accept",
                "join",
                player.hasPermission("skyblockdragons.party.forcejoin") ? "forcejoin" : "",
                player.hasPermission("skyblockdragons.party.silentjoin") ? "silentjoin" : "",
                "transfer",
                "promote",
                "warp",
                "list",
                "delete",
                "leave",
                "kick",
                "kickOffline",
                "ban",
                player.hasPermission("skyblockdragons.party.seeparties") ? "parties" : ""
        ));

        tabs.add(new Argument(1, "accept", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != player.getParty()).map(PlayerSD::getName).collect(Collectors.toList())));
        tabs.add(new Argument(1, "join", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != null && p.getParty() != player.getParty() && p.getParty().getType() == PartyType.PUBLIC).map(PlayerSD::getName).collect(Collectors.toList())));
        if (player.hasPermission("skyblockdragons.party.forcejoin")) tabs.add(new Argument(1, "forceJoin", Party.parties.stream().map(p -> p.getLeader().getName()).collect(Collectors.toList())));
        if (player.hasPermission("skyblockdragons.party.silentjoin")) tabs.add(new Argument(1, "silentJoin", Party.parties.stream().map(p -> p.getLeader().getName()).collect(Collectors.toList())));

        if (player.getParty() != null) {
            tabs.add(new Argument(1, "invite", SkyblockDragons.getOnlinePlayers().stream().filter(p -> p.getParty() != player.getParty()).map(PlayerSD::getName).collect(Collectors.toList())));
            tabs.add(new Argument(1, "kick", player.getParty().getPlayers().keySet().stream().filter(p -> p != player).map(PlayerSD::getName).collect(Collectors.toList())));
            tabs.add(new Argument(1, "promote", player.getParty().getPlayers().keySet().stream().filter(p -> p != player).map(PlayerSD::getName).collect(Collectors.toList())));
            tabs.add(new Argument(1, "transfer", player.getParty().getPlayers().keySet().stream().filter(p -> p != player).map(PlayerSD::getName).collect(Collectors.toList())));
            tabs.add(new Argument(1, "ban", SkyblockDragons.getPlayers().stream().filter(p -> p != player).map(PlayerSD::getName).collect(Collectors.toList())));
        }
        return tabs;
    }

    private boolean nullCheck(PlayerSD player, String[] args, PlayerPartyType... playerTypes) {
        if (args.length <= 1) {
            player.sendMessage("/party " + args[0] + " <player>");
            return false;
        }
        if (Bukkit.getPlayer(args[1]) == null) {
            player.sendMessage(ChatColor.RED + "There is no online player with the name " + args[1]);
            return false;
        }
        if (player.getParty() != null && player.getParty().getPlayers().get(player) == PlayerPartyType.SILENT) return true;
        return playerTypes.length == 0 || Arrays.stream(playerTypes).collect(Collectors.toList()).contains(player.getParty().getPlayers().get(player));
    }
}
