package me.maxiiiiii.skyblockdragons.material;

import lombok.Getter;
import lombok.ToString;
import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.itemcreator.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter
@ToString
public class ItemMaterial {
    public static HashMap<String, ItemMaterial> Items = new HashMap<>();
    public static HashMap<String, ItemMaterial> ItemMaterials = new HashMap<>();

    public static ToolMaterial NULL = null;

    protected Material material;
    protected ItemFamily family;
    protected String name;
    protected ItemType type;
    protected Rarity rarity;
    protected String id;
    protected String nbt;

    ItemMaterial(Material material, ItemFamily family, String name, ItemType type, Rarity rarity, String id, String nbt) {
        this.material = material;
        this.family = family;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.id = id;
        this.nbt = nbt;
    }

    public String getName() {
        return Functions.setTitleCase(this.name.replaceAll("_", " "));
    }

    public static void registerItems() {
        // Swords
        Items.put("ASPECT_OF_THE_END", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.ASPECT_OF_THE_END,"Aspect of The End", Rarity.RARE, new ArrayList<>(Arrays.asList(100, 100, 0, 0, 0, 0, 0, 0, 0, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Transmission", ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + Stat.SPEED.getIconAndText() + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + ".", 50, 0)));
        Items.put("ASPECT_OF_THE_VOID", new SwordMaterial(Material.DIAMOND_SPADE, ItemFamily.ASPECT_OF_THE_END, "Aspect of The Void", Rarity.EPIC, new ArrayList<>(Arrays.asList(120, 100, 0, 0, 0, 0, 0, 0, 0, 0)), "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.RIGHT_CLICK, "Transmission", ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "12 blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + Stat.SPEED.getIconAndText() + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + ".", 45, 0), new ItemAbility(AbilityAction.RIGHT_SHIFT_CLICK, "Ether Transmission", ChatColor.GRAY + "Teleport to your targeted block up to " + ChatColor.GREEN + "61 blocks " + ChatColor.GRAY + "away.", 180, 0)))));
        Items.put("BONZO_STAFF", new SwordMaterial(Material.BLAZE_ROD, ItemFamily.BONZO_STAFF, "Bonzo's Staff", Rarity.RARE ,new ArrayList<>(Arrays.asList(160, 0, 0, 0, 0, 0, 0, 0, 0, 250)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Showtime", ChatColor.GRAY + "Shoots balloons that create a large explosion on impact", 50, 0)));
        Items.put("LEAPING_SWORD", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.LEAPING_SWORD, "Leaping Sword", Rarity.EPIC, new ArrayList<>(Arrays.asList(150, 100, 25, 0, 0, 0, 0, 0, 0, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Leap", "Leap into the air and deal " + ChatColor.GREEN + "400 " + ChatColor.GRAY + "base Magic Damage to nearby NEW_LINE enemies upon landing on the NEW_LINE ground. Damaged enemies will NEW_LINE also be frozen for " + ChatColor.GREEN + "1 second" + ChatColor.GRAY + ".", 50, 1)));
        Items.put("ATOMSPLIT_KATANA", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.VOIDWALKER_KATANA, "Atomsplit Katana", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(240, 100, 25, 0, 0, 0, 0, 0, 0, 300)), "Deal " + ChatColor.GREEN + "+200% RESET_LENGTH " + ChatColor.GRAY + "damage to Endermen.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Soulcry", "Gain " + ChatColor.RED + "+400" + Stat.FEROCITY.getIconAndText() + ChatColor.GRAY + " against Endermen for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + ".", 200, 4)));
        Items.put("SOUL_WHIP", new SwordMaterial(Material.FISHING_ROD, ItemFamily.SOUL_WHIP, "Soul Whip", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(145, 175, 0, 0, 0, 0, 0, 0, 0, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Flay", "Flay your whip in an arc, dealing your melee damage to all enemeies in its path.", 0, 0)));
        Items.put("FLOWER_OF_TRUTH", new SwordMaterial(Material.RED_ROSE, ItemFamily.FLOWER_OF_TRUTH, "Flower of Truth", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(100, 360, 0, 0, 0, 0, 0, 0, 0, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Heat-Seeking Rose", "Shoots a rose that ricochets between enemies, damaging up to " + ChatColor.GREEN + "3 " + ChatColor.GRAY + "of your foes! Damage multiplies as more enemies are hit.", 100000, 1)));
        Items.put("SHADOW_FURY", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.SHADOW_FURY, "Shadow Fury", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(300, 125, 0, 0, 0, 0, 0, 0, 30, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Shadow Fury", "Rapidly teleports you to up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "enemies within " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "blocks, rooting each of them and allowing you to hit them.", 0, 15)));
        Items.put("SPIRIT_SCEPTRE", new SwordMaterial(Material.RED_ROSE, ItemFamily.SPIRIT_SCEPTRE, "Spirit Spectre", Rarity.LEGENDARY, "2", new ArrayList<>(Arrays.asList(180, 0, 0, 0, 0, 0, 0, 0, 0, 300)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Guided Bat", "Shoots a guided spirit bat, following your aim and exploding for " + ChatColor.RED + "2,000 " + ChatColor.GRAY + "damage.", 250, 0)));
        Items.put("ROGUE_SWORD", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.ROGUE_SWORD, "Rogue Sword", Rarity.COMMON, new ArrayList<>(Arrays.asList(20, 0, 0, 0, 0, 0, 0, 0, 0, 0)), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Speed Boost", "Increases your movement " + Stat.SPEED.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "+20 " + ChatColor.GRAY + "for " + ChatColor.GREEN + "30 " + ChatColor.GRAY + "seconds. Only gives " + ChatColor.GREEN + "+10 " + Stat.SPEED.getIconAndText() + " " + ChatColor.GRAY + "if already in use.", 50, 0)));
        Items.put("AXE_OF_THE_SHREDDED", new SwordMaterial(Material.DIAMOND_AXE, ItemFamily.REVENANT_FALCHION, "Axe of the Shredded", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(140, 115, 0, 0, 0, 0, 0, 0, 0, 0)), "Heal " + ChatColor.RED + "50" + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per hit." + " NEW_LINE " + "Deal " + ChatColor.GREEN + "+250% " + ChatColor.GRAY + "damage to Zombies." + " NEW_LINE " + "Receive " + ChatColor.GREEN + "25% " + ChatColor.GRAY + "less damage from Zombies when held.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Throw", "Throw your axe damaging all enemies in its path dealing " + ChatColor.RED + "10% " + ChatColor.GRAY + "melee damage. Consecutive throws stack " + ChatColor.RED + "2x " + ChatColor.GRAY + "damage but cost " + ChatColor.BLUE + "2x " + ChatColor.GRAY + "mana up to 16x", 20, 0)));
        Items.put("MIDAS_STAFF", new SwordMaterial(Material.GOLD_SPADE, ItemFamily.MIDAS_STAFF, "Midas Staff", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(130, 150, 0, 0, 0, 0, 0, 0, 0, 50)), ChatColor.GOLD + "Ability Greed" + " NEW_LINE " + ChatColor.GRAY + "The " + ChatColor.DARK_AQUA + "ability damage bonus " + ChatColor.GRAY + "of this item is dependent on the price paid for it at the " + ChatColor.DARK_PURPLE + "Dark Action" + ChatColor.GRAY + "! The maximum bonus of this item is " + ChatColor.DARK_AQUA + "26,000 " + ChatColor.GRAY + "if the bid was " + ChatColor.GOLD + "100,000,000 Coins " + ChatColor.GRAY + "or higher!", new ItemAbility(AbilityAction.RIGHT_CLICK, "Molten Wave", "Cast a wave of molten gold in the direction you are facing! Deals up to " + ChatColor.RED + "6,000 " + ChatColor.GRAY + "damage.", 500, 1)));

        Items.put("VALKYRIE", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Valkyrie", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(270, 145, 0, 0, 0, 60, 0, 0, 0, 60)), NecronBladeMaterial.NecronBladeType.VALKYRIE));

        // Bows
        Items.put("TERMINATOR", new BowMaterial(Material.BOW, ItemFamily.JUJU,"Terminator", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(335, 50, 250, 0, 40, 0, 0, 0, 0, 0)), ChatColor.GOLD + "Shortbow: Instantly Shoots! NEW_LINE " + ChatColor.GRAY + "Shoots " + ChatColor.AQUA + "3 " + ChatColor.GRAY + "arrows at once. " + ChatColor.GRAY + "Can damage endermen. NEW_LINE NEW_LINE " + ChatColor.RED + "Divides your RESET_LENGTH " + Stat.CRIT_CHANCE.getIconAndText() + ChatColor.RED + " by 4!", new ItemAbility(AbilityAction.RIGHT_CLICK, "Salvation", ChatColor.GRAY + "Can be casted after landing RESET_LENGTH " + ChatColor.GOLD + "3 " + ChatColor.GRAY + "hits. NEW_LINE Shoot a beam, penetrating up NEW_LINE to " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "foes and dealing " + ChatColor.RED + "2x " + ChatColor.GRAY + "the damage an arrow would. NEW_LINE The beam always crits.", 0, 2)));
        Items.put("BONEMERANG", new BowMaterial(Material.BONE, ItemFamily.BONEMERANG, "Bonemerang", Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(270, 130, 0, 0, 0, 0, 0, 0, 0, 0)), "Deals " + ChatColor.RED + "double damage " + ChatColor.GRAY + "when coming back. Pierces up to " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "foes.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Swing", "Throw the bone a short distance, dealing the damage an arrow would.", 0, 0)));

        // Armors
        // Necron
        Items.put("NECRON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.NECRON_ARMOR, "Necron's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "16b91b55-02b7-3315-bd9a-7da8467e4a96", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzI0OTc5MywKICAicHJvZmlsZUlkIiA6ICIwNjEzY2I1Y2QxYjg0M2JjYjI4OTk1NWU4N2QzMGEyYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJicmVhZGxvYWZzcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYmJiMmZhN2E2Y2EwODcyODBlYTBjYjU2NGI0MWVmMWFlNDA0YTE5ZjdhODEyOGQzZDI4YzUxOWE4NWUwNjNmIgogICAgfQogIH0KfQ", new ArrayList<>(Arrays.asList(0, 40, 30, 0, 0, 0, 180, 100, 0, 30)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN));
        Items.put("NECRON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.NECRON_ARMOR, "Necron's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 40, 30, 0, 0, 0, 260, 140, 0, 10)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(231,65,60)));
        Items.put("NECRON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.NECRON_ARMOR, "Necron's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 40, 30, 0, 0, 0, 230, 125, 0, 30)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(231,92,60)));
        Items.put("NECRON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.NECRON_ARMOR, "Necron's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 40, 30, 0, 0, 0, 145, 85, 0, 10)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(231,110,60)));
        // Storm
        Items.put("STORM_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.STORM_ARMOR, "Storm's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "a3552cdc-39ab-3f9f-9373-61c035f61b7d", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzM2NjgxNiwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mODMxNTljNWJiN2Q4MDQ1NGQyZDY0NmIxNTc3NGI3MTE2YWFiY2IzYWY1YjY3NzdhMjc1NzNmYzQ1Zjc4NTRmIgogICAgfQogIH0KfQ", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 180, 80, 0, 400)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN));
        Items.put("STORM_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.STORM_ARMOR,"Storm's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 260, 120, 0, 250)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(23,147,196)));
        Items.put("STORM_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.STORM_ARMOR, "Storm's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 230, 105, 0, 250)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(23,168,196)));
        Items.put("STORM_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.STORM_ARMOR, "Storm's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 145, 65, 0, 250)), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHERBORN, Color.fromRGB(28,212,228)));
        // Superior
        Items.put("SUPERIOR_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "7fd17d6f-9e42-478c-8cce-68aec1d52eec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=", new ArrayList<>(Arrays.asList(0, 10, 10, 2, 0, 0, 90, 130, 3, 25)), "", ItemFullSet.SUPERIOR_BLOOD));
        Items.put("SUPERIOR_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 10, 10, 2, 0, 0, 150, 190, 3, 25)), "", ItemFullSet.SUPERIOR_BLOOD, Color.fromRGB(242,223,17)));
        Items.put("SUPERIOR_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.LEGGINGS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 10, 10, 2, 0, 0, 130, 170, 3, 25)), "", ItemFullSet.SUPERIOR_BLOOD, Color.fromRGB(242,223,17)));
        Items.put("SUPERIOR_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.BOOTS, Rarity.LEGENDARY, new ArrayList<>(Arrays.asList(0, 10, 10, 2, 0, 0, 80, 110, 3, 25)), "", ItemFullSet.SUPERIOR_BLOOD, Color.fromRGB(242,93,24)));

        // Tools
        Items.put("TREE_CAPITATOR", new ToolMaterial(Material.GOLD_AXE, ItemFamily.JUNGLE_AXE, "Tree Capitator", ItemType.AXE, Rarity.EPIC, "A forceful Gold Axe which can break a large amount of logs in a single hit!", new ItemAbility(AbilityAction.NULL, "", "", 0, 0)));
        Items.put("WORLD_EATER", new ToolMaterial(Material.DIAMOND_PICKAXE, ItemFamily.JUNGLE_AXE, "World Eater", ItemType.PICKAXE, Rarity.DIVINE, ChatColor.ITALIC + "I became a world ERROR.", new ItemAbility(AbilityAction.NULL, "", "", 0, 0)));

        // Reforges
        Items.put("PRECURSOR_GEAR", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Precursor Gear", Rarity.EPIC, "62ffd058-94c8-3b63-b027-5e9f4d52b78e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRmODkwOTQ5OGMyNWY2ZTc1ZWYxOWUzNzZhN2Y4NGY2MWFmMjM0NTI1ZDYzOWJhNDYzZjk5MWY0YzgyZDAifX19", "Ancient"));
        Items.put("WITHER_BLOOD", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Wither's Blood", Rarity.EPIC, "d3323a7b-65e3-394a-95da-018d2f52f917", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTU0MzQ1NTU3OCwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JmNWQyMGUwNjAwMTc0ZGNiYWI3NzQ1ZDk0NDgzMmZiMjA2M2MyYmQxNDkwYzY1MDU5MDFiMjhiZmFhY2Q4ZTUiCiAgICB9CiAgfQp9", "Withered"));
        Items.put("NECROMANCER_BROOCH", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Necromancer's Brooch", Rarity.RARE, "ea3ee289-11c8-32b4-8913-c98703b1ab1c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViNmJlYWM1NzM2NWNhZjQ2ZjAzN2YzZDJhM2E0NTdmNmNhZmU2NDc1N2JhZjE0ZTg5OTNjZDJkYTE4Y2ZmNyJ9fX0K", "Necrotic"));

        // Skins
        Items.put("NECRON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Necron Skin", Rarity.EPIC, "95b93eb6-4ed9-4841-81ee-729f6d1516e4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYzZWEzZmE1NGE4ZDcwMTQ5NjkyMjY2NmNiMjQyNTg5MGNjZGRhYzY1OTkzYjVkNzViMjY0MmJmN2U2YWY4ZCJ9fX0="));
        Items.put("STORM_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Storm Skin", Rarity.EPIC, "4e279894-281e-4ab1-806e-3b3728f899d0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5OTM5ZWE1ZTc2NTVlNjE2YmU4NmEzYTQwMDEzMWMyYzljNzQ1ZTdlMzhlZDBkMWVlMjk4N2JiZTc1YTQ0YSJ9fX0="));
        Items.put("SUPERIOR_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Superior Skin", Rarity.EPIC, "8bac9ff5-6006-3a10-99e4-55799153a1f8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJhNzcwNDU5ZmE2ZDY1ZTIyYWE5NTY3OWQ5M2EyODcwYWFmZWE3MGY5ZjFjNmEwZjc4ZWI2NDFlOTI4OTAifX19Cg"));

        // Accessories
        Items.put("NETHER_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.NETHER_ARTIFACT, "Nether Artifact", Rarity.EPIC, "0ef495a4-e5df-41c2-b9a2-b2e647cbb491", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0=", new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), "Reduces the damage taken from Zombie Pigmen, Magma Cubes, Ghasts and Blazes by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ". NEW_LINE NEW_LINE While in the " + ChatColor.GOLD + "Blazing Fortress " + ChatColor.GRAY + "you will receive the damage reduction from all mobs."));

        // Wands
        Items.put("GYROKINETIC_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.GYROKINETIC_WAND, "Gyrokinetic Wand", ItemType.WAND, Rarity.EPIC, "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.LEFT_CLICK, "Gravity Storm", "Create a large " + ChatColor.DARK_PURPLE + "rift " + ChatColor.GRAY + "at aimed location, pulling all mobs toegether.", 1200, 5), new ItemAbility(AbilityAction.RIGHT_CLICK, "Cells Alignment", "Apply " + ChatColor.GREEN + "Aligned " + ChatColor.GRAY + "to 4 nearby players and yourself for " + ChatColor.GREEN + "6 seconds" + ChatColor.GRAY + ".", 220, 10)))));
        Items.put("TORNADO_WAND", new ToolMaterial(Material.STICK, ItemFamily.TORNADO_WAND, "Tornado Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a tornado on you with random colored blocks.", 10, 1)));
        Items.put("TWISTER_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Twister Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes on you with random colored blocks.", 10, 1)));
        Items.put("HURRICANE_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Hurricane Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes around you with random colored blocks.", 10, 1)));
        Items.put("PARABOLA_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Parabola Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Parabola", "Cast a parabola on you with random colored blocks.", 10, 1)));
        Items.put("BUILDERS_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.BUILDERS_WAND, "Builder's Wand", ItemType.WAND, Rarity.LEGENDARY, "", new ArrayList<>(Arrays.asList(new ItemAbility(AbilityAction.RIGHT_CLICK, "Grand Architect", "Right-click the face of a block to extend all connected block faces.", 0, 0), new ItemAbility(AbilityAction.LEFT_CLICK, "Built-in Storage", "Opens the wand storage. Blocks will be placed from your inventory or the wand storage.", 0, 0)))));
        Items.put("ICE_SPRAY_WAND", new ToolMaterial(Material.STICK, ItemFamily.ICE_SPRAY_WAND, "Ice Spray Wand", ItemType.WAND, Rarity.EPIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Ice Spray", "Produces a cone of ice in front of the caster that deals " + ChatColor.RED + "17,000 " + ChatColor.GRAY + "damage to mobs and freezes them in place for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds! Frozen mobs take " + ChatColor.RED + "10% " + ChatColor.GRAY + "increased damage!", 50, 5)));
        Items.put("ERRORMERANG_WAND", new ToolMaterial(Material.STICK, ItemFamily.ERRORMERANG_WAND, "ERRORMerang Wand", ItemType.WAND, Rarity.SPECIAL, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "ERRORMerang", "Error item of eyalc i don't know why", 50, 5)));

        // Scrolls
        Items.put("IMPLOSION", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Implosion", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility()));
        Items.put("WITHER_SHIELD", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Shield", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility()));
        Items.put("SHADOW_WARP", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Shadow Warp", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()));
        Items.put("WITHER_IMPACT", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Impact", Rarity.EPIC, "", new ArrayList<>(Arrays.asList(NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility(), NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility(), NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()))));

        // Power Orbs
        Items.put("RADIANT_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Radiant Power Orb", ItemType.POWER_ORB, Rarity.UNCOMMON, "10a23a36-4f37-47f7-a28f-fc330de3ff5a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Heals " + ChatColor.RED + "1% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second."));
        Items.put("MANA_FLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Mana Flux Power Orb", ItemType.POWER_ORB, Rarity.RARE, "2131e1e3-cd0f-4212-b625-8ccb402e895e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJhZGExYzdmY2M4Y2YzNWRlZmViOTQ0YTRmOGZmYTlhOWQyNjA1NjBmYzdmNWY1ODI2ZGU4MDg1NDM1OTY3YyJ9fX0", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+50% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+10 Strength" + ChatColor.GRAY + "."));
        Items.put("OVERFLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Overflux Power Orb", ItemType.POWER_ORB, Rarity.EPIC, "05624a23-a2f1-46b9-9e26-e463855f05c1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+100% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2.5% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+5%"  + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+25 Strength" + ChatColor.GRAY + "."));
        Items.put("PLASMA_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Plasma Power Orb", ItemType.POWER_ORB, Rarity.LEGENDARY, "6de57aa8-ffd6-414d-ad9b-85563a6dc417", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "20 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+125% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "3% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+7.5%" + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+35 Strength" + ChatColor.GRAY + "."));

        // Items
        Items.put("TROLL_EYE", new ToolMaterial(Material.EYE_OF_ENDER, ItemFamily.TROLL, "Troll Eye", ItemType.ITEM, Rarity.EPIC, ChatColor.ITALIC + "Eye of the ender lost ERROR", new ItemAbility(AbilityAction.RIGHT_CLICK, "Get Out From My Face", "Make your target entity shoot ender pearls to every direction", 100, 15)));
        Items.put("GRAPPLING_HOOK", new ToolMaterial(Material.FISHING_ROD, ItemFamily.GRAPPLING_HOOK, "Grappling Hook", ItemType.ITEM , Rarity.UNCOMMON, ChatColor.GRAY + "Travel around in style using this " + ChatColor.GREEN + "Grappling Hook" + ChatColor.GRAY + "."));
        Items.put("RECOMBABULATOR", new ToolMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Recombobulator 3000", ItemType.ITEM, Rarity.LEGENDARY, "96538e7f-6b56-3557-9b7d-458afe4239e9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjY2QzNmRjOGY3MmFkY2IxZjhjOGU2MWVlODJjZDk2ZWFkMTQwY2YyYTE2YTEzNjZiZTliNWE4ZTNjYzNmYyJ9fX0K", "Use in a " + ChatColor.GREEN + "Reforge Anvil "+ ChatColor.GRAY + "or at the Dungeon Blacksmith to permanently increase the rarity of a piece of armor, weapon, tool or talisman. An item's rarity can only be upgraded once!"));
        Items.put("ELECTRO_MAGNET", new ToolMaterial(Material.TORCH, ItemFamily.ELECTRO_MAGNET, "Electro Magnet", ItemType.ITEM, Rarity.EPIC, "", new ItemAbility(AbilityAction.NONE, "Force Field", "Repel hostile creatures and projectiles in radius of " + ChatColor.GREEN + "10 blocks" + ChatColor.GRAY + ".", 0, 0)));

        // Normal Items
        Items.put("ENCHANTED_EYE_OF_ENDER", new NormalMaterial(Material.EYE_OF_ENDER, ItemFamily.ENCHANTED_ITEM, "Enchanted Eye of Ender", ItemType.ITEM, Rarity.UNCOMMON, "", "", true, true));
        Items.put("ENCHANTED_DIAMOND", new NormalMaterial(Material.DIAMOND, ItemFamily.ENCHANTED_ITEM, "Enchanted Diamond", ItemType.ITEM, Rarity.UNCOMMON, "", "", true, true));
        Items.put("HOT_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Hot Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Combine this Book in an Anvil with a weapon or armor piece to gain a small but permanent stat boost!", true, false, false));
        Items.put("FUMING_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Fuming Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Use in an anvil to combine this book with a weapon or armor pieace to gain a small but permanent stat boost!" + " NEW_LINE NEW_LINE " + "This book bypasses the Hot Potato Book limit of 10, allowing you to upgrade an item up to " + ChatColor.GREEN + "15 " + ChatColor.GRAY + "times!", true, false, false));
        Items.put("SKYBLOCK_MENU", new NormalMaterial(Material.NETHER_STAR, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, false));

        // Other Items
        Items.put("NULL_OVOID", new NormalMaterial(Material.MONSTER_EGG, ItemFamily.NULL, "Null Ovoid", ItemType.ITEM, Rarity.RARE, "58", "", true, true));

        Items.put("ENCHANTED_BOOK", new BookMaterial(Material.ENCHANTED_BOOK, ItemFamily.BOOK, "Enchanted Book", ItemType.BOOK, Rarity.COMMON, "", ""));

        ItemMaterials = (HashMap<String, ItemMaterial>) Items.clone();

        for (Material material : Material.values()) {
            if (material.isItem() && !Items.containsKey(material.name())) {
                Rarity rarity = Rarity.COMMON;
                if (material == Material.NETHER_STAR || material == Material.BEDROCK) rarity = Rarity.LEGENDARY;
                Items.put(material.name(), new ItemMaterial(material, ItemFamily.ITEM, Functions.setTitleCase(material.name()), ItemType.ITEM, rarity, "", ""));
            }
        }

        NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");

//        Items.get("NULL", new ItemMaterial(Material.BARRIER, "Null", ItemType.NULL, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", new ArrayList<>(Arrays.asList(AbilityAction.NONE)), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0)), Rarity.SPECIAL));
    }

    public static ItemMaterial get(String name) {
        return Items.get(name);
    }

    public String name() {
        for (String key : Items.keySet()) {
            if (Items.get(key) == this) {
                return key;
            }
        }
        return "";
    }
}

// OLD SYSTEM
//    ItemMaterial(Material material, ItemFamily family, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, ArrayList<Integer> manaCost, Rarity rarity) {
//        this(material, family, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, manaCost, rarity, "", "");
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, ArrayList<Integer> manaCost, Rarity rarity, String id, String nbt) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, manaCost, rarity, id, nbt);
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, ArrayList<Integer> manaCost, Rarity rarity, String id) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, manaCost, rarity, id, "");
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, ArrayList<Integer> manaCost, Rarity rarity) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, manaCost, rarity, "", "");
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, Rarity rarity, String id, String nbt) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, new ArrayList<>(Arrays.asList(0)), rarity, id, nbt);
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, Rarity rarity, String id) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, new ArrayList<>(Arrays.asList(0)), rarity, id, "");
//    }
//
//    ItemMaterial(Material material, String name, ItemType type, ArrayList<Integer> stats, String itemDescription, ArrayList<AbilityAction> action, ArrayList<String> abilityName, ArrayList<String> abilityDescription, ArrayList<Integer> cooldown, Rarity rarity) {
//        this(material, ItemFamily.NULL, name, type, stats, itemDescription, action, abilityName, abilityDescription, cooldown, new ArrayList<>(Arrays.asList(0)), rarity, "", "");
//    }

//    public String name() {
//        return this.name();
//    }

