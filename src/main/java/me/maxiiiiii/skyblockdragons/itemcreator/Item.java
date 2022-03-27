package me.maxiiiiii.skyblockdragons.itemcreator;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTList;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.itemcreator.enchants.UltimateEnchantType;
import me.maxiiiiii.skyblockdragons.itemcreator.objects.*;
import me.maxiiiiii.skyblockdragons.itemcreator.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.material.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.UnaryOperator;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.util.Functions.manaCostCalculator;

@Getter
public class Item extends ItemStack {
    private final ItemMaterial material;
    private final int amount;

    public Item(ItemMaterial material, int amount, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
        super(material.getMaterial(), amount, material.getMaterial() == Material.SKULL_ITEM ? (short) 3 : ((material.getNbt().equals("") && !material.getId().equals("")) ? Short.parseShort(material.getId()) : (short) 0));
        this.material = material;
        this.amount = amount;

        this.toItem(hotPotato, reforge, recombabulated, skin, enchants, necronBladeAbilities);
    }

    public Item(ItemMaterial material, int amount, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants) {
        this(material, amount, hotPotato, reforge, recombabulated, skin, enchants, new ArrayList<>());
    }

    public Item(ItemMaterial material, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
        this(material, 1, hotPotato, reforge, recombabulated, skin, enchants, necronBladeAbilities);
    }

    public Item(ItemMaterial material, int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants) {
        this(material, 1, hotPotato, reforge, recombabulated, skin, enchants, new ArrayList<>());
    }

    public Item(ItemMaterial material, int amount) {
        this(material, amount, 0, ReforgeType.NULL, false, SkinMaterial.NULL, new HashMap<>(), new ArrayList<>());
    }

    public Item(ItemMaterial material) {
         this(material, 1);
    }

    public Item(ItemMaterial material, ItemStack fromItem) {
        this(material, (isStackable(fromItem) ? fromItem.getAmount() : 1), getHotPotato(fromItem), getReforge(fromItem), isRecombed(fromItem), getSkin(fromItem), getEnchants(fromItem), getNecronScrolls(fromItem));
    }

    public void setAmount(int amount) {
        System.out.println(amount);
        super.setAmount(amount);
    }

    private void toItem(int hotPotato, ReforgeType reforge, boolean recombabulated, SkinMaterial skin, Map<EnchantType, Short> enchants, ArrayList<NecronBladeMaterial.NecronBladeAbility> necronBladeAbilities) {
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

        double[] stats = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (this.material instanceof WeaponMaterial) {
            stats = applyStats(lores, hotPotato, reforge, rarity, enchants);

            applyEnchants(lores, enchants, enchantList);

            applyDescription(lores);

            applyAbilities(lores, enchants);

            if (this.material instanceof NecronBladeMaterial) {
                if (necronBladeAbilities.size() >= 3) {
                    if (isNotLastEmpty(lores)) lores.add("");
                    lores.add(ChatColor.GOLD + "Item Ability: Wither Impact " + ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK");

                    String description = ChatColor.GRAY + "Teleports " + ChatColor.GREEN + "10 blocks " + ChatColor.GRAY + "ahead of you. Then implode dealing " + ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby enemies. Also applies the " + ChatColor.YELLOW + "wither shield " + ChatColor.GRAY + "scroll ability reducing damage taken and granting an " + ChatColor.GOLD + "❤ Absorption " + ChatColor.GRAY + "shield for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds.";
                    lores.addAll(Functions.loreBuilder(description));

                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(300, enchants));
                } else if (necronBladeAbilities.size() > 0) {
                    for (NecronBladeMaterial.NecronBladeAbility ability : necronBladeAbilities) {
                        applyNecronAbility(lores, ability, enchants);
                    }
                } else {
                    if (isNotLastEmpty(lores)) lores.add("");

                    lores.add(ChatColor.YELLOW + "Right-click to use your class ability!");
                }
            }
        } else if (this.material instanceof ArmorMaterial) {
            stats = applyStats(lores, hotPotato, reforge, rarity, enchants);

            applyEnchants(lores, enchants, enchantList);

            applyDescription(lores);

            applyFullSet(lores);
        } else if (this.material instanceof BookMaterial) {
            applyEnchants(lores, enchants, enchantList);

            int cost = getBookCost(enchants);
            lores.add("");
            lores.add(ChatColor.GRAY + "Apply Cost: " + ChatColor.DARK_AQUA + cost + " Exp Levels");
            lores.add("");
            lores.add(ChatColor.GRAY + "Use this item on an item in an Anvil");
            lores.add(ChatColor.GRAY + "to apply it!");
        } else if (this.material instanceof ToolMaterial) {
            applyDescription(lores);

            applyAbilities(lores, enchants);
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
            lores.add(ItemMaterial.Items.get(material.name().replaceAll("_SKIN", "")).getRarity().getColor() + ItemMaterial.Items.get(material.name().replaceAll("_SKIN", "")).getName());
        } else if (this.material instanceof AccessoryMaterial) {
            stats = applyStats(lores, hotPotato, reforge, rarity);

            applyDescription(lores);
        } else if (this.material instanceof PowerOrbMaterial) {
            PowerOrbMaterial material = (PowerOrbMaterial) this.material;

            lores.add(ChatColor.GOLD + "Item Ability: " + material.getAbility().getName());
            lores.addAll(Functions.loreBuilder(material.getAbility().getDescription(), ChatColor.GRAY, 34));
            lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "50%");

            lores.add("");

            lores.add(material.getRarity().getColor() + "Orb Buff: " + material.getPowerOrbName());
            lores.addAll(Functions.loreBuilder(material.getPowerOrbDescription(), ChatColor.GRAY, 50));
        } else if (this.material instanceof NormalMaterial) {
            NormalMaterial material = (NormalMaterial) this.material;

            lores.addAll(loreBuilder(material.getDescription()));

            if (material.isShowRecipe()) {
                lores.add(ChatColor.YELLOW + "Right-click to view recipe!");
            }
        }

        NBTItem nbtItem = new NBTItem(this, true);
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
        for (double num : stats) {
            statList.add(Double.parseDouble(num + ""));
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
            for (EnchantType enchantType : EnchantType.Enchants.values()) {
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
        if (lores.size() > 0) lores.add("");
        if (recombabulated) {
            lores.add(rarity.getColor() + "" + ChatColor.MAGIC + "X" + ChatColor.RESET + " " + rarity + " " + this.material.getType().toString() + " " + rarity.getColor() + "" + ChatColor.MAGIC + "X");
        } else {
            lores.add(rarity + " " + this.material.getType().toString());
        }
        lores.replaceAll(s -> s.replace(".0", ""));
        meta.setLore(lores);

        if (this.material instanceof NormalMaterial) {
            NormalMaterial material = (NormalMaterial) this.material;

            if (material.isEnchanted()) meta.addEnchant(Enchantment.DURABILITY, 3, true);
        }

        this.setItemMeta(meta);

        if (this.material instanceof ArmorMaterial)
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

    private void applyNecronAbility(ArrayList<String> lores, NecronBladeMaterial.NecronBladeAbility ability, Map<EnchantType, Short> enchants) {
        if (isNotLastEmpty(lores)) lores.add("");
        if (this.material instanceof NecronBladeMaterial) {
            lores.add(ChatColor.GOLD + "Item Ability: " + ability.getAbility().getName() + " " + ability.getAbility().getAction().toString());

            lores.addAll(loreBuilder(ability.getAbility().getDescription()));

            if (ability.getAbility().getManaCost() != 0) {
                if (ability.getAbility().getManaCost() >= 10000) {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator((ability.getAbility().getManaCost() / 10000), enchants) + "%");
                } else {
                    lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + manaCostCalculator(ability.getAbility().getManaCost(), enchants));
                }
            }

            if (ability.getAbility().getCooldown() != 0)
                lores.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + ability.getAbility().getCooldown() + "s");
        }
    }

    private void applyAbilities(ArrayList<String> lores, Map<EnchantType, Short> enchants) {
        if (this.material instanceof ToolMaterial) {
            ToolMaterial material = (ToolMaterial) this.material;
            if (material.getAbilities().get(0).getAction() != AbilityAction.NULL) {
                for (ItemAbility ability : material.getAbilities()) {
                    if (isNotLastEmpty(lores)) lores.add("");

                    lores.add(ChatColor.GOLD + "Item Ability: " + ability.getName() + " " + ChatColor.YELLOW + "" + ChatColor.BOLD + ability.getAction().toString());

                    lores.addAll(loreBuilder(ability.getDescription()));

                    if (ability.getManaCost() != 0) {
                        if (ability.getManaCost() >= 10000) {
                            lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator((ability.getManaCost() / 10000), enchants) + "%");
                        } else {
                            lores.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + Functions.manaCostCalculator(ability.getManaCost(), enchants));
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

    private double[] applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity) {
        Map<EnchantType, Short> enchants = new HashMap<>();
        enchants.put(EnchantType.NULL, (short) 0);
        return applyStats(lores, hotPotato, reforge, rarity, enchants, false);
    }

    private double[] applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity, Map<EnchantType, Short> enchants) {
        return applyStats(lores, hotPotato, reforge, rarity, enchants, true);
    }

    private double[] applyStats(ArrayList<String> lores, int hotPotato, ReforgeType reforge, Rarity rarity, Map<EnchantType, Short> enchants, boolean enchant) {
        double[] stats = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < stats.length; i++) {
            String statReforge = "";
            String statPotato = "";

            String statSymbol = "+";

            ChatColor statColor = ChatColor.GREEN;
            if (getStat(i).isDamageStat()) statColor = ChatColor.RED;

            String percent = "";
            if (i == 2 || i == 3 || i == 4) percent = "%";

            double statAdder = 0;
            if (hotPotato > 0) {
                if (i == 0 || i == 1) {
                    statAdder += hotPotato * 2;
                    statPotato = ChatColor.YELLOW + "(+" + hotPotato * 2 + ") ";
                }
            }
            if (reforge != ReforgeType.NULL && reforge != null) {
                if (reforge.getStats().get(rarity.getLevel() - 1).get(i) != 0) {
                    statAdder += reforge.getStats().get(rarity.getLevel() - 1).get(i);
                    statReforge = ChatColor.BLUE + "(" + reforge + " SYMBOL" + Math.abs(reforge.getStats().get(rarity.getLevel() - 1).get(i)) + percent + ")";
                }
            }
            boolean oneForAll = false;
            if (enchant) {
                if (!enchants.containsKey(EnchantType.NULL)) {
                    for (EnchantType enchantType : EnchantType.Enchants.values()) {
                        if (enchants.containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                            if (enchantType.getStats().get(i) != 0) {
                                statAdder += enchantType.getStats().get(i) * enchants.get(enchantType);
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

                if (oneForAll && i == 0)
                    statAdder += (material.getStats().get(i) + statAdder * 6);

                double stat = material.getStats().get(i) + statAdder;
                stats[i] = stat;
                if (material.getStats().get(i) + statAdder != 0) {
                    if (material.getStats().get(i) + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replaceAll("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + getStat(i).toString() + ": " + statColor + statSymbol + Math.abs(stat) + percent + " " + statPotato + statReforge);
                }
            } else if (this.material instanceof ArmorMaterial) {
                ArmorMaterial material = (ArmorMaterial) this.material;

                if (oneForAll && i == 0)
                    statAdder += (material.getStats().get(i) + statAdder * 6);

                double stat = material.getStats().get(i) + statAdder;
                stats[i] = stat;
                if (material.getStats().get(i) + statAdder != 0) {
                    if (material.getStats().get(i) + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replaceAll("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + getStat(i).toString() + ": " + statColor + statSymbol + Math.abs(stat) + percent + " " + statPotato + statReforge);
                }
            } else if (this.material instanceof AccessoryMaterial) {
                AccessoryMaterial material = (AccessoryMaterial) this.material;

                if (oneForAll && i == 0)
                    statAdder += (material.getStats().get(i) + statAdder * 6);

                double stat = material.getStats().get(i) + statAdder;
                stats[i] = stat;
                if (material.getStats().get(i) + statAdder != 0) {
                    if (material.getStats().get(i) + statAdder < 0) statSymbol = "-";
                    statReforge = statReforge.replaceAll("SYMBOL", statSymbol);
                    lores.add(ChatColor.GRAY + getStat(i).toString() + ": " + statColor + statSymbol + Math.abs(stat) + percent + " " + statPotato + statReforge);
                }
            }
        }

        if (!enchants.containsKey(EnchantType.NULL) && !lores.get(lores.size() - 1).equals("")) lores.add("");

        return stats;
    }

    private int getBookCost(Map<EnchantType, Short> enchants) {
        int cost = 3;
        if (!enchants.containsKey(EnchantType.NULL)) {
            for (EnchantType enchantType : EnchantType.Enchants.values()) {
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
            boolean everyLine = (enchantList.size() <= 6);
            int times = 0;
            StringBuilder enchant = new StringBuilder(",");
            if (!enchants.containsKey(EnchantType.NULL)) {
                for (EnchantType enchantType : enchantList) {
                    if (enchants.containsKey(enchantType) && enchantType.getTypes().contains(this.material.getType())) {
                        times++;
                        if (everyLine) {
                            if (enchantType instanceof UltimateEnchantType) {
                                lores.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + enchantType + " " + Functions.integerToRoman(enchants.get(enchantType)));
                            } else {
                                lores.add(ChatColor.BLUE + enchantType.toString() + " " + Functions.integerToRoman(enchants.get(enchantType)));
                            }
                        } else {
                            if (enchantType instanceof UltimateEnchantType) {
                                enchant.append(", " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD).append(enchantType).append(" ").append(Functions.integerToRoman(enchants.get(enchantType)));
                            } else {
                                enchant.append(", " + ChatColor.BLUE).append(enchantType).append(" ").append(Functions.integerToRoman(enchants.get(enchantType)));
                            }
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

    public static Stat getStat(int num) {
        switch (num) {
            case 0:
                return Stat.DAMAGE;
            case 1:
                return Stat.STRENGTH;
            case 2:
                return Stat.CRIT_DAMAGE;
            case 3:
                return Stat.CRIT_CHANCE;
            case 4:
                return Stat.ABILITY_DAMAGE;
            case 5:
                return Stat.ABILITY_SCALING;
            case 6:
                return Stat.ATTACK_SPEED;
            case 7:
                return Stat.FEROCITY;
            case 8:
                return Stat.HEALTH;
            case 9:
                return Stat.DEFENSE;
            case 10:
                return Stat.SPEED;
            case 11:
                return Stat.INTELLIGENCE;
        }
        return Stat.DAMAGE;
    }

    private static boolean isNotLastEmpty(ArrayList<String> lores) {
        try {
            if (lores.get(lores.size() - 1).equals("")) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
        return true;
    }

//    @Override
//    public boolean isSimilar(ItemStack item) {
//        if (item == null)
//            return false;
//
//        if (item == this)
//            return true;
//
//        NBTItem nbtItem1 = new NBTItem(this.clone());
//        NBTItem nbtItem2 = new NBTItem(item.clone());
//
//        NBTCompound nbt1 = nbtItem1.getCompound("Item");
//        NBTCompound nbt2 = nbtItem2.getCompound("Item");
//
//        nbt1.removeKey("Stack");
//        nbt2.removeKey("Stack");
//        nbt1.removeKey("Date");
//        nbt2.removeKey("Date");
//
//        return getType() == item.getType() && getAmount() == item.getAmount() && nbtItem1.equals(nbtItem2);
//    }
}