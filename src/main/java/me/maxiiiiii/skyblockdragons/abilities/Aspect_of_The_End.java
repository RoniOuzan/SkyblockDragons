package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Aspect_of_The_End implements Listener{
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Functions.getId(item).equals("ASPECT_OF_THE_END")) {
                Player player = e.getPlayer();

                if (Functions.cooldown(player, cooldown, 50, false)) return;

                if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(player.getEquipment().getItemInMainHand(), 0)) return;
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                Functions.teleportForward(player, 8);
            }
        }
    }
}
