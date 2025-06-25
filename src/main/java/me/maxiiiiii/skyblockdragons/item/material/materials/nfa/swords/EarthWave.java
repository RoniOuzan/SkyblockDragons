package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.MeleeEntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityUsage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

public class EarthWave extends ToolMaterial {
    public EarthWave() {
        super("EARTH_WAVE",
                Material.WOOD_SPADE,
                ItemFamily.NULL,
                "Earth Wave",
                ItemType.WAND,
                Rarity.EPIC,
                "",
                new EarthQuake());
    }

    private static class EarthQuake extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown, Listener {

        protected EarthQuake() {
            super(AbilityAction.RIGHT_CLICK,
                    "Earth Quake",
                    "Cast a earth wave in the direction you are facing! Deals up to " + ChatColor.RED + "8,000 " + ChatColor.GRAY + "damage.");
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 1;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 400;
        }

        @Override
        protected PlayerAbilityRunnable setupAbility() {
            return new AbilityRunnable();
        }

        @EventHandler
        public void onFallingBlockLand(EntityChangeBlockEvent e) {
            if (e.getEntity().getName().contains("EarthQuake")) {
                e.setCancelled(true);
            }
        }

        private static class AbilityRunnable implements PlayerAbilityRunnable {
            private static final int LENGTH = 14;
            private static final double AMOUNT_OF_BLOCKS_MULTIPLIER = 1;

            @Override
            public void run(PlayerAbilityUsage e) {
                PlayerSD player = e.getPlayer();

                Location location = player.getLocation().add(new Vector(0, 1, 0));
                Vector direction = player.getLocation().getDirection().setY(0).normalize();
                Vector horizontal = direction.getCrossProduct(new Vector(0, 1, 0)).normalize();

                Functions.Loop(LENGTH, 3L, i -> {
                    double amountOfBlocks = i * AMOUNT_OF_BLOCKS_MULTIPLIER;
//                    amountOfBlocks = Math.round(amountOfBlocks * 2) / 2.0;
                    Location centerLocation = location.clone().add(direction.clone().multiply(i));

                    for (double j = -amountOfBlocks / 2; j <= amountOfBlocks / 2; j++) {
                        Location newLocation = centerLocation.clone().add(horizontal.clone().multiply(j));
                        MaterialData material = Functions.getLowestBlock(newLocation).getState().getData();
                        spawnBlock(newLocation, material);

                        Functions.loopEntities(newLocation.subtract(new Vector(0, -3, 0)), 1.2, 7).forEach(en ->
                                player.damage(new MeleeEntityDamageEntity(player, en)));
                    }
                });
            }

            private void spawnBlock(Location location, MaterialData materialData) {
                FallingBlock block = location.getWorld().spawnFallingBlock(location, materialData);
                block.setInvulnerable(true);
                block.setDropItem(false);
                block.setCustomName("EarthQuake");
                block.setHurtEntities(false);

                SkyblockDragons.entitiesToKill.add(block);
            }
        }
    }
}
