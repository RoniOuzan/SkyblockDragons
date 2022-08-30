package me.maxiiiiii.skyblockdragons.player.party;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class PartyCommand extends CommandSD {
    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length == 0) {
            // TODO add list of the commands
            return;
        }

        if (args[0].equalsIgnoreCase("create")) {
            player.setParty(new Party(player));
            return;
        }
        if (player.getParty() == null) {
            player.sendMessage(ChatColor.RED + "You don't have a party!");
            return;
        }
        if (args[0].equalsIgnoreCase("public")) {
            player.getParty().setType(PartyType.PUBLIC);
        } else if (args[0].equalsIgnoreCase("private")) {
            player.getParty().setType(PartyType.PRIVATE);
        } else if (args[0].equalsIgnoreCase("invite")) {
            if (nullCheck(player, args))
                player.getParty().invite(player, SkyblockDragons.getPlayer(args[1]));
        } else if (args[0].equalsIgnoreCase("accept")) {
            if (nullCheck(player, args))
                SkyblockDragons.getPlayer(args[1]).getParty().accept(player);
        } else if (args[0].equalsIgnoreCase("join")) {
            if (nullCheck(player, args))
                SkyblockDragons.getPlayer(args[1]).getParty().join(player);
        } else if (args[0].equalsIgnoreCase("silentJoin") || args[0].equalsIgnoreCase("joinSilent") || args[0].equalsIgnoreCase("sneakyJoin")) {
            if (!player.hasPermission("skyblockdragons.party.silentjoin")) return;
            if (nullCheck(player, args))
                SkyblockDragons.getPlayer(args[1]).getParty().joinSilent(player);
        } else if (args[0].equalsIgnoreCase("transfer")) {
            if (nullCheck(player, args, true))
                player.getParty().transferLeader(SkyblockDragons.getPlayer(args[1]));
        } else if (args[0].equalsIgnoreCase("promote")) {
            if (nullCheck(player, args, true))
                player.getParty().promote(SkyblockDragons.getPlayer(args[1]));
        } else if (args[0].equalsIgnoreCase("warp")) {
            player.getParty().warp();
        } else if (args[0].equalsIgnoreCase("list")) {
            player.getParty().sendList(player);
        } else if (args[0].equalsIgnoreCase("delete")) {
            if (player.getParty().getLeader() == player) player.getParty().delete();
        } else if (args[0].equalsIgnoreCase("leave")) {
            player.getParty().leave(player);
        } else if (args[0].equalsIgnoreCase("kick")) {
            if (nullCheck(player, args, true))
                player.getParty().kick(SkyblockDragons.getPlayer(args[1]));
        } else if (args[0].equalsIgnoreCase("kickOffline")) {
            if (nullCheck(player, args, true))
                player.getParty().kickOffline();
        } else if (args[0].equalsIgnoreCase("ban")) {
            if (nullCheck(player, args, true))
                player.getParty().ban(SkyblockDragons.getPlayer(args[1]));
        }
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(1, "invite", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != player.getParty()).map(PlayerSD::getName).collect(Collectors.toList())));
        tabs.add(new Argument(1, "accept", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != player.getParty()).map(PlayerSD::getName).collect(Collectors.toList())));

        tabs.add(new Argument(1, "join", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != player.getParty() && p.getParty().getType() == PartyType.PUBLIC).map(PlayerSD::getName).collect(Collectors.toList())));

        tabs.add(new Argument(1, "join", SkyblockDragons.getPlayers().stream().filter(p -> p.getParty() != player.getParty() && p.getParty().getType() == PartyType.PUBLIC).map(PlayerSD::getName).collect(Collectors.toList())));
        return tabs;
    }

    private boolean nullCheck(PlayerSD player, String[] args, boolean leaderCheck) {
        if (args.length <= 1) {
            player.sendMessage("/party " + args[0] + " <player>");
            return false;
        }
        if (Bukkit.getPlayer(args[1]) == null) {
            player.sendMessage(ChatColor.RED + "There is no online player with the name " + args[1]);
            return false;
        }
        return !leaderCheck || player.getParty().getLeader() == player;
    }

    private boolean nullCheck(PlayerSD player, String[] args) {
        return nullCheck(player, args, false);
    }
}
