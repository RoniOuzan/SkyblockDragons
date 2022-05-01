package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Magma_Cloak implements Listener {
    public static final Map<PlayerSD, MagmaVeil> activated = new HashMap<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("MAGMA_CLOAK")) return;

        PlayerSD player = e.getPlayer();

        if (activated.get(player) == null) {
            MagmaVeil magmaVeil = new MagmaVeil(player);

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Magma Veil " + ChatColor.GREEN + "Activated!");
            activated.put(player, magmaVeil);
        } else {
            activated.get(player).kill();

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Magma Veil " + ChatColor.RED + "De-Activated!");
            activated.put(player, null);
        }
    }

    public static class MagmaVeil {
        private final PlayerSD player;
        private final List<ArmorStand> stands;

        private boolean isRunning;

        public MagmaVeil(PlayerSD player) {
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
