package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.tools;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class TreeCapitator extends MiningMaterial {
    public TreeCapitator() {
        super("TREE_CAPITATOR",
                Material.GOLD_AXE,
                ItemFamily.JUNGLE_AXE,
                "Tree Capitator",
                ItemType.AXE,
                Rarity.EPIC,
                1,
                "",
                new Break()
        );
    }

    private static class Break extends ItemAbility implements Listener {
        private final Cooldown<Player> cooldown = new Cooldown<>();

        public Break() {
            super(AbilityAction.NONE,
                    "Break",
                    "A forceful Gold Axe which can break a large amount of logs in a single hit!"
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }

        @EventHandler
        public void onBreak(BlockBreakEvent e) {
            if (e.getPlayer() == null) return;

            ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();

            if (!getId(item).equals("TREE_CAPITATOR")) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

            if (cooldown(player, cooldown, 2000, true)) return;

            if (e.getBlock().getType().toString().contains("LOG") || e.getBlock().getType().toString().contains("OAK") || e.getBlock().getType().toString().contains("WOOD") || e.getBlock().getType().toString().contains("BIRCH") || e.getBlock().getType().toString().contains("SPRUCE") || e.getBlock().getType().toString().contains("JUNGLE") || e.getBlock().getType().toString().contains("ACACIA")) {
                recursiveBreakBlocks(e.getBlock().getLocation(), 12, e.getBlock().getType(), 2);
            }
        }
    }
}
