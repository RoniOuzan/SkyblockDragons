package me.maxiiiiii.skyblockdragons.item.stats;

import me.maxiiiiii.skyblockdragons.item.stats.stats.combat.*;
import me.maxiiiiii.skyblockdragons.item.stats.stats.gathering.FarmingFortuneStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.gathering.ForagingFortuneStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.gathering.MiningFortuneStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.gathering.MiningSpeedStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.misc.FishingSpeedStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.misc.MagicFindStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.misc.PetLuckStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.misc.SeaCreatureChanceStat;
import me.maxiiiiii.skyblockdragons.item.stats.stats.wisdom.*;

import java.util.ArrayList;
import java.util.List;

public class StatTypes {
    public static final List<StatType> STATS = new ArrayList<>();

    // Combat
    public static final DamageStat DAMAGE = new DamageStat();
    public static final StrengthStat STRENGTH = new StrengthStat();
    public static final CritDamageStat CRIT_DAMAGE = new CritDamageStat();
    public static final CritChanceStat CRIT_CHANCE = new CritChanceStat();
    public static final AbilityDamageStat ABILITY_DAMAGE = new AbilityDamageStat();
    public static final AttackSpeedStat ATTACK_SPEED = new AttackSpeedStat();
    public static final FerocityStat FEROCITY = new FerocityStat();
    public static final HealthStat HEALTH = new HealthStat();
    public static final DefenseStat DEFENSE = new DefenseStat();
    public static final TrueDefenseStat TRUE_DEFENSE = new TrueDefenseStat();
    public static final SpeedStat SPEED = new SpeedStat();
    public static final VitalityStat VITALITY = new VitalityStat();
    public static final IntelligenceStat INTELLIGENCE = new IntelligenceStat();
    public static final ManaStat MANA = new ManaStat();

    // Gathering
    public static final MiningSpeedStat MINING_SPEED = new MiningSpeedStat();
    public static final MiningFortuneStat MINING_FORTUNE = new MiningFortuneStat();
    public static final FarmingFortuneStat FARMING_FORTUNE = new FarmingFortuneStat();
    public static final ForagingFortuneStat FORAGING_FORTUNE = new ForagingFortuneStat();

    // Misc
    public static final MagicFindStat MAGIC_FIND = new MagicFindStat();
    public static final PetLuckStat PET_LUCK = new PetLuckStat();
    public static final SeaCreatureChanceStat SEA_CREATURE_CHANCE = new SeaCreatureChanceStat();
    public static final FishingSpeedStat FISHING_SPEED = new FishingSpeedStat();

    // Wisdom
    public static final FarmingWisdomStat FARMING_WISDOM = new FarmingWisdomStat();
    public static final MiningWisdomStat MINING_WISDOM = new MiningWisdomStat();
    public static final CombatWisdomStat COMBAT_WISDOM = new CombatWisdomStat();
    public static final ForagingWisdomStat FORAGING_WISDOM = new ForagingWisdomStat();
    public static final FishingWisdomStat FISHING_WISDOM = new FishingWisdomStat();
    public static final EnchantingWisdomStat ENCHANTING_WISDOM = new EnchantingWisdomStat();
    public static final AlchemyWisdomStat ALCHEMY_WISDOM = new AlchemyWisdomStat();
    public static final TamingWisdomStat TAMING_WISDOM = new TamingWisdomStat();
    public static final DungeoneeringWisdomStat DUNGEONEERING_WISDOM = new DungeoneeringWisdomStat();
}
