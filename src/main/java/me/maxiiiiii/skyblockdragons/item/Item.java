package me.maxiiiiii.skyblockdragons.item;

import de.tr7zw.changeme.nbtapi.*;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystal;
import me.maxiiiiii.skyblockdragons.item.crystals.Crystals;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifier;
import me.maxiiiiii.skyblockdragons.item.modifiers.ItemModifiers;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetAbility;
import me.maxiiiiii.skyblockdragons.item.pet.material.PetRarity;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.item.stats.ItemStats;
import me.maxiiiiii.skyblockdragons.item.stats.Stat;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import me.maxiiiiii.skyblockdragons.item.stats.UpdateItemStatsEvent;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.*;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.loreBuilder;
import static me.maxiiiiii.skyblockdragons.util.Functions.setTitleCase;

@Getter
public class Item extends ItemStack implements Comparable<Item>, ConfigurationSerializable {
    public static final Item SKYBLOCK_MENU = new Item(Items.get("SKYBLOCK_MENU"), 1);
    public static final Item SKYBLOCK_MENU_ARROW = new Item(Items.get("SKYBLOCK_MENU_ARROW"), 64);

    static {
        updateSkyblockMenuLores(SKYBLOCK_MENU);
        updateSkyblockMenuLores(SKYBLOCK_MENU_ARROW);
    }

    private final PlayerSD player;

    private final ItemMaterial material;
    private final int amount;
    private final UUID uuid;

    private final ItemModifiers modifiers;
    private final Rarity rarity;
    private final Stats stats;

    // int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities
    public Item(PlayerSD player, ItemMaterial material, int amount, UUID uuid, ItemModifier... modifiers) {
        super(material.getMaterial(),
                amount,
                material.getMaterial() == Material.SKULL_ITEM ? (short) 3 : (short) material.getData()
        );
        this.player = player;
        this.material = material;
        this.amount = amount;
        this.uuid = isNotStackable() ? uuid : null;

        this.modifiers = new ItemModifiers(modifiers);
        if (this.material instanceof BookMaterial) {
            rarity = Rarity.getBookRarity(this.modifiers);
        } else if (this.modifiers.getPet().getRarity() != Rarity.NONE) {
            rarity = this.modifiers.getPet().getRarity();
        } else {
            rarity = Rarity.getRarity(material.getRarity().getLevel() + (this.modifiers.getRecombabulated() ? 1 : 0));
        }
        this.stats = new Stats();

        this.toItem(player);
    }

    public Item(PlayerSD player, ItemMaterial material, ItemModifier... modifiers) {
        this(player, material, 1, UUID.randomUUID(), modifiers);
    }

    public Item(ItemMaterial material, int amount, UUID uuid, ItemModifier... modifiers) {
        this(null, material, amount, uuid, modifiers);
    }

    public Item(ItemMaterial material, int amount, ItemModifier... modifiers) {
        this(null, material, amount, UUID.randomUUID(), modifiers);
    }

    public Item(ItemMaterial material, ItemModifier... modifiers) {
        this(material, 1, modifiers);
    }

    public Item(ItemStack itemStack) {
        this(Items.get(itemStack), itemStack);
    }

    public Item(ItemMaterial material, ItemStack fromItem, ItemModifier... modifiers) {
        this(null, material, fromItem, modifiers);
    }

    public Item(PlayerSD player, ItemStack itemStack, ItemModifier... modifiers) {
        this(player, Items.get(itemStack), itemStack, modifiers);
    }

    public Item(PlayerSD player, ItemMaterial material, ItemModifiers itemModifiers, ItemModifier... modifiers) {
        this(player,
                material,
                overrideModifiers(itemModifiers, modifiers)
        );
    }

    public Item(PlayerSD player, ItemMaterial material, ItemStack fromItem, ItemModifier... modifiers) {
        this(player,
                material,
                Functions.isStackable(fromItem) ? fromItem.getAmount() : 1,
                getUUID(fromItem),
                convertModifiersFromItem(modifiers, fromItem)
        );
        Functions.copyNBTStack(this, fromItem);
    }

    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    private void toItem(PlayerSD player) {
        List<String> lores = new ArrayList<>();

        if (modifiers.getPet().getRarity() != Rarity.NONE) {
            applyPetLores(lores);
        } else {
            if (this.material instanceof ItemStatsAble)
                applyStats(lores);

            if (this.material instanceof ItemEnchantAble)
                applyEnchants(lores);

            if (this.material instanceof ItemDescriptionAble)
                applyDescription(lores);

            if (this.material instanceof ItemAbilityAble)
                applyAbilities(lores);

            if (this.material instanceof NecronBladeMaterial) {
                if (modifiers.getNecronBladeScrolls().size() >= 3) {
                    if (isNotLastEmpty(lores)) lores.add("");
                    lores.add(ChatColor.GOLD + "Item Ability: Wither Impact " + ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK");

                    String description = ChatColor.GRAY + "Teleports " + ChatColor.GREEN + "10 blocks " + ChatColor.GRAY + "ahead of you. Then implode dealing " + ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby enemies. Also applies the " + ChatColor.YELLOW + "wither shield " + ChatColor.GRAY + "scroll ability reducing damage taken and granting an " + ChatColor.GOLD + "❤ Absorption " + ChatColor.GRAY + "shield for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds.";
                    lores.addAll(Functions.loreBuilder(description));

                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(300, player, modifiers));
                } else if (modifiers.getNecronBladeScrolls().size() > 0) {
                    for (NecronBladeMaterial.NecronBladeAbility ability : modifiers.getNecronBladeScrolls()) {
                        applyNecronAbility(lores, ability);
                    }
                } else {
                    if (isNotLastEmpty(lores)) lores.add("");

                    lores.add(ChatColor.YELLOW + "Right-click to use your class ability!");
                }
            } else if (this.material instanceof BookMaterial) {
                int cost = getBookCost();
                lores.add("");
                lores.add(ChatColor.GRAY + "Apply Cost: " + ChatColor.DARK_AQUA + cost + " Exp Levels");
                lores.add("");
                lores.add(ChatColor.GRAY + "Use this item on an item in an Anvil");
                lores.add(ChatColor.GRAY + "to apply it!");
                int levelRequirement = 0;
                for (EnchantType enchantType : modifiers.getEnchants().keySet()) {
                    if (enchantType.getRequirement().getLevel() >= levelRequirement)
                        levelRequirement = enchantType.getRequirement().getLevel();
                }
                if (player == null || player.getSkill().getEnchantingSkill().getLevel() < levelRequirement) {
                    lores.add("");
                    lores.add(SkillRequirement.toString(SkillType.ENCHANTING, levelRequirement));
                }
            } else if (this.material instanceof MiningMaterial) {
                lores.add(0, ChatColor.DARK_GRAY + "Breaking Power " + ((MiningMaterial) material).getBreakingPower());
                lores.add(1, "");
            } else if (this.material instanceof ReforgeMaterial) {
                ReforgeMaterial material = (ReforgeMaterial) this.material;

                lores.add(ChatColor.DARK_GRAY + "Reforge Stone");
                lores.add("");
                lores.add(ChatColor.GRAY + "Can be used in a Reforge Anvil");
                lores.add(ChatColor.GRAY + "or with the Dungeon Blacksmith");
                lores.add(ChatColor.GRAY + "to apply the " + ChatColor.BLUE + material.getReforgeName() + ChatColor.GRAY + " reforge");
                lores.add(ChatColor.GRAY + "to " + Functions.setTitleCase(Functions.getReforge(material.getReforgeName().toUpperCase()).getTypes().get(0).toString()) + ".");
            } else if (this.material instanceof SkinMaterial) {
                SkinMaterial material = (SkinMaterial) this.material;

                lores.add(ChatColor.GRAY + "Skins give your gear fresh");
                lores.add(ChatColor.GRAY + "new look! Apply them with an item");
                lores.add(ChatColor.GRAY + "in a anvil.");
                lores.add("");
                lores.add(ChatColor.GRAY + "This skin can be applied to");
                lores.add(Items.items.get(material.name().replaceAll("_SKIN", "")).getRarity().getColor() + Items.items.get(material.name().replaceAll("_SKIN", "")).getName());
            } else if (this.material instanceof PowerOrbMaterial) {
                PowerOrbMaterial material = (PowerOrbMaterial) this.material;

                if (isNotLastEmpty(lores)) lores.add("");

                lores.add(material.getRarity().getColor() + "Orb Buff: " + material.getPowerOrbName());
                lores.addAll(Functions.loreBuilder(material.getDescription(player), ChatColor.GRAY, 50));
            } else if (this.material instanceof NormalMaterial) {
                NormalMaterial material = (NormalMaterial) this.material;

                if (material.isShowRecipe()) {
                    if (!material.getDescription(player).equals("")) {
                        lores.add("");
                    }
                    lores.add(ChatColor.YELLOW + "Right-click to view recipe!");
                }
            }
        }

        NBTItem nbtItem = new NBTItem(this, true);
        try {
            NBTCompound nbt = nbtItem.addCompound("Item");
            NBTCompound extra = nbtItem.addCompound("ExtraAttributes");
            nbt.setString("id", this.material.name());
            extra.setString("id", this.material.name());

            if (isNotStackable())
                nbt.setString("uuid", this.uuid.toString());

            NBTList<Double> statList = nbt.getDoubleList("Stats");
            for (Stat stat : stats) {
                statList.add(stat.amount);
            }
//            if (isStackable()) {
//                nbt.setInteger("Stack", Functions.randomInt(1, 10000));
//                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//                nbt.setString("Date", format.format(new Date()));
//            }
            nbt.setInteger("Rarity", rarity.getLevel());

            for (ItemModifier modifier : modifiers) {
                modifier.applyNBT(this, nbt);
            }

            if (this.material instanceof BookMaterial) {
                nbt.setInteger("BookCost", getBookCost());
            }

            if (material.getItemSkull() != null) {
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                if (modifiers.getSkin() != null && modifiers.getSkin() != SkinMaterial.NULL) {
                    skull.setString("Id", modifiers.getSkin().getItemSkull().getId());
                    NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                    texture.setString("Value", modifiers.getSkin().getItemSkull().getValue());
                } else {
                    skull.setString("Id", material.getItemSkull().getId());
                    NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                    texture.setString("Value", material.getItemSkull().getValue());
                }
            }
        } catch (NbtApiException ignored) {}

        ItemMeta meta = this.getItemMeta();
        String reforgeText = "";
        if (modifiers.getReforge() != ReforgeType.NULL) {
            reforgeText = modifiers.getReforge() + " ";
        }
        if (modifiers.getPet().getRarity() != Rarity.NONE)
            meta.setDisplayName(rarity.getColor() + reforgeText + this.material.getName() + " " + ChatColor.GRAY + "[Level " + modifiers.getPet().getLevel() + "]");
        else
            meta.setDisplayName(rarity.getColor() + reforgeText + this.material.getName());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (lores.size() > 0 && isNotLastEmpty(lores)) lores.add("");

        if (material instanceof ItemRequirementAble) {
            lores.addAll(((ItemRequirementAble) material).getRequirements().getRequirements().stream().filter(r -> player == null || !r.hasRequirement(player) || player.getGameMode() == GameMode.CREATIVE).map(Requirement::toString).collect(Collectors.toList()));
        }

        if (modifiers.getRecombabulated()) {
            lores.add(rarity.getColor() + "" + ChatColor.MAGIC + "X" + ChatColor.RESET + " " + rarity + " " + this.material.getType().toString() + " " + rarity.getColor() + "" + ChatColor.MAGIC + "X");
        } else {
            lores.add(rarity + " " + this.material.getType().toString());
        }
        lores.replaceAll(s -> s.replace(".0", ""));
        if (lores.get(0).isEmpty() && lores.size() != 2) lores.remove(0);
        meta.setLore(lores);

        if (this.material instanceof NormalMaterial) {
            NormalMaterial material = (NormalMaterial) this.material;

            if (material.isEnchanted()) meta.addEnchant(Enchantment.DURABILITY, 3, true);
        }

        this.setItemMeta(meta);

        if (this.material instanceof ArmorMaterial && ((ArmorMaterial) this.material).getColor() != null)
            Functions.setArmorColor(this, ((ArmorMaterial) this.material).getColor());
    }

    private void applyPetLores(List<String> lores) {
        PetMaterial material = (PetMaterial) this.material;
        int level = modifiers.getPet().getLevel();
        double currentXp = modifiers.getPet().getCurrentXp();
        double needXp = material.getNeedXp(level);

        lores.add(ChatColor.DARK_GRAY + setTitleCase(material.getSkill().name()) + " Pet");
        lores.add("");

        ItemStatsAble statsAble = (ItemStatsAble) this.material;
        ItemStats stats = new ItemStats(statsAble.getStats(), this);
        for (Stat stat : stats) {
            stat.multiply(level);
        }

        if (player != null) {
            UpdateItemStatsEvent event = new UpdateItemStatsEvent(player, stats);
            Bukkit.getPluginManager().callEvent(event);

            stats.applyMultipliers();
        }

        stats.stream().filter(s -> !s.isEmpty()).forEach(s ->
                lores.add(ChatColor.GRAY + s.getType().toString() + ": " + ChatColor.GREEN + Functions.getNumSymbol(s) + stats.getLoreModifiers(s.getType()))
        );
        this.stats.add(stats);

        material.getAbilities().stream().filter(p -> p.getRarity() == rarity).map(PetRarity::getAbility).forEach(abilities -> {
            for (PetAbility ability : abilities) {
                if (isNotLastEmpty(lores)) lores.add("");

                lores.addAll(ability.getLore(player, level, this));
            }
        });

        if (level < material.getMaxLevel()) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Progress to Level " + (level + 1) + ": " + ChatColor.YELLOW + Math.round((currentXp / needXp * 100) * 100d) / 100d + "%");
            lores.add(Functions.progressBar(currentXp, needXp, 20) + " " + ChatColor.YELLOW + Functions.getNumberFormat(currentXp) + ChatColor.GOLD + "/" + ChatColor.YELLOW + Functions.getShortNumber(needXp));
        } else {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.add(ChatColor.GRAY + "Pet XP: " + ChatColor.GREEN + Functions.getNumberFormat(currentXp));
        }
    }

    private void applyNecronAbility(List<String> lores, NecronBladeMaterial.NecronBladeAbility ability) {
        if (isNotLastEmpty(lores)) lores.add("");
        lores.addAll(ability.getAbility().getLore(player, this));
    }

    private void applyAbilities(List<String> lores) {
        ItemAbilityAble material = (ItemAbilityAble) this.material;

        if (material.getAbilities().size() == 0 || material.getAbilities().get(0) == null)
            return;

        for (ItemAbility ability : material.getAbilities()) {
            if (isNotLastEmpty(lores)) lores.add("");
            lores.addAll(ability.getLore(player, this));
        }
    }

    private void applyDescription(List<String> lores) {
        ItemDescriptionAble material = (ItemDescriptionAble) this.material;
        if (!material.getDescription(player).isEmpty()) {
            if (isNotLastEmpty(lores)) lores.add("");
            lores.addAll(loreBuilder(material.getDescription(player)));
        }
    }

    private void applyStats(List<String> lores) {
        ItemStatsAble statsAble = (ItemStatsAble) this.material;
        ItemStats stats = new ItemStats(statsAble.getStats(), this);

        if (player != null) {
            UpdateItemStatsEvent event = new UpdateItemStatsEvent(player, stats);
            Bukkit.getPluginManager().callEvent(event);

            stats.applyMultipliers();
        }

        stats.stream().filter(s -> !s.isEmpty()).forEach(s ->
                lores.add(ChatColor.GRAY + s.getType().toString() + ": " + ChatColor.GREEN + Functions.getNumSymbol(s) + stats.getLoreModifiers(s.getType()))
        );
        this.stats.add(stats);

        StringBuilder crystalLore = new StringBuilder();
        Crystals crystals = this.modifiers.getCrystals();
        for (int i = 0; i < statsAble.getMaxCrystals(); i++) {
            if (i >= crystals.size()) {
                crystalLore.append(ChatColor.GRAY).append("[✧] ");
            } else {
                Crystal crystal = crystals.get(i);
                crystalLore.append(Rarity.getRarity(crystal.getLevel()).getColor()).append("[").append(ChatColor.LIGHT_PURPLE).append(crystal.getCrystal().getStatType().getIcon()).append(Rarity.getRarity(crystal.getLevel()).getColor()).append("] ");
            }
        }
        lores.add(crystalLore.toString());

        if (!modifiers.getEnchants().containsKey(EnchantType.NULL) && !lores.get(lores.size() - 1).equals("")) lores.add("");
    }

    public int getBookCost() {
        int cost = 3;
        if (!modifiers.getEnchants().containsKey(EnchantType.NULL)) {
            for (EnchantType enchantType : EnchantType.enchants.values()) {
                if (modifiers.getEnchants().containsKey(enchantType)) {
                    cost += modifiers.getEnchants().get(enchantType) * 6;
                }
            }
        }
        cost = (int) Math.pow(cost, 0.8);
        return cost;
    }

    private void applyEnchants(List<String> lores) {
        List<EnchantType> enchantList = new ArrayList<>(modifiers.getEnchants().keySet());
        Collections.sort(enchantList);

        if (modifiers.getEnchants().containsKey(EnchantType.ONE_FOR_ALL)) {
            if (modifiers.getEnchants().containsKey(EnchantType.TELEKINESIS)) {
                lores.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "One For All I, " + ChatColor.BLUE + "Telekinesis I");
            } else {
                lores.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "One For All I");
            }
        } else {
            boolean everyLine = enchantList.size() <= 6;
            boolean description = enchantList.size() <= 3;
            int times = 0;
            StringBuilder enchant = new StringBuilder(",");
            if (!modifiers.getEnchants().containsKey(EnchantType.NULL)) {
                for (EnchantType enchantType : enchantList) {
                    if (modifiers.getEnchants().containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        times++;
                        if (everyLine) {
                            lores.add(getEnchantLoreColor(enchantType, modifiers.getEnchants().get(enchantType)) + enchantType + " " + Functions.integerToRoman(modifiers.getEnchants().get(enchantType)));
                            if (description) {
                                lores.addAll(loreBuilder(enchantType.getDescription(modifiers.getEnchants().get(enchantType))));
                            }
                        } else {
                            enchant.append(", ").append(getEnchantLoreColor(enchantType, modifiers.getEnchants().get(enchantType))).append(enchantType).append(" ").append(Functions.integerToRoman(modifiers.getEnchants().get(enchantType)));
                            if (times % 3 == 0) {
                                enchant = new StringBuilder(enchant.toString().replaceAll(",, ", ""));
                                lores.add(enchant.toString());
                                enchant = new StringBuilder(",");
                            } else if (times == modifiers.getEnchants().size()) {
                                enchant = new StringBuilder(enchant.toString().replaceAll(",, ", ""));
                                lores.add(enchant.toString());
                                enchant = new StringBuilder(",");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isNotStackable() {
        return material.getType() != ItemType.ITEM || (material instanceof NormalMaterial && !((NormalMaterial) material).isStackAble());
    }

    public static String getEnchantLoreColor(EnchantType enchantType, int level) {
        if (enchantType instanceof UltimateEnchantType)
            return ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD;
        if (level <= enchantType.getMaxLevel())
            return ChatColor.BLUE + "";
        if (level == enchantType.getMaxLevel() + 1)
            return ChatColor.GOLD + "";
        if (level == enchantType.getMaxLevel() + 2)
            return ChatColor.GOLD + "" + ChatColor.BOLD;
        return ChatColor.BLUE + "";
    }

    private static boolean isNotLastEmpty(List<String> lores) {
        try {
            if (lores.get(lores.size() - 1).contains("Requires")) return false;

            if (lores.get(lores.size() - 1).equals("")) {
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public int getPetLength() {
        return (modifiers.getPet().getLevel() * 1_000_000) +
                (this.rarity.getLevel() * 100_000_000) +
                (int) this.modifiers.getPet().getCurrentXp();
    }

    @Override
    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();

        result.put("Player", player);
        result.put("Item", super.serialize());

        return result;
    }

    public static Item deserialize(Map<String, Object> args) {
        if (!args.containsKey("Player") || !args.containsKey("Item")) return new Item(null, Items.NULL);

        Object player = args.get("Player");
        return new Item(player == null ? null : (PlayerSD) player, ItemStack.deserialize((Map<String, Object>) args.get("Item")));
    }

    @Override
    public int compareTo(Item item) {
        return item.getPetLength() - this.getPetLength();
    }

    @Override
    public boolean equals(Object item) {
        if (item == null) return false;

        if (item instanceof Item) {
            return this.uuid.equals(((Item) item).getUUID());
        }
        return super.equals(item);
    }

    private static ItemModifier[] convertModifiersFromItem(ItemModifier[] modifiers, ItemStack item) {
        Map<Class<? extends ItemModifier>, ItemModifier> newModifiers = new HashMap<>();
        for (ItemModifier modifier : ItemModifiers.getModifiers(item)) {
            newModifiers.put(modifier.getClass(), modifier);
        }
        for (ItemModifier modifier : modifiers) {
            newModifiers.put(modifier.getClass(), modifier);
        }
        return newModifiers.values().toArray(new ItemModifier[0]);
    }

    private static ItemModifier[] overrideModifiers(ItemModifiers itemModifiers, ItemModifier[] modifiers) {
        return itemModifiers.getOverrided(modifiers).toArray();
    }

    private static void updateSkyblockMenuLores(Item menu) {
        ItemMeta menuMeta = menu.getItemMeta();
        List<String> lores = menuMeta.getLore();
        lores.remove(lores.size() - 1);
        lores.add(ChatColor.YELLOW + "Click to open!");
        menuMeta.setLore(lores);
        menu.setItemMeta(menuMeta);
    }

    public static UUID getUUID(ItemStack item) {
        if (!Functions.isNotAir(item)) return UUID.randomUUID();

        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbt = nbtItem.getCompound("Item");
        if (nbt != null && nbt.hasKey("uuid")) {
            return UUID.fromString(nbt.getString("uuid"));
        }
        return UUID.randomUUID();
    }
}