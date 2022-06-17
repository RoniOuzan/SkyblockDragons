package me.maxiiiiii.skyblockdragons.worlds.end;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.FlyToLocation;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.worlds.end.events.DragonKillEvent;
import me.maxiiiiii.skyblockdragons.worlds.end.events.PlayerPlaceEyeEvent;
import me.maxiiiiii.skyblockdragons.worlds.end.listeners.DragonKillListener;
import me.maxiiiiii.skyblockdragons.worlds.end.listeners.PlayerPlaceEyeListener;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class TheEnd extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("TheEnd");
    public static final Location MIDDLE_OF_LOOT = new Location(world, 1, 61, 15);
    public static final Location DRAGON_SPAWN = new Location(world, 0, 80, 0);
    public static final Location MIDDLE = new Location(world, 0, 64, 0);
    public static EntitySD dragon = null;

    public static final Map<PlayerSD, Double> dragonDamage = new HashMap<>();

    public TheEnd(JavaPlugin plugin) {
        super(world, "The End", WorldType.COMBAT, WorldType.MINING);
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerPlaceEyeListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new DragonKillListener(), plugin);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) e.getEntity();
            dragon.setPhase(EnderDragon.Phase.HOVER);
            Functions.While(() -> !dragon.isDead(), 2L, i -> {
                for (Entity entity : dragon.getNearbyEntities(1, 1, 1)) {
                    if (entity instanceof Arrow && ((Arrow) entity).getShooter() != null) {
                        entity.remove();
                        SkyblockDragons.getPlayer((Player) ((Arrow) entity).getShooter()).makeDamage(dragon, Damage.DamageType.PROJECTILE, 1);
                    }
                }
            });
            Functions.While(() -> !dragon.isDead(), 80L, i -> {
                if (i % 6 == 4 || i % 6 == 5) {
                    return;
                }
                double x = Functions.randomDouble(-40, 40);
                double y = Functions.randomDouble(70, 120);
                double z = Functions.randomDouble(-40, 40);
                Location location = new Location(getWorld(), x, y, z);
                new FlyToLocation(dragon, location, 80, 5, true);
            });
        }
    }

    @EventHandler
    public void onClickBlock(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;

        if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME && e.getClickedBlock().getData() < 4) {
            ItemStack item = e.getItem();
            if (Functions.getId(item).equals("SUMMONING_EYE")) {
                if (e.getClickedBlock().getLocation().distance(MIDDLE) <= 5) {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
                        e.getPlayer().getEquipment().setItemInMainHand(null);
                    e.setCancelled(true);
                    PlayerPlaceEyeEvent event = new PlayerPlaceEyeEvent(SkyblockDragons.getPlayer(e.getPlayer()), e.getClickedBlock(), e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(event);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEntityEvent e) {
        if (e.getVictim().type instanceof EntityDragon) {
            if (e.getAttacker() instanceof PlayerSD)
                dragonDamage.put((PlayerSD) e.getAttacker(), dragonDamage.getOrDefault((PlayerSD) e.getAttacker(), 0d) + e.getDamage());
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) e.getEntity();
            if (dragon.getWorld().getName().equals("TheEnd")) {
                Functions.Wait(1L, () -> {
                    DragonKillEvent event = new DragonKillEvent(EntitySD.get(e.getEntity()), dragonDamage);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                });
            }
        }
    }

    @EventHandler
    public void onDragonChangePhase(EnderDragonChangePhaseEvent e) {
        if (e.getNewPhase() == EnderDragon.Phase.DYING)
            e.setCancelled(true);
    }

    public static void resetEyes() {
        for (Block block : Functions.loopBlocksHorizontally(MIDDLE, 10)) {
            if (block.getType() == Material.ENDER_PORTAL_FRAME && block.getData() > 3) {
                block.setData((byte) (block.getData() - 4));
            }
        }
    }

    public static EntityMaterial getRandomDragon() {
        double random = Math.random() * 100;
        if (random >= 84)
            return EntityMaterial.get("OLD_DRAGON");
        if (random >= 68)
            return EntityMaterial.get("PROTECTOR_DRAGON");
        if (random >= 52)
            return EntityMaterial.get("WISE_DRAGON");
        if (random >= 36)
            return EntityMaterial.get("UNSTABLE_DRAGON");
        if (random >= 20)
            return EntityMaterial.get("YOUNG_DRAGON");
        if (random >= 4)
            return EntityMaterial.get("STRONG_DRAGON");
        return EntityMaterial.get("SUPERIOR_DRAGON");
    }
}
