package me.maxiiiiii.skyblockdragons.item.enchants;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemTypeGroup;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirements;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;

import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.setTitleCase;

@Getter
public class EnchantType implements Comparable<EnchantType> {

    public static HashMap<String, EnchantType> enchants = new HashMap<>();

    // Ultimate Enchants
    public static UltimateEnchantType CHIMERA = null;
    public static UltimateEnchantType ONE_FOR_ALL = null;
    public static UltimateEnchantType ULTIMATE_WISE = null;
    // Swords
    public static EnchantType BANE_OF_ARTHROPODS = null;
    public static EnchantType CLEAVE = null;
    public static EnchantType CRITICAL = null;
    public static EnchantType ENDER_SLAYER = null;
    public static EnchantType EXECUTE = null;
    public static EnchantType EXPERIENCE = null;
    public static EnchantType FIRE_ASPECT = null;
    public static EnchantType FIRST_STRIKE = null;
    public static EnchantType GIANT_KILLER = null;
    public static EnchantType KNOCKBACK = null;
    public static EnchantType LETHALITY = null;
    public static EnchantType LIFE_STEAL = null;
    public static EnchantType LOOTING = null;
    public static EnchantType LUCK = null;
    public static EnchantType MANA_STEAL = null;
    public static EnchantType PROSECUTE = null;
    public static EnchantType SCAVENGER = null;
    public static EnchantType SHARPNESS = null;
    public static EnchantType SMITE = null;
    public static EnchantType SYPHON = null;
    public static EnchantType THUNDERBOLT = null;
    public static EnchantType THUNDERLORD = null;
    public static EnchantType TITAN_KILLER = null;
    public static EnchantType TRIPLE_STRIKE = null;
    public static EnchantType VAMPIRISM = null;
    public static EnchantType VENOMOUS = null;

    // Bows
    public static EnchantType AIMING = null;
    public static EnchantType CHANCE = null;
    public static EnchantType DRAGON_TRACER = null;
    public static EnchantType FLAME = null;
//    public static EnchantType INFINITE_QUIVER = null;
    public static EnchantType PIERCING = null;
    public static EnchantType OVERLOAD = null;
    public static EnchantType POWER = null;
    public static EnchantType PUNCH = null;
    public static EnchantType SNIPE = null;

    // Weapons
    public static EnchantType CUBISM = null;
    public static EnchantType DRAGON_HUNTER = null;
    public static EnchantType IMPALING = null;
    public static EnchantType VICIOUS = null;

    // Armor
    // Helmet
    public static EnchantType AQUA_AFFINITY = null;
    public static EnchantType BIG_BRAIN = null;
    public static EnchantType RESPIRATION = null;
    // Chestplate
    public static EnchantType COUNTER_STRIKE = null;
    public static EnchantType TRUE_PROTECTION = null;
    // Leggings
    public static EnchantType SMARTY_PANTS = null;
    // Boots
    public static EnchantType DEPTH_STRIDER = null;
    public static EnchantType FEATHER_FALLING = null;
    public static EnchantType FROST_WALKER = null;
    public static EnchantType SUGAR_RUSH = null;
    // All
    public static EnchantType BLAST_PROTECTION = null;
    public static EnchantType FIRE_PROTECTION = null;
    public static EnchantType GROWTH = null;
    public static EnchantType PROJECTILE_PROTECTION = null;
    public static EnchantType PROTECTION = null;
    public static EnchantType REJUVENATE = null;
    public static EnchantType RESPITE = null;

    // Tools
    public static EnchantType FORTUNE = null;
    public static EnchantType EFFICIENCY = null;

    // Hoes
    public static EnchantType REPLANT = null;

    // Not For Armors
    public static EnchantType TELEKINESIS = null;

    // Null
    public static EnchantType NULL = new EnchantType(0, "", new EnchantMultiplier(0), SkillRequirement.NULL);

    private final Stats stats; // dmg, str, cd, cc, as, fer, hp, def, speed, mana
    private final short maxLevel;
    private final String description;
    private final EnchantMultiplier multipliers;
    private final Requirements requirements;
    private final List<ItemType> types;
    private final ArrayList<String> distractions;
    private final boolean inEnchantingTable;

    EnchantType(Stats stats, int maxLevel , String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, ArrayList<String> distractions, boolean inEnchantingTable) {
        this.stats = stats;
        this.description = description;
        this.multipliers = multiplayers;
        if (!types.contains(ItemType.BOOK)) types.add(ItemType.BOOK);
        this.requirements = new Requirements(requirements);
        this.types = types;
        this.distractions = distractions;
        this.maxLevel = (short) maxLevel;
        this.inEnchantingTable = inEnchantingTable;
    }

    EnchantType(Stats stats, int maxLevel , String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, ArrayList<String> distractions) {
        this(stats, maxLevel, description, multiplayers, requirements, types, distractions, true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, ArrayList<String> distractions) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, types, distractions, true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, ArrayList<String> distractions, boolean inEnchantingTable) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, types, distractions, inEnchantingTable);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type, ArrayList<String> distractions) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), distractions, true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type, ArrayList<String> distractions, boolean inEnchantingTable) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), distractions, inEnchantingTable);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type, String distraction) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), new ArrayList<>(Collections.singletonList(distraction)), true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), new ArrayList<>(), true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type, boolean inEnchantingTable) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), new ArrayList<>(), inEnchantingTable);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, types, new ArrayList<>(), true);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, boolean inEnchantingTable) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, types, new ArrayList<>(), inEnchantingTable);
    }

    EnchantType(Stats stats, int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types) {
        this(stats, maxLevel, description, multiplayers, requirements, types, new ArrayList<>(), true);
    }

    EnchantType(Stats stats, int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, List<ItemType> types, boolean inEnchantingTable) {
        this(stats, maxLevel, description, multiplayers, requirements, types, new ArrayList<>(), inEnchantingTable);
    }

    EnchantType(Stats stats, int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type) {
        this(stats, maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), new ArrayList<>(), true);
    }

    EnchantType(Stats stats, int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements, ItemType type, boolean inEnchantingTable) {
        this(stats, maxLevel, description, multiplayers, requirements, new ArrayList<>(Collections.singletonList(type)), new ArrayList<>(), inEnchantingTable);
    }

    EnchantType(int maxLevel, String description, EnchantMultiplier multiplayers, SkillRequirement requirements) {
        this(new Stats(), maxLevel, description, multiplayers, requirements, new ArrayList<>(Arrays.asList(ItemType.values())), new ArrayList<>(), true);
    }

    public static void registerEnchants() {
        // Swords
        enchants.put("BANE_OF_ARTHROPODS", new EnchantType(5, "Increases damage dealt to Spiders, Cave Spiders and Silverfish by MULTIPLIER%", new EnchantMultiplier(10, 20, 30, 40, 60, 80, 100), SkillRequirement.NULL, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "SMITE"))));
        enchants.put("CRITICAL", new EnchantType(new Stats(0, 0, 10, 0, 0, 0, 0, 0, 0, 0), 5, "Increases " + StatTypes.CRIT_DAMAGE + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(10, 20, 30, 40, 50, 70, 100), new SkillRequirement(SkillType.ENCHANTING, 9), ItemType.SWORD));
        enchants.put("ENDER_SLAYER", new EnchantType(5, "Increases damage dealt to Ender Dragons and Endermen by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(10, 20, 30, 40, 60, 80, 100), new SkillRequirement(SkillType.ENCHANTING, 11), ItemType.SWORD));
        enchants.put("EXECUTE", new EnchantType(5, "Increases damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for each percent of " + StatTypes.HEALTH + " " + ChatColor.GRAY + "missing of your target.", new EnchantMultiplier(0.2, 0.4, 0.6, 0.8, 1, 1.25), new SkillRequirement(SkillType.ENCHANTING, 14), ItemType.SWORD, "PROSECUTE"));
        enchants.put("EXPERIENCE", new EnchantType(3, "Grants a " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GREEN + "chance for mobs and ores to drop double experience.", new EnchantMultiplier(12.5, 25, 37.5, 50), SkillRequirement.NULL, ItemType.SWORD));
        enchants.put("FIRST_STRIKE" , new EnchantType(4, "Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for the first hit on a mob.", new EnchantMultiplier(25, 50, 75, 100, 125), new SkillRequirement(SkillType.ENCHANTING, 10), ItemType.SWORD, "TRIPLE_STRIKE"));
        enchants.put("GIANT_KILLER", new EnchantType(5, "Increases damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for each percent of extra " + StatTypes.HEALTH + " " + ChatColor.GRAY + "that your target has above you up to " + ChatColor.GREEN + "50%" + ChatColor.GRAY + ".", new EnchantMultiplier(0.1, 0.2, 0.3, 0.4, 0.6, 0.9, 1.2), new SkillRequirement(SkillType.ENCHANTING, 8), ItemType.SWORD, "TITAN_KILLER"));
//        enchants.put("LETHALITY", new EnchantType(5, "Reduces the " + StatTypes.DEFENSE + " " + ChatColor.GRAY + "of your target by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for 4s seconds.", new EnchantMultiplier(1.2, 2.4, 3.6, 4.8, 6, 9), new SkillRequirement(SkillType.ENCHANTING, 14), ItemType.SWORD));
        enchants.put("LIFE_STEAL", new EnchantType(5, "Heals for " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "of your max " + StatTypes.HEALTH + " " + ChatColor.GRAY + "each time you hit a mob.", new EnchantMultiplier(0.5, 1, 1.5, 2, 2.5), new SkillRequirement(SkillType.ENCHANTING, 5), ItemType.SWORD, new ArrayList<>(Arrays.asList("SYPHON", "MANA_STEAL"))));
        enchants.put("LOOTING", new EnchantType(5, "Increases the amount of drops of a Monster by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 25), SkillRequirement.NULL, ItemType.SWORD));
        enchants.put("LUCK", new EnchantType(5, "Increases the chance of a Monster dropping their rare drops by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 25, 30, 35), new SkillRequirement(SkillType.ENCHANTING, 3), ItemType.SWORD));
        enchants.put("MANA_STEAL", new EnchantType(5, "Regain " + ChatColor.AQUA + "MULTIPLIER% " + ChatColor.GRAY + "of your " + StatTypes.MANA + " " + ChatColor.GRAY + "on hit.", new EnchantMultiplier(0.25, 0.5, 0.75), new SkillRequirement(SkillType.ENCHANTING, 20), ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "SYPHON")), false));
        enchants.put("PROSECUTE", new EnchantType(5, "Increases damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for each percent of " + StatTypes.HEALTH + " " + ChatColor.GRAY + "your target has.", new EnchantMultiplier(0.1, 0.2, 0.3, 0.4, 0.7, 1), new SkillRequirement(SkillType.ENCHANTING, 25), ItemType.SWORD, "EXECUTE"));
        enchants.put("SCAVENGER", new EnchantType(3, "Scavenge " + ChatColor.GOLD + "MULTIPLIER Coins " + ChatColor.GRAY + "per monster level on kill", new EnchantMultiplier(0.1, 0.2, 0.3, 0.5, 0.8), new SkillRequirement(SkillType.ENCHANTING, 1), ItemType.SWORD));
        enchants.put("SHARPNESS", new EnchantType(5, "Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 30, 45, 65), SkillRequirement.NULL, ItemType.SWORD, new ArrayList<>(Arrays.asList("SMITE", "BANE_OF_ARTHROPODS"))));
        enchants.put("SMITE", new EnchantType(5, "increases damage dealt to Skeletons, Zombies, Pigmen and Withers by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(10, 20, 30, 40, 60, 80, 100), SkillRequirement.NULL, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "BANE_OF_ARTHROPODS"))));
        enchants.put("SYPHON", new EnchantType(3, "Heals for " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "of your max " + StatTypes.HEALTH + " " + ChatColor.GRAY + "per " + ChatColor.BLUE + "100 " + StatTypes.CRIT_DAMAGE + " " + ChatColor.GRAY + "you deal per hit, up to " + ChatColor.BLUE + "1,000 " + StatTypes.CRIT_DAMAGE + ChatColor.GRAY + ".", new EnchantMultiplier(0.2, 0.3, 0.4, 0.5, 0.6), new SkillRequirement(SkillType.ENCHANTING, 15), ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "MANA_STEAL"))));
        enchants.put("TITAN_KILLER", new EnchantType(5, "Increases damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "for every " + ChatColor.GREEN + "100 " + StatTypes.DEFENSE + " " + ChatColor.GRAY + "your target has up to " + ChatColor.GREEN + "60%" + ChatColor.GRAY + ".", new EnchantMultiplier(2, 4, 6, 8, 12, 16, 20), new SkillRequirement(SkillType.ENCHANTING, 28), ItemType.SWORD, "GIANT_KILLER"));
        enchants.put("TRIPLE_STRIKE", new EnchantType(4, "Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "if the mob has more than " + ChatColor.GREEN + "67% " + ChatColor.GRAY + "health.", new EnchantMultiplier(10, 20, 30, 40, 50), new SkillRequirement(SkillType.ENCHANTING, 19), ItemType.SWORD, "FIRST_STRIKE"));
        enchants.put("VAMPIRISM", new EnchantType(5, "Heals for " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "of your missing " + StatTypes.HEALTH + " " + ChatColor.GRAY + "whenever you kill an enemy.", new EnchantMultiplier(1, 2, 3, 4, 5, 6), new SkillRequirement(SkillType.ENCHANTING, 15), ItemType.SWORD));

        // Bow
        enchants.put("CHANCE", new EnchantType(5, "Increases the amount of drops of a Monster by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 25), new SkillRequirement(SkillType.ENCHANTING, 11), ItemTypeGroup.BOWS.toType()));
        enchants.put("AIMING", new EnchantType(5, "Arrows home towards Ender Dragons if they are within " + ChatColor.GREEN + "MULTIPLIER " + ChatColor.GRAY + "blocks.", new EnchantMultiplier(2, 4, 6, 8, 10), new SkillRequirement(SkillType.ENCHANTING, 8), ItemTypeGroup.BOWS.toType()));
//        enchants.put("INFINITE_QUIVER", new EnchantType(5, "Saves arrows " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "of the time when you fire your bow.", new EnchantMultiplier(6, 12, 18, 24, 30), new SkillRequirement(SkillType.ENCHANTING, 2), ItemTypeGroup.BOWS.toType()));
//        enchants.put("OVERLOAD", new EnchantType(5, "Having a " + StatTypes.CRIT_CHANCE + " " + ChatColor.GRAY + "above 100% grants a chance to perform a Mega Critical Hit dealing " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "extra damage.", new EnchantMultiplayer(15, 30, 45, 60, 75), new SkillRequirement(SkillType.ENCHANTING, 33), ItemType.BOW, false));
        enchants.put("POWER", new EnchantType(5, "Increases bow damage by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(8, 16, 24, 32, 40, 50, 65), SkillRequirement.NULL, ItemTypeGroup.BOWS.toType()));
        enchants.put("SNIPE", new EnchantType(3, "Increases bow damage by " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "damage for every " + ChatColor.GREEN + "10 " + ChatColor.GRAY + "blocks travelled.", new EnchantMultiplier(1, 2, 3, 4), new SkillRequirement(SkillType.ENCHANTING, 6), ItemTypeGroup.BOWS.toType()));

        // Weapons
        enchants.put("CUBISM", new EnchantType(5, "Increases damage dealt to Magma Cubes, Slimes and Creeper by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(10, 20, 30, 40, 60, 80), new SkillRequirement(SkillType.ENCHANTING, 3), ItemTypeGroup.WEAPON.toType()));
        enchants.put("DRAGON_HUNTER", new EnchantType(5, "Increases damage dealt to Ender Dragons by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(8, 16, 24, 32, 40), new SkillRequirement(SkillType.ENCHANTING, 16), ItemTypeGroup.WEAPON.toType(), false));
        enchants.put("IMPALING", new EnchantType(3, "Increases damage dealt to Guardians and Squids by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(25, 50, 75), new SkillRequirement(SkillType.ENCHANTING, 12), ItemTypeGroup.WEAPON.toType()));
        enchants.put("VICIOUS", new EnchantType(new Stats(0, 0, 0, 0, 0, 1, 0, 0, 0, 0), 5, "Grants " + ChatColor.RED + "+MULTIPLIER" + StatTypes.FEROCITY + ChatColor.GRAY + ".", new EnchantMultiplier(3, 4, 5), new SkillRequirement(SkillType.ENCHANTING, 26), ItemTypeGroup.WEAPON.toType(), false));

        // Armor
        // Helmet
        enchants.put("BIG_BRAIN", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 5), 5, "Grants " + ChatColor.AQUA + "MULTIPLIER" + StatTypes.INTELLIGENCE, new EnchantMultiplier(15, 20, 25), new SkillRequirement(SkillType.ENCHANTING, 21), ItemType.HELMET, false));
        enchants.put("RESPIRATION", new EnchantType(3, "Extends your underwater breathing time by " + ChatColor.GREEN + "MULTIPLIER" + ChatColor.GRAY + ".", new EnchantMultiplier(15, 30, 45), SkillRequirement.NULL, ItemType.HELMET));
        // Chestplate
        enchants.put("COUNTER_STRIKE", new EnchantType(5, "Gain " + ChatColor.GREEN + "+10" + StatTypes.DEFENSE + " " + ChatColor.GRAY + "for " + ChatColor.GREEN + "7s " + ChatColor.GRAY + "on the first hit from an enemy.", new EnchantMultiplier(10, 10, 10, 10, 10), new SkillRequirement(SkillType.ENCHANTING, 22), ItemType.CHESTPLATE, false));
        enchants.put("TRUE_PROTECTION", new EnchantType(1, "Grants " + ChatColor.WHITE + "+MULTIPLIER" + StatTypes.TRUE_DEFENSE + ChatColor.GRAY + ".", new EnchantMultiplier(5), new SkillRequirement(SkillType.ENCHANTING, 15), ItemType.CHESTPLATE, false));
        // Leggings
        enchants.put("SMARTY_PANTS", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 5), 5, "Grants " + ChatColor.AQUA + "MULTIPLIER" + StatTypes.INTELLIGENCE + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 25), new SkillRequirement(SkillType.ENCHANTING, 21), ItemType.LEGGINGS, false));
        // Boots
//        enchants.put("DEPTH_STRIDER", new EnchantType(3, "", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("FROST_WALKER"))));
        enchants.put("FEATHER_FALLING", new EnchantType(4, "Reduces fall damage by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(5, 10, 15, 20, 25), SkillRequirement.NULL, ItemType.BOOTS));
        enchants.put("FROST_WALKER", new EnchantType(2, "Ice blocks will be created below you when you walk above water in radius of " + ChatColor.GREEN + "MULTIPLIER " + ChatColor.GRAY + "blocks.", new EnchantMultiplier(1, 2), SkillRequirement.NULL, ItemType.BOOTS, new ArrayList<>(Collections.singletonList("DEPTH_STRIDER"))));
        enchants.put("SUGAR_RUSH", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 2, 0), 3, "Grants " + ChatColor.WHITE + "+MULTIPLIER" + StatTypes.SPEED + ChatColor.GRAY + ".", new EnchantMultiplier(4, 8, 12), new SkillRequirement(SkillType.ENCHANTING, 7), ItemType.BOOTS));
        // All Armors
        enchants.put("BLAST_PROTECTION", new EnchantType(5, "Grants " + ChatColor.GREEN + "+MULTIPLIER" + StatTypes.DEFENSE + " " + ChatColor.GRAY + "against explosions.", new EnchantMultiplier(30, 60, 90, 120 ,150, 180, 210), SkillRequirement.NULL, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("FIRE_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        enchants.put("FIRE_PROTECTION", new EnchantType(5, "Grants " + ChatColor.WHITE + "+MULTIPLIER" + StatTypes.TRUE_DEFENSE + " " + ChatColor.GRAY + "against fire and lava.", new EnchantMultiplier(2, 4, 6, 8, 10, 12, 14), SkillRequirement.NULL, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        enchants.put("GROWTH", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 15, 0, 0, 0), 5, "Grants " + ChatColor.RED + "+MULTIPLIER" + StatTypes.HEALTH, new EnchantMultiplier(15, 30, 45, 60, 75, 105), new SkillRequirement(SkillType.ENCHANTING, 5), ItemTypeGroup.ARMOR.toType()));
        enchants.put("PROJECTILE_PROTECTION", new EnchantType(5, "Grants " + ChatColor.GREEN + "+MULTIPLIER" + StatTypes.DEFENSE + " " + ChatColor.GRAY + "against projectiles.", new EnchantMultiplier(7, 14, 21, 28, 35, 42, 49), SkillRequirement.NULL, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROTECTION"))));
        enchants.put("PROTECTION", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 3, 0, 0), 5, "Grants " + ChatColor.GREEN + "+MULTIPLIER" + StatTypes.DEFENSE + ChatColor.GRAY + ".", new EnchantMultiplier(3, 6, 9, 12, 15, 18, 21), SkillRequirement.NULL, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROJECTILE_PROTECTION"))));
        enchants.put("REJUVENATE", new EnchantType(5, "Increases your " + StatTypes.VITALITY + " by " + ChatColor.GREEN + "+MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(2, 4, 6, 8, 10), new SkillRequirement(SkillType.ENCHANTING, 10), ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Collections.singletonList("RESPITE")), false));

        enchants.put("FORTUNE", new EnchantType(new Stats(0, 15), 3, "Grants " + ChatColor.GOLD + "+MULTIPLIER" + StatTypes.MINING_FORTUNE + ChatColor.GRAY + ".", new EnchantMultiplier(15, 30, 45, 60), SkillRequirement.NULL, ItemTypeGroup.MINING.toType()));

        enchants.put("REPLANT", new EnchantType(new Stats(0, 0), 1, "Replants crops when you break them", new EnchantMultiplier(0), SkillRequirement.NULL, ItemTypeGroup.FARMING.toType()));

        enchants.put("EFFICIENCY", new EnchantType(new Stats(25, 0), 3, "Grants " + ChatColor.GOLD + "+MULTIPLIER" + StatTypes.MINING_SPEED + ChatColor.GRAY + ".", new EnchantMultiplier(25, 50, 75, 100, 125, 150, 175, 200, 225, 250), SkillRequirement.NULL, ItemTypeGroup.MINING.toType()));

        // Not For Armors
        enchants.put("TELEKINESIS", new EnchantType(1, "Block and mob drops go directly into your inventory.", new EnchantMultiplier(0), SkillRequirement.NULL, Arrays.stream(ItemType.values()).collect(Collectors.toList())));

        // Ultimate Enchants
        enchants.put("CHIMERA", new UltimateEnchantType(5, "Copies " + ChatColor.GREEN + "MULTIPLIER% " + ChatColor.GRAY + "of your active pet's stats.", new EnchantMultiplier(20, 40, 60, 80, 100), new SkillRequirement(SkillType.ENCHANTING, 31), new ArrayList<>(Collections.singletonList(ItemType.SWORD)), "CHIMERA"));
        enchants.put("ONE_FOR_ALL", new UltimateEnchantType(1, "Removes all other enchants but increases your weapon damage by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(500), new SkillRequirement(SkillType.ENCHANTING, 37), new ArrayList<>(Collections.singletonList(ItemType.SWORD)), "ONE_FOR_ALL"));
        enchants.put("ULTIMATE_WISE", new UltimateEnchantType(5, "Reduces the ability mana cost of this item by " + ChatColor.GREEN + "MULTIPLIER%" + ChatColor.GRAY + ".", new EnchantMultiplier(10, 20, 30, 40, 50), new SkillRequirement(SkillType.ENCHANTING, 20), new ArrayList<>(Collections.singletonList(ItemType.SWORD)), "ULTIMATE_WISE"));

        BANE_OF_ARTHROPODS = enchants.get("BANE_OF_ARTHROPODS");
        CRITICAL = enchants.get("CRITICAL");
        ENDER_SLAYER = enchants.get("ENDER_SLAYER");
        EXECUTE = enchants.get("EXECUTE");
        EXPERIENCE = enchants.get("EXPERIENCE");
        FIRST_STRIKE = enchants.get("TRIPLE_STRIKE");
        GIANT_KILLER = enchants.get("TITAN_KILLER");
        LETHALITY = enchants.get("LETHALITY");
        LIFE_STEAL = enchants.get("LIFE_STEAL");
        LOOTING = enchants.get("LOOTING");
        LUCK = enchants.get("LUCK");
        MANA_STEAL = enchants.get("MANA_STEAL");
        PROSECUTE = enchants.get("PROSECUTE");
        SCAVENGER = enchants.get("SCAVENGER");
        SHARPNESS = enchants.get("SHARPNESS");
        SMITE = enchants.get("SMITE");
        SYPHON = enchants.get("SYPHON");
        TITAN_KILLER = enchants.get("TITAN_KILLER");
        TRIPLE_STRIKE = enchants.get("TRIPLE_STRIKE");
        VAMPIRISM = enchants.get("VAMPIRISM");
        VENOMOUS = enchants.get("VENOMOUS");

        AIMING = enchants.get("AIMING");
        CHANCE = enchants.get("CHANCE");
        DRAGON_TRACER = enchants.get("DRAGON_TRACER");
        FLAME = enchants.get("FLAME");
//        INFINITE_QUIVER = enchants.get("INFINITE_QUIVER");
        PIERCING = enchants.get("PIERCING");
        OVERLOAD = enchants.get("OVERLOAD");
        POWER = enchants.get("POWER");
        PUNCH = enchants.get("PUNCH");
        SNIPE = enchants.get("SNIPE");

        CUBISM = enchants.get("CUBISM");
        DRAGON_HUNTER = enchants.get("DRAGON_HUNTER");
        IMPALING = enchants.get("IMPALING");
        VICIOUS = enchants.get("VICIOUS");

        AQUA_AFFINITY = enchants.get("AQUA_AFFINITY");
        BIG_BRAIN = enchants.get("BIG_BRAIN");
        RESPIRATION = enchants.get("RESPIRATION");

        COUNTER_STRIKE = enchants.get("COUNTER_STRIKE");
        TRUE_PROTECTION = enchants.get("TRUE_PROTECTION");

        SMARTY_PANTS = enchants.get("SMARTY_PANTS");

        DEPTH_STRIDER = enchants.get("DEPTH_STRIDER");
        FEATHER_FALLING = enchants.get("FEATHER_FALLING");
        FROST_WALKER = enchants.get("FROST_WALKER");
        SUGAR_RUSH = enchants.get("SUGAR_RUSH");

        BLAST_PROTECTION = enchants.get("BLAST_PROTECTION");
        FIRE_PROTECTION = enchants.get("FIRE_PROTECTION");
        GROWTH = enchants.get("GROWTH");
        PROJECTILE_PROTECTION = enchants.get("PROJECTILE_PROTECTION");
        PROTECTION = enchants.get("PROTECTION");
        REJUVENATE = enchants.get("REJUVENATE");
        RESPITE = enchants.get("RESPITE");

        FORTUNE = enchants.get("FORTUNE");
        EFFICIENCY = enchants.get("EFFICIENCY");

        REPLANT = enchants.get("REPLANT");

        TELEKINESIS = enchants.get("TELEKINESIS");

        CHIMERA = (UltimateEnchantType) enchants.get("CHIMERA");
        ONE_FOR_ALL = (UltimateEnchantType) enchants.get("ONE_FOR_ALL");
        ULTIMATE_WISE = (UltimateEnchantType) enchants.get("ULTIMATE_WISE");
    }

    public String getDescription(int level) {
        return this.getDescription().replace("MULTIPLIER", this.getMultipliers().get(level) + "");
    }

    public String name() {
        for (String key : enchants.keySet()) {
            if (enchants.get(key) == this) {
                String output = key.replaceAll(" ", "");
                output = output.replaceAll("AAA", "");
                return output;
            }
        }
        return "";
    }

    public String getRealName() {
        for (String key : enchants.keySet()) {
            if (enchants.get(key) == this) {
                return key.replaceAll(" ", "");
            }
        }
        return "";
    }

    public static EnchantType valueOf(String name) {
        name = name.replaceAll("AAA", "");
        for (EnchantType enchantType : enchants.values()) {
            if (enchantType.name().equals(name.toUpperCase())) {
                return enchantType;
            }
        }
        return EnchantType.NULL;
    }

    @Override
    public String toString() {
        for (String key : enchants.keySet()) {
            if (enchants.get(key) == this) {
                String output = key.replaceAll("_", " ");
                output = output.replaceAll("AAA", "");
                return setTitleCase(output);
            }
        }
        return "";
    }

    @Override
    public int compareTo(EnchantType e) {
        int size = this.name().compareTo(e.name());
        if (this instanceof UltimateEnchantType) {
            size -= 100_000;
        } else if (e instanceof UltimateEnchantType) {
            size += 100_000;
        }
        return size;
    }
}
