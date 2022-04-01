package me.maxiiiiii.skyblockdragons.material;

import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Items {
    public static HashMap<String, ItemMaterial> items = new HashMap<>();
    public static HashMap<String, ItemMaterial> itemMaterials = new HashMap<>();
    public static HashMap<String, ItemMaterial> vanillaMaterials = new HashMap<>();

    public static ToolMaterial NULL = null;

    public static void registerItems() {
        // Swords
        items.put("WOOD_SWORD", new SwordMaterial(Material.WOOD_SWORD, ItemFamily.WOOD, "Wood Sword", Rarity.COMMON, new ItemStats(5, 0, 0, 0, 0,0 ,0, 0, 0, 0), ""));
        items.put("STONE_SWORD", new SwordMaterial(Material.STONE_SWORD, ItemFamily.STONE, "Stone Sword", Rarity.COMMON, new ItemStats(5, 5, 0, 0, 0,0 ,0, 0, 0, 0), ""));
        items.put("IRON_SWORD", new SwordMaterial(Material.IRON_SWORD, ItemFamily.IRON, "Iron Sword", Rarity.COMMON, new ItemStats(10, 5, 0, 0, 0,0 ,0, 0, 0, 0), ""));
        items.put("GOLD_SWORD", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.GOLD, "Gold Sword", Rarity.COMMON, new ItemStats(15, 5, 0, 0, 0,0 ,0, 0, 0, 0), ""));
        items.put("DIAMOND_SWORD", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.DIAMOND, "Diamond Sword", Rarity.UNCOMMON, new ItemStats(15, 10, 0, 0, 0,0 ,0, 0, 0, 0), ""));

        items.put("ASPECT_OF_THE_END", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.ASPECT_OF_THE_END,"Aspect of The End", Rarity.RARE, new ItemStats(100, 100, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Transmission", ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + Stat.SPEED.getIconAndText() + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + ".", 50, 0)));
        items.put("ASPECT_OF_THE_VOID", new SwordMaterial(Material.DIAMOND_SPADE, ItemFamily.ASPECT_OF_THE_END, "Aspect of The Void", Rarity.EPIC, new ItemStats(120, 100, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Transmission", ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "12 blocks " + ChatColor.GRAY + "ahead of you and gain " + ChatColor.GREEN + "+50 " + Stat.SPEED.getIconAndText() + " NEW_LINE for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + ".", 45, 0), new ItemAbility(AbilityAction.RIGHT_SHIFT_CLICK, "Ether Transmission", ChatColor.GRAY + "Teleport to your targeted block up to " + ChatColor.GREEN + "61 blocks " + ChatColor.GRAY + "away.", 180, 0)));
        items.put("BONZO_STAFF", new SwordMaterial(Material.BLAZE_ROD, ItemFamily.BONZO_STAFF, "Bonzo's Staff", Rarity.RARE , new ItemStats(160, 0, 0, 0, 0, 0, 0, 0, 0, 250), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Showtime", ChatColor.GRAY + "Shoots balloons that create a large explosion on impact", 50, 0)));
        items.put("LEAPING_SWORD", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.LEAPING_SWORD, "Leaping Sword", Rarity.EPIC, new ItemStats(150, 100, 25, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Leap", "Leap into the air and deal " + ChatColor.GREEN + "400 " + ChatColor.GRAY + "base Magic Damage to nearby NEW_LINE enemies upon landing on the NEW_LINE ground. Damaged enemies will NEW_LINE also be frozen for " + ChatColor.GREEN + "1 second" + ChatColor.GRAY + ".", 50, 1)));
        items.put("ATOMSPLIT_KATANA", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.VOIDWALKER_KATANA, "Atomsplit Katana", Rarity.LEGENDARY, new ItemStats(240, 100, 25, 0, 0, 0, 0, 0, 0, 300), "Deal " + ChatColor.GREEN + "+200% RESET_LENGTH " + ChatColor.GRAY + "damage to Endermen.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Soulcry", "Gain " + ChatColor.RED + "+400" + Stat.FEROCITY.getIconAndText() + ChatColor.GRAY + " against Endermen for " + ChatColor.GREEN + "4 seconds" + ChatColor.GRAY + ".", 200, 4)));
        items.put("SOUL_WHIP", new SwordMaterial(Material.FISHING_ROD, ItemFamily.SOUL_WHIP, "Soul Whip", Rarity.LEGENDARY, new ItemStats(145, 175, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Flay", "Flay your whip in an arc, dealing your melee damage to all enemeies in its path.", 0, 0)));
        items.put("FLOWER_OF_TRUTH", new SwordMaterial(Material.RED_ROSE, ItemFamily.FLOWER_OF_TRUTH, "Flower of Truth", Rarity.LEGENDARY, new ItemStats(100, 360, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Heat-Seeking Rose", "Shoots a rose that ricochets between enemies, damaging up to " + ChatColor.GREEN + "3 " + ChatColor.GRAY + "of your foes! Damage multiplies as more enemies are hit.", 100000, 1)));
        items.put("SHADOW_FURY", new SwordMaterial(Material.DIAMOND_SWORD, ItemFamily.SHADOW_FURY, "Shadow Fury", Rarity.LEGENDARY, new ItemStats(300, 125, 0, 0, 0, 0, 0, 0, 30, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Shadow Fury", "Rapidly teleports you to up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "enemies within " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "blocks, rooting each of them and allowing you to hit them.", 0, 15)));
        items.put("SPIRIT_SCEPTRE", new SwordMaterial(Material.RED_ROSE, ItemFamily.SPIRIT_SCEPTRE, "Spirit Spectre", Rarity.LEGENDARY, "2", "", new ItemStats(180, 0, 0, 0, 0, 0, 0, 0, 0, 300), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Guided Bat", "Shoots a guided spirit bat, following your aim and exploding for " + ChatColor.RED + "2,000 " + ChatColor.GRAY + "damage.", 250, 0)));
        items.put("ROGUE_SWORD", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.ROGUE_SWORD, "Rogue Sword", Rarity.COMMON, new ItemStats(20, 0, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Speed Boost", "Increases your movement " + Stat.SPEED.getIconAndText() + " " + ChatColor.GRAY + "by " + ChatColor.GREEN + "+20 " + ChatColor.GRAY + "for " + ChatColor.GREEN + "30 " + ChatColor.GRAY + "seconds. Only gives " + ChatColor.GREEN + "+10 " + Stat.SPEED.getIconAndText() + " " + ChatColor.GRAY + "if already in use.", 50, 0)));
        items.put("AXE_OF_THE_SHREDDED", new SwordMaterial(Material.DIAMOND_AXE, ItemFamily.REVENANT_FALCHION, "Axe of the Shredded", Rarity.LEGENDARY, new ItemStats(140, 115, 0, 0, 0, 0, 0, 0, 0, 0), "Heal " + ChatColor.RED + "50" + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per hit." + " NEW_LINE " + "Deal " + ChatColor.GREEN + "+250% " + ChatColor.GRAY + "damage to Zombies." + " NEW_LINE " + "Receive " + ChatColor.GREEN + "25% " + ChatColor.GRAY + "less damage from Zombies when held.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Throw", "Throw your axe damaging all enemies in its path dealing " + ChatColor.RED + "10% " + ChatColor.GRAY + "melee damage. Consecutive throws stack " + ChatColor.RED + "2x " + ChatColor.GRAY + "damage but cost " + ChatColor.BLUE + "2x " + ChatColor.GRAY + "mana up to 16x", 20, 0)));
        items.put("MIDAS_STAFF", new SwordMaterial(Material.GOLD_SPADE, ItemFamily.MIDAS_STAFF, "Midas Staff", Rarity.LEGENDARY, new ItemStats(130, 150, 0, 0, 0, 0, 0, 0, 0, 50), ChatColor.GOLD + "Ability Greed" + " NEW_LINE " + ChatColor.GRAY + "The " + ChatColor.DARK_AQUA + "ability damage bonus " + ChatColor.GRAY + "of this item is dependent on the price paid for it at the " + ChatColor.DARK_PURPLE + "Dark Action" + ChatColor.GRAY + "! The maximum bonus of this item is " + ChatColor.DARK_AQUA + "26,000 " + ChatColor.GRAY + "if the bid was " + ChatColor.GOLD + "100,000,000 Coins " + ChatColor.GRAY + "or higher!", new ItemAbility(AbilityAction.RIGHT_CLICK, "Molten Wave", "Cast a wave of molten gold in the direction you are facing! Deals up to " + ChatColor.RED + "6,000 " + ChatColor.GRAY + "damage.", 500, 1)));
        items.put("PIGMAN_DAGGER", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.PIGMAN, "Pigman Dagger", Rarity.UNCOMMON, new ItemStats(25, 20, 5, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Burning", "Cast vortex of " + ChatColor.RED + "flames " + ChatColor.GRAY + "towards enemies.", 15, 1)));

        items.put("VALKYRIE", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Valkyrie", Rarity.LEGENDARY, new ItemStats(270, 145, 0, 0, 0, 60, 0, 0, 0, 60), NecronBladeMaterial.NecronBladeType.VALKYRIE));

        // Bows
        items.put("BOW", new BowMaterial(Material.BOW, ItemFamily.WOOD, "Bow", Rarity.COMMON, new ItemStats(5, 5, 0, 0, 0, 0, 0, 0, 0, 0), ""));

        items.put("GOLDEN_SKELETON_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Golden Skeleton Bow", Rarity.COMMON, new ItemStats(30, 0, 0, 0, 0, 0, 0, 0, 0, 0), ""));

        items.put("TERMINATOR", new BowMaterial(Material.BOW, ItemFamily.JUJU,"Terminator", Rarity.LEGENDARY, new ItemStats(335, 50, 250, 0, 40, 0, 0, 0, 0, 0), ChatColor.GOLD + "Shortbow: Instantly Shoots! NEW_LINE " + ChatColor.GRAY + "Shoots " + ChatColor.AQUA + "3 " + ChatColor.GRAY + "arrows at once. " + ChatColor.GRAY + "Can damage endermen. NEW_LINE NEW_LINE " + ChatColor.RED + "Divides your RESET_LENGTH " + Stat.CRIT_CHANCE.getIconAndText() + ChatColor.RED + " by 4!", new ItemAbility(AbilityAction.RIGHT_CLICK, "Salvation", ChatColor.GRAY + "Can be casted after landing RESET_LENGTH " + ChatColor.GOLD + "3 " + ChatColor.GRAY + "hits. NEW_LINE Shoot a beam, penetrating up NEW_LINE to " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "foes and dealing " + ChatColor.RED + "2x " + ChatColor.GRAY + "the damage an arrow would. NEW_LINE The beam always crits.", 0, 2)));
        items.put("BONEMERANG", new BowMaterial(Material.BONE, ItemFamily.BONEMERANG, "Bonemerang", Rarity.LEGENDARY, new ItemStats(270, 130, 0, 0, 0, 0, 0, 0, 0, 0), "Deals " + ChatColor.RED + "double damage " + ChatColor.GRAY + "when coming back. Pierces up to " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "foes.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Swing", "Throw the bone a short distance, dealing the damage an arrow would.", 0, 0)));

        // Armors
        // Leather
        items.put("LEATHER_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.LEATHER, "Leather Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL));
        items.put("LEATHER_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.LEATHER, "Leather Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL));
        items.put("LEATHER_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.LEATHER, "Leather Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL));
        items.put("LEATHER_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.LEATHER, "Leather Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL));
        // Chain
        items.put("CHAIN_HELMET", new ArmorMaterial(Material.CHAINMAIL_HELMET, ItemFamily.CHAIN, "Chain Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_CHESTPLATE", new ArmorMaterial(Material.CHAINMAIL_CHESTPLATE, ItemFamily.CHAIN, "Chain Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.CHAIN, "Chain Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_BOOTS", new ArmorMaterial(Material.CHAINMAIL_BOOTS, ItemFamily.CHAIN, "Chain Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 8, 0, 5, 0), "", ItemFullSet.NULL));
        // Iron
        items.put("IRON_HELMET", new ArmorMaterial(Material.IRON_HELMET, ItemFamily.IRON, "Iron Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_CHESTPLATE", new ArmorMaterial(Material.IRON_CHESTPLATE, ItemFamily.IRON, "Iron Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_LEGGINGS", new ArmorMaterial(Material.IRON_LEGGINGS, ItemFamily.IRON, "Iron Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_BOOTS", new ArmorMaterial(Material.IRON_BOOTS, ItemFamily.IRON, "Iron Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        // Gold
        items.put("GOLD_HELMET", new ArmorMaterial(Material.GOLD_HELMET, ItemFamily.GOLD, "Gold Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_CHESTPLATE", new ArmorMaterial(Material.GOLD_CHESTPLATE, ItemFamily.GOLD, "Gold Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_LEGGINGS", new ArmorMaterial(Material.GOLD_LEGGINGS, ItemFamily.GOLD, "Gold Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_BOOTS", new ArmorMaterial(Material.GOLD_BOOTS, ItemFamily.GOLD, "Gold Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        // Lapis
        items.put("LAPIS_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.LAPIS, "Lapis Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.LAPIS, "Lapis Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.LAPIS, "Lapis Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.LAPIS, "Lapis Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        // Redstone
        items.put("REDSTONE_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.REDSTONE, "Redstone Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.REDSTONE, "Redstone Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.REDSTONE, "Redstone Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.REDSTONE, "Redstone Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        // Emerald
        items.put("EMERALD_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.EMERALD, "Emerald Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.EMERALD, "Emerald Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.EMERALD, "Emerald Leggings", ItemType.LEGGINGS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.EMERALD, "Emerald Boots", ItemType.BOOTS, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        // Diamond
        items.put("DIAMOND_HELMET", new ArmorMaterial(Material.DIAMOND_HELMET, ItemFamily.DIAMOND, "Diamond Helmet", ItemType.HELMET, Rarity.UNCOMMON, new ItemStats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_CHESTPLATE", new ArmorMaterial(Material.DIAMOND_CHESTPLATE, ItemFamily.DIAMOND, "Diamond Chestplate", ItemType.CHESTPLATE, Rarity.UNCOMMON, new ItemStats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_LEGGINGS", new ArmorMaterial(Material.DIAMOND_LEGGINGS, ItemFamily.DIAMOND, "Diamond Leggings", ItemType.LEGGINGS, Rarity.UNCOMMON, new ItemStats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_BOOTS", new ArmorMaterial(Material.DIAMOND_BOOTS, ItemFamily.DIAMOND, "Diamond Boots", ItemType.BOOTS, Rarity.UNCOMMON, new ItemStats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        // Golden Skeleton
        items.put("GOLDEN_SKELETON_HELMET", new ArmorMaterial(Material.GOLD_HELMET, ItemFamily.NULL, "Golden Skeleton Helmet", ItemType.HELMET, Rarity.COMMON, new ItemStats(0, 0, 0, 0, 0, 0, 25, 25, 0, 5), "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.WHITE + "Golden Skeleton Bow" + ChatColor.GRAY + ".", null));
        // Pigman
        items.put("PIGMAN_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.PIGMAN, "Pigman Helmet", ItemType.HELMET, Rarity.UNCOMMON, "a94086e2-ae98-42c2-a96b-d7b548e3ae2a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg1Mzg2Zjc4MDMzNDNlNWU2YjdlOGVlMDYxNjc3ZmYxN2U0ZjU2MTAwNTQ3OWQzOTQ3MmIyNjU3ZTA4ODQyZSJ9fX0=", new ItemStats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_CHESTPLATE", new ArmorMaterial(Material.GOLD_CHESTPLATE, ItemFamily.PIGMAN, "Pigman Chestplate", ItemType.CHESTPLATE, Rarity.UNCOMMON, "", "", new ItemStats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.PIGMAN, "Pigman Leggings", ItemType.LEGGINGS, Rarity.UNCOMMON, "", "", new ItemStats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_BOOTS", new ArmorMaterial(Material.GOLD_BOOTS, ItemFamily.PIGMAN, "Pigman Boots", ItemType.BOOTS, Rarity.UNCOMMON, "", "", new ItemStats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        // Necron
        items.put("NECRON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.NECRON_ARMOR, "Necron's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "16b91b55-02b7-3315-bd9a-7da8467e4a96", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzI0OTc5MywKICAicHJvZmlsZUlkIiA6ICIwNjEzY2I1Y2QxYjg0M2JjYjI4OTk1NWU4N2QzMGEyYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJicmVhZGxvYWZzcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYmJiMmZhN2E2Y2EwODcyODBlYTBjYjU2NGI0MWVmMWFlNDA0YTE5ZjdhODEyOGQzZDI4YzUxOWE4NWUwNjNmIgogICAgfQogIH0KfQ", new ItemStats(0, 40, 30, 0, 0, 0, 180, 100, 0, 30), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("NECRON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.NECRON_ARMOR, "Necron's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ItemStats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,65,60)));
        items.put("NECRON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.NECRON_ARMOR, "Necron's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new ItemStats(0, 40, 30, 0, 0, 0, 230, 125, 0, 30), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,92,60)));
        items.put("NECRON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.NECRON_ARMOR, "Necron's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new ItemStats(0, 40, 30, 0, 0, 0, 145, 85, 0, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,110,60)));
        // Storm
        items.put("STORM_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.STORM_ARMOR, "Storm's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "a3552cdc-39ab-3f9f-9373-61c035f61b7d", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzM2NjgxNiwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mODMxNTljNWJiN2Q4MDQ1NGQyZDY0NmIxNTc3NGI3MTE2YWFiY2IzYWY1YjY3NzdhMjc1NzNmYzQ1Zjc4NTRmIgogICAgfQogIH0KfQ", new ItemStats(0, 0, 0, 0, 0, 0, 180, 80, 0, 400), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("STORM_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.STORM_ARMOR,"Storm's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ItemStats(0, 0, 0, 0, 0, 0, 260, 120, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(23,147,196)));
        items.put("STORM_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.STORM_ARMOR, "Storm's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new ItemStats(0, 0, 0, 0, 0, 0, 230, 105, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(23,168,196)));
        items.put("STORM_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.STORM_ARMOR, "Storm's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new ItemStats(0, 0, 0, 0, 0, 0, 145, 65, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(28,212,228)));
        // Superior
        items.put("SUPERIOR_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "7fd17d6f-9e42-478c-8cce-68aec1d52eec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=", new ItemStats(0, 10, 10, 2, 0, 0, 90, 130, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON));
        items.put("SUPERIOR_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.CHESTPLATE, Rarity.LEGENDARY, new ItemStats(0, 10, 10, 2, 0, 0, 150, 190, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,223,17)));
        items.put("SUPERIOR_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.LEGGINGS, Rarity.LEGENDARY, new ItemStats(0, 10, 10, 2, 0, 0, 130, 170, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,223,17)));
        items.put("SUPERIOR_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.SUPERIOR_DRAGON_ARMOR, "Superior Dragon Helmet", ItemType.BOOTS, Rarity.LEGENDARY, new ItemStats(0, 10, 10, 2, 0, 0, 80, 110, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,93,24)));

        // Tools
        items.put("WOOD_PICKAXE", new MiningMaterial(Material.WOOD_PICKAXE, ItemFamily.WOOD, "Wood Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new ItemStats(50, 0), 1, ""));
        items.put("STONE_PICKAXE", new MiningMaterial(Material.STONE_PICKAXE, ItemFamily.STONE, "Stone Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new ItemStats(80, 0), 2, ""));
        items.put("IRON_PICKAXE", new MiningMaterial(Material.IRON_PICKAXE, ItemFamily.IRON, "Iron Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new ItemStats(120, 0), 3, ""));
        items.put("GOLD_PICKAXE", new MiningMaterial(Material.GOLD_PICKAXE, ItemFamily.GOLD, "Gold Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new ItemStats(300, 0), 1, ""));
        items.put("DIAMOND_PICKAXE", new MiningMaterial(Material.DIAMOND_PICKAXE, ItemFamily.DIAMOND, "Diamond Pickaxe", ItemType.PICKAXE, Rarity.UNCOMMON, new ItemStats(230, 0), 4, ""));

        items.put("WOOD_AXE", new MiningMaterial(Material.WOOD_AXE, ItemFamily.WOOD, "Wood Axe", ItemType.AXE, Rarity.COMMON, new ItemStats(50, 0), 1, ""));
        items.put("STONE_AXE", new MiningMaterial(Material.STONE_AXE, ItemFamily.STONE, "Stone Axe", ItemType.AXE, Rarity.COMMON, new ItemStats(80, 5), 1, ""));
        items.put("IRON_AXE", new MiningMaterial(Material.IRON_AXE, ItemFamily.IRON, "Iron Axe", ItemType.AXE, Rarity.COMMON, new ItemStats(120, 0), 1, ""));
        items.put("GOLD_AXE", new MiningMaterial(Material.GOLD_AXE, ItemFamily.GOLD, "Gold Axe", ItemType.AXE, Rarity.COMMON, new ItemStats(300, 0), 1, ""));
        items.put("DIAMOND_AXE", new MiningMaterial(Material.DIAMOND_AXE, ItemFamily.DIAMOND, "Diamond Axe", ItemType.AXE, Rarity.UNCOMMON, new ItemStats(230, 0), 1, ""));

        items.put("WOOD_SHOVEL", new MiningMaterial(Material.WOOD_SPADE, ItemFamily.WOOD, "Wood Shovel", ItemType.SHOVEL, Rarity.COMMON, new ItemStats(50, 0), 1, ""));
        items.put("STONE_SHOVEL", new MiningMaterial(Material.STONE_SPADE, ItemFamily.STONE, "Stone Shovel", ItemType.SHOVEL, Rarity.COMMON, new ItemStats(80, 0), 1, ""));
        items.put("IRON_SHOVEL", new MiningMaterial(Material.IRON_SPADE, ItemFamily.IRON, "Iron Shovel", ItemType.SHOVEL, Rarity.COMMON, new ItemStats(120, 0), 1, ""));
        items.put("GOLD_SHOVEL", new MiningMaterial(Material.GOLD_SPADE, ItemFamily.GOLD, "Gold Shovel", ItemType.SHOVEL, Rarity.COMMON, new ItemStats(300, 0), 1, ""));
        items.put("DIAMOND_SHOVEL", new MiningMaterial(Material.DIAMOND_SPADE, ItemFamily.DIAMOND, "Diamond Shovel", ItemType.SHOVEL, Rarity.UNCOMMON, new ItemStats(230, 0), 1, ""));

        items.put("WOOD_HOE", new MiningMaterial(Material.WOOD_HOE, ItemFamily.WOOD, "Wood Hoe", ItemType.HOE, Rarity.COMMON, new ItemStats(50, 0), 1, ""));
        items.put("STONE_HOE", new MiningMaterial(Material.STONE_HOE, ItemFamily.STONE, "Stone Hoe", ItemType.HOE, Rarity.COMMON, new ItemStats(80, 0), 1, ""));
        items.put("IRON_HOE", new MiningMaterial(Material.IRON_HOE, ItemFamily.IRON, "Iron Hoe", ItemType.HOE, Rarity.COMMON, new ItemStats(120, 0), 1, ""));
        items.put("GOLD_HOE", new MiningMaterial(Material.GOLD_HOE, ItemFamily.GOLD, "Gold Hoe", ItemType.HOE, Rarity.COMMON, new ItemStats(300, 0), 1, ""));
        items.put("DIAMOND_HOE", new MiningMaterial(Material.DIAMOND_HOE, ItemFamily.DIAMOND, "Diamond Hoe", ItemType.HOE, Rarity.UNCOMMON, new ItemStats(230, 0), 1, ""));

        items.put("TREE_CAPITATOR", new MiningMaterial(Material.GOLD_AXE, ItemFamily.JUNGLE_AXE, "Tree Capitator", ItemType.AXE, Rarity.EPIC, 1, "A forceful Gold Axe which can break a large amount of logs in a single hit!", new ItemAbility(AbilityAction.NULL, "", "", 0, 0)));
        items.put("WORLD_EATER", new MiningMaterial(Material.DIAMOND_PICKAXE, ItemFamily.JUNGLE_AXE, "World Eater", ItemType.PICKAXE, Rarity.DIVINE, 10, ChatColor.ITALIC + "I became a world ERROR.", new ItemAbility(AbilityAction.NULL, "", "", 0, 0)));

        // Reforges
        items.put("PRECURSOR_GEAR", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Precursor Gear", Rarity.EPIC, "62ffd058-94c8-3b63-b027-5e9f4d52b78e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRmODkwOTQ5OGMyNWY2ZTc1ZWYxOWUzNzZhN2Y4NGY2MWFmMjM0NTI1ZDYzOWJhNDYzZjk5MWY0YzgyZDAifX19", "Ancient"));
        items.put("WITHER_BLOOD", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Wither's Blood", Rarity.EPIC, "d3323a7b-65e3-394a-95da-018d2f52f917", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTU0MzQ1NTU3OCwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JmNWQyMGUwNjAwMTc0ZGNiYWI3NzQ1ZDk0NDgzMmZiMjA2M2MyYmQxNDkwYzY1MDU5MDFiMjhiZmFhY2Q4ZTUiCiAgICB9CiAgfQp9", "Withered"));
        items.put("NECROMANCER_BROOCH", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Necromancer's Brooch", Rarity.RARE, "ea3ee289-11c8-32b4-8913-c98703b1ab1c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViNmJlYWM1NzM2NWNhZjQ2ZjAzN2YzZDJhM2E0NTdmNmNhZmU2NDc1N2JhZjE0ZTg5OTNjZDJkYTE4Y2ZmNyJ9fX0K", "Necrotic"));

        // Skins
        items.put("NECRON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Necron Skin", Rarity.EPIC, "95b93eb6-4ed9-4841-81ee-729f6d1516e4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYzZWEzZmE1NGE4ZDcwMTQ5NjkyMjY2NmNiMjQyNTg5MGNjZGRhYzY1OTkzYjVkNzViMjY0MmJmN2U2YWY4ZCJ9fX0="));
        items.put("STORM_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Storm Skin", Rarity.EPIC, "4e279894-281e-4ab1-806e-3b3728f899d0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5OTM5ZWE1ZTc2NTVlNjE2YmU4NmEzYTQwMDEzMWMyYzljNzQ1ZTdlMzhlZDBkMWVlMjk4N2JiZTc1YTQ0YSJ9fX0="));
        items.put("SUPERIOR_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Superior Skin", Rarity.EPIC, "8bac9ff5-6006-3a10-99e4-55799153a1f8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJhNzcwNDU5ZmE2ZDY1ZTIyYWE5NTY3OWQ5M2EyODcwYWFmZWE3MGY5ZjFjNmEwZjc4ZWI2NDFlOTI4OTAifX19Cg"));

        // Accessories
        items.put("NETHER_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.NETHER_ARTIFACT, "Nether Artifact", Rarity.EPIC, "0ef495a4-e5df-41c2-b9a2-b2e647cbb491", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0=", new ItemStats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "Reduces the damage taken from Zombie Pigmen, Magma Cubes, Ghasts and Blazes by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ". NEW_LINE NEW_LINE While in the " + ChatColor.GOLD + "Blazing Fortress " + ChatColor.GRAY + "you will receive the damage reduction from all mobs."));
        items.put("SLIME_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.SLIME, "Slime Talisman", Rarity.UNCOMMON, "7f0b0873-df6a-4a19-9bcd-f6c90ef804c7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODk1YWVlYzZiODQyYWRhODY2OWY4NDZkNjViYzQ5NzYyNTk3ODI0YWI5NDRmMjJmNDViZjNiYmI5NDFhYmU2YyJ9fX0=", new ItemStats(0, 2, 2, 0, 0, 0, 0, 0, 0, 0), ""));

        // Wands
        items.put("GYROKINETIC_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.GYROKINETIC_WAND, "Gyrokinetic Wand", ItemType.WAND, Rarity.EPIC, "", new ItemAbility(AbilityAction.LEFT_CLICK, "Gravity Storm", "Create a large " + ChatColor.DARK_PURPLE + "rift " + ChatColor.GRAY + "at aimed location, pulling all mobs toegether.", 1200, 5), new ItemAbility(AbilityAction.RIGHT_CLICK, "Cells Alignment", "Apply " + ChatColor.GREEN + "Aligned " + ChatColor.GRAY + "to 4 nearby players and yourself for " + ChatColor.GREEN + "6 seconds" + ChatColor.GRAY + ".", 220, 10)));
        items.put("TORNADO_WAND", new ToolMaterial(Material.STICK, ItemFamily.TORNADO_WAND, "Tornado Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a tornado on you with random colored blocks.", 10, 1)));
        items.put("TWISTER_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Twister Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes on you with random colored blocks.", 10, 1)));
        items.put("HURRICANE_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Hurricane Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes around you with random colored blocks.", 10, 1)));
        items.put("PARABOLA_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Parabola Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Parabola", "Cast a parabola on you with random colored blocks.", 10, 1)));
        items.put("BUILDERS_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.BUILDERS_WAND, "Builder's Wand", ItemType.WAND, Rarity.LEGENDARY, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Grand Architect", "Right-click the face of a block to extend all connected block faces.", 0, 0), new ItemAbility(AbilityAction.LEFT_CLICK, "Built-in Storage", "Opens the wand storage. Blocks will be placed from your inventory or the wand storage.", 0, 0)));
        items.put("ICE_SPRAY_WAND", new ToolMaterial(Material.STICK, ItemFamily.ICE_SPRAY_WAND, "Ice Spray Wand", ItemType.WAND, Rarity.EPIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Ice Spray", "Produces a cone of ice in front of the caster that deals " + ChatColor.RED + "17,000 " + ChatColor.GRAY + "damage to mobs and freezes them in place for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds! Frozen mobs take " + ChatColor.RED + "10% " + ChatColor.GRAY + "increased damage!", 50, 5)));
        items.put("ERRORMERANG_WAND", new ToolMaterial(Material.STICK, ItemFamily.ERRORMERANG_WAND, "ERRORMerang Wand", ItemType.WAND, Rarity.SPECIAL, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "ERRORMerang", "Error item of eyalc i don't know why", 50, 5)));

        // Scrolls
        items.put("IMPLOSION", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Implosion", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility()));
        items.put("WITHER_SHIELD", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Shield", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility()));
        items.put("SHADOW_WARP", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Shadow Warp", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()));
        items.put("WITHER_IMPACT", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Impact", Rarity.EPIC, "", new ArrayList<>(Arrays.asList(NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility(), NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility(), NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()))));

        // Power Orbs
        items.put("RADIANT_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Radiant Power Orb", ItemType.POWER_ORB, Rarity.UNCOMMON, "10a23a36-4f37-47f7-a28f-fc330de3ff5a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Heals " + ChatColor.RED + "1% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second."));
        items.put("MANA_FLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Mana Flux Power Orb", ItemType.POWER_ORB, Rarity.RARE, "2131e1e3-cd0f-4212-b625-8ccb402e895e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJhZGExYzdmY2M4Y2YzNWRlZmViOTQ0YTRmOGZmYTlhOWQyNjA1NjBmYzdmNWY1ODI2ZGU4MDg1NDM1OTY3YyJ9fX0", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+50% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+10 Strength" + ChatColor.GRAY + "."));
        items.put("OVERFLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Overflux Power Orb", ItemType.POWER_ORB, Rarity.EPIC, "05624a23-a2f1-46b9-9e26-e463855f05c1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+100% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2.5% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+5%"  + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+25 Strength" + ChatColor.GRAY + "."));
        items.put("PLASMA_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Plasma Power Orb", ItemType.POWER_ORB, Rarity.LEGENDARY, "6de57aa8-ffd6-414d-ad9b-85563a6dc417", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "20 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, 30), "Grants " + ChatColor.AQUA + "+125% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "3% " + ChatColor.GRAY + "of max " + Stat.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+7.5%" + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+35 Strength" + ChatColor.GRAY + "."));

        // Items
        items.put("TROLL_EYE", new ToolMaterial(Material.EYE_OF_ENDER, ItemFamily.TROLL, "Troll Eye", ItemType.ITEM, Rarity.EPIC, ChatColor.ITALIC + "Eye of the ender lost ERROR", new ItemAbility(AbilityAction.RIGHT_CLICK, "Get Out From My Face", "Make your target entity shoot ender pearls to every direction", 100, 15)));
        items.put("GRAPPLING_HOOK", new ToolMaterial(Material.FISHING_ROD, ItemFamily.GRAPPLING_HOOK, "Grappling Hook", ItemType.ITEM , Rarity.UNCOMMON, ChatColor.GRAY + "Travel around in style using this " + ChatColor.GREEN + "Grappling Hook" + ChatColor.GRAY + "."));
        items.put("RECOMBABULATOR", new ToolMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Recombobulator 3000", ItemType.ITEM, Rarity.LEGENDARY, "96538e7f-6b56-3557-9b7d-458afe4239e9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjY2QzNmRjOGY3MmFkY2IxZjhjOGU2MWVlODJjZDk2ZWFkMTQwY2YyYTE2YTEzNjZiZTliNWE4ZTNjYzNmYyJ9fX0K", "Use in a " + ChatColor.GREEN + "Reforge Anvil "+ ChatColor.GRAY + "or at the Dungeon Blacksmith to permanently increase the rarity of a piece of armor, weapon, tool or talisman. An item's rarity can only be upgraded once!"));
        items.put("ELECTRO_MAGNET", new ToolMaterial(Material.TORCH, ItemFamily.ELECTRO_MAGNET, "Electro Magnet", ItemType.ITEM, Rarity.EPIC, "", new ItemAbility(AbilityAction.NONE, "Force Field", "Repel hostile creatures and projectiles in radius of " + ChatColor.GREEN + "10 blocks" + ChatColor.GRAY + ".", 0, 0)));

        // Normal Items
//        items.put("ENCHANTED_EYE_OF_ENDER", new NormalMaterial(Material.EYE_OF_ENDER, ItemFamily.ENCHANTED_ITEM, "Enchanted Eye of Ender", ItemType.ITEM, Rarity.UNCOMMON, "", "", true, true));
        items.put("HOT_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Hot Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Combine this Book in an Anvil with a weapon or armor piece to gain a small but permanent stat boost!", true, false, false));
        items.put("FUMING_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Fuming Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Use in an anvil to combine this book with a weapon or armor pieace to gain a small but permanent stat boost!" + " NEW_LINE NEW_LINE " + "This book bypasses the Hot Potato Book limit of 10, allowing you to upgrade an item up to " + ChatColor.GREEN + "15 " + ChatColor.GRAY + "times!", true, false, false));
        items.put("SKYBLOCK_MENU", new NormalMaterial(Material.NETHER_STAR, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, false));
        items.put("SMALL_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Small Backpack", ItemType.ITEM, Rarity.UNCOMMON, "0523fc65-c396-309b-b153-de7d8e5f666d", "eyJ0aW1lc3RhbXAiOjE1NjgyMTI5NjI1MzMsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMWQ4MzdjYTIyMmNiYzBiYzEyNDI2ZjVkYTAxOGMzYTkzMWI0MDYwMDg4MDA5NjBhOWRmMTEyYTU5NmU3ZDYyIn19fQ==", ChatColor.GRAY + "A bag with 9 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("MEDIUM_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Medium Backpack", ItemType.ITEM, Rarity.RARE, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 18 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("LARGE_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Large Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 27 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("GREATER_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Greater Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 36 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("JUMBO_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Jumbo Backpack", ItemType.ITEM, Rarity.LEGENDARY, "49a240cd-8e28-3f5e-8bd3-b690ff1cabfc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY4NDA1MTE2YzFkYWE3Y2UyZjAxMjU5MTQ1OGQ1MDI0NmQwYTQ2N2JjYjk1YTVhMmMwMzNhZWZkNjAwOGI2MyJ9fX0K", ChatColor.GRAY + "A bag with 45 slots which can be placed in your Storage Menu to store additional items.", false, false, false));

        // Other Items
        items.put("NULL_OVOID", new NormalMaterial(Material.MONSTER_EGG, ItemFamily.NULL, "Null Ovoid", ItemType.ITEM, Rarity.RARE, "58", "", true, true));

        items.put("ENCHANTED_BOOK", new BookMaterial(Material.ENCHANTED_BOOK, ItemFamily.BOOK, "Enchanted Book", ItemType.BOOK, Rarity.COMMON, "", ""));

        itemMaterials = (HashMap<String, ItemMaterial>) items.clone();

        for (Material material : Material.values()) {
            if (!items.containsKey(material.name()) && !material.toString().contains("SPADE")) {
                Rarity rarity = Rarity.COMMON;
                if (Functions.isColorable(material)) {
                    if (material == Material.INK_SACK)
                        for (short i = 0; i < 16; i++) {
                            vanillaMaterials.put(Functions.getColorName(15 - i).toUpperCase() + "_DYE", new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(15 - i) + " DYE"), ItemType.ITEM, rarity, i + "", "", ""));
                        }
                    else
                        for (short i = 0; i < 16; i++) {
                            vanillaMaterials.put(Functions.getColorName(i).toUpperCase() + "_" + material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(i) + " " +  material.name()), ItemType.ITEM, rarity, i + "", "", ""));
                        }
                } else {
                    if (material == Material.NETHER_STAR || material == Material.BEDROCK) rarity = Rarity.LEGENDARY;
                    if (material.toString().contains("PICKAXE"))
                        vanillaMaterials.put(material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.PICKAXE, rarity, ""));
                    else if (material.toString().contains("AXE"))
                        vanillaMaterials.put(material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.AXE, rarity, ""));
                    else if (material.toString().contains("HOE"))
                        vanillaMaterials.put(material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.HOE, rarity, ""));
                    else if (material.toString().contains("HELMET"))
                        vanillaMaterials.put(material.name(), new ArmorMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.HELMET, rarity, new ItemStats(), "", null));
                    else if (material.toString().contains("CHESTPLATE"))
                        vanillaMaterials.put(material.name(), new ArmorMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.CHESTPLATE, rarity, new ItemStats(), "", null));
                    else if (material.toString().contains("LEGGINGS"))
                        vanillaMaterials.put(material.name(), new ArmorMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.LEGGINGS, rarity, new ItemStats(), "", null));
                    else if (material.toString().contains("BOOTS"))
                        vanillaMaterials.put(material.name(), new ArmorMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.BOOTS, rarity, new ItemStats(), "", null));
                    else if (material.toString().contains("SWORD"))
                        vanillaMaterials.put(material.name(), new SwordMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), rarity, new ItemStats(), ""));
                    else if (material.toString().contains("BOW"))
                        vanillaMaterials.put(material.name(), new BowMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), rarity, new ItemStats(), "", (ItemAbility) null));
                    else if (material.toString().contains("ROD"))
                        vanillaMaterials.put(material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.ROD, rarity, ""));
                    else
                        vanillaMaterials.put(material.name(), new ToolMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(material.name()), ItemType.ITEM, rarity, "", "", ""));
                }
            }
        }
        vanillaMaterials.put("LAPIS", new ToolMaterial(Material.INK_SACK, ItemFamily.VANILLA, "Lapis Lazuli", ItemType.ITEM, Rarity.COMMON, "4", "", ""));
        vanillaMaterials.remove("BLUE_DYE");

        items.putAll(vanillaMaterials);

        ArrayList<String> keys = new ArrayList<>();
        for (String material : Items.items.keySet()) {
            ItemMaterial item = Items.items.get(material);
            if (item.getType() == ItemType.ITEM && !(item instanceof NormalMaterial) && !material.contains("ENCHANTED_") && !material.contains("BOOK") && !material.contains("BARDING")) {
                keys.add(material);
            }
        }
        for (String string : keys) {
            ItemMaterial item = ItemMaterial.get(string);
            NormalMaterial normalMaterial = new NormalMaterial(item.getMaterial(), ItemFamily.ENCHANTED_ITEM, "Enchanted " + item.getName(), ItemType.ITEM, Rarity.values()[item.getRarity().getLevel()], item.getId(), item.getNbt(), true, true, true);
            Items.items.put("ENCHANTED_" + string, normalMaterial);
        }

        NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");

//        Items.get("NULL", new ItemMaterial(Material.BARRIER, "Null", ItemType.NULL, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.", new ArrayList<>(Arrays.asList(AbilityAction.NONE)), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList("")), new ArrayList<>(Arrays.asList(0)), new ArrayList<>(Arrays.asList(0)), Rarity.SPECIAL));
    }

    public static ItemMaterial get(String name) {
        return Items.items.getOrDefault(name, Items.NULL);
    }
}
