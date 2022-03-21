package me.maxiiiiii.skyblockdragons.stat;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.abilities.Atomsplit_Katana;
import me.maxiiiiii.skyblockdragons.abilities.Rogue_Sword;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemType;
import me.maxiiiiii.skyblockdragons.material.*;
import me.maxiiiiii.skyblockdragons.pet.Pet;
import me.maxiiiiii.skyblockdragons.skill.Skill;
import me.maxiiiiii.skyblockdragons.skill.Skills.*;
import me.maxiiiiii.skyblockdragons.storage.StorageUtil;
import me.maxiiiiii.skyblockdragons.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.wardrobe.WardrobeSlot;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.maxiiiiii.skyblockdragons.Functions.manaCostCalculator;

@Getter
@Setter
public class PlayerData {
    private final Player player;
    private double damage;
    private double strength;
    private double critDamage;
    private double critChance;
    private double attackSpeed;
    private double ferocity;
    private double health;
    private double defense;
    private double speed;
    private double mana;
    private double intelligence;

    private Skill skill;
    private Wardrobe wardrobe;

    private Pet activePet;

    private double purse;
    private ArrayList<ItemStack> accessoryBag;

    public PlayerData(Player player, double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence, Skill skill, Wardrobe wardrobe, Pet activePet) {
        this.player = player;
        this.damage = damage;
        this.strength = strength;
        this.critDamage = critDamage;
        this.critChance = critChance;
        if (attackSpeed <= 100) {
            this.attackSpeed = attackSpeed;
        }
        this.ferocity = ferocity;
        this.health = health;
        this.defense = defense;
        if (speed <= 500) {
            this.speed = speed;
        }
        this.mana = intelligence;
        this.intelligence = intelligence;

        this.wardrobe = wardrobe;
        this.skill = skill;

        this.activePet = activePet;

        this.purse = SkyblockDragons.purses.get(player.getUniqueId());
        this.accessoryBag = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            try {
                accessoryBag.add((ItemStack) SkyblockDragons.getSerializer().deserialize(StorageUtil.getVariable(player.getUniqueId(), "AccessoryBag", i).getValue()));
            } catch (NullPointerException ignored) {
            }
        }
    }

    public PlayerData(Player player) {
        this(
                player,
                new Skill(
                        new FarmingSkill(0, 0),
                        new MiningSkill(0, 0),
                        new CombatSkill(0, 0),
                        new ForagingSkill(0, 0),
                        new FishingSkill(0, 0),
                        new EnchantingSkill(0, 0),
                        new AlchemySkill(0, 0),
                        new TamingSkill(0, 0),
                        new DungeoneeringSkill(0, 0)
                ),
                new Wardrobe(
                        new WardrobeSlot(1, null, null, null, null),
                        new WardrobeSlot(2, null, null, null, null),
                        new WardrobeSlot(3, null, null, null, null),
                        new WardrobeSlot(4, null, null, null, null),
                        new WardrobeSlot(5, null, null, null, null),
                        new WardrobeSlot(6, null, null, null, null),
                        new WardrobeSlot(7, null, null, null, null),
                        new WardrobeSlot(8, null, null, null, null),
                        new WardrobeSlot(9, null, null, null, null),
                        new WardrobeSlot(10, null, null, null, null),
                        new WardrobeSlot(11, null, null, null, null),
                        new WardrobeSlot(12, null, null, null, null),
                        new WardrobeSlot(13, null, null, null, null),
                        new WardrobeSlot(14, null, null, null, null),
                        new WardrobeSlot(15, null, null, null, null),
                        new WardrobeSlot(16, null, null, null, null),
                        new WardrobeSlot(17, null, null, null, null),
                        new WardrobeSlot(18, null, null, null, null)
                ),
                null
        );
    }

    public PlayerData(Player player, Skill skill, Wardrobe wardrobe, Pet activePet) {
        this(player, 0, 0, 0, 20, 0, 0, 100, 0, 100, 100, skill, wardrobe, activePet);
    }

    public void increasePlayerStat(double damage, double strength, double critDamage, double critChance, double attackSpeed, double ferocity, double health, double defense, double speed, double intelligence) {
        this.damage *= 1 + damage / 100;
        this.strength *= 1 + strength / 100;
        this.critDamage *= 1 + critDamage / 100;
        this.critChance *= 1 + critChance / 100;
        if (this.attackSpeed * (attackSpeed / 100) > 100) {
            this.attackSpeed = 100;
        } else {
            this.attackSpeed *= 1 + attackSpeed / 100;
        }
        this.ferocity *= 1 + ferocity / 100;
        this.health *= 1 + health / 100;
        this.defense *= 1 + defense / 100;
        if (this.speed * (speed / 100) > 500) {
            this.speed = 500;
        } else {
            this.speed *= 1 + speed / 100;
        }
        this.intelligence *= 1 + intelligence / 100;
    }

    public void addPlayerStat(ArrayList<Double> num) {
        try {
            this.damage += num.get(0);
            this.strength += num.get(1);
            this.critDamage += num.get(2);
            this.critChance += num.get(3);
            this.attackSpeed += num.get(4);
            if (this.attackSpeed > 100) {
                this.attackSpeed = 100;
            }
            this.ferocity += num.get(5);
            this.health += num.get(6);
            this.defense += num.get(7);
            this.speed += num.get(8);
            if (this.speed > 500) {
                this.speed = 500;
            }
            this.intelligence += num.get(9);

        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    public void addItemStat(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            ArrayList<Double> stats = new ArrayList<>();
            for (int num : nbt.getIntArray("Stats")) {
                stats.add(num * 1.0);
            }
            addPlayerStat(stats);
        } catch (NullPointerException ignored) {
        }
    }

    public ArrayList<Double> getStats() {
        ArrayList<Double> stats = new ArrayList<>();
        stats.add(getDamage());
        stats.add(getStrength());
        stats.add(getCritDamage());
        stats.add(getCritChance());
        stats.add(getAttackSpeed());
        stats.add(getFerocity());
        stats.add(getHealth());
        stats.add(getDefense());
        stats.add(getSpeed());
        stats.add(getIntelligence());
        return stats;
    }

    public void applyStats(boolean manaRegan) {
        ItemStack tool = this.getPlayer().getEquipment().getItemInMainHand();
        if (Functions.getItemMaterial(tool) instanceof ToolMaterial) {
            ToolMaterial toolMaterial = (ToolMaterial) Functions.getItemMaterial(tool);
        }

        ItemStack helmet = this.getPlayer().getEquipment().getHelmet();
        ArmorMaterial helmetMaterial = ArmorMaterial.NULL;
        try {
            helmetMaterial = (ArmorMaterial) Functions.getItemMaterial(helmet);
        } catch (ClassCastException ignored) {}

        ItemStack chestplate = this.getPlayer().getEquipment().getChestplate();
        ArmorMaterial chestplateMaterial = ArmorMaterial.NULL;
        try {
            chestplateMaterial = (ArmorMaterial) Functions.getItemMaterial(chestplate);
        } catch (ClassCastException ignored) {}

        ItemStack leggings = this.getPlayer().getEquipment().getLeggings();
        ArmorMaterial leggingsMaterial = ArmorMaterial.NULL;
        try {
            leggingsMaterial = (ArmorMaterial) Functions.getItemMaterial(leggings);
        } catch (ClassCastException ignored) {}

        ItemStack boots = this.getPlayer().getEquipment().getBoots();
        ArmorMaterial bootsMaterial = ArmorMaterial.NULL;
        try {
            bootsMaterial = (ArmorMaterial) Functions.getItemMaterial(boots);
        } catch (ClassCastException ignored) {}

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
        speed = 100;
        intelligence = 100;

        for (ItemStack accessory : accessoryBag) {
            if (Functions.getItemMaterial(accessory).getType() == ItemType.ACCESSORY) {
                this.addItemStat(accessory);
            }
        }

        // tool
        try {
            if (Functions.getItemMaterial(tool).getType().isTool()) this.addItemStat(tool);
        } catch (NullPointerException ignored) {}

        // helmet
        try {
        if (Functions.getItemMaterial(helmet).getType() == ItemType.HELMET) this.addItemStat(helmet);
        } catch (NullPointerException ignored) {}

        // chestplate
        try {
        if (Functions.getItemMaterial(chestplate).getType() == ItemType.CHESTPLATE) this.addItemStat(chestplate);
        } catch (NullPointerException ignored) {}

        // leggings
        try {
        if (Functions.getItemMaterial(leggings).getType() == ItemType.LEGGINGS) this.addItemStat(leggings);
        } catch (NullPointerException ignored) {}

        // boots
        try {
        if (Functions.getItemMaterial(boots).getType() == ItemType.BOOTS) this.addItemStat(boots);
        } catch (NullPointerException ignored) {}

        // Full Sets
        if (fullSet.equals("Superior Blood")) {
            increasePlayerStat(0, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        }

        if (System.currentTimeMillis() - Atomsplit_Katana.atomsplitAbility <= 4000) {
            this.ferocity += 400;
        }

        if (Functions.getId(tool).equals("TERMINATOR")) {
            this.critChance = this.critChance / 4;
        }

        if (System.currentTimeMillis() - Rogue_Sword.rogueSwordLastTimeUsed.getOrDefault(this.player, 0L) <= 30000) {
            this.speed += (Rogue_Sword.rogueSwordAmountUsed.get(this.player) + 1) * 10;
        }

        if (manaRegan) {
            if (mana < intelligence) {
                mana += intelligence / 200;
            }
        }
        if (mana > intelligence) {
            mana = intelligence;
        }

    }

    public boolean manaCost(int manaCost, ItemStack item, String abilityName) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        int cost = Functions.manaCostCalculator(manaCost, item);
        if (this.mana >= cost) {
            this.mana -= cost;
            Functions.sendActionBar(this.player, abilityName + ChatColor.AQUA + "! (" + cost + " Mana)");
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
            Functions.sendActionBar(this.player, ((WeaponMaterial) Functions.getItemMaterial(item)).getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
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
            Functions.sendActionBar(this.player, material.getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }
}
