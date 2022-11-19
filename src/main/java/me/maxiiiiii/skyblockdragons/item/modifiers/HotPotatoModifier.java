package me.maxiiiiii.skyblockdragons.item.modifiers;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.stats.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class HotPotatoModifier extends ItemModifier implements Listener {
    private final int amount;

    public HotPotatoModifier(int amount) {
        super(HotPotatoModifier.class);
        this.amount = amount;
    }

    public HotPotatoModifier() {
        this(0);
    }

    public int get() {
        return amount;
    }

    @Override
    public void applyNBT(Item item, NBTCompound nbt) {
        nbt.setInteger("HotPotato", this.amount);
    }

    public static HotPotatoModifier getModifier(ItemStack item) {
        return Functions.getHotPotato(item);
    }

    public static class Listener implements org.bukkit.event.Listener {
        @EventHandler
        public void updateItemStats(UpdateItemStatsEvent e) {
            int hotPotato = e.getStats().getItem().getModifiers().getHotPotato();
            e.getStats().addModifier(new StatModifier(StatModifierType.HOT_POTATO, "", new Stat(StatTypes.DAMAGE, hotPotato * 2)));
            e.getStats().addModifier(new StatModifier(StatModifierType.HOT_POTATO, "", new Stat(StatTypes.STRENGTH, hotPotato * 2)));
        }
    }
}
