package me.maxiiiiii.skyblockdragons.item.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wither_Cloak implements Listener {
    public static final Map<PlayerSD, CreeperVeil> activated = new HashMap<>();

    @EventHandler
    public void onPlayerUseAbility(PlayerUseAbilityEvent e) {
        Item item = e.getItem();

        if (item.getMaterial() != Items.get("WITHER_CLOAK")) return;

        PlayerSD player = e.getPlayer();

        if (activated.get(player) == null) {
            CreeperVeil creeperVeil = new CreeperVeil(player);

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Creeper Veil " + ChatColor.GREEN + "Activated!");
            activated.put(player, creeperVeil);
        } else {
            activated.get(player).kill();

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Creeper Veil " + ChatColor.RED + "De-Activated!");
            activated.put(player, null);
        }
    }

    public static class CreeperVeil {
        private final PlayerSD player;
        private final List<Creeper> creepers;

        private boolean isRunning;

        public CreeperVeil(PlayerSD player) {
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
