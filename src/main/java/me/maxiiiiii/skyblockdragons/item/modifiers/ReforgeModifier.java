package me.maxiiiiii.skyblockdragons.item.modifiers;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifier;
import me.maxiiiiii.skyblockdragons.item.stats.StatModifierType;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateItemStatsEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class ReforgeModifier extends ItemModifier {
    private final ReforgeType reforge;

    public ReforgeModifier(ReforgeType reforge) {
        super(ReforgeModifier.class);
        this.reforge = reforge;
    }

    public ReforgeModifier() {
        this(ReforgeType.NULL);
    }

    public ReforgeType get() {
        return this.reforge;
    }

    public static ItemModifier getModifier(ItemStack item) {
        return new ReforgeModifier(Functions.getReforge(item));
    }

    public static class Listener implements org.bukkit.event.Listener {
        @EventHandler
        public void updateItemStats(UpdateItemStatsEvent e) {
            Item item = e.getStats().getItem();
            item.getModifiers().getReforge().getStats().get(item.getRarity().getLevel() - 1).stream().filter(s -> !s.isEmpty()).forEach(s ->
                    e.getStats().getModifiers().add(new StatModifier(StatModifierType.REFORGE, item.getModifiers().getReforge().toString(), s))
            );
        }
    }
}
