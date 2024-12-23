package me.maxiiiiii.skyblockdragons.player;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.events.EntityDamageEvent;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamage.EntityDamage;
import me.maxiiiiii.skyblockdragons.damage.types.entitydamageentity.EntityDamageEntity;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.Equipment;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemRequirementAble;
import me.maxiiiiii.skyblockdragons.item.material.materials.nfa.powerorbs.PowerOrbDeployAbility;
import me.maxiiiiii.skyblockdragons.item.material.types.BowMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.MiningMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.item.pet.PlayerPet;
import me.maxiiiiii.skyblockdragons.item.stats.StatTypes;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateStatsEvent;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBag;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import me.maxiiiiii.skyblockdragons.player.events.PlayerDeathEvent;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetItemEvent;
import me.maxiiiiii.skyblockdragons.player.events.PlayerRegainHealthEvent;
import me.maxiiiiii.skyblockdragons.player.objects.ActionBarSupplier;
import me.maxiiiiii.skyblockdragons.player.party.Party;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.skill.Skills;
import me.maxiiiiii.skyblockdragons.player.skill.events.PlayerGetSkillXpEvent;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.player.storage.EnderChest;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.objects.Queue;
import me.maxiiiiii.skyblockdragons.util.objects.SignMenuFactory;
import me.maxiiiiii.skyblockdragons.util.objects.cooldowns.Cooldown;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.world.worlds.deepermines.forge.Forge;
import me.maxiiiiii.skyblockdragons.world.worlds.griffin.Griffin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static me.maxiiiiii.skyblockdragons.util.Functions.cooldown;

@Getter
@Setter
public class PlayerSD extends PlayerClass implements ConfigurationSerializable {
    public PlayerStats stats;

    private ScoreboardSD scoreboardSD;

    private ChatChannel chatChannel;

    private boolean tracked;
    private Logger logger;

    private Party party;

    public int playTime;
    public int bits;

    public Skills skills;
    public Wardrobe wardrobe;
    public BankAccount bank;
    public PlayerPet playerPet;
    public EnderChest enderChestSD;

    public Forge forge;

    public Griffin griffin;

    private PowerOrbDeployAbility.PowerOrbType activePowerOrb = null;

    private double lastCoins;

    private final List<Menu> menuHistory = new ArrayList<>();

    private final Cooldown<EntitySD> hitTick = new Cooldown<>();

    private final Queue<ActionBarSupplier> actionBarQueue = new Queue<>();

    public static final double HEALTH_REGEN = 0.05;

    public PlayerSD(Player player) {
        super(player);
        System.out.println("player " + player);
        this.update(player);
        SkyblockDragons.players.put(player.getUniqueId(), this);

        this.stats = new PlayerStats(this);

        this.skills = new Skills(this);
        this.wardrobe = new Wardrobe(this);
        this.bank = new BankAccount(this, 50_000_000);
        this.playerPet = new PlayerPet(this);
        this.enderChestSD = new EnderChest(this);

        this.chatChannel = ChatChannel.valueOf(Variables.getString(player.getUniqueId(), "ChatChannel", "ALL"));

        this.tracked = Variables.getBoolean(player.getUniqueId(), "Tracked", true);
//        setupLogger();

        this.party = null;

        this.playTime = Variables.getInt(player.getUniqueId(), "PlayTime", 0);
        this.bits = Variables.getInt(player.getUniqueId(), "Bits", 0);

        this.forge = new Forge(this);

        this.griffin = new Griffin(this);

        this.lastCoins = this.getCoins();

        this.scoreboardSD = new ScoreboardSD(this);
        this.equipment = new PlayerEquipment();
    }

    private void setupLogger() {
        this.logger = Logger.getLogger("PlayerLogger");
        FileHandler fh;
        try {

            // This block configures the logger with handler and formatter
            fh = new FileHandler(SkyblockDragons.plugin.getDataFolder().getAbsoluteFile() + "/PlayersLogs/" + getUniqueId() + ".log", 0,1, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // don't log to console
            logger.setUseParentHandlers(false);
            // the following statement is used to log any messages
            logger.info("Init Logger: " + getUniqueId());
            logLogin();

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public void logLogin() {
        logger.info("Player Login: " + player.getName());
        logger.info("Player location: " + player.getLocation());
    }

    public void logLogout() {
        logger.info("Player Logout: " + player.getName());
        logger.info("Player location: " + player.getLocation());
    }

    public void update(Player player) {
        this.setPlayer(player);
        this.player.setHealthScale(40d);
    }

    public void save() {
        Variables.set(player.getUniqueId(), "PlayTime", this.playTime);
        Variables.set(player.getUniqueId(), "Bits", this.bits);

        Variables.set(player.getUniqueId(), "ChatChannel", this.chatChannel.name());
        Variables.set(player.getUniqueId(), "Tracked", this.tracked);

        this.wardrobe.save();
        this.skills.save();
        this.bank.save();
        this.playerPet.save();
        this.getItems().save();
        this.enderChestSD.save();

        this.forge.save();
    }

    public void setActivePowerOrb(PowerOrbDeployAbility.PowerOrbType activePowerOrb) {
        this.activePowerOrb = activePowerOrb;
    }

    public PowerOrbDeployAbility.PowerOrbType getActivePowerOrb() {
        return activePowerOrb;
    }

    public void giveSkill(SkillType skillType, double amount) {
        Bukkit.getPluginManager().callEvent(new PlayerGetSkillXpEvent(this, skillType, amount));
    }

    public void addPlayTime(int amount) {
        this.playTime += amount;
    }

    public void addBits(int amount) {
        this.bits += amount;
    }

    public void setBits(int amount) {
        this.bits = amount;
    }

    public void setActivePet(int activePetSlot) {
        this.playerPet.setActivePetSlot(activePetSlot);
    }

    public Item getActivePet() {
        if (this.playerPet == null) return null;

        return this.playerPet.getActivePet();
    }

    public PetMaterial getActivePetMaterial() {
        if (this.getActivePet() != null && this.getActivePet().getMaterial() instanceof PetMaterial) {
            return (PetMaterial) this.getActivePet().getMaterial();
        }
        return PetMaterial.NULL;
    }

    public Item getPet() {
        return this.getActivePet();
    }

    public int getBreakingPower() {
        ItemMaterial material = Items.get(Functions.getId(player.getEquipment().getItemInMainHand()));
        if (material instanceof MiningMaterial) {
            return ((MiningMaterial) material).getBreakingPower();
        }
        return 0;
    }

    @Override
    public double getMaxHealth() {
        return this.stats.getHealth().get();
    }

    public double getPurse() {
        return SkyblockDragons.economy.getBalance(this);
    }

    public void giveCoins(double amount) {
        amount = Math.floor(amount * 10d)/ 10d;
        this.lastCoins = this.getCoins();
        SkyblockDragons.economy.depositPlayer(this, amount);
    }

    public void removeCoins(double amount) {
        amount = Math.floor(amount * 10d)/ 10d;
        this.lastCoins = this.getCoins();
        SkyblockDragons.economy.withdrawPlayer(this, amount);
    }

    public void setCoins(double amount) {
        amount = Math.floor(amount * 10d)/ 10d;
        SkyblockDragons.economy.withdrawPlayer(player, SkyblockDragons.economy.getBalance(this.player));
        SkyblockDragons.economy.depositPlayer(player, amount);
    }

    public double getCoins() {
        return SkyblockDragons.economy.getBalance(this);
    }

    public double getPersonalBank() {
        return this.bank.personal;
    }

    public double getCoopBank() {
        return this.bank.coop;
    }

    public double getBankBalance(BankAccount.Type type) {
        if (type == BankAccount.Type.COOP)
            return this.getCoopBank();
        return this.getPersonalBank();
    }

    public void give(ItemStack item) {
        if (item == null) return;

        int amount = item.getAmount();

        if (amount > 64){
            throw new IllegalArgumentException("Amount must be less than 64");
        }

        this.player.getInventory().addItem(item);

        PlayerGetItemEvent event = new PlayerGetItemEvent(this, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void applyStats(boolean ManaRegain) {
        this.equipment.update();
        PlayerEquipment equipment = this.getItems();

        if (this.getWorldSD().isType(WorldType.MINING)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, -1, false, false), true);
        } else {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

        this.stats.reset();

        for (AbstractSkill skill : this.getSkills()) {
            this.stats.add(skill.getRewards().getStat(), skill.getRewards().getStatAmount() * skill.getLevel());
        }
        this.stats.add(StatTypes.MINING_FORTUNE, this.getSkills().getMiningSkill().getLevel() * 4);
        this.stats.add(StatTypes.FARMING_FORTUNE, this.getSkills().getFarmingSkill().getLevel() * 4);
        this.stats.add(StatTypes.FORAGING_FORTUNE, this.getSkills().getForagingSkill().getLevel() * 4);

        for (Item item : equipment) {
            if (item.getMaterial().name().equals("NULL") ||
                    (item.getMaterial() instanceof ItemRequirementAble &&
                            !((ItemRequirementAble) item.getMaterial()).getRequirements().hasRequirements(this)))
                continue;

            stats.add(item.getStats());
        }
        stats.add(equipment.getAccessoryBag().getStats());

        UpdateStatsEvent event = new UpdateStatsEvent(stats);
        Bukkit.getPluginManager().callEvent(event);

        stats.update();

        if (getEnchantLevel(EnchantType.RESPIRATION) > 0)
            player.setMaximumAir((getEnchantLevel(EnchantType.RESPIRATION) * 200) + 200);

        if (ManaRegain && this.stats.getMana().get() < this.stats.getIntelligence().get()) {
            this.stats.getMana().add(this.stats.getIntelligence().get() / 50);
        }
        if (this.stats.getMana().get() > this.stats.getIntelligence().get()) {
            this.stats.getMana().set(this.stats.getIntelligence().get());
        }

        this.stats.normalize(this);

        if (this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() != this.getMaxHealth()) {
            this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().clear();
            this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.getMaxHealth());
        }

        PlayerRegainHealthEvent regainHealthEvent = new PlayerRegainHealthEvent(this);
        Bukkit.getPluginManager().callEvent(regainHealthEvent);

        this.setWalkSpeed((float) Math.min((this.stats.getSpeed().get() / 500), 500));
    }

    public void sendActionBar() {
        ActionBarSupplier defense = new ActionBarSupplier(ChatColor.GREEN.toString() + this.getStats().getDefense().get() + StatTypes.DEFENSE.getIcon(), 0);

        ActionBarSupplier actionBar = this.actionBarQueue.getOrDefault(defense);
        if (actionBar != null && SkyblockDragons.getCurrentTimeInSeconds() - actionBar.getStartedAt() > actionBar.getDuration()) {
            this.actionBarQueue.remove();
            actionBar = this.actionBarQueue.getOrDefault(defense);
        }

        this.sendActionBar(ChatColor.RED.toString() + (int) this.getHealth() + "/" + (int) this.getMaxHealth() + " " +
                actionBar + " " +
                ChatColor.AQUA + (int) this.getStats().getMana().get() + "/" + (int) this.getStats().getIntelligence().get()
        );
    }

    public void addActionBar(String text, double duration) {
        this.actionBarQueue.add(new ActionBarSupplier(text, duration));
    }

    @Override
    public void kill() {
        Bukkit.getPluginManager().callEvent(new PlayerDeathEvent(this));
    }

    public short getEnchantLevel(EnchantType enchant) {
        if (skills.getEnchantingSkill().getLevel() < enchant.getRequirements().getRequirement(0).getLevel() && this.getGameMode() != GameMode.CREATIVE)
            return 0;
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public short getEnchantLevel(EnchantType enchant, Condition condition) {
        if (skills.getEnchantingSkill().getLevel() < enchant.getRequirements().getRequirement(0).getLevel() && this.getGameMode() != GameMode.CREATIVE)
            return 0;
        if (!condition.check())
            return 0;
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public void updatePlayerInventory() {
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            if (i > 39) continue;
            ItemStack itemStack = player.getInventory().getContents()[i];

            if (!Functions.isNotAir(itemStack)) continue;

            ItemMaterial itemMaterial = Items.get(itemStack);
            if (itemMaterial == Items.get("NULL") || Functions.nbtHasKey(itemStack, "NOTSD")) {
                return;
            }

            Item item = new Item(this, itemMaterial, itemStack);
            Functions.copyNBTStack(item, itemStack);
            if (!item.isSimilar(itemStack) && !Functions.getId(itemStack).contains("_PET") && !Functions.getId(item).equals("SKYBLOCK_MENU")) {
                player.getInventory().setItem(i, item);
            }
        }
        setSkyblockMenu();
    }

    public void setSkyblockMenu() {
        ItemMaterial hand = Items.get(getEquipment().getItemInMainHand());
        if (hand instanceof BowMaterial){
            player.getInventory().setItem(8, Item.SKYBLOCK_MENU_ARROW);
        }
        else if (!Functions.isNotAir(player.getInventory().getItem(8)) || !Functions.getId(player.getInventory().getItem(8)).equals("SKYBLOCK_MENU")) {
            player.getInventory().setItem(8, Item.SKYBLOCK_MENU);
        }
    }

    public void sendActionBar(String message, boolean ignoreCooldown) {
        if (cooldown(player, actionBarCooldown, 500, false) && !ignoreCooldown) return;

        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void sendActionBar(String message) {
        this.sendActionBar(message, false);
    }

    public Creature getTargetEntity(int maxDistance) {
        Location location = player.getLocation();

        for (int i = 0; i <= maxDistance; i++) {
            Location loc = location.clone().add(location.clone().getDirection().multiply(i));

            for (Entity entity : Functions.loopEntities(loc, 1.5)) {
                if (entity instanceof Creature) {
                    return (Creature) entity;
                }
            }

            if (loc.getBlock().getType() != Material.AIR) break;
        }
        return null;
    }

    public void openSign(String line1, String line2, String line3, String line4, Consumer<String[]> response) {
        SignMenuFactory.Menu menu = SkyblockDragons.signMenuFactory.newMenu(Arrays.asList(line1, line2, line3, line4))
                .reopenIfFail(false)
                .response((player, strings) -> response.accept(strings));
        menu.open(player);
    }

    public void openSign(String line, Consumer<String[]> response) {
        this.openSign("", "", "---------------", line, response);
    }

    public Map<ItemMaterial, Integer> getAllItems() {
        Map<ItemMaterial, Integer> items = new HashMap<>();
        for (ItemStack item : this.getInventory().getContents()) {
            if (item == null) continue;
            items.put(Items.get(item), items.getOrDefault(Items.get(item), 0) + item.getAmount());
        }
        return items;
    }

    public boolean hasItem(ItemMaterial material, int amount) {
        Map<ItemMaterial, Integer> inventory = this.getAllItems();
        return inventory.getOrDefault(material, 0) >= amount;
    }

    public Item getItem(ItemMaterial material, int amount) {
        if (!this.hasItem(material, amount)) return null;

        for (ItemStack item : this.getInventory().getContents()) {
            if (item == null) continue;

            if (item.getAmount() >= amount && Items.get(item) == material) {
                return new Item(this, item);
            }
        }
        return null;
    }

    public ItemStack getItemStack(ItemMaterial material, int amount) {
        if (!this.hasItem(material, amount)) return null;

        for (ItemStack item : this.getInventory().getContents()) {
            if (item == null) continue;

            if (item.getAmount() >= amount && Items.get(item) == material) {
                return item;
            }
        }
        return null;
    }

    public void removeItems(ItemMaterial material, int amount) {
        for (int i = 0; i < 36; i++) {
            ItemStack item = this.getInventory().getItem(i);

            if (item == null) continue;

            if (Functions.getId(item).equals(material.name())) {
                if (item.getAmount() > amount) {
                    item.setAmount(item.getAmount() - amount);
                    this.getInventory().setItem(i, item);
                    break;
                } else {
                    amount -= item.getAmount();
                    this.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }
        }
    }

    public void removeItems(Map<ItemMaterial, Integer> items) {
        for (ItemMaterial material : items.keySet()) {
            this.removeItems(material, items.get(material));
        }
    }

    public boolean ignoreItemRequirements() {
        return this.getGameMode() == GameMode.CREATIVE;
    }

    public void sendNoRequirementsMessage(String whatToUse) {
        this.sendMessage(ChatColor.RED + "You don't have the requirements to use this " + whatToUse + "!");
    }

    public void damage(EntityDamage damage) {
        if (damage instanceof EntityDamageEntity) {
            ((EntityDamageEntity) damage).setAttacker(this);
            ((EntityDamageEntity) damage).setAttackerEquipment(this.getItems());
        } else {
            damage.setVictim(this);
        }
        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damage));
    }

    public void makePlayerSay(String message) {
        SkyblockDragons.logger.info(String.format("%s [%s]: %s", getDisplayName(), getChatChannel(), message));
        this.chatChannel.send(this, message);
    }

    public WorldSD getWorldSD() {
        return WorldSD.get(player.getWorld());
    }

    public void warp(Warp warp) {
        warp.warp(this);
    }

    public List<EntitySD> getEntities(double radius) {
        return Functions.loopEntities(this.getLocation(), radius);
    }

    /**
     * @return the hit tik with the attack speed calculations in milliseconds
     */
    public int getHitTick() {
        return (int) (500 - (500 * (this.stats.getAttackSpeed().get() / (this.stats.getAttackSpeed().get() + 100))));
    }

    public boolean isOnHitTick(EntitySD victim) {
        return Functions.cooldown(victim, this.hitTick, this.getHitTick(), false);
    }

    public void heal(double amount) {
        this.setHealth(Math.min(this.getHealth() + amount, this.getMaxHealth()));
    }

    @Override
    public PlayerEquipment getItems() {
        return (PlayerEquipment) super.getItems();
    }

    @Getter
    public class PlayerEquipment extends Equipment implements Cloneable {
        private final AccessoryBag accessoryBag;
        private Item pet;

        public PlayerEquipment() {
            super(PlayerSD.this);
            this.accessoryBag = new AccessoryBag(PlayerSD.this);
        }

        public void setAccessoryBag(List<Item> items) {
            accessoryBag.setItems(items);
        }

        public PetMaterial getPetMaterial() {
            return (PetMaterial) pet.getMaterial();
        }

        @Override
        public void save() {
            super.save();
            this.accessoryBag.save();
        }

        @Override
        public void update() {
            super.update();
            this.pet = PlayerSD.this.getPlayerPet().getActivePet();
        }

        @Override
        public List<Item> toList() {
            List<Item> list = super.toList();
            list.addAll(this.accessoryBag.getItems());
            if (this.pet != null) list.add(this.pet);
            return list;
        }

        @Override
        public PlayerEquipment clone() {
            try {
                return (PlayerEquipment) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Entity)) {
            return false;
        }
        return this.getUniqueId().equals(((Entity) other).getUniqueId());
    }

    @Override
    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();
        result.put("UUID", this.getUniqueId().toString());
        return result;
    }

    public static PlayerSD deserialize(Map<String, Object> args) {
        if (!args.containsKey("UUID") || args.get("UUID") == null)
            return null;

        return SkyblockDragons.getPlayer(UUID.fromString((String) args.get("UUID")));
    }
}