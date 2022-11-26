package me.maxiiiiii.skyblockdragons.player.accessorybag;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AccessoryBag implements Iterable<Item> {
    private final PlayerSD player;
    private List<Item> items;
    private PowerStone powerStone;
    private final Set<PowerStone> learnedPowerStones;
    private int magicalPower;
    private final Map<StatType, Integer> tuning;

    public AccessoryBag(PlayerSD player) {
        this.player = player;
        this.items = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            ItemStack itemStack = Variables.getItemStack(player.getUniqueId(), "AccessoryBag", i);
            if (itemStack == null) break;
            this.items.add(new Item(player, itemStack));
        }

        this.powerStone = PowerStone.valueOf(Variables.getString(player.getUniqueId(), "PowerStone", "NONE"));
        this.learnedPowerStones = PowerStone.getStarterPowerStones();
        for (int i = 0; i < PowerStone.values().length; i++) {
            String powerStone = Variables.getString(player.getUniqueId(), "LearnedPowerStone", i, "");
            if (powerStone.isEmpty()) {
                break;
            }
            this.learnedPowerStones.add(PowerStone.valueOf(powerStone));
        }
        this.updateMagicalPower();

        this.tuning = new HashMap<>();
    }

    private void updateMagicalPower() {
        this.magicalPower = this.stream().mapToInt(i -> PowerStone.getRarityMagicalPower(i.getRarity())).sum();
    }

    public int getMagicalPower() {
        return magicalPower;
    }

    public Stats getStats() {
        Stats stats = this.getPowerStone().getStats(player);
        stats.add(this.getPowerStone().getUniqueStats());
        for (Map.Entry<StatType, Integer> entry : this.getTuning().entrySet()) {
            stats.add(entry.getKey(), entry.getValue() * PowerStoneMenu.AMOUNT_PER_POINT.get(entry.getKey()));
        }
        return stats;
    }

    public void tune(StatType stat, int amount) {
        this.tuning.put(stat, this.tuning.getOrDefault(stat, 0) + amount);
    }

    public Map<StatType, Integer> getTuning() {
        return tuning;
    }

    public Map<StatType, Double> getTuningStats() {
        Map<StatType, Double> stats = new HashMap<>();
        for (Map.Entry<StatType, Integer> entry : this.getTuning().entrySet()) {
            stats.put(entry.getKey(), entry.getValue() * PowerStoneMenu.AMOUNT_PER_POINT.get(entry.getKey()));
        }
        return stats;
    }

    public int getUsedTuning() {
        return tuning.values().stream().mapToInt(i -> i).sum();
    }

    public int getTuningPoints() {
        return this.getMagicalPower() / 10;
    }

    public int getLeftTuningPoints() {
        return this.getTuningPoints() - this.getUsedTuning();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;

        this.updateMagicalPower();
    }

    public PowerStone getPowerStone() {
        return powerStone;
    }

    public Set<PowerStone> getLearnedPowerStones() {
        return learnedPowerStones;
    }

    public void learnPowerStone(PowerStone powerStone) {
        this.learnedPowerStones.add(powerStone);
    }

    public void setPowerStone(PowerStone powerStone) {
        this.powerStone = powerStone;

        this.updateMagicalPower();
    }

    public void save() {
        Variables.delete(player.getUniqueId(), "AccessoryBag");
        for (int i = 0; i < items.size(); i++) {
            Variables.set(player.getUniqueId(), "AccessoryBag", i, items.get(i));
        }
        Variables.set(player.getUniqueId(), "PowerStone", this.powerStone.name());
        List<PowerStone> learnedStones = this.learnedPowerStones.stream().filter(p -> !p.isStarter()).collect(Collectors.toList());
        for (int i = 0; i < learnedStones.size(); i++) {
            Variables.set(player.getUniqueId(), "LearnedPowerStone", i, learnedStones.get(i).name());
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return this.items.iterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Objects.requireNonNull(action);
        for (Item e : this.items) {
            action.accept(e);
        }
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Spliterators.spliterator(this.items, Spliterator.ORDERED);
    }

    public Stream<Item> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
