package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheEnd;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.HashMap;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.players;
import static me.maxiiiiii.skyblockdragons.util.Functions.Loop;

public class AxeOfTheShredded extends SwordMaterial {
    public AxeOfTheShredded() {
        super("AXE_OF_THE_SHREDDED",
                Material.DIAMOND_AXE,
                ItemFamily.REVENANT_FALCHION,
                "Axe of the Shredded",
                Rarity.LEGENDARY,
                new Stats(140, 115, 0, 0, 0, 0, 0, 0, 0, 0),
                "Heal " + ChatColor.RED + "50" + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per hit." + " NEW_LINE " + "Deal " + ChatColor.GREEN + "+250% " + ChatColor.GRAY + "damage to Zombies." + " NEW_LINE " + "Receive " + ChatColor.GREEN + "25% " + ChatColor.GRAY + "less damage from Zombies when held.",
                new Throw()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class Throw extends ItemAbility {
        public Throw() {
            super(AbilityAction.RIGHT_CLICK,
                    "Throw",
                    "Throw your axe damaging all enemies in its path dealing " + ChatColor.RED + "10% " + ChatColor.GRAY + "melee damage. Consecutive throws stack " + ChatColor.RED + "2x " + ChatColor.GRAY + "damage but cost " + ChatColor.BLUE + "2x " + ChatColor.GRAY + "mana up to 16x",
                    20,
                    0
            );
        } // TODO: finish this

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return new Runnable() {
                private int manaCost = 0;
                private long lastTimeUsed = 0;

                @Override
                public void run() {
                    PlayerSD player = e.getPlayer();
                    Location location = player.getLocation();

                    if (System.currentTimeMillis() - lastTimeUsed >= 5_000) manaCost = 20;
                    else if (manaCost < 320) manaCost *= 2;

                    manaCost.put(player, System.currentTimeMillis());

                    player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 1f, 1f);
                    ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

                    stand.setVisible(false);
                    stand.setGravity(false);
                    stand.setMarker(true);
                    stand.addScoreboardTag("AxeOfTheShredded");

                    ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                    stand.setItemInHand(axe);

                    Loop(50, 1L, (i) -> {
                        if (stand.getLocation().add(0, 1, 0).getBlock().getType().isSolid()) {
                            stand.remove();
                            return;
                        }

                        Location newLocation = location.clone().add(location.clone().getDirection().multiply(i));
                        stand.teleport(newLocation);
                        stand.setRightArmPose(new EulerAngle(Math.toRadians(i * 40), 0, 0));
                    }, (i) -> stand.remove());
                }
            };
        }
    }
}
