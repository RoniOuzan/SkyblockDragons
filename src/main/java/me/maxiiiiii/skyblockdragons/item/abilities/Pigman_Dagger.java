package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.util.objects.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Pigman_Dagger implements Listener {
    private static final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!Functions.getId(item).equals("PIGMAN_DAGGER")) return;
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) return;

        if (Functions.cooldown(e.getPlayer(), cooldown, 1000, true)) return;

        PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
        Location location = player.getEyeLocation().subtract(0, 0.2, 0).add(player.getLocation().getDirection());

        ParticleUtil particle = new ParticleUtil(Particle.FLAME, 0, 0, 0, 0.01f, 1);

        Functions.playCircle(location, 0.4, 20, particle);
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_PIG_HURT, 1f, 0.5f);

        Functions.Wait(4L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(0.7)), 0.6, 40, particle));

        Functions.Wait(8L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(1.4)), 0.8, 60, particle));

        Functions.Wait(12L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(2.1)), 1, 80, particle));

        Functions.Loop(4, 4L, i -> {
            Location newLocation = location.clone().add(location.getDirection().multiply(i));
            location.getWorld().spawnParticle(Particle.LAVA, newLocation, 3, 0, 0, 0, 1);
        });
    }
}
