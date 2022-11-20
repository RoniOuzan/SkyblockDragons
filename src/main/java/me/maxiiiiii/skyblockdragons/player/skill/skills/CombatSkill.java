package me.maxiiiiii.skyblockdragons.player.skill.skills;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.damage.events.UpdateEntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillRewards;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

@Getter
public class CombatSkill extends AbstractSkill {
    public CombatSkill(PlayerSD player, int level, double totalXp) {
        super(player,
                "Combat",
                "Fight mobs and players to earn Combat XP!",
                Material.STONE_SWORD,
                new SkillRewards(
                        "Warrior",
                        l -> ChatColor.WHITE + "Deal " + ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + (l * 4) + "➡" + ChatColor.GREEN + ((l + 1) * 4) + "% " + ChatColor.WHITE + "more damage to mobs",
                        StatTypes.CRIT_CHANCE,
                        0.5,
                        coinsAmount
                ),
                level,
                60,
                totalXp);
    }

    @EventHandler
    public void updateDamage(UpdateEntityDamageEntityEvent e) {
        if (e.getPlayerAttacker() != this.getPlayer()) return;

        e.getDamage().getMultiplier().addPost(this.getLevel() * 4);
    }
}
