package me.maxiiiiii.skyblockdragons.player;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.material.*;
import me.maxiiiiii.skyblockdragons.player.coop.Coop;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.abilities.Atomsplit_Katana;
import me.maxiiiiii.skyblockdragons.item.abilities.Rogue_Sword;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.player.pet.Pet;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeSlot;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.worlds.mining.Mining;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.*;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class PlayerSD extends PlayerClass {
    private final Player player;
    public Coop coop;

    public double damage;
    public double strength;
    public double critDamage;
    public double critChance;
    public double abilityDamage;
    public double baseAbilityDamage;
    public double abilityScaling;
    public double attackSpeed;
    public double ferocity;
    public double health;
    public double defense;
    public double trueDefense;
    public double speed;
    public double mana;
    public double intelligence;
    public double magicFind;
    public double petLuck;
    public double miningSpeed;
    public double miningFortune;
    public double seaCreatureChance;
    public double absorption;

    public int playTime;
    public int bits;

    public Skill skill;
    public Wardrobe wardrobe;

    public BankAccount bank;

    public int activePet;
    public ArrayList<Pet> pets;
    public Pet.ArmorStand petArmorStand;
    public boolean hidePets;

    public List<ItemStack> accessoryBag;

    public final Cooldown updateStatsCooldown = new Cooldown();

    public static final double HEALTH_REGEN = 1.02;

    public PlayerSD(Player player) {
        super(player);

        this.player = player;
        this.damage = 0;
        this.strength = 0;
        this.critDamage = 0;
        this.critChance = 20;
        this.abilityDamage = 0;
        this.baseAbilityDamage = 0;
        this.abilityScaling = 0;
        this.attackSpeed = 0;
        this.ferocity = 0;
        this.health = 100;
        this.defense = 0;
        this.trueDefense = 0;
        this.speed = 100;
        this.intelligence = 100;
        this.mana = intelligence;
        this.magicFind = 0;
        this.petLuck = 0;
        this.miningSpeed = 0;
        this.miningFortune = 0;
        this.seaCreatureChance = 0;
        this.absorption = 0;

        this.playTime = Variables.get(player.getUniqueId(), "PlayTime", 0);
        this.bits = Variables.get(player.getUniqueId(), "Bits", 0);

        ArrayList<WardrobeSlot> wardrobeSlots = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            wardrobeSlots.add(new WardrobeSlot(
                    i,
                    Variables.get(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 0), null),
                    Variables.get(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 1), null),
                    Variables.get(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 2), null),
                    Variables.get(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 3), null)
            ));
        }
        this.wardrobe = new Wardrobe(wardrobeSlots);
        this.skill = new Skill(
                this,
                new FarmingSkill(Variables.get(player.getUniqueId(), "Farming", 1, 0), Variables.get(player.getUniqueId(), "Farming", 2, 0d)),
                new MiningSkill(Variables.get(player.getUniqueId(), "Mining", 1, 0), Variables.get(player.getUniqueId(), "Mining", 2, 0d)),
                new CombatSkill(Variables.get(player.getUniqueId(), "Combat", 1, 0), Variables.get(player.getUniqueId(), "Combat", 2, 0d)),
                new ForagingSkill(Variables.get(player.getUniqueId(), "Foraging", 1, 0), Variables.get(player.getUniqueId(), "Foraging", 2, 0d)),
                new FishingSkill(Variables.get(player.getUniqueId(), "Fishing", 1, 0), Variables.get(player.getUniqueId(), "Fishing", 2, 0d)),
                new EnchantingSkill(Variables.get(player.getUniqueId(), "Enchanting", 1, 0), Variables.get(player.getUniqueId(), "Enchanting", 2, 0d)),
                new AlchemySkill(Variables.get(player.getUniqueId(), "Alchemy", 1, 0), Variables.get(player.getUniqueId(), "Alchemy", 2, 0d)),
                new TamingSkill(Variables.get(player.getUniqueId(), "Taming", 1, 0), Variables.get(player.getUniqueId(), "Taming", 2, 0d)),
                new DungeoneeringSkill(Variables.get(player.getUniqueId(), "Dungeoneering", 1, 0), Variables.get(player.getUniqueId(), "Dungeoneering", 2, 0d)
                ));

        this.bank = new BankAccount(this, Variables.get(player.getUniqueId(), "BankPersonal", 0d), Variables.get(player.getUniqueId(), "BankCoop", 0d));

        this.pets = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            ItemStack pet = Variables.get(player.getUniqueId(), "Pets", i, null);
            if (pet != null)
                this.pets.add(Pet.getPet(pet, false));
        }

        this.activePet = Variables.get(player.getUniqueId(), "ActivePet", -1);
        if (this.activePet < 0)
            this.petArmorStand = null;
        else
            this.petArmorStand = new Pet.ArmorStand(this, this.getPetActive(), Pet.spawnPet(this, this.getPetActive()), this.activePet);

        this.hidePets = Variables.get(player.getUniqueId(), "HidePets", 0) == 1;

        this.accessoryBag = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            ItemStack accessory = Variables.get(player.getUniqueId(), "AccessoryBag", i, null);
            if (accessory != null)
                this.accessoryBag.add(new Item(accessory));
        }
    }

    public void sendPacket(Packet<?> packet) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.playerConnection.sendPacket(packet);
    }

    public void addPlayTime(int amount) {
        this.playTime += amount;
        Variables.set(player.getUniqueId(), "PlayTime", this.playTime);
    }

    public void addBits(int amount) {
        this.bits += amount;
        Variables.set(player.getUniqueId(), "Bits", this.bits);
    }

    public void setBits(int amount) {
        this.bits = amount;
        Variables.set(player.getUniqueId(), "Bits", this.bits);
    }

    public void setActivePet(int activePet) {
        this.activePet = activePet;
        Variables.set(player.getUniqueId(), "ActivePet", activePet);
    }

    public Pet getPetActive() {
        return this.pets.get(this.activePet);
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
        return this.health;
    }

    public void setHealthStat(double health) {
        this.health = health;
    }

    public double getPurse() {
        return SkyblockDragons.economy.getBalance(this);
    }

    public void addBalance(double amount) {
        SkyblockDragons.economy.depositPlayer(this, amount);
    }

    public void removeBalance(double amount) {
        SkyblockDragons.economy.withdrawPlayer(this, amount);
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
        if (item instanceof ItemDrop) {
            ItemStack itemStack = ((ItemDrop) item).generate();
            if (itemStack != null)
                this.player.getInventory().addItem(itemStack);
        } else {
            this.player.getInventory().addItem(item);
        }
    }

    public void increasePlayerStat(double damage, double strength, double critDamage, double critChance, double abilityDamage, double abilityScaling, double attackSpeed, double ferocity, double health, double defense, double trueDefense, double speed, double intelligence, double magicFind, double petLuck, double miningSpeed, double miningFortune, double seaCreatureChance, double absorption) {
        this.damage *= 1 + damage / 100;
        this.strength *= 1 + strength / 100;
        this.critDamage *= 1 + critDamage / 100;
        this.critChance *= 1 + critChance / 100;
        this.abilityDamage *= 1 + abilityDamage / 100;
        this.abilityScaling *= 1 + abilityScaling / 100;
        if (this.attackSpeed * (attackSpeed / 100) > 100) {
            this.attackSpeed = 100;
        } else {
            this.attackSpeed *= 1 + attackSpeed / 100;
        }
        this.ferocity *= 1 + ferocity / 100;
        this.health *= 1 + health / 100;
        this.defense *= 1 + defense / 100;
        this.trueDefense *= 1 + trueDefense / 100;
        if (this.speed * (speed / 100) > 500) {
            this.speed = 500;
        } else {
            this.speed *= 1 + speed / 100;
        }
        this.intelligence *= 1 + intelligence / 100;
        this.magicFind *= 1 + magicFind / 100;
        this.petLuck *= 1 + petLuck / 100;
        this.miningSpeed *= 1 + miningSpeed / 100;
        this.miningFortune *= 1 + miningFortune / 100;
        this.seaCreatureChance *= 1 + seaCreatureChance / 100;
        this.absorption *= 1 + absorption / 100;
    }

    public void addPlayerStat(List<Double> num) {
        try {
            this.damage += num.get(0);
            this.strength += num.get(1);
            this.critDamage += num.get(2);
            this.critChance += num.get(3);
            this.abilityDamage += num.get(4);
            this.abilityScaling += num.get(5);
            this.attackSpeed += num.get(6);
            if (this.attackSpeed > 100) {
                this.attackSpeed = 100;
            }
            this.ferocity += num.get(7);
            this.health += num.get(8);
            this.defense += num.get(9);
            this.trueDefense += num.get(10);
            this.speed += num.get(11);
            if (this.speed > 500) {
                this.speed = 500;
            }
            this.intelligence += num.get(12);
            this.magicFind += num.get(13);
            this.petLuck += num.get(14);
            this.miningSpeed += num.get(15);
            this.miningFortune += num.get(16);
            this.seaCreatureChance += num.get(17);
            this.absorption += num.get(18);

        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public void addItemStat(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            List<Double> stats = nbt.getDoubleList("Stats");
            addPlayerStat(stats);
        } catch (NullPointerException ignored) {
        }
    }

    public void addPetStats(Pet pet) {
        try {
            NBTItem nbtItem = new NBTItem(pet);
            NBTCompound nbt = nbtItem.getCompound("Item");
            List<Double> stats = nbt.getDoubleList("Stats");
            addPlayerStat(stats);
        } catch (NullPointerException ignored) {
        }
    }

    public ArrayList<Double> getStats() {
        ArrayList<Double> stats = new ArrayList<>();
        stats.add(this.damage);
        stats.add(this.strength);
        stats.add(this.critDamage);
        stats.add(this.critChance);
        stats.add(this.abilityDamage);
        stats.add(this.abilityScaling);
        stats.add(this.attackSpeed);
        stats.add(this.ferocity);
        stats.add(this.health);
        stats.add(this.defense);
        stats.add(this.trueDefense);
        stats.add(this.speed);
        stats.add(this.intelligence);
        stats.add(this.magicFind);
        stats.add(this.petLuck);
        stats.add(this.miningSpeed);
        stats.add(this.miningFortune);
        stats.add(this.seaCreatureChance);
        stats.add(this.absorption);
        return stats;
    }

    public void applyStats(boolean manaRegan) {
        Equipment equipment = this.getItems();

        ItemStack tool = equipment.tool;
        ItemStack helmet = equipment.helmet;
        ItemStack chestplate = equipment.chestplate;
        ItemStack leggings = equipment.leggings;
        ItemStack boots = equipment.boots;

        ToolMaterial toolMaterial = equipment.toolMaterial;
        ArmorMaterial helmetMaterial = equipment.helmetMaterial;
        ArmorMaterial chestplateMaterial = equipment.chestplateMaterial;
        ArmorMaterial leggingsMaterial = equipment.leggingsMaterial;
        ArmorMaterial bootsMaterial = equipment.bootsMaterial;

        String fullSet = equipment.fullSet;

        damage = 0;
        strength = 0;
        critDamage = 0;
        critChance = 10;
        attackSpeed = 0;
        ferocity = 0;
        health = 100;
        defense = 0;
        trueDefense = 0;
        speed = 100;
        intelligence = 100;
        magicFind = 0;
        petLuck = 0;
        miningSpeed = 0;
        miningFortune = 0;
        seaCreatureChance = 0;
        absorption = 0;

        if (Mining.miningWorlds.contains(player.getWorld().getName())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, -1, false, false));
        } else {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

        ArrayList<ItemFamily> accessoryFamilies = new ArrayList<>();
        for (ItemStack accessory : this.accessoryBag) {
            if (Functions.getItemMaterial(accessory).getType() == ItemType.ACCESSORY && !accessoryFamilies.contains(Items.get(accessory).getFamily())) {
                accessoryFamilies.add(Items.get(accessory).getFamily());
                this.addItemStat(accessory);
            }
        }

        // tool
        if (Functions.isNotAir(tool))
            if (toolMaterial.getType().isTool()) this.addItemStat(tool);

        // helmet
        if (Functions.isNotAir(helmet))
            if (helmetMaterial.getType() == ItemType.HELMET) this.addItemStat(helmet);

        // chestplate
        if (Functions.isNotAir(chestplate))
            if (chestplateMaterial.getType() == ItemType.CHESTPLATE) this.addItemStat(chestplate);

        // leggings
        if (Functions.isNotAir(leggings))
            if (leggingsMaterial.getType() == ItemType.LEGGINGS) this.addItemStat(leggings);

        // boots
        if (Functions.isNotAir(boots))
            if (bootsMaterial.getType() == ItemType.BOOTS) this.addItemStat(boots);

        if (this.activePet >= 0) {
            this.addPetStats(this.getPetActive());
        }

        // Full Sets
        if (fullSet.equals("Superior Blood")) {
            increasePlayerStat(0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0);
        }

        if (System.currentTimeMillis() - Atomsplit_Katana.atomsplitAbility.getOrDefault(player, 0L) <= 4000) {
            this.ferocity += 400;
        }

        if (Functions.getId(tool).equals("TERMINATOR")) {
            this.critChance = this.critChance / 4;
        }

        if (System.currentTimeMillis() - Rogue_Sword.rogueSwordLastTimeUsed.getOrDefault(this.player, 0L) <= 30000) {
            this.speed += (Rogue_Sword.rogueSwordAmountUsed.get(this.player) + 1) * 10;
        }

        if (manaRegan) {
            if (this.mana < this.intelligence) {
                this.mana += this.intelligence / 50;
            }
        }
        if (this.mana > this.intelligence) {
            this.mana = this.intelligence;
        }

        this.damage = Math.floor(this.damage * 10d) / 10d;
        this.strength = Math.floor(this.strength * 10d) / 10d;
        this.critDamage = Math.floor(this.critDamage * 10d) / 10d;
        this.critChance = Math.floor(this.critChance * 10d) / 10d;
        this.abilityDamage = Math.floor(this.abilityDamage * 10d) / 10d;
        this.abilityScaling = Math.floor(this.abilityScaling * 10d) / 10d;
        this.attackSpeed = Math.floor(this.attackSpeed * 10d) / 10d;
        this.ferocity = Math.floor(this.ferocity * 10d) / 10d;
        this.health = Math.floor(this.health * 10d) / 10d;
        this.defense = Math.floor(this.defense * 10d) / 10d;
        this.trueDefense = Math.floor(this.trueDefense * 10d) / 10d;
        this.speed = Math.floor(this.speed * 10d) / 10d;
        this.mana = Math.floor(this.mana * 10d) / 10d;
        this.intelligence = Math.floor(this.intelligence * 10d) / 10d;
        this.magicFind = Math.floor(this.magicFind * 10d) / 10d;
        this.petLuck = Math.floor(this.petLuck * 10d) / 10d;
        this.miningSpeed = Math.floor(this.miningSpeed * 10d) / 10d;
        this.miningFortune = Math.floor(this.miningFortune * 10d) / 10d;
        this.seaCreatureChance = Math.floor(this.seaCreatureChance * 10d) / 10d;
        this.absorption = Math.floor(this.absorption * 10d) / 10d;

        if (this.getMaxHealth() != this.getHealthStat()) {
            this.setMaxHealth(this.getHealthStat());
        }

        if (this.getHealth() * HEALTH_REGEN < this.getMaxHealth()) {
            this.setHealth(this.getHealth() * HEALTH_REGEN);
        } else if (this.getHealth() * HEALTH_REGEN > this.getMaxHealth()) {
            this.setHealth(this.getHealth());
        }

        this.setWalkSpeed((float) (this.speed / 500));

        Functions.sendActionBar(this);
    }

    public boolean manaCost(int manaCost, ItemStack item, String abilityName) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        int cost = Functions.manaCostCalculator(manaCost, item);
        if (this.mana >= cost) {
            this.mana -= cost;
            Functions.sendActionBar(this, abilityName + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }

    public boolean manaCost(int manaCost, ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return true;

        int cost = Functions.manaCostCalculator(manaCost, item);
        if (this.mana >= cost) {
            this.mana -= cost;
            Functions.sendActionBar(this, ((WeaponMaterial) Functions.getItemMaterial(item)).getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return true;
        }
        return false;
    }

    public boolean manaCost(ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        ToolMaterial material = (ToolMaterial) Functions.getItemMaterial(item);
        int cost = manaCostCalculator(material.getAbilities().get(i).getManaCost(), item);
        if (this.mana >= cost) {
            this.mana -= cost;
            Functions.sendActionBar(this, material.getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }

    public short getEnchantLevel(EnchantType enchant) {
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public short getEnchantLevel(EnchantType enchant, Condition condition) {
        if (!condition.check())
            return 0;
        return Functions.getEnchantLevel(player.getEquipment().getItemInMainHand(), enchant);
    }

    public void updatePlayerInventory() {
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            if (i > 39) continue;
            ItemStack itemStack = player.getInventory().getContents()[i];

            if (!isNotAir(itemStack)) continue;

            ItemMaterial itemMaterial = getItemMaterial(itemStack);

            Item item = new Item(itemMaterial, itemStack);
            copyNBTStack(item, itemStack);
            if (!item.isSimilar(itemStack) && !getId(itemStack).contains("_PET") && !Functions.getId(item).equals("SKYBLOCK_MENU")) {
                player.getInventory().setItem(i, item);
            }
        }

        if (!Functions.isNotAir(player.getInventory().getItem(8)) || !Functions.getId(player.getInventory().getItem(8)).equals("SKYBLOCK_MENU")) {
            Item menu = new Item(ItemMaterial.get("SKYBLOCK_MENU"), 1);
            ItemMeta menuMeta = menu.getItemMeta();
            List<String> lores = menuMeta.getLore();
            lores.remove(lores.size() - 1);
            lores.add(ChatColor.YELLOW + "Click to open!");
            menuMeta.setLore(lores);
            menu.setItemMeta(menuMeta);
            player.getInventory().setItem(8, menu);
        }
    }

    public void setScoreboardScores() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective(this.getPlayer().getName(), "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Skyblock Dragons");
        ArrayList<Score> scores = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        scores.add(objective.getScore(ChatColor.GRAY + format.format(now) + ChatColor.DARK_GRAY + " " + this.getWorld().getName()));
        scores.add(objective.getScore(""));
        scores.add(objective.getScore(ChatColor.WHITE + "Player: " + ChatColor.GREEN + this.getPlayer().getName()));
        scores.add(objective.getScore(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(this.getPurse())));
        String bitsAdder = "";

        if (playTime % 36000L >= 0L && playTime % 36000L < 20L) {
            bitsAdder = ChatColor.AQUA + "(+250 Bits)";
            if (playTime % 36000L < 5L) {
                this.addBits(250);
            }
        }
        scores.add(objective.getScore(ChatColor.WHITE + "Bits: " + ChatColor.AQUA + getNumberFormat(bits) + " " + bitsAdder));
        scores.add(objective.getScore(" "));
        if (this.getActivePet() >= 0) {
            scores.add(objective.getScore(ChatColor.WHITE + "Active Pet:"));
            scores.add(objective.getScore("  " + this.getPetActive().getRarity().getColor() + this.getPetActive().getPetMaterial().getName()));
            scores.add(objective.getScore("  "));
        }
        scores.add(objective.getScore(ChatColor.YELLOW + "www.error.net"));
        Collections.reverse(scores);

        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).setScore(i);
        }

        this.setScoreboard(scoreboard);
    }

    public static void loadPlayerData(Player player) {
        player.setHealthScale(40d);

        SkyblockDragons.players.put(player.getUniqueId(), new PlayerSD(player));

        List<ItemStack> accessories = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            ItemStack item = Variables.get(player.getUniqueId(), "AccessoryBag", i, null);
            if (item != null)
                accessories.add(item);
        }
        SkyblockDragons.players.get(player.getUniqueId()).setAccessoryBag(accessories);
    }

    public void sendActionBar(String message, boolean ignoreCooldown) {
        if (cooldown(player, actionBarCooldown, 500, false) && !ignoreCooldown) return;

        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void sendActionBar(String message) {
        this.sendActionBar(message, false);
    }

    public Entity getTargetEntity(int maxDistance) {
        Location location = player.getLocation();

        for (int i = 0; i <= maxDistance; i++) {
            Location loc = location.clone().add(location.clone().getDirection().multiply(i));

            for (Entity entity : Functions.loopEntities(loc, 1.5)) {
                if (entity instanceof Creature) {
                    return entity;
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

    public boolean chanceOf(double percent) {
        return Functions.chanceOf(percent);
    }

    public void makeDamage(Entity entity, Damage.DamageType damageType, double damage, double baseAbilityDamage, double abilityScaling) {
        EntityDamageEntityEvent event = new EntityDamageEntityEvent(this, entity, damageType, damage, false, baseAbilityDamage, abilityScaling);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void makeDamage(Entity entity, Damage.DamageType damageType, double damage) {
        makeDamage(entity, damageType, damage, 0, 0);
    }
}
