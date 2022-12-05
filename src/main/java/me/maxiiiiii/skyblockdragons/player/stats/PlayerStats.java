package me.maxiiiiii.skyblockdragons.player.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.stats.*;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class PlayerStats extends Stats {
    @Getter(AccessLevel.NONE)
    private final Map<StatType, Multiplier> multiplayer;
    private final PlayerSD player;
    private final PlayerBaseStats baseStats;
    private final Map<StatType, List<StatAdder<?>>> multiplierAdders;

    public PlayerStats(PlayerSD player) {
        super();
        this.player = player;
        this.baseStats = new PlayerBaseStats(player);
        this.multiplayer = new HashMap<>();
        this.multiplierAdders = new HashMap<>();
    }

    @Override
    protected double getDefaultValue(StatType type) {
        return type.getBase(this.player);
    }

    @Override
    public void reset() {
        super.reset();
        multiplayer.clear();
        multiplierAdders.clear();
    }

    public <T> void addPostMultiplier(StatType statType, double post, StatAdderType<T> adderType, T source) {
        Multiplier multiplier = multiplayer.getOrDefault(statType, new Multiplier());
        multiplier.addPost(post);
        this.multiplayer.put(statType, multiplier);

        List<StatAdder<?>> adders = this.multiplierAdders.getOrDefault(statType, new ArrayList<>());
        adders.add(new StatAdder<>(post, adderType, source, true));
        this.multiplierAdders.put(statType, adders);
    }

    public <T> void addBaseMultiplier(StatType statType, double base, StatAdderType<T> adderType, T source) {
        Multiplier multiplier = multiplayer.getOrDefault(statType, new Multiplier());
        multiplier.addBase(base);
        this.multiplayer.put(statType, multiplier);

        List<StatAdder<?>> adders = this.multiplierAdders.getOrDefault(statType, new ArrayList<>());
        adders.add(new StatAdder<>(base, adderType, source, true));
        this.multiplierAdders.put(statType, adders);
    }

    public void update() {
        for (StatType statType : multiplayer.keySet()) {
            this.get(statType).set(multiplayer.get(statType).multiply(this.get(statType).get()));

            for (StatAdder<?> adder : multiplierAdders.get(statType)) {
                this.get(statType).getStatAdders().add(adder);
            }
        }

        for (Entry<StatType, Double> entry : baseStats) {
            this.get(entry.getA()).set(entry.getB());
        }
    }
}
