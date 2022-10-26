package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCostPercentage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.interfaces.LoopTask;
import me.maxiiiiii.skyblockdragons.util.objects.AIFly;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class FlowerOfTruth extends SwordMaterial {
    public FlowerOfTruth() {
        super("FLOWER_OF_TRUTH",
                Material.RED_ROSE,
                ItemFamily.FLOWER_OF_TRUTH,
                "Flower of Truth",
                Rarity.LEGENDARY,
                new Stats(100, 360, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new HeatSeekingRose()
        );
    }

    public static class HeatSeekingRose extends ItemAbility implements ItemAbilityManaCostPercentage, ItemAbilityCooldown {
        public HeatSeekingRose() {
            super(AbilityAction.RIGHT_CLICK,
                    "Heat-Seeking Rose",
                    "Shoots a rose that ricochets between enemies, damaging up to " + ChatColor.GREEN + "3 " + ChatColor.GRAY + "of your foes! Damage multiplies as more enemies are hit."
            );
        }

        @Override
        public double getBaseManaCostPercentage(PlayerSD player) {
            return 10;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 1;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                Player player = e.getPlayer();
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
                                    new AIFly(stand, entity, 500).runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
                                    Wait(10L, () -> ((Creature) entity).damage(1, player));
                                }
                            }
                        }
                    }
                }, (i) -> stand.remove());
            };
        }
    }
}
