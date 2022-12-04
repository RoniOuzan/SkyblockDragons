package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.item.stats.*;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class RogueSword extends SwordMaterial {
    public RogueSword() {
        super("ROGUE_SWORD",
                Material.GOLD_SWORD,
                ItemFamily.ROGUE_SWORD,
                "Rogue Sword",
                Rarity.COMMON,
                new Stats(20, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                "",
                new SpeedBoost()
        );
    }

    @EventHandler
    public void updateStats(UpdateStatsEvent e) {
        if (e.isNotThisItem(this)) return;
        
        if (System.currentTimeMillis() - SpeedBoost.rogueSwordLastTimeUsed.getOrDefault(e.getPlayer(), 0L) <= 30000) {
            e.getStats().add(StatTypes.SPEED, (SpeedBoost.rogueSwordAmountUsed.get(e.getPlayer()) + 1) * 10, new StatAdd<>(StatAddType.ITEM_ABILITY, this.getAbilities().get(0)));
        }
    }

    public static class SpeedBoost extends ItemAbility implements ItemAbilityManaCost {
        public static final Map<PlayerSD, Integer> rogueSwordAmountUsed = new HashMap<>();
        public static final Map<PlayerSD, Long> rogueSwordLastTimeUsed = new HashMap<>();

        public SpeedBoost() {
            super(AbilityAction.RIGHT_CLICK,
                    "Speed Boost",
                    "Increases your movement " + StatTypes.SPEED + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "+20 " + ChatColor.GRAY + "for " + ChatColor.GREEN + "30 " + ChatColor.GRAY + "seconds. Only gives " + ChatColor.GREEN + "+10 " + StatTypes.SPEED + " " + ChatColor.GRAY + "if already in use."
            );
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 50;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                rogueSwordAmountUsed.put(player, rogueSwordAmountUsed.getOrDefault(player, 0) + 1);
                rogueSwordLastTimeUsed.put(player, System.currentTimeMillis());

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        rogueSwordAmountUsed.put(player, rogueSwordAmountUsed.get(player) - 1);
                    }
                }.runTaskLater(SkyblockDragons.plugin, 600L);
            };
        }
    }
}
