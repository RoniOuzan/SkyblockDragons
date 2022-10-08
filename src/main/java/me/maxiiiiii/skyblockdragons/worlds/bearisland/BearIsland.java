package me.maxiiiiii.skyblockdragons.worlds.bearisland;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.EntityWither;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.worlds.bearisland.events.PlayerPlaceFurEvent;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class BearIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("BearIsland");
    public static final Location MIDDLE_OF_LOOT = new Location(world, -59.486, 90.00000, 197.481);
    public static final Location BEAR_SPAWN = new Location(world, -62.500, 92.00000, 212.500);
    public static final Location MIDDLE = new Location(world, -62.500, 92.00000, 212.500);
    public static EntitySD bear = null;

    public static Map<UUID, Double> bearDamage = new HashMap<>();

    public BearIsland(JavaPlugin plugin) {
        super(world, "Bear Island", Warp.BEAR_ISLAND, WorldType.COMBAT);
    }

    public static EntityMaterial getRandomBear() {
        double random = Math.random() * 100;
        if (random >= 84)
            return EntityMaterial.get("GRIZZLY_BEAR");
        if (random >= 68)
            return EntityMaterial.get("POLAR_BEAR");
        if (random >= 52)
            return EntityMaterial.get("PANDA_BEAR");
        if (random >= 36)
            return EntityMaterial.get("KOALA_BEAR");
        if (random >= 1)
            return EntityMaterial.get("RED_PANDA_BEAR");
        return EntityMaterial.get("RED_PANDA_BEAR");
    }

    public static void spawnBear() {
        bear = new EntitySD(BEAR_SPAWN, getRandomBear());
        NBTEntity nbtEntity = new NBTEntity(bear.entity);
        nbtEntity.setInteger("BearPhase", 1);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BearIsland")).collect(Collectors.toList())) {
            if (player.getLocation().distance(MIDDLE) <= 50) {
                player.setVelocity(new Vector(
                        BEAR_SPAWN.getX() - player.getLocation().getX(),
                        0,
                        BEAR_SPAWN.getZ() - player.getLocation().getZ()
                ).normalize().multiply(2).setY(3));
            }
        }
    }
    @EventHandler
    public void onClickBlock(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;

        if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME && e.getClickedBlock().getData() < 4) {
            ItemStack item = e.getItem();
            if (Functions.getId(item).equals("GRIZZLY_BEAR_FUR")) {
                if (e.getClickedBlock().getLocation().distance(MIDDLE) <= 5) {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
                        e.getPlayer().getEquipment().setItemInMainHand(null);
                    e.setCancelled(true);
                    PlayerPlaceFurEvent event = new PlayerPlaceFurEvent(SkyblockDragons.getPlayer(e.getPlayer()), e.getClickedBlock(), e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(event);
                }
            }
        }
    }
    @EventHandler
    public void onBearDeath(EntityDeathEvent e){
        try {
            LivingEntity entity = e.getEntity();
            if (bear.getUniqueId() != null && entity.getUniqueId().equals(bear.getUniqueId())){
                Functions.Wait(20, () -> sendBearDeadMessage(e.getEntity().getKiller()));
            }
        } catch (NullPointerException ignored){}
    }
    public static void sendBearDeadMessage(Player lastDamager){
        EntityWither type = (EntityWither) bear.type;
        Map<UUID, Double> sortedBearDamageMap = sortedBearDamageMap();
        List<UUID> damageDealers = sortedBearDamage();
        List<Killer> killers = new ArrayList<>();
        for (UUID killer : damageDealers) {
            killers.add(new Killer(SkyblockDragons.getPlayer(killer), sortedBearDamageMap.get(killer)));
        }
        Collections.reverse(killers);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BearIsland")).collect(Collectors.toList())) {
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

            if (sortedBearDamageMap.containsKey(player.getUniqueId())) {
                player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(sortedBearDamageMap.get(player.getUniqueId())) + " " + ChatColor.GRAY + "(Position #" + (damageDealers.indexOf(player.getUniqueId()) + 1) + ")");
                player.sendMessage(ChatColor.RESET + " ");
            }
            player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
        }
        bear = null;

    }
    public static List<UUID> sortedBearDamage(){
        return new ArrayList<>(sortedBearDamageMap().keySet());
    }

    @NotNull
    public static Map<UUID, Double> sortedBearDamageMap() {
        return Functions.sortByValue(bearDamage);
    }
    public static PlayerSD getBearTarget(){
        List<UUID> sortedWitherDamage = sortedBearDamage();
        for (UUID uuid : sortedWitherDamage) {
            PlayerSD target = SkyblockDragons.getPlayer(uuid);
            if (target != null && target.isOnline()) {
                Location location = bear.entity.getLocation();
                if (location.getWorld().equals(target.getWorld()) && location.distance(target.getLocation()) <= 70) {
                    return target;
                }
            }
        }
        return null;
    }
        }