package me.maxiiiiii.skyblockdragons.world.worlds.bearisland;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.ProjectileEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.events.BearKillEvent;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.listeners.BearKillListener;
import me.maxiiiiii.skyblockdragons.world.worlds.bearisland.listeners.PlayerPlaceFurListener;
import me.maxiiiiii.skyblockdragons.world.worlds.end.events.PlayerPlaceEyeEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class BearIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("BearIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -62.500, 92, 197.500);
    public static final Location BEAR_SPAWN = new Location(world, -62.500, 92, 212.500);
    public static final Location MIDDLE = new Location(world, -62.500, 92, 212.500);
    public static final Map<PlayerSD, Double> bearDamage = new HashMap<>();
    public static EntitySD bear = null;
    public static long time = 0;

    public BearIsland(JavaPlugin plugin) {
        super(world, "Bear Island", Warp.BEAR_ISLAND, WorldType.COMBAT);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerPlaceFurListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BearKillListener(), plugin);
    }

    public static void resetFurs() {
        for (Block block : Functions.loopBlocksHorizontally(MIDDLE, 10)) {
            if (block.getType() == Material.ENDER_PORTAL_FRAME && block.getData() > 3) {
                block.setData((byte) (block.getData() - 4));
            }
        }
    }

    public static EntityMaterial getRandomBear() {
        double random = Math.random() * 100;
        if (random >= 84)
            return EntityMaterial.get("POLAR_BEAR");
        if (random >= 68)
            return EntityMaterial.get("PANDA_BEAR");
        if (random >= 52)
            return EntityMaterial.get("KOALA_BEAR");
        if (random >= 36)
            return EntityMaterial.get("GRIZZLY_BEAR");
        if (random >= 1)
            return EntityMaterial.get("RED_PANDA_BEAR");
        return EntityMaterial.get("RED_PANDA_BEAR");
    }

    public static void spawnBear() {
        bear = new EntitySD(BEAR_SPAWN, getRandomBear());

        Functions.While(() -> !bear.isDead(), 5L, i -> {
            for (Entity entity : bear.getNearbyEntities(1)) {
                if (entity instanceof Arrow && ((Arrow) entity).getShooter() != null) {
                    entity.remove();
                    PlayerSD player = SkyblockDragons.getPlayer((Player) ((Arrow) entity).getShooter());
                    player.damage(new ProjectileEntityDamageEntity(player, bear, (Projectile) entity));
                }
            }
        });
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

        if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME && e.getClickedBlock().getData() < 4) {
            ItemStack item = e.getItem();
            String id = Functions.getId(item);
            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());
            if (id.equals("GRIZZLY_BEAR_FUR") || id.equals("POLAR_BEAR_FUR") || id.equals("PANDA_BEAR_FUR") || id.equals("KOALA_BEAR_FUR") || id.equals("ADMIN_BEAR_FUR"))  {
                if (e.getClickedBlock().getLocation().distance(MIDDLE) <= 5) {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE && !id.equals("ADMIN_BEAR_FUR")) {
                        e.getPlayer().getEquipment().setItemInMainHand(null);
                        player.sendMessage("debug1");
                    }
                    e.setCancelled(true);
                    PlayerPlaceEyeEvent event = new PlayerPlaceEyeEvent(player, e.getClickedBlock(), e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(event);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof IronGolem) {
            IronGolem bear = (IronGolem) e.getEntity();
            if (bear.getWorld().getName().equals("BearIsland")) {
                long diff = System.currentTimeMillis() - time;
                if (diff <= 2000){
                    SkyblockDragons.logger.warning("Bear died too quick to count! " + diff);
                    return;
                }
                time = System.currentTimeMillis();
                Functions.Wait(1L, () -> {
                    BearKillEvent event = new BearKillEvent(EntitySD.get(e.getEntity()), bearDamage);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                });
            }
        }
    }
}
