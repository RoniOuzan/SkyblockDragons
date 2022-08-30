package me.maxiiiiii.skyblockdragons.item;

import de.tr7zw.changeme.nbtapi.*;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.SkyblockDragons;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.*;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.modifiers.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.Requirement;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
public class Item extends ItemStack {
    protected final ItemMaterial material;
    protected final int amount;

    protected final ItemModifiers modifiers;

    // int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities
    public Item(PlayerSD player, ItemMaterial material, int amount, ItemModifier... modifiers) {
        super(material.getMaterial(), amount, material.getMaterial() == Material.SKULL_ITEM ? (short) 3 : ((material.getNbt().equals("") && !material.getId().equals("")) ? Short.parseShort(material.getId()) : (short) 0));
        this.modifiers = new ItemModifiers(modifiers);
        this.material = material;
        this.amount = amount;

        this.toItem(player);
    }

    public Item(PlayerSD player, ItemMaterial material, ItemModifier... modifiers) {
        this(player, material, 1, modifiers);
    }

    public Item(ItemMaterial material, int amount, ItemModifier... modifiers) {
        this(null, material, amount, modifiers);
    }

    public Item(ItemMaterial material, ItemModifier... modifiers) {
        this(material, 1, modifiers);
    }

    public Item(ItemStack itemStack) {
        this(Functions.getItemMaterial(itemStack), itemStack);
    }

    public Item(ItemMaterial material, ItemStack fromItem, ItemModifier... modifiers) {
        this(null, material, fromItem, modifiers);
    }

    public Item(PlayerSD player, ItemStack itemStack, ItemModifier... modifiers) {
        this(player, Functions.getItemMaterial(itemStack), itemStack, modifiers);
    }

    public Item(PlayerSD player, ItemMaterial material, ItemModifiers itemModifiers, ItemModifier... modifiers) {
        this(
                player,
                material,
                overrideModifiers(itemModifiers, modifiers)
        );
    }

    public Item(PlayerSD player, ItemMaterial material, ItemStack fromItem, ItemModifier... modifiers) {
        this(
                player,
                material,
                isStackable(fromItem) ? fromItem.getAmount() : 1,
                convertModifiersFromItem(modifiers, fromItem)
        );
        Functions.copyNBTStack(this, fromItem);
    }

    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    private void toItem(PlayerSD player) {
        int recombed = modifiers.getRecombabulated() ? 1 : 0;

        Rarity rarity;
        if (this.material instanceof BookMaterial) {
            rarity = Functions.getBookRarity(modifiers);
        } else {
            rarity = Functions.getRarity(material.getRarity().getLevel() + recombed);
        }

        ArrayList<String> lores = new ArrayList<>();

        Stats stats = new Stats();

        if (this.material instanceof ItemStatsAble)
            stats = applyStats(lores, player, rarity);

        if (this.material instanceof ItemEnchantAble) {
            applyEnchants(lores);
        }

        if (this.material instanceof ItemDescriptionAble)
            applyDescription(lores);

        if (this.material instanceof ArmorMaterial)
            applyFullSet(lores);

        if (this.material instanceof ItemAbilityAble)
            applyAbilities(lores, player);


        if (this.material instanceof NecronBladeMaterial) {
            if (modifiers.getNecronBladeScrolls().size() >= 3) {
                if (isNotLastEmpty(lores)) lores.add("");
                lores.add(ChatColor.GOLD + "Item Ability: Wither Impact " + ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK");

                String description = ChatColor.GRAY + "Teleports " + ChatColor.GREEN + "10 blocks " + ChatColor.GRAY + "ahead of you. Then implode dealing " + ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby enemies. Also applies the " + ChatColor.YELLOW + "wither shield " + ChatColor.GRAY + "scroll ability reducing damage taken and granting an " + ChatColor.GOLD + "❤ Absorption " + ChatColor.GRAY + "shield for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds.";
                lores.addAll(Functions.loreBuilder(description));

                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(300, player, modifiers));
            } else if (modifiers.getNecronBladeScrolls().size() > 0) {
                for (NecronBladeMaterial.NecronBladeAbility ability : modifiers.getNecronBladeScrolls()) {
                    applyNecronAbility(lores, ability, player);
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
            lores.addAll(Functions.loreBuilder(material.getPowerOrbDescription(), ChatColor.GRAY, 50));
        } else if (this.material instanceof NormalMaterial) {
            NormalMaterial material = (NormalMaterial) this.material;

            if (material.isShowRecipe()) {
                lores.add(ChatColor.YELLOW + "Right-click to view recipe!");
            }
        }

        NBTItem nbtItem = new NBTItem(this, true);
        try {
            NBTCompound nbt = nbtItem.addCompound("Item");
            NBTCompound extra = nbtItem.addCompound("ExtraAttributes");
            if (this.material instanceof BookMaterial) {
                nbt.setString("id", "ENCHANTED_BOOK");
                extra.setString("id", "ENCHANTED_BOOK");
            } else {
                nbt.setString("id", this.material.name());
                extra.setString("id", this.material.name());
            }
            NBTList<Double> statList = nbt.getDoubleList("Stats");
            for (Stat stat : stats) {
                statList.add(stat.amount);
            }
            if (material.getType() != ItemType.ITEM || (material instanceof NormalMaterial && !((NormalMaterial) material).isStackAble())) {
                nbt.setInteger("Stack", Functions.randomInt(1, 10000));
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                nbt.setString("Date", format.format(new Date()));
            }
            nbt.setInteger("Rarity", rarity.getLevel());
            nbt.setString("Reforge", modifiers.getReforge().name());
            nbt.setInteger("HotPotato", modifiers.getHotPotato());
            nbt.setBoolean("RarityUpgraded", modifiers.getRecombabulated());

            NBTCompound nbtEnchants = nbt.addCompound("Enchants");
            NBTCompound nbtUltimateEnchant = nbt.addCompound("UltimateEnchant");
            if (modifiers.getEnchants().containsKey(EnchantType.ONE_FOR_ALL)) {
                if (modifiers.getEnchants().containsKey(EnchantType.TELEKINESIS)) {
                    nbtEnchants.setShort("TELEKINESIS", (short) 1);
                }
                nbtEnchants.setShort("ONE_FOR_ALL", (short) 1);
            } else {
                for (EnchantType enchantType : EnchantType.enchants.values()) {
                    if (modifiers.getEnchants().containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        if (enchantType instanceof UltimateEnchantType) {
                            nbtUltimateEnchant.setShort(enchantType.name(), modifiers.getEnchants().get(enchantType));
                        }
                        nbtEnchants.setShort(enchantType.name(), modifiers.getEnchants().get(enchantType));
                    }
                }
            }

            if (this.material instanceof BookMaterial) {
                nbt.setInteger("BookCost", getBookCost());
            }

            if (this.material instanceof NecronBladeMaterial) {
                NBTCompound necronScrolls = nbt.addCompound("NecronScrolls");
                necronScrolls.setBoolean("IMPLOSION", modifiers.getNecronBladeScrolls().contains(NecronBladeMaterial.NecronBladeAbility.IMPLOSION));
                necronScrolls.setBoolean("WITHER_SHIELD", modifiers.getNecronBladeScrolls().contains(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD));
                necronScrolls.setBoolean("SHADOW_WARP", modifiers.getNecronBladeScrolls().contains(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP));
            }

            if (!material.getNbt().equals("")) {
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                if (modifiers.getSkin() != null && modifiers.getSkin() != SkinMaterial.NULL) {
                    skull.setString("Id", modifiers.getSkin().getId());
                    NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                    texture.setString("Value", modifiers.getSkin().getNbt());

                    nbt.setString("Skin", modifiers.getSkin().name());
                } else {
                    skull.setString("Id", material.getId());
                    NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                    texture.setString("Value", material.getNbt());

                    nbt.setString("Skin", "");
                }
            } else {
                nbt.setString("Skin", "");
            }
        } catch (NbtApiException ignored) {}

        ItemMeta meta = this.getItemMeta();
        String reforgeText = "";
        if (modifiers.getReforge() != ReforgeType.NULL) {
            reforgeText = modifiers.getReforge() + " ";
        }
        meta.setDisplayName(rarity.getColor() + reforgeText + this.material.getName());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (lores.size() > 0 && isNotLastEmpty(lores)) lores.add("");

        if (material instanceof ItemRequirementAble) {
            lores.addAll(((ItemRequirementAble) material).getRequirements().stream().filter(r -> player == null || !r.hasRequirement(player)).map(Requirement::toString).collect(Collectors.toList()));
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

    private void applyFullSet(ArrayList<String> lores) {
        if (this.material instanceof ArmorMaterial) {
            ArmorMaterial material = (ArmorMaterial) this.material;
            if (!material.getFullSet().getName().isEmpty()) {
                if (!lores.get(lores.size() - 1).isEmpty()) lores.add("");
                lores.add(ChatColor.GOLD + "Full Set Bonus: " + material.getFullSet().getName());

                lores.addAll(loreBuilder(material.getFullSet().getDescription()));

                if (material.getFullSet().getCooldown() != 0)
                    lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + material.getFullSet().getCooldown() + "s");
            }
        }
    }

    private void applyNecronAbility(ArrayList<String> lores, NecronBladeMaterial.NecronBladeAbility ability, PlayerSD player) {
        if (isNotLastEmpty(lores)) lores.add("");
        if (this.material instanceof NecronBladeMaterial) {
            lores.add(ChatColor.GOLD + "Item Ability: " + ability.getAbility().getName() + " " + ability.getAbility().getAction().toString());

            lores.addAll(loreBuilder(ability.getAbility().getDescription()));

            if (ability.getAbility().getManaCost() != 0) {
                if (ability.getAbility().getManaCost() >= 10000) {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator((ability.getAbility().getManaCost() / 10000), player, modifiers) + "%");
                } else {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator(ability.getAbility().getManaCost(), player, modifiers));
                }
            }

            if (ability.getAbility().getCooldown() != 0)
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getAbility().getCooldown() + "s");
        }
    }

    private void applyAbilities(ArrayList<String> lores, PlayerSD player) {
        ItemAbilityAble material = (ItemAbilityAble) this.material;

        if (material.getAbilities().size() == 0 || material.getAbilities().get(0) == null)
            return;

        if (material.getAbilities().get(0).getAction() != AbilityAction.NULL) {
            for (ItemAbility ability : material.getAbilities()) {
                if (isNotLastEmpty(lores)) lores.add("");

                lores.add(ChatColor.GOLD + "Item Ability: " + ability.getName() + " " + ChatColor.YELLOW + "" + ChatColor.BOLD + ability.getAction().toString());

                lores.addAll(loreBuilder(ability.getDescription().replace("ABILITY_DAMAGE", Functions.getNumberFormat(ability.getAbilityDamage()))));

                if (ability.getManaCost() != 0) {
                    if (ability.getManaCost() >= 10000) {
                        lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator((ability.getManaCost() / 10000), player, modifiers) + "%");
                    } else {
                        lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(ability.getManaCost(), player, modifiers));
                    }
                }

                if (ability.getCooldown() != 0) {
                    lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getCooldown() + "s");
                }
            }
        }
    }

    private void applyDescription(ArrayList<String> lores) {
        ItemDescriptionAble material = (ItemDescriptionAble) this.material;
        if (!material.getDescription().isEmpty()) {
            if (isNotLastEmpty(lores)) lores.add("");

            lores.addAll(loreBuilder(material.getDescription()));
        }
    }

    private Stats applyStats(ArrayList<String> lores, PlayerSD player, Rarity rarity) {
        Stats stats = new Stats();
        for (Stat stat : stats.toList()) {
            String statReforge = "";
            String statPotato = "";

            String statSymbol = "+";

            ChatColor statColor = ChatColor.GREEN;

            String percent = "";
            if (stat.type.isPercentage()) percent = "%";

            double statAdder = 0;
            if (modifiers.getHotPotato() > 0) {
                if (stat.type == StatType.STRENGTH || stat.type == StatType.DAMAGE) {
                    statAdder += modifiers.getHotPotato() * 2;
                    statPotato = ChatColor.YELLOW + "(+" + modifiers.getHotPotato() * 2 + ") ";
                }
            }
            if (modifiers.getReforge() != ReforgeType.NULL && modifiers.getReforge() != null) {
                if (modifiers.getReforge().getStats().get(rarity.getLevel() - 1).get(stat).amount != 0) {
                    statAdder += modifiers.getReforge().getStats().get(rarity.getLevel() - 1).get(stat).amount;
                    statReforge = ChatColor.BLUE + "(" + modifiers.getReforge() + " SYMBOL" + Math.abs(modifiers.getReforge().getStats().get(rarity.getLevel() - 1).get(stat).amount) + percent + ")";
                }
            }
            boolean oneForAll = modifiers.getEnchants().keySet().stream().anyMatch(e -> e == EnchantType.ONE_FOR_ALL);
            if (!modifiers.getEnchants().containsKey(EnchantType.NULL)) {
                for (EnchantType enchantType : EnchantType.enchants.values()) {
                    if (modifiers.getEnchants().containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        if (enchantType.getStats().get(stat).amount != 0) {
                            if (enchantType.getStats().get(stat).amount != 0)
                                statAdder += enchantType.getMultiplayers().get(modifiers.getEnchants().get(enchantType));
                        }
                    }
                }
            }

            ItemStatsAble material = (ItemStatsAble) this.material;

            if (oneForAll && stat.type == StatType.DAMAGE)
                statAdder += (material.getStats().get(stat).amount + statAdder * 6);

            if (player != null) {
                if (player.getPlayerPet().getActivePet() >= 0) {
                    if (player.getPetActive().getPetMaterial() == Items.get("ENDER_DRAGON") && this.material == Items.get("ASPECT_OF_THE_DRAGON")) {
                        if (stat.type == StatType.DAMAGE)
                            statAdder += player.getPetActive().getLevel() * 0.5;
                        else if (stat.type == StatType.STRENGTH)
                            statAdder += player.getPetActive().getLevel() * 0.3;
                    }
                }
            }

            double statDisplay = material.getStats().get(stat).amount + statAdder;
            stats.get(stat).amount = statDisplay;
            if (material.getStats().get(stat).amount + statAdder != 0) {
                if (material.getStats().get(stat).amount + statAdder < 0) statSymbol = "-";
                statReforge = statReforge.replace("SYMBOL", statSymbol);
                lores.add(ChatColor.GRAY + stat.type.toString() + ": " + statColor + statSymbol + Math.abs(statDisplay) + percent + " " + statPotato + statReforge);
            }
        }

        if (lores.size() == 0) return stats;

        if (!modifiers.getEnchants().containsKey(EnchantType.NULL) && !lores.get(lores.size() - 1).equals("")) lores.add("");

        return stats;
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

    private void applyEnchants(ArrayList<String> lores) {
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

    private static boolean isNotLastEmpty(ArrayList<String> lores) {
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

    public Map<EnchantType, Short> getEnchants() {
        Map<EnchantType, Short> enchants = new HashMap<>();
        NBTItem nbtItem = new NBTItem(this);
        NBTCompound nbt = nbtItem.getCompound("Item");
        NBTCompound compound1 = nbt.getCompound("Enchants");
        NBTCompound compound2 = nbt.getCompound("UltimateEnchant");
        for (EnchantType enchant : EnchantType.enchants.values()) {
            if (compound1.hasKey(enchant.name()))
                enchants.put(enchant, Short.parseShort(compound1.getInteger(enchant.name()) + ""));
            else if (compound2.hasKey(enchant.name()))
                enchants.put(enchant, Short.parseShort(compound1.getInteger(enchant.name()) + ""));
        }
        return enchants;
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
}