package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class WitherCloak extends SwordMaterial {
    public WitherCloak() {
        super("WITHER_CLOAK",
                Material.STONE_SWORD,
                ItemFamily.NULL,
                "Wither Cloak Sword",
                Rarity.EPIC,
                new Stats(190, 135, 0, 0, 0, 0, 0, 250, 0, 0),
                "",
                new CreeperVeil()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class CreeperVeil extends ItemAbility {
        public CreeperVeil() {
            super(AbilityAction.RIGHT_CLICK,
                    "Creeper Veil",
                    "Spawns a veil around you that grants you immunity from damage. Costs " + ChatColor.RED + "10% " + ChatColor.GRAY + "of your maximum mana each time you block a hit. Click again to de-activate."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new PlayerAbilityRunnable() {
                private CreeperVeils creeperVeil = null;

                @Override
                public void run(PlayerAbilityUsage e) {
                    PlayerSD player = e.getPlayer();

                    if (creeperVeil == null) {
                        creeperVeil = new CreeperVeils(player);

                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Creeper Veil " + ChatColor.GREEN + "Activated!");
                    } else {
                        creeperVeil.kill();
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Creeper Veil " + ChatColor.RED + "De-Activated!");
                        creeperVeil = null;
                    }
                }
            };
        }

        public static class CreeperVeils {
            private final PlayerSD player;
            private final List<Creeper> creepers;

            private boolean isRunning;

            public CreeperVeils(PlayerSD player) {
                this.player = player;
                this.creepers = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    Creeper creeper = player.getWorld().spawn(player.getLocation(), Creeper.class);
                    creeper.setPowered(true);
                    creeper.setGravity(false);
                    creeper.setAI(false);
                    creeper.setInvulnerable(true);
                    creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                    creeper.setCanPickupItems(false);
                    creepers.add(creeper);
                    SkyblockDragons.entitiesToKill.add(creeper);
                }

                this.start();
            }

            public void start() {
                this.isRunning = true;
                Functions.While(() -> this.isRunning, 1L, i -> {
                    int j = 0;
                    for (Creeper creeper : creepers) {
                        double angle = (i * 4) + (360d / creepers.size() * j);

                        double x = Math.sin(Math.toRadians(angle)) * 1.5;
                        double z = Math.cos(Math.toRadians(angle)) * 1.5;

                        Location newLocation = player.getLocation().add(x, 0.3, z);
                        newLocation.setDirection(new Vector(-x, 0, -z).normalize());

                        creeper.teleport(newLocation);

                        j++;
                    }
                });
            }

            public void kill() {
                this.isRunning = false;

                SkyblockDragons.entitiesToKill.removeAll(creepers);
                creepers.forEach(Entity::remove);
            }
        }
    }
}
