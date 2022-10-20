package me.maxiiiiii.skyblockdragons.item.material;

import com.comphenix.protocol.wrappers.EnumWrappers;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Items {
    public static Map<String, ItemMaterial> items = new HashMap<>();
    public static Map<String, ItemMaterial> itemMaterials = new HashMap<>();
    public static Map<String, PetMaterial> pets = new HashMap<>();
    public static Map<String, ItemMaterial> vanillaMaterials = new HashMap<>();

    public static ToolMaterial NULL = null;

    public static void registerItems() {
        // Swords
        items.put("WITHER_CLOAK", new SwordMaterial(Material.STONE_SWORD, ItemFamily.NULL, "Wither Cloak Sword", Rarity.EPIC, new Stats(190, 135, 0, 0, 0, 0, 0, 250, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Creeper Veil", "Spawns a veil around you that grants you immunity from damage. Costs " + ChatColor.RED + "10% " + ChatColor.GRAY + "of your maximum mana each time you block a hit. Click again to de-activate.")));
        items.put("MAGMA_CLOAK", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.NULL, "MAGMA Cloak Sword", Rarity.LEGENDARY, new Stats(245, 170, 0, 0, 0, 0, 0, 350, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Magma Veil", "Spawns a veil around you that grants you immunity from damage. Costs " + ChatColor.RED + "10% " + ChatColor.GRAY + "of your maximum mana each time you block a hit. Click again to de-activate.")));
        items.put("SWORD_OF_THE_FLAME", new SwordMaterial(Material.GOLD_SWORD, ItemFamily.NULL, "Sword of the Flame", Rarity.SPECIAL, new Stats(), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Flamer", "Creates a line of flames that damage every mob that goes through, click left-click to shoot.", 150, false, 3, 10_000, 0.2)));

        items.put("VALKYRIE", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Valkyrie", Rarity.LEGENDARY, new Stats(270, 145, 0, 0, 0, 60, 0, 0, 0, 60), NecronBladeMaterial.NecronBladeType.VALKYRIE));
        items.put("HYPERION", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Hyperion", Rarity.LEGENDARY, new Stats(260, 150, 0, 0, 0, 30, 0, 0, 0, 350), NecronBladeMaterial.NecronBladeType.HYPERION));
        items.put("SCYLLA", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Scylla", Rarity.LEGENDARY, new Stats(270, 150, 35, 12, 0, 30, 0, 0, 0, 50), NecronBladeMaterial.NecronBladeType.SCYLLA));
        items.put("ASTRAEA", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Astraea", Rarity.LEGENDARY, new Stats(270, 150, 0, 0, 0, 30, 0, 250, 0, 50), NecronBladeMaterial.NecronBladeType.ASTRAEA));
        items.put("NECRON_BLADE", new NecronBladeMaterial(Material.IRON_SWORD, ItemFamily.NECRON_BLADE, "Necron's Blade (Unrefined)", Rarity.LEGENDARY, new Stats(260, 110, 0, 0, 0, 30, 0, 0, 0, 50), NecronBladeMaterial.NecronBladeType.NECRON_BLADE));

        items.put("ERROR_SCYTHE", new SwordMaterial(Material.DIAMOND_HOE, ItemFamily.ERROR_SCYTHE,"ERROR Scythe", Rarity.MYTHIC, new Stats(3500, 0, 0, 0, 0, 0, 0, 0, 0, 0), "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Arrow", ChatColor.GRAY + "Shoots " + ChatColor.GREEN + "arrows" ), new ItemAbility(AbilityAction.RIGHT_SHIFT_CLICK, "Wither Skull", ChatColor.GRAY + "Shoots " + ChatColor.GOLD + "Wither Skull"), new SkillRequirement(SkillType.COMBAT, 0)));

        // Bows
        items.put("BOW", new BowMaterial(Material.BOW, ItemFamily.WOOD, "Bow", Rarity.COMMON, new Stats(5, 5, 0, 0, 0, 0, 0, 0, 0, 0), ""));

        items.put("GOLDEN_SKELETON_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Golden Skeleton Bow", Rarity.COMMON, new Stats(30, 0, 0, 0, 0, 0, 0, 0, 0, 0), ""));

        items.put("ENDER_BOW", new BowMaterial(Material.BOW, ItemFamily.ENDER, "Ender Bow", Rarity.EPIC, new Stats(125, 80, 5, 10, 0, 0), "Deals double damage to Ender Dragons.", new SkillRequirement(SkillType.COMBAT, 15)));

        items.put("TERMINATOR", new BowMaterial(Material.BOW, ItemFamily.JUJU,"Terminator", Rarity.LEGENDARY, new Stats(335, 50, 250, 0, 40, 0, 0, 0, 0, 0), ChatColor.GOLD + "Shortbow: Instantly Shoots! NEW_LINE " + ChatColor.GRAY + "Shoots " + ChatColor.AQUA + "3 " + ChatColor.GRAY + "arrows at once. " + ChatColor.GRAY + "Can damage endermen. NEW_LINE NEW_LINE " + ChatColor.RED + "Divides your RESET_LENGTH " + StatType.CRIT_CHANCE.getIconAndText() + ChatColor.RED + " by 4!", new ItemAbility(AbilityAction.RIGHT_CLICK, "Salvation", ChatColor.GRAY + "Can be casted after landing RESET_LENGTH " + ChatColor.GOLD + "3 " + ChatColor.GRAY + "hits. NEW_LINE Shoot a beam, penetrating up NEW_LINE to " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "foes and dealing " + ChatColor.RED + "2x " + ChatColor.GRAY + "the damage an arrow would. NEW_LINE The beam always crits.", 0, false, 2)));
        items.put("BONEMERANG", new BowMaterial(Material.BONE, ItemFamily.BONEMERANG, "Bonemerang", Rarity.LEGENDARY, new Stats(270, 130, 0, 0, 0, 0, 0, 0, 0, 0), "Deals " + ChatColor.RED + "double damage " + ChatColor.GRAY + "when coming back. Pierces up to " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "foes.", new ItemAbility(AbilityAction.RIGHT_CLICK, "Swing", "Throw the bone a short distance, dealing the damage an arrow would.")));

        items.put("RUNAANS_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Runaan's Bow", Rarity.LEGENDARY, new Stats(160, 50, 0, 0, 0, 0), "",new ItemAbility(AbilityAction.NONE, "Triple Shot", "Shoots 3 arrows at a time! with automatic homing!"), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("YOUNG_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Young Bow", Rarity.LEGENDARY, new Stats(160, 50, 0, 0, 0, 0), "",new ItemAbility(AbilityAction.NONE, "Fast Triple Shot", "Shoots 3 fast arrows at a time! with automatic homing!"), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("STRONG_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Strong Bow", Rarity.LEGENDARY, new Stats(180, 80, 0, 0, 0, 0), "",new ItemAbility(AbilityAction.NONE, "Strong Triple Shot", "Shoots 3 strong arrows at a time! with automatic homing!"), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("HURRICANE_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Hurricane Bow", Rarity.EPIC, new Stats(120, 50, 0, 0, 0, 0), "",new ItemAbility(AbilityAction.NONE, "Hurricane Shot", "Shoots 5 arrows at a time! without homing!"), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("EXPLOSIVE_BOW", new BowMaterial(Material.BOW, ItemFamily.NULL, "Explosive Bow", Rarity.EPIC, new Stats(100, 30, 0, 0, 0, 0), "",new ItemAbility(AbilityAction.NONE, "Explosive", "Arrows explode on impact"), new SkillRequirement(SkillType.COMBAT, 15)));


        // Armors
        // Admin
        items.put("ADMIN_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.ADMIN, "Admin Boots", ItemType.BOOTS, Rarity.SPECIAL, new Stats(10000000, 10000000, 10000000, 10000000, 10000000, 0, 10000000, 10000000, 5000, 10000000), "", ItemFullSet.NULL, (Color) null));
        items.put("ADMIN_LEGGINGS_SUPER_LUCKY", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.ADMIN, "Admin Leggings", ItemType.LEGGINGS, Rarity.SPECIAL, new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 100000, 0, 0, 0, 0, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        items.put("ADMIN_LEGGINGS_LUCKY", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.ADMIN, "Admin Leggings", ItemType.LEGGINGS, Rarity.SPECIAL, new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 100, 0, 0, 0, 0, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        items.put("ADMIN_NOOB_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.ADMIN, "Admin N00B Boots", ItemType.BOOTS, Rarity.SPECIAL, new Stats(1000, 1000, 1000, 1000, 1000, 0, 1000, 1000, 100, 1000), "", ItemFullSet.NULL, (Color) null));
        items.put("ADMIN_SURVIVAL_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.ADMIN, "Admin N00B Boots", ItemType.CHESTPLATE, Rarity.SPECIAL, new Stats(0, 0, 0, 0, 0, 0, 100000, 10000000, 100, 0), "", ItemFullSet.NULL, (Color) null));
        // Leather
        items.put("LEATHER_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.LEATHER, "Leather Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        items.put("LEATHER_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.LEATHER, "Leather Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        items.put("LEATHER_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.LEATHER, "Leather Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        items.put("LEATHER_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.LEATHER, "Leather Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 5, 0, 0, 0), "", ItemFullSet.NULL, (Color) null));
        // Chain
        items.put("CHAIN_HELMET", new ArmorMaterial(Material.CHAINMAIL_HELMET, ItemFamily.CHAIN, "Chain Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_CHESTPLATE", new ArmorMaterial(Material.CHAINMAIL_CHESTPLATE, ItemFamily.CHAIN, "Chain Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.CHAIN, "Chain Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 8, 5, 0, 0), "", ItemFullSet.NULL));
        items.put("CHAIN_BOOTS", new ArmorMaterial(Material.CHAINMAIL_BOOTS, ItemFamily.CHAIN, "Chain Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 8, 0, 5, 0), "", ItemFullSet.NULL));
        // Iron
        items.put("IRON_HELMET", new ArmorMaterial(Material.IRON_HELMET, ItemFamily.IRON, "Iron Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_CHESTPLATE", new ArmorMaterial(Material.IRON_CHESTPLATE, ItemFamily.IRON, "Iron Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_LEGGINGS", new ArmorMaterial(Material.IRON_LEGGINGS, ItemFamily.IRON, "Iron Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        items.put("IRON_BOOTS", new ArmorMaterial(Material.IRON_BOOTS, ItemFamily.IRON, "Iron Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 10, 8, 0, 0), "", ItemFullSet.NULL));
        // Gold
        items.put("GOLD_HELMET", new ArmorMaterial(Material.GOLD_HELMET, ItemFamily.GOLD, "Gold Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_CHESTPLATE", new ArmorMaterial(Material.GOLD_CHESTPLATE, ItemFamily.GOLD, "Gold Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_LEGGINGS", new ArmorMaterial(Material.GOLD_LEGGINGS, ItemFamily.GOLD, "Gold Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        items.put("GOLD_BOOTS", new ArmorMaterial(Material.GOLD_BOOTS, ItemFamily.GOLD, "Gold Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 15, 10, 0, 0), "", ItemFullSet.NULL));
        // Lapis
        items.put("LAPIS_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.LAPIS, "Lapis Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.LAPIS, "Lapis Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.LAPIS, "Lapis Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        items.put("LAPIS_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.LAPIS, "Lapis Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 20, 12, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(55, 55, 215)));
        // Redstone
        items.put("REDSTONE_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.REDSTONE, "Redstone Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.REDSTONE, "Redstone Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.REDSTONE, "Redstone Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        items.put("REDSTONE_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.REDSTONE, "Redstone Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 25, 15, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(200, 20, 20)));
        // Emerald
        items.put("EMERALD_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.EMERALD, "Emerald Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.EMERALD, "Emerald Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.EMERALD, "Emerald Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        items.put("EMERALD_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.EMERALD, "Emerald Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 35, 20, 0, 0), "", ItemFullSet.NULL, Color.fromRGB(5, 240, 15)));
        // Diamond
        items.put("DIAMOND_HELMET", new ArmorMaterial(Material.DIAMOND_HELMET, ItemFamily.DIAMOND, "Diamond Helmet", ItemType.HELMET, Rarity.UNCOMMON, new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_CHESTPLATE", new ArmorMaterial(Material.DIAMOND_CHESTPLATE, ItemFamily.DIAMOND, "Diamond Chestplate", ItemType.CHESTPLATE, Rarity.UNCOMMON, new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_LEGGINGS", new ArmorMaterial(Material.DIAMOND_LEGGINGS, ItemFamily.DIAMOND, "Diamond Leggings", ItemType.LEGGINGS, Rarity.UNCOMMON, new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        items.put("DIAMOND_BOOTS", new ArmorMaterial(Material.DIAMOND_BOOTS, ItemFamily.DIAMOND, "Diamond Boots", ItemType.BOOTS, Rarity.UNCOMMON, new Stats(0, 2, 3, 0, 0, 0, 45, 25, 0, 0), "", ItemFullSet.NULL));
        // Obsidian
        items.put("OBSIDIAN_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.OBSIDIAN, "Obsidian Helmet", ItemType.HELMET, Rarity.RARE, "4871fc40-b2c7-431d-9eb8-b54cd666dca7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MGI4N2Q1MjI3MWQyYTc1NWRlZGM4Mjg3N2UwZWQzZGY2N2RjYzQyZWE0NzllYzE0NjE3NmIwMjc3OWE1In19fQ==", new Stats(0, 8, 12, 0, 0, 0, 95, 45, -5, 5), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 3)));
        items.put("OBSIDIAN_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.OBSIDIAN, "Obsidian Chestplate", ItemType.CHESTPLATE, Rarity.RARE, new Stats(0, 8, 12, 0, 0, 0, 95, 45, -5, 5), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 3)));
        items.put("OBSIDIAN_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.OBSIDIAN, "Obsidian Leggings", ItemType.LEGGINGS, Rarity.RARE, new Stats(0, 8, 12, 0, 0, 0, 95, 45, -5, 5), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 3)));
        items.put("OBSIDIAN_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.OBSIDIAN, "Obsidian Boots", ItemType.BOOTS, Rarity.RARE, new Stats(0, 8, 12, 0, 0, 0, 95, 45, -5, 5), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 3)));
        // Golden Skeleton
        items.put("GOLDEN_SKELETON_HELMET", new ArmorMaterial(Material.GOLD_HELMET, ItemFamily.NULL, "Golden Skeleton Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(0, 0, 0, 0, 0, 0, 25, 25, 0, 5), "Deals " + ChatColor.GREEN + "+20% " + ChatColor.GRAY + "damage with " + ChatColor.WHITE + "Golden Skeleton Bow" + ChatColor.GRAY + ".", null));
        // Pigman
        items.put("PIGMAN_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.PIGMAN, "Pigman Helmet", ItemType.HELMET, Rarity.UNCOMMON, "a94086e2-ae98-42c2-a96b-d7b548e3ae2a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjg1Mzg2Zjc4MDMzNDNlNWU2YjdlOGVlMDYxNjc3ZmYxN2U0ZjU2MTAwNTQ3OWQzOTQ3MmIyNjU3ZTA4ODQyZSJ9fX0=", new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_CHESTPLATE", new ArmorMaterial(Material.GOLD_CHESTPLATE, ItemFamily.PIGMAN, "Pigman Chestplate", ItemType.CHESTPLATE, Rarity.UNCOMMON, "", "", new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.PIGMAN, "Pigman Leggings", ItemType.LEGGINGS, Rarity.UNCOMMON, "", "", new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        items.put("PIGMAN_BOOTS", new ArmorMaterial(Material.GOLD_BOOTS, ItemFamily.PIGMAN, "Pigman Boots", ItemType.BOOTS, Rarity.UNCOMMON, "", "", new Stats(0, 5, 5, 0, 0, 0, 70, 40, 0, 10), "", ItemFullSet.PIGMAN));
        // Ender
        items.put("ENDER_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.ENDER, "Ender Helmet", ItemType.HELMET, Rarity.RARE, "7ddb45e0-6b48-4e42-943e-b0a6dcec34ee", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjcxNWNhMGY3NDI1NDRhZTNjYTEwNDI5NzU3OGMyZWQ3MDBlYTNhNTQ5ODA0MTM1MTJmNWU3YTBiYzA2NzI5YSJ9fX0=", new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 12)));
        items.put("ENDER_CHESTPLATE", new ArmorMaterial(Material.CHAINMAIL_CHESTPLATE, ItemFamily.ENDER, "Ender Chestplate", ItemType.CHESTPLATE, Rarity.RARE, "", "", new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 12)));
        items.put("ENDER_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.ENDER, "Ender Leggings", ItemType.LEGGINGS, Rarity.RARE, "", "", new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 12)));
        items.put("ENDER_BOOTS", new ArmorMaterial(Material.CHAINMAIL_BOOTS, ItemFamily.ENDER, "Ender Boots", ItemType.BOOTS, Rarity.RARE, "", "", new Stats(0, 5, 5, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 12)));
        // Ender Guard
        items.put("ENDER_GUARD_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.ENDER, "Ender Guard Helmet", ItemType.HELMET, Rarity.EPIC, "0dc3174c-0fac-4cb1-8c5f-3dba5c1d335b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQ4YjZmODc4OTAxNWVhNjdiMWQ3ZmE1N2E1OWQ3YTRhZDJhZTc0Y2U3ZDk5ZTY1ZmZhZDExNDg5ZTdkYzY1In19fQ==", new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("ENDER_GUARD_CHESTPLATE", new ArmorMaterial(Material.CHAINMAIL_CHESTPLATE, ItemFamily.ENDER, "Ender Guard Chestplate", ItemType.CHESTPLATE, Rarity.EPIC, "", "", new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("ENDER_GUARD_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.ENDER, "Ender Guard Leggings", ItemType.LEGGINGS, Rarity.EPIC, "", "", new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, Color.fromRGB(182, 9, 176), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("ENDER_GUARD_BOOTS", new ArmorMaterial(Material.CHAINMAIL_BOOTS, ItemFamily.ENDER, "Ender Guard Boots", ItemType.BOOTS, Rarity.EPIC, "", "", new Stats(0, 6, 6, 2, 0, 0, 135, 60, 0, 10), "Increases all damage stats by " + ChatColor.GREEN + "10% " + ChatColor.GRAY + "in " + ChatColor.DARK_PURPLE + "The End" + ChatColor.GRAY + ".", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        // Necron
        items.put("NECRON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.NECRON_ARMOR, "Necron's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "16b91b55-02b7-3315-bd9a-7da8467e4a96", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzI0OTc5MywKICAicHJvZmlsZUlkIiA6ICIwNjEzY2I1Y2QxYjg0M2JjYjI4OTk1NWU4N2QzMGEyYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJicmVhZGxvYWZzcyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYmJiMmZhN2E2Y2EwODcyODBlYTBjYjU2NGI0MWVmMWFlNDA0YTE5ZjdhODEyOGQzZDI4YzUxOWE4NWUwNjNmIgogICAgfQogIH0KfQ", new Stats(0, 40, 30, 0, 0, 0, 180, 100, 0, 30), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("NECRON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.NECRON_ARMOR, "Necron's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,65,60)));
        items.put("NECRON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.NECRON_ARMOR, "Necron's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 230, 125, 0, 30), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,92,60)));
        items.put("NECRON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.NECRON_ARMOR, "Necron's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 145, 85, 0, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(231,110,60)));
        // Storm
        items.put("STORM_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.STORM_ARMOR, "Storm's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "a3552cdc-39ab-3f9f-9373-61c035f61b7d", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzM2NjgxNiwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mODMxNTljNWJiN2Q4MDQ1NGQyZDY0NmIxNTc3NGI3MTE2YWFiY2IzYWY1YjY3NzdhMjc1NzNmYzQ1Zjc4NTRmIgogICAgfQogIH0KfQ", new Stats(0, 0, 0, 0, 0, 0, 180, 80, 0, 400), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("STORM_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.STORM_ARMOR,"Storm's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 260, 120, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(23,147,196)));
        items.put("STORM_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.STORM_ARMOR, "Storm's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 230, 105, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(23,168,196)));
        items.put("STORM_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.STORM_ARMOR, "Storm's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 145, 65, 0, 250), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(28,212,228)));
        // Goldor
        items.put("GOLDOR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.GOLDOR_ARMOR, "Goldor's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "ab7539c4-b79d-4a7b-9b1b-2a855bc4b210", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjM5OGNmM2Y2MzEyNDJlZjNhOTgxODRkZDk0MjgzZjMxOGZiYzJkOGM4ZDNhNDI5NzM0MWIwM2ZkM2FmNmZlMCJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 210, 180, 0, 30), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("GOLDOR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.GOLDOR_ARMOR,"Goldor's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 310, 275, 0, 20), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(69,65,60)));
        items.put("GOLDOR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.GOLDOR_ARMOR, "Goldor's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 250, 220, 0, 20), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(101,96,90)));
        items.put("GOLDOR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.GOLDOR_ARMOR, "Goldor's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 190, 165, 0, 20), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(136,131,126)));
        // Maxor
        items.put("MAXOR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.MAXOR_ARMOR, "Maxor's Helmet", ItemType.HELMET, Rarity.LEGENDARY, "51932520-2800-4162-9fcf-2898ba93a1a2", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ1MGE4ZmQ3YTFkZDQ3MWU4ZjkyOTJmMTI4M2U5OWM1ZTUyYTUxODYzMDJiZmYzYTkzZjgwN2ZhOWI0NDJhNCJ9fX0=", new Stats(0, 0, 45, 0, 0, 0, 180, 80, 30, 15), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR));
        items.put("MAXOR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.MAXOR_ARMOR,"Maxor's Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 45, 0, 0, 0, 260, 110, 30, 15), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(74,20,183)));
        items.put("MAXOR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.MAXOR_ARMOR,"Maxor's Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 45, 0, 0, 0, 230, 105, 30, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(93,47,185)));
        items.put("MAXOR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.MAXOR_ARMOR, "Maxor's Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 45, 0, 0, 0, 145, 65, 30, 10), "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".", ItemFullSet.WITHER_ARMOR, Color.fromRGB(137,105,200)));
        // Superior
        items.put("SUPERIOR_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "7fd17d6f-9e42-478c-8cce-68aec1d52eec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=", new Stats(0, 20, 10, 2, 0, 0, 90, 130, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, new SkillRequirement(SkillType.COMBAT, 21)));
        items.put("SUPERIOR_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 10, 2, 0, 0, 150, 190, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,223,17), new SkillRequirement(SkillType.COMBAT, 21)));
        items.put("SUPERIOR_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 10, 2, 0, 0, 130, 170, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,223,17), new SkillRequirement(SkillType.COMBAT, 21)));
        items.put("SUPERIOR_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 10, 2, 0, 0, 80, 110, 3, 25), "", ItemFullSet.SUPERIOR_DRAGON, Color.fromRGB(242,93,24), new SkillRequirement(SkillType.COMBAT, 21)));
        // Strong
        items.put("STRONG_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.STRONG_DRAGON, "Strong Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "007b09db-936a-4ee1-8e60-6f61172bfe4e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWZkZTA5NjAzYjAyMjViOWQyNGE3M2EwZDNmM2UzYWYyOTA1OGQ0NDhjY2Q3Y2U1YzY3Y2QwMmZhYjBmZjY4MiJ9fX0=", new Stats(0, 25, 0, 0, 0, 0, 70, 110, 0, 0), "", ItemFullSet.STRONG_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("STRONG_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.STRONG_DRAGON, "Strong Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 25, 0, 0, 0, 0, 120, 160, 0, 0), "", ItemFullSet.STRONG_DRAGON, Color.fromRGB(217,30,65), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("STRONG_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.STRONG_DRAGON, "Strong Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 25, 0, 0, 0, 0, 100, 140, 0, 0), "", ItemFullSet.STRONG_DRAGON, Color.fromRGB(224,148,25), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("STRONG_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.STRONG_DRAGON, "Strong Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 25, 0, 0, 0, 0, 60, 90, 0, 0), "", ItemFullSet.STRONG_DRAGON, Color.fromRGB(240,209,36), new SkillRequirement(SkillType.COMBAT, 15)));
        // Strong
        items.put("YOUNG_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.YOUNG_DRAGON, "Young Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "12e3458b-42ba-49ba-bdee-0a746f16b143", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWNmNmFlNGQ4MjdiNzdlMzNlNDMxMGM3ZmJjODY4M2E4MWZlYjJiNTJhZDFmMjYwNGM2MWQzMzZjNTdhMTljMCJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 70, 110, 20, 0), "", ItemFullSet.YOUNG_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("YOUNG_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.YOUNG_DRAGON, "Young Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 120, 160, 20, 0), "", ItemFullSet.YOUNG_DRAGON, Color.fromRGB(221,228,240), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("YOUNG_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.YOUNG_DRAGON, "Young Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 100, 140, 20, 0), "", ItemFullSet.YOUNG_DRAGON, Color.fromRGB(221,228,240), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("YOUNG_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.YOUNG_DRAGON, "Young Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 60, 90, 20, 0), "", ItemFullSet.YOUNG_DRAGON, Color.fromRGB(221,228,240), new SkillRequirement(SkillType.COMBAT, 15)));
        // Unstable
        items.put("UNSTABLE_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "787379c1-f850-49e1-9ffc-d5311b61e762", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjkyMmI1ZjhkNTU0Y2E5MjNmOTY4MzJhNWE0ZTkxNjliYzJjZGIzNjBhMmIwNmViZWMwOWI2YTZhZjQ2MThlMyJ9fX0=", new Stats(0, 0, 15, 5, 0, 0, 70, 110, 0, 0), "", ItemFullSet.UNSTABLE_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("UNSTABLE_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 15, 5, 0, 0, 120, 160, 0, 0), "", ItemFullSet.UNSTABLE_DRAGON, Color.fromRGB(178,18,227), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("UNSTABLE_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 15, 5, 0, 0, 100, 140, 0, 0), "", ItemFullSet.UNSTABLE_DRAGON, Color.fromRGB(178,18,227), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("UNSTABLE_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 15, 5, 0, 0, 60, 90, 0, 0), "", ItemFullSet.UNSTABLE_DRAGON, Color.fromRGB(178,18,227), new SkillRequirement(SkillType.COMBAT, 15)));
        // Wise
        items.put("WISE_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.WISE_DRAGON, "Wise Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "ab70371e-f3ed-461c-b3d7-22032884e470", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWEyOTg0Y2YwN2M0OGRhOTcyNDgxNmE4ZmYwODY0YmM2OGJjZTY5NGNlOGJkNmRiMjExMmI2YmEwMzEwNzBkZSJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 70, 110, 0, 75), "", ItemFullSet.WISE_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("WISE_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.WISE_DRAGON, "Wise Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 120, 160, 0, 75), "", ItemFullSet.WISE_DRAGON, Color.fromRGB(41,240,233), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("WISE_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.WISE_DRAGON, "Wise Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 100, 140, 0, 75), "", ItemFullSet.WISE_DRAGON, Color.fromRGB(41,240,233), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("WISE_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.WISE_DRAGON, "Wise Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 60, 90, 0, 75), "", ItemFullSet.WISE_DRAGON, Color.fromRGB(41,240,233), new SkillRequirement(SkillType.COMBAT, 15)));
        // Protector
        items.put("PROTECTOR_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.PROTECTOR_DRAGON, "Protector Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "15d988d6-db86-4fa4-ae92-025f6fdad9f5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjM3YTU5NmNkYzRiMTFhOTk0OGZmYTM4YzJhYTNjNjk0MmVmNDQ5ZWIwYTM5ODIyODFkM2E1YjVhMTRlZjZhZSJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 70, 135, 0, 0), "", ItemFullSet.PROTECTOR_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("PROTECTOR_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.PROTECTOR_DRAGON, "Protector Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 120, 185, 0, 0), "", ItemFullSet.PROTECTOR_DRAGON, Color.fromRGB(153,151,139), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("PROTECTOR_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.PROTECTOR_DRAGON, "Protector Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 100, 165, 0, 0), "", ItemFullSet.PROTECTOR_DRAGON, Color.fromRGB(153,151,139), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("PROTECTOR_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.PROTECTOR_DRAGON, "Protector Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 60, 115, 0, 0), "", ItemFullSet.PROTECTOR_DRAGON, Color.fromRGB(153,151,139), new SkillRequirement(SkillType.COMBAT, 15)));
        // Old
        items.put("OLD_DRAGON_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.OLD_DRAGON, "Old Dragon Helmet", ItemType.HELMET, Rarity.LEGENDARY, "59919a61-9e44-4e2b-99fc-84b54f8e6f5c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTllOWU1NjAwNDEwYzFkMDI1NDQ3NGE4MWZlY2ZiMzg4NWMxY2Y2ZjUwNDE5MGQ4NTZmMGVjN2M5ZjA1NWM0MiJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 90, 110, 0, 0), "", ItemFullSet.OLD_DRAGON, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("OLD_DRAGON_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.OLD_DRAGON, "Old Dragon Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 150, 160, 0, 0), "", ItemFullSet.OLD_DRAGON, Color.fromRGB(240,230,170), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("OLD_DRAGON_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.OLD_DRAGON, "Old Dragon Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 130, 140, 0, 0), "", ItemFullSet.OLD_DRAGON, Color.fromRGB(240,230,170), new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("OLD_DRAGON_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.OLD_DRAGON, "Old Dragon Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 0, 0, 0, 0, 0, 80, 90, 0, 0), "", ItemFullSet.OLD_DRAGON, Color.fromRGB(240,230,170), new SkillRequirement(SkillType.COMBAT, 15)));

        // Eternity
        items.put("ETERNITY_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.ETERNITY, "Enternity Helmet", ItemType.HELMET, Rarity.LEGENDARY, "c9add4d5-2750-4246-ace7-1437f05154ec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2MzZTU0YzE2NGY0NWY2ZmQ5ZWNmZmQ3NmIxYjYwZGUzNWU4OTZlODUxMDVmOGVkZDU1NTZmNmEzMDU3YjdmMyJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 90, 110, 0, 0), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 25)));
        items.put("ETERNITY_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.ETERNITY, "Enternity Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("ETERNITY_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.ETERNITY, "Enternity Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("ETERNITY_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.ETERNITY, "Enternity Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 40, 30, 0, 0, 0, 260, 140, 0, 10), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));

        // Bear
        items.put("GRIZZLY_BEAR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.GRIZZLY_BEAR, "Grizzly Bear Helmet", ItemType.HELMET, Rarity.LEGENDARY, "16a37715-3d58-407c-87f8-c18c1198ea6d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmRmZTcwOWRhMmNmMzJlMWUyNzNmNWQ3MmM2NmZjMzZkYzY0MzY2ZmVjZDk4MGFhNmIxNDgwZDc5YmM2NGNkNyJ9fX0=", new Stats(0, 20, 20, 15, 0, 20, 800, 300, 0, 50), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("GRIZZLY_BEAR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.GRIZZLY_BEAR, "Grizzly Bear Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 20, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("GRIZZLY_BEAR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.GRIZZLY_BEAR, "Grizzly Bear Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 20, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("GRIZZLY_BEAR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.GRIZZLY_BEAR, "Grizzly Bear Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 20, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));
        items.put("BEAR_EYE", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Bear Eye", ItemType.ITEM, Rarity.EPIC, "3256c7a2-a510-4467-b1d2-fa2b35d00cd2", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVjMmNhNDBhZDhhOWViYjljYWJiZTBkMWEwNTJiNWVlMjQzMDU5M2U1MmQ0NTI2M2ZjNGFhZjI5ZDUyNDY5MSJ9fX0=", "Used to spawn the Bear boss", false, false, false));

        items.put("POLAR_BEAR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.POLAR_BEAR, "Polar Bear Helmet", ItemType.HELMET, Rarity.LEGENDARY, "3fcab007-18aa-4730-b228-64712d40f105", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRmZTkyNjkyMmZiYjQwNmYzNDNiMzRhMTBiYjk4OTkyY2VlNDQxMDEzN2QzZjg4MDk5NDI3YjIyZGUzYWI5MCJ9fX0=", new Stats(0, 20, 20, 15, 0, 10, 800, 500, 0, 50), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("POLAR_BEAR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.POLAR_BEAR, "Polar Bear Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 500, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("POLAR_BEAR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.POLAR_BEAR, "Polar Bear Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 500, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("POLAR_BEAR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.POLAR_BEAR, "Polar Bear Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 500, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));

        items.put("PANDA_BEAR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.PANDA_BEAR, "Panda Bear Helmet", ItemType.HELMET, Rarity.LEGENDARY, "3fcab007-18aa-4730-b228-64712d40f105", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRmZTkyNjkyMmZiYjQwNmYzNDNiMzRhMTBiYjk4OTkyY2VlNDQxMDEzN2QzZjg4MDk5NDI3YjIyZGUzYWI5MCJ9fX0=", new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 50), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("PANDA_BEAR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.PANDA_BEAR, "Panda Bear Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("PANDA_BEAR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.PANDA_BEAR, "Panda Bear Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("PANDA_BEAR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.PANDA_BEAR, "Panda Bear Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 50), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));

        items.put("KOALA_BEAR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.KOALA_BEAR, "Koala Bear Helmet", ItemType.HELMET, Rarity.LEGENDARY, "3fcab007-18aa-4730-b228-64712d40f105", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRmZTkyNjkyMmZiYjQwNmYzNDNiMzRhMTBiYjk4OTkyY2VlNDQxMDEzN2QzZjg4MDk5NDI3YjIyZGUzYWI5MCJ9fX0=", new Stats(0, 20, 20, 15, 0, 10, 800, 500, 0, 50), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("KOALA_BEAR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.KOALA_BEAR, "Koala Bear Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 300), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("KOALA_BEAR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.KOALA_BEAR, "Koala Bear Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 300), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("KOALA_BEAR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.KOALA_BEAR, "Koala Bear Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 10, 800, 300, 0, 300), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));

        items.put("RED_PANDA_BEAR_HELMET", new ArmorMaterial(Material.SKULL_ITEM, ItemFamily.RED_PANDA_BEAR, "Red Panda Bear Helmet", ItemType.HELMET, Rarity.LEGENDARY, "3fcab007-18aa-4730-b228-64712d40f105", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRmZTkyNjkyMmZiYjQwNmYzNDNiMzRhMTBiYjk4OTkyY2VlNDQxMDEzN2QzZjg4MDk5NDI3YjIyZGUzYWI5MCJ9fX0=", new Stats(0, 20, 20, 15, 0, 15, 950, 500, 0, 70), "", ItemFullSet.NULL, new SkillRequirement(SkillType.COMBAT, 15)));
        items.put("RED_PANDA_BEAR_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.RED_PANDA_BEAR, "Red Panda Bear Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 15, 950, 500, 0, 70), "", ItemFullSet.NULL, Color.fromRGB(67,143,167)));
        items.put("RED_PANDA_BEAR_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.RED_PANDA_BEAR, "Red Panda Bear Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 15, 950, 500, 0, 70), "", ItemFullSet.NULL, Color.fromRGB(191,95,194)));
        items.put("RED_PANDA_BEAR_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.RED_PANDA_BEAR, "Red Panda Bear Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(0, 20, 20, 15, 0, 15, 950, 500, 0, 70), "", ItemFullSet.NULL, Color.fromRGB(210,213,35)));

        // Deeper Mines
        // Cobalt
        items.put("COBALT_HELMET", new ArmorMaterial(Material.DIAMOND_HELMET, ItemFamily.COBALT, "Cobalt Helmet", ItemType.HELMET, Rarity.COMMON, new Stats(35, 25, 0, 20, 75, 20, 0), "", ItemFullSet.COBALT));
        items.put("COBALT_CHESTPLATE", new ArmorMaterial(Material.DIAMOND_CHESTPLATE, ItemFamily.COBALT, "Cobalt Chestplate", ItemType.CHESTPLATE, Rarity.COMMON, new Stats(50, 35, 0, 20, 90, 25, 0), "", ItemFullSet.COBALT));
        items.put("COBALT_LEGGINGS", new ArmorMaterial(Material.DIAMOND_LEGGINGS, ItemFamily.COBALT, "Cobalt Leggings", ItemType.LEGGINGS, Rarity.COMMON, new Stats(40, 30, 0, 20, 80, 20, 0), "", ItemFullSet.COBALT));
        items.put("COBALT_BOOTS", new ArmorMaterial(Material.DIAMOND_BOOTS, ItemFamily.COBALT, "Cobalt Boots", ItemType.BOOTS, Rarity.COMMON, new Stats(35, 20, 0, 20, 70, 15, 0), "", ItemFullSet.COBALT));
        // Chlorophyte
        items.put("CHLOROPHYTE_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.CHLOROPHYTE, "Chlorophyte Helmet", ItemType.HELMET, Rarity.UNCOMMON, new Stats(50, 35, 0, 20, 115, 25, 0), "", ItemFullSet.CHLOROPHYTE, Color.fromRGB(27, 190, 0)));
        items.put("CHLOROPHYTE_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.CHLOROPHYTE, "Chlorophyte Chestplate", ItemType.CHESTPLATE, Rarity.UNCOMMON, new Stats(65, 45, 0, 20, 125, 35, 0), "", ItemFullSet.CHLOROPHYTE, Color.fromRGB(20, 205, 0)));
        items.put("CHLOROPHYTE_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.CHLOROPHYTE, "Chlorophyte Leggings", ItemType.LEGGINGS, Rarity.UNCOMMON, new Stats(60, 40, 0, 20, 120, 30, 0), "", ItemFullSet.CHLOROPHYTE, Color.fromRGB(23, 228, 0)));
        items.put("CHLOROPHYTE_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.CHLOROPHYTE, "Chlorophyte Boots", ItemType.BOOTS, Rarity.UNCOMMON, new Stats(45, 35, 0, 20, 110, 25, 0), "", ItemFullSet.CHLOROPHYTE, Color.fromRGB(26, 255, 0)));
        // Luminate
        items.put("LUMINATE_HELMET", new ArmorMaterial(Material.CHAINMAIL_HELMET, ItemFamily.LUMINATE, "Luminate Helmet", ItemType.HELMET, Rarity.RARE, new Stats(70, 60, 0, 20, 165, 40, 2), "", ItemFullSet.LUMINATE));
        items.put("LUMINATE_CHESTPLATE", new ArmorMaterial(Material.IRON_CHESTPLATE, ItemFamily.LUMINATE, "Luminate Chestplate", ItemType.CHESTPLATE, Rarity.RARE, new Stats(85, 70, 0, 20, 125, 50, 2), "", ItemFullSet.LUMINATE));
        items.put("LUMINATE_LEGGINGS", new ArmorMaterial(Material.CHAINMAIL_LEGGINGS, ItemFamily.LUMINATE, "Luminate Leggings", ItemType.LEGGINGS, Rarity.RARE, new Stats(80, 65, 0, 20, 120, 45, 2), "", ItemFullSet.LUMINATE));
        items.put("LUMINATE_BOOTS", new ArmorMaterial(Material.IRON_BOOTS, ItemFamily.LUMINATE, "Luminate Boots", ItemType.BOOTS, Rarity.RARE, new Stats(65, 55, 0, 20, 150, 35, 2), "", ItemFullSet.LUMINATE));
        // Dernic
        items.put("DERNIC_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.LUMINATE, "Dernic Helmet", ItemType.HELMET, Rarity.EPIC, new Stats(85, 90, 0, 30, 190, 65, 5), "", ItemFullSet.DERNIC, Color.fromRGB(200, 105, 40)));
        items.put("DERNIC_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.LUMINATE, "Dernic Chestplate", ItemType.CHESTPLATE, Rarity.EPIC, new Stats(100, 105, 0, 30, 205, 80, 5), "", ItemFullSet.DERNIC, Color.fromRGB(190, 100, 35)));
        items.put("DERNIC_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.LUMINATE, "Dernic Leggings", ItemType.LEGGINGS, Rarity.EPIC, new Stats(90, 100, 0, 30, 200, 75, 5), "", ItemFullSet.DERNIC, Color.fromRGB(140, 85, 25)));
        items.put("DERNIC_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.LUMINATE, "Dernic Boots", ItemType.BOOTS, Rarity.EPIC, new Stats(80, 85, 0, 30, 180, 60, 5), "", ItemFullSet.DERNIC, Color.fromRGB(130, 85, 25)));
        // Hematite
        items.put("HEMATITE_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.HEMATITE, "Hematite Helmet", ItemType.HELMET, Rarity.LEGENDARY, new Stats(115, 90, 50, 30, 275, 95, 10), "", ItemFullSet.HEMATITE, Color.fromRGB(120, 0, 0)));
        items.put("HEMATITE_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.HEMATITE, "Hematite Chestplate", ItemType.CHESTPLATE, Rarity.LEGENDARY, new Stats(130, 105, 80, 30, 310, 110, 10), "", ItemFullSet.HEMATITE, Color.fromRGB(100, 0, 0)));
        items.put("HEMATITE_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.HEMATITE, "Hematite Leggings", ItemType.LEGGINGS, Rarity.LEGENDARY, new Stats(125, 100, 60, 30, 290, 105, 10), "", ItemFullSet.HEMATITE, Color.fromRGB(70, 0, 0)));
        items.put("HEMATITE_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.HEMATITE  , "Hematite Boots", ItemType.BOOTS, Rarity.LEGENDARY, new Stats(105, 85, 40, 30, 260, 90, 10), "", ItemFullSet.HEMATITE, Color.fromRGB(40, 0, 0)));
        // Void Crystal
        items.put("VOID_CRYSTAL_HELMET", new ArmorMaterial(Material.LEATHER_HELMET, ItemFamily.VOID_CRYSTAL, "Void Crystal Helmet", ItemType.HELMET, Rarity.MYTHIC, new Stats(135, 125, 70, 30, 275, 95, 20), "", ItemFullSet.VOID_CRYSTAL, Color.fromRGB(160, 20, 210)));
        items.put("VOID_CRYSTAL_CHESTPLATE", new ArmorMaterial(Material.LEATHER_CHESTPLATE, ItemFamily.VOID_CRYSTAL, "Void Crystal Chestplate", ItemType.CHESTPLATE, Rarity.MYTHIC, new Stats(145, 140, 120, 30, 310, 110, 20), "", ItemFullSet.VOID_CRYSTAL, Color.fromRGB(180, 40, 225)));
        items.put("VOID_CRYSTAL_LEGGINGS", new ArmorMaterial(Material.LEATHER_LEGGINGS, ItemFamily.VOID_CRYSTAL, "Void Crystal Leggings", ItemType.LEGGINGS, Rarity.MYTHIC, new Stats(140, 135, 80, 30, 290, 105, 20), "", ItemFullSet.VOID_CRYSTAL, Color.fromRGB(190, 60, 230)));
        items.put("VOID_CRYSTAL_BOOTS", new ArmorMaterial(Material.LEATHER_BOOTS, ItemFamily.VOID_CRYSTAL, "Void Crystal Boots", ItemType.BOOTS, Rarity.MYTHIC, new Stats(130, 120, 60, 30, 260, 90, 20), "", ItemFullSet.VOID_CRYSTAL, Color.fromRGB(200, 70, 235)));
        // Tools
        items.put("WOOD_PICKAXE", new MiningMaterial(Material.WOOD_PICKAXE, ItemFamily.WOOD, "Wood Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new Stats(50, 0), 1, ""));
        items.put("STONE_PICKAXE", new MiningMaterial(Material.STONE_PICKAXE, ItemFamily.STONE, "Stone Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new Stats(80, 0), 2, ""));
        items.put("IRON_PICKAXE", new MiningMaterial(Material.IRON_PICKAXE, ItemFamily.IRON, "Iron Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new Stats(120, 0), 3, ""));
        items.put("GOLD_PICKAXE", new MiningMaterial(Material.GOLD_PICKAXE, ItemFamily.GOLD, "Gold Pickaxe", ItemType.PICKAXE, Rarity.COMMON, new Stats(300, 0), 1, ""));
        items.put("DIAMOND_PICKAXE", new MiningMaterial(Material.DIAMOND_PICKAXE, ItemFamily.DIAMOND, "Diamond Pickaxe", ItemType.PICKAXE, Rarity.UNCOMMON, new Stats(230, 0), 4, ""));

        items.put("WOOD_AXE", new MiningMaterial(Material.WOOD_AXE, ItemFamily.WOOD, "Wood Axe", ItemType.AXE, Rarity.COMMON, new Stats(50, 0), 1, ""));
        items.put("STONE_AXE", new MiningMaterial(Material.STONE_AXE, ItemFamily.STONE, "Stone Axe", ItemType.AXE, Rarity.COMMON, new Stats(80, 5), 1, ""));
        items.put("IRON_AXE", new MiningMaterial(Material.IRON_AXE, ItemFamily.IRON, "Iron Axe", ItemType.AXE, Rarity.COMMON, new Stats(120, 0), 1, ""));
        items.put("GOLD_AXE", new MiningMaterial(Material.GOLD_AXE, ItemFamily.GOLD, "Gold Axe", ItemType.AXE, Rarity.COMMON, new Stats(300, 0), 1, ""));
        items.put("DIAMOND_AXE", new MiningMaterial(Material.DIAMOND_AXE, ItemFamily.DIAMOND, "Diamond Axe", ItemType.AXE, Rarity.UNCOMMON, new Stats(230, 0), 1, ""));

        items.put("WOOD_SPADE", new MiningMaterial(Material.WOOD_SPADE, ItemFamily.WOOD, "Wood Spade", ItemType.SPADE, Rarity.COMMON, new Stats(50, 0), 1, ""));
        items.put("STONE_SPADE", new MiningMaterial(Material.STONE_SPADE, ItemFamily.STONE, "Stone Spade", ItemType.SPADE, Rarity.COMMON, new Stats(80, 0), 1, ""));
        items.put("IRON_SPADE", new MiningMaterial(Material.IRON_SPADE, ItemFamily.IRON, "Iron Spade", ItemType.SPADE, Rarity.COMMON, new Stats(120, 0), 1, ""));
        items.put("GOLD_SPADE", new MiningMaterial(Material.GOLD_SPADE, ItemFamily.GOLD, "Gold Spade", ItemType.SPADE, Rarity.COMMON, new Stats(300, 0), 1, ""));
        items.put("DIAMOND_SPADE", new MiningMaterial(Material.DIAMOND_SPADE, ItemFamily.DIAMOND, "Diamond Spade", ItemType.SPADE, Rarity.UNCOMMON, new Stats(230, 0), 1, ""));

        items.put("WOOD_HOE", new MiningMaterial(Material.WOOD_HOE, ItemFamily.WOOD, "Wood Hoe", ItemType.HOE, Rarity.COMMON, new Stats(50, 0), 1, ""));
        items.put("STONE_HOE", new MiningMaterial(Material.STONE_HOE, ItemFamily.STONE, "Stone Hoe", ItemType.HOE, Rarity.COMMON, new Stats(80, 0), 1, ""));
        items.put("IRON_HOE", new MiningMaterial(Material.IRON_HOE, ItemFamily.IRON, "Iron Hoe", ItemType.HOE, Rarity.COMMON, new Stats(120, 0), 1, ""));
        items.put("GOLD_HOE", new MiningMaterial(Material.GOLD_HOE, ItemFamily.GOLD, "Gold Hoe", ItemType.HOE, Rarity.COMMON, new Stats(300, 0), 1, ""));
        items.put("DIAMOND_HOE", new MiningMaterial(Material.DIAMOND_HOE, ItemFamily.DIAMOND, "Diamond Hoe", ItemType.HOE, Rarity.UNCOMMON, new Stats(230, 0), 1, ""));

        items.put("TREE_CAPITATOR", new MiningMaterial(Material.GOLD_AXE, ItemFamily.JUNGLE_AXE, "Tree Capitator", ItemType.AXE, Rarity.EPIC, 1, "A forceful Gold Axe which can break a large amount of logs in a single hit!", new ItemAbility(AbilityAction.NULL, "", "")));
        items.put("WORLD_EATER", new MiningMaterial(Material.DIAMOND_PICKAXE, ItemFamily.JUNGLE_AXE, "World Eater", ItemType.PICKAXE, Rarity.DIVINE, 10, ChatColor.ITALIC + "I became a world ERROR.", new ItemAbility(AbilityAction.NULL, "", "")));

        // Drills
        items.put("COBALT_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.COBALT, "Cobalt Drill", Rarity.COMMON, new Stats(70, 0, 0, 0, 0, 0, 420, 60), 5, ""));
        items.put("CHLOROPHYTE_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.CHLOROPHYTE, "Chlorophyte Drill", Rarity.UNCOMMON, new Stats(90, 0, 0, 0, 0, 0, 600, 110), 6, ""));
        items.put("LUMINATE_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.LUMINATE, "Luminate Drill", Rarity.RARE, new Stats(120, 20, 0, 0, 0, 0, 730, 160), 7, ""));
        items.put("DERNIC_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.DERNIC, "Dernic Drill", Rarity.EPIC, new Stats(155, 35, 0, 0, 0, 0, 910, 210), 8, ""));
        items.put("HEMATITE_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.HEMATITE, "Hematite Drill", Rarity.LEGENDARY, new Stats(190, 35, 0, 0, 0, 0, 1320, 290), 9, ""));
        items.put("VOID_CRYSTAL_DRILL", new DrillMaterial(Material.PRISMARINE_SHARD, ItemFamily.VOID_CRYSTAL, "Void Crystal Drill", Rarity.MYTHIC, new Stats(265, 60, 0, 0, 0, 0, 1610, 410), 10, ""));


        // Reforges
        items.put("PRECURSOR_GEAR", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Precursor Gear", Rarity.EPIC, "62ffd058-94c8-3b63-b027-5e9f4d52b78e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWRmODkwOTQ5OGMyNWY2ZTc1ZWYxOWUzNzZhN2Y4NGY2MWFmMjM0NTI1ZDYzOWJhNDYzZjk5MWY0YzgyZDAifX19", "Ancient"));
        items.put("WITHER_BLOOD", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Wither's Blood", Rarity.EPIC, "d3323a7b-65e3-394a-95da-018d2f52f917", "ewogICJ0aW1lc3RhbXAiIDogMTYwNTU0MzQ1NTU3OCwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JmNWQyMGUwNjAwMTc0ZGNiYWI3NzQ1ZDk0NDgzMmZiMjA2M2MyYmQxNDkwYzY1MDU5MDFiMjhiZmFhY2Q4ZTUiCiAgICB9CiAgfQp9", "Withered"));
        items.put("NECROMANCER_BROOCH", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Necromancer's Brooch", Rarity.RARE, "ea3ee289-11c8-32b4-8913-c98703b1ab1c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViNmJlYWM1NzM2NWNhZjQ2ZjAzN2YzZDJhM2E0NTdmNmNhZmU2NDc1N2JhZjE0ZTg5OTNjZDJkYTE4Y2ZmNyJ9fX0K", "Necrotic"));
        items.put("DRAGON_HORN", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Dragon Horn", Rarity.EPIC, "e63501f5-9f3e-49cd-ac39-c976bc94e18e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc0NGM4MTA5OWMxYWViZjIwZjY3N2ZhZWQyNGNhY2U1MjBhMjk0Y2Y0NmJkZWI2YTI1N2Y0MzZhMzIzYTFkOCJ9fX0=", "Renowned"));
        items.put("DRAGON_CLAW", new ReforgeMaterial(Material.SKULL_ITEM, ItemFamily.REFORGE_STONE, "Dragon Claw", Rarity.RARE, "95cc2473-d382-4f60-aa77-549071e339e4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmI3ZjlmNDg3MjZlNTI1YjBkOWEwODY4MTc4YzMyMzM0NzRlZTRlZDNkYTNmNzYxOTg1NzQ0OWQ0MWEwYzYzYSJ9fX0=", "Fabled"));



        // Skins
        items.put("NECRON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Necron Skin", Rarity.EPIC, "95b93eb6-4ed9-4841-81ee-729f6d1516e4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYzZWEzZmE1NGE4ZDcwMTQ5NjkyMjY2NmNiMjQyNTg5MGNjZGRhYzY1OTkzYjVkNzViMjY0MmJmN2U2YWY4ZCJ9fX0="));
        items.put("STORM_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Psydra Storm Skin", Rarity.EPIC, "4e279894-281e-4ab1-806e-3b3728f899d0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5OTM5ZWE1ZTc2NTVlNjE2YmU4NmEzYTQwMDEzMWMyYzljNzQ1ZTdlMzhlZDBkMWVlMjk4N2JiZTc1YTQ0YSJ9fX0="));
        items.put("SUPERIOR_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Superior Dragon Skin", Rarity.EPIC, "8bac9ff5-6006-3a10-99e4-55799153a1f8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJhNzcwNDU5ZmE2ZDY1ZTIyYWE5NTY3OWQ5M2EyODcwYWFmZWE3MGY5ZjFjNmEwZjc4ZWI2NDFlOTI4OTAifX19Cg"));
        items.put("STRONG_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Strong Dragon Skin", Rarity.EPIC, "9110158d-ad70-4ce8-b58e-c5dcec6828c7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWVjMTBiMGRjZWI1YmIzNGFmMmNiOTUzNzMzZjAxZjIwYmIzYWFmZDVmMjFmZTBlYWRmMWNjNGNlN2Y0ZGFiNCJ9fX0="));
        items.put("YOUNG_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Young Dragon Skin", Rarity.EPIC, "9e70cec6-382c-4b08-8515-03b85308c53c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM1MDNkY2FhZTZhNmNkZTAyNzMxNmEzY2U2ZTIwM2I2NDMyN2I3ZGU3MjQ0MTg2NWNmZDNjZGI0NWQ1ZWY0NCJ9fX0="));
        items.put("UNSTABLE_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Unstable Dragon Skin", Rarity.EPIC, "ec1229d1-f6cc-4734-974a-7eaa2a7262b6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJhMDIzNjM2YTdmMDMwMGE3NGNjNTBkM2U0ZGJiODFhN2JjMTQ4NWYwZGFmNmU5YTdjMGY0ZDhhMmUzYTE4ZiJ9fX0="));
        items.put("WISE_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Wise Dragon Skin", Rarity.EPIC, "2e3221d0-df79-438a-812a-d060e57ee980", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRjNjgzNGM4YmYwYjgxZjkyY2YwZGE0YmQ5OWFkNzY0MTU0ZDUzNjhhMzhlMDNjODVhZGRhMTBlNTE4ODkwYSJ9fX0="));
        items.put("PROTECTOR_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Protector Dragon Skin", Rarity.EPIC, "46130034-6f49-4dd9-a494-5444d2add6af", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTc5YzkxNDQ0OTZkMjFmMzMzNGIzMjQzN2RlZWI2MzM3OTc2NjM5OWQwYTkyYmMwNjcxMmJhNTkyYmMyMzdlNiJ9fX0="));
        items.put("OLD_DRAGON_HELMET_SKIN", new SkinMaterial(Material.SKULL_ITEM, ItemFamily.SKIN, "Baby Old Dragon Skin", Rarity.EPIC, "5d73f057-312a-4ffb-b2b0-bef5fe120482", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmVhZjgzNmI3MTgzNTU0MjY5NjQ3ODI3Mjg5YzY3YTM4NTVlOGQwYjM0NTcxOTMyODE4OGRlZmRiZDA1YTY0YiJ9fX0="));



        // Accessories
        items.put("NETHER_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.NETHER_ARTIFACT, "Nether Artifact", Rarity.EPIC, "0ef495a4-e5df-41c2-b9a2-b2e647cbb491", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0=", new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), "Reduces the damage taken from Zombie Pigmen, Magma Cubes, Ghasts and Blazes by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ". NEW_LINE NEW_LINE While in the " + ChatColor.GOLD + "Blazing Fortress " + ChatColor.GRAY + "you will receive the damage reduction from all mobs."));

        items.put("COAL_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.COAL, "Coal Talisman", Rarity.COMMON, "305b2484-1620-44de-85ef-42816b89dfb3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzExMTA3ZjcwZjhjYTA0NzRmMDIzMjQzYmQzODJiYmQ2YjQxNDlhZWY0ZjQyYjI1ZGRiYmNmZWM4Nzk4YjRkYyJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("COAL_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.COAL, "Coal Ring", Rarity.UNCOMMON, "e993c0e0-e185-4015-a955-36cb402c56bf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWNmOTQ3ODRkN2MxZGM2YTkzYjQxM2JlNTJiNTRmYTc3YjdhNzI1ODY3NWU4NzAyMWIzNGVkMmJmNzUxZGIyMSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("COAL_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.COAL, "Coal Artifact", Rarity.RARE, "c7b9f611-64c6-4e9c-ac97-8dedf8b97e17", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjZjNWVjYWM5NDJjNzdiOTVhYjQ2MjBkZjViODVlMzgwNjRjOTc0ZjljNWM1NzZiODQzNjIyODA2YTQ1NTcifX19", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("IRON_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.IRON, "Iron Talisman", Rarity.COMMON, "f4fd5204-a7eb-433c-8718-e0600772bacf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODM4NWFhZWRkNzg0ZmFlZjhlOGY2Zjc4MmZhNDhkMDdjMmZjMmJiY2Y2ZmVhMWZiYzliOTg2MmQwNWQyMjhjMSJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("IRON_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.IRON, "Iron Ring", Rarity.UNCOMMON, "cc7b245c-78cb-481e-bd07-4e0b7cec1198", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmUxMTNhMzEyODhlNGFjZGU0ZTdkZDc0MDMxYjY0NDdjYzZmZDBiNjE0YTc4YTE0YjNhMzMzNGE0ZTk2ZTU1NSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("IRON_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.IRON, "Iron Artifact", Rarity.RARE, "d909eb70-59b9-48ec-a4d0-a947d91d3c52", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZjYTZmOWY4ZTZiMzE0Nzg0N2JiZGViMTBhNTI3NzM5NmI5YjA1ZDA3NjMyNjczNmY3ZjBiMmJmZDZkMWE2NCJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("GOLD_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.GOLD, "Gold Talisman", Rarity.COMMON, "46bd18e7-4511-45b4-b1ae-783a4d9224a0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODViNGFiZDRmMDdiNjg5NDYwN2NiZDg3MDg2OGY2N2UwMjVjN2ZiNTUyYTFhNTdmNTZmNzdjMDQ0Y2NhNDFjZSJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("GOLD_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.GOLD, "Gold Ring", Rarity.UNCOMMON, "0042c4ee-9501-4b88-815b-efe56bab5031", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBjOWM1ZWIxYmZjZjk5YTBkOGM2ZTgyMmM1MDBjOTk3MTAzNjU3NWY0MDExNzVkNGEwNDAwZWMzMmEzYTFhYyJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("GOLD_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.GOLD, "Gold Artifact", Rarity.RARE, "0019aa3d-b677-415f-a2e5-8edf2036eb0c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmNTdlN2FhOGRlODY1OTFiYjBiYzUyY2JhMzBhNDlkOTMxYmZhYmJkNDdiYmM4MGJkZDY2MjI1MTM5MjE2MSJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));


        items.put("LAPIS_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.LAPIS, "Lapis Talisman", Rarity.COMMON, "b87f063d-01af-4519-bc20-372885110df4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEwMDFiNDI1MTExYmZlMGFjZmY3MTBhOGI0MWVhOTVlM2I5MzZhODVlNWJiNjUxNzE2MGJhYjU4N2U4ODcwZiJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("LAPIS_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.LAPIS, "Lapis Ring", Rarity.UNCOMMON, "7b46c83a-0cb3-4aa1-bd71-8154f3654043", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM1YmY0OWZlYmVjOTU1ZWIzYjMyOGNlMTcwOWY3YmI5ZjQ2OWMyN2E3ZmY0MGQxMzA2OTQyYzA4Zjk2MjUwZSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("LAPIS_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.LAPIS, "Lapis Artifact", Rarity.RARE, "cc92a530-e118-444b-8913-294229ed55bc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTUxODliMzc5YTc4ODBmZjlhNGJiZDU4OGFkYjRlMWI3YjljMzM0MWRlN2Q2ZDAwNmQzNjJhZTU0NTBkYTk4NiJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("REDSTONE_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.REDSTONE, "Redstone Talisman", Rarity.COMMON, "97a050b5-da9b-4d60-bc4c-2bd07661494b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMyY2NmNzgxNDUzOWE2MWY4YmZjMTViY2YxMTFhMzlhZDhhZTE2M2MzNmU0NGI2Mzc5NDE1NTU2NDc1ZDcyYSJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("REDSTONE_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.REDSTONE, "Redstone Ring", Rarity.UNCOMMON, "c3bde4ec-3ac7-48b4-b341-a31bdee7d1db", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMzNjFhZjk2YWMyYzk5Nzc0NTA4Mzg0NDY0NTI3OTk4N2FjYTY1Njc5MDQ2ZTVhZGJhNzdmYzVmNGYyODFkMCJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("REDSTONE_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.REDSTONE, "Redstone Artifact", Rarity.RARE, "544b201d-46ec-44b7-8555-a761471fbc7b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzE2ODI5MWFiYWM0YTVmODZmZThiMzYwMzM4OTg2YWVlN2FiY2I3ZjRiODE2OWViNTVkZmVjOTI4NTYxMjU4In19fQ==", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("EMERALD_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.EMERALD, "Emerald Talisman", Rarity.COMMON, "1ae4ec85-0f5b-41c5-bd47-bbd8dd8a0cd5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE0MGJhZWI5NmZlYTFiZDZlZTA2NDY5NmNkYjc0ZmZkMDhhNmY3YzQwNjE3ZDQ2MmU0ZTJkYThmYWFmNzNlNSJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("EMERALD_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.EMERALD, "Emerald Ring", Rarity.UNCOMMON, "88a36678-a6fb-4c5f-a066-c9323af4e021", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQxNzNhNDY1MTJmYzFhYzIyNzIxNGY1ZTZiOGE5MjQ1ZGE1NDcyYzQ4OWNiOTM2Yjk0NzY0YWFjMTNmOWJmOSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("EMERALD_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.EMERALD, "Emerald Artifact", Rarity.RARE, "5507ab36-636a-4d40-a74a-056156e5c03f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTk2MGQ2ZmZhZjQ0ZThhZmNiZGY4YjI5YTc3ZDg0Y2UyMmM3MWQwMGM2NGJmZDk5YWYzNDBhNjk1MzViZmQ3In19fQ==", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("DIAMOND_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.DIAMOND, "Diamond Talisman", Rarity.COMMON, "9fa6fe15-2b2e-445e-b45d-40b85115a557", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMzYjZjOTA3ZjFjMmExYWU1NGY5MGFhZmJjOWU1NjFmMmY0ZGQ0ZWM0YjczZTU2ZDU0OTU1YmMxZGZjYzJhMCJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("DIAMOND_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.DIAMOND, "Diamond Ring", Rarity.UNCOMMON, "fe765590-5398-4bcb-bc6f-3157e1fa8b82", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UwYWEzYTk1MjVkODY0NmYwNmIxMmE1NGExOTc3MGVhZjMyMDA1N2M5OGViZjYzZTY2M2ZkZTJkOWQ5YjEzMSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("DIAMOND_ARTIFACT", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.DIAMOND, "Diamond Artifact", Rarity.RARE, "70beb9aa-9736-410a-ab1a-a71a4e434076", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAwYjI2YTQyZGYxM2M3Njk5NDJiMDE3MjdlMGE0MjA1YmJkNTZjNjFjNWZiZDI1Y2UzNWYzZDc0NzhjNzNiOCJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("OBSIDIAN_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.OBSIDIAN, "Obsidian Talisman", Rarity.COMMON, "4871fc40-b2c7-431d-9eb8-b54cd666dca7", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MGI4N2Q1MjI3MWQyYTc1NWRlZGM4Mjg3N2UwZWQzZGY2N2RjYzQyZWE0NzllYzE0NjE3NmIwMjc3OWE1In19fQ==", new Stats(0, 4, 4, 0, 0, 0), ""));
        items.put("OBSIDIAN_RING", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.OBSIDIAN, "Obsidian Ring", Rarity.UNCOMMON, "232c9427-6425-4280-ade0-c5c57afd1c46", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE1Yzg2ODY0MTFkNDQ2YzkwYzE5MWM5M2Y4MGI5ZmZiMWNkMjQ3YWExMmEyMjZmODk3OTk4MWFkNDM4OGJlZSJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));

        items.put("ENDERMAN_TALISMAN_COMMON", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.ENDERMAN, "Enderman Talisman", Rarity.COMMON, "0575cc58-309c-4598-9876-e3df5b8f4315", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA0ZWZlMTU1ZWNhMjFhZDhlZmNiYTlkMThmMTBhMjU5MTA2MTI2NDgwYWY0MzlkYjA4OTVhZjQyOWY5Zjc2NCJ9fX0=", new Stats(0, 2, 2, 0, 0, 0), ""));
        items.put("ENDERMAN_TALISMAN_UNCOMMON", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.ENDERMAN, "Enderman Talisman", Rarity.UNCOMMON, "4cc3ce0e-15ac-4c8c-974d-149b4f0014b4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmM0NGRmY2QzNDM4OTg3MGNjZDRkZjEyYzZjODdjMzI1Nzg3OTdhZmQ0NmU2YzFkZTI4ZDY3YjFkZGY3YjllMSJ9fX0=", new Stats(0, 4, 2, 0, 0, 0), ""));
        items.put("ENDERMAN_TALISMAN_RARE", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.ENDERMAN, "Enderman Talisman", Rarity.RARE, "8eb28675-93a9-4f4c-8983-fedd8787a6f3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhiMzE5N2UyZjMzYzEyNWZkOThmY2E4NmQ1ZDJhZDhmOGIyY2JjMjYxZGZjMGU0MDQ0OGU4ZDFlMzE3N2VlMCJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), ""));
        items.put("ENDERMAN_TALISMAN_EPIC", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.ENDERMAN, "Enderman Talisman", Rarity.EPIC, "28308167-5368-4b40-92b0-7c97858acb4f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzgxOTNkOWExOTY1ZjBkNGM5NDI2NzdmMWZjNjAwYjQxYzFkZmNjZGU1YmI2OGVlZGY2ODQ2NTA3MzFjY2RjNyJ9fX0=", new Stats(0, 12, 4, 1, 0, 0), ""));
        items.put("ENDERMAN_TALISMAN_LEGENDARY", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.ENDERMAN, "Enderman Talisman", Rarity.LEGENDARY, "771ee983-f259-46ac-936f-38251b1c7c96", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWIzMzBmYmNlMjA4NWY2MGRmMTZjYmM5YTdhMzg5NDgzNjJiYzlmMGFhNDU0YjI5MDU2MWZlNDcxZGFlMDU3OCJ9fX0=", new Stats(0, 16, 8, 2, 0, 0), ""));

        items.put("KING_TALISMAN", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.KING, "King of the Kingdom", Rarity.RARE, "53ad9b08-b403-4241-ab0f-4323326bfb9d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZkYTQ4OGNjZGViOTdjMzYxYTA4Mzc2MGUxZGUxMDIzMGQwMjQ4NDg1OTdjNWY3YzA3OTUyODYzZjk1YTA5NyJ9fX0=", new Stats(0, 8, 4, 1, 0, 0), "You pissed off the King"));

        items.put("KING_TALISMAN_GOD", new AccessoryMaterial(Material.SKULL_ITEM, ItemFamily.KING, "King of the Kingdom", Rarity.RARE, "ff3fc337-69e0-418e-8f44-aa1ef8fe42ac", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODgzZmJjODZhOGI2Y2E5MTJiM2E5ZDFkMjQwODc2ODA1Mjk5NTY0MWJhODllMjZhMWZmNjAwNjI3ZDgzYjIzOSJ9fX0=", new Stats(0, 10, 3, 2, 0, 0), "You pissed off GOD!"));


        // Wands
        items.put("GYROKINETIC_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.GYROKINETIC_WAND, "Gyrokinetic Wand", ItemType.WAND, Rarity.EPIC, "", new ItemAbility(AbilityAction.LEFT_CLICK, "Gravity Storm", "Create a large " + ChatColor.DARK_PURPLE + "rift " + ChatColor.GRAY + "at aimed location, pulling all mobs toegether.", 1200, false, 5), new ItemAbility(AbilityAction.RIGHT_CLICK, "Cells Alignment", "Apply " + ChatColor.GREEN + "Aligned " + ChatColor.GRAY + "to 4 nearby players and yourself for " + ChatColor.GREEN + "6 seconds" + ChatColor.GRAY + ".", 220, false, 10)));
        items.put("TORNADO_WAND", new ToolMaterial(Material.STICK, ItemFamily.TORNADO_WAND, "Tornado Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a tornado on you with random colored blocks.", 10, false, 1)));
        items.put("TWISTER_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Twister Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes on you with random colored blocks.", 10, false, 1)));
        items.put("HURRICANE_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Hurricane Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Tornado", "Cast a multi tornadoes around you with random colored blocks.", 10, false, 1)));
        items.put("PARABOLA_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.TORNADO_WAND, "Parabola Wand", ItemType.WAND, Rarity.MYTHIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Parabola", "Cast a parabola on you with random colored blocks.", 10, false, 1)));
        items.put("BUILDERS_WAND", new ToolMaterial(Material.BLAZE_ROD, ItemFamily.BUILDERS_WAND, "Builder's Wand", ItemType.WAND, Rarity.LEGENDARY, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Grand Architect", "Right-click the face of a block to extend all connected block faces."), new ItemAbility(AbilityAction.LEFT_CLICK, "Built-in Storage", "Opens the wand storage. Blocks will be placed from your inventory or the wand storage.")));
        items.put("ICE_SPRAY_WAND", new ToolMaterial(Material.STICK, ItemFamily.ICE_SPRAY_WAND, "Ice Spray Wand", ItemType.WAND, Rarity.EPIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Ice Spray", "Produces a cone of ice in front of the caster that deals " + ChatColor.RED + "17,000 " + ChatColor.GRAY + "damage to mobs and freezes them in place for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds! Frozen mobs take " + ChatColor.RED + "10% " + ChatColor.GRAY + "increased damage!", 50, false, 5)));



        // Scrolls
        items.put("IMPLOSION", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Implosion", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility()));
        items.put("WITHER_SHIELD", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Shield", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility()));
        items.put("SHADOW_WARP", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Shadow Warp", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()));
        items.put("WITHER_IMPACT", new NecronBladeMaterial.NecronBladeScroll(Material.BOOK_AND_QUILL, "Wither Impact", Rarity.EPIC, "", NecronBladeMaterial.NecronBladeAbility.IMPLOSION.getAbility(), NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD.getAbility(), NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP.getAbility()));



        // Power Orbs
        items.put("RADIANT_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Radiant Power Orb", ItemType.POWER_ORB, Rarity.UNCOMMON, "10a23a36-4f37-47f7-a28f-fc330de3ff5a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FiNGM0ZDZlZTY5YmMyNGJiYTJiOGZhZjY3YjlmNzA0YTA2YjAxYWE5M2YzZWZhNmFlZjdhOTY5NmM0ZmVlZiJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, true, 30), "Heals " + ChatColor.RED + "1% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second."));
        items.put("MANA_FLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Mana Flux Power Orb", ItemType.POWER_ORB, Rarity.RARE, "2131e1e3-cd0f-4212-b625-8ccb402e895e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODJhZGExYzdmY2M4Y2YzNWRlZmViOTQ0YTRmOGZmYTlhOWQyNjA1NjBmYzdmNWY1ODI2ZGU4MDg1NDM1OTY3YyJ9fX0", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, true, 30), "Grants " + ChatColor.AQUA + "+50% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+10 Strength" + ChatColor.GRAY + "."));
        items.put("OVERFLUX_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Overflux Power Orb", ItemType.POWER_ORB, Rarity.EPIC, "05624a23-a2f1-46b9-9e26-e463855f05c1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ4NTlkMGFkZmM5M2JlMTliYjQ0MWU2ZWRmZDQzZjZiZmU2OTEyNzIzMDMzZjk2M2QwMDlhMTFjNDgyNDUxMCJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, true, 30), "Grants " + ChatColor.AQUA + "+100% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "2.5% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+5%"  + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+25 Strength" + ChatColor.GRAY + "."));
        items.put("PLASMA_POWER_ORB", new PowerOrbMaterial(Material.SKULL_ITEM, ItemFamily.RADIANT_POWER_ORB, "Plasma Power Orb", ItemType.POWER_ORB, Rarity.LEGENDARY, "6de57aa8-ffd6-414d-ad9b-85563a6dc417", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNlZDRjZTIzOTMzZTY2ZTA0ZGYxNjA3MDY0NGY3NTk5ZWViNTUzMDdmN2VhZmU4ZDkyZjQwZmIzNTIwODYzYyJ9fX0=", new ItemAbility(AbilityAction.NULL, "Deploy", "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "20 " + ChatColor.GRAY + "blocks. Only one orb applies per player.", 500000, true, 30), "Grants " + ChatColor.AQUA + "+125% " + ChatColor.GRAY + "base mana regen." + " NEW_LINE " + "Heals " + ChatColor.RED + "3% " + ChatColor.GRAY + "of max " + StatType.HEALTH.getIcon() + " " + ChatColor.GRAY + "per second." + " NEW_LINE " + "Increases all heals by " + ChatColor.GREEN + "+7.5%" + ChatColor.GRAY + "." + " NEW_LINE " + ChatColor.GRAY + "Grants " + ChatColor.RED + "+35 Strength" + ChatColor.GRAY + "."));



        // Items
        items.put("MOODY_GRAPPLESHOT", new ToolMaterial(Material.TRIPWIRE_HOOK, ItemFamily.ITEM, "Moody Grappleshot", ItemType.ITEM, Rarity.EPIC, "", new ItemAbility(AbilityAction.RIGHT_CLICK, "Big Pull", "Throw a hook to grab and pull monsters." + " NEW_LINE " + "Pulling a monster multiplies its damage taken by " + ChatColor.RED + "2x " + ChatColor.GRAY + "and stops it from flying.")));
        items.put("TROLL_EYE", new ToolMaterial(Material.EYE_OF_ENDER, ItemFamily.TROLL, "Troll Eye", ItemType.ITEM, Rarity.EPIC, ChatColor.ITALIC + "Eye of the ender lost ERROR", new ItemAbility(AbilityAction.RIGHT_CLICK, "Get Out From My Face", "Make your target entity shoot ender pearls to every direction", 100, false, 15)));
        items.put("GRAPPLING_HOOK", new ToolMaterial(Material.FISHING_ROD, ItemFamily.GRAPPLING_HOOK, "Grappling Hook", ItemType.ITEM , Rarity.UNCOMMON, ChatColor.GRAY + "Travel around in style using this " + ChatColor.GREEN + "Grappling Hook" + ChatColor.GRAY + "."));

        String mythologsSpadeDescription = "Hold in your hand to reveal and dig out " + ChatColor.YELLOW + "Griffin Burrows " + ChatColor.GRAY + "in the griffin's island, which hold both " + ChatColor.GOLD + "treasure " + ChatColor.GRAY + "and " + ChatColor.RED + "dangers" + ChatColor.GRAY + "." + " NEW_LINE " + "Each level of the " + ChatColor.DARK_GREEN + "Mytholog's Spade " + ChatColor.GRAY + "you can find better " + ChatColor.GOLD + "treasures " + ChatColor.GRAY + "and drops.";
        ItemAbility mythologicalSpadeAbility = new ItemAbility(AbilityAction.RIGHT_CLICK, "Echo", "Show the way to the next or nearby Griffin Burrow.", 50, false, 2);
        items.put("MYTHOLOGS_SPADE_RARE", new ToolMaterial(Material.STONE_SPADE, ItemFamily.MYTHOLOGS_SPADE, "Mytholog's Spade", ItemType.ITEM, Rarity.RARE, mythologsSpadeDescription, mythologicalSpadeAbility));
        items.put("MYTHOLOGS_SPADE_EPIC", new ToolMaterial(Material.IRON_SPADE, ItemFamily.MYTHOLOGS_SPADE, "Mytholog's Spade", ItemType.ITEM, Rarity.EPIC, mythologsSpadeDescription, mythologicalSpadeAbility));
        items.put("MYTHOLOGS_SPADE_LEGENDARY", new ToolMaterial(Material.GOLD_SPADE, ItemFamily.MYTHOLOGS_SPADE, "Mytholog's Spade", ItemType.ITEM, Rarity.LEGENDARY, mythologsSpadeDescription, mythologicalSpadeAbility));
        items.put("MYTHOLOGS_SPADE_MYTHIC", new ToolMaterial(Material.DIAMOND_SPADE, ItemFamily.MYTHOLOGS_SPADE, "Mytholog's Spade", ItemType.ITEM, Rarity.MYTHIC, mythologsSpadeDescription, mythologicalSpadeAbility));

        // wither island
        items.put("WITHER_SHARD", new NormalMaterial(Material.FLINT, ItemFamily.ITEM, "Wither Shard", ItemType.ITEM, Rarity.EPIC, "", "", "", false, true, true));
        items.put("WITHER_SKULL", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Wither Skull", ItemType.ITEM, Rarity.EPIC, "d6e9ad20-9dc1-4e9e-b97f-fa663ece60a0", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRhOTdjMDZjMDdhMDM1YmQ4NzQ2YjQ4ZDMyZGMwNGU4NDE2MGJkMTYxZjI2YmUxMjcyYjYyNzEyNTFhYWE3In19fQ==", "Used to spawn the wither boss", false, false, false));

        items.put("WITHER_STONE_STRENGTH", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Ares crystal", ItemType.ITEM, Rarity.LEGENDARY, "b83ce84d-5a82-4111-a1cf-7e797aeb4849", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY3MzZmZDk1ZDNhNmE0YWFhYzQ2NzA5YTA3YWVjN2YxYzM4ZjBhM2FhZTU3M2U2ZjQ4MzM4ODgxOTQxMmI2NSJ9fX0=", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.STRENGTH.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        items.put("WITHER_STONE_HEALTH", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Phanes crystal", ItemType.ITEM, Rarity.LEGENDARY, "eb2e049e-5563-4df7-be8a-1c89123a79a1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU0ODYxNWRmNmI3ZGRmM2FkNDk1MDQxODc2ZDkxNjliZGM5ODNhM2ZhNjlhMmFjYTEwN2U4ZjI1MWY3Njg3In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.HEALTH.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        items.put("WITHER_STONE_SPEED", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Hermes crystal", ItemType.ITEM, Rarity.LEGENDARY, "ca749fda-0403-496c-9ebe-7c770c653135", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTA2ZTZkODNjZjdlZDVjZjdiZjBlMDE4ZWNiNjAzOWIwNDZkOGRjNmRiNTU2OTAxNGZjYWIzN2I2MTdmMTM5OSJ9fX0=", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.SPEED.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        items.put("WITHER_STONE_CRIT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Demeter crystal", ItemType.ITEM, Rarity.LEGENDARY, "9f04e709-14a0-4f82-ba72-2bc48f1f585c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVlZjY5NDY0NjI0ZjhlNmY4ZWI5NjI3MWUwMjY4MmZmOTY3NTUzYzFlMTg5NzMzZmMyNTQ2YjE4ZDk3In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.CRIT_DAMAGE.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        items.put("WITHER_STONE_INTELLIGENCE", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Athena crystal", ItemType.ITEM, Rarity.LEGENDARY, "14661ef6-e01b-4c8d-87ce-a9ba7ca61b1d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE1ZjI5YTc2ZDFmOTFjMTY1ZjYzYmFhYzA0ODY3MGU3YjFkMzdjZTc4NWE0ZDljMjFkOGMzYTE3N2I1In19fQ==", "Use in a " + ChatColor.GREEN + "Wither Chamber "+ ChatColor.GRAY + "to increase the " + StatType.INTELLIGENCE.getIconAndText() + ChatColor.GRAY +  " of a piece of armor, weapon or tool.", false, true, true));

        // Normal Items
        items.put("RECOMBABULATOR", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.ITEM, "Recombobulator 3000", ItemType.ITEM, Rarity.LEGENDARY, "96538e7f-6b56-3557-9b7d-458afe4239e9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjY2QzNmRjOGY3MmFkY2IxZjhjOGU2MWVlODJjZDk2ZWFkMTQwY2YyYTE2YTEzNjZiZTliNWE4ZTNjYzNmYyJ9fX0K", "Use in a " + ChatColor.GREEN + "Reforge Anvil "+ ChatColor.GRAY + "or at the Dungeon Blacksmith to permanently increase the rarity of a piece of armor, weapon or tool. An item's rarity can only be upgraded once!", false, false, false));

        items.put("HOT_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Hot Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Combine this Book in an Anvil with a weapon or armor piece to gain a small but permanent stat boost!", true, false, false));
        items.put("FUMING_POTATO_BOOK", new NormalMaterial(Material.BOOK, ItemFamily.HOT_POTATO, "Fuming Potato Book", ItemType.ITEM, Rarity.EPIC, "", "", "Use in an anvil to combine this book with a weapon or armor pieace to gain a small but permanent stat boost!" + " NEW_LINE NEW_LINE " + "This book bypasses the Hot Potato Book limit of 10, allowing you to upgrade an item up to " + ChatColor.GREEN + "15 " + ChatColor.GRAY + "times!", true, false, false));

        items.put("SKYBLOCK_MENU", new NormalMaterial(Material.NETHER_STAR, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, false));

        items.put("SKYBLOCK_MENU_ARROW", new NormalMaterial(Material.ARROW, ItemFamily.NULL, "Skyblock Menu", ItemType.ITEM, Rarity.UNCOMMON, "", "", ChatColor.GRAY + "View all of your Skyblock progress, including your Skills, Recipes and more!", true, false, true));

        items.put("SMALL_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Small Backpack", ItemType.ITEM, Rarity.UNCOMMON, "0523fc65-c396-309b-b153-de7d8e5f666d", "eyJ0aW1lc3RhbXAiOjE1NjgyMTI5NjI1MzMsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMWQ4MzdjYTIyMmNiYzBiYzEyNDI2ZjVkYTAxOGMzYTkzMWI0MDYwMDg4MDA5NjBhOWRmMTEyYTU5NmU3ZDYyIn19fQ==", ChatColor.GRAY + "A bag with 9 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("MEDIUM_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Medium Backpack", ItemType.ITEM, Rarity.RARE, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 18 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("LARGE_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Large Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 27 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("GREATER_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Greater Backpack", ItemType.ITEM, Rarity.EPIC, "f1e3c520-3f66-38f0-a110-70e3c183df38", "eyJ0aW1lc3RhbXAiOjE1NjgyMTMwNTE0MjYsInByb2ZpbGVJZCI6IjgyYzYwNmM1YzY1MjRiNzk4YjkxYTEyZDNhNjE2OTc3IiwicHJvZmlsZU5hbWUiOiJOb3ROb3RvcmlvdXNOZW1vIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmYzYjNhMDU0ODFjZGU3NzI0MDAwNWMwZGRjZWUxYzA2OWU1NTA0YTYyY2UwOTc3ODc5ZjU1YTM5Mzk2MTQ2In19fQ", ChatColor.GRAY + "A bag with 36 slots which can be placed in your Storage Menu to store additional items.", false, false, false));
        items.put("JUMBO_BACKPACK", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.BACKPACK, "Jumbo Backpack", ItemType.ITEM, Rarity.LEGENDARY, "49a240cd-8e28-3f5e-8bd3-b690ff1cabfc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY4NDA1MTE2YzFkYWE3Y2UyZjAxMjU5MTQ1OGQ1MDI0NmQwYTQ2N2JjYjk1YTVhMmMwMzNhZWZkNjAwOGI2MyJ9fX0K", ChatColor.GRAY + "A bag with 45 slots which can be placed in your Storage Menu to store additional items.", false, false, false));

        items.put("EYE_OF_ENDERMAN", new NormalMaterial(Material.ENDER_PEARL, ItemFamily.ENDERMAN, "Eye of Enderman", ItemType.ITEM, Rarity.RARE, "", "", "", true, true, true));
        items.put("EYE_OF_ENDER_GUARD", new NormalMaterial(Material.EYE_OF_ENDER, ItemFamily.ENDERMAN, "Eye of Ender Guard", ItemType.ITEM, Rarity.EPIC, "", "", "", true, true, true));
        items.put("SUMMONING_EYE", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.NULL, "Summoning Eye", ItemType.ITEM, Rarity.EPIC, "36122cdc-6c97-4b97-990a-ef4df57db922", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ", "Use this in " + ChatColor.DARK_PURPLE + "The End " + ChatColor.GRAY + "to summon Ender Dragons!", false, false, false));
        items.put("ADMIN_SUMMONING_EYE", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.NULL, "ADMIN Summoning Eye", ItemType.ITEM, Rarity.MYTHIC, "36122cdc-6c97-4b97-990a-ef4df57db922", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ", "Use this in " + ChatColor.DARK_PURPLE + "The End " + ChatColor.GRAY + "to summon Ender Dragons! ADMIN ITEM!!!!", false, false, false));
        items.put("FROZEN_KEY", new NormalMaterial(Material.TRIPWIRE_HOOK, ItemFamily.NULL, "Frozen Key", ItemType.ITEM, Rarity.LEGENDARY, "", "", "Use that" + ChatColor.GRAY + "to summon" + ChatColor.AQUA + "Frozen Dragon!", true, false, false));

        items.put("SUPERIOR_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON, "Superior Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "7c3c5222-2aca-47c0-a1fc-493fec60f166", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmY4OWIxNTBiZTljNGM1MjQ5ZjM1NWY2OGVhMGM0MzkxMzAwYTliZTFmMjYwZDc1MGZjMzVhMTgxN2FkNzk2ZSJ9fX0=", false, true, true));
        items.put("STRONG_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.STRONG_DRAGON, "Strong Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "bab4c3e4-6e41-4d46-a3f7-17fb8b7f34a4", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVlMzJmYmQ0YzdiMDNiODY5MDc4YWExZjQ5M2EzOTBlNmUxM2I0NjFkNjEzNzA3ZWFmYjMyNmRiY2QyYjRiNSJ9fX0=", false, true, true));
        items.put("YOUNG_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.YOUNG_DRAGON, "Young Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "e9a59e69-331d-4022-83ce-3bf7b3deb85a", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI1YmQ2YjY0ZThiZDZjNThmNWNkMWU3OWE1NTAyZDQ0NDhiYWZjMDA2ZDJmZTA1NjhmNmEwZDZiODZkNDQ5ZSJ9fX0=", false, true, true));
        items.put("UNSTABLE_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.UNSTABLE_DRAGON, "Unstable Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "990ea925-bb21-4c72-a5b6-229667ae2ac1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTgyMjhjMjM0YzM5MDNjNTEyYTVhMGFhNDUyNjBlN2I1NjdlMGUyMGVlZmM3ZDU2MWNjZWM5N2IyOTU4NzFhZiJ9fX0", false, true, true));
        items.put("WISE_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.WISE_DRAGON, "Wise Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "66c875ab-a643-43a2-920f-c7a6d217eb26", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ3NjIwYjJlNDkzNDk2M2JiMTI1MDgzMTBkMDU0OTRjMDY3ZGMzM2UwMDhjZWNmMmNkN2I0NTQ5NjU0ZmFiMyJ9fX0=", false, true, true));
        items.put("PROTECTOR_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.SUPERIOR_DRAGON, "Protector Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "1d128fd0-bc28-4fe4-8cdd-17e9db01fe35", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhkZTMzOWFmNjNhMjI5YzkyMzhkMDI3ZTQ3ZjUzZWViNTYxNDFhNDE5ZjUxYjM1YzMxZWExNDk0YjQzNWRkMyJ9fX0=", false, true, true));
        items.put("OLD_DRAGON_FRAGMENT", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.OLD_DRAGON, "Old Dragon Fragment", ItemType.ITEM, Rarity.EPIC, "9479343f-f19a-419a-a12e-a16880fd21ec", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FhMDlhZDE3N2ZiY2NjNTNmYTMxNmNjMDRiZGQyYzkzNjZiYWVkODg5ZGY3NmM1YTI5ZGVmZWE4MTcwZGVmNSJ9fX0=", false, true, true));

        items.put("COBALT", new NormalMaterial(Material.PRISMARINE_SHARD, ItemFamily.COBALT, "Cobalt", ItemType.ITEM, Rarity.COMMON, "", "", false, true, true));
        items.put("CHLOROPHYTE", new NormalMaterial(Material.EMERALD, ItemFamily.CHLOROPHYTE, "Chlorophyte", ItemType.ITEM, Rarity.UNCOMMON, "", "", false, true, true));
        items.put("LUMINATE", new NormalMaterial(Material.QUARTZ, ItemFamily.LUMINATE, "Luminate", ItemType.ITEM, Rarity.RARE, "", "", false, true, true));
        items.put("DERNIC", new NormalMaterial(Material.INK_SACK, ItemFamily.DERNIC, "Dernic", ItemType.ITEM, Rarity.EPIC, "3", "", false, true, true));
        items.put("HEMATITE", new NormalMaterial(Material.NETHER_BRICK_ITEM, ItemFamily.HEMATITE, "Hematite", ItemType.ITEM, Rarity.LEGENDARY, "", "", false, true, true));
        items.put("VOID_CRYSTAL", new NormalMaterial(Material.NETHER_STAR, ItemFamily.VOID_CRYSTAL, "Void Crystal", ItemType.ITEM, Rarity.MYTHIC, "", "", false, true, true));

        items.put("COBALT_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.COBALT, "Cobalt Shard", ItemType.ITEM, Rarity.COMMON, "a0a3a357-99ea-4faa-b2ed-166a001c59d6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJmNGQwMzI5NWI1YTlhZDk5MDYxNTM4ZGIwMzIyMDMxNWJkODhlZjkxNzhiODI2YzhjNjI4ZWVkOTg0ZWRmZCJ9fX0=", true, true));
        items.put("CHLOROPHYTE_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.CHLOROPHYTE, "Chlorophyte Shard", ItemType.ITEM, Rarity.UNCOMMON, "6e26d3e8-8776-4ecb-8319-b4aa448c8ce5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTEwM2ExOTUzYjA1NWM2MDE2N2E3YWUyODQwMmVmNTY2YmJjYTI2ZDAxNmNiMGE0N2UzNDY3MjUyYTNlYTA3NiJ9fX0=", true, true));
        items.put("LUMINATE_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.LUMINATE, "Luminate Shard", ItemType.ITEM, Rarity.RARE, "d97bce18-26b9-4b6d-a4b6-e468f19f62cd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmMxMmZmN2RjYWQxYTIzNWEyODliYmI3NTU0MWRjOTlmZTNlNWY0MzkyZmExNmVlNmMwYTgwZmQxOTIyNmExIn19fQ==", true, true));
        items.put("DERNIC_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.DERNIC, "Dernic Shard", ItemType.ITEM, Rarity.EPIC, "30bc6893-b5fa-40cc-9138-0265732450cd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJhMDEwNjNlZDJhMmJlZjEzN2FmNGEyYWRmZTRhYTE1ZmFjYzY3YTlmNTFlYzdjNjRlYzEwMzZiNjRjMWEzMSJ9fX0=", true, true));
        items.put("HEMATITE_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.HEMATITE, "Hematite Shard", ItemType.ITEM, Rarity.LEGENDARY, "204063be-02cc-494f-b754-aaa6df8d2d6f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E2ZGNmMjc1Y2Y1OGM2NGNhN2I0ZDFmYzRlYTAwOWEyYjU2OTk1ZjUxYjU0OTg3NGJhNzg5ODZjZGVhYjdkMyJ9fX0=", true, true));
        items.put("VOID_CRYSTAL_SHARD", new NormalMaterial(Material.SKULL_ITEM, ItemFamily.VOID_CRYSTAL, "Void Crystal Shard", ItemType.ITEM, Rarity.MYTHIC, "3e7d42f3-7abc-45e3-8c68-4670da688924", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0=", true, true));


        // Other Items
        items.put("NULL_OVOID", new NormalMaterial(Material.MONSTER_EGG, ItemFamily.NULL, "Null Ovoid", ItemType.ITEM, Rarity.RARE, "58", "", true, true));

        items.put("ENCHANTED_BOOK", new BookMaterial(Material.ENCHANTED_BOOK, ItemFamily.BOOK, "Enchanted Book", ItemType.BOOK, Rarity.COMMON, "", ""));

        // Pets
        pets.put("ENDER_DRAGON", new PetMaterial("Ender Dragon",
                "083a89e8-c8b9-4c15-bccb-7b4af8d31b20",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFkMDhjMDI4OWQ5ZWZlNTE5ZTg3ZjdiODE0Y2IyMzQ5ZjQ0NzViZDNjMzdkNDRmOWM0ZjBlNTA4ZTc3OTgxZSJ9fX0=",
                new Stats(0, 0.5, 0.5, 0.1, 0, 0),
                new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY)),
                new ArrayList<>(Arrays.asList(new PetAbility("End Strike", "Deal " + ChatColor.GREEN + "LEVEL*0.25% " + ChatColor.GRAY + "more damage to end mobs", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("One with the Dragons", "Buffs the Aspect of the Dragons sword by " + ChatColor.GREEN + "LEVEL*0.5 " + StatType.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + "LEVEL*0.3 " + StatType.STRENGTH.getIconAndText(), new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("Superior", "Increases most stats by " + ChatColor.GREEN + "LEVEL*0.1%", new ArrayList<>(Arrays.asList(Rarity.LEGENDARY))))),
                SkillType.COMBAT,
                EnumWrappers.Particle.SMOKE_NORMAL, EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERDRAGON_FLAP
        ));

        pets.put("ENDERMAN", new PetMaterial("Enderman",
                "6e4d2ed0-05e8-4959-b06c-e649d9113349",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjMGIzNmQ1M2ZmZjY5YTQ5YzdkNmYzOTMyZjJiMGZlOTQ4ZTAzMjIyNmQ1ZTgwNDVlYzU4NDA4YTM2ZTk1MSJ9fX0=",
                new Stats(0, 0, 0.5, 0.1, 0, 0),
                new ArrayList<>(Arrays.asList(Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE)),
                new ArrayList<>(Arrays.asList(new PetAbility("Enderian", "Take " + ChatColor.GREEN + "LEVEL*0.3% " + ChatColor.GRAY + "less damage from end monsters.", new ArrayList<>(Arrays.asList(Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE))), new PetAbility("Enderator", "Increases the chance of enderman drops by " + ChatColor.GREEN + "LEVEL*0.5%" + ChatColor.GRAY + ".", new ArrayList<>(Arrays.asList(Rarity.RARE))))),
                SkillType.COMBAT,
                EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERMEN_AMBIENT
        ));
        pets.put("BEAR", new PetMaterial("Bear",
                "877042bf-3a95-4a20-8c42-aaa234bfea69",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIxMjUwM2Q2MWM0OWY3MDFmZWU4NjdkNzkzZjFkY2M1MjJlNGQ3YzVjNDFhNjhmMjk1MTU3OWYyNGU3Y2IyYSJ9fX0=",
                new Stats(0, 2, 1, 0.5, 0, 0.5),
                new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY)),
                new ArrayList<>(Arrays.asList(new PetAbility("Furious Bear", "Increases " + ChatColor.RED + "⫽ Ferocity " + ChatColor.GRAY + "by" + ChatColor.GREEN + "0.5%", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("Ferocity is Stronger", "Ferocity Attacks deal " + ChatColor.GREEN + "LEVEL*0.5%" + ChatColor.GRAY + "more damage", new ArrayList<>(Arrays.asList(Rarity.LEGENDARY))))),
                SkillType.COMBAT,
                EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERMEN_AMBIENT
                ));
        
        items.putAll(pets);

        itemMaterials = new HashMap<>(items);

        for (Material material : Material.values()) {
            if (material == Material.AIR) continue;
            if (!material.isItem()) continue;
            ItemStack itemStack = new ItemStack(material, 1, (short) 0);
            String localName = Functions.getLocalName(itemStack);
            String name = material.name();
            if (name.equals("SULPHUR"))
                name = "GUNPOWDER";
            if (items.containsKey(name) || name.contains("_NUGGET"))
                continue;
            Rarity rarity = Rarity.COMMON;
            if (Functions.isColorable(material)) {
                if (material == Material.INK_SACK)
                    for (short i = 0; i < 16; i++) {
                        vanillaMaterials.put(Functions.getColorName(15 - i).toUpperCase() + "_DYE", new NormalMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(15 - i) + " DYE"), ItemType.ITEM, rarity, i + "", "", false, false));
                    }
                else
                    for (short i = 0; i < 16; i++) {
                        vanillaMaterials.put(Functions.getColorName(i).toUpperCase() + "_" + name, new NormalMaterial(material, ItemFamily.VANILLA, Functions.setTitleCase(Functions.getColorName(i) + " " +  name), ItemType.ITEM, rarity, i + "", "", false, false));
                    }
            } else {
                short maxDurability = 16;
                for (int i = 1; i < maxDurability; i++) {
                    ItemStack newItemStack = new ItemStack(material, 1, (short) i);
                    String newLocalName = Functions.getLocalName(newItemStack);
                    if (localName.equals(newLocalName)) {
                        break;
                    }
                    vanillaMaterials.put(name + ":" + i, new NormalMaterial(material, ItemFamily.VANILLA, newLocalName, ItemType.ITEM, rarity, i + "", "", false, false));
                }
            }

            if (material == Material.NETHER_STAR || material == Material.BEDROCK)
                rarity = Rarity.LEGENDARY;
            vanillaMaterials.put(name, new NormalMaterial(material, ItemFamily.VANILLA, localName, ItemType.ITEM, rarity, "", "", false, false));
        }
        vanillaMaterials.put("LAPIS", new NormalMaterial(Material.INK_SACK, ItemFamily.VANILLA, "Lapis Lazuli", ItemType.ITEM, Rarity.COMMON, "4", "", false, false));
        vanillaMaterials.remove("BLUE_DYE");

        items.putAll(vanillaMaterials);

        ArrayList<String> keys = new ArrayList<>();
        try {
            for (String material : Items.items.keySet()) {
                ItemMaterial item = Items.items.get(material);
                if (item.getType() == ItemType.ITEM && !material.contains("ENCHANTED_") && !material.contains("BOOK") && !material.contains("BARDING") && !(item instanceof ItemAbilityAble)) {
                    keys.add(material);
                }
            }
            for (String material : keys) {
                ItemMaterial item = Items.get(material);
                int rarityAdder = 0;
                if (item.getMaterial().isBlock()) rarityAdder++;
                NormalMaterial normalMaterial = new NormalMaterial(item.getMaterial(), ItemFamily.ENCHANTED_ITEM, "Enchanted " + item.getName(), ItemType.ITEM, Rarity.values()[item.getRarity().getLevel() + 1 + rarityAdder], item.getId(), item.getNbt(), true, true, true);
                Items.items.put("ENCHANTED_" + material, normalMaterial);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        NULL = new ToolMaterial(Material.BARRIER, ItemFamily.NULL,"Null", ItemType.NULL, Rarity.SPECIAL, "", "", ChatColor.GRAY + "" + ChatColor.ITALIC + "Null barrier that created by " + ChatColor.GRAY + "" + ChatColor.ITALIC + "ERROR with an item.");
    }

    public static ItemMaterial get(String name) {
        String[] splitName = name.split(":");
        name = splitName[0];
        if (Functions.isInt(name)){
            name = Material.getMaterial(Functions.getIntOrDefault(name, 1)).name();
        }
        name = name.toUpperCase(Locale.ROOT);
        name = name.replace(":0", "");
        if (splitName.length > 1) {
            name = name + ":" + splitName[1];
        }
        return Items.items.getOrDefault(name, Items.NULL);
    }

    public static ItemMaterial get(ItemStack item) {
        return Items.items.getOrDefault(Functions.getId(item), Items.NULL);
    }
}
