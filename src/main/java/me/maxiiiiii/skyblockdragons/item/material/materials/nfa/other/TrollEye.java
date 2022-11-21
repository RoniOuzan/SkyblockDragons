package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.other;

import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.AbilityAction;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.cooldown.ItemAbilityCooldown;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.modifiers.manacosts.ItemAbilityManaCost;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static me.maxiiiiii.skyblockdragons.SkyblockDragons.plugin;
import static me.maxiiiiii.skyblockdragons.util.Functions.getTargetEntity;
import static me.maxiiiiii.skyblockdragons.util.Functions.randomDouble;

public class TrollEye extends ToolMaterial {
    public TrollEye() {
        super("TROLL_EYE",
                Material.EYE_OF_ENDER,
                ItemFamily.TROLL,
                "Troll Eye",
                ItemType.ITEM,
                Rarity.EPIC,
                ChatColor.ITALIC + "Eye of the ender lost ERROR",
                new GetOutFromMyFace()
        );
    }

    public static class GetOutFromMyFace extends ItemAbility implements ItemAbilityManaCost, ItemAbilityCooldown {
        public GetOutFromMyFace() {
            super(AbilityAction.RIGHT_CLICK,
                    "Get Out From My Face",
                    "Make your target entity shoot ender pearls to every direction"
            );
        }

        @Override
        public double getBaseCooldown(PlayerSD player) {
            return 100;
        }

        @Override
        public double getBaseManaCost(PlayerSD player) {
            return 15;
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {
                PlayerSD player = e.getPlayer();

                Entity targetEntity = getTargetEntity(player, 100);
                if (targetEntity == null)
                    return;

                new BukkitRunnable() {
                    double times = 0;
                    @Override
                    public void run() {
                        if (times >= 20 || targetEntity.isDead()) return;
                        ((LivingEntity) targetEntity).launchProjectile(EnderPearl.class, new Vector(randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100, randomDouble(-300, 300) / 100));
                        times++;
                    }
                }.runTaskTimer(plugin, 0L, 1L);

            };
        }
    }
}
