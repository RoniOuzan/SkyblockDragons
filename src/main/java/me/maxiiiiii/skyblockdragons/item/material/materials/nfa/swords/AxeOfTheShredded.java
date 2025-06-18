package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.NormalEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilitySilentCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.objects.AIFly;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class AxeOfTheShredded extends SwordMaterial {
    public AxeOfTheShredded() {
        super("AXE_OF_THE_SHREDDED",
                Material.DIAMOND_AXE,
                ItemFamily.REVENANT_FALCHION,
                "Axe of the Shredded",
                Rarity.LEGENDARY,
                new Stats(140, 115, 0, 0, 0, 0, 0, 0, 0, 0),
                "Heal " + ChatColor.RED + "50" + StatTypes.HEALTH.getIcon() + " " + ChatColor.GRAY + "per hit." + " NEW_LINE " + "Deal " + ChatColor.GREEN + "+250% " + ChatColor.GRAY + "damage to Zombies." + " NEW_LINE " + "Receive " + ChatColor.GREEN + "25% " + ChatColor.GRAY + "less damage from Zombies when held.",
                new Throw()
        );
    }

    public static class Throw extends ItemAbility implements ItemAbilityManaCost, ItemAbilitySilentCooldown {
        public Throw() {
            super(AbilityAction.RIGHT_CLICK,
                    "Throw",
                    "Throw your axe damaging all enemies in its path dealing " + ChatColor.RED + "10% " + ChatColor.GRAY + "melee damage. Consecutive throws stack " + ChatColor.RED + "2x " + ChatColor.GRAY + "damage but cost " + ChatColor.BLUE + "2x " + ChatColor.GRAY + "mana up to 16x"
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            if (!users.containsKey(player)) {
                return 20;
            }

            ThrowRunnable runnable = ((ThrowRunnable) getAbilityOfPlayer(player).getRunnable());

            if (SkyblockDragons.getCurrentTimeInSeconds() - runnable.lastTimeUsed >= 5) {
                runnable.manaCost = 20;
                runnable.damageMultiplier = 10;
            }

            return runnable.manaCost;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 0.2;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return new ThrowRunnable();
        }

        private static class ThrowRunnable implements PlayerAbilityRunnable {
            private int manaCost = 20;
            private int damageMultiplier = 10;
            private double lastTimeUsed = 0;

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();
                Location location = player.getLocation();

                if (manaCost < 320) {
                    manaCost *= 2;
                }

                if (damageMultiplier < 160) {
                    damageMultiplier *= 2;
                }

                lastTimeUsed = SkyblockDragons.getCurrentTimeInSeconds();

                player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1f, 1f);
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                stand.setVisible(false);
                final ArrayList<EntitySD> damagedEntities = new ArrayList<>();
                Loop(50, 1L, (i) -> {
                    if (stand.getLocation().add(0, 1, 0).getBlock().getType().isSolid()) {
                        stand.remove();
                        return;
                    }

                    Location newLocation = location.clone().add(location.clone().getDirection().multiply(i));
                    stand.teleport(newLocation);
                    stand.setRightArmPose(new EulerAngle(Math.toRadians(i * 40), 0, 0));

                    for (EntitySD entity : loopEntities(newLocation, 1)) {
                        if (!damagedEntities.contains(entity)) {
                            damagedEntities.add(entity);
                            MeleeEntityDamageEntity damage = new MeleeEntityDamageEntity(player, entity);
                            damage.getMultiplier().addPost(-100 + damageMultiplier);
                            player.damage(damage);
                        }
                    }
                }, (i) -> stand.remove());
                stand.setGravity(false);
                stand.setMarker(true);
                stand.addScoreboardTag("AxeOfTheShredded");

                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                stand.setItemInHand(axe);


            }
        }
    }
}
