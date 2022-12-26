package me.maxiiiiii.skyblockdragons.world.worlds.hub.npcs;

import me.maxiiiiii.skyblockdragons.inventory.PageMenu;
import me.maxiiiiii.skyblockdragons.inventory.enums.InventoryGlassType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerClickOnNPCEvent;
import me.maxiiiiii.skyblockdragons.world.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;

public class CombatShopNPC extends NPC {
    public CombatShopNPC(Location location) {
        super("Combat Shop", location, EntityType.VILLAGER);
    }

    @Override
    public void onClick(PlayerClickOnNPCEvent e) {
        new View(e.getPlayer());
    }

    private static class View extends PageMenu {
        public View(PlayerSD player) {
            super(player,
                    "Combat Shop",
                    6,
                    InventoryGlassType.SURROUND,
                    Arrays.asList(
                            addLine(new Item(Items.get("VILLAGE_TALISMAN")), "", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1,000", "", ChatColor.YELLOW + "Click to buy!"),
                            addLine(new Item(Items.get("SCAVENGER_TALISMAN")), "", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "1,000", "", ChatColor.YELLOW + "Click to buy!")
                    ),
                    false);
        }

        @Override
        public void onInventoryClick(InventoryClickEvent e) {
            if (this.getNBT(e.getCurrentItem()).equals("PAGE_ITEM")) {
                if (player.getCoins() >= 1000) {
                    player.give(new Item(Items.get(e.getCurrentItem())));
                    player.removeCoins(1000);
                    player.sendMessage(ChatColor.GREEN + "You have bought " + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GREEN + ".");
                    player.playSound(Sound.BLOCK_NOTE_HARP);
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough coins to buy this item!");
                }
            }
        }
    }
}
