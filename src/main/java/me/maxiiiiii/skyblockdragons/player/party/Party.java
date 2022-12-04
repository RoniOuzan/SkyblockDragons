package me.maxiiiiii.skyblockdragons.player.party;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.util.objects.MessageModifier;
import me.maxiiiiii.skyblockdragons.util.objects.TextMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
public class Party implements Iterable<PlayerSD> {
    public static final List<Party> parties = new ArrayList<>();
    public static final String LINE = ChatColor.BLUE + "--------------------------------------------------"; // += ------

    private boolean isAlive;

    private final Map<PlayerSD, PlayerPartyType> players;
    private PlayerSD leader;

    private PartyType type;

    private final Map<PlayerSD, Long> invites;

    private final List<UUID> bannedPlayers;

    private final Map<PlayerSD, Boolean> mutedPlayers;
    private boolean muteAll;

    public Party(PlayerSD leader) {
        parties.add(this);
        this.isAlive = true;

        this.players = new LinkedHashMap<>();
        this.players.put(leader, PlayerPartyType.LEADER);
        this.leader = leader;

        this.type = PartyType.PRIVATE;

        this.invites = new HashMap<>();

        this.bannedPlayers = new ArrayList<>();

        this.mutedPlayers = new HashMap<>();
        this.muteAll = false;

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + "Party has been created!");
        this.sendLine();
    }

    public void setType(PartyType type) {
        if (this.type == type) {
            this.getLeader().sendMessage(LINE);
            this.getLeader().sendCenteredMessage(ChatColor.YELLOW + "The party is already on " + type.name());
            this.getLeader().sendMessage(LINE);
        } else {
            this.type = type;
            this.sendLine();
            this.sendCenteredMessage(ChatColor.YELLOW + "The party has been set to " + type.name());
            this.sendLine();
        }
    }

    public void invite(PlayerSD inverter, PlayerSD player) {
        if (this.invites.containsKey(player)) {
            inverter.sendMessage(ChatColor.RED + "You already invited that player!");
            return;
        }

        if (this.players.get(inverter) == PlayerPartyType.MEMBER) {
            inverter.sendMessage(LINE);
            inverter.sendCenteredMessage(ChatColor.RED + "You have to be moderator or higher to invite someone!");
            inverter.sendMessage(LINE);
            return;
        }
        if (this.players.containsKey(player)) {
            inverter.sendMessage(LINE);
            inverter.sendCenteredMessage(ChatColor.RED + "This player is already in your party!");
            inverter.sendMessage(LINE);
            return;
        }

        this.invites.put(player, System.currentTimeMillis());

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + inverter.getDisplayName() + ChatColor.YELLOW + " invited " + player.getDisplayName() + ChatColor.YELLOW + " to the party");
        this.sendLine();

        player.sendMessage(LINE);
        player.sendMessage(new MessageModifier(ChatColor.YELLOW + inverter.getDisplayName() + ChatColor.YELLOW + " invited you to " + this.leader.getDisplayName() + ChatColor.YELLOW + "'s party "), new MessageModifier(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK HERE TO JOIN!", new Entry<>(TextMessage.Modifier.MESSAGE, "/party accept " + leader.getName())));
        player.sendMessage(LINE);

        Functions.Wait(1200L, () -> {
            if (this.invites.containsKey(player)) {
                player.sendMessage(LINE);
                player.sendCenteredMessage(ChatColor.RED + "Your party invite from " + inverter.getDisplayName() + ChatColor.RED + " expired!");
                player.sendMessage(LINE);

                inverter.sendMessage(LINE);
                inverter.sendCenteredMessage(ChatColor.RED + "Your party invite to " + player.getDisplayName() + ChatColor.RED + " expired!");
                inverter.sendMessage(LINE);

                this.invites.remove(player);
            }
        });
    }

    public void accept(PlayerSD player) {
        if (System.currentTimeMillis() - this.invites.getOrDefault(player, 0L) > 60_000) {
            player.sendMessage(LINE);
            player.sendMessage(ChatColor.RED + "Your invite is already expired!");
            player.sendMessage(LINE);
            return;
        }
        this.invites.remove(player);
        this.add(player);
    }

    public void join(PlayerSD player) {
        if (this.type == PartyType.PRIVATE) {
            player.sendMessage(LINE);
            player.sendCenteredMessage(ChatColor.RED + "This party is not on public!");
            player.sendMessage(LINE);
            return;
        }
        if (this.bannedPlayers.contains(player.getUniqueId())) {
            player.sendMessage(LINE);
            player.sendCenteredMessage(ChatColor.RED + "You have been banned from this party!");
            player.sendMessage(LINE);
            return;
        }
        this.add(player);
    }

    public void forceJoin(PlayerSD player) {
        this.add(player);
    }

    public void joinSilent(PlayerSD player) {
//        if (!player.hasPermission("skyblockdragons.party.silentjoin")) return;
        this.players.put(player, PlayerPartyType.SILENT);
        player.setParty(this);

        player.sendMessage(LINE);
        player.sendMessage(ChatColor.YELLOW + "You have joined the party in silent");
        player.sendMessage(LINE);
    }

    private void add(PlayerSD player) {
        if (player.getParty() != null) {
            player.getParty().leave(player);
        }

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + player.getName() + ChatColor.YELLOW + " joined the party");
        this.sendLine();

        this.players.put(player, PlayerPartyType.MEMBER);
        player.setParty(this);
        player.sendMessage(LINE);
        player.sendCenteredMessage(ChatColor.YELLOW + "You have joined the party");
        player.sendMessage(LINE);
    }

    public void transferLeader(PlayerSD player) {
        if (this.leader == player) {
            this.leader.sendMessage(LINE);
            this.leader.sendCenteredMessage(ChatColor.RED + "You are already the leader!");
            this.leader.sendMessage(LINE);
            return;
        }
        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " transferred the leader to " + player.getDisplayName());
        this.sendLine();
        this.players.put(this.leader, PlayerPartyType.MODERATOR);
        this.players.put(player, PlayerPartyType.LEADER);
        this.leader = player;
    }

    public void promote(PlayerSD player) {
        if (this.players.get(player) == PlayerPartyType.MEMBER) {
            this.players.put(player, PlayerPartyType.MODERATOR);

            this.sendLine();
            this.sendCenteredMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.YELLOW + " has been promoted to Moderator");
            this.sendLine();
        } else if (this.players.get(player) == PlayerPartyType.MODERATOR) {
            this.transferLeader(player);
        } else {
            this.leader.sendMessage(ChatColor.RED + "You can't promote a leader!");
        }
    }

    public void warp() {
        this.sendLine();
        this.sendMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " warped everyone to " + this.leader.getWorldSD().getWarp().getName());
        this.sendLine();

        for (PlayerSD player : this) {
            player.warp(this.leader.getWorldSD().getWarp());
        }
    }

    public void sendList(PlayerSD player) {
        player.sendMessage(LINE);

        player.sendMessage(ChatColor.YELLOW + "  Leader:");
        player.sendMessage("    " + ChatColor.YELLOW + this.leader.getDisplayName());
        player.sendMessage();

        player.sendMessage(ChatColor.YELLOW + "  Moderators:");
//        StringBuilder moderators = new StringBuilder();
//        int i = 0;
//        for (PlayerSD moderator : this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MODERATOR).collect(Collectors.toList())) {
//            if (i != 0)
//                moderators.append(", ");
//            moderators.append(moderator.getDisplayName());
//            i++;
//        }
//        player.sendMessage("    " + ChatColor.YELLOW + moderators);
        player.sendMessage("    " + ChatColor.YELLOW + this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MODERATOR).collect(Collectors.toList()));
        player.sendMessage();

        player.sendMessage(ChatColor.YELLOW + "  Members:");
//        StringBuilder members = new StringBuilder();
//        i = 0;
//        for (PlayerSD moderator : this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MEMBER).collect(Collectors.toList())) {
//            if (i != 0)
//                members.append(", ");
//            members.append(moderator.getDisplayName());
//            i++;
//        }
//        player.sendMessage("    " + ChatColor.YELLOW + members);
        player.sendMessage("    " + ChatColor.YELLOW + this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MEMBER).collect(Collectors.toList()));
        player.sendMessage(LINE);
    }

    public void delete() {
        this.isAlive = false;

        this.sendLine();
        this.sendCenteredMessage(ChatColor.RED + this.leader.getDisplayName() + ChatColor.YELLOW + " deleted the party!");
        this.sendLine();

        for (PlayerSD player : this) {
            player.setParty(null);
        }

        parties.remove(this);

        this.players.clear();
        this.leader = null;

        this.type = null;

        this.invites.clear();
    }

    public void leave(PlayerSD player) {
        if (!this.players.containsKey(player)) return;

        if (this.players.get(player) != PlayerPartyType.SILENT) {
            this.sendLine();
            this.sendCenteredMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.YELLOW + " has left from the party!");
            this.sendLine();

            if (this.players.size() == 1) {
                this.delete();
                return;
            }

            if (this.leader == player) {
                List<PlayerSD> moderators = this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MODERATOR).collect(Collectors.toList());
                if (moderators.size() > 0) {
                    this.transferLeader(moderators.get(0));
                } else {
                    this.transferLeader(this.players.keySet().stream().filter(p -> p != player).iterator().next());
                }
            }
        }
        this.remove(player);
    }

    public void kick(PlayerSD player) {
        PlayerSD toRemove = null;
        for (PlayerSD playerSD : this) {
            if (playerSD.getUniqueId().equals(player.getUniqueId())) {
                toRemove = playerSD;
                break;
            }
        }
        if (toRemove == null) return;

        this.remove(toRemove);

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + ((Player) player).getDisplayName() + ChatColor.YELLOW + " has been kicked from the party!");
        this.sendLine();
    }

    private void remove(PlayerSD player) {
        this.players.remove(player);
        player.setParty(null);
    }

    public void kickOffline() {
        for (PlayerSD player : this) {
            if (!player.isOnline()) {
                this.kick(player);
            }
        }
    }

    public void ban(PlayerSD player) {
        this.bannedPlayers.add(player.getUniqueId());

        for (PlayerSD playerSD : players.keySet().stream().filter(p -> this.players.get(p) != PlayerPartyType.MEMBER).collect(Collectors.toList())) {
            playerSD.sendMessage(LINE);
            playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " banned " + ((Player) player).getDisplayName() + ChatColor.YELLOW + " from the party");
            playerSD.sendMessage(LINE);
        }
    }

    public void unban(PlayerSD player) {
        if (!this.bannedPlayers.contains(player.getUniqueId())) {
            this.leader.sendMessage(LINE);
            this.leader.sendCenteredMessage(ChatColor.RED + "This player is not banned from this party!");
            this.leader.sendMessage(LINE);
            return;
        }

        this.bannedPlayers.remove(player.getUniqueId());

        for (PlayerSD playerSD : players.keySet().stream().filter(p -> this.players.get(p) != PlayerPartyType.MEMBER).collect(Collectors.toList())) {
            playerSD.sendMessage(LINE);
            playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " unbanned " + ((Player) player).getDisplayName() + ChatColor.YELLOW + " from the party");
            playerSD.sendMessage(LINE);
        }
    }

    public void mute(PlayerSD player, PlayerSD to) {
        if (this.players.get(player) == PlayerPartyType.MODERATOR && this.players.get(to) == PlayerPartyType.MODERATOR) {
            player.sendMessage(ChatColor.RED + "You can't mute moderator as moderator!");
            return;
        }

        if (this.mutedPlayers.getOrDefault(to, false)) {
            this.sendLine();
            this.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.YELLOW + " unmuted " + to.getDisplayName());
            this.sendLine();
            this.mutedPlayers.put(to, false);
        } else {
            this.sendLine();
            this.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.YELLOW + " muted " + to.getDisplayName());
            this.sendLine();
            this.mutedPlayers.put(to, true);
        }
    }

    public static void sendParties(PlayerSD player) {
        player.sendMessage(LINE);
        player.sendMessage(ChatColor.YELLOW + "Parties:");
        for (Party party : parties) {
            player.sendMessage("  " + ChatColor.YELLOW + party.getLeader().getDisplayName() + " - " + party.getPlayers().size());
        }
        player.sendMessage(LINE);
    }

    public void muteAll() {
        if (this.muteAll) {
            this.sendLine();
            this.sendMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " has muted the chat");
            this.sendLine();

            this.muteAll = false;
        } else {
            this.sendLine();
            this.sendMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " has unmuted the chat");
            this.sendLine();

            this.muteAll = true;
        }
    }

    public PlayerSD getLeader() {
        return this.leader;
    }

    public void makePlayerSay(PlayerSD from, String message) {
        ChatChannel.PARTY.send(from, message);
    }

    public void sendMessage(String message) {
        for (PlayerSD player : this.players.keySet()) {
            player.sendMessage(message);
        }
    }

    public void sendCenteredMessage(String message) {
        for (PlayerSD player : this.players.keySet()) {
            player.sendCenteredMessage(message);
        }
    }

    private void sendLine() {
        this.sendMessage(LINE);
    }

    @Override
    public Iterator<PlayerSD> iterator() {
        return this.players.keySet().stream().iterator();
    }

    @Override
    public void forEach(Consumer<? super PlayerSD> action) {
        Objects.requireNonNull(action);
        for (PlayerSD e : this.players.keySet()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<PlayerSD> spliterator() {
        return Spliterators.spliterator(this.players.keySet(), Spliterator.ORDERED);
    }

    public Stream<PlayerSD> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public String toString() {
        return this.leader.getName();
    }
}
