package me.maxiiiiii.skyblockdragons.player;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.inventory.Menu;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.abilities.Atomsplit_Katana;
import me.maxiiiiii.skyblockdragons.item.abilities.Rogue_Sword;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.item.pet.Pet;
import me.maxiiiiii.skyblockdragons.item.pet.PlayerPet;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBag;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.player.chat.ChatChannel;
import me.maxiiiiii.skyblockdragons.player.events.PlayerGetItemEvent;
import me.maxiiiiii.skyblockdragons.player.party.Party;
import me.maxiiiiii.skyblockdragons.player.skill.AbstractSkill;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import me.maxiiiiii.skyblockdragons.player.storage.EnderChest;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.util.objects.Multiplier;
import me.maxiiiiii.skyblockdragons.world.WorldSD;
import me.maxiiiiii.skyblockdragons.world.WorldType;
import me.maxiiiiii.skyblockdragons.world.warp.Warp;
import me.maxiiiiii.skyblockdragons.worlds.deepermines.forge.Forge;
import me.maxiiiiii.skyblockdragons.worlds.griffin.Griffin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class PlayerSD extends PlayerClass {
    public PlayerStats stats;

    private ScoreboardSD scoreboardSD;

    private ChatChannel chatChannel;

    private boolean tracked;
    private Logger logger;

    private Party party;

    public int playTime;
    public int bits;

    public Skill skill;
    public Wardrobe wardrobe;
    public BankAccount bank;
    public PlayerPet playerPet;
    public EnderChest enderChestSD;

    public Multiplier abilityCostMultiplier = new Multiplier(); // TODO: make items work with it

    public Forge forge;

    public Griffin griffin;

    public final Cooldown<PlayerSD> updateStatsCooldown = new Cooldown<>();

    private double lastCoins;

    private final List<Menu> menuHistory = new ArrayList<>();

    public static final double HEALTH_REGEN = 1.05;

    public PlayerSD(Player player) {
        super(player);
        this.update(player);

        this.stats = new PlayerStats(this,
                0,
                0,
                0,
                10,
                0,
                0,
                0,
                0,
                100,
                0,
                0,
                100,
                100,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
        );

        this.chatChannel = Variables.get(player.getUniqueId(), "ChatChannel", 0, ChatChannel.ALL);

        this.tracked = Variables.get(player.getUniqueId(), "Tracked", 0, true);
        setupLogger();

        this.party = null;

        this.playTime = Variables.get(player.getUniqueId(), "PlayTime", 0, 0);
        this.bits = Variables.get(player.getUniqueId(), "Bits", 0, 0);

        this.wardrobe = new Wardrobe(this);
        this.skill = new Skill(this);
        this.bank = new BankAccount(this, 50_000_000);
        this.playerPet = new PlayerPet(this);
        this.enderChestSD = new EnderChest(this);

        this.forge = new Forge(this);

        this.griffin = new Griffin(this);

        this.lastCoins = this.getCoins();

        this.scoreboardSD = new ScoreboardSD(this);

        this.equipment = new PlayerEquipment();

        SkyblockDragons.players.put(player.getUniqueId(), this);
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
        logger.info("Player Login: " + getName());
        logger.info("Display Player name: " + getDisplayName());
        logger.info("Player location: " + getLocation());
    }

    public void logLogout() {
        logger.info("Player Logout: " + getName());
        logger.info("Player location: " + getLocation());
    }

    public void update(Player player) {
        this.setPlayer(player);
        this.player.setHealthScale(40d);
    }

    public void save() {
        Variables.set(player.getUniqueId(), "PlayTime", 0, this.playTime);
        Variables.set(player.getUniqueId(), "Bits", 0, this.bits);

        Variables.set(player.getUniqueId(), "ChatChannel", 0, this.chatChannel);
        Variables.set(player.getUniqueId(), "Tracked", 0, this.tracked);

        this.wardrobe.save();
        this.skill.save();
        this.bank.save();
        this.playerPet.save();
        this.getItems().save();
        this.enderChestSD.save();

        this.forge.save();
    }

    public void giveSkill(SkillType skillType, double amount) {
        this.skill.get(skillType.name()).giveXp(amount);
        String message = ChatColor.DARK_AQUA + "+" + getInt(amount + "") + " " + skillType + " (" + Math.floor(this.getSkill().get(skillType).getCurrentXp() / this.getSkill().get(skillType).getCurrentNeedXp() * 1000d) / 10d + "%)";
        Functions.sendActionBar(this, message);
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

    public void setActivePet(int activePet) {
        this.playerPet.activePet = activePet;
//        Variables.set(player.getUniqueId(), "ActivePet", activePet);
    }

    public Pet getPetActive() {
        return this.playerPet.getCurrentPet();
    }

    public Pet getPet() {
        return this.getPetActive();
    }

    public int getBreakingPower() {
        ItemMaterial material = Items.get(Functions.getId(player.getEquipment().getItemInMainHand()));
        if (material instanceof MiningMaterial) {
            return ((MiningMaterial) material).getBreakingPower();
        }
        return 0;
    }

    public double getHealthStat() {
        return this.stats.getHealth().amount;
    }

    public void setHealthStat(double health) {
        this.stats.getHealth().amount = health;
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

    public void give(ItemStack item, Object source) {
        int amount = item.getAmount();

        if (amount > 64){
            throw new IllegalArgumentException("Amount must be less than 64");
        }
//        sendMessage("Give ITEMSD %s %s", item, source);

        if (item instanceof Drop) {
            ItemStack itemStack = ((Drop) item).generate(this, source);
            if (itemStack != null)
                this.player.getInventory().addItem(itemStack);
        } else {
            this.player.getInventory().addItem(item);
        }

        PlayerGetItemEvent event = new PlayerGetItemEvent(this, item);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void give(ItemStack item) {
        this.give(item, null);
    }

    public void addItemStat(Item item) {
        this.stats.add(item.getStats());
    }

    public void applyStats(boolean manaRegan) {
        PlayerEquipment equipment = getPlayerItems();

        if (this.getWorldSD().isType(WorldType.MINING)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, -1, false, false), true);
        } else {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

        this.stats.reset();
        this.stats.getHealth().set(500); // no other way to have more base health so for now it will be that

        // ALPHA BASE SKILLS
        if (getSkill().get(SkillType.COMBAT).getLevel() < 15){
            getSkill().get(SkillType.COMBAT).setLevel(15);
        }
        if (getSkill().get(SkillType.ENCHANTING).getLevel() < 40){
            getSkill().get(SkillType.ENCHANTING).setLevel(40);
        }

        for (AbstractSkill skill : this.getSkill()) {
            this.stats.add(skill.getRewards().getStat(), skill.getRewards().getStatAmount() * skill.getLevel());
        }
        this.stats.add(StatType.MINING_FORTUNE, this.getSkill().getMiningSkill().getLevel() * 4);
        this.stats.add(StatType.FARMING_FORTUNE, this.getSkill().getFarmingSkill().getLevel() * 4);
        this.stats.add(StatType.FORAGING_FORTUNE, this.getSkill().getForagingSkill().getLevel() * 4);

        if (getEnchantLevel(EnchantType.RESPIRATION) > 0)
            player.setMaximumAir((getEnchantLevel(EnchantType.RESPIRATION) * 200) + 200);

        for (Item item : equipment) {
            this.addItemStat(item);
        }

        // TODO: change it to be automatically
//        if (helmetMaterial == Items.get("ENDER_HELMET"))
//            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
//        if (helmetMaterial == Items.get("ENDER_CHESTPLATE"))
//            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
//        if (helmetMaterial == Items.get("ENDER_LEGGINGS"))
//            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
//        if (helmetMaterial == Items.get("ENDER_BOOTS"))
//            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
//
//        if (helmetMaterial == Items.get("ENDER_GUARD_HELMET"))
//            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
//        if (helmetMaterial == Items.get("ENDER_GUARD_CHESTPLATE"))
//            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
//        if (helmetMaterial == Items.get("ENDER_GUARD_LEGGINGS"))
//            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
//        if (helmetMaterial == Items.get("ENDER_GuARD_BOOTS"))
//            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);


        if (System.currentTimeMillis() - Atomsplit_Katana.atomsplitAbility.getOrDefault(player, 0L) <= 4000) {
            this.stats.getFerocity().amount += 400;
        }

        // TODO: change it to be automatically
//        if (Functions.getId(tool).equals("TERMINATOR")) {
//            this.stats.critChance.amount = this.stats.critChance.amount / 4;
//        }

        if (System.currentTimeMillis() - Rogue_Sword.rogueSwordLastTimeUsed.getOrDefault(this.player, 0L) <= 30000) {
            this.stats.getSpeed().amount += (Rogue_Sword.rogueSwordAmountUsed.get(this.player) + 1) * 10;
        }


        // TODO: change it to be automatically
        // Full Set
//        if (fullSet.equals("Old Blood")) {
//            statsMultiplayer.increase(StatType.HEALTH, 20);
//        }
//        if (fullSet.equals("Protector Blood") && player.getHealth() >= player.getMaxHealth() / 2) {
//            statsMultiplayer.increase(StatType.DEFENSE, 30);
//        }
//        if (fullSet.equals("Young Blood") && player.getHealth() >= player.getMaxHealth() / 2) {
//            this.stats.speed.amount += 70;
//        }
//        if (fullSet.equals("Superior Blood")) {
//            statsMultiplayer.increase(0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 0);
//        }


        // Pets
        if (this.getPlayerPet().getActivePet() >= 0) {
            if (this.getPetActive().getPetMaterial() == PetMaterial.get("ENDER_DRAGON")) {
                double amount = this.getPetActive().getLevel() * 0.1;
                stats.getMultiplayer().increase(0, amount, amount, amount, amount, 0, amount, amount, amount, amount, amount, amount, amount, amount, amount, amount, amount, amount, 0);
            }
        }

        stats.applyMultipliers();
        abilityCostMultiplier.reset();

        if (manaRegan) {
            if (this.stats.mana.amount < this.stats.getIntelligence().amount) {
                this.stats.mana.amount += this.stats.getIntelligence().amount / 50;
            }
        }
        if (this.stats.mana.amount > this.stats.getIntelligence().amount) {
            this.stats.mana.amount = this.stats.getIntelligence().amount;
        }

        this.stats.normalize();

        if (this.getMaxHealth() != this.getHealthStat()) {
            this.setMaxHealth(this.getHealthStat());
        }

        if (this.getHealth() * HEALTH_REGEN < this.getMaxHealth()) {
            this.setHealth(this.getHealth() * HEALTH_REGEN);
        } else if (this.getHealth() * HEALTH_REGEN > this.getMaxHealth()) {
            this.setHealth(this.getHealth());
        }

        this.setWalkSpeed((float) (this.stats.getSpeed().amount / 500));

        Functions.sendActionBar(this);
    }

    public boolean manaCost(int manaCost, ItemStack item, String abilityName) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        int cost = Functions.manaCostCalculator(manaCost, this);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
            Functions.sendActionBar(this, abilityName + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }

    public boolean manaCost(int manaCost, ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        int cost = Functions.manaCostCalculator(manaCost, this);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
            Functions.sendActionBar(this, ((WeaponMaterial) Functions.getItemMaterial(item)).getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        return true;
    }

    public boolean manaCost(ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        ToolMaterial material = (ToolMaterial) Functions.getItemMaterial(item);
        int cost = manaCostCalculator(material.getAbilities().get(i).isPlayerHasEnoughMana(), this);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
            Functions.sendActionBar(this, material.getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }

    public double getAbilityCost(double baseCost) {
        return this.abilityCostMultiplier.multiply(baseCost);
    }

    public boolean hasEnoughToUseAbility(double baseManaCost) {
        return this.getStats().getMana().get() >= this.getAbilityCost(baseManaCost);
    }

    public short getEnchantLevel(EnchantType enchant) {
        if (skill.getEnchantingSkill().getLevel() < enchant.getRequirement().getLevel() && this.getGameMode() != GameMode.CREATIVE)
            return 0;
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public short getEnchantLevel(EnchantType enchant, Condition condition) {
        if (skill.getEnchantingSkill().getLevel() < enchant.getRequirement().getLevel() && this.getGameMode() != GameMode.CREATIVE)
            return 0;
        if (!condition.check())
            return 0;
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public void updatePlayerInventory() {
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            if (i > 39) continue;
            ItemStack itemStack = player.getInventory().getContents()[i];

            if (!isNotAir(itemStack)) continue;

            ItemMaterial itemMaterial = Items.get(itemStack);
            if (itemMaterial == Items.get("NULL") || Functions.nbtHasKey(itemStack, "NOTSD")) {
                return;
            }

            Item item = new Item(this, itemMaterial, itemStack);
            copyNBTStack(item, itemStack);
            if (!item.isSimilar(itemStack) && !getId(itemStack).contains("_PET") && !Functions.getId(item).equals("SKYBLOCK_MENU")) {
                player.getInventory().setItem(i, item);
            }
        }
        setSkyblockMenu();
    }

    public void setSkyblockMenu() {
        ItemMaterial hand = Items.get(getEquipment().getItemInMainHand());
        if (hand instanceof BowMaterial){
            Item menu = new Item(Items.get("SKYBLOCK_MENU_ARROW"), 64);
            menuLores(menu);
            player.getInventory().setItem(8, menu);
        }
        else if (!Functions.isNotAir(player.getInventory().getItem(8)) || !Functions.getId(player.getInventory().getItem(8)).equals("SKYBLOCK_MENU")) {
            Item menu = new Item(Items.get("SKYBLOCK_MENU"), 1);
            menuLores(menu);
            player.getInventory().setItem(8, menu);
        }
    }

    private void menuLores(Item menu) {
        ItemMeta menuMeta = menu.getItemMeta();
        List<String> lores = menuMeta.getLore();
        lores.remove(lores.size() - 1);
        lores.add(ChatColor.YELLOW + "Click to open!");
        menuMeta.setLore(lores);
        menu.setItemMeta(menuMeta);
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

    public boolean chanceOf(double percent, Object... other) {
        double multiplier = 1;
        multiplier += this.getStats().getMagicFind().amount / 100;
        return Functions.chanceOf(percent * multiplier);
    }

    public void makeDamage(Entity entity, Damage.DamageType damageType, double damage, double baseAbilityDamage, double abilityScaling) {
        EntityDamageEntityEvent event = new EntityDamageEntityEvent(this, entity, damageType, damage, false, baseAbilityDamage, abilityScaling);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void makeDamage(Entity entity, Damage.DamageType damageType, double damage) {
        makeDamage(entity, damageType, damage, 0, 0);
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

    @Override
    public void giveExp(int amount) {
        amount *= 1 + (this.getSkill().getEnchantingSkill().getLevel() * 4 / 100);
        super.giveExp(amount);
    }

    public PlayerEquipment getPlayerItems() {
        return (PlayerEquipment) this.getItems();
    }

    @Getter
    public class PlayerEquipment extends Equipment {
        private AccessoryBag accessoryBag;
        private Pet pet;

        @Override
        public void save() {
            this.accessoryBag.save();
        }

        @Override
        public void update() {
            super.update();
            this.accessoryBag = new AccessoryBag(PlayerSD.this);
            this.pet = PlayerSD.this.getPlayerPet().getCurrentPet();
        }

        @Override
        public List<Item> toList() {
            List<Item> list = super.toList();
            list.addAll(this.accessoryBag.getItems());
            list.add(this.pet);
            return list;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Entity)) {
            return false;
        }
        return this.getUniqueId().equals(((Entity) other).getUniqueId());
    }
}
