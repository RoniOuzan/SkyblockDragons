package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.*;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class EntityMaterial implements ConfigurationSerializable {
    public static HashMap<String, EntityMaterial> entities = new HashMap<>();

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
    public double coins;

    public ItemDrop[] drops;

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, boolean ai, double combatXp, double coins, ItemDrop... drops) {
        this.entityType = entityType;
        this.name = name;
        this.level = level;
        this.health = health;
        this.defense = defense;
        this.damage = damage;
        this.trueDamage = trueDamage;
        this.equipment = equipment;
        this.speed = speed;
        this.knockbackResistance = knockbackResistance;
        this.ai = ai;
        this.combatXp = combatXp;
        this.coins = coins;
        this.drops = drops;
    }

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, double coins, ItemDrop... drops) {
        this(entityType, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, combatXp, coins, drops);
    }

    public static void registerItems() {
        entities.put("GOLDEN_SKELETON", new EntityMaterial(
                EntityType.SKELETON,
                ChatColor.GOLD + "Golden Skeleton",
                1,
                50,
                0,
                20,
                0,
                new Equipment(Material.GOLD_HELMET, null, null, null, Material.BOW, null),
                105,
                0,
                4,
                0.5,
                new RareDrop(Items.get("GOLDEN_SKELETON_BOW"), 1, 2d),
                new RareDrop(Items.get("GOLDEN_SKELETON_HELMET"), 1, 2d),
                new ItemDrop(Items.get("BONE"), 1, 4),
                new ItemDrop(Items.get("ARROW"), 2, 10)
        ));

        entities.put("LAPIS_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.BLUE + "Lapis Zombie",
                2,
                80,
                0,
                100,
                0,
                new Equipment(Material.LAPIS_BLOCK, null, null, null, Material.STONE_SWORD, null),
                110,
                0,
                5,
                1,
                new ItemDrop(Items.get("LAPIS"), 1, 6),
                new RareDrop(Items.get("ENCHANTED_LAPIS"), 1, 2, 2d),
                new RareDrop(Items.get("LAPIS_TALISMAN"), 1, 0.5d)
        ));

        entities.put("REDSTONE_PIGMAN", new EntityMaterial(
                EntityType.PIG_ZOMBIE,
                ChatColor.RED + "Redstone Pigman",
                3,
                150,
                5,
                150,
                0,
                new Equipment(null, null, null, null, Material.GOLD_SWORD, null),
                125,
                0,
                10,
                1,
                new ItemDrop(Items.get("ROTTEN_FLESH"), 1, 6),
                new ItemDrop(Items.get("REDSTONE"), 1, 5),
                new RareDrop(Items.get("ENCHANTED_REDSTONE"), 1, 2, 1d),
                new RareDrop(Items.get("PIGMAN_HELMET"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_CHESTPLATE"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_LEGGINGS"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_BOOTS"), 1, 2d),
                new RareDrop(Items.get("PIGMAN_DAGGER"), 1, 2d),
                new RareDrop(Items.get("REDSTONE_TALISMAN"), 1, 0.5d)
        ));

        entities.put("EMERALD_CREEPER", new EntityMaterial(
                EntityType.CREEPER,
                ChatColor.GREEN + "Emerald Creeper",
                5,
                100,
                15,
                100,
                0,
                new Equipment(),
                140,
                0,
                15,
                2,
                new ItemDrop(Items.get("GUNPOWDER"), 1, 10),
                new ItemDrop(Items.get("EMERALD"), 1, 6),
                new RareDrop(Items.get("EMERALD_TALISMAN"), 1, 0.5d)
        ));

        entities.put("DIAMOND_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.AQUA + "Diamond Zombie",
                8,
                300,
                10,
                200,
                0,
                new Equipment(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD, null),
                120,
                0.1,
                25,
                3,
                new ItemDrop(Items.get("DIAMOND"), 1, 5),
                new RareDrop(Items.get("ENCHANTED_DIAMOND"), 1, 1d),
                new RareDrop(Items.get("DIAMOND_TALISMAN"), 1, 0.5d),
                new RareDrop(Items.get("DIAMOND_BLOCK_TALISMAN"), 1, 0.1d)
        ));

        entities.put("OBSIDIAN_ZOMBIE", new EntityMaterial(
                EntityType.ZOMBIE,
                ChatColor.DARK_GRAY + "Obsidian Zombie",
                11,
                500,
                20,
                300,
                0,
                new Equipment(Material.OBSIDIAN, null, null, null, Material.STONE_SWORD, null),
                100,
                0.2,
                35,
                4,
                new ItemDrop(Items.get("OBSIDIAN"), 1, 4),
                new RareDrop(Items.get("ENCHANTED_OBSIDIAN"), 1, 2d),
                new RareDrop(Items.get("OBSIDIAN_HELMET"), 1, 1.5d),
                new RareDrop(Items.get("OBSIDIAN_LEGGINGS"), 1, 1.5d),
                new RareDrop(Items.get("OBSIDIAN_LEGGINGS"), 1, 1.5d),
                new RareDrop(Items.get("OBSIDIAN_BOOTS"), 1, 1.5d),
                new RareDrop(Items.get("OBSIDIAN_TALISMAN"), 1, 0.5d)
        ));

        entities.put("ENDERMAN_TIER_1", new EntityMaterial(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 1",
                15,
                700,
                0,
                350,
                0,
                new Equipment(),
                100,
                0,
                50,
                8,
                new ItemDrop(Items.get("ENDER_PEARL"), 1),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 0.35),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.15),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.04)
        ));

        entities.put("ENDERMAN_TIER_2", new EntityMaterial(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 2",
                18,
                900,
                0,
                400,
                0,
                new Equipment(),
                100,
                0,
                75,
                12,
                new ItemDrop(Items.get("ENDER_PEARL"), 1, 2),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 0.5),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.2),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.05),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_EPIC"), 1, 0.01)
        ));

        entities.put("ENDERMAN_TIER_3", new EntityMaterial(
                EntityType.ENDERMAN,
                ChatColor.WHITE + "Enderman Tier 3",
                21,
                1100,
                2,
                400,
                0,
                new Equipment(),
                100,
                0,
                75,
                12,
                new ItemDrop(Items.get("ENDER_PEARL"), 1, 3),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_COMMON"), 1, 0.75),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_UNCOMMON"), 1, 0.3),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_RARE"), 1, 0.1),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_EPIC"), 1, 0.05),
                new RareDrop(Items.get("ENDERMAN_TALISMAN_LEGENDARY"), 1, 0.02)
        ));

        entities.put("ENDER_GUARD", new EntityMaterial(
                EntityType.ENDERMAN,
                ChatColor.DARK_PURPLE + "Ender Guard",
                24,
                1300,
                5,
                500,
                0,
                new Equipment(),
                100,
                0,
                110,
                15,
                new ItemDrop(Items.get("ENDER_PEARL"), 1, 3),
                new RareDrop(Items.get("SUMMONING_EYE"), 1, 1)
        ));

        entities.put("DUMMY", new EntityMaterial(EntityType.ZOMBIE, "Dummy", Integer.MAX_VALUE, 500000, 0, 0, 0, new Equipment(), 0, 1, false, 0, 0));

        entities.put("PLAYER", new EntityMaterial(EntityType.PLAYER, "", -1, -1, -1, -1, -1, new Equipment(), -1, 0, -1, -1));

        NULL = new EntityMaterial(EntityType.SKELETON, "Null", 0, 1, 0, 0, 0, new Equipment(), 0, 1, 0, 0);
    }

    public static EntityMaterial get(String name) {
        return entities.getOrDefault(name, EntityMaterial.NULL);
    }

    public String name() {
        for (String key : entities.keySet()) {
            if (entities.get(key) == this) {
                return key;
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "EntityMaterial{" +
                "entityType=" + entityType +
                "name=" + name +
                "level=" + level +
                "health=" + health +
                "defense=" + defense +
                "damage=" + damage +
                "trueDamage=" + trueDamage +
                "equipment=" + equipment +
                "speed=" + speed +
                "knockbackResistance=" + knockbackResistance +
                "ai=" + ai +
                "combatXp=" + combatXp +
                "drops=" + Arrays.toString(drops) +
        '}';
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.name());
        return map;
    }

    public static EntityMaterial deserialize(Map<String, Object> args) {
        return EntityMaterial.get((String) args.get("id"));
    }
}
