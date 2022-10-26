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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

public class WorldEater extends MiningMaterial {
    public WorldEater() {
        super("WORLD_EATER",
                Material.DIAMOND_PICKAXE,
                ItemFamily.JUNGLE_AXE,
                "World Eater",
                ItemType.PICKAXE,
                Rarity.DIVINE,
                10,
                "",
                new BreakError()
        );
    }

    private static class BreakError extends ItemAbility implements Listener {
        private final Cooldown<Player> cooldown = new Cooldown<>();

        public BreakError() {
            super(AbilityAction.NONE,
                    "Break",
                    ChatColor.ITALIC + "I became a world ERROR."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {};
        }

        @EventHandler
        public void onBreak(BlockBreakEvent e) {
            ItemStack item = e.getPlayer().getEquipment().getItemInMainHand();
            if (!getId(item).equals("WORLD_EATER")) return;

            PlayerSD player = SkyblockDragons.getPlayer(e.getPlayer());

            if (cooldown(player, cooldown, 1000, true)) return;

            recursiveBreakBlocks(e.getBlock().getLocation(), 500, e.getBlock().getType(), 2);
        }
    }
}
