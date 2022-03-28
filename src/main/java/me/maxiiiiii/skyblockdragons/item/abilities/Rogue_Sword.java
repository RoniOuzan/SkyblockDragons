package me.maxiiiiii.skyblockdragons.item.abilities;

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
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("ROGUE_SWORD")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        PlayerSD stat = SkyblockDragons.players.get(player.getUniqueId());

        if (stat.manaCost(item, 0)) return;
        SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0);

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
