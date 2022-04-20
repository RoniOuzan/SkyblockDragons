package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Pigman_Dagger implements Listener {
    @EventHandler
    public void onClick(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("PIGMAN_DAGGER")) return;

        PlayerSD player = e.getPlayer();
        Location location = player.getEyeLocation().subtract(0, 0.2, 0).add(player.getLocation().getDirection());

        ParticleUtil particle = new ParticleUtil(Particle.FLAME, 0, 0, 0, 0.01f, 1);

        Functions.playCircle(location, 0.4, 20, particle);
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_PIG_HURT, 1f, 0.5f);

        ItemAbility ability = ((ToolMaterial) Items.get(item)).getAbilities().get(0);

        Functions.Wait(4L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(0.7)), 0.6, 40, particle));

        Functions.Wait(8L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(1.4)), 0.8, 60, particle));

        Functions.Wait(12L, () -> Functions.playCircle(location.clone().add(location.getDirection().multiply(2.1)), 1, 80, particle));

        List<Entity> damaged = new ArrayList<>();
        Functions.Loop(4, 4L, i -> {
            Location newLocation = location.clone().add(location.getDirection().multiply(i));
            location.getWorld().spawnParticle(Particle.LAVA, newLocation, 3, 0, 0, 0, 1);

            for (Entity entity : Functions.loopEntities(newLocation, 2)) {
                if (entity instanceof Creature && !damaged.contains(entity)) {
                    player.makeDamage(entity, Damage.DamageType.MAGIC, 1, ability.getAbilityDamage(), ability.getAbilityScaling());
                    damaged.add(entity);
                }
            }
        });
    }
}
