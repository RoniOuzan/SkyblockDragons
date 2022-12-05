package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Getter
public class StatAdder<T> implements Comparable<StatAdder<?>> {
    private final double amount;
    private final StatAdderType<T> type;
    private final T source;
    private final boolean isPercentage;

    public StatAdder(double amount, StatAdderType<T> type, T source, boolean isPercentage) {
        this.amount = amount;
        this.type = type;
        this.source = source;
        this.isPercentage = isPercentage;
    }

    public StatAdder(double amount, StatAdderType<T> type, T source) {
        this(amount, type, source, false);
    }

    public ItemStack getItem(StatType statType) {
        return this.type.getItem(this.source, this.amount, this.isPercentage, statType);
    }

    @Override
    public String toString() {
        return "StatAdder{" +
                "amount=" + amount +
                ", type=" + type +
                ", source=" + source +
                '}';
    }

    @Override
    public int compareTo(@NotNull StatAdder<?> o) {
        return (int) Math.ceil(o.getAmount() - this.getAmount());
    }
}
