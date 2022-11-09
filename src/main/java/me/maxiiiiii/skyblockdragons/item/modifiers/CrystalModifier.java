package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.CrystalType;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystals;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifier;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifierType;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateItemStatsEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CrystalModifier extends ItemModifier {
    private final Crystals crystals;

    public CrystalModifier(Crystals crystals) {
        super(CrystalModifier.class);
        this.crystals = crystals;
    }

    public CrystalModifier(Crystals crystals, Crystal... crystalsToAdd) {
        this(crystals);
        Arrays.stream(crystalsToAdd).forEach(this.crystals::add);
    }

    public CrystalModifier(Crystal... crystals) {
        this(new Crystals(crystals));
    }

    public CrystalModifier() {
        this(new Crystals());
    }

    public Crystals get() {
        return crystals;
    }

    public static CrystalModifier getModifier(ItemStack item) {
        Crystals crystals = new Crystals();
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            NBTCompound compound = nbt.getCompound("Crystals");

            if (compound == null) return new CrystalModifier(crystals);

            for (String key : compound.getKeys()) {
                String[] values = compound.getString(key).split("::");
                crystals.add(new Crystal(
                        CrystalType.valueOf(values[0]),
                        Integer.parseInt(values[1])
                ));
            }

            return new CrystalModifier(crystals);
        } catch (NullPointerException ignored) {}
        return new CrystalModifier(crystals);
    }

    public static class Listener implements org.bukkit.event.Listener {
        @EventHandler
        public void updateItemStats(UpdateItemStatsEvent e) {
            Item item = e.getStats().getItem();
            for (Crystal crystal : item.getModifiers().getCrystals()) {
                e.getStats().getModifiers().add(new StatModifier(StatModifierType.CRYSTAL, "", crystal.getStat()));
            }
        }
    }
}
