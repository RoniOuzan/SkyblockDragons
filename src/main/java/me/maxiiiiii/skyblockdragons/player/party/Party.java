package me.maxiiiiii.skyblockdragons.player.party;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.TextMessage;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Getter
public class Party implements Iterable<PlayerSD> {
    public static final List<Party> parties = new ArrayList<>();
    public static final String LINE = ChatColor.BLUE + "--------------------------------------------------------";

    private boolean isAlive;

    private final Map<PlayerSD, PlayerPartyType> players;
    private PlayerSD leader;

    private PartyType type;

    private final Map<PlayerSD, Long> invites;

    private final List<UUID> bannedPlayers;

    public Party(PlayerSD leader) {
        parties.add(this);
        this.isAlive = true;

        this.players = new LinkedHashMap<>();
        this.players.put(leader, PlayerPartyType.LEADER);
        this.leader = leader;

        this.type = PartyType.PRIVATE;

        this.invites = new HashMap<>();

        this.bannedPlayers = new ArrayList<>();

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
        }

        this.invites.put(player, System.currentTimeMillis());

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + inverter.getDisplayName() + ChatColor.YELLOW + " invited " + player.getDisplayName() + ChatColor.YELLOW + " to the party");
        this.sendLine();

        player.sendMessage(LINE);
        new TextMessage().append(ChatColor.YELLOW + inverter.getDisplayName() + ChatColor.YELLOW + " invited you to " + this.leader.getDisplayName() + ChatColor.YELLOW + "'s party ").save().append(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK HERE TO JOIN!").setClickAsExecuteCmd("/party accept " + leader.getName()).save().send(player);
        player.sendMessage(LINE);

        Functions.Wait(1200L, () -> {
            if (this.invites.containsKey(player)) {
                player.sendMessage(LINE);
                player.sendCenteredMessage(ChatColor.RED + "Your party invite from " + inverter.getDisplayName() + ChatColor.YELLOW + " expired!");
                player.sendMessage(LINE);
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
        // TODO
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
            this.remove(player);
        }

        parties.remove(this);

        this.players.clear();
        this.leader = null;

        this.type = null;

        this.invites.clear();
    }

    public void leave(PlayerSD player) {
        if (!this.players.containsKey(player)) return;
        this.remove(player);

        if (this.players.get(player) == PlayerPartyType.SILENT) return;

        player.sendMessage(LINE);
        player.sendCenteredMessage(ChatColor.RED + "You have left from the party!");
        player.sendMessage(LINE);
        if (this.players.size() == 0) {
            this.delete();
            return;
        }

        this.sendLine();
        this.sendCenteredMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.YELLOW + " has left from the party!");
        this.sendLine();

        if (this.leader == player) {
            List<PlayerSD> moderators = this.players.keySet().stream().filter(p -> this.players.get(p) == PlayerPartyType.MODERATOR).collect(Collectors.toList());
            if (moderators.size() > 0) {
                this.leader = moderators.get(0);
            } else {
                this.leader = this.players.keySet().iterator().next();
            }
            this.sendLine();
            this.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " has promoted to the leader");
            this.sendLine();
        }
    }

    public void kick(OfflinePlayer player) {
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
        if (player instanceof Player)
            this.sendCenteredMessage(ChatColor.YELLOW + ((Player) player).getDisplayName() + ChatColor.YELLOW + " has been kicked from the party!");
        else
            this.sendCenteredMessage(ChatColor.YELLOW + player.getName() + ChatColor.YELLOW + " has been kicked from the party!");
        this.sendLine();
    }

    private void remove(OfflinePlayer player) {
        PlayerSD playerSD = null;
        for (PlayerSD partyMember : this) {
            if (partyMember.getUniqueId().equals(player.getUniqueId())) {
                playerSD = partyMember;
                break;
            }
        }
        if (playerSD == null) return;
        this.players.remove(playerSD);
        playerSD.setParty(null);
    }

    public void kickOffline() {
        // TODO
    }

    public void ban(OfflinePlayer player) {
        this.bannedPlayers.add(player.getUniqueId());

        for (PlayerSD playerSD : players.keySet().stream().filter(p -> this.players.get(p) != PlayerPartyType.MEMBER).collect(Collectors.toList())) {
            playerSD.sendMessage(LINE);
            if (player instanceof Player)
                playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " banned " + ((Player) player).getDisplayName() + ChatColor.YELLOW + " from the party");
            else
                playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " banned " + player.getName() + ChatColor.YELLOW + " from the party");
            playerSD.sendMessage(LINE);
        }
    }

    public void unban(OfflinePlayer player) {
        if (!this.bannedPlayers.contains(player.getUniqueId())) {
            this.leader.sendMessage(LINE);
            this.leader.sendCenteredMessage(ChatColor.RED + "This player is not banned from this party!");
            this.leader.sendMessage(LINE);
            return;
        }

        this.bannedPlayers.remove(player.getUniqueId());

        for (PlayerSD playerSD : players.keySet().stream().filter(p -> this.players.get(p) != PlayerPartyType.MEMBER).collect(Collectors.toList())) {
            playerSD.sendMessage(LINE);
            if (player instanceof Player)
                playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " unbanned " + ((Player) player).getDisplayName() + ChatColor.YELLOW + " from the party");
            else
                playerSD.sendCenteredMessage(ChatColor.YELLOW + this.leader.getDisplayName() + ChatColor.YELLOW + " unbanned " + player.getName() + ChatColor.YELLOW + " from the party");
            playerSD.sendMessage(LINE);
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
