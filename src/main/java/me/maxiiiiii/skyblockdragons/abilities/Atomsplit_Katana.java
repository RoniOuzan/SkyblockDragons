package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static me.maxiiiiii.skyblockdragons.Functions.*;

public class Atomsplit_Katana implements Listener {
    private final Cooldown cooldown = new Cooldown();
    public static long atomsplitAbility = 0;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("ATOMSPLIT_KATANA")) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

        Player player = e.getPlayer();

        if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
        if (cooldown(player, cooldown, 4000, true)) return;

        SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0);
        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1f, 1f);
        item.setType(Material.GOLD_SWORD);
        atomsplitAbility = System.currentTimeMillis();

        new BukkitRunnable() {
            @Override
            public void run() {
                SkyblockDragons.players.get(player.getUniqueId()).applyStats(false);
            }
        }.runTaskLater(SkyblockDragons.plugin, 1L);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 36; i++) {
                    try {
                        if (player.getInventory().getItem(i).isSimilar(item)) {
                            player.getInventory().getItem(i).setType(Material.DIAMOND_SWORD);
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            }
        }.runTaskLater(SkyblockDragons.plugin, 80L);
    }
}
