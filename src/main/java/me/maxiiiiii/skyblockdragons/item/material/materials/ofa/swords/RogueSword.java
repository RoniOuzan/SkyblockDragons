package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerUseAbilityEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

    @Override
    public void updateStats(PlayerStats stats) {
        if (System.currentTimeMillis() - SpeedBoost.rogueSwordLastTimeUsed.getOrDefault(stats.getPlayer(), 0L) <= 30000) {
            stats.getSpeed().amount += (SpeedBoost.rogueSwordAmountUsed.get(stats.getPlayer()) + 1) * 10;
        }
    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class SpeedBoost extends ItemAbility {
        public static final Map<PlayerSD, Integer> rogueSwordAmountUsed = new HashMap<>();
        public static final Map<PlayerSD, Long> rogueSwordLastTimeUsed = new HashMap<>();

        public SpeedBoost() {
            super(AbilityAction.RIGHT_CLICK,
                    "Speed Boost",
                    "Increases your movement " + StatType.SPEED.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "+20 " + ChatColor.GRAY + "for " + ChatColor.GREEN + "30 " + ChatColor.GRAY + "seconds. Only gives " + ChatColor.GREEN + "+10 " + StatType.SPEED.getIconAndText() + " " + ChatColor.GRAY + "if already in use.",
                    50,
                    0
            );
        }

        @Override
        public Runnable onAbilityUse(PlayerUseAbilityEvent e) {
            return () -> {
                PlayerSD player = e.getPlayer();

                if (player.manaCost(e.getItem(), 0)) return;

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
