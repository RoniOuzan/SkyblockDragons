package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;

@Getter
public class StatAdd<T> {
    private final StatAddType<T> type;
    private final T source;

    public StatAdd(StatAddType<T> type, T source) {
        this.type = type;
        this.source = source;
    }
}
