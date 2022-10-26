package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.wands.cool;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HurricaneWand extends ToolMaterial {
    public HurricaneWand() {
        super("HURRICANE_WAND",
                Material.BLAZE_ROD,
                ItemFamily.TORNADO_WAND,
                "Twister Wand",
                ItemType.WAND,
                Rarity.MYTHIC,
                "",
                new Tornado()
        );
    }

    public static class Tornado extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public Tornado() {
            super(AbilityAction.RIGHT_CLICK,
                    "Tornado",
                    "Cast a multi tornadoes on you with random colored blocks."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 10;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 1;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();
                Location location = player.getLocation().add(0, 2, 0);

                ArrayList<Location> locations = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    locations.add(location);
                }
                locations.set(0, locations.get(0).clone().add(10, 0, 0));
                locations.set(1, locations.get(1).clone().add(7.07, 0, 7.07));
                locations.set(2, locations.get(2).clone().add(0, 0, 10));
                locations.set(3, locations.get(3).clone().add(7.07, 0, -7.07));
                locations.set(4, locations.get(4).clone().add(0, 0, -10));
                locations.set(5, locations.get(5).clone().add(-7.07, 0, -7.07));
                locations.set(6, locations.get(6).clone().add(-10, 0, 0));
                locations.set(7, locations.get(7).clone().add(-7.07, 0, 7.07));

                for (Location loc : locations) {
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
                }

            };
        }

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
}
