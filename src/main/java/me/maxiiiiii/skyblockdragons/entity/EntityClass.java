package me.maxiiiiii.skyblockdragons.entity;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EntityClass implements LivingEntity {
    public LivingEntity entity;

    public EntityClass(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public double getEyeHeight() {
        return this.entity.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return this.entity.getEyeHeight(ignorePose);
    }

    @Override
    public Location getEyeLocation() {
        return this.entity.getEyeLocation();
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return this.entity.getLineOfSight(transparent, maxDistance);
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        return this.entity.getTargetBlock(transparent, maxDistance);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        return this.entity.getLastTwoTargetBlocks(transparent, maxDistance);
    }

    @Override
    public int getRemainingAir() {
        return this.entity.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int ticks) {
        this.entity.setRemainingAir(ticks);
    }

    @Override
    public int getMaximumAir() {
        return this.entity.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int ticks) {
        this.entity.setMaximumAir(ticks);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return this.entity.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        this.entity.setMaximumNoDamageTicks(ticks);
    }

    @Override
    public double getLastDamage() {
        return this.entity.getLastDamage();
    }

    @Override
    public void setLastDamage(double damage) {
        this.entity.setLastDamage(damage);
    }

    @Override
    public int getNoDamageTicks() {
        return this.entity.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        this.entity.setNoDamageTicks(ticks);
    }

    @Override
    public Player getKiller() {
        return this.entity.getKiller();
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return this.entity.addPotionEffect(effect);
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        return this.entity.addPotionEffect(effect, force);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        return this.entity.addPotionEffects(effects);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return this.entity.hasPotionEffect(type);
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        return this.entity.getPotionEffect(type);
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        this.entity.removePotionEffect(type);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return this.entity.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        return this.entity.hasLineOfSight(other);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return this.entity.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        this.entity.setRemoveWhenFarAway(false);
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.entity.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        this.entity.setCanPickupItems(pickup);
    }

    @Override
    public boolean getCanPickupItems() {
        return this.entity.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return this.entity.isLeashed();
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return this.entity.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        return this.entity.setLeashHolder(holder);
    }

    @Override
    public boolean isGliding() {
        return this.entity.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        this.entity.setGliding(gliding);
    }

    @Override
    public void setAI(boolean ai) {
        this.entity.setAI(ai);
    }

    @Override
    public boolean hasAI() {
        return this.entity.hasAI();
    }

    @Override
    public void setCollidable(boolean collidable) {
        this.entity.setCollidable(collidable);
    }

    @Override
    public boolean isCollidable() {
        return this.entity.isCollidable();
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return this.entity.getAttribute(attribute);
    }

    @Override
    public void damage(double amount) {
        this.entity.damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.entity.damage(amount, source);
    }

    @Override
    public double getHealth() {
        return this.entity.getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.entity.setHealth(health);
    }

    @Override
    public double getMaxHealth() {
        return this.entity.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.entity.setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        this.entity.resetMaxHealth();
    }

    @Override
    public Location getLocation() {
        return this.entity.getLocation();
    }

    @Override
    public Location getLocation(Location loc) {
        return this.entity.getLocation(loc);
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.entity.setVelocity(velocity);
    }

    @Override
    public Vector getVelocity() {
        return this.entity.getVelocity();
    }

    @Override
    public double getHeight() {
        return this.entity.getHeight();
    }

    @Override
    public double getWidth() {
        return this.entity.getWidth();
    }

    @Override
    public boolean isOnGround() {
        return this.entity.isOnGround();
    }

    @Override
    public World getWorld() {
        return this.entity.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        return this.entity.teleport(location);
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        return this.entity.teleport(location, cause);
    }

    @Override
    public boolean teleport(Entity destination) {
        return this.entity.teleport(destination);
    }

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return this.entity.teleport(destination, cause);
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return this.entity.getNearbyEntities(x, y, z);
    }

    public List<Entity> getNearbyEntities(double range) {
        return this.getNearbyEntities(range, range, range);
    }

    @Override
    public int getEntityId() {
        return this.entity.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return this.entity.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return this.entity.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.entity.setFireTicks(ticks);
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public boolean isDead() {
        return this.entity.isDead();
    }

    @Override
    public boolean isValid() {
        return this.entity.isValid();
    }

    @Override
    public void sendMessage(String message) {
        this.entity.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        this.entity.sendMessage(messages);
    }

    @Override
    public Server getServer() {
        return this.entity.getServer();
    }

    @Override
    public String getName() {
        return this.entity.getName();
    }

    @Override
    public Entity getPassenger() {
        return this.entity.getPassenger();
    }

    @Override
    public boolean setPassenger(Entity passenger) {
        return this.entity.setPassenger(passenger);
    }

    @Override
    public List<Entity> getPassengers() {
        return this.entity.getPassengers();
    }

    @Override
    public boolean addPassenger(Entity passenger) {
        return this.entity.addPassenger(passenger);
    }

    @Override
    public boolean removePassenger(Entity passenger) {
        return this.entity.removePassenger(passenger);
    }

    @Override
    public boolean isEmpty() {
        return this.entity.isEmpty();
    }

    @Override
    public boolean eject() {
        return this.entity.eject();
    }

    @Override
    public float getFallDistance() {
        return this.entity.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        this.entity.setFallDistance(distance);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        this.entity.setLastDamageCause(event);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.entity.getLastDamageCause();
    }

    @Override
    public UUID getUniqueId() {
        return this.entity.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return this.entity.getTicksLived();
    }

    @Override
    public void setTicksLived(int value) {
        this.entity.setTicksLived(value);
    }

    @Override
    public void playEffect(EntityEffect type) {
        this.entity.playEffect(type);
    }

    @Override
    public EntityType getType() {
        return this.entity.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return this.entity.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return this.entity.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return this.entity.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        this.entity.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.entity.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        this.entity.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return this.entity.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        this.entity.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return this.entity.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return this.entity.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        this.entity.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return this.entity.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        this.entity.setGravity(gravity);
    }

    @Override
    public int getPortalCooldown() {
        return this.entity.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        this.entity.setPortalCooldown(cooldown);
    }

    @Override
    public Set<String> getScoreboardTags() {
        return this.entity.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        return this.entity.addScoreboardTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        return this.entity.removeScoreboardTag(tag);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return this.entity.getPistonMoveReaction();
    }

    @Override
    public Spigot spigot() {
        return this.entity.spigot();
    }

    @Override
    public String getCustomName() {
        return this.entity.getCustomName();
    }

    @Override
    public void setCustomName(String name) {
        this.entity.setCustomName(name);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.entity.setMetadata(metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.entity.getMetadata(metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.entity.hasMetadata(metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.entity.removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public boolean isPermissionSet(String name) {
        return this.entity.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return this.entity.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return this.entity.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return this.entity.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return this.entity.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.entity.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return this.entity.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return this.entity.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        this.entity.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        this.entity.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.entity.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return this.entity.isOp();
    }

    @Override
    public void setOp(boolean value) {
        this.entity.setOp(value);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return this.entity.launchProjectile(projectile);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        return this.entity.launchProjectile(projectile, velocity);
    }
}
