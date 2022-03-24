package me.maxiiiiii.skyblockdragons.abilities;

import me.maxiiiiii.skyblockdragons.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;

public class Grappling_Hook implements Listener {
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.FAILED_ATTEMPT && e.getState() != PlayerFishEvent.State.IN_GROUND) return;

        ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

        if (!getId(item).equals("GRAPPLING_HOOK")) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 2000, true)) return;

        Location l1 = player.getLocation();
        Location l2 = e.getHook().getLocation();

        Vector v = new Vector(
                (l2.getX() - l1.getX()) / 2,
                (l2.getY() - l1.getY()) / 8 + 0.5,
                (l2.getZ() - l1.getZ()) / 2);

        player.setVelocity(v);
    }
}
