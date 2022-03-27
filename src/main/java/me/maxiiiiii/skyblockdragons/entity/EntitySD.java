package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.UUID;

public class EntitySD {
    public static final HashMap<UUID, EntitySD> entities = new HashMap<>();

    public LivingEntity entity;
    public EntityMaterial type;
    public PlayerSD attacker;

    public EntitySD(Location location, EntityMaterial type) {
        this.type = type;
        this.entity = (LivingEntity) location.getWorld().spawnEntity(location, type.entityType);

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

        this.entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(this.type.speed);
        this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.type.health);
        this.entity.setHealth(this.type.health);
        this.entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(this.type.knockbackResistance);
        this.entity.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " + this.type.level + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + this.type.name);
        this.entity.setCustomNameVisible(true);
        this.entity.setAI(this.type.ai);
        this.entity.setCanPickupItems(false);

        this.attacker = null;

        entities.put(this.entity.getUniqueId(), this);
    }

    public EntitySD(LivingEntity entity) {
        if (!isEntitySD(entity)) throw new EntityAssociateException("Tried to Associate entity " + Functions.getEntityName(entity));

        this.type = Functions.getEntityMaterial(entity);
        this.entity = entity;

        entities.put(this.entity.getUniqueId(), this);
    }

    public static EntitySD get(UUID uuid) {
        return entities.get(uuid);
    }

    public static boolean isEntitySD(LivingEntity entity) {
        EntityMaterial type = Functions.getEntityMaterial(entity);
        return type != EntityMaterial.NULL;
    }

    public void setAttacker(PlayerSD attacker) {
        this.attacker = attacker;
    }

    public PlayerSD getKiller() {
        return this.attacker;
    }

    public double getHealth() {
        return Math.round(this.entity.getHealth() * 100d) / 100d;
    }

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
        return this.entity instanceof Spider || this.entity instanceof Silverfish;
    }

    public boolean isEnderMob() {
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

    public static class EntityAssociateException extends RuntimeException {
        public EntityAssociateException(String message) {
            super(message);
        }
    }
}
