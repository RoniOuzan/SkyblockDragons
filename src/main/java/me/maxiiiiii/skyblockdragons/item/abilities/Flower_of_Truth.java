package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.util.interfaces.LoopTask;
import me.maxiiiiii.skyblockdragons.util.objects.aifly;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.util.Functions.Wait;

public class Flower_of_Truth implements Listener {
    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("FLOWER_OF_TRUTH")) return;

        Player player = e.getPlayer();

        if (SkyblockDragons.players.get(player.getUniqueId()).manaCost(Integer.parseInt(Functions.getInt(SkyblockDragons.players.get(player.getUniqueId()).getStats().getIntelligence().amount / 10 + "")), player.getEquipment().getItemInMainHand(), 0)) return;

        Location location = player.getLocation();

        player.playSound(location, Sound.ENTITY_GENERIC_EAT, 1f, 1f);

        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.setCustomName("FlowerOfTruth_" + player.getName());
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.setItemInHand(new ItemStack(Material.RED_ROSE));

        Loop(50, 1L, new LoopTask() {
            int damagedAmount = 0;
            boolean isStopped = false;
            final ArrayList<Entity> damagedEntities = new ArrayList<>();
            @Override
            public void task(int i) {
                if (stand.isDead()) return;
                if (stand.getLocation().add(0, 1, 0).getBlock().getType().isSolid()) {
                    stand.remove();
                    return;
                }

                if (!isStopped) {
                    Location newLocation = stand.getLocation().add(location.getDirection());
                    stand.teleport(newLocation);
                }

                for (Entity entity : loopEntities(location, 5)) {
                    if (damagedAmount >= 5) return;
                    if (entity instanceof Creature) {
                        isStopped = true;
                        if (!damagedEntities.contains(entity)) {
                            damagedEntities.add(entity);
                            damagedAmount++;
                            new aifly(stand, entity, 500).runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
                            Wait(10L, () -> ((Creature) entity).damage(1, player));
                        }
                    }
                }
            }
        }, (i) -> stand.remove());
    }
}
