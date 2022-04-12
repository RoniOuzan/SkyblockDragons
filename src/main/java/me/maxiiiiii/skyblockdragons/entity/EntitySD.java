package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ToolMaterial;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class EntitySD extends EntityClass {
    public static final HashMap<UUID, EntitySD> entities = new HashMap<>();
    public static HashMap<Location, EntityMaterial> entitiesLocations = new HashMap<>();

    public LivingEntity entity;
    public EntityMaterial type;
    public EntitySD attacker;
    public Location location;

    public final Cooldown<Player> actionBarCooldown = new Cooldown<>();
    public final Cooldown<EntitySD> damageCooldownMelee = new Cooldown<>();
    public final Cooldown<EntitySD> damageCooldownProjectile = new Cooldown<>();
    public final Cooldown<EntitySD> damageCooldownLava = new Cooldown<>();

    public EntitySD(Location location, EntityMaterial type) {
        super((LivingEntity) location.getWorld().spawnEntity(location, type.entityType));
        this.type = type;
        this.entity = super.entity;

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
        if (this.type.level < 0)
            this.entity.setCustomName(ChatColor.WHITE + this.type.name);
        else
            this.entity.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv " + this.type.level + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + this.type.name);
        this.entity.setCustomNameVisible(true);
        this.entity.setAI(this.type.ai);
        this.entity.setCanPickupItems(false);
        this.entity.addScoreboardTag("EntitySD");

        if (this.entity instanceof Zombie)
            ((Zombie) this.entity).setBaby(false);

        this.entity.setRemoveWhenFarAway(false);
        if (this.entity.getVehicle() != null)
            this.entity.getVehicle().remove();

        this.location = location;

        this.attacker = null;

        entities.put(this.entity.getUniqueId(), this);
    }

    public EntitySD(LivingEntity entity) {
        super(entity);
        if (!isEntitySD(entity)) throw new EntityAssociateException("Tried to Associate entity " + Functions.getEntityName(entity));

        this.type = Functions.getEntityMaterial(entity);
        this.entity = entity;

        entities.put(this.entity.getUniqueId(), this);
    }

    public static void saveLocations() {
        int i = 0;
        for (Location location : entitiesLocations.keySet()) {
            Variables.set("EntitiesSpawnsLocations", i, location);
            Variables.set("EntitiesSpawnsEntity", i, entitiesLocations.get(location));
            i++;
        }
    }

    public static void loadLocations() {
        for (int i = 0; i < Variables.getSize("EntitiesSpawnsLocations"); i++) {
            if (Variables.get("EntitiesSpawnsEntity", i) != null)
                entitiesLocations.put(Variables.get("EntitiesSpawnsLocations", i), Variables.get("EntitiesSpawnsEntity", i));
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
        this.entity.remove();
        entities.remove(this.entity.getUniqueId());
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

    public class Equipment {
        public ItemStack tool;
        public ItemStack helmet;
        public ItemStack chestplate;
        public ItemStack leggings;
        public ItemStack boots;
        public ToolMaterial toolMaterial;
        public ArmorMaterial helmetMaterial;
        public ArmorMaterial chestplateMaterial;
        public ArmorMaterial leggingsMaterial;
        public ArmorMaterial bootsMaterial;
        public String fullSet;

        public Equipment() {
            tool = entity.getEquipment().getItemInMainHand();
            toolMaterial = ToolMaterial.NULL;
            if (Functions.getItemMaterial(tool) instanceof ToolMaterial) {
                toolMaterial = (ToolMaterial) Functions.getItemMaterial(tool);
            }

            helmet = entity.getEquipment().getHelmet();
            helmetMaterial = ArmorMaterial.NULL;
            if (Functions.getItemMaterial(helmet) instanceof ArmorMaterial) {
                helmetMaterial = (ArmorMaterial) Functions.getItemMaterial(helmet);
            }

            chestplate = entity.getEquipment().getChestplate();
            chestplateMaterial = ArmorMaterial.NULL;
            if (Functions.getItemMaterial(chestplate) instanceof ArmorMaterial) {
                chestplateMaterial = (ArmorMaterial) Functions.getItemMaterial(chestplate);
            }

            leggings = entity.getEquipment().getLeggings();
            leggingsMaterial = ArmorMaterial.NULL;
            if (Functions.getItemMaterial(leggings) instanceof ArmorMaterial) {
                leggingsMaterial = (ArmorMaterial) Functions.getItemMaterial(leggings);
            }

            boots = entity.getEquipment().getBoots();
            bootsMaterial = ArmorMaterial.NULL;
            if (Functions.getItemMaterial(boots) instanceof ArmorMaterial) {
                bootsMaterial = (ArmorMaterial) Functions.getItemMaterial(boots);
            }

            fullSet = "";
            if (helmetMaterial.getFullSet().getName().equals(chestplateMaterial.getFullSet().getName()) && chestplateMaterial.getFullSet().getName().equals(leggingsMaterial.getFullSet().getName()) && leggingsMaterial.getFullSet().getName().equals(bootsMaterial.getFullSet().getName())) {
                fullSet = helmetMaterial.getFullSet().getName();
            }
        }
    }

    public Equipment getItems() {
        return new Equipment();
    }

    public Cooldown<EntitySD> getDamageCooldown(Damage.DamageType type) {
        switch (type) {
            case NORMAL:
            case FALL:
                return this.damageCooldownMelee;

            case PROJECTILE:
                return this.damageCooldownProjectile;
        }
        return this.damageCooldownMelee;
    }

    public static class EntityAssociateException extends RuntimeException {
        public EntityAssociateException(String message) {
            super(message);
        }
    }
}
