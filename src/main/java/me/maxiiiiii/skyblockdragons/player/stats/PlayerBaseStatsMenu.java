package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.stream.Collectors;

public class PlayerBaseStatsMenu extends PageMenu {
    public PlayerBaseStatsMenu(PlayerSD player) {
        super(player,
                "Your Base Stats",
                6,
                InventoryGlassType.ALL,
                StatTypes.STATS.stream().map(s -> addLine(addNBT(s.getItem(), "STAT_" + s.getName().toUpperCase().replace(" ", "_")), "", ChatColor.YELLOW + "Click to set!")).collect(Collectors.toList()),
                false
        );
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.equals("STAT_")) {
            StatType stat = StatTypes.get(nbt.replace("STAT_", ""));
            final String[] amount = new String[]{""};

            try {
                player.getStats().getBaseStats().set(stat, Double.parseDouble(amount[0]));
            } catch (NumberFormatException ex) {
                player.getStats().getBaseStats().reset(stat);
            }
        }
    }
}
