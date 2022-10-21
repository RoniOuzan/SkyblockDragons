package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.materials.theend.swords.AspectOfTheEnd;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.costs.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.*;
import org.bukkit.block.Block;

public class AspectOfTheVoid extends SwordMaterial {
    public AspectOfTheVoid() {
        super("ASPECT_OF_THE_VOID",
                Material.DIAMOND_SPADE,
                ItemFamily.ASPECT_OF_THE_END,
                "Aspect of the Void",
                Rarity.EPIC,
                new Stats(120, 100, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new AspectOfTheEnd.Transmission(12),
                new EtherTransmission()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class EtherTransmission extends ItemAbility implements ItemAbilityManaCost {
        public EtherTransmission() {
            super(AbilityAction.RIGHT_SHIFT_CLICK,
                    "Ether Transmission",
                    ChatColor.GRAY + "Teleport to your targeted block up to " + ChatColor.GREEN + "61 blocks " + ChatColor.GRAY + "away."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 180;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Block b = player.getTargetBlock(null, 61);
                Block b1 = b.getLocation().add(0, 1, 0).getBlock();
                Block b2 = b.getLocation().add(0, 2, 0).getBlock();
                float yaw = player.getLocation().getYaw();
                float pitch = player.getLocation().getPitch();
                Location l = b1.getLocation();
                l.add(0.5, 0, 0.5);
                l.setYaw(yaw);
                l.setPitch(pitch);
                if (b.getType() != Material.AIR) {
                    if (b1.getType() == Material.AIR && b2.getType() == Material.AIR) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1f, 1f);
                        player.teleport(l);
                        b1.getWorld().spawnParticle(Particle.PORTAL, l, 500, 0.1, 0.1, 0.1);
                    } else {
                        player.sendMessage(ChatColor.RED + "There is a block there!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "No blocks found!");
                }
            };
        }
    }
}
