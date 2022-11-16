package me.maxiiiiii.skyblockdragons.item.stats;

import lombok.AccessLevel;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;

import java.util.*;

@Getter
public class ItemStats extends Stats {
    private final Item item;
    @Getter(AccessLevel.NONE)
    private final Map<StatType, Multiplier> multiplayer;
    private final List<StatModifier> modifiers;

    public ItemStats(Stats stats, Item item) {
        super(stats.toList());
        this.item = item;
        this.multiplayer = new HashMap<>();
        this.modifiers = new ArrayList<>();
    }

    @Override
    public void reset(PlayerSD player) {
        super.reset(player);

        multiplayer.clear();
    }

    public void addMultiplier(StatType statType, double base, double post) {
        Multiplier multiplier = multiplayer.getOrDefault(statType, new Multiplier());
        multiplier.addBase(base);
        multiplier.addPost(post);
        this.multiplayer.put(statType, multiplier);
    }

    public void addMultiplier(StatType statType, double base) {
        this.addMultiplier(statType, base, 0);
    }

    public void addAllStatsMultipliers(double base, double post) {
        for (Stat stat : this) {
            this.addMultiplier(stat.getType(), base, post);
        }
    }

    public void addDamageMultipliers(double base, double post) {
        this.addMultiplier(StatTypes.DAMAGE, base, post);
        this.addMultiplier(StatTypes.STRENGTH, base, post);
        this.addMultiplier(StatTypes.CRIT_DAMAGE, base, post);
        this.addMultiplier(StatTypes.CRIT_CHANCE, base, post);
        this.addMultiplier(StatTypes.ABILITY_DAMAGE, base, post);
        this.addMultiplier(StatTypes.ATTACK_SPEED, base, post);
        this.addMultiplier(StatTypes.FEROCITY, base, post);
        this.addMultiplier(StatTypes.MANA, base, post);
    }

    public void applyMultipliers() {
        for (StatModifier modifier : modifiers) {
            this.add(modifier.getStat().getType(), modifier.getStat().get());
        }

        for (StatType statType : multiplayer.keySet()) {
            this.get(statType).set(multiplayer.get(statType).multiply(this.get(statType).get()));
        }
    }

    public String getLoreModifiers(StatType statType) {
        this.modifiers.sort(Comparator.comparingInt(m -> m.getType().getPriority()));

        StringBuilder string = new StringBuilder();
        modifiers.stream().filter(m -> !m.getStat().isEmpty() && m.getStat().getType() == statType).forEach(m ->
                string.append(" ").append(m.getType().getText().apply(m.getText(), m.getStat()))
        );
        return string.toString();
    }
}
