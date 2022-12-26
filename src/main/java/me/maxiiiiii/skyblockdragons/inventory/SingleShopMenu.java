package me.maxiiiiii.skyblockdragons.inventory;

import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SingleShopMenu extends Menu {
    private final Item item;
    private final double cost;

    public SingleShopMenu(PlayerSD player, String title, Item item, double cost) {
        super(player, title, 6, InventoryGlassType.ALL, false);
        this.item = item;
        this.cost = cost;
    }

    @Override
    public void update() {
        this.setItem(22, addLine(addNBT(new Item(player, this.item), "ITEM"), "", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + Functions.getNumberFormat(this.cost), "", ChatColor.YELLOW + "Click to buy!"));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (this.getNBT(e.getCurrentItem()).equals("ITEM")) {
            if (player.ignoreRequirements() || player.getCoins() >= this.cost) {
                player.give(new Item(player, this.item));
                player.sendMessage(ChatColor.GREEN + "You have bought " + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GREEN + " for " + Functions.getNumberFormat(cost) + ".");
                player.playSound(Sound.BLOCK_NOTE_HARP);
                if (!player.ignoreRequirements())
                    player.removeCoins(this.cost);
            } else {
                player.sendMessage(ChatColor.RED + "You don't have enough coins to buy this item!");
            }
        }
    }
}
