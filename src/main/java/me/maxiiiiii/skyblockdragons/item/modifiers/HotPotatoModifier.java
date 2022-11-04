package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifier;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifierType;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateItemStatsEvent;
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

    public static ItemModifier getModifier(ItemStack item) {
        return Functions.getHotPotato(item);
    }

    public static class Listener implements org.bukkit.event.Listener {
        @EventHandler
        public void updateItemStats(UpdateItemStatsEvent e) {
            int hotPotato = e.getStats().getItem().getModifiers().getHotPotato();
            e.getStats().getModifiers().add(new StatModifier(StatModifierType.HOT_POTATO, "", new Stat(hotPotato * 2, StatType.DAMAGE)));
            e.getStats().getModifiers().add(new StatModifier(StatModifierType.HOT_POTATO, "", new Stat(hotPotato * 2, StatType.STRENGTH)));
        }
    }
}
