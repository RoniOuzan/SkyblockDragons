package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@Getter
public class EntityMaterial implements ConfigurationSerializable {
    public static HashMap<String, EntityMaterial> Entities = new HashMap<>();

    public static EntityMaterial NULL = null;

    public EntityType entityType;
    public String name;
    public int level;
    public double health;
    public double defense;
    public double damage;
    public double trueDamage;
    public Equipment equipment;
    public double speed;
    public double knockbackResistance;
    public boolean ai;
    public double combatXp;

    public ItemDrop[] drops;

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, boolean ai, double combatXp, ItemDrop... drops) {
        this.entityType = entityType;
        this.name = name;
        this.level = level;
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.trueDamage = trueDamage;
        this.equipment = equipment;
        this.speed = speed / 500;
        this.knockbackResistance = knockbackResistance;
        this.ai = ai;
        this.combatXp = combatXp;
        this.drops = drops;
    }

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, ItemDrop... drops) {
        this(entityType, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, combatXp, drops);
    }

    public static void registerItems() {
//        Entities.put("ZOMBIE", new EntityMaterial(EntityType.ZOMBIE,
//                "Zombie",
//                1,
//                50,
//                0,
//                new Equipment(null, null, null, null, new ItemStack(Material.WOOD_SWORD), null),
//                0.2,
//                0,
//                new ItemDrop(ItemMaterial.get("ROTTEN_FLESH"), 5, 8, 100)));
        Entities.put("GOLDEN_SKELETON", new EntityMaterial(
                EntityType.SKELETON,
                ChatColor.GOLD + "Golden Skeleton",
                1,
                50,
                0,
                40,
                0,
                new Equipment(Material.GOLD_HELMET, null, null, null, Material.BOW, null),
                100,
                0,
                2,
                new ItemDrop(ItemMaterial.get("GOLDEN_SKELETON_BOW"), 1, 1d),
                new ItemDrop(ItemMaterial.get("GOLDEN_SKELETON_HELMET"), 1, 1d),
                new ItemDrop(ItemMaterial.get("BONE"), 1, 4),
                new ItemDrop(ItemMaterial.get("ARROW"), 2, 10)
        ));

        Entities.put("LAPIS_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.BLUE + "Lapis Zombie",
                2,
                80,
                0,
                100,
                0,
                new Equipment(Material.LAPIS_BLOCK, null, null, null, Material.STONE_SWORD, null),
                100,
                0,
                5,
                new ItemDrop(ItemMaterial.get("LAPIS"), 1, 6),
                new RareDrop(ItemMaterial.get("ENCHANTED_LAPIS"), 1, 2, 2d, RareDrop.Rarity.RARE)
        ));

        Entities.put("REDSTONE_PIGMAN", new EntityMaterial(
                EntityType.PIG_ZOMBIE,
                ChatColor.RED + "Redstone Pigman",
                3,
                150,
                5,
                150,
                0,
                new Equipment(null, null, null, null, Material.GOLD_SWORD, null),
                110,
                0,
                8,
                new ItemDrop(ItemMaterial.get("ROTTEN_FLESH"), 1, 6),
                new ItemDrop(ItemMaterial.get("REDSTONE"), 1, 5),
                new RareDrop(ItemMaterial.get("ENCHANTED_REDSTONE"), 1, 2, 2d, RareDrop.Rarity.RARE),
                new RareDrop(ItemMaterial.get("PIGMAN_HELMET"), 1, 1d, RareDrop.Rarity.RARE),
                new RareDrop(ItemMaterial.get("PIGMAN_CHESTPLATE"), 1, 1d, RareDrop.Rarity.RARE),
                new RareDrop(ItemMaterial.get("PIGMAN_LEGGINGS"), 1, 1d, RareDrop.Rarity.RARE),
                new RareDrop(ItemMaterial.get("PIGMAN_BOOTS"), 1, 1d, RareDrop.Rarity.RARE),
                new RareDrop(ItemMaterial.get("PIGMAN_DAGGER"), 1, 2d, RareDrop.Rarity.RARE)
        ));

        Entities.put("SLIME", new EntityMaterial(
                EntityType.SLIME,
                ChatColor.GREEN + "Slime",
                5,
                200,
                10,
                150,
                0,
                new Equipment(),
                120,
                0,
                10,
                new ItemDrop(ItemMaterial.get("SLIME_BALL"), 1, 10),
                new ItemDrop(ItemMaterial.get("EMERALD"), 1, 6),
                new RareDrop(ItemMaterial.get("SLIME_TALISMAN"), 1, 1d, RareDrop.Rarity.RARE)
        ));

        Entities.put("DIAMOND_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.AQUA + "Diamond Zombie",
                8,
                300,
                10,
                250,
                0,
                new Equipment(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD, null),
                110,
                0.1,
                20,
                new ItemDrop(ItemMaterial.get("DIAMOND"), 1, 7),
                new RareDrop(ItemMaterial.get("ENCHANTED_DIAMOND"), 1, 1d, RareDrop.Rarity.RARE)
        ));

        Entities.put("OBSIDIAN_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.DARK_GRAY + "Obsidian Zombie",
                11,
                500,
                20,
                400,
                0,
                new Equipment(Material.OBSIDIAN, null, null, null, Material.STONE_SWORD, null),
                80,
                0.2,
                35,
                new ItemDrop(ItemMaterial.get("OBSIDIAN"), 1, 4),
                new RareDrop(ItemMaterial.get("ENCHANTED_OBSIDIAN"), 1, 1d, RareDrop.Rarity.RARE)
        ));

        Entities.put("DUMMY", new EntityMaterial(EntityType.ZOMBIE, "Dummy", Integer.MAX_VALUE, 500000, 0, 0, 0, new Equipment(), 0, 1, false, 0));

        Entities.put("PLAYER", new EntityMaterial(EntityType.PLAYER, "", -1, -1, -1, -1, -1, new Equipment(), -1, -1, -1));

        NULL = new EntityMaterial(EntityType.SKELETON, "Null", 0, 1, 0, 0, 0, new Equipment(), 0, 1, 0);
    }

    public static EntityMaterial get(String name) {
        return Entities.getOrDefault(name, EntityMaterial.NULL);
    }

    public String name() {
        for (String key : Entities.keySet()) {
            if (Entities.get(key) == this) {
                return key;
            }
        }
        return "";
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("entityType", entityType);
        map.put("name", name);
        map.put("level", level);
        map.put("health", health);
        map.put("defense", defense);
        map.put("equipment", equipment);
        map.put("speed", speed);
        map.put("knockbackResistance", knockbackResistance);
        map.put("ai", ai);
        map.put("combatXp", combatXp);
        return map;
    }
}
