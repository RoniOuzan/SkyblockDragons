package me.maxiiiiii.skyblockdragons.item.anvil;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.material.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.material.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static me.maxiiiiii.skyblockdragons.util.Functions.*;

@Getter
@Setter
public class Anvil {
    private ItemStack item1;
    private ItemStack item2;

    public Anvil(ItemStack item1, ItemStack item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Anvil() {
        this(new ItemStack(Material.AIR), new ItemStack(Material.AIR));
    }

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

                    for (EnchantType enchantType : EnchantType.enchants.values()) {
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
                    if (material2.equals(Items.get("IMPLOSION")) && !nbt2.getBoolean("IMPLOSION")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.IMPLOSION);
                    }
                    if (material2.equals(Items.get("WITHER_SHIELD")) && !nbt2.getBoolean("WITHER_SHIELD")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.WITHER_SHIELD);
                    }
                    if (material2.equals(Items.get("SHADOW_WARP")) && !nbt2.getBoolean("SHADOW_WARP")) {
                        scrolls.add(NecronBladeMaterial.NecronBladeAbility.SHADOW_WARP);
                    }

                    if (material2.equals(Items.get("WITHER_IMPACT"))) {
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
