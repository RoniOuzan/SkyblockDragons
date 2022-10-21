package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Ice_Spray_Wand implements Listener {
    private final Cooldown<Player> cooldown = new Cooldown<>();

//    @EventHandler
//    public void onClick(PlayerInteractEvent e) {
//        ItemStack item = e.getItem();
//
//        if (!getId(item).equals("ICE_SPRAY_WAND")) return;
//        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
//
//        Player player = e.getPlayer();
//
////        if (cooldown(player, cooldown, 5000, true)) return;
////        if (HypixelItems.players.get(player.getUniqueId()).manaCost(item, 0)) return;
//
//        Location location = player.getEyeLocation().add(player.getLocation().getDirection());
//
//        particleCircle(location, Particle.CLOUD, 1);
//    }
}
