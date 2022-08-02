package me.maxiiiiii.skyblockdragons.dungeon;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DungeonParty extends ArrayList<DungeonPlayer> {
    public DungeonParty(List<PlayerSD> players) {
        this.addAll(players.stream().map(DungeonPlayer::new).collect(Collectors.toList()));
    }

    public void send(String... messages) {
        for (String message : messages) {
            this.forEach(p -> p.sendMessage(message));
        }
    }
}
