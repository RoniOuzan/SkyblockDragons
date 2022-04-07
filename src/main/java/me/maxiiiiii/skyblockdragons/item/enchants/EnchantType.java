package me.maxiiiiii.skyblockdragons.item.enchants;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemTypeGroup;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static me.maxiiiiii.skyblockdragons.util.Functions.setTitleCase;

@Getter
public class EnchantType {
    public static HashMap<String, EnchantType> enchants = new HashMap<>();

    // Ultimate Enchants
    public static UltimateEnchantType CHIMERA = (UltimateEnchantType) enchants.get("AAACHIMERA");
    public static UltimateEnchantType ONE_FOR_ALL = (UltimateEnchantType) enchants.get("AAAONE_FOR_ALL");
    public static UltimateEnchantType ULTIMATE_WISE = (UltimateEnchantType) enchants.get("AAAULTIMATE_WISE");
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
    public static EnchantType CHANCE = null;
    public static EnchantType DRAGON_TRACER = null;
    public static EnchantType FLAME = null;
    public static EnchantType INFINITE_QUIVER = null;
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

    // Not For Armors
    public static EnchantType TELEKINESIS = null;

    // Null
    public static EnchantType NULL = new EnchantType(0, "", 1);

    private final Stats stats; // dmg, str, cd, cc, as, fer, hp, def, speed, mana
    private final short maxLevel;
    private final String description;
    private final double multiplayer;
    private final ArrayList<ItemType> types;
    private final ArrayList<String> distractions;

    EnchantType(Stats stats, int maxLevel , String description, double multiplayer, ArrayList<ItemType> types, ArrayList<String> distractions) {
        this.stats = stats;
        this.description = description;
        this.multiplayer = multiplayer;
        if (!types.contains(ItemType.BOOK)) types.add(ItemType.BOOK);
        this.types = types;
        this.distractions = distractions;
        this.maxLevel = (short) maxLevel;
    }

    EnchantType(int maxLevel, String description, double multiplayer, ArrayList<ItemType> types, ArrayList<String> distractions) {
        this(new Stats(), maxLevel, description, multiplayer, types, distractions);
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type, ArrayList<String> distractions) {
        this(new Stats(), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), distractions);
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type, String distraction) {
        this(new Stats(), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>(Arrays.asList(distraction)));
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type) {
        this(new Stats(), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>());
    }

    EnchantType(int maxLevel, String description, double multiplayer, ArrayList<ItemType> types) {
        this(new Stats(), maxLevel, description, multiplayer, types, new ArrayList<>());
    }

    EnchantType(Stats stats, int maxLevel, String description, double multiplayer, ArrayList<ItemType> types) {
        this(stats, maxLevel, description, multiplayer, types, new ArrayList<>());
    }

    EnchantType(Stats stats, int maxLevel, String description, double multiplayer, ItemType type) {
        this(stats, maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>());
    }

    EnchantType(int maxLevel, String description, double multiplayer) {
        this(new Stats(), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(ItemType.values())), new ArrayList<>());
    }

    public static void registerEnchants() {
        // Swords
        enchants.put("BANE_OF_ARTHROPODS", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "SMITE"))));
        enchants.put("CLEAVE", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("CRITICAL", new EnchantType(new Stats(0, 0, 10, 0, 0, 0, 0, 0, 0, 0), 5, "", 0, ItemType.SWORD));
        enchants.put("ENDER_SLAYER", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("EXECUTE", new EnchantType(5, "", 0, ItemType.SWORD, "PROSECUTE"));
        enchants.put("EXPERIENCE", new EnchantType(3, "", 0, ItemType.SWORD));
        enchants.put("FIRE_ASPECT", new EnchantType(2, "", 0, ItemType.SWORD));
        enchants.put("FIRST_STRIKE" , new EnchantType(4, "", 0, ItemType.SWORD, "TRIPLE_STRIKE"));
        enchants.put("GIANT_KILLER", new EnchantType(5, "", 0, ItemType.SWORD, "TITAN_KILLER"));
        enchants.put("KNOCKBACK", new EnchantType(2, "", 0, ItemType.SWORD));
        enchants.put("LETHALITY", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("LIFE_STEAL", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SYPHON", "MANA_STEAL"))));
        enchants.put("LOOTING", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("LUCK", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("MANA_STEAL", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "SYPHON"))));
        enchants.put("PROSECUTE", new EnchantType(5, "", 0, ItemType.SWORD, "EXECUTE"));
        enchants.put("SCAVENGER", new EnchantType(3, "", 0, ItemType.SWORD));
        enchants.put("SHARPNESS", new EnchantType(5, "Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLAYER% " + ChatColor.GRAY + ".", 5, ItemType.SWORD, new ArrayList<>(Arrays.asList("SMITE", "BANE_OF_ARTHROPODS"))));
        enchants.put("SMITE", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "BANE_OF_ARTHROPODS"))));
        enchants.put("SYPHON", new EnchantType(3, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "MANA_STEAL"))));
        enchants.put("THUNDERBOLT", new EnchantType(5, "", 0, ItemType.SWORD, "THUNDERLORD"));
        enchants.put("THUNDERLORD", new EnchantType(5, "", 0, ItemType.SWORD, "THUNDERBOLT"));
        enchants.put("TITAN_KILLER", new EnchantType(5, "", 0, ItemType.SWORD, "GIANT_KILLER"));
        enchants.put("TRIPLE_STRIKE", new EnchantType(4, "", 0, ItemType.SWORD, "FIRST_STRIKE"));
        enchants.put("VAMPIRISM", new EnchantType(5, "", 0, ItemType.SWORD));
        enchants.put("VENOMOUS", new EnchantType(5, "", 0, ItemType.SWORD));

        // Bow
        enchants.put("CHANCE", new EnchantType(5, "", 0, ItemType.BOW));
        enchants.put("DRAGON_TRACER", new EnchantType(5, "", 0, ItemType.BOW));
        enchants.put("FLAME", new EnchantType(1, "", 0, ItemType.BOW));
        enchants.put("INFINITE_QUIVER", new EnchantType(5, "", 0, ItemType.BOW));
        enchants.put("PIERCING", new EnchantType(1, "", 0, ItemType.BOW));
        enchants.put("OVERLOAD", new EnchantType(5, "", 0, ItemType.BOW));
        enchants.put("POWER", new EnchantType(5, "", 0, ItemType.BOW));
        enchants.put("PUNCH", new EnchantType(2, "", 0, ItemType.BOW));
        enchants.put("SNIPE", new EnchantType(3, "", 0, ItemType.BOW));

        // Weapons
        enchants.put("CUBISM", new EnchantType(5, "", 0, ItemTypeGroup.WEAPON.toType()));
        enchants.put("DRAGON_HUNTER", new EnchantType(5, "", 0, ItemTypeGroup.WEAPON.toType()));
        enchants.put("IMPALING", new EnchantType(3, "", 0, ItemTypeGroup.WEAPON.toType()));
        enchants.put("VICIOUS", new EnchantType(new Stats(0, 0, 0, 0, 0, 1, 0, 0, 0, 0), 5, "", 0, ItemTypeGroup.WEAPON.toType()));

        // Armor
        // Helmet
        enchants.put("AQUA_AFFINITY", new EnchantType(1, "", 0, ItemType.HELMET));
        enchants.put("BIG_BRAIN", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 5), 5, "", 0, ItemType.HELMET));
        enchants.put("RESPIRATION", new EnchantType(3, "", 0, ItemType.HELMET));
        // Chestplate
        enchants.put("COUNTER_STRIKE", new EnchantType(5, "", 0, ItemType.CHESTPLATE));
        enchants.put("TRUE_PROTECTION", new EnchantType(1, "", 0, ItemType.CHESTPLATE));
        // Leggings
        enchants.put("SMARTY_PANTS", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 5), 5, "", 0, ItemType.LEGGINGS));
        // Boots
        enchants.put("DEPTH_STRIDER", new EnchantType(3, "", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("FROST_WALKER"))));
        enchants.put("FEATHER_FALLING", new EnchantType(4, "", 0, ItemType.BOOTS));
        enchants.put("FROST_WALKER", new EnchantType(2, "", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("DEPTH_STRIDER"))));
        enchants.put("SUGAR_RUSH", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 2, 0), 3, "", 0, ItemType.BOOTS));
        // All Armors
        enchants.put("BLAST_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("FIRE_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        enchants.put("FIRE_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        enchants.put("GROWTH", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 15, 0, 0, 0), 5, "", 0, ItemTypeGroup.ARMOR.toType()));
        enchants.put("PROJECTILE_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROTECTION"))));
        enchants.put("PROTECTION", new EnchantType(new Stats(0, 0, 0, 0, 0, 0, 0, 3, 0, 0), 5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROJEC)TILE_PROTECTION"))));
        enchants.put("REJUVENATE", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("RESPITE"))));
        enchants.put("RESPITE", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("REJUVENATE"))));

        enchants.put("FORTUNE", new EnchantType(3, "", 5, ItemTypeGroup.MINING.toType()));

        // Not For Armors
        enchants.put("TELEKINESIS", new EnchantType(1, "", 0, new ArrayList<>(Arrays.asList(ItemType.ACCESSORY, ItemType.SWORD, ItemType.AXE, ItemType.ITEM, ItemType.BOW, ItemType.PICKAXE, ItemType.ROD, ItemType.WAND, ItemType.HOE))));

        // Ultimate Enchants
        enchants.put("AAACHIMERA", new UltimateEnchantType(5, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "CHIMERA"));
        enchants.put("AAAONE_FOR_ALL", new UltimateEnchantType(1, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "ONE_FOR_ALL"));
        enchants.put("AAAULTIMATE_WISE", new UltimateEnchantType(5, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "ULTIMATE_WISE"));

        BANE_OF_ARTHROPODS = enchants.get("BANE_OF_ARTHROPODS");
        CLEAVE = enchants.get("CLEAVE");
        CRITICAL = enchants.get("CRITICAL");
        ENDER_SLAYER = enchants.get("ENDER_SLAYER");
        EXECUTE = enchants.get("EXECUTE");
        EXPERIENCE = enchants.get("EXPERIENCE");
        FIRE_ASPECT = enchants.get("FIRE_ASPECT");
        FIRST_STRIKE = enchants.get("TRIPLE_STRIKE");
        GIANT_KILLER = enchants.get("TITAN_KILLER");
        KNOCKBACK = enchants.get("KNOCKBACK");
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
        THUNDERBOLT = enchants.get("THUNDERLORD");
        THUNDERLORD = enchants.get("THUNDERBOLT");
        TITAN_KILLER = enchants.get("TITAN_KILLER");
        TRIPLE_STRIKE = enchants.get("TRIPLE_STRIKE");
        VAMPIRISM = enchants.get("VAMPIRISM");
        VENOMOUS = enchants.get("VENOMOUS");
        CHANCE = enchants.get("CHANCE");
        DRAGON_TRACER = enchants.get("DRAGON_TRACER");
        FLAME = enchants.get("FLAME");
        INFINITE_QUIVER = enchants.get("INFINITE_QUIVER");
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
        TELEKINESIS = enchants.get("TELEKINESIS");
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

    public String realname() {
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
}
