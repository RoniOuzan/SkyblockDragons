package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EntityDeathListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        e.getEntity().kill();

        if (e.getKiller() instanceof PlayerSD) {
            EntitySD entity = e.getEntity();
            PlayerSD killer = (PlayerSD) e.getKiller();
            killer.giveSkill(SkillType.COMBAT, entity.type.getCombatXp());
            killer.giveExp((int) (entity.type.getCoins() / 100));

            for (EntityDrop drop : entity.type.getDrops()) {
                drop.drop(killer, entity);
            }

            // TODO: drops
//            if (killer.getEnchantLevel(EnchantType.TELEKINESIS) > 0) {
//                for (Drop drop : killer.type.getDrops()) {
//                    killer.give(drop, killer);
//                }
//            } else {
//                for (Drop drop : killer.type.getDrops()) {
//                    ItemStack item = drop.generate(killer, entity);
//                    if (item != null) {
//                        org.bukkit.entity.Item dropped = entity.getWorld().dropItem(entity.getLocation(), item);
//                        dropped.addScoreboardTag(killer.getName());
//                    }
//                }
//            }

            // TODO: coins
//            Functions.createHologram(entity.getLocation().add(0, 3, 0), new ArrayList<>(Arrays.asList(ChatColor.GOLD + player.getName(), ChatColor.GOLD + "+" + Functions.getNumberFormat(entity.type.getCoins()))), 40);
//            double coins = entity.type.getCoins();
//            if (player.getEnchantLevel(EnchantType.SCAVENGER) > 0)
//                coins += (player.getEnchantLevel(EnchantType.SCAVENGER) * EnchantType.SCAVENGER.getMultiplayers().get(player.getEnchantLevel(EnchantType.SCAVENGER)));
//            player.giveCoins(coins);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(org.bukkit.event.entity.EntityDeathEvent e) {
        e.getDrops().clear();

        if (e.getEntity().getLastDamageCause().getEntity() == null) return;

        if (e.getEntity() instanceof Creature) {
            EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());
            EntitySD killer = EntitySD.get(e.getEntity().getLastDamageCause().getEntity());
            e.getDrops().clear();
            e.setDroppedExp(0);

            Bukkit.getPluginManager().callEvent(new EntityDeathEvent(entity, killer, EntityDeathEvent.DeathCause.ENTITY));



        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getEntity());

        if (player.getAttacker() == null) {
            e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died!");
        } else if (player.getAttacker() instanceof Player) {
            e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + ((Player) player.getAttacker()).getDisplayName() + ChatColor.GRAY + "!");
        } else {
            e.setDeathMessage(ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + " died by " + player.getAttacker().type.getName() + ChatColor.GRAY + "!");
        }

        // TODO: lose coins
//        if (LOSE_COINS) {
//            double amount = player.getCoins() / 2;
//            player.removeCoins(amount);
//            player.sendMessage(ChatColor.RED + "You died and lost " + Functions.getNumberFormat(amount) + " coins.");
//        }
        player.teleport(player.getWorldSD().getWarp().getLocation());
        player.setInvulnerable(true);
        Functions.Wait(1L, () -> player.spigot().respawn());
        Functions.Wait(100L, () -> player.setInvulnerable(false));
    }
}
