package me.maxiiiiii.skyblockdragons.player.chat;

import me.maxiiiiii.skyblockdragons.commands.CommandSD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChatCommand extends CommandSD {
    private static final List<String> channelsTabs = Arrays.stream(ChatChannel.values()).map(Enum::name).collect(Collectors.toList());

    @Override
    public void command(PlayerSD player, String[] args) {
        if (args.length == 0) {
            player.sendMessage("/chat <channel>");
            return;
        }

        ChatChannel channel = null;
        for (ChatChannel chatChannel : ChatChannel.values()) {
            if (chatChannel.name().startsWith(args[0].toUpperCase())) {
                channel = chatChannel;
            }
        }

        if (channel == null) {
            player.sendMessage(ChatColor.RED + "There is no chat channel that called " + args[0] + "!");
            return;
        }

        if (!channel.condition.check(player)) {
            player.sendMessage(channel.messageElse);
            return;
        }

        player.setChatChannel(channel);
        player.sendMessage(ChatColor.GREEN + "moved to channel " + channel.name() + ".");
    }

    @Override
    public List<Argument> tabComplete(PlayerSD player, List<Argument> tabs) {
        tabs.add(new Argument(0, "", channelsTabs));
        return tabs;
    }
}
