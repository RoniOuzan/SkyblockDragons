package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

public class Killer implements Comparable<Killer> {
    public final PlayerSD player;
    public final double damage;

    public Killer(PlayerSD player, double damage) {
        this.player = player;
        this.damage = damage;
    }

    @Override
    public int compareTo(Killer killer) {
        return (int) (killer.damage - this.damage);
    }
}
