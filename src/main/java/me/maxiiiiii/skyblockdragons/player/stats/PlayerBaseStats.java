package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PlayerBaseStats implements Iterable<Entry<StatType, Double>> {
    private final PlayerSD player;
    private final Map<StatType, Double> stats;

    public PlayerBaseStats(PlayerSD player) {
        this.player = player;
        this.stats = new HashMap<>();
    }

    public void set(StatType statType, double amount) {
        this.stats.put(statType, amount);
    }

    public void reset(StatType statType) {
        this.stats.remove(statType);
    }

    private List<Entry<StatType, Double>> toEntries() {
        return this.stats.entrySet().stream().map(e -> new Entry<>(e.getKey(), e.getValue())).collect(Collectors.toList());
    }

    @Override
    public Iterator<Entry<StatType, Double>> iterator() {
        return toEntries().iterator();
    }

    @Override
    public void forEach(Consumer<? super Entry<StatType, Double>> action) {
        Objects.requireNonNull(action);
        for (Entry<StatType, Double> e : this.toEntries()) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Entry<StatType, Double>> spliterator() {
        return Spliterators.spliterator(this.toEntries(), Spliterator.ORDERED);
    }

    public Stream<Entry<StatType, Double>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
