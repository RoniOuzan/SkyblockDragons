package me.maxiiiiii.skyblockdragons.entity;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.types.bearisland.Boss.*;
import me.maxiiiiii.skyblockdragons.entity.types.bearisland.Normal.*;
import me.maxiiiiii.skyblockdragons.entity.types.deepermines.Ghost;
import me.maxiiiiii.skyblockdragons.entity.types.deepermines.IceMiner;
import me.maxiiiiii.skyblockdragons.entity.types.deepmines.*;
import me.maxiiiiii.skyblockdragons.entity.types.eternity.ERROR_Infinity;
import me.maxiiiiii.skyblockdragons.entity.types.eternity.EternityBoss;
import me.maxiiiiii.skyblockdragons.entity.types.eternity.Infinity;
import me.maxiiiiii.skyblockdragons.entity.types.eternity.Super_Infinity;
import me.maxiiiiii.skyblockdragons.entity.types.other.Dummy;
import me.maxiiiiii.skyblockdragons.entity.types.other.NullEntity;
import me.maxiiiiii.skyblockdragons.entity.types.other.PlayerEntity;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EnderGuard;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EndermanTier1;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EndermanTier2;
import me.maxiiiiii.skyblockdragons.entity.types.theend.dragon.*;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.WitherGuard;
import me.maxiiiiii.skyblockdragons.entity.types.witherisland.wither.*;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityDrop;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class EntityMaterial implements ConfigurationSerializable, Listener {
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

    public EntityDrop[] drops;

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, boolean ai, double combatXp, double coins, EntityDrop... drops) {
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

        SkyblockDragons.plugin.getServer().getPluginManager().registerEvents(this, SkyblockDragons.plugin);
    }

    public EntityMaterial(EntityType entityType, String name, int level, double health, double defense, double damage, double trueDamage, Equipment equipment, double speed, double knockbackResistance, double combatXp, double coins, EntityDrop... drops) {
        this(entityType, name, level, health, defense, damage, trueDamage, equipment, speed, knockbackResistance, true, combatXp, coins, drops);
    }

    public abstract void onSpawn(EntitySD entity);

    public void onTick(EntitySD entity){
    }

    public static void registerEntities() {
        entities.put("GOLDEN_SKELETON", new GoldenSkeleton());
        entities.put("LAPIS_ZOMBIE", new LapisZombie());
        entities.put("REDSTONE_PIGMAN", new RedstonePigman());
        entities.put("EMERALD_CREEPER", new EmeraldCreeper());
        entities.put("DIAMOND_ZOMBIE", new DiamondZombie());
        entities.put("OBSIDIAN_ZOMBIE", new ObsidianZombie());


        entities.put("ENDERMAN_TIER_1", new EndermanTier1());
        entities.put("ENDERMAN_TIER_2", new EndermanTier2());
        entities.put("ENDER_GUARD", new EnderGuard());


        entities.put("OLD_DRAGON", new OldDragon());
        entities.put("PROTECTOR_DRAGON", new ProtectorDragon());
        entities.put("WISE_DRAGON", new WiseDragon());
        entities.put("UNSTABLE_DRAGON", new UnstableDragon());
        entities.put("YOUNG_DRAGON", new YoungDragon());
        entities.put("STRONG_DRAGON", new StrongDragon());
        entities.put("SUPERIOR_DRAGON", new SuperiorDragon());
        entities.put("ERROR_DRAGON", new ERRORDragon());

        entities.put("ICE_MINER", new IceMiner());
        entities.put("GHOST", new Ghost());

        entities.put("TEST_WITHER", new TestWither());
        entities.put("ATHENA_WITHER", new AthenaWither()); // wise
        entities.put("ARES_WITHER", new AresWither()); // strong
        entities.put("PHANES_WITHER", new PhanesWither()); // old
        entities.put("HERMES_WITHER", new HermesWither()); // young
        entities.put("DEMETER_WITHER", new DemeterWither()); // unstable
        entities.put("ERROR_WITHER", new ErrorWither()); // error
        entities.put("WITHER_GUARD", new WitherGuard());

        entities.put("DUMMY", new Dummy());

        entities.put("PLAYER", new PlayerEntity());

        entities.put("ETERNITYBOSS", new EternityBoss());
        entities.put("INFINITY", new Infinity());
        entities.put("SUPER_INFINITY", new Super_Infinity());
        entities.put("ERROR_INFINITY", new ERROR_Infinity());

        entities.put("GRIZZLY_BEAR", new Grizzly_Bear());
        entities.put("POLAR_BEAR", new Polar_Bear());
        entities.put("PANDA_BEAR", new Panda_Bear());
        entities.put("KOALA_BEAR", new Koala_Bear());
        entities.put("RED_PANDA_BEAR", new Red_Panda_Bear());

        entities.put("RED_PANDA_BEAR_BOSS", new Red_Panda_Bear_Boss());
        entities.put("GRIZZLY_BEAR_BOSS", new Grizzly_Bear_Boss());
        entities.put("POLAR__BEAR_BOSS", new Polar_Bear_Boss());
        entities.put("KOALA_BEAR_BOSS", new Koala_Bear_Boss());
        entities.put("PANDA_BOSS", new Panda_Bear_Boss());

        NullEntity nullEntity = new NullEntity();
        entities.put("NULL", nullEntity);

        NULL = nullEntity;
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
    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.name());
        return map;
    }

    @Utility
    public static EntityMaterial deserialize(Map<String, Object> args) {
        return EntityMaterial.get((String) args.get("id"));
    }
}
