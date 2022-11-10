package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.events.EntityDeathEvent;
import me.maxiiiiii.skyblockdragons.entity.types.theend.EntityDragon;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.events.PlayerDeathEvent;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.world.worlds.end.TheEnd;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;

import java.util.HashMap;
import java.util.UUID;

public class EntitySD extends EntityClass {
    public static final HashMap<UUID, EntitySD> entities = new HashMap<>();
    public static HashMap<Location, EntityMaterial> entitiesLocations = new HashMap<>();

    public EntityMaterial type;
    public EntityHologram hologram;
    public EntitySD attacker;
    public Location location;
    protected Equipment equipment;

    public final Cooldown<Player> actionBarCooldown = new Cooldown<>();

    public EntitySD(Location location, EntityMaterial type) {
        super((LivingEntity) location.getWorld().spawnEntity(location, type.entityType));
        this.type = type;

        if (this.type.equipment.helmet != null)
            this.entity.getEquipment().setHelmet(this.type.equipment.helmet);
        this.entity.getEquipment().setHelmetDropChance(0);
        if (this.type.equipment.chestplate != null)
            this.entity.getEquipment().setChestplate(this.type.equipment.chestplate);
        this.entity.getEquipment().setChestplateDropChance(0);
        if (this.type.equipment.leggings != null)
            this.entity.getEquipment().setLeggings(this.type.equipment.leggings);
        this.entity.getEquipment().setLeggingsDropChance(0);
        if (this.type.equipment.boots != null)
            this.entity.getEquipment().setBoots(this.type.equipment.boots);
        this.entity.getEquipment().setBootsDropChance(0);
        if (this.type.equipment.hand != null)
            this.entity.getEquipment().setItemInMainHand(this.type.equipment.hand);
        this.entity.getEquipment().setLeggingsDropChance(0);
        if (this.type.equipment.offHand != null)
            this.entity.getEquipment().setItemInOffHand(this.type.equipment.offHand);
        this.entity.getEquipment().setBootsDropChance(0);

        this.entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(this.type.speed / 500);
        this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.type.health);
        this.entity.setHealth(this.type.health);
        this.entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(this.type.knockbackResistance);
        if (this.type instanceof EntityDragon)
            this.entity.setCustomName(this.getCustomName());
        this.entity.setCustomNameVisible(false);
        this.entity.setAI(this.type.ai);
        this.entity.setCanPickupItems(false);
        this.entity.addScoreboardTag("EntitySD");

        this.hologram = new EntityHologram(this);

        if (this.entity instanceof Zombie)
            ((Zombie) this.entity).setBaby(false);

        this.entity.setRemoveWhenFarAway(false);
        if (this.entity.getVehicle() != null)
            this.entity.getVehicle().remove();

        this.type.onSpawn(this);
        Functions.While(() -> !this.entity.isDead(), 1, i -> this.type.onTick(this), i -> {
            if (!this.hologram.getStand().isDead()){
                this.hologram.remove();
            }
        });

        this.location = location;
        this.equipment = new Equipment(this);

        this.attacker = null;

        entities.put(this.entity.getUniqueId(), this);
    }

    @Override
    public String getName() {
        if (this instanceof PlayerSD) {
            return ((PlayerSD) this).getDisplayName();
        }
        return this.type.name;
    }

    @Override
    public String getCustomName() {
        if (this.type.level < 0)
            return ChatColor.WHITE + this.type.name;
        return ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " + this.type.level + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + this.type.name;
    }

    public EntitySD(LivingEntity entity) {
        super(entity);
        if (!isEntitySD(entity)) throw new EntityAssociateException("Tried to associate entity " + Functions.getEntityName(entity));

        this.type = Functions.getEntityMaterial(entity);
        this.equipment = new Equipment(this);

        entities.put(this.entity.getUniqueId(), this);
    }

    public static void saveLocations() {
        int i = 0;
        Variables.VARIABLES.get().set("EntitiesSpawns", null);
        for (Location location : entitiesLocations.keySet()) {
            Variables.set("EntitiesSpawns", i, new EntitySpawn(location, entitiesLocations.get(location).name()));
            i++;
        }
    }

    public static void loadLocations() {
        long entitiesSpawnsLocations = Variables.getSize("EntitiesSpawns");
        SkyblockDragons.logger.info(String.format("Loading Entity locations... %s", entitiesSpawnsLocations));
        for (int i = 0; i < entitiesSpawnsLocations; i++) {
            EntitySpawn spawn = Variables.get("EntitiesSpawns", i);
            if (spawn == null) {
                SkyblockDragons.logger.info(String.format("Skip entity %s object NULL", i));
                continue;
            }
            Location location = spawn.getLocation();
            EntityMaterial material = spawn.getEntityMaterial();
            if (material == EntityMaterial.NULL) {
                if (location.getWorld().getName().equals("DeepMines")) {
                    if (location.getY() >= 170) {
                        material = EntityMaterial.get("GOLDEN_SKELETON");
                    } else if (location.getY() >= 120) {
                        material = Functions.getRandom(EntityMaterial.get("LAPIS_ZOMBIE"), EntityMaterial.get("REDSTONE_PIGMAN"), EntityMaterial.get("EMERALD_CREEPER"));
                    } else {
                        material = Functions.getRandom(EntityMaterial.get("DIAMOND_ZOMBIE"), EntityMaterial.get("DIAMOND_ZOMBIE"), EntityMaterial.get("OBSIDIAN_ZOMBIE"));
                    }
                }
                SkyblockDragons.logger.info(String.format("Set entity %s material NULL to %s", i, material.getName()));
            }
            if (location.getWorld().getName().equals("TheEnd")) {
                if (material.name().startsWith("ENDER") || material == EntityMaterial.NULL) {
                    if (location.distance(TheEnd.MIDDLE) <= 60) {
                        material = EntityMaterial.get("ENDER_GUARD");
                    } else if (location.distance(TheEnd.MIDDLE) <= 100) {
                        material = EntityMaterial.get("ENDERMAN_TIER_2");
                    } else
                        material = EntityMaterial.get("ENDERMAN_TIER_1");
                }
            }
            if (material != null) {
                SkyblockDragons.logger.info(String.format("Entity at %s has material %s", location, material.getName()));
            }
            entitiesLocations.put(location, material);
        }
    }

    public static EntitySD get(UUID uuid) {
        return entities.get(uuid);
    }

    public static EntitySD get(Entity entity) {
        return entities.get(entity.getUniqueId());
    }

    public static boolean isEntitySD(LivingEntity entity) {
        EntityMaterial type = Functions.getEntityMaterial(entity);
        return type != EntityMaterial.NULL;
    }

    public void kill() {
        if (this.hologram != null)
            this.hologram.remove();
        entities.remove(this.entity.getUniqueId());
        Functions.Wait(1L, () -> this.entity.setHealth(0));
    }

    public void setAttacker(EntitySD attacker) {
        this.attacker = attacker;
    }

    public EntitySD getAttacker() {
        return this.attacker;
    }

    public double getHealth() {
        return Math.round(this.entity.getHealth() * 100d) / 100d;
    }

    @Override
    public double getMaxHealth() {
        return this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
    }

    public double getDefense() {
        return this.type.defense;
    }

    public boolean isUndead() {
        return this.entity instanceof Zombie || this.entity instanceof Skeleton;
    }

    public boolean isBaneOfArthropods() {
        return this.entity instanceof Spider || this.entity instanceof Silverfish | this.entity instanceof Endermite;
    }

    public boolean isEndMob() {
        return this.entity instanceof Enderman || this.entity instanceof EnderDragon || this.entity instanceof Endermite;
    }

    public boolean isCubism() {
        return this.entity instanceof Slime || this.entity instanceof Creeper;
    }

    public boolean isImpaled() {
        return this.entity instanceof Guardian || this.entity instanceof Squid;
    }

    public Location getLocation() {
        return this.entity.getLocation();
    }

    public short getEnchantLevel(EnchantType enchant) {
        return Functions.getEnchantLevel(entity.getEquipment().getItemInMainHand(), enchant);
    }

    public short getEnchantLevel(EnchantType enchant, Condition condition) {
        if (!condition.check())
            return 0;
        return Functions.getEnchantLevel(entity.getEquipment().getItemInMainHand(), enchant);
    }

    public EntityEquipment getEquipment() {
        return this.entity.getEquipment();
    }


    public Equipment getItems() {
        return this.equipment;
    }

    public void removeHealth(double amount) {
        if (this.getHealth() - amount <= 0) {
            if (this instanceof PlayerSD)
                Bukkit.getPluginManager().callEvent(new PlayerDeathEvent((PlayerSD) this));
            else
                Bukkit.getPluginManager().callEvent(new EntityDeathEvent(this, this.getAttacker(), EntityDeathEvent.DeathCause.ENTITY));
        } else {
            this.damage(0);
            this.setHealth(this.getHealth() - amount);
        }

        if (this.hologram != null)
            this.hologram.update();
    }

    public static class EntityAssociateException extends RuntimeException {
        public EntityAssociateException(String message) {
            super(message);
        }
    }
}
