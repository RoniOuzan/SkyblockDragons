package me.maxiiiiii.skyblockdragons.player;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.*;

public class PlayerClass extends EntitySD implements Player {
    private final Player player;

    public PlayerClass(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public String getDisplayName() {
        return this.player.getDisplayName();
    }

    @Override
    public void setDisplayName(String name) {
        this.player.setDisplayName(name);
    }

    @Override
    public String getPlayerListName() {
        return this.player.getPlayerListName();
    }

    @Override
    public void setPlayerListName(String name) {
        this.player.setPlayerListName(name);
    }

    @Override
    public void setCompassTarget(Location loc) {
        this.player.setCompassTarget(loc);
    }

    @Override
    public Location getCompassTarget() {
        return this.player.getCompassTarget();
    }

    @Override
    public InetSocketAddress getAddress() {
        return this.player.getAddress();
    }

    @Override
    public boolean isConversing() {
        return this.player.isConversing();
    }

    @Override
    public void acceptConversationInput(String input) {
        this.player.acceptConversationInput(input);
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return this.player.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        this.player.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        this.player.abandonConversation(conversation, details);
    }

    @Override
    public void sendRawMessage(String message) {
        this.player.sendRawMessage(message);
    }

    @Override
    public void kickPlayer(String message) {
        this.player.kickPlayer(message);
    }

    @Override
    public void chat(String msg) {
        this.player.chat(msg);
    }

    @Override
    public boolean performCommand(String command) {
        return this.player.performCommand(command);
    }

    @Override
    public boolean isSneaking() {
        return this.player.isSneaking();
    }

    @Override
    public void setSneaking(boolean sneak) {
        this.player.setSneaking(sneak);
    }

    @Override
    public boolean isSprinting() {
        return this.player.isSprinting();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        this.player.setSprinting(sprinting);
    }

    @Override
    public void saveData() {
        this.player.saveData();
    }

    @Override
    public void loadData() {
        this.player.loadData();
    }

    @Override
    public void setSleepingIgnored(boolean isSleeping) {
        this.player.setSleepingIgnored(isSleeping);
    }

    @Override
    public boolean isSleepingIgnored() {
        return this.player.isSleepingIgnored();
    }

    @Override
    @Deprecated
    public void playNote(Location loc, byte instrument, byte note) {
        this.player.playNote(loc, instrument, note);
    }

    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {
        this.player.playNote(loc, instrument, note);
    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {
        this.player.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {
        this.player.playSound(location, sound, volume, pitch);
    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {
        this.player.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {
        this.player.playSound(location, sound, category, volume, pitch);
    }

    @Override
    public void stopSound(Sound sound) {
        this.player.stopSound(sound);
    }

    @Override
    public void stopSound(String sound) {
        this.player.stopSound(sound);
    }

    @Override
    public void stopSound(Sound sound, SoundCategory category) {
        this.player.stopSound(sound, category);
    }

    @Override
    public void stopSound(String sound, SoundCategory category) {
        this.player.stopSound(sound, category);
    }

    @Override
    @Deprecated
    public void playEffect(Location loc, Effect effect, int data) {
        this.player.playEffect(loc, effect, data);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        this.player.playEffect(loc, effect, data);
    }

    @Override
    @Deprecated
    public void sendBlockChange(Location loc, Material material, byte data) {
        this.player.sendBlockChange(loc, material, data);
    }

    @Override
    @Deprecated
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        return this.player.sendChunkChange(loc, sx, sy, sz, data);
    }

    @Override
    @Deprecated
    public void sendBlockChange(Location loc, int material, byte data) {
        this.player.sendBlockChange(loc, material, data);
    }

    @Override
    public void sendSignChange(Location loc, String[] lines) throws IllegalArgumentException {
        this.player.sendSignChange(loc, lines);
    }

    @Override
    public void sendMap(MapView map) {
        this.player.sendMap(map);
    }

    @Override
    public void updateInventory() {
        this.player.updateInventory();
    }

    @Override
    @Deprecated
    public void awardAchievement(Achievement achievement) {
        this.player.awardAchievement(achievement);
    }

    @Override
    @Deprecated
    public void removeAchievement(Achievement achievement) {
        this.player.removeAchievement(achievement);
    }

    @Override
    @Deprecated
    public boolean hasAchievement(Achievement achievement) {
        return this.player.hasAchievement(achievement);
    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        this.player.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {
        this.player.decrementStatistic(statistic, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {
        this.player.setStatistic(statistic, newValue);
    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return this.player.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        this.player.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return this.player.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic, material, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {
        this.player.decrementStatistic(statistic, material, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int newValue) throws IllegalArgumentException {
        this.player.setStatistic(statistic, material, newValue);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        this.player.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return this.player.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) throws IllegalArgumentException {
        this.player.incrementStatistic(statistic, entityType, amount);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {
        this.player.decrementStatistic(statistic, entityType, amount);
    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {
        this.player.setStatistic(statistic, entityType, newValue);
    }

    @Override
    public void setPlayerTime(long time, boolean relative) {
        this.player.setPlayerTime(time, relative);
    }

    @Override
    public long getPlayerTime() {
        return this.player.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return this.player.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return this.player.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        this.player.resetPlayerTime();
    }

    @Override
    public void setPlayerWeather(WeatherType type) {
        this.player.setPlayerWeather(type);
    }

    @Override
    public WeatherType getPlayerWeather() {
        return this.player.getPlayerWeather();
    }

    @Override
    public void resetPlayerWeather() {
        this.player.resetPlayerWeather();
    }

    @Override
    public void giveExp(int amount) {
        this.player.giveExp(amount);
    }

    @Override
    public void giveExpLevels(int amount) {
        this.player.giveExpLevels(amount);
    }

    @Override
    public float getExp() {
        return this.player.getExp();
    }

    @Override
    public void setExp(float exp) {
        this.player.setExp(exp);
    }

    @Override
    public int getLevel() {
        return this.player.getLevel();
    }

    @Override
    public void setLevel(int level) {
        this.player.setLevel(level);
    }

    @Override
    public int getTotalExperience() {
        return this.player.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int exp) {
        this.player.setTotalExperience(exp);
    }

    @Override
    public float getExhaustion() {
        return this.player.getExhaustion();
    }

    @Override
    public void setExhaustion(float value) {
        this.player.setExhaustion(value);
    }

    @Override
    public float getSaturation() {
        return this.player.getSaturation();
    }

    @Override
    public void setSaturation(float value) {
        this.player.setSaturation(value);
    }

    @Override
    public int getFoodLevel() {
        return this.player.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int value) {
        this.player.setFoodLevel(value);
    }

    @Override
    public boolean isOnline() {
        return this.player.isOnline();
    }

    @Override
    public boolean isBanned() {
        return this.player.isBanned();
    }

    @Override
    public boolean isWhitelisted() {
        return this.player.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean value) {
        this.player.setWhitelisted(value);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public long getFirstPlayed() {
        return this.player.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return this.player.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return this.player.hasPlayedBefore();
    }

    @Override
    public Location getBedSpawnLocation() {
        return this.player.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        this.player.setBedSpawnLocation(location);
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean force) {
        this.player.setBedSpawnLocation(location, force);
    }

    @Override
    public boolean getAllowFlight() {
        return this.player.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean flight) {
        this.player.setAllowFlight(flight);
    }

    @Override
    @Deprecated
    public void hidePlayer(Player player) {
        this.player.hidePlayer(player);
    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {
        this.player.hidePlayer(plugin, player);
    }

    public void hideEntity(Entity entity) {
        if (entity == null) return;

        SkyblockDragons.entityHider.hideEntity(player, entity);
    }

    public void showEntity(Entity entity) {
        if (entity == null) return;

        SkyblockDragons.entityHider.showEntity(player, entity);
    }

    @Override
    @Deprecated
    public void showPlayer(Player player) {
        this.player.showPlayer(player);
    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {
        this.player.showPlayer(plugin, player);
    }

    @Override
    public boolean canSee(Player player) {
        return this.player.canSee(player);
    }

    @Override
    public boolean isFlying() {
        return this.player.isFlying();
    }

    @Override
    public void setFlying(boolean value) {
        this.player.setFlying(value);
    }

    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {
        this.player.setFlySpeed(value);
    }

    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {
        this.player.setWalkSpeed(value);
    }

    @Override
    public float getFlySpeed() {
        return this.player.getFlySpeed();
    }

    @Override
    public float getWalkSpeed() {
        return this.player.getWalkSpeed();
    }

    @Override
    @Deprecated
    public void setTexturePack(String url) {
        this.player.setTexturePack(url);
    }

    @Override
    public void setResourcePack(String url) {
        this.player.setResourcePack(url);
    }

    @Override
    public void setResourcePack(String url, byte[] hash) {
        this.player.setResourcePack(url, hash);
    }

    @Override
    public Scoreboard getScoreboard() {
        return this.player.getScoreboard();
    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        this.player.setScoreboard(scoreboard);
    }

    @Override
    public boolean isHealthScaled() {
        return this.player.isHealthScaled();
    }

    @Override
    public void setHealthScaled(boolean scale) {
        this.player.setHealthScaled(scale);
    }

    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {
        this.player.setHealthScale(scale);
    }

    @Override
    public double getHealthScale() {
        return this.player.getHealthScale();
    }

    @Override
    public Entity getSpectatorTarget() {
        return this.player.getSpectatorTarget();
    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        this.player.setSpectatorTarget(entity);
    }

    @Override
    @Deprecated
    public void sendTitle(String title, String subtitle) {
        this.player.sendTitle(title, subtitle);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    @Override
    public void resetTitle() {
        this.player.resetTitle();
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
        this.player.spawnParticle(particle, location, count);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
        this.player.spawnParticle(particle, x, y, z, count);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
        this.player.spawnParticle(particle, location, count, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
        this.player.spawnParticle(particle, x, y, z, count, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        this.player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        this.player.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, data);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        this.player.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, data);
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.player.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        this.player.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra);
    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return this.player.getAdvancementProgress(advancement);
    }

    @Override
    public String getLocale() {
        return this.player.getLocale();
    }

    @Override
    public Location getLocation() {
        return this.player.getLocation();
    }

    @Override
    public Location getLocation(Location loc) {
        return this.player.getLocation();
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.player.setVelocity(velocity);
    }

    @Override
    public Vector getVelocity() {
        return this.player.getVelocity();
    }

    @Override
    public double getHeight() {
        return this.player.getHeight();
    }

    @Override
    public double getWidth() {
        return this.player.getWidth();
    }

    @Override
    public boolean isOnGround() {
        return this.player.isOnGround();
    }

    @Override
    public World getWorld() {
        return this.player.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        return this.player.teleport(location);
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        return this.player.teleport(location, cause);
    }

    @Override
    public boolean teleport(Entity destination) {
        return this.player.teleport(destination);
    }

    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return this.player.teleport(destination, cause);
    }

    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return this.player.getNearbyEntities(x, y, z);
    }

    @Override
    public int getEntityId() {
        return this.player.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return this.player.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return this.player.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        this.player.setFireTicks(ticks);
    }

    @Override
    public void remove() {
        this.player.remove();
    }

    @Override
    public boolean isDead() {
        return this.player.isDead();
    }

    @Override
    public boolean isValid() {
        return this.player.isValid();
    }

    @Override
    public void sendMessage(String message) {
        this.player.sendMessage(message);
    }

    public void sendMessage(Object message) {
        this.player.sendMessage(String.valueOf(message));
    }

    public void sendMessage(Object... messages) {
        if (messages.length == 0) {
            this.player.sendMessage("");
            return;
        }

        StringBuilder message = new StringBuilder(",");
        for (Object value : messages) {
            message.append(", ").append(value);
        }
        message = new StringBuilder(message.toString().replace(",, ", ""));
        this.player.sendMessage(message.toString());
    }

    @Override
    public void sendMessage(String[] messages) {
        this.player.sendMessage(messages);
    }

    public void sendCenteredMessage(String message) {
        this.sendMessage(message);
    }

    @Override
    public Server getServer() {
        return this.player.getServer();
    }

    @Override
    @Deprecated
    public Entity getPassenger() {
        return this.player.getPassenger();
    }

    @Override
    @Deprecated
    public boolean setPassenger(Entity passenger) {
        return this.player.setPassenger(passenger);
    }

    @Override
    public List<Entity> getPassengers() {
        return this.player.getPassengers();
    }

    @Override
    public boolean addPassenger(Entity passenger) {
        return this.player.addPassenger(passenger);
    }

    @Override
    public boolean removePassenger(Entity passenger) {
        return this.player.removePassenger(passenger);
    }

    @Override
    public boolean isEmpty() {
        return this.player.isEmpty();
    }

    @Override
    public boolean eject() {
        return this.player.eject();
    }

    @Override
    public float getFallDistance() {
        return this.player.getFallDistance();
    }

    @Override
    public void setFallDistance(float distance) {
        this.player.setFallDistance(distance);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        this.player.setLastDamageCause(event);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return this.player.getLastDamageCause();
    }

    @Override
    public UUID getUniqueId() {
        return this.player.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return this.player.getTicksLived();
    }

    @Override
    public void setTicksLived(int value) {
        this.player.setTicksLived(value);
    }

    @Override
    public void playEffect(EntityEffect type) {
        this.player.playEffect(type);
    }

    @Override
    public EntityType getType() {
        return this.player.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return this.player.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return this.player.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return this.player.getVehicle();
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        this.player.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.player.isCustomNameVisible();
    }

    @Override
    public void setGlowing(boolean flag) {
        this.player.setGlowing(flag);
    }

    @Override
    public boolean isGlowing() {
        return this.player.isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        this.player.setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return this.player.isInvulnerable();
    }

    @Override
    public boolean isSilent() {
        return this.player.isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        this.player.setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return this.player.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        this.player.setGravity(gravity);
    }

    @Override
    public int getPortalCooldown() {
        return this.player.getPortalCooldown();
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        this.player.setPortalCooldown(cooldown);
    }

    @Override
    public Set<String> getScoreboardTags() {
        return this.player.getScoreboardTags();
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        return this.player.addScoreboardTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        return this.player.removeScoreboardTag(tag);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return this.player.getPistonMoveReaction();
    }

    @Override
    public Player.Spigot spigot() {
        return this.player.spigot();
    }

    @Override
    public Map<String, Object> serialize() {
        return this.player.serialize();
    }

    @Override
    public String getName() {
        return this.player.getName();
    }

    @Override
    public PlayerInventory getInventory() {
        return this.player.getInventory();
    }

    @Override
    public Inventory getEnderChest() {
        return this.player.getEnderChest();
    }

    @Override
    public MainHand getMainHand() {
        return this.player.getMainHand();
    }

    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        return this.player.setWindowProperty(prop, value);
    }

    @Override
    public InventoryView getOpenInventory() {
        return this.player.getOpenInventory();
    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        return this.player.openInventory(inventory);
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        return this.player.openWorkbench(location, force);
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        return this.player.openEnchanting(location, force);
    }

    @Override
    public void openInventory(InventoryView inventory) {
        this.player.openInventory(inventory);
    }

    @Override
    public InventoryView openMerchant(Villager trader, boolean force) {
        return this.player.openMerchant(trader, force);
    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean force) {
        return this.player.openMerchant(merchant, force);
    }

    @Override
    public void closeInventory() {
        this.player.closeInventory();
    }

    @Override
    @Deprecated
    public ItemStack getItemInHand() {
        return this.player.getItemInHand();
    }

    @Override
    @Deprecated
    public void setItemInHand(ItemStack item) {
        this.player.setItemInHand(item);
    }

    @Override
    public ItemStack getItemOnCursor() {
        return this.player.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(ItemStack item) {
        this.player.setItemOnCursor(item);
    }

    @Override
    public boolean hasCooldown(Material material) {
        return this.player.hasCooldown(material);
    }

    public int getCooldown(Material material) {
        return this.player.getCooldown(material);
    }

    public void setCooldown(Material material, int ticks) {
        this.player.setCooldown(material, ticks);
    }

    @Override
    public boolean isSleeping() {
        return this.player.isSleeping();
    }

    @Override
    public int getSleepTicks() {
        return this.player.getSleepTicks();
    }

    @Override
    public GameMode getGameMode() {
        return this.player.getGameMode();
    }

    @Override
    public void setGameMode(GameMode mode) {
        this.player.setGameMode(mode);
    }

    @Override
    public boolean isBlocking() {
        return this.player.isBlocking();
    }

    @Override
    public boolean isHandRaised() {
        return this.player.isHandRaised();
    }

    @Override
    public int getExpToLevel() {
        return this.player.getExpToLevel();
    }

    @Override
    @Deprecated
    public Entity getShoulderEntityLeft() {
        return this.player.getShoulderEntityLeft();
    }

    @Override
    @Deprecated
    public void setShoulderEntityLeft(Entity entity) {
        this.player.setShoulderEntityLeft(entity);
    }

    @Override
    @Deprecated
    public Entity getShoulderEntityRight() {
        return this.player.getShoulderEntityRight();
    }

    @Override
    @Deprecated
    public void setShoulderEntityRight(Entity entity) {
        this.player.setShoulderEntityRight(entity);
    }

    @Override
    public double getEyeHeight() {
        return this.player.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return this.player.getEyeHeight(ignorePose);
    }

    @Override
    public Location getEyeLocation() {
        return this.player.getEyeLocation();
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return this.player.getLineOfSight(transparent, maxDistance);
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        return this.player.getTargetBlock(transparent, maxDistance);
    }

    public Block getTargetBlock(int maxDistance) {
        return this.player.getTargetBlock(Functions.getNotSolidBlocks(), maxDistance);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        return this.player.getLastTwoTargetBlocks(transparent, maxDistance);
    }

    @Override
    public int getRemainingAir() {
        return this.player.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int ticks) {
        this.player.setRemainingAir(ticks);
    }

    @Override
    public int getMaximumAir() {
        return this.player.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int ticks) {
        this.player.setMaximumAir(ticks);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return this.player.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        this.player.setMaximumNoDamageTicks(ticks);
    }

    @Override
    public double getLastDamage() {
        return this.player.getLastDamage();
    }

    @Override
    public void setLastDamage(double damage) {
        this.player.setLastDamage(damage);
    }

    @Override
    public int getNoDamageTicks() {
        return this.player.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        this.player.setNoDamageTicks(ticks);
    }

    @Override
    public Player getKiller() {
        return this.player.getKiller();
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return this.player.addPotionEffect(effect);
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        return this.player.addPotionEffect(effect);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        return this.player.addPotionEffects(effects);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return this.player.hasPotionEffect(type);
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        return this.player.getPotionEffect(type);
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        this.player.removePotionEffect(type);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return this.player.getActivePotionEffects();
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        return this.player.hasLineOfSight(other);
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return this.player.getRemoveWhenFarAway();
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        this.player.setRemoveWhenFarAway(remove);
    }

    @Override
    public EntityEquipment getEquipment() {
        return this.player.getEquipment();
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        this.player.setCanPickupItems(pickup);
    }

    @Override
    public boolean getCanPickupItems() {
        return this.player.getCanPickupItems();
    }

    @Override
    public boolean isLeashed() {
        return this.player.isLeashed();
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return this.player.getLeashHolder();
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        return this.player.setLeashHolder(holder);
    }

    @Override
    public boolean isGliding() {
        return this.player.isGliding();
    }

    @Override
    public void setGliding(boolean gliding) {
        this.player.setGliding(gliding);
    }

    @Override
    public void setAI(boolean ai) {
        this.player.setAI(ai);
    }

    @Override
    public boolean hasAI() {
        return this.player.hasAI();
    }

    @Override
    public void setCollidable(boolean collidable) {
        this.player.setCollidable(collidable);
    }

    @Override
    public boolean isCollidable() {
        return this.player.isCollidable();
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return this.player.getAttribute(attribute);
    }

    @Override
    public void damage(double amount) {
        this.player.damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        this.player.damage(amount, source);
    }

    @Override
    public double getHealth() {
        return this.player.getHealth();
    }

    @Override
    public void setHealth(double health) {
        this.player.setHealth(health);
    }

    @Override
    public double getMaxHealth() {
        return this.player.getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        this.player.setMaxHealth(health);
    }

    @Override
    @Deprecated
    public void resetMaxHealth() {
        this.player.resetMaxHealth();
    }

    @Override
    public String getCustomName() {
        return this.player.getCustomName();
    }

    @Override
    public void setCustomName(String name) {
        this.player.setCustomName(name);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.player.setMetadata(metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.player.getMetadata(metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.player.hasMetadata(metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.player.removeMetadata(metadataKey, owningPlugin);
    }

    @Override
    public boolean isPermissionSet(String name) {
        return this.player.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return this.player.isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return this.player.hasMetadata(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return this.player.hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return this.player.addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return this.player.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return this.player.addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return this.player.addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        this.player.removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        this.player.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return this.player.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return this.player.isOp();
    }

    @Override
    public void setOp(boolean value) {
        this.player.setOp(value);
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        this.player.sendPluginMessage(source, channel, message);
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return this.player.getListeningPluginChannels();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return this.player.launchProjectile(projectile);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        return this.player.launchProjectile(projectile, velocity);
    }

    public void sendPacket(Packet<?> packet) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.playerConnection.sendPacket(packet);
    }

    public void sendProtocolPacket(PacketContainer packet) {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        try {
            protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException ignored) {}
    }

    @Override
    public String toString() {
        return this.player.getName();
    }
}
