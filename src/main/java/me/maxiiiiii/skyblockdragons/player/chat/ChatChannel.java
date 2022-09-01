package me.maxiiiiii.skyblockdragons.player.chat;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.chat.events.PlayerGetMessageEvent;
import me.maxiiiiii.skyblockdragons.player.chat.interfaces.ChatCondition;
import me.maxiiiiii.skyblockdragons.player.chat.interfaces.ChatPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum ChatChannel {
    ALL("", p -> SkyblockDragons.getPlayers()),
    WORLD(ChatColor.GREEN + "World > ", p -> p.getWorldSD().getPlayers()),
    PARTY(ChatColor.BLUE + "Party > ", p -> p.getParty() != null, ChatColor.RED + "You don't have a party!", p -> new ArrayList<>(p.getParty().getPlayers().keySet())),
    ADMIN(ChatColor.RED + "Admin > ", p -> SkyblockDragons.getPlayers().stream().filter(ServerOperator::isOp).collect(Collectors.toList()))
    ;

    private final String name;
    public final ChatCondition condition;
    public final String messageElse;
    private final ChatPlayers chatPlayers;

    ChatChannel(String name, ChatCondition condition, String messageElse, ChatPlayers chatPlayers) {
        this.name = name;
        this.condition = condition;
        this.messageElse = messageElse;
        this.chatPlayers = chatPlayers;
    }

    ChatChannel(String name, ChatPlayers chatPlayers) {
        this(name, p -> true, "", chatPlayers);
    }

    public String getName() {
        return this.name;
    }

    public List<PlayerSD> getPlayers(PlayerSD player) {
        return chatPlayers.getPlayers(player);
    }

    public void send(PlayerSD from, String message) {
        for (PlayerSD player : this.getPlayers(from)) {
            final String finalMessage = this.getName() + ChatColor.WHITE + from.getDisplayName() + ": " + message;
            Bukkit.getPluginManager().callEvent(new PlayerGetMessageEvent(player, finalMessage, from.getChatChannel()));
        }
    }
}
