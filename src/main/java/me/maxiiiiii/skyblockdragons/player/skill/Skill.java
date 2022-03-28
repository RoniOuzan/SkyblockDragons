package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.Skills.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@Getter
public class Skill {
    private final FarmingSkill farmingSkill;
    private final MiningSkill miningSkill;
    private final CombatSkill combatSkill;
    private final ForagingSkill foragingSkill;
    private final FishingSkill fishingSkill;
    private final EnchantingSkill enchantingSkill;
    private final AlchemySkill alchemySkill;
    private final TamingSkill tamingSkill;
    private final DungeoneeringSkill dungeoneeringSkill;
    private final PlayerSD player;

    public Skill(PlayerSD player, FarmingSkill farmingSkill, MiningSkill miningSkill, CombatSkill combatSkill, ForagingSkill foragingSkill, FishingSkill fishingSkill, EnchantingSkill enchantingSkill, AlchemySkill alchemySkill, TamingSkill tamingSkill, DungeoneeringSkill dungeoneeringSkill) {
        this.player = player;
        this.farmingSkill = farmingSkill;
        this.miningSkill = miningSkill;
        this.combatSkill = combatSkill;
        this.foragingSkill = foragingSkill;
        this.fishingSkill = fishingSkill;
        this.enchantingSkill = enchantingSkill;
        this.alchemySkill = alchemySkill;
        this.tamingSkill = tamingSkill;
        this.dungeoneeringSkill = dungeoneeringSkill;
    }

    public void give(SkillType skillType, double amount) {
        this.get(skillType.name()).setXp(this.get(skillType.name()).getCurrentXp() + amount, player);
        player.sendActionBar(ChatColor.DARK_AQUA + "+" + amount + " " + skillType + " (" + (player.getSkill().get(skillType).getCurrentXp() / player.getSkill().get(skillType).getCurrentNeedXp()) * 100d + "%)");
    }

    public AbstractSkill get(String name) {
        if (name.equalsIgnoreCase("farming")) return this.farmingSkill;
        if (name.equalsIgnoreCase("mining")) return this.miningSkill;
        if (name.equalsIgnoreCase("combat")) return this.combatSkill;
        if (name.equalsIgnoreCase("foraging")) return this.foragingSkill;
        if (name.equalsIgnoreCase("fishing")) return this.fishingSkill;
        if (name.equalsIgnoreCase("enchanting")) return this.enchantingSkill;
        if (name.equalsIgnoreCase("alchemy")) return this.alchemySkill;
        if (name.equalsIgnoreCase("taming")) return this.tamingSkill;
        if (name.equalsIgnoreCase("dungeoneering")) return this.dungeoneeringSkill;
        return farmingSkill;
    }

    public AbstractSkill get(SkillType skillType) {
        return this.get(skillType.name());
    }
}
