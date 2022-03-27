package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.util.Cooldown;
import me.maxiiiiii.skyblockdragons.util.interfaces.LoopTask;
import me.maxiiiiii.skyblockdragons.util.aifly;
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
    private final Cooldown cooldown = new Cooldown();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("FLOWER_OF_TRUTH")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();

        if (cooldown(player, cooldown, 1000, true)) return;
//        if (HypixelItems.players.get(player.getUniqueId()).manaCost(Integer.parseInt(Functions.getInt(HypixelItems.players.get(player.getUniqueId()).getIntelligence() / 10 + "")), player.getEquipment().getItemInMainHand(), 0)) return;

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

//        ItemStack item = e.getItem();
//
//        if (!Functions.getId(item).equals("FLOWER_OF_TRUTH")) return;
//        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
//
//
//        Player player = e.getPlayer();
//        if (Functions.cooldown(player, cooldown, 1000, true)) return;
//        if (!HypixelItems.players.get(player.getUniqueId()).manaCost(Integer.parseInt(Functions.getInt(HypixelItems.players.get(player.getUniqueId()).getIntelligence() / 10 + "")), player.getEquipment().getItemInMainHand(), 0)) return;
//
//        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 1f, 1f);
//
//        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
//        armorStand.setVisible(false);
//        armorStand.setCustomName("FLOWER_OF_TRUTH_" + player.getName());
//        armorStand.setCustomNameVisible(false);
//        armorStand.setGravity(false);
//        armorStand.setArms(true);
//        armorStand.setItemInHand(new ItemStack(Material.RED_ROSE));
//        armorStand.setBasePlate(false);
//
//        ArrayList<Entity> damagedEntities = new ArrayList<>();
//
//        Bukkit.getScheduler().runTaskTimer(HypixelItems.plugin, new Runnable() {
//            int i = 0;
//            int damaged = 0;
//            Location l = player.getLocation().clone();
//            boolean isStopped = false;
//            @Override
//            public void run() {
//                if (i >= 20) {
//                    armorStand.remove();
//                    return;
//                }
//                if (!isStopped) {
//                    armorStand.teleport(armorStand.getLocation().add(l.getDirection().multiply((i + 1) / 2)));
//                }
//                List<Entity> entities = armorStand.getNearbyEntities(5, 5, 5);
//                for (Entity entity : entities) {
//                    if (entity instanceof Creature) {
//                        if (!damagedEntities.contains(entity)) {
//                            damagedEntities.add(entity);
//                            new aifly(armorStand, entity.getLocation(), 200).runTaskTimer(HypixelItems.plugin, 0L, 2L);
//                            ((Creature) entity).damage(1d, player);
//                            damaged++;
//                            if (damaged >= 5) {
//                                isStopped = true;
//                                break;
//                            }
//                        }
//                    }
//                }
//                i++;
//            }
//        }, 0L, 2L);
    }
}
