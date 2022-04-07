package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Skill {
    private final PlayerSD player;
    private final FarmingSkill farmingSkill;
    private final MiningSkill miningSkill;
    private final CombatSkill combatSkill;
    private final ForagingSkill foragingSkill;
    private final FishingSkill fishingSkill;
    private final EnchantingSkill enchantingSkill;
    private final AlchemySkill alchemySkill;
    private final TamingSkill tamingSkill;
    private final DungeoneeringSkill dungeoneeringSkill;

    public Skill(PlayerSD player) {
        this.player = player;
        this.farmingSkill = new FarmingSkill(Variables.get(player.getUniqueId(), "SkillFarming", 0, 0), Variables.get(player.getUniqueId(), "SkillFarming", 1, 0d));
        this.miningSkill = new MiningSkill(Variables.get(player.getUniqueId(), "SkillMining", 0, 0), Variables.get(player.getUniqueId(), "SkillMining", 1, 0d));
        this.combatSkill = new CombatSkill(Variables.get(player.getUniqueId(), "SkillCombat", 0, 0), Variables.get(player.getUniqueId(), "SkillCombat", 1, 0d));
        this.foragingSkill = new ForagingSkill(Variables.get(player.getUniqueId(), "SkillForaging", 0, 0), Variables.get(player.getUniqueId(), "SkillForaging", 1, 0d));
        this.fishingSkill = new FishingSkill(Variables.get(player.getUniqueId(), "SkillFishing", 0, 0), Variables.get(player.getUniqueId(), "SkillFishing", 1, 0d));
        this.enchantingSkill = new EnchantingSkill(Variables.get(player.getUniqueId(), "SkillEnchanting", 0, 0), Variables.get(player.getUniqueId(), "SkillEnchanting", 1, 0d));
        this.alchemySkill = new AlchemySkill(Variables.get(player.getUniqueId(), "SkillAlchemy", 0, 0), Variables.get(player.getUniqueId(), "SkillAlchemy", 1, 0d));
        this.tamingSkill = new TamingSkill(Variables.get(player.getUniqueId(), "SkillTaming", 0, 0), Variables.get(player.getUniqueId(), "SkillTaming", 1, 0d));
        this.dungeoneeringSkill = new DungeoneeringSkill(Variables.get(player.getUniqueId(), "SkillDungeoneering", 0, 0), Variables.get(player.getUniqueId(), "SkillDungeoneering", 1, 0d));
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

    public void save() {
        this.farmingSkill.save(player);
        this.miningSkill.save(player);
        this.combatSkill.save(player);
        this.foragingSkill.save(player);
        this.fishingSkill.save(player);
        this.enchantingSkill.save(player);
        this.alchemySkill.save(player);
        this.tamingSkill.save(player);
        this.dungeoneeringSkill.save(player);
    }
}
