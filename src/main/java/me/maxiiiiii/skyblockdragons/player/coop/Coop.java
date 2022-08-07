package me.maxiiiiii.skyblockdragons.player.coop;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class Coop extends ArrayList<PlayerSD> {
    public static final HashMap<UUID, Coop> coops = new HashMap<>();

    public PlayerSD leader;
    public UUID uuid;

    public Coop(PlayerSD... players) {
        super(Arrays.stream(players).collect(Collectors.toList()));
        this.leader = players[0];
        this.uuid = UUID.randomUUID();
//        for (PlayerSD player : players) {
//            player.coop = this;
//        }

        coops.put(this.uuid, this);
//        Coop.save();
    }

    @Override
    public boolean add(PlayerSD player) {
        super.add(player);
//        Coop.save();
        return true;
    }

    public ArrayList<PlayerSD> getPlayers() {
        return this;
    }

    public static Coop getPlayerCoop(PlayerSD player) {
        for (Coop coop : coops.values()) {
            if (coop.contains(player))
                return coop;
        }
        return null;
    }

//    public static void save() {
//        int i = 0;
//        for (Coop coop : coops.values()) {
//            Variables.setVariable(coop.uuid, "Coops", SkyblockDragons.serializer.serialize(coop), i);
//            i++;
//        }
//    }
//
//    public static void load() {
//        for (int i = 0; i < Variables.getVariableSize("Coops"); i++) {
//            coops.put(((Coop) SkyblockDragons.serializer.deserialize(Variables.getVariable("Coops", i).getValue())).uuid, ((Coop) SkyblockDragons.serializer.deserialize(Variables.getVariable("Coops", i).getValue())));
//        }
//    }
}
