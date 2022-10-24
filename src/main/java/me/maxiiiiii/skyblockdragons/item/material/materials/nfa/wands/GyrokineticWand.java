package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.wands;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GyrokineticWand extends ToolMaterial {
    public GyrokineticWand() {
        super("GYROKINETIC_WAND",
                Material.BLAZE_ROD,
                ItemFamily.GYROKINETIC_WAND,
                "Gyrokinetic Wand",
                ItemType.WAND,
                Rarity.EPIC,
                "",
                new GravityStorm(),
                new CellsAlignment()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class CellsAlignment extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public CellsAlignment() {
            super(AbilityAction.RIGHT_CLICK,
                    "Cells Alignment",
                    "Apply " + ChatColor.GREEN + "Aligned " + ChatColor.GRAY + "to 4 nearby players and yourself for " + ChatColor.GREEN + "6 seconds" + ChatColor.GRAY + "."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 220;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 10;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }
    }

    public static class GravityStorm extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown, Listener {
        public GravityStorm() {
            super(AbilityAction.LEFT_CLICK,
                    "Gravity Storm",
                    "Create a large " + ChatColor.DARK_PURPLE + "rift " + ChatColor.GRAY + "at aimed location, pulling all mobs together."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 1200;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 5;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Block block = player.getTargetBlock(null, 128);
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        if (i >= 60) {
                            Bukkit.getScheduler().runTaskLater(SkyblockDragons.plugin, () -> {
                                for (Entity entity : Functions.loopEntities(block.getLocation(), 4)) {
                                    entity.setVelocity(new Vector(0, 2, 0));
                                }
                            }, 1L);
                            cancel();
                        }
                        int size;
                        int size2;
                        int size3;
                        if (i <= 12) {
                            size = 10;
                            size2 = 9;
                            size3 = 8;
                        } else if (i <= 24) {
                            size = 8;
                            size2 = 7;
                            size3 = 6;
                        } else if (i <= 36) {
                            size = 6;
                            size2 = 5;
                            size3 = 4;
                        } else if (i <= 48) {
                            size = 4;
                            size2 = 3;
                            size3 = 2;
                        } else {
                            size = 3;
                            size2 = 2;
                            size3 = 1;
                        }
                        for (Block value2 : Functions.loopBlocksScopeHor(block.getLocation(), size, size2, size3)) {
                            Block value = value2;
                            for (int i1 = 0; i1 < 10; i1++) {
                                if (value2.getLocation().clone().add(0, i1 + 1, 0).getBlock().getType() == Material.AIR) {
                                    if (value2.getLocation().add(0, i1, 0).getBlock().getType() != Material.AIR) {
                                        value = value2.getLocation().add(0, i1, 0).getBlock();
                                        break;
                                    }
                                }
                            }
                            if (value.getLocation().getY() == value2.getLocation().getY()) {
                                for (int i1 = 0; i1 < 10; i1++) {
                                    if (value2.getLocation().clone().add(0, -i1, 0).getBlock().getType() != Material.AIR) {
                                        value = value2.getLocation().clone().add(0, -i1, 0).getBlock();
                                        break;
                                    }
                                }
                            }
                            if (Functions.randomInt(0, 100) <= 20) {
                                value.getWorld().spawnParticle(Particle.SPELL_WITCH, value.getLocation().clone().add(0, 0.5, 0), 1);
                                int n = Functions.randomInt(1, 6);
                                double a = Functions.randomDouble(2, 3);
                                a /= 10;
                                MaterialData materialData;
                                if (n <= 4) {
                                    materialData = new MaterialData(Material.OBSIDIAN);
                                } else if (n == 5) {
                                    materialData = new MaterialData(Material.WOOL);
                                    materialData.setData(Byte.parseByte("2"));
                                } else {
                                    materialData = new MaterialData(Material.WOOL);
                                    materialData.setData(Byte.parseByte("10"));
                                }
                                FallingBlock fb = value.getWorld().spawnFallingBlock(value.getLocation().clone().add(0, a, 0), materialData);
                                fb.setDropItem(false);
                                fb.addScoreboardTag("GyrokineticWand");
                            }
                        }
                        for (Entity entity : Functions.loopEntitiesScope(block.getLocation(), size, size2, size3)) {
                            if (entity instanceof Creature) {
                                entity.setVelocity(new Vector((block.getX() - entity.getLocation().getX()) / 5, (block.getY() - entity.getLocation().getY()) / 10 - 0.1, (block.getZ() - entity.getLocation().getZ()) / 5));
                            }
                        }
                        i++;
                    }
                }.runTaskTimer(SkyblockDragons.plugin, 0L, 1L);
            };
        }

        @EventHandler
        public void onLand(EntityChangeBlockEvent e) {
            if (e.getEntity().getScoreboardTags().contains("GyrokineticWand")) {
                e.setCancelled(true);
            }
        }
    }
}
