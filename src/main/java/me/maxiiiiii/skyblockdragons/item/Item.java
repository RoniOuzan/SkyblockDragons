package me.maxiiiiii.skyblockdragons.item;

import de.tr7zw.changeme.nbtapi.*;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemAbilityAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemDescriptionAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemEnchantAble;
import me.maxiiiiii.skyblockdragons.item.material.interfaces.ItemStatsAble;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.requirements.SkillRequirement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.*;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.util.Functions.manaCostCalculator;

@Getter
public class Item extends ItemStack implements ConfigurationSerializable {
    private final ItemMaterial material;
    private final int amount;

    public Item(PlayerSD player, ItemMaterial material, int amount, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
        super(material.getMaterial(), amount, material.getMaterial() == Material.SKULL_ITEM ? (short) 3 : ((material.getNbt().equals("") && !material.getId().equals("")) ? Short.parseShort(material.getId()) : (short) 0));
        this.material = material;
        this.amount = amount;

        this.toItem(player, hotPotato, reforge, recombabulated, skin, enchants, necronBladeAbilities);
    }

    public Item(PlayerSD player, ItemMaterial material, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
        this(player, material, 1, hotPotato, reforge, recombabulated, skin, enchants, necronBladeAbilities);
    }

    public Item(PlayerSD player, ItemMaterial material, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants) {
        this(player, material, 1, hotPotato, reforge, recombabulated, skin, enchants, new ArrayList<>());
    }

    public Item(ItemMaterial material, int amount) {
        this(null, material, amount, 0, ReforgeType.NULL, false, SkinMaterial.NULL, new HashMap<>(), new ArrayList<>());
    }

    public Item(PlayerSD player, ItemMaterial material, int amount) {
        this(player, material, amount, 0, ReforgeType.NULL, false, SkinMaterial.NULL, new HashMap<>(), new ArrayList<>());
    }

    public Item(ItemMaterial material) {
        this(material, 1);
    }

    public Item(PlayerSD player, ItemMaterial material) {
        this(player, material, 1);
    }

    public Item(PlayerSD player, ItemStack itemStack) {
        this(player, Functions.getItemMaterial(itemStack), itemStack);
    }

    public Item(PlayerSD player, ItemMaterial material, ItemStack fromItem) {
        this(player, material, (isStackable(fromItem) ? fromItem.getAmount() : 1), getHotPotato(fromItem), getReforge(fromItem), isRecombed(fromItem), getSkin(fromItem), Functions.getEnchants(fromItem), getNecronScrolls(fromItem));
        Functions.copyNBTStack(this, fromItem);
    }

    public void setAmount(int amount) {
        super.setAmount(amount);
    }

    private void toItem(PlayerSD player, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
        int recombed = recombabulated ? 1 : 0;

        ArrayList<String> enchantsString = new ArrayList<>();
        for (EnchantType enchantType : enchants.keySet()) {
            if (enchantType.getTypes().contains(this.material.getType())) {
                enchantsString.add(enchantType.realname());
            }
        }
        Collections.sort(enchantsString);

        ArrayList<EnchantType> enchantList = new ArrayList<>();
        for (String key : enchantsString) {
            enchantList.add(EnchantType.valueOf(key));
        }

        Rarity rarity;
        if (this.material instanceof BookMaterial) {
            rarity = Functions.getBookRarity(enchants);
        } else {
            rarity = Functions.getRarity(material.getRarity().getLevel() + recombed);
        }

        ArrayList<String> lores = new ArrayList<>();

        Stats stats = new Stats();

        if (this.material instanceof ItemStatsAble)
            stats = applyStats(lores, hotPotato, reforge, rarity, enchants);

        if (this.material instanceof ItemEnchantAble)
            applyEnchants(lores, enchants, enchantList);

        if (this.material instanceof ItemDescriptionAble)
            applyDescription(lores);

        if (this.material instanceof ArmorMaterial)
            applyFullSet(lores);

        if (this.material instanceof ItemAbilityAble)
            applyAbilities(lores, player, enchants);


        if (this.material instanceof NecronBladeMaterial) {
            if (necronBladeAbilities.size() >= 3) {
                if (isNotLastEmpty(lores)) lores.add("");
                lores.add(ChatColor.GOLD + "Item Ability: Wither Impact " + ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK");

                String description = ChatColor.GRAY + "Teleports " + ChatColor.GREEN + "10 blocks " + ChatColor.GRAY + "ahead of you. Then implode dealing " + ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby enemies. Also applies the " + ChatColor.YELLOW + "wither shield " + ChatColor.GRAY + "scroll ability reducing damage taken and granting an " + ChatColor.GOLD + "❤ Absorption " + ChatColor.GRAY + "shield for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds.";
                lores.addAll(Functions.loreBuilder(description));

                lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(300, player, enchants));
            } else if (necronBladeAbilities.size() > 0) {
                for (NecronBladeMaterial.NecronBladeAbility ability : necronBladeAbilities) {
                    applyNecronAbility(lores, ability, player, enchants);
                }
            } else {
                if (isNotLastEmpty(lores)) lores.add("");

                lores.add(ChatColor.YELLOW + "Right-click to use your class ability!");
            }
        } else if (this.material instanceof BookMaterial) {
            int cost = getBookCost(enchants);
            lores.add("");
            lores.add(ChatColor.GRAY + "Apply Cost: " + ChatColor.DARK_AQUA + cost + " Exp Levels");
            lores.add("");
            lores.add(ChatColor.GRAY + "Use this item on an item in an Anvil");
            lores.add(ChatColor.GRAY + "to apply it!");
            int levelRequirement = 0;
            for (EnchantType enchantType : enchants.keySet()) {
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
            if (material.getType() != ItemType.ITEM || rarity.getLevel() >= 5 || ((material instanceof NormalMaterial) && !((NormalMaterial) material).isStackAble())) {
                int random = Functions.randomInt(1, 10000);
                nbt.setInteger("Stack", random);
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                nbt.setString("Date", format.format(now));
            }
            nbt.setInteger("Rarity", rarity.getLevel());
            nbt.setString("Reforge", reforge.name());
            nbt.setInteger("HotPotato", hotPotato);
            nbt.setBoolean("RarityUpgraded", recombabulated);

            NBTCompound nbtEnchants = nbt.addCompound("Enchants");
            NBTCompound nbtUltimateEnchant = nbt.addCompound("UltimateEnchant");
            if (enchantList.toString().contains("One For All")) {
                if (enchantList.toString().contains("Telekinesis")) {
                    nbtEnchants.setShort("TELEKINESIS", (short) 1);
                }
                nbtEnchants.setShort("ONE_FOR_ALL", (short) 1);
            } else {
                for (EnchantType enchantType : EnchantType.enchants.values()) {
                    if (enchants.containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        if (enchantType instanceof UltimateEnchantType) {
                            nbtUltimateEnchant.setShort(enchantType.name(), enchants.get(enchantType));
                        }
                        nbtEnchants.setShort(enchantType.name(), enchants.get(enchantType));
                    }
                }
            }

            if (this.material instanceof BookMaterial) {
                nbt.setInteger("BookCost", getBookCost(enchants));
            }

            if (this.material instanceof NecronBladeMaterial) {
                NBTCompound necronScrolls = nbt.addCompound("NecronScrolls");
                necronScrolls.setBoolean("IMPLOSION", necronBladeAbilities.contains(NecronBladeMaterial.NecronBladeAbility.IMPLOSION));
                necronScrolls.setBoolean("WITHER_SHIELD", necronBladeAbilities.contains(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD));
                necronScrolls.setBoolean("SHADOW_WARP", necronBladeAbilities.contains(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP));
            }

            if (!material.getNbt().equals("")) {
                NBTCompound skull = nbtItem.addCompound("SkullOwner");
                if (skin != null && skin != SkinMaterial.NULL) {
                    skull.setString("Id", skin.getId());
                    NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
                    texture.setString("Value", skin.getNbt());

                    nbt.setString("Skin", skin.name());
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
        if (reforge != ReforgeType.NULL) {
            reforgeText = reforge + " ";
        }
        meta.setDisplayName(rarity.getColor() + reforgeText + this.material.getName());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        if (lores.size() > 0 && isNotLastEmpty(lores)) lores.add("");
        if (recombabulated) {
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

    private void applyNecronAbility(ArrayList<String> lores, NecronBladeMaterial.NecronBladeAbility ability, PlayerSD player, Map<EnchantType, Short> enchants) {
        if (isNotLastEmpty(lores)) lores.add("");
        if (this.material instanceof NecronBladeMaterial) {
            lores.add(ChatColor.GOLD + "Item Ability: " + ability.getAbility().getName() + " " + ability.getAbility().getAction().toString());

            lores.addAll(loreBuilder(ability.getAbility().getDescription()));

            if (ability.getAbility().getManaCost() != 0) {
                if (ability.getAbility().getManaCost() >= 10000) {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator((ability.getAbility().getManaCost() / 10000), player, enchants) + "%");
                } else {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator(ability.getAbility().getManaCost(), player, enchants));
                }
            }

            if (ability.getAbility().getCooldown() != 0)
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getAbility().getCooldown() + "s");
        }
    }

    private void applyAbilities(ArrayList<String> lores, PlayerSD player, Map<EnchantType, Short> enchants) {
        if (this.material instanceof ToolMaterial) {
            ToolMaterial material = (ToolMaterial) this.material;

            if (material.getAbilities().size() == 0 || material.getAbilities().get(0) == null)
                return;

            if (material.getAbilities().get(0).getAction() != AbilityAction.NULL) {
                for (ItemAbility ability : material.getAbilities()) {
                    if (isNotLastEmpty(lores)) lores.add("");

                    lores.add(ChatColor.GOLD + "Item Ability: " + ability.getName() + " " + ChatColor.YELLOW + "" + ChatColor.BOLD + ability.getAction().toString());

                    lores.addAll(loreBuilder(ability.getDescription().replace("ABILITY_DAMAGE", Functions.getNumberFormat(ability.getAbilityDamage()))));

                    if (ability.getManaCost() != 0) {
                        if (ability.getManaCost() >= 10000) {
                            lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator((ability.getManaCost() / 10000), player, enchants) + "%");
                        } else {
                            lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(ability.getManaCost(), player, enchants));
                        }
                    }

                    if (ability.getCooldown() != 0) {
                        lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getCooldown() + "s");
                    }
                }
            }
        }
    }

    private void applyDescription(ArrayList<String> lores) {
        if (this.material instanceof ToolMaterial) {
            ToolMaterial material = (ToolMaterial) this.material;
            if (!material.getDescription().isEmpty()) {
                if (isNotLastEmpty(lores)) lores.add("");
                lores.addAll(loreBuilder(material.getDescription()));
            }
        } else if (this.material instanceof AccessoryMaterial) {
            AccessoryMaterial material = (AccessoryMaterial) this.material;
            if (!material.getDescription().isEmpty()) {
                if (isNotLastEmpty(lores)) lores.add("");
                lores.addAll(loreBuilder(material.getDescription()));
            }
        } else if (this.material instanceof ArmorMaterial) {
            ArmorMaterial material = (ArmorMaterial) this.material;
            if (!material.getDescription().isEmpty()) {
                if (isNotLastEmpty(lores)) lores.add("");
                lores.addAll(loreBuilder(material.getDescription()));
            }
        }
    }

    private Stats applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity) {
        Map<EnchantType, Short> enchants = new HashMap<>();
        enchants.put(EnchantType.NULL, (short) 0);
        return applyStats(lores, hotPotato, reforge, rarity, enchants, false);
    }

    private Stats applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity, Map<EnchantType, Short> enchants) {
        return applyStats(lores, hotPotato, reforge, rarity, enchants, true);
    }

    private Stats applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity, Map<EnchantType, Short> enchants, boolean enchant) {
        Stats stats = new Stats();
        for (Stat stat : stats.toList()) {
            String statReforge = "";
            String statPotato = "";

            String statSymbol = "+";

            ChatColor statColor = ChatColor.GREEN;

            String percent = "";
            if (stat.type.isPercentage()) percent = "%";

            double statAdder = 0;
            if (hotPotato > 0) {
                if (stat.type == StatType.STRENGTH || stat.type == StatType.DAMAGE) {
                    statAdder += hotPotato * 2;
                    statPotato = ChatColor.YELLOW + "(+" + hotPotato * 2 + ") ";
                }
            }
            if (reforge != ReforgeType.NULL && reforge != null) {
                if (reforge.getStats().get(rarity.getLevel() - 1).get(stat).amount != 0) {
                    statAdder += reforge.getStats().get(rarity.getLevel() - 1).get(stat).amount;
                    statReforge = ChatColor.BLUE + "(" + reforge + " SYMBOL" + Math.abs(reforge.getStats().get(rarity.getLevel() - 1).get(stat).amount) + percent + ")";
                }
            }
            boolean oneForAll = false;
            if (enchant) {
                if (!enchants.containsKey(EnchantType.NULL)) {
                    for (EnchantType enchantType : EnchantType.enchants.values()) {
                        if (enchants.containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                            if (enchantType.getStats().get(stat).amount != 0) {
                                if (enchantType.getStats().get(stat).amount != 0)
                                    statAdder += enchantType.getMultiplayers().get(enchants.get(enchantType));
                            }
                            if (enchantType.name().equals("ONE_FOR_ALL")) {
                                oneForAll = true;
                            }
                        }
                    }
                }
            }

            if (this.material instanceof WeaponMaterial) {
                WeaponMaterial material = (WeaponMaterial) this.material;

                if (oneForAll && stat.type == StatType.DAMAGE)
                    statAdder += (material.getStats().get(stat).amount + statAdder * 6);

                double statDisplay = material.getStats().get(stat).amount + statAdder;
                stats.get(stat).amount = statDisplay;
                if (material.getStats().get(stat).amount + statAdder != 0) {
                    if (material.getStats().get(stat).amount + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replace("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + stat.type.toString() + ": " + statColor + statSymbol + Math.abs(statDisplay) + percent + " " + statPotato + statReforge);
                }
            } else if (this.material instanceof ArmorMaterial) {
                ArmorMaterial material = (ArmorMaterial) this.material;

                if (oneForAll && stat.type == StatType.DAMAGE)
                    statAdder += (material.getStats().get(stat).amount + statAdder * 6);

                double statDisplay = material.getStats().get(stat).amount + statAdder;
                stats.get(stat).amount = statDisplay;
                if (material.getStats().get(stat).amount + statAdder != 0) {
                    if (material.getStats().get(stat).amount + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replace("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + stat.type.toString() + ": " + statColor + statSymbol + Math.abs(statDisplay) + percent + " " + statPotato + statReforge);
                }
            } else if (this.material instanceof AccessoryMaterial) {
                AccessoryMaterial material = (AccessoryMaterial) this.material;

                if (oneForAll && stat.type == StatType.DAMAGE)
                statAdder += (material.getStats().get(stat).amount + statAdder * 6);

                double statDisplay = material.getStats().get(stat).amount + statAdder;
                stats.get(stat).amount = statDisplay;
                if (material.getStats().get(stat).amount + statAdder != 0) {
                    if (material.getStats().get(stat).amount + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replace("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + stat.type.toString() + ": " + statColor + statSymbol + Math.abs(statDisplay) + percent + " " + statPotato + statReforge);
                }
            } else if (this.material instanceof MiningMaterial) {
                MiningMaterial material = (MiningMaterial) this.material;

                if (oneForAll && stat.type == StatType.DAMAGE)
                    statAdder += (material.getStats().get(stat).amount + statAdder * 6);

                double statDisplay = material.getStats().get(stat).amount + statAdder;
                stats.get(stat).amount = statDisplay;
                if (material.getStats().get(stat).amount + statAdder != 0) {
                    if (material.getStats().get(stat).amount + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replace("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + stat.type.toString() + ": " + statColor + statSymbol + Math.abs(statDisplay) + percent + " " + statPotato + statReforge);
                }
            }
        }

        if (lores.size() == 0) return stats;

        if (!enchants.containsKey(EnchantType.NULL) && !lores.get(lores.size() - 1).equals("")) lores.add("");

        return stats;
    }

    public static int getBookCost(Map<EnchantType, Short> enchants) {
        int cost = 3;
        if (!enchants.containsKey(EnchantType.NULL)) {
            for (EnchantType enchantType : EnchantType.enchants.values()) {
                if (enchants.containsKey(enchantType)) {
                    cost += enchants.get(enchantType) * 4;
                }
            }
        }
        cost = (int) Math.pow(cost, 0.7);
        return cost;
    }

    private void applyEnchants(ArrayList<String> lores, Map<EnchantType, Short> enchants, ArrayList<EnchantType> enchantList) {
        if (enchantList.toString().contains("One For All")) {
            if (enchantList.toString().contains("Telekinesis")) {
                lores.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "One For All I, " + ChatColor.BLUE + "Telekinesis I");
            } else {
                lores.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "One For All I");
            }
        } else {
            boolean everyLine = enchantList.size() <= 6;
            boolean description = enchantList.size() <= 3;
            int times = 0;
            StringBuilder enchant = new StringBuilder(",");
            if (!enchants.containsKey(EnchantType.NULL)) {
                for (EnchantType enchantType : enchantList) {
                    if (enchants.containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        times++;
                        if (everyLine) {
                            lores.add(getEnchantLoreColor(enchantType, enchants.get(enchantType)) + enchantType + " " + Functions.integerToRoman(enchants.get(enchantType)));
                            if (description) {
                                lores.addAll(loreBuilder(enchantType.getDescription(enchants.get(enchantType))));
                            }
                        } else {
                            enchant.append(", " + getEnchantLoreColor(enchantType, enchants.get(enchantType))).append(enchantType).append(" ").append(Functions.integerToRoman(enchants.get(enchantType)));
                            if (times % 3 == 0) {
                                enchant = new StringBuilder(enchant.toString().replaceAll(",, ", ""));
                                lores.add(enchant.toString());
                                enchant = new StringBuilder(",");
                            } else if (times == enchants.size()) {
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

    public static StatType getStat(int num) {
        switch (num) {
            case 0:
                return StatType.DAMAGE;
            case 1:
                return StatType.STRENGTH;
            case 2:
                return StatType.CRIT_DAMAGE;
            case 3:
                return StatType.CRIT_CHANCE;
            case 4:
                return StatType.ABILITY_DAMAGE;
            case 5:
                return StatType.ABILITY_SCALING;
            case 6:
                return StatType.ATTACK_SPEED;
            case 7:
                return StatType.FEROCITY;
            case 8:
                return StatType.HEALTH;
            case 9:
                return StatType.DEFENSE;
            case 10:
                return StatType.TRUE_DEFENSE;
            case 11:
                return StatType.SPEED;
            case 12:
                return StatType.INTELLIGENCE;
            case 13:
                return StatType.MAGIC_FIND;
            case 14:
                return StatType.PET_LUCK;
            case 15:
                return StatType.MINING_SPEED;
            case 16:
                return StatType.MINING_FORTUNE;
            case 17:
                return StatType.SEA_CREATURE_CHANCE;
            case 18:
                return StatType.ABSORPTION;
        }
        return StatType.DAMAGE;
    }

    private static boolean isNotLastEmpty(ArrayList<String> lores) {
        try {
            if (lores.get(lores.size() - 1).contains("Requires")) return false;

            if (lores.get(lores.size() - 1).equals("")) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
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

    public Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("material", this.material);
        map.put("amount", this.amount);
        return map;
    }
}