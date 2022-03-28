package me.maxiiiiii.skyblockdragons.player;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.player.coop.Coop;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.abilities.Atomsplit_Katana;
import me.maxiiiiii.skyblockdragons.item.abilities.Rogue_Sword;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.player.bits.BitsUtil;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.material.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.material.ToolMaterial;
import me.maxiiiiii.skyblockdragons.material.WeaponMaterial;
import me.maxiiiiii.skyblockdragons.player.pet.Pet;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.player.wardrobe.WardrobeSlot;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class PlayerSD extends EntitySD implements Player {
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

    public Skill skill;
    public Wardrobe wardrobe;

    public BankAccount bank;

    public int activePet;
    public ArrayList<Pet> pets;
    public Pet.ArmorStand petArmorStand;
    public boolean hidePets;

    public ArrayList<ItemStack> accessoryBag;

    public final Cooldown actionBarCooldown = new Cooldown();
    public final Cooldown damageCooldown = new Cooldown();

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
        this.miningFortune= 0;
        this.seaCreatureChance = 0;
        this.absorption = 0;

        ArrayList<WardrobeSlot> wardrobeSlots = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            wardrobeSlots.add(new WardrobeSlot(
                    i,
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 0), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 1), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 2), "null"), null),
                    (ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariableValue(player.getUniqueId(), "Wardrobe", numberToItemSlot(i, 3), "null"), null)
            ));
        }
        this.wardrobe = new Wardrobe(wardrobeSlots);
        this.skill = new Skill(
                this,
                new FarmingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Farming", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Farming", 2, "0"))),
                new MiningSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Mining", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Mining", 2, "0"))),
                new CombatSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Combat", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Combat", 2, "0"))),
                new ForagingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Foraging", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Foraging", 2, "0"))),
                new FishingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Fishing", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Fishing", 2, "0"))),
                new EnchantingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Enchanting", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Enchanting", 2, "0"))),
                new AlchemySkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Alchemy", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Alchemy", 2, "0"))),
                new TamingSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Taming", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Taming", 2, "0"))),
                new DungeoneeringSkill(Integer.parseInt(Variables.getVariableValue(player.getUniqueId(), "Dungeoneering", 1, "0")), Double.parseDouble(Variables.getVariableValue(player.getUniqueId(), "Dungeoneering", 2, "0")))
        );

        try {
            this.bank = new BankAccount(this, Double.parseDouble(Variables.getVariable(player.getUniqueId(), "BankPersonal").getValue()), Double.parseDouble(Variables.getVariable(player.getUniqueId(), "BankCoop").getValue()));
        } catch (NullPointerException ex) {
            this.bank = new BankAccount(this, 0, 0);
        }

        this.pets = new ArrayList<>();
        for (int i = 0; i < 112; i++) {
            try {
                this.pets.add(Pet.getPet((ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariable(player.getUniqueId(), "Pets", i).getValue()), false));
            } catch (NullPointerException ignored) {
            }
        }

        try {
            this.activePet = Integer.parseInt(Variables.getVariable(player.getUniqueId(), "ActivePet").getValue());
            if (this.activePet < 0)
                this.petArmorStand = null;
            else
                this.petArmorStand = new Pet.ArmorStand(this, this.getPetActive(), Pet.spawnPet(this, this.getPetActive()), this.activePet);
        } catch (NullPointerException ex) {
            this.activePet = -1;
            this.petArmorStand = null;
        }

        try {
            this.hidePets = Variables.getVariable(player.getUniqueId(), "HidePets").equals("1");
        } catch (NullPointerException ex) {
            this.hidePets = false;
        }

        this.accessoryBag = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            try {
                this.accessoryBag.add((ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariable(player.getUniqueId(), "AccessoryBag", i).getValue()));
            } catch (NullPointerException ignored) {
            }
        }
    }

    public void setActivePet(int activePet) {
        this.activePet = activePet;
        Variables.setVariable(player.getUniqueId(), "ActivePet", activePet + "");
    }

    public Pet getPetActive() {
        return this.pets.get(this.activePet);
    }

    public Pet getPet() {
        return this.getPetActive();
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
            this.trueDefense += num.get(9);
            this.defense += num.get(10);
            this.speed += num.get(11);
            if (this.speed > 500) {
                this.speed = 500;
            }
            this.intelligence += num.get(12);
            this.magicFind += num.get(12);
            this.petLuck += num.get(12);
            this.miningSpeed += num.get(12);
            this.miningFortune += num.get(12);
            this.seaCreatureChance += num.get(12);
            this.absorption += num.get(12);

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
        } catch (NullPointerException ignored) {}
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
        ItemStack tool = this.getPlayer().getEquipment().getItemInMainHand();
        ToolMaterial toolMaterial = ToolMaterial.NULL;
        if (Functions.getItemMaterial(tool) instanceof ToolMaterial) {
            toolMaterial = (ToolMaterial) Functions.getItemMaterial(tool);
        }

        ItemStack helmet = this.getPlayer().getEquipment().getHelmet();
        ArmorMaterial helmetMaterial = ArmorMaterial.NULL;
        if (Functions.getItemMaterial(helmet) instanceof ArmorMaterial) {
            helmetMaterial = (ArmorMaterial) Functions.getItemMaterial(helmet);
        }

        ItemStack chestplate = this.getPlayer().getEquipment().getChestplate();
        ArmorMaterial chestplateMaterial = ArmorMaterial.NULL;
        if (Functions.getItemMaterial(chestplate) instanceof ArmorMaterial) {
            chestplateMaterial = (ArmorMaterial) Functions.getItemMaterial(chestplate);
        }

        ItemStack leggings = this.getPlayer().getEquipment().getLeggings();
        ArmorMaterial leggingsMaterial = ArmorMaterial.NULL;
        if (Functions.getItemMaterial(leggings) instanceof ArmorMaterial) {
            leggingsMaterial = (ArmorMaterial) Functions.getItemMaterial(leggings);
        }

        ItemStack boots = this.getPlayer().getEquipment().getBoots();
        ArmorMaterial bootsMaterial = ArmorMaterial.NULL;
        if (Functions.getItemMaterial(boots) instanceof ArmorMaterial) {
            bootsMaterial = (ArmorMaterial) Functions.getItemMaterial(boots);
        }

        String fullSet = "";
        if (helmetMaterial.getFullSet().getName().equals(chestplateMaterial.getFullSet().getName()) && chestplateMaterial.getFullSet().getName().equals(leggingsMaterial.getFullSet().getName()) && leggingsMaterial.getFullSet().getName().equals(bootsMaterial.getFullSet().getName())) {
            fullSet = helmetMaterial.getFullSet().getName();
        }

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

        for (ItemStack accessory : accessoryBag) {
            if (Functions.getItemMaterial(accessory).getType() == ItemType.ACCESSORY) {
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

        this.damage = Math.floor(this.damage * 100d) / 100d;
        this.strength = Math.floor(this.strength * 100d) / 100d;
        this.critDamage = Math.floor(this.critDamage * 100d) / 100d;
        this.critChance = Math.floor(this.critChance * 100d) / 100d;
        this.abilityDamage = Math.floor(this.abilityDamage * 100d) / 100d;
        this.abilityScaling = Math.floor(this.abilityScaling * 100d) / 100d;
        this.attackSpeed = Math.floor(this.attackSpeed * 100d) / 100d;
        this.ferocity = Math.floor(this.ferocity * 100d) / 100d;
        this.health = Math.floor(this.health * 100d) / 100d;
        this.defense = Math.floor(this.defense * 100d) / 100d;
        this.trueDefense = Math.floor(this.trueDefense * 100d) / 100d;
        this.speed = Math.floor(this.speed * 100d) / 100d;
        this.mana = Math.floor(this.mana * 100d) / 100d;
        this.intelligence = Math.floor(this.intelligence * 100d) / 100d;
        this.magicFind = Math.floor(this.magicFind * 100d) / 100d;
        this.petLuck = Math.floor(this.petLuck * 100d) / 100d;
        this.miningSpeed = Math.floor(this.miningSpeed * 100d) / 100d;
        this.miningFortune = Math.floor(this.miningFortune * 100d) / 100d;
        this.seaCreatureChance = Math.floor(this.seaCreatureChance * 100d) / 100d;
        this.absorption = Math.floor(this.absorption * 100d) / 100d;

        if (this.getMaxHealth() != this.getHealthStat()) {
            this.setMaxHealth(this.getHealthStat());
        }

        if (this.getHealth() * HEALTH_REGEN < this.getMaxHealth()) {
            this.setHealth(Math.floor(this.getHealth() * HEALTH_REGEN * 100d) / 100d);
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
        if (SkyblockDragons.playTime.getOrDefault(this.getPlayer().getUniqueId(), 0L) % 36000L >= 0L && SkyblockDragons.playTime.getOrDefault(this.getPlayer().getUniqueId(), 0L) % 36000L < 20L) {
            bitsAdder = ChatColor.AQUA + "(+250 Bits)";
            if (SkyblockDragons.playTime.getOrDefault(this.getPlayer().getUniqueId(), 0L) % 36000L < 5L) {
                BitsUtil.add(this.getPlayer(), 250L);
            }
        }
        scores.add(objective.getScore(ChatColor.WHITE + "Bits: " + ChatColor.AQUA + getNumberFormat(SkyblockDragons.bits.get(this.getPlayer().getUniqueId())) + " " + bitsAdder));
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
        SkyblockDragons.purses.put(player.getUniqueId(), 0d);
        SkyblockDragons.bits.put(player.getUniqueId(), 0L);
        player.setHealthScale(40d);

        SkyblockDragons.players.put(player.getUniqueId(), new PlayerSD(player));

        ArrayList<ItemStack> accessories = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            try {
                accessories.add((ItemStack) SkyblockDragons.getSerializer().deserialize(Variables.getVariable(player.getUniqueId(), "AccessoryBag", i).getValue()));
            } catch (NullPointerException ex) {
                accessories.add(new ItemStack(Material.AIR));
            }
        }
        SkyblockDragons.players.get(player.getUniqueId()).setAccessoryBag(accessories);

        try {
            SkyblockDragons.purses.put(player.getUniqueId(), Double.parseDouble(Variables.getVariable(player.getUniqueId(), "Purse").getValue()));
        } catch (NullPointerException ex) {
            SkyblockDragons.purses.put(player.getUniqueId(), 0d);
        }

        try {
            SkyblockDragons.bits.put(player.getUniqueId(), Long.parseLong(Variables.getVariable(player.getUniqueId(), "Bits").getValue()));
        } catch (NullPointerException ex) {
            SkyblockDragons.bits.put(player.getUniqueId(), 0L);
        }

        if (!SkyblockDragons.disablePlayTime) {
            try {
                SkyblockDragons.playTime.put(player.getUniqueId(), Long.parseLong(Variables.getVariable(player.getUniqueId(), "PlayTime").getValue()));
            } catch (NullPointerException ex) {
                SkyblockDragons.playTime.put(player.getUniqueId(), 0L);
            }
        }
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
    public void playEffect(Location loc, Effect effect, int data) {
        this.player.playEffect(loc, effect, data);
    }

    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {
        this.player.playEffect(loc, effect, data);
    }

    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {
        this.player.sendBlockChange(loc, material, data);
    }

    @Override
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        return this.player.sendChunkChange(loc, sx, sy, sz, data);
    }

    @Override
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
    public void awardAchievement(Achievement achievement) {
        this.player.awardAchievement(achievement);
    }

    @Override
    public void removeAchievement(Achievement achievement) {
        this.player.removeAchievement(achievement);
    }

    @Override
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
//        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entity.getEntityId());
//        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void showEntity(Entity entity) {
        if (entity == null) return;

        SkyblockDragons.entityHider.showEntity(player, entity);
//        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving) entity);
//        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
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
        String message = ",";
        for (Object value : messages) {
            message += ", " + value;
        }
        message = message.replace(",, ", "");
        this.player.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] messages) {
        this.player.sendMessage(messages);
    }

    public void sendActionBar(String message, boolean ignoreCooldown) {
        if (cooldown(player, actionBarCooldown, 500, false) && !ignoreCooldown) return;

        this.player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public void sendActionBar(String message) {
        this.sendActionBar(message, false);
    }

    @Override
    public Server getServer() {
        return this.player.getServer();
    }

    @Override
    public Entity getPassenger() {
        return this.player.getPassenger();
    }

    @Override
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
    public Spigot spigot() {
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
    public ItemStack getItemInHand() {
        return this.player.getItemInHand();
    }

    @Override
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
    public Entity getShoulderEntityLeft() {
        return this.player.getShoulderEntityLeft();
    }

    @Override
    public void setShoulderEntityLeft(Entity entity) {
        this.player.setShoulderEntityLeft(entity);
    }

    @Override
    public Entity getShoulderEntityRight() {
        return this.player.getShoulderEntityRight();
    }

    @Override
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

    public Entity getTargetEntity(int maxDistance) {
        Location location = player.getLocation();

        for (int i = 0; i < maxDistance; i++) {
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

    @Override
    public String toString() {
        return this.player.getName();
    }
}
