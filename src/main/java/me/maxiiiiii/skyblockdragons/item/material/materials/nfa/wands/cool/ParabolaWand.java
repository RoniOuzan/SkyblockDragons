package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.wands.cool;

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

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;

public class ParabolaWand extends ToolMaterial {
    public ParabolaWand() {
        super("PARABOLA_WAND",
                Material.BLAZE_ROD,
                ItemFamily.TORNADO_WAND,
                "Parabola Wand",
                ItemType.WAND,
                Rarity.MYTHIC,
                "",
                new Parabola()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Parabola extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public Parabola() {
            super(AbilityAction.RIGHT_CLICK,
                    "Parabola",
                    "Cast a parabola on you with random colored blocks."
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
                Location loc = player.getLocation();

                ArrayList<Location> locations = new ArrayList<>();

                for (double x = -20; x < 20; x += 0.2) {
                    Location newLocation = loc.clone().add(x, Math.pow(x, 2), 0);
                    locations.add(newLocation);
                }

                Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    locations.forEach(location -> {
                        location.getWorld().spawnParticle(Particle.REDSTONE, location, 0, 200, 0, 0);
                    });
                }, 0L, 1L);

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
