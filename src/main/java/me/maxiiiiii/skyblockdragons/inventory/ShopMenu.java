package me.maxiiiiii.skyblockdragons.inventory;

import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Entry;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShopMenu extends PageMenu {
    private final List<Entry<Item, Number>> items;

    @SafeVarargs
    public ShopMenu(PlayerSD player, String title, Entry<Item, Number>... items) {
        super(player,
                title,
                6,
                InventoryGlassType.SURROUND,
                Arrays.stream(items).map(e -> addLine(e.getA(), "", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + Functions.getNumberFormat(e.getB()), "", ChatColor.YELLOW + "Click to buy!")).collect(Collectors.toList()),
                true
        );
        this.items = Arrays.asList(items);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
            Item item = new Item(player, e.getCurrentItem());
            for (Entry<Item, Number> entry : items) {
                if (entry.getA().getMaterial() == item.getMaterial()) {
                    double cost = entry.getB().doubleValue();
                    if (!player.ignoreRequirements() && player.getCoins() >= cost) {
                        player.give(item);
                        player.sendMessage(ChatColor.GREEN + "You have bought " + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GREEN + " for " + Functions.getNumberFormat(cost) + ".");
                        player.playSound(Sound.BLOCK_NOTE_HARP);
                        if (!player.ignoreRequirements())
                            player.removeCoins(cost);
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have enough coins to buy this item!");
                    }
                    return;
                }
            }
        }
    }
}
