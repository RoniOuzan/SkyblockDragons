package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.itemcreator.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.util.Equipment;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class EntityMaterial {
    public static HashMap<String, EntityMaterial> Entities = new HashMap<>();

    public static EntityMaterial NULL = null;

    public EntityType entityType;
    public String name;
    public int level;
    public double health;
    public double defense;
    public Equipment equipment;
    public double speed;
    public double knockbackResistance;
    public boolean ai;

    public ItemDrop[] drops;

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, Equipment equipment, double speed, double knockbackResistance, boolean ai, ItemDrop... drops) {
        this.entityType = entityType;
        this.name = name;
        this.level = level;
        this.health = health;
        this.defense = defense;
        this.equipment = equipment;
        this.speed = speed;
        this.knockbackResistance = knockbackResistance;
        this.ai = ai;
        this.drops = drops;
    }

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, Equipment equipment, double speed, double knockbackResistance, ItemDrop... drops) {
        this(entityType, name, level, health, defense, equipment, speed, knockbackResistance, true, drops);
    }

    public static void registerItems() {
        Entities.put("ZOMBIE", new EntityMaterial(EntityType.ZOMBIE,
                "Zombie",
                1,
                50,
                0,
                new Equipment(null, null, null, null, new ItemStack(Material.WOOD_SWORD), null),
                0.2,
                0,
                new ItemDrop(ItemMaterial.get("ROTTEN_FLESH"), 5, 8, 100)));

        Entities.put("DUMMY", new EntityMaterial(EntityType.ZOMBIE, "Dummy", Integer.MAX_VALUE, 50000000, 0, new Equipment(null, null, null, null, null, null), 0, 1, false));

        NULL = new EntityMaterial(EntityType.SKELETON, "Null", 0, 1, 0, new Equipment(null, null, null, null, null ,null), 0, 1);
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
}
