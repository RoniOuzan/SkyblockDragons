package me.maxiiiiii.skyblockdragons.itemcreator.abilities;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static me.maxiiiiii.skyblockdragons.util.Functions.getId;

public class Tornado_Wand implements Listener {
    private static final DyeColor[] RAINBOW = new DyeColor[] {
            DyeColor.RED,
            DyeColor.ORANGE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.GREEN,
            DyeColor.CYAN,
            DyeColor.LIGHT_BLUE,
            DyeColor.BLUE,
            DyeColor.PURPLE,
            DyeColor.PINK
    };

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        ItemStack item = e.getItem();

        if (!getId(item).equals("TORNADO_WAND")) return;
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        Location loc = player.getLocation().add(0, 2, 0);

        player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1f, 1.5f);

        List<RainbowPiece> pieces = new ArrayList<>();

        double height = 200;
        for (double y = 0; y < height; ++y) {
            double radius = y / 5;
            double rad = (y / 20.0) * Math.PI;
            Location standLocation = loc.clone().add(
                    Math.cos(rad) * radius,
                    y / 4,
                    Math.sin(rad) * radius
            );
            ArmorStand stand = spawnArmorStand(standLocation, (int) y);
            RainbowPiece rainbowPiece = new RainbowPiece(stand, y, radius, rad);
            pieces.add(rainbowPiece);
        }

        Bukkit.getScheduler().runTaskTimer(SkyblockDragons.plugin, () -> {
            pieces.forEach(piece -> {
                piece.rad += (Math.PI + 2) * 2;
                Location newPieceLoc = loc.clone().add(
                        Math.cos(piece.rad) * piece.radius,
                        piece.height,
                        Math.sin(piece.rad) * piece.radius
                );
                piece.stand.teleport(newPieceLoc);
            });
        }, 0L, 1L);

//        Location blockLocation = getBlockBelow(loc).getLocation();

//        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
//            pieces.forEach(piece -> {
//                Location newPieceLoc = piece.stand.getLocation().add(
//                        randomDouble(-100, 100) / 100,
//                        0,
//                        randomDouble(-100, 100) / 100
//                );
//                piece.stand.teleport(newPieceLoc);
//            });
//        }, 0L, 1L);

        // ofir error wand
//        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
//            double num = 0;
//            @Override
//            public void run() {
//                if (pieces.get(0).stand.isDead()) return;
//                double randomX = 0;
//                double randomZ = 0;
//                if (chanceOf(90)) {
//                    randomX = randomDouble(-100, 100) / 100;
//                    randomZ = randomDouble(-100, 100) / 100;
//                }
//                num += randomX;
//                num += randomZ;
//                for (RainbowPiece piece : pieces) {
//                    piece.rad += (Math.PI + 2) * 2;
//                    Location newPieceLoc = blockLocation.clone().add(
//                            Math.cos(piece.rad) * piece.radius,
//                            piece.height,
//                            Math.sin(piece.rad) * piece.radius
//                    );
//                    newPieceLoc.setYaw((float) randomDouble(-179, 179));
//                    newPieceLoc.add(num + randomX, 0, num + randomZ);
//                    newPieceLoc.add(randomDouble(-1, 1), 0, randomDouble(-1, 1));
//                    piece.stand.teleport(newPieceLoc);
//
//                    DyeColor color = RAINBOW[randomInt(0, 100) % RAINBOW.length];
//
//                    ItemStack wool = new ItemStack(Material.WOOL, 1, color.getWoolData());
//                    piece.stand.setHelmet(wool);
//                }
////                Bukkit.getScheduler().runTaskLater(plugin, () -> {
////                    for (Block block : loopBlocks(pieces.get(0).stand.getLocation(), 10)) {
////                        if (block.getType().isSolid() && block.getWorld().getBlockAt(block.getX(), block.getY() + 1, block.getZ()).getType() == Material.AIR) {
////                            if (chanceOf(5)) {
////                                MaterialData material = new MaterialData(block.getType());
////                                block.setType(Material.AIR);
////                                Entity fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation(), material);
////                                fallingBlock.setVelocity(new Vector(randomDouble(0, 100) / 100, 3, randomDouble(0, 100) / 100));
////                            }
////                        }
////                    }
////                }, 1L);
//            }
//        }, 0L, 1L);
    }

    private ArmorStand spawnArmorStand(Location loc, int index) {
        ArmorStand stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        stand.teleport(loc.clone().subtract(0, 1, 0));
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setMarker(true);
        stand.addScoreboardTag("Tornado");

        DyeColor color = RAINBOW[index % RAINBOW.length];

        ItemStack wool = new ItemStack(Material.WOOL, 1, color.getWoolData());
        stand.setHelmet(wool);

        return stand;
    }

    private static class RainbowPiece {
        private final ArmorStand stand;
        private final double height;
        private final double radius;
        private double rad;

        private RainbowPiece(ArmorStand stand, double height, double radius, double rad) {
            this.stand = stand;
            this.height = height;
            this.radius = radius;
            this.rad = rad;
        }
    }
}
