package me.maxiiiiii.skyblockdragons.worlds.bearisland;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Killer;

import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.worlds.bearisland.events.BearKillEvent;
import me.maxiiiiii.skyblockdragons.worlds.bearisland.events.PlayerPlaceFurEvent;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


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
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
    public static final Map<PlayerSD, Integer> amountOfPlacedFurs = new HashMap<>();

    public void onPlaceFur(PlayerPlaceFurEvent e) {
        e.getBlock().setData((byte) (e.getBlock().getData() + 4));
        e.addToAmountOfFurs();
        amountOfPlacedFurs.put(e.getPlayer(), amountOfPlacedFurs.getOrDefault(e.getPlayer(), 0) + 1);
        for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BearIsland")).collect(Collectors.toList())) {
            player.sendMessage( ChatColor.DARK_PURPLE + "☬ " + e.getPlayer().getDisplayName() + ChatColor.LIGHT_PURPLE + " placed an fur! (" + e.getAmountOfFurs() + "/8)");
        }


        if (e.getAmountOfFurs() >= 8) {
            Functions.Wait(20L, BearIsland::spawnBear);
        }
    }
    @EventHandler
    public void onClickBlock(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;

        if (e.getClickedBlock().getType() == Material.ENDER_PORTAL_FRAME && e.getClickedBlock().getData() < 4) {
            ItemStack item = e.getItem();
            if (Functions.getId(item).equals("BEAR_EYE")) {
                if (e.getClickedBlock().getLocation().distance(MIDDLE) <= 5) {
                    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
                        e.getPlayer().getEquipment().setItemInMainHand(null);
                    e.setCancelled(true);
                    PlayerPlaceFurEvent event = new PlayerPlaceFurEvent(SkyblockDragons.getPlayer(e.getPlayer()), e.getClickedBlock(), e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(event);
                    onPlaceFur(event);
                }
            }
        }
    }

    public static void resetFurs() {
        for (Block block : Functions.loopBlocksHorizontally(MIDDLE, 10)) {
            if (block.getType() == Material.ENDER_PORTAL_FRAME && block.getData() > 3) {
                block.setData((byte) (block.getData() - 4));
            }
        }
    }


        @EventHandler
        public void onBearKill(BearKillEvent e){
            List<Killer> killers = new ArrayList<>();
            for (PlayerSD player : e.getKillers().keySet()) {
                killers.add(new Killer(player, e.getKillers().get(player)));
            }
            Collections.sort(killers);

            BearType bearType = BearType.getBearType(e.getEntity().type.getName());

            if (bearType == null) {
                SkyblockDragons.logger.info(ChatColor.RED + "Cannot find the type of the bear!");
                return;
            }

            Map<PlayerSD, Integer> positions = new HashMap<>();
            // 1 to make the place be easier to see
            for (int i = 1; i < killers.size() + 1; i++) {
                PlayerSD player = killers.get(i - 1).player;
                double damage = killers.get(i - 1).damage;

                positions.put(player, i);
            }

            for (Player player : Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals("BearIsland")).collect(Collectors.toList())) {
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
                player.sendMessage("                      " + bearType.color + "" + ChatColor.BOLD + Functions.setTitleCase(bearType.name()) + " Bear");
                player.sendMessage(ChatColor.RESET + " ");
                player.sendMessage("           " + ChatColor.WHITE + e.getKiller().getDisplayName() + ChatColor.GRAY + " dealt the final hit!");
                player.sendMessage(ChatColor.RESET + " ");
                if (killers.size() > 0)
                    player.sendMessage("           " + ChatColor.YELLOW + "" + ChatColor.BOLD + "1st Damager " + ChatColor.GRAY + "- " + killers.get(0).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(0).damage));
                if (killers.size() > 1)
                    player.sendMessage("         " + ChatColor.GOLD + "" + ChatColor.BOLD + "2nd Damager " + ChatColor.GRAY + "- " + killers.get(1).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(1).damage));
                if (killers.size() > 2)
                    player.sendMessage("       " + ChatColor.RED + "" + ChatColor.BOLD + "3rd Damager " + ChatColor.GRAY + "- " + killers.get(2).player.getDisplayName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + Functions.getNumberFormat(killers.get(2).damage));
                player.sendMessage(ChatColor.RESET + " ");
                if (e.getKillers().containsKey(SkyblockDragons.getPlayer(player))) {
                    player.sendMessage("                  " + ChatColor.YELLOW + "Your Damage: " + ChatColor.GREEN + Functions.getNumberFormat(e.getKillers().get(SkyblockDragons.getPlayer(player))) + " " + ChatColor.GRAY + "(Position #" + (positions.get(SkyblockDragons.getPlayer(player))) + ")");
                    player.sendMessage(ChatColor.RESET + " ");
                }
                player.sendMessage(ChatColor.RESET + "" + ChatColor.GREEN + "" + ChatColor.BOLD + "----------------------------------------");
            }

            BearIsland.bearDamage.clear();
            BearIsland.bear = null;
            amountOfPlacedFurs.clear();
            PlayerPlaceFurEvent.resetAmountOfFurs();

            Functions.Wait(100L, BearIsland::resetFurs);

            for (Block block : Functions.loopBlocksHorizontally(BearIsland.MIDDLE_OF_LOOT, 5.5)) {
                block.setType(Material.DIRT);
            }
            Functions.Wait(400L, () -> {
                for (Block block : Functions.loopBlocksHorizontally(BearIsland.MIDDLE_OF_LOOT, 5.5)) {
                    block.setType(Material.GRASS);
                }
            });
        }
    }