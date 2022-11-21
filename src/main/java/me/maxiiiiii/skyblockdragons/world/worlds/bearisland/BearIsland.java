package me.maxiiiiii.skyblockdragons.world.worlds.bearisland;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.types.bearisland.EntityBear;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class BearIsland extends WorldSD implements Listener {
    public static final World world = Bukkit.getWorld("BearIsland");
    public static final Location BEAR_SPAWN = new Location(world, -62.599, 92.06250, 212.430);
    public static final int TIME_FOR_BEAR_SOUL = 60;
    public static EntitySD iron_golem = null;

    public static final Map<UUID, Double> bearDamage = new HashMap<>();
    public static final Map<UUID, Integer> amountOfPlacedEyes = new HashMap<>();

    public BearIsland(JavaPlugin plugin) {
        super(world, "Bear Island", Warp.BEAR_ISLAND, WorldType.COMBAT);
        clearBearArea();
        buildAllBearSoul();
    }

    @EventHandler
    public void onBearDeath(EntityDeathEvent e) {
        try {
            LivingEntity entity = e.getEntity();
            if (iron_golem.getUniqueId() != null && !iron_golem.getUniqueId().equals(iron_golem.getUniqueId())) {
                Functions.Wait(20, () -> sendBearDeadMessage(e.getEntity().getKiller()));
            }
        } catch (NullPointerException ignored) {
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        if (block.getWorld() == world) {
            ItemStack item = event.getItemInHand();
            ItemMaterial material = Functions.getItemMaterial(item);
            if (material == Items.get("BEAR_EYE")) {
                if (block.getY() == 73 && block.getZ() == 63) {
                    if (block.getX() <= -63 && block.getX() >= -65) {
                        onBearEyePlace(event);
                    }
                }
            }
        }
    }

    public static EntityMaterial getRandomBear() {
        double random = Math.random() * 100;
        if (random >= 68)
            return EntityMaterial.get("KOALA_BEAR");
        if (random >= 52)
            return EntityMaterial.get("POLAR_BEAR");
        if (random >= 36)
            return EntityMaterial.get("PANDA_BEAR");
        if (random >= 4)
            return EntityMaterial.get("GRIZZLY_BEAR");
        if (random >= 1)
            return EntityMaterial.get("RED_PANDA_BEAR");
        return EntityMaterial.get("GRIZZLY_BEAR");
    }

    public int getAmountOfEyes() {
        return amountOfPlacedEyes.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void onBearEyePlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        PlayerSD player = SkyblockDragons.getPlayer(event.getPlayer());
        UUID uuid = player.getUniqueId();
        event.setCancelled(false);
        Integer eyes = amountOfPlacedEyes.getOrDefault(uuid, 0);
        amountOfPlacedEyes.put(uuid, eyes + 1);
        for (Player loop_player : player.getWorld().getPlayers()) {
            loop_player.sendMessage(ChatColor.DARK_PURPLE + "☬ " + player.getDisplayName() + ChatColor.LIGHT_PURPLE + " placed a eye-! (" + getAmountOfEyes() + "/3)");
        }

        if (getAmountOfEyes() >= 3) {
            this.spawnBear();
        }
    }

    public void spawnBear() {
        iron_golem = new EntitySD(BEAR_SPAWN, getRandomBear());
        clearBearArea();
    }

    public void clearBearArea() {
        Cuboid cuboid = new Cuboid(new Location(world, -65, 71, 63), new Location(world, -63, 73, 63));
        cuboid.forEach(block -> {
            block.setType(Material.AIR);
        });
    }

    public void buildAllBearSoul() {
        for (int i = 1; i <= 4; i++) {
            buildConcrete(i);
        }
    }

    public void buildConcrete(int num) {
        Location location = getConcrete(num);
        location.getBlock().setType(Material.CONCRETE);
    }

    public Location getConcrete(int num) {
        switch (num) {
            case 1:
                return new Location(world, -62, 92, 212);
            case 2:
                return new Location(world, -62, 93, 212); // right side of the concrete
            case 3:
                return new Location(world, -62.500, 94.00000, 212.500); // niddle side of the concrete
            case 4:
                return new Location(world, -62, 94, 212); // the left side of the concrete
            default:
                return new Location(world, -62, 92, 212);
        }
    }


    public void sendBearDeadMessage(Player lastDamager) {
        EntityBear type = (EntityBear) iron_golem.material;
        Map<UUID, Double> sortedBearDamageMap = sortedBearDamageMap();
        List<UUID> damageDealers = sortedBearDamage();
        List<Killer> killers = new ArrayList<>();
        amountOfPlacedEyes.clear();
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
                player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(sortedBearDamageMap.get(player.getUniqueId())) + " " + ChatColor.GRAY + "(Position #" + (damageDealers.size() - damageDealers.indexOf(player.getUniqueId())) + ")");
                player.sendMessage(ChatColor.RESET + " ");
            }
            player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
        }
        iron_golem = null;
        buildBlackConcrteSlowly();

    }

    public void buildBlackConcrteSlowly() {
        Functions.Loop(4, 20 * TIME_FOR_BEAR_SOUL, amount -> {
            buildConcrete(amount + 1);
        });
    }

    public static List<UUID> sortedBearDamage() {
        return new ArrayList<>(sortedBearDamageMap().keySet());
    }

    @NotNull
    public static Map<UUID, Double> sortedBearDamageMap() {
        return Functions.sortByValue(bearDamage);
    }
}

