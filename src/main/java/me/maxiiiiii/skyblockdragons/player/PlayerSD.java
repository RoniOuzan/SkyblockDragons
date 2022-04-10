package me.maxiiiiii.skyblockdragons.player;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.damage.Damage;
import me.maxiiiiii.skyblockdragons.damage.EntityDamageEntityEvent;
import me.maxiiiiii.skyblockdragons.entity.ItemDrop;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.abilities.Atomsplit_Katana;
import me.maxiiiiii.skyblockdragons.item.abilities.Rogue_Sword;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.player.accessorybag.AccessoryBag;
import me.maxiiiiii.skyblockdragons.player.bank.objects.BankAccount;
import me.maxiiiiii.skyblockdragons.pet.Pet;
import me.maxiiiiii.skyblockdragons.pet.PlayerPet;
import me.maxiiiiii.skyblockdragons.player.skill.Skill;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.player.wardrobe.Wardrobe;
import me.maxiiiiii.skyblockdragons.storage.Variables;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.interfaces.Condition;
import me.maxiiiiii.skyblockdragons.util.objects.Cooldown;
import me.maxiiiiii.skyblockdragons.worlds.mining.Mining;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class PlayerSD extends PlayerClass {
    private final Player player;
//    public Coop coop;

    public PlayerStats stats;

    public int playTime;
    public int bits;

    public Skill skill;
    public Wardrobe wardrobe;

    public BankAccount bank;

    public PlayerPet playerPet;

    public AccessoryBag accessoryBag;

    public final Cooldown<PlayerSD> updateStatsCooldown = new Cooldown<>();

    private double lastCoins;

    public static final double HEALTH_REGEN = 1.05;

    public PlayerSD(Player player) {
        super(player);

        player.setHealthScale(40d);
        this.player = player;

        this.stats = new PlayerStats(
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
                0
        );

        this.playTime = Variables.get(player.getUniqueId(), "PlayTime", 0, 0);
        this.bits = Variables.get(player.getUniqueId(), "Bits", 0, 0);

        this.wardrobe = new Wardrobe(this);
        this.skill = new Skill(this);

        this.bank = new BankAccount(this, 50_000_000);

        this.playerPet = new PlayerPet(this);

        this.accessoryBag = new AccessoryBag(this);

        this.lastCoins = this.getCoins();

        SkyblockDragons.players.put(player.getUniqueId(), this);
    }

    public void save() {
        Variables.set(player.getUniqueId(), "PlayTime", 0, this.playTime);
        Variables.set(player.getUniqueId(), "Bits", 0, this.bits);
        this.wardrobe.save();
        this.skill.save();
        this.bank.save();
        this.playerPet.save();
        this.accessoryBag.save();
    }

    public void giveSkill(SkillType skillType, double amount) {
        this.skill.get(skillType.name()).giveXp(amount, player);
        this.sendActionBar(ChatColor.DARK_AQUA + "+" + Functions.getInt(amount + "") + " " + skillType + " (" + Math.floor(this.getSkill().get(skillType).getCurrentXp() / this.getSkill().get(skillType).getCurrentNeedXp() * 1000d) / 10d + "%)", true);
    }

    public void sendPacket(Packet<?> packet) {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        entityPlayer.playerConnection.sendPacket(packet);
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
        return this.playerPet.getPetActive();
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
        return this.stats.health.amount;
    }

    public void setHealthStat(double health) {
        this.stats.health.amount = health;
    }

    public double getPurse() {
        return SkyblockDragons.economy.getBalance(this);
    }

    public void addCoins(double amount) {
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
        SkyblockDragons.economy.depositPlayer(this, this.getCoins() - amount);
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
        if (item instanceof ItemDrop) {
            ItemStack itemStack = ((ItemDrop) item).generate(this);
            if (itemStack != null)
                this.player.getInventory().addItem(itemStack);
        } else {
            this.player.getInventory().addItem(item);
        }
    }

    public void addItemStat(ItemStack item) {
        try {
            NBTItem nbtItem = new NBTItem(item);
            NBTCompound nbt = nbtItem.getCompound("Item");
            List<Double> stats = nbt.getDoubleList("Stats");
            this.stats.add(stats);
        } catch (NullPointerException ignored) {
        }
    }

    public void addPetStats(Pet pet) {
        try {
            NBTItem nbtItem = new NBTItem(pet);
            NBTCompound nbt = nbtItem.getCompound("Item");
            List<Double> stats = nbt.getDoubleList("Stats");
            this.stats.add(stats);
        } catch (NullPointerException ignored) {
        }
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

        stats.reset();

        StatsMultiplayer statsMultiplayer = new StatsMultiplayer();

        if (Mining.miningWorlds.contains(player.getWorld())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 255, false, false));
        } else {
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

        ArrayList<ItemFamily> accessoryFamilies = new ArrayList<>();
        for (ItemStack accessory : this.accessoryBag.getItems()) {
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

        if (this.playerPet.activePet >= 0) {
            this.addPetStats(this.getPetActive());
        }

        if (helmetMaterial == Items.get("ENDER_HELMET"))
            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
        if (helmetMaterial == Items.get("ENDER_CHESTPLATE"))
            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
        if (helmetMaterial == Items.get("ENDER_LEGGINGS"))
            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);
        if (helmetMaterial == Items.get("ENDER_BOOTS"))
            statsMultiplayer.increase(5, 5, 5, 5, 5, 5);

        if (helmetMaterial == Items.get("ENDER_GUARD_HELMET"))
            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
        if (helmetMaterial == Items.get("ENDER_GUARD_CHESTPLATE"))
            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
        if (helmetMaterial == Items.get("ENDER_GUARD_LEGGINGS"))
            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);
        if (helmetMaterial == Items.get("ENDER_GuARD_BOOTS"))
            statsMultiplayer.increase(10, 10, 10, 10, 10, 10);


        if (System.currentTimeMillis() - Atomsplit_Katana.atomsplitAbility.getOrDefault(player, 0L) <= 4000) {
            this.stats.ferocity.amount += 400;
        }

        if (Functions.getId(tool).equals("TERMINATOR")) {
            this.stats.critChance.amount = this.stats.critChance.amount / 4;
        }

        if (System.currentTimeMillis() - Rogue_Sword.rogueSwordLastTimeUsed.getOrDefault(this.player, 0L) <= 30000) {
            this.stats.speed.amount += (Rogue_Sword.rogueSwordAmountUsed.get(this.player) + 1) * 10;
        }


        // Full Sets
        if (fullSet.equals("Superior Blood")) {
            statsMultiplayer.increase(0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 5, 5, 5, 5, 0, 0, 0, 0);
        }

        statsMultiplayer.apply(this.stats);

        if (manaRegan) {
            if (this.stats.mana.amount < this.stats.intelligence.amount) {
                this.stats.mana.amount += this.stats.intelligence.amount / 50;
            }
        }
        if (this.stats.mana.amount > this.stats.intelligence.amount) {
            this.stats.mana.amount = this.stats.intelligence.amount;
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

        this.setWalkSpeed((float) (this.stats.speed.amount / 500));

        Functions.sendActionBar(this);
    }

    public boolean manaCost(int manaCost, ItemStack item, String abilityName) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        int cost = Functions.manaCostCalculator(manaCost, item);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
            Functions.sendActionBar(this, abilityName + ChatColor.AQUA + "! (" + cost + " Mana)");
            return false;
        }
        this.player.sendMessage(ChatColor.RED + "You don't have enough mana to use this item!");
        return true;
    }

    public boolean manaCost(int manaCost, ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return true;

        int cost = Functions.manaCostCalculator(manaCost, item);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
            Functions.sendActionBar(this, ((WeaponMaterial) Functions.getItemMaterial(item)).getAbilities().get(i).getName() + ChatColor.AQUA + "! (" + cost + " Mana)");
            return true;
        }
        return false;
    }

    public boolean manaCost(ItemStack item, int i) {
        if (this.player.getGameMode() == GameMode.CREATIVE) return false;

        ToolMaterial material = (ToolMaterial) Functions.getItemMaterial(item);
        int cost = manaCostCalculator(material.getAbilities().get(i).getManaCost(), item);
        if (this.stats.mana.amount >= cost) {
            this.stats.mana.amount -= cost;
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
            Item menu = new Item(Items.get("SKYBLOCK_MENU"), 1);
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
        if (this.lastCoins != this.getCoins()) {
            this.setCoins(this.getCoins());
            if (this.getCoins() - this.lastCoins > 0)
                scores.add(objective.getScore(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(this.getPurse()) + " (+" + (this.getCoins() - this.lastCoins) + ")"));
            else
                scores.add(objective.getScore(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(this.getPurse()) + " (" + (this.getCoins() - this.lastCoins) + ")"));
            this.lastCoins = this.getCoins();
        } else {
            scores.add(objective.getScore(ChatColor.WHITE + "Purse: " + ChatColor.GOLD + getNumberFormat(this.getPurse())));
        }
        String bitsAdder = "";

        if (playTime % 36000L >= 0L && playTime % 36000L < 20L) {
            bitsAdder = ChatColor.AQUA + "(+250 Bits)";
            if (playTime % 36000L < 5L) {
                this.addBits(250);
            }
        }
        scores.add(objective.getScore(ChatColor.WHITE + "Bits: " + ChatColor.AQUA + getNumberFormat(bits) + " " + bitsAdder));
        scores.add(objective.getScore(" "));
        if (this.playerPet.activePet >= 0) {
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
