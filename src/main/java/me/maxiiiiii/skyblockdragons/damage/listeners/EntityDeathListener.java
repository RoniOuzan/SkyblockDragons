package me.maxiiiiii.skyblockdragons.damage.listeners;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerDeathEvent;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSource;
import me.maxiiiiii.skyblockdragons.player.skill.SkillXpSourceType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Arrays;

public class EntityDeathListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(EntityDeathEvent e) {
        e.getEntity().kill();

        if (e.getKiller() instanceof PlayerSD) {
            EntitySD entity = e.getEntity();
            PlayerSD killer = (PlayerSD) e.getKiller();
            killer.giveSkill(SkillType.COMBAT, entity.material.getCombatXp(), new SkillXpSource<>(SkillXpSourceType.ENTITY, entity));
            killer.giveExp((int) (entity.material.getCoins() / 100));

            for (EntityDrop drop : entity.material.getDrops()) {
                drop.drop(killer, entity);
            }

            Functions.createHologram(entity.getLocation().add(0, 3, 0), new ArrayList<>(Arrays.asList(ChatColor.GOLD + killer.getName(), ChatColor.GOLD + "+" + Functions.getNumberFormat(entity.material.getCoins()))), 40);
            double coins = entity.material.getCoins();
            killer.giveCoins(coins);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDeath(org.bukkit.event.entity.EntityDeathEvent e) {
        e.getDrops().clear();

        if (e.getEntity() instanceof Creature && !e.getEntity().isDead()) {
            EntitySD entity = EntitySD.get(e.getEntity().getUniqueId());

            if (entity == null || entity.getAttacker() == null) return;

            EntitySD killer = entity.getAttacker();
            e.setDroppedExp(0);

            SkyblockDragons.getPlayer("LidanTheGamer").sendMessage("death");
            Bukkit.getPluginManager().callEvent(new EntityDeathEvent(entity, killer));
        }
    }

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        PlayerSD player = SkyblockDragons.getPlayer(e.getEntity());

        Bukkit.getPluginManager().callEvent(new PlayerDeathEvent(player));
    }
}
