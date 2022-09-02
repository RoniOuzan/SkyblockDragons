package me.maxiiiiii.skyblockdragons.worlds.witherisland;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class WitherIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("WitherIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location WITHER_SPAWN = new Location(world, -63.500, 72.50000, 63.500);
    public static final Location MIDDLE = new Location(world, -63.500, 72.50000, 63.500);
    public static EntityWither wither = null;

    public static final Map<UUID, Double> witherDamage = new HashMap<>();

    public WitherIsland(JavaPlugin plugin) {
        super(world, "Wither Island", new Location(world, -109.500,65.00000, 139.500), WorldType.COMBAT, WorldType.MINING);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e) {
        if (e.getLocation().getWorld() != world) return;
        if (e.getEntity() instanceof Wither) {
            Wither Wither = (Wither) e.getEntity();

        }
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        if (wither.uuid != null && entity.getUniqueId().equals(wither.uuid)){
//            EntitySD entitySD = EntitySD.get(entity);
            Functions.Wait(20, () -> sendWitherDeadMessage(e.getEntity().getKiller()));

        }
    }

    public static void sendWitherDeadMessage(Player lastDamager){
        Map<UUID, Double> sortedWitherDamageMap = sortedWitherDamageMap();
        List<UUID> damageDealers = sortedWitherDamage();
        List<Killer> killers = new ArrayList<>();
        for (UUID killer : damageDealers) {
            killers.add(new Killer(SkyblockDragons.getPlayer(killer), sortedWitherDamageMap.get(killer)));
        }
        Collections.reverse(killers);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("WitherIsland")).collect(Collectors.toList())) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
            player.sendMessage("                      " + wither.color + "" + ChatColor.BOLD + Functions.setTitleCase(wither.name()) + " Dragon");
            player.sendMessage(ChatColor.RESET + " ");
            if (lastDamager != null)
                player.sendMessage("           " + ChatColor.WHITE + lastDamager.getDisplayName() + ChatColor.GRAY + " dealt the final hit!");
            player.sendMessage(ChatColor.RESET + " ");
            if (killers.size() > 0)
                player.sendMessage("           " + ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Damager " + ChatColor.GRAY + "- " + killers.get(0).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(0).damage));
            if (killers.size() > 1)
                player.sendMessage("         " + ChatColor.GOLD + "" + ChatColor.BOLD + "2nd Damager " + ChatColor.GRAY + "- " + killers.get(1).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(1).damage));
            if (killers.size() > 2)
                player.sendMessage("       " + ChatColor.RED + "" + ChatColor.BOLD + "3rd Damager " + ChatColor.GRAY + "- " + killers.get(2).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(2).damage));
            player.sendMessage(ChatColor.RESET + " ");

            if (sortedWitherDamageMap.containsKey(player.getUniqueId())) {
                player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(sortedWitherDamageMap.get(player.getUniqueId())) + " " + ChatColor.GRAY + "(Position #" + (damageDealers.indexOf(player.getUniqueId()) + 1) + ")");
                player.sendMessage(ChatColor.RESET + " ");
            }
            player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
        }
    }

    @EventHandler
    public void onWitherSkullHit(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        LivingEntity shooter = (LivingEntity) projectile.getShooter();
        if (wither.uuid != null && shooter.getUniqueId().equals(wither.uuid) && projectile instanceof WitherSkull){
            int radius = 3;
            int damage = 500;
            WitherSkull witherSkull = (WitherSkull) projectile;
            if (witherSkull.isCharged()){
                projectile.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, projectile.getLocation(), 1, 7, 7, 7);
                radius = 7;
                damage = 2000;
            }
            for (Entity entity : projectile.getNearbyEntities(radius, radius, radius)) {
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    player.damage(damage, wither.entitySD.entity);
                    player.sendMessage(String.format("Wither hit you %s damage VEL: %s", damage, witherSkull.getDirection()));
                }
            }
        }
    }

    public static List<UUID> sortedWitherDamage(){
        return new ArrayList<>(sortedWitherDamageMap().keySet());
    }

    @NotNull
    public static Map<UUID, Double> sortedWitherDamageMap() {
        return Functions.sortByValue(witherDamage);
    }

    public static PlayerSD getWitherTarget(){
        List<UUID> sortedWitherDamage = sortedWitherDamage();
        for (UUID uuid : sortedWitherDamage) {
            PlayerSD target = SkyblockDragons.getPlayer(uuid);
            if (target != null && target.isOnline()) {
                Location location = wither.entitySD.entity.getLocation();
                if (location.getWorld().equals(target.getWorld()) && location.distance(target.getLocation()) <= 70) {
                    return target;
                }
            }
        }
        return null;
    }

    public UUID getLeaderBoard(int index){
        return sortedWitherDamage().get(index);
    }

}
