package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MagmaCloak extends SwordMaterial {
    public MagmaCloak() {
        super("MAGMA_CLOAK",
                Material.GOLD_SWORD,
                ItemFamily.NULL,
                "Magma Cloak Sword",
                Rarity.LEGENDARY,
                new Stats(245, 170, 0, 0, 0, 0, 0, 350, 0, 0),
                "",
                new MagmaVeil()
        );
    }

    public static class MagmaVeil extends ItemAbility {
        public MagmaVeil() {
            super(AbilityAction.RIGHT_CLICK,
                    "Magma Veil",
                    "Spawns a veil around you that grants you immunity from damage. Costs " + ChatColor.RED + "10% " + ChatColor.GRAY + "of your maximum mana each time you block a hit. Click again to de-activate."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new PlayerAbilityRunnable() {
                private MagmaVeils veil = null;

                @Override
                public void run(PlayerAbilityUsage e) {
                    PlayerSD player = e.getPlayer();

                    if (veil == null) {
                        veil = new MagmaVeils(player);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Magma Veil " + ChatColor.GREEN + "Activated!");
                    } else {
                        veil.kill();

                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Magma Veil " + ChatColor.RED + "De-Activated!");
                        veil = null;
                    }
                }
            };
        }

        public static class MagmaVeils {
            private final PlayerSD player;
            private final List<ArmorStand> stands;

            private boolean isRunning;

            public MagmaVeils(PlayerSD player) {
                this.player = player;
                this.stands = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    ArmorStand stand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
                    stand.setGravity(false);
                    stand.setInvulnerable(true);
                    stand.setVisible(false);
                    stand.setCanPickupItems(false);
                    stand.setHelmet(Functions.applySkull(Functions.createItem(Material.SKULL_ITEM, 3, ""), "5452dd81-4fc0-470f-a25d-b9c8a62ddfbc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTlhNWExZTY5YjRmODEwNTYyNTc1MmJjZWUyNTM0MDY2NGIwODlmYTFiMmY1MjdmYTkxNDNkOTA2NmE3YWFkMiJ9fX0="));
                    stands.add(stand);
                    SkyblockDragons.entitiesToKill.add(stand);
                }

                this.start();
            }

            public void start() {
                this.isRunning = true;
                Functions.While(() -> this.isRunning, 1L, i -> {
                    int j = 0;
                    for (ArmorStand stand : stands) {
                        double angle = (i * 4) + (360d / stands.size() * j);

                        double x = Math.sin(Math.toRadians(angle)) * 2.5;
                        double z = Math.cos(Math.toRadians(angle)) * 2.5;

                        Location newLocation = player.getLocation().add(x, -0.3, z);
                        newLocation.setDirection(new Vector(-x, 0, -z).normalize());

                        stand.teleport(newLocation);

                        double xP = Math.sin(Math.toRadians(angle - 20)) * 2.5;
                        double zP = Math.cos(Math.toRadians(angle - 20)) * 2.5;

                        player.getWorld().spawnParticle(Particle.DRIP_LAVA, player.getLocation().getX() + xP, player.getLocation().getY() + 1.2, player.getLocation().getZ() + zP, 1, 0, 0, 0, 0);

                        j++;
                    }
                });
            }

            public void kill() {
                this.isRunning = false;
                stands.forEach(SkyblockDragons.entitiesToKill::remove);
                stands.forEach(Entity::remove);
            }
        }
    }
}
