package me.maxiiiiii.skyblockdragons.anvil;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.maxiiiiii.skyblockdragons.itemcreator.EnchantType;
import me.maxiiiiii.skyblockdragons.itemcreator.Item;
import me.maxiiiiii.skyblockdragons.itemcreator.ItemFamily;
import me.maxiiiiii.skyblockdragons.itemcreator.ReforgeType;
import me.maxiiiiii.skyblockdragons.material.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;
import static me.maxiiiiii.skyblockdragons.itemcreator.EnchantType.Enchants;

public class Anvil {
    private ItemStack item1;
    private ItemStack item2;

    public Anvil(ItemStack item1, ItemStack item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Anvil() {
        new Anvil(new ItemStack(Material.AIR), new ItemStack(Material.AIR));
    }

    public ItemStack getItem1() {
        return item1;
    }

    public void setItem1(ItemStack item1) {
        this.item1 = item1;
    }

    public ItemStack getItem2() {
        return item2;
    }

    public void setItem2(ItemStack item2) {
        this.item2 = item2;
    }

//    public ItemStack combine() {
//        ItemStack item = new ItemStack(Material.BARRIER);
//        setName(item, ChatColor.RED + "Anvil");
//        setLore(item, new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Place a target item in the left", ChatColor.GRAY + "slot and a sacrifice item in the", ChatColor.GRAY + "right slot to combine", ChatColor.GRAY + "Enchantments and Others!")));
//        try {
//            if (item1.getType() != Material.AIR && item2.getType() != Material.AIR && item1.getAmount() == 1 && item2.getAmount() == 1) {
//                ItemMaterial material1 = getItemMaterial(item1);
//                ItemMaterial material2 = getItemMaterial(item2);
//                if (material2 instanceof ReforgeMaterial) {
//                    ReforgeMaterial reforgeMaterial2 = (ReforgeMaterial) material2;
//                    if (getReforge(reforgeMaterial2.getName()).getTypes().contains(material1.getType())) {
//                        NBTItem nbtItem = new NBTItem(item1);
//                        NBTCompound nbt = nbtItem.getCompound("Item");
//                        if (!nbt.getString("Reforge").equals(reforgeMaterial2.getReforgeName())) {
//                            ItemMaterial skin = setSkin(item1);
//                            item = new Item(material1).toItem(getReforge(reforgeMaterial2.getReforgeName()), nbt.getBoolean("RarityUpgraded"), skin, getEnchants(item1));
//                        }
//                    }
//                } else if (material2.getType() == ItemType.SKIN) {
//                    SkinMaterial skinMaterial2 = (SkinMaterial) material2;
//                    if (skinMaterial2.getItem() == material1) {
//                        NBTItem nbtItem = new NBTItem(item1);
//                        NBTCompound nbt = nbtItem.getCompound("Item");
//                        if (!nbt.getString("Skin").equals(material2.name())) {
//                            item = new Item(material1).toItem(getReforge(nbt.getString("Reforge")), nbt.getBoolean("RarityUpgraded"), material2, getEnchants(item1));
//                        }
//                    }
//                } else if (material2.getName().contains("Recombobulator 3000")) {
//                    if (!(material1 instanceof SkinMaterial) && !(material1 instanceof ReforgeMaterial)) {
//                        NBTItem nbtItem = new NBTItem(item1);
//                        NBTCompound nbt = nbtItem.getCompound("Item");
//                        if (!nbt.getBoolean("RarityUpgraded")) {
//                            item = new Item(material1).toItem(getReforge(nbt.getString("Reforge")), true, getSkin(item1), getEnchants(item1));
//                        }
//                    }
//                } else if (material2.getType() == ItemType.BOOK) {
//                    Map<EnchantType, Short> enchants = getEnchants(item1);
//
//                    NBTItem nbtItem1 = new NBTItem(item1);
//                    NBTCompound nbt1 = nbtItem1.getCompound("Item");
//                    NBTCompound compound1 = nbt1.getCompound("Enchants");
//
//                    NBTItem nbtItem2 = new NBTItem(item2);
//                    NBTCompound nbt2 = nbtItem2.getCompound("Item");
//                    NBTCompound compound2 = nbt2.getCompound("Enchants");
//
//                    for (EnchantType enchantType : Enchantments.values()) {
//                        if (compound1.hasKey(enchantType.name()) && compound2.hasKey(enchantType.name()) && compound1.getInteger(enchantType.name()).equals(compound2.getInteger(enchantType.name()))) {
//                            if (compound1.getInteger(enchantType.name()) < 5) {
//                                for (EnchantType enchantDistraction : getEnchantsList(item1)) {
//                                    if (enchantType.getDistractions().contains(enchantDistraction.name())) {
//                                        enchants.remove(enchantDistraction);
//                                    }
//                                }
//                                enchants.put(enchantType, Short.parseShort((compound2.getInteger(enchantType.name()) + 1) + ""));
//                            }
//                        } else if (compound1.hasKey(enchantType.name()) && compound2.hasKey(enchantType.name())) {
//                            for (EnchantType enchantDistraction : getEnchantsList(item1)) {
//                                if (enchantType.getDistractions().contains(enchantDistraction.name())) {
//                                    enchants.remove(enchantDistraction);
//                                }
//                            }
//                            enchants.put(enchantType, Short.parseShort(compound2.getInteger(enchantType.name()) + ""));
//                        } else if (!compound1.hasKey(enchantType.name()) && compound2.hasKey(enchantType.name())) {
//                            for (EnchantType enchantDistraction : getEnchantsList(item1)) {
//                                if (enchantType.getDistractions().contains(enchantDistraction.name())) {
//                                    enchants.remove(enchantDistraction);
//                                }
//                            }
//                            enchants.put(enchantType, Short.parseShort((compound2.getInteger(enchantType.name())) + ""));
//                        }
//                    }
//                    item = new Item(material1).toItem(getReforge(nbt1.getString("Reforge")), nbt1.getBoolean("RarityUpgraded"), getSkin(item1), enchants);
//                }
//            }
//        } catch (NullPointerException e) {
//            return item;
//        }
//        return item;
//    }

    public ItemStack combine() {
        ItemStack item = createItem(Material.BARRIER, ChatColor.RED + "Anvil", new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Place a target item in the left", ChatColor.GRAY + "slot and a sacrifice item in the", ChatColor.GRAY + "right slot to combine", ChatColor.GRAY + "Enchantments and Others!")));
        try {
            if (isNotAir(item1) && isNotAir(item2)) {
                NBTItem nbtItem1 = new NBTItem(item1);
                NBTCompound nbt1 = nbtItem1.getCompound("Item");

                NBTItem nbtItem2 = new NBTItem(item2);
                NBTCompound nbt2 = nbtItem2.getCompound("Item");

                ItemMaterial material1 = getItemMaterial(item1);
                ItemMaterial material2 = getItemMaterial(item2);

                if (material2 instanceof ReforgeMaterial) {
                    ReforgeMaterial reforge = (ReforgeMaterial) material2;
                    ReforgeType reforgeType = getReforge(reforge.getReforgeName());
                    if (reforgeType.getTypes().contains(material1.getType())) {
                        if (!nbt1.getString("Reforge").equals(reforge.name())) {
                            item = new Item(material1, getHotPotato(item), reforgeType, isRecombed(item1), getSkin(item1), getEnchants(item1), getNecronScrolls(item1));
                        }
                    }
                } else if (material2 instanceof SkinMaterial) {
                    SkinMaterial skin = (SkinMaterial) material2;
                    if (material1.name().equals(skin.name().replaceAll("_SKIN", ""))) {
                        if (!nbt1.getString("Skin").equals(skin.name())) {
                            item = new Item(material1, getHotPotato(item), getReforge(item1), isRecombed(item1), skin, getEnchants(item1), getNecronScrolls(item1));
                        }
                    }
                } else if (nbt2.getString("id").equals("RECOMBABULATOR")) {
                    if (isRecombable(material1)) {
                        if (!nbt1.getBoolean("RarityUpgrade")) {
                            item = new Item(material1, getHotPotato(item), getReforge(item1), true, getSkin(item1), getEnchants(item1), getNecronScrolls(item1));
                        }
                    }
                } else if (material2 instanceof BookMaterial) {
                    Map<EnchantType, Short> enchants = getEnchants(item1);

                    NBTCompound enchants1 = nbt1.getCompound("Enchants");

                    NBTCompound enchants2 = nbt2.getCompound("Enchants");

                    for (EnchantType enchantType : Enchants.values()) {
                        if (enchants1.hasKey(enchantType.name()) && enchants2.hasKey(enchantType.name()) && enchants1.getShort(enchantType.name()).equals(enchants2.getShort(enchantType.name()))) {
                            for (EnchantType enchantDistraction : getEnchants(item1).keySet()) {
                                if (enchantType.getDistractions().contains(enchantDistraction.name())) {
                                    enchants.remove(enchantDistraction);
                                }
                            }
                            if (enchants2.getInteger(enchantType.name()) + 1 <= enchantType.getMaxLevel()) {
                                enchants.put(enchantType, Short.parseShort((enchants2.getInteger(enchantType.name()) + 1) + ""));
                            }
                        } else if (enchants2.hasKey(enchantType.name())) {
                            for (EnchantType enchantDistraction : getEnchants(item1).keySet()) {
                                if (enchantType.getDistractions().contains(enchantDistraction.name()) || (enchantDistraction.realname().contains("AAA") && getEnchants(item2).keySet().toString().contains("AAA"))) {
                                    enchants.remove(enchantDistraction);
                                }
                            }
                            enchants.put(enchantType, Short.parseShort(enchants2.getInteger(enchantType.name()) + ""));
                        }
                    }
                    item = new Item(material1, getHotPotato(item), getReforge(item1), isRecombed(item1), getSkin(item1), enchants, getNecronScrolls(item1));
                } else if (material2 instanceof NecronBladeMaterial.NecronBladeScroll && material1 instanceof NecronBladeMaterial) {
                    ArrayList<NecronBladeMaterial.NecronBladeAbility> scrolls = getNecronScrolls(item1);
                    if (material2.equals(ItemMaterial.get("IMPLOSION")) && !nbt2.getBoolean("IMPLOSION")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.IMPLOSION);
                    }
                    if (material2.equals(ItemMaterial.get("WITHER_SHIELD")) && !nbt2.getBoolean("WITHER_SHIELD")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD);
                    }
                    if (material2.equals(ItemMaterial.get("SHADOW_WARP")) && !nbt2.getBoolean("SHADOW_WARP")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP);
                    }

                    if (material2.equals(ItemMaterial.get("WITHER_IMPACT"))) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.IMPLOSION);
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD);
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP);
                    }

                    item = new Item(material1, getHotPotato(item), getReforge(item1), isRecombed(item1), getSkin(item1), getEnchants(item1), scrolls);
                } else if (material2.getFamily() == ItemFamily.HOT_POTATO && material2 instanceof NormalMaterial && (material1 instanceof WeaponMaterial || material1 instanceof ArmorMaterial)) {
                    if ((material2.name().contains("FUMING") && getHotPotato(item1) < 15) || (material2.name().contains("HOT") && getHotPotato(item1) < 10)) {
                        item = new Item(material1, getHotPotato(item1) + 1, getReforge(item1), isRecombed(item1), getSkin(item1), getEnchants(item1), getNecronScrolls(item1));
                    }
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return item;
    }
}
