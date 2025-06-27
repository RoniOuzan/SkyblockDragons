package me.maxiiiiii.skyblockdragons.world.worlds.witherisland;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Cuboid;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class WitherIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("WitherIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -63.5, 72.5, 63.5);
    public static final Location WITHER_SPAWN = new Location(world, -63.5, 72.5, 63.5);
    public static final Location MIDDLE = new Location(world, -63.5, 72.5, 63.5);
    public static final int TIME_FOR_SOULSAND = 60;

    public final Map<UUID, Double> witherDamage = new HashMap<>();
    public final Map<UUID, Integer> amountOfPlacedEyes = new HashMap<>();

    public EntitySD wither = null;

    public WitherIsland(JavaPlugin plugin) {
        super(world, "Wither Island", Warp.WITHER_ISLAND, WorldType.COMBAT);
        clearWitherArea();
        buildAllSoulSand();
    }

    public EntityMaterial getRandomWither() {
        double random = Math.random() * 100;
        if (random >= 84)
            return EntityMaterial.get("PHANES_WITHER"); // old
        if (random >= 68)
            return EntityMaterial.get("ATHENA_WITHER"); // wise
        if (random >= 52)
            return EntityMaterial.get("DEMETER_WITHER"); // unstable
        if (random >= 36)
            return EntityMaterial.get("HERMES_WITHER"); // young
        if (random >= 4)
            return EntityMaterial.get("ARES_WITHER"); // strong
        if (random >= 1)
            return EntityMaterial.get("ERROR_WITHER");
        return EntityMaterial.get("TEST_WITHER");
    }

    public List<UUID> sortedWitherDamage() {
        return new ArrayList<>(sortedWitherDamageMap().keySet());
    }

    public Map<UUID, Double> sortedWitherDamageMap() {
        return Functions.sortByValue(witherDamage);
    }

    public static WitherIsland deserialize(Map<String, Object> args) {
        return WorldSD.WITHER_ISLAND;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onDamage(EntityDamageEvent e) {
        if (e.getVictim().material instanceof EntityWither && e.getAttacker() instanceof PlayerSD) {
            witherDamage.put(e.getAttacker().getUniqueId(), witherDamage.getOrDefault(e.getAttacker().getUniqueId(), 0d) + e.getFinalDamage());
        }
    }

    @EventHandler
    public void onWitherDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (wither != null && wither.getUniqueId() != null && entity.getUniqueId().equals(wither.getUniqueId())) {
            Functions.Wait(20, () -> sendWitherDeadMessage(e.getEntity().getKiller()));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getWorld() == world) {
            ItemStack item = event.getItemInHand();
            ItemMaterial material = Functions.getItemMaterial(item);
            if (material == Items.get("WITHER_SKULL")) {
                if (block.getY() == 73 && block.getZ() == 63) {
                    if (block.getX() <= -63 && block.getX() >= -65) {
                        onSkullPlace(event);
                    }
                }
            }
        }
    }

    public void onSkullPlace(BlockPlaceEvent event) {
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        UUID uuid = player.getUniqueId();
        event.setCancelled(false);
        Integer eyes = amountOfPlacedEyes.getOrDefault(uuid, 0);
        amountOfPlacedEyes.put(uuid, eyes + 1);
        for (Player loop_player : player.getWorld().getPlayers()) {
            loop_player.sendMessage(ChatColor.DARK_PURPLE + "☬ " + player.getDisplayName() + ChatColor.LIGHT_PURPLE + " placed a skull! (" + getAmountOfEyes() + "/3)");
        }

        if (getAmountOfEyes() >= 3) {
            this.spawnWither();
        }
    }

    public void spawnWither() {
        wither = new EntitySD(WITHER_SPAWN, getRandomWither());
        witherDamage.clear();
        clearWitherArea();
    }

    public void clearWitherArea() {
        Cuboid cuboid = new Cuboid(new Location(world, -65, 71, 63), new Location(world, -63, 73, 63));
        cuboid.forEach(block -> {
            block.setType(Material.AIR);
        });
    }

    public void buildAllSoulSand() {
        for (int i = 1; i <= 4; i++) {
            buildSoulSand(i);
        }
    }

    public void buildSoulSand(int num) {
        Location location = getSoulSand(num);
        location.getBlock().setType(Material.SOUL_SAND);
    }

    public Location getSoulSand(int num) {
        switch (num) {
            case 2:
                return new Location(world, -65, 72, 63);
            case 3:
                return new Location(world, -64, 72, 63);
            case 4:
                return new Location(world, -63, 72, 63);
            case 1:
            default:
                return new Location(world, -64, 71, 63);
        }
    }

    public int getAmountOfEyes() {
        return amountOfPlacedEyes.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void sendWitherDeadMessage(Player lastDamager) {
        EntityWither type = (EntityWither) wither.material;
        Map<UUID, Double> sortedWitherDamageMap = sortedWitherDamageMap();
        List<UUID> damageDealers = sortedWitherDamage();
        List<Killer> killers = new ArrayList<>();
        amountOfPlacedEyes.clear();
        for (UUID killer : damageDealers) {
            killers.add(new Killer(SkyblockDragons.getPlayer(killer), sortedWitherDamageMap.get(killer)));
        }
        Collections.reverse(killers);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("WitherIsland")).collect(Collectors.toList())) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
            player.sendMessage("                      " + type.color + "" + ChatColor.BOLD + Functions.setTitleCase(type.name) + ChatColor.GOLD + ChatColor.BOLD + " DOWN!");
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
                player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(sortedWitherDamageMap.get(player.getUniqueId())) + " " + ChatColor.GRAY + "(Position #" + (damageDealers.size() - damageDealers.indexOf(player.getUniqueId())) + ")");
                player.sendMessage(ChatColor.RESET + " ");
            }
            player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
        }
        wither = null;
        buildSoulSandSlowly();

    }

    public void buildSoulSandSlowly() {
        Functions.Loop(4, 20 * TIME_FOR_SOULSAND, amount -> {
            buildSoulSand(amount + 1);
        });
    }

    @EventHandler
    public void onWitherSkullHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        LivingEntity shooter = (LivingEntity) projectile.getShooter();
        try {
            if (shooter != null && wither.getUniqueId() != null && shooter.getUniqueId().equals(wither.getUniqueId()) && projectile instanceof WitherSkull) {
                EntityWither type = (EntityWither) wither.material;
                int radius = 3;
                double damage = type.damage;
                WitherSkull witherSkull = (WitherSkull) projectile;
                if (witherSkull.isCharged()) {
                    projectile.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, projectile.getLocation(), 1, 7, 7, 7);
                    radius = 7;
                    damage *= 4;
                }
                for (Entity entity : projectile.getNearbyEntities(radius, radius, radius)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        player.damage(damage, wither.entity);
                    }
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    public UUID getLeaderBoard(int index) {
        return sortedWitherDamage().get(index);
    }
}
