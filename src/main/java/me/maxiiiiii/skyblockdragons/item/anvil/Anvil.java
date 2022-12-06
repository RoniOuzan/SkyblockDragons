package me.maxiiiiii.skyblockdragons.item.anvil;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.*;
import me.maxiiiiii.skyblockdragons.item.modifiers.*;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.reforge.ReforgeType;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public ItemStack combine(PlayerSD player) {
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
                            item = new Item(player, material1, ItemModifiers.getModifiers(item1), new ReforgeModifier(reforgeType));
                        }
                    }
                } else if (material2 instanceof SkinMaterial) {
                    SkinMaterial skin = (SkinMaterial) material2;
                    if (((SkinMaterial) material2).getCanApplyTo().contains(material1.name())) {
                        if (!nbt1.getString("Skin").equals(skin.name())) {
                            item = new Item(player, material1, ItemModifiers.getModifiers(item1), new SkinModifier(skin));
                        }
                    }
                } else if (nbt2.getString("id").equals("RECOMBABULATOR")) {
                    if (isRecombable(material1)) {
                        if (!nbt1.getBoolean("RarityUpgrade")) {
                            item = new Item(player, material1, ItemModifiers.getModifiers(item1), new RecombabulatorModifier(true));
                        }
                    }
                } else if (material2 instanceof BookMaterial) {
                    Map<EnchantType, Short> enchants = ItemModifiers.getModifiers(item1).getEnchants();

                    NBTCompound enchants1 = nbt1.getCompound("Enchants");

                    NBTCompound enchants2 = nbt2.getCompound("Enchants");

                    for (EnchantType enchantType : EnchantType.enchants.values()) {
                        if (!enchantType.getTypes().contains(material1.getType())) continue;
                        if (enchants1.hasKey(enchantType.name()) && enchants2.hasKey(enchantType.name()) && enchants1.getShort(enchantType.name()).equals(enchants2.getShort(enchantType.name()))) {
                            for (EnchantType enchantDistraction : ItemModifiers.getModifiers(item1).getEnchants().keySet()) {
                                if (enchantType.getDistractions().contains(enchantDistraction.name())) {
                                    enchants.remove(enchantDistraction);
                                }
                            }
                            if (enchants2.getInteger(enchantType.name()) + 1 <= enchantType.getMaxLevel()) {
                                enchants.put(enchantType, Short.parseShort((enchants2.getInteger(enchantType.name()) + 1) + ""));
                            }
                        } else if (enchants2.hasKey(enchantType.name())) {
                            for (EnchantType enchantDistraction : ItemModifiers.getModifiers(item1).getEnchants().keySet()) {
                                if (enchantType.getDistractions().contains(enchantDistraction.name()) || (enchantDistraction.getRealName().contains("AAA") && ItemModifiers.getModifiers(item2).getEnchants().keySet().toString().contains("AAA"))) {
                                    enchants.remove(enchantDistraction);
                                }
                            }
                            enchants.put(enchantType, Short.parseShort(enchants2.getInteger(enchantType.name()) + ""));
                        }
                    }
                    item = new Item(player, material1, ItemModifiers.getModifiers(item1), new EnchantModifier(enchants));
                } else if (material2 instanceof NecronBladeMaterial.NecronBladeScroll && material1 instanceof NecronBladeMaterial) {
                    List<NecronBladeMaterial.NecronBladeAbility> scrolls = ItemModifiers.getModifiers(item1).getNecronBladeScrolls();
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

                    item = new Item(player, material1, ItemModifiers.getModifiers(item1), new NecronBladeScrollsModifier(scrolls));
                } else if (material2.getFamily() == ItemFamily.HOT_POTATO && material2 instanceof NormalMaterial && (material1 instanceof WeaponMaterial || material1 instanceof ArmorMaterial)) {
                    if ((material2.name().contains("FUMING") && ItemModifiers.getModifiers(item1).getHotPotato() < 15) || (material2.name().contains("HOT") && ItemModifiers.getModifiers(item1).getHotPotato() < 10)) {
                        item = new Item(player, material1, ItemModifiers.getModifiers(item1), new HotPotatoModifier(ItemModifiers.getModifiers(item1).getHotPotato() + 1));
                    }
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return item;
    }
}
