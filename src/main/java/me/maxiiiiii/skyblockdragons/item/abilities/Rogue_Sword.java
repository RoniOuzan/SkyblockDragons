package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Rogue_Sword implements Listener {
    public static HashMap<Player, Integer> rogueSwordAmountUsed = new HashMap<>();
    public static HashMap<Player, Long> rogueSwordLastTimeUsed = new HashMap<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("ROGUE_SWORD")) return;

        PlayerSD player = e.getPlayer();
        PlayerSD stat = SkyblockDragons.players.get(player.getUniqueId());

        if (stat.manaCost(item, 0)) return;

        rogueSwordAmountUsed.put(player, rogueSwordAmountUsed.getOrDefault(player, 0) + 1);
        rogueSwordLastTimeUsed.put(player, System.currentTimeMillis());

        new BukkitRunnable() {
            @Override
            public void run() {
                rogueSwordAmountUsed.put(player, rogueSwordAmountUsed.get(player) - 1);
            }
        }.runTaskLater(SkyblockDragons.plugin, 600L);
    }
}
