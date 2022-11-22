package me.maxiiiiii.skyblockdragons.player.stats;

import me.maxiiiiii.skyblockdragons.inventory.UpdatingPageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.stats.StatType;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PlayerStatsAdminMenu extends UpdatingPageMenu {
    private final PlayerSD target;

    public PlayerStatsAdminMenu(PlayerSD player, PlayerSD target) {
        super(player,
                player == target ? target.getName() + "'s Stats Admin" : "Stats Admin",
                6,
                InventoryGlassType.ALL,
                StatTypes.STATS.stream().map(s -> (Supplier<ItemStack>) () -> getItem(target, s)).collect(Collectors.toList()),
                false
        );
        this.target = target;
    }

    private static ItemStack getItem(PlayerSD player, StatType statType) {
        ItemStack item = addNBT(statType.getItem(), "STAT_" + statType.getName().toUpperCase().replace(" ", "_"));
        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.GRAY + "Current: " + ChatColor.GREEN + player.getStats().get(statType).getString());
        lores.add("");
        lores.addAll(Functions.loreBuilder(statType.getDescription()));
        lores.add("");
        lores.add(ChatColor.YELLOW + "Click to change!");
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        String nbt = this.getNBT(e.getCurrentItem());
        if (nbt.contains("STAT_")) {
            StatType statType = StatTypes.get(nbt.replace("STAT_", ""));
            player.openSign("Enter Number", lines -> {
                if (lines[0].trim().isEmpty()) {
                    target.getStats().getBaseStats().reset(statType);
                    Functions.Wait(1L, () -> {
                        target.applyStats(false);
                        this.update();
                        this.open(false);
                    });
                    return;
                }

                try {
                    target.getStats().getBaseStats().set(statType, Double.parseDouble(lines[0]));
                    Functions.Wait(1L, () -> {
                        target.applyStats(false);
                        this.update();
                        this.open(false);
                    });
                } catch (NumberFormatException ex) {
                    target.sendMessage(ChatColor.RED + "Can't understand this number " + lines[0]);
                }
            });
        }
    }
}
