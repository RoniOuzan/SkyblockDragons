package me.maxiiiiii.skyblockdragons.item.stats.newfile;

import me.maxiiiiii.skyblockdragons.player.PlayerSD;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Stats implements Iterable<Stat> {
    private final Map<StatType, Stat> stats;

    public Stats(List<Stat> stats) {
        this.stats = new HashMap<>();
        for (Stat stat : stats) {
            this.stats.put(stat.getType(), new Stat(stat.getType(), stat.get()));
        }
    }

    public Stats() {
        this(new ArrayList<>());
    }

    public Stat get(StatType type) {
        return stats.getOrDefault(type, new Stat(type, 0));
    }

    public void add(StatType type, double amount) {
        Stat stat = stats.get(type);
        stat.add(amount);
    }

    public void remove(StatType type, double amount) {
        Stat stat = stats.get(type);
        stat.remove(amount);
    }

    public void normalize(PlayerSD player, StatType type) {
        Stat stat = stats.get(type);
        stat.normalize(player);
    }

    public void normalize(PlayerSD player) {
        for (Stat stat : this) {
            stat.normalize(player);
        }
    }

    public List<Stat> toList() {
        return new ArrayList<>(this.stats.values());
    }

    @Override
    public Iterator<Stat> iterator() {
        return this.toList().iterator();
    }

    @Override
    public void forEach(Consumer<? super Stat> action) {
        Objects.requireNonNull(action);
        for (Stat e : this.toList()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Stat> spliterator() {
        return Spliterators.spliterator(this.toList(), Spliterator.ORDERED);
    }

    public Stream<Stat> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
