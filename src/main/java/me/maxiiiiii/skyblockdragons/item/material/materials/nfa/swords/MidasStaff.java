package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.util.Functions.Loop;

public class MidasStaff extends SwordMaterial {
    public MidasStaff() {
        super("MIDAS_STAFF",
                Material.GOLD_SPADE,
                ItemFamily.MIDAS_STAFF,
                "Midas Staff",
                Rarity.LEGENDARY,
                new Stats(130, 150, 0, 0, 0, 0, 0, 0, 0, 50),
                ChatColor.GOLD + "Ability Greed" + " NEW_LINE " + ChatColor.GRAY + "The " + ChatColor.DARK_AQUA + "ability damage bonus " + ChatColor.GRAY + "of this item is dependent on the price paid for it at the " + ChatColor.DARK_PURPLE + "Dark Action" + ChatColor.GRAY + "! The maximum bonus of this item is " + ChatColor.DARK_AQUA + "26,000 " + ChatColor.GRAY + "if the bid was " + ChatColor.GOLD + "100,000,000 Coins " + ChatColor.GRAY + "or higher!",
                new MoltenWave()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class MoltenWave extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown, Listener {
        public MoltenWave() {
            super(AbilityAction.RIGHT_CLICK,
                    "Molten Wave",
                    "Cast a wave of molten gold in the direction you are facing! Deals up to " + ChatColor.RED + "6,000 " + ChatColor.GRAY + "damage."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 500;
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 1;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Vector direction = player.getLocation().getDirection().clone();
                direction.setY(0);
                direction.multiply(5);
                direction.normalize();
                Location location = player.getLocation().add(direction.clone().multiply(1));
                location.add(0, 1, 0);

                Loop(7, 3L, (i) -> {
                    Location location1 = location.clone().add(direction.clone().multiply(i));
                    Location location2 = location.clone().add(direction.clone().multiply(i));
                    location2.setYaw(location2.getYaw() + 90);
                    location2.add(location2.clone().getDirection().multiply(1));

                    Location location3 = location.clone().add(direction.clone().multiply(i));
                    location3.setYaw(location3.getYaw() - 90);
                    location3.add(location3.clone().getDirection().multiply(1));

                    spawnBlock(location1);
                    spawnBlock(location2);
                    spawnBlock(location3);
                });
            };
        }

        @EventHandler
        public void onFallingBlockLand(EntityChangeBlockEvent e) {
            if (e.getEntity().getName().contains("MidasStaff")) {
                e.setCancelled(true);
            }
        }

        private void spawnBlock(Location location) {
            FallingBlock block = location.getWorld().spawnFallingBlock(location, new MaterialData(Material.GOLD_BLOCK));
            block.setInvulnerable(true);
            block.setDropItem(false);
            block.setCustomName("MidasStaff");
            block.setHurtEntities(false);
        }
    }
}
