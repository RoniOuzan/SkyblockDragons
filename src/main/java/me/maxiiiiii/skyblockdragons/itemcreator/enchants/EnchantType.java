package me.maxiiiiii.skyblockdragons.itemcreator.enchants;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemType;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.ItemTypeGroup;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static me.maxiiiiii.skyblockdragons.util.Functions.setTitleCase;

@Getter
public class EnchantType {
//    // Swords
//    BANE_OF_ARTHROPODS = new EnchantType("", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "SMITE"))),
//    CLEAVE = new EnchantType("", 0, ItemType.SWORD),
//    CRITICAL(new ArrayList<>(Arrays.asList(0, 0, 10, 0, 0, 0, 0, 0, 0, 0)), "", 0, ItemType.SWORD),
//    ENDER_SLAYER = new EnchantType("", 0, ItemType.SWORD),
//    EXECUTE = new EnchantType("", 0, ItemType.SWORD, "PROSECUTE"),
//    EXPERIENCE = new EnchantType("", 0, ItemType.SWORD),
//    FIRE_ASPECT = new EnchantType("", 0, ItemType.SWORD),
//    FIRST_STRIKE = new EnchantType("", 0, ItemType.SWORD, "TRIPLE_STRIKE"),
//    GIANT_KILLER = new EnchantType("", 0, ItemType.SWORD, "TITAN_KILLER"),
//    KNOCKBACK = new EnchantType("", 0, ItemType.SWORD),
//    LETHALITY = new EnchantType("", 0, ItemType.SWORD),
//    LIFE_STEAL = new EnchantType("", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SYPHON", "MANA_STEAL"))),
//    LOOTING = new EnchantType("", 0, ItemType.SWORD),
//    LUCK = new EnchantType("", 0, ItemType.SWORD),
//    MANA_STEAL = new EnchantType("", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "SYPHON"))),
//    PROSECUTE = new EnchantType("", 0, ItemType.SWORD, "EXECUTE"),
//    SCAVENGER = new EnchantType("", 0, ItemType.SWORD),
//    SHARPNESS("Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLAYER% " + ChatColor.GRAY + ".", 5, ItemType.SWORD, new ArrayList<>(Arrays.asList("SMITE", "BANE_OF_ARTHROPODS"))),
//    SMITE = new EnchantType("", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "BANE_OF_ARTHROPODS"))),
//    SYPHON = new EnchantType("", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "MANA_STEAL"))),
//    THUNDERBOLT = new EnchantType("", 0, ItemType.SWORD, "THUNDERLORD"),
//    THUNDERLORD = new EnchantType("", 0, ItemType.SWORD, "THUNDERBOLT"),
//    TITAN_KILLER = new EnchantType("", 0, ItemType.SWORD, "GIANT_KILLER"),
//    TRIPLE_STRIKE = new EnchantType("", 0, ItemType.SWORD, "FIRST_STRIKE"),
//    VAMPIRISM = new EnchantType("", 0, ItemType.SWORD),
//    VENOMOUS = new EnchantType("", 0, ItemType.SWORD),
//    VICIOUS(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)), "", 0, ItemType.SWORD),
//
//    // Weapons
//    CUBISM("", 0, ItemTypeGroup.WEAPON.toType()),
//    DRAGON_HUNTER("", 0, ItemTypeGroup.WEAPON.toType()),
//    IMPALING("", 0, ItemTypeGroup.WEAPON.toType()),
//
//    // Bows
//    CHANCE("", 0, ItemType.BOW),
//    DRAGON_TRACER("", 0, ItemType.BOW),
//    FLAME("", 0, ItemType.BOW),
//    INFINITE_QUIVER("", 0, ItemType.BOW),
//    PIERCING("", 0, ItemType.BOW),
//    OVERLOAD("", 0, ItemType.BOW),
//    POWER("", 0, ItemType.BOW),
//    PUNCH("", 0, ItemType.BOW),
//    SNIPE("", 0, ItemType.BOW),
//
//    // Armor
//    // Helmet
//    AQUA_AFFINITY("", 0, ItemType.HELMET),
//    BIG_BRAIN(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 5)), "", 0, ItemType.HELMET),
//    RESPIRATION("", 0, ItemType.HELMET),
//    // Chestplate
//    COUNTER_STRIKE("", 0, ItemType.CHESTPLATE),
//    TRUE_PROTECTION("", 0, ItemType.CHESTPLATE),
//    // Leggings
//    SMARTY_PANTS(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 5)), "", 0, ItemType.LEGGINGS),
//    // Boots
//    DEPTH_STRIDER("", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("FROST_WALKER"))),
//    FEATHER_FALLING("", 0, ItemType.BOOTS),
//    FROST_WALKER("", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("DEPTH_STRIDER"))),
//    SUGAR_RASH(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 2, 0)), "", 0, ItemType.BOOTS),
//    // All
//    BLAST_PROTECTION("", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("FIRE_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))),
//    FIRE_PROTECTION("", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))),
//    GROWTH(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 15, 0, 0, 0)), "", 0, ItemTypeGroup.ARMOR.toType()),
//    PROJECTILE_PROTECTION("", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROTECTION"))),
//    PROTECTION(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 3, 0, 0)), "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROJECTILE_PROTECTION"))),
//    REJUVENATE("", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("RESPITE"))),
//    RESPITE("", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("REJUVENATE"))),
//    THORNS("", 0, ItemTypeGroup.ARMOR.toType()),
//
//    // All
//    TELEKINESIS("", 0, new ArrayList<>(Arrays.asList(ItemType.ACCESSORY, ItemType.SWORD, ItemType.AXE, ItemType.ITEM, ItemType.BOW, ItemType.PICKAXE, ItemType.ROD, ItemType.WAND, ItemType.HOE))),
//
//    // Null
//    NULL("", 1);
    public static HashMap<String, EnchantType> Enchants = new HashMap<>();

    // Ultimate Enchants
    public static final UltimateEnchantType CHIMERA = (UltimateEnchantType) Enchants.get("AAACHIMERA");
    public static final UltimateEnchantType ONE_FOR_ALL = (UltimateEnchantType) Enchants.get("AAAONE_FOR_ALL");
    public static final UltimateEnchantType ULTIMATE_WISE = (UltimateEnchantType) Enchants.get("AAAULTIMATE_WISE");
    // Swords
    public static final EnchantType BANE_OF_ARTHROPODS = Enchants.get("BANE_OF_ARTHROPODS");
    public static final EnchantType CLEAVE = Enchants.get("CLEAVE");
    public static final EnchantType CRITICAL = Enchants.get("CRITICAL");
    public static final EnchantType ENDER_SLAYER = Enchants.get("ENDER_SLAYER");
    public static final EnchantType EXECUTE = Enchants.get("EXECUTE");
    public static final EnchantType EXPERIENCE = Enchants.get("EXPERIENCE");
    public static final EnchantType FIRE_ASPECT = Enchants.get("FIRE_ASPECT");
    public static final EnchantType FIRST_STRIKE = Enchants.get("TRIPLE_STRIKE");
    public static final EnchantType GIANT_KILLER = Enchants.get("TITAN_KILLER");
    public static final EnchantType KNOCKBACK = Enchants.get("KNOCKBACK");
    public static final EnchantType LETHALITY = Enchants.get("LETHALITY");
    public static final EnchantType LIFE_STEAL = Enchants.get("LIFE_STEAL");
    public static final EnchantType LOOTING = Enchants.get("LOOTING");
    public static final EnchantType LUCK = Enchants.get("LUCK");
    public static final EnchantType MANA_STEAL = Enchants.get("MANA_STEAL");
    public static final EnchantType PROSECUTE = Enchants.get("PROSECUTE");
    public static final EnchantType SCAVENGER = Enchants.get("SCAVENGER");
    public static final EnchantType SHARPNESS = Enchants.get("SHARPNESS");
    public static final EnchantType SMITE = Enchants.get("SMITE");
    public static final EnchantType SYPHON = Enchants.get("SYPHON");
    public static final EnchantType THUNDERBOLT = Enchants.get("THUNDERLORD");
    public static final EnchantType THUNDERLORD = Enchants.get("THUNDERBOLT");
    public static final EnchantType TITAN_KILLER = Enchants.get("TITAN_KILLER");
    public static final EnchantType TRIPLE_STRIKE = Enchants.get("TRIPLE_STRIKE");
    public static final EnchantType VAMPIRISM = Enchants.get("VAMPIRISM");
    public static final EnchantType VENOMOUS = Enchants.get("VENOMOUS");

    // Bows
    public static final EnchantType CHANCE = Enchants.get("CHANCE");
    public static final EnchantType DRAGON_TRACER = Enchants.get("DRAGON_TRACER");
    public static final EnchantType FLAME = Enchants.get("FLAME");
    public static final EnchantType INFINITE_QUIVER = Enchants.get("INFINITE_QUIVER");
    public static final EnchantType PIERCING = Enchants.get("PIERCING");
    public static final EnchantType OVERLOAD = Enchants.get("OVERLOAD");
    public static final EnchantType POWER = Enchants.get("POWER");
    public static final EnchantType PUNCH = Enchants.get("PUNCH");
    public static final EnchantType SNIPE = Enchants.get("SNIPE");

    // Weapons
    public static final EnchantType CUBISM = Enchants.get("CUBISM");
    public static final EnchantType DRAGON_HUNTER = Enchants.get("DRAGON_HUNTER");
    public static final EnchantType IMPALING = Enchants.get("IMPALING");
    public static final EnchantType VICIOUS = Enchants.get("VICIOUS");

    // Armor
    // Helmet
    public static final EnchantType AQUA_AFFINITY = Enchants.get("AQUA_AFFINITY");
    public static final EnchantType BIG_BRAIN = Enchants.get("BIG_BRAIN");
    public static final EnchantType RESPIRATION = Enchants.get("RESPIRATION");
    // Chestplate
    public static final EnchantType COUNTER_STRIKE = Enchants.get("COUNTER_STRIKE");
    public static final EnchantType TRUE_PROTECTION = Enchants.get("TRUE_PROTECTION");
    // Leggings
    public static final EnchantType SMARTY_PANTS = Enchants.get("SMARTY_PANTS");
    // Boots
    public static final EnchantType DEPTH_STRIDER = Enchants.get("DEPTH_STRIDER");
    public static final EnchantType FEATHER_FALLING = Enchants.get("FEATHER_FALLING");
    public static final EnchantType FROST_WALKER = Enchants.get("FROST_WALKER");
    public static final EnchantType SUGAR_RUSH = Enchants.get("SUGAR_RUSH");
    // All
    public static final EnchantType BLAST_PROTECTION = Enchants.get("BLAST_PROTECTION");
    public static final EnchantType FIRE_PROTECTION = Enchants.get("FIRE_PROTECTION");
    public static final EnchantType GROWTH = Enchants.get("GROWTH");
    public static final EnchantType PROJECTILE_PROTECTION = Enchants.get("PROJECTILE_PROTECTION");
    public static final EnchantType PROTECTION = Enchants.get("PROTECTION");
    public static final EnchantType REJUVENATE = Enchants.get("REJUVENATE");
    public static final EnchantType RESPITE = Enchants.get("RESPITE");

    // Not For Armors
    public static final EnchantType TELEKINESIS = Enchants.get("TELEKINESIS");

    // Null
    public static final EnchantType NULL = new EnchantType(0, "", 1);

    private final ArrayList<Integer> stats; // dmg, str, cd, cc, as, fer, hp, def, speed, mana
    private final short maxLevel;
    private final String description;
    private final double multiplayer;
    private final ArrayList<ItemType> types;
    private final ArrayList<String> distractions;

    EnchantType(ArrayList<Integer> stats, int maxLevel ,String description, double multiplayer, ArrayList<ItemType> types, ArrayList<String> distractions) {
        this.stats = stats;
        this.description = description;
        this.multiplayer = multiplayer;
        if (!types.contains(ItemType.BOOK)) types.add(ItemType.BOOK);
        this.types = types;
        this.distractions = distractions;
        this.maxLevel = (short) maxLevel;
    }

    EnchantType(int maxLevel, String description, double multiplayer, ArrayList<ItemType> types, ArrayList<String> distractions) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, types, distractions);
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type, ArrayList<String> distractions) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), distractions);
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type, String distraction) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>(Arrays.asList(distraction)));
    }

    EnchantType(int maxLevel, String description, double multiplayer, ItemType type) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>());
    }

    EnchantType(int maxLevel, String description, double multiplayer, ArrayList<ItemType> types) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, types, new ArrayList<>());
    }

    EnchantType(int maxLevel, ArrayList<Integer> stats , String description, double multiplayer, ItemType type) {
        this(stats, maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>());
    }

    EnchantType(ArrayList<Integer> stats, int maxLevel, String description, double multiplayer, ArrayList<ItemType> types) {
        this(stats, maxLevel, description, multiplayer, types, new ArrayList<>());
    }

    EnchantType(ArrayList<Integer> stats, int maxLevel, String description, double multiplayer, ItemType type) {
        this(stats, maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(type)), new ArrayList<>());
    }

    EnchantType(int maxLevel, String description, double multiplayer) {
        this(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), maxLevel, description, multiplayer, new ArrayList<>(Arrays.asList(ItemType.values())), new ArrayList<>());
    }

    public static void registerEnchants() {
        // Swords
        Enchants.put("BANE_OF_ARTHROPODS", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "SMITE"))));
        Enchants.put("CLEAVE", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("CRITICAL", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 10, 0, 0, 0, 0, 0, 0, 0)), 5, "", 0, ItemType.SWORD));
        Enchants.put("ENDER_SLAYER", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("EXECUTE", new EnchantType(5, "", 0, ItemType.SWORD, "PROSECUTE"));
        Enchants.put("EXPERIENCE", new EnchantType(3, "", 0, ItemType.SWORD));
        Enchants.put("FIRE_ASPECT", new EnchantType(2, "", 0, ItemType.SWORD));
        Enchants.put("FIRST_STRIKE" , new EnchantType(4, "", 0, ItemType.SWORD, "TRIPLE_STRIKE"));
        Enchants.put("GIANT_KILLER", new EnchantType(5, "", 0, ItemType.SWORD, "TITAN_KILLER"));
        Enchants.put("KNOCKBACK", new EnchantType(2, "", 0, ItemType.SWORD));
        Enchants.put("LETHALITY", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("LIFE_STEAL", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SYPHON", "MANA_STEAL"))));
        Enchants.put("LOOTING", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("LUCK", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("MANA_STEAL", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "SYPHON"))));
        Enchants.put("PROSECUTE", new EnchantType(5, "", 0, ItemType.SWORD, "EXECUTE"));
        Enchants.put("SCAVENGER", new EnchantType(3, "", 0, ItemType.SWORD));
        Enchants.put("SHARPNESS", new EnchantType(5, "Increases melee damage dealt by " + ChatColor.GREEN + "MULTIPLAYER% " + ChatColor.GRAY + ".", 5, ItemType.SWORD, new ArrayList<>(Arrays.asList("SMITE", "BANE_OF_ARTHROPODS"))));
        Enchants.put("SMITE", new EnchantType(5, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("SHARPNESS", "BANE_OF_ARTHROPODS"))));
        Enchants.put("SYPHON", new EnchantType(3, "", 0, ItemType.SWORD, new ArrayList<>(Arrays.asList("LIFE_STEAL", "MANA_STEAL"))));
        Enchants.put("THUNDERBOLT", new EnchantType(5, "", 0, ItemType.SWORD, "THUNDERLORD"));
        Enchants.put("THUNDERLORD", new EnchantType(5, "", 0, ItemType.SWORD, "THUNDERBOLT"));
        Enchants.put("TITAN_KILLER", new EnchantType(5, "", 0, ItemType.SWORD, "GIANT_KILLER"));
        Enchants.put("TRIPLE_STRIKE", new EnchantType(4, "", 0, ItemType.SWORD, "FIRST_STRIKE"));
        Enchants.put("VAMPIRISM", new EnchantType(5, "", 0, ItemType.SWORD));
        Enchants.put("VENOMOUS", new EnchantType(5, "", 0, ItemType.SWORD));

        // Bow
        Enchants.put("CHANCE", new EnchantType(5, "", 0, ItemType.BOW));
        Enchants.put("DRAGON_TRACER", new EnchantType(5, "", 0, ItemType.BOW));
        Enchants.put("FLAME", new EnchantType(1, "", 0, ItemType.BOW));
        Enchants.put("INFINITE_QUIVER", new EnchantType(5, "", 0, ItemType.BOW));
        Enchants.put("PIERCING", new EnchantType(1, "", 0, ItemType.BOW));
        Enchants.put("OVERLOAD", new EnchantType(5, "", 0, ItemType.BOW));
        Enchants.put("POWER", new EnchantType(5, "", 0, ItemType.BOW));
        Enchants.put("PUNCH", new EnchantType(2, "", 0, ItemType.BOW));
        Enchants.put("SNIPE", new EnchantType(3, "", 0, ItemType.BOW));

        // Weapons
        Enchants.put("CUBISM", new EnchantType(5, "", 0, ItemTypeGroup.WEAPON.toType()));
        Enchants.put("DRAGON_HUNTER", new EnchantType(5, "", 0, ItemTypeGroup.WEAPON.toType()));
        Enchants.put("IMPALING", new EnchantType(3, "", 0, ItemTypeGroup.WEAPON.toType()));
        Enchants.put("VICIOUS", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)), 5, "", 0, ItemTypeGroup.WEAPON.toType()));

        // Armor
        // Helmet
        Enchants.put("AQUA_AFFINITY", new EnchantType(1, "", 0, ItemType.HELMET));
        Enchants.put("BIG_BRAIN", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 5)), 5, "", 0, ItemType.HELMET));
        Enchants.put("RESPIRATION", new EnchantType(3, "", 0, ItemType.HELMET));
        // Chestplate
        Enchants.put("COUNTER_STRIKE", new EnchantType(5, "", 0, ItemType.CHESTPLATE));
        Enchants.put("TRUE_PROTECTION", new EnchantType(1, "", 0, ItemType.CHESTPLATE));
        // Leggings
        Enchants.put("SMARTY_PANTS", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 5)), 5, "", 0, ItemType.LEGGINGS));
        // Boots
        Enchants.put("DEPTH_STRIDER", new EnchantType(3, "", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("FROST_WALKER"))));
        Enchants.put("FEATHER_FALLING", new EnchantType(4, "", 0, ItemType.BOOTS));
        Enchants.put("FROST_WALKER", new EnchantType(2, "", 0, ItemType.BOOTS, new ArrayList<>(Arrays.asList("DEPTH_STRIDER"))));
        Enchants.put("SUGAR_RUSH", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 2, 0)), 3, "", 0, ItemType.BOOTS));
        // All Armors
        Enchants.put("BLAST_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("FIRE_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        Enchants.put("FIRE_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "PROJECTILE_PROTECTION", "PROTECTION"))));
        Enchants.put("GROWTH", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 15, 0, 0, 0)), 5, "", 0, ItemTypeGroup.ARMOR.toType()));
        Enchants.put("PROJECTILE_PROTECTION", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROTECTION"))));
        Enchants.put("PROTECTION", new EnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 3, 0, 0)), 5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("BLAST_PROTECTION", "FIRE_PROTECTION", "PROJEC)TILE_PROTECTION"))));
        Enchants.put("REJUVENATE", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("RESPITE"))));
        Enchants.put("RESPITE", new EnchantType(5, "", 0, ItemTypeGroup.ARMOR.toType(), new ArrayList<>(Arrays.asList("REJUVENATE"))));

        // Not For Armors
        Enchants.put("TELEKINESIS", new EnchantType(1, "", 0, new ArrayList<>(Arrays.asList(ItemType.ACCESSORY, ItemType.SWORD, ItemType.AXE, ItemType.ITEM, ItemType.BOW, ItemType.PICKAXE, ItemType.ROD, ItemType.WAND, ItemType.HOE))));

        // Ultimate Enchants
        Enchants.put("AAACHIMERA", new UltimateEnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), 5, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "CHIMERA"));
        Enchants.put("AAAONE_FOR_ALL", new UltimateEnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), 1, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "ONE_FOR_ALL"));
        Enchants.put("AAAULTIMATE_WISE", new UltimateEnchantType(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), 5, "", 0, new ArrayList<>(Arrays.asList(ItemType.SWORD)), "ULTIMATE_WISE"));
    }

    public String name() {
        for (String key : Enchants.keySet()) {
            if (Enchants.get(key) == this) {
                String output = key.replaceAll(" ", "");
                output = output.replaceAll("AAA", "");
                return output;
            }
        }
        return "";
    }

    public String realname() {
        for (String key : Enchants.keySet()) {
            if (Enchants.get(key) == this) {
                return key.replaceAll(" ", "");
            }
        }
        return "";
    }

    public static EnchantType valueOf(String name) {
        name = name.replaceAll("AAA", "");
        for (EnchantType enchantType : Enchants.values()) {
            if (enchantType.name().equals(name.toUpperCase())) {
                return enchantType;
            }
        }
        return EnchantType.NULL;
    }

    @Override
    public String toString() {
        for (String key : Enchants.keySet()) {
            if (Enchants.get(key) == this) {
                String output = key.replaceAll("_", " ");
                output = output.replaceAll("AAA", "");
                return setTitleCase(output);
            }
        }
        return "";
    }
}
