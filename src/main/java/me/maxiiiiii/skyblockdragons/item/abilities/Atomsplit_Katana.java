package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Atomsplit_Katana implements Listener {
    public static HashMap<Player, Long> atomsplitAbility = new HashMap<>();

    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("ATOMSPLIT_KATANA")) return;

        PlayerSD player = e.getPlayer();

        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 1f, 1f);
        player.getEquipment().getItemInMainHand().setType(Material.GOLD_SWORD);
        atomsplitAbility.put(player, System.currentTimeMillis());

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
                        if (Functions.getId(player.getInventory().getItem(i)).equals("ATOMSPLIT_KATANA")) {
                            player.getInventory().getItem(i).setType(Material.DIAMOND_SWORD);
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            }
        }.runTaskLater(SkyblockDragons.plugin, 80L);
    }
}
