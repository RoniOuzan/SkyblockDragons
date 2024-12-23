package me.maxiiiiii.skyblockdragons.player.skill;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.player.skill.skills.*;

import java.util.Iterator;

@Getter
public class Skills implements Iterable<AbstractSkill> {
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

    public Skills(PlayerSD player) {
        this.player = player;
        this.farmingSkill = new FarmingSkill(player, Variables.getInt(player.getUniqueId(), "SkillFarming", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillFarming", 1, 0d));
        this.miningSkill = new MiningSkill(player, Variables.getInt(player.getUniqueId(), "SkillMining", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillMining", 1, 0d));
        this.combatSkill = new CombatSkill(player, Variables.getInt(player.getUniqueId(), "SkillCombat", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillCombat", 1, 0d));
        this.foragingSkill = new ForagingSkill(player, Variables.getInt(player.getUniqueId(), "SkillForaging", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillForaging", 1, 0d));
        this.fishingSkill = new FishingSkill(player, Variables.getInt(player.getUniqueId(), "SkillFishing", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillFishing", 1, 0d));
        this.enchantingSkill = new EnchantingSkill(player, Variables.getInt(player.getUniqueId(), "SkillEnchanting", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillEnchanting", 1, 0d));
        this.alchemySkill = new AlchemySkill(player, Variables.getInt(player.getUniqueId(), "SkillAlchemy", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillAlchemy", 1, 0d));
        this.tamingSkill = new TamingSkill(player, Variables.getInt(player.getUniqueId(), "SkillTaming", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillTaming", 1, 0d));
        this.dungeoneeringSkill = new DungeoneeringSkill(player, Variables.getInt(player.getUniqueId(), "SkillDungeoneering", 0, 0), Variables.getDouble(player.getUniqueId(), "SkillDungeoneering", 1, 0d));
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

    public double getAverage() {
        return Functions.average(
                this.getFarmingSkill().getLevel(),
                this.getMiningSkill().getLevel(),
                this.getCombatSkill().getLevel(),
                this.getForagingSkill().getLevel(),
                this.getFishingSkill().getLevel(),
                this.getEnchantingSkill().getLevel(),
                this.getAlchemySkill().getLevel(),
                this.getTamingSkill().getLevel(),
                this.getDungeoneeringSkill().getLevel()
        );
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

    public int size() {
        return 9;
    }

    public AbstractSkill get(int index) {
        switch (index) {
            case 0:
                return farmingSkill;
            case 1:
                return miningSkill;
            case 2:
                return combatSkill;
            case 3:
                return foragingSkill;
            case 4:
                return fishingSkill;
            case 5:
                return enchantingSkill;
            case 6:
                return alchemySkill;
            case 7:
                return tamingSkill;
            case 8:
                return dungeoneeringSkill;
        }
        return farmingSkill;
    }

    @Override
    public Iterator<AbstractSkill> iterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator<AbstractSkill> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public AbstractSkill next() {
            return get(index++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("not supported yet");
        }
    }
}
