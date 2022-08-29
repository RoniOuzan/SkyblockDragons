package me.maxiiiiii.skyblockdragons.item.reforge;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.objects.ItemType;
import me.maxiiiiii.skyblockdragons.item.objects.ItemTypeGroup;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.util.Functions;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public enum ReforgeType {

    // Reforge Stones
    // Armors
    RENOWNED(new ArrayList<>(Arrays.asList(new Stats(0, 3, 3, 2, 1, 0, 2, 2, 1, 1), new Stats(0, 4, 4, 4, 1, 0, 3, 3, 1, 4), new Stats(0, 6, 6, 6, 2, 0, 4, 4, 1, 8), new Stats(0, 8, 8, 8, 3, 0, 6, 6, 1, 8), new Stats(0, 10, 10, 10, 4, 0, 8, 8, 1, 10), new Stats(0, 12, 12, 12, 5, 0, 10, 10, 1, 12))), ItemTypeGroup.ARMOR.toType(), true),
    ANCIENT(new ArrayList<>(Arrays.asList(new Stats(0, 4, 10, 3, 0, 0, 7, 7, 0, 6), new Stats(0, 8, 20, 5, 0, 0, 7, 7, 0, 8), new Stats(0, 7, 30, 7, 0, 0, 7, 7, 0, 12), new Stats(0, 18, 40, 9, 0, 0, 7, 7, 0, 16), new Stats(0, 25, 50, 12, 0, 0, 7, 7, 0, 20), new Stats(0, 35, 60, 15, 0, 0, 7, 7, 0, 25))), new ArrayList<>(Arrays.asList(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS)), true),
    NECROTIC(new ArrayList<>(Arrays.asList(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 30), new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 60), new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 90), new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 120), new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 150), new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 200))), new ArrayList<>(Arrays.asList(ItemType.HELMET, ItemType.CHESTPLATE, ItemType.LEGGINGS, ItemType.BOOTS)), true),
    // Swords
    FABLED(new ArrayList<>(Arrays.asList(new Stats(0, 30, 15, 0, 0, 0), new Stats(0, 35, 20, 0, 0, 0), new Stats(0, 40, 25, 0, 0, 0), new Stats(0, 50, 32, 0, 0, 0), new Stats(0, 60, 40, 0, 0, 0), new Stats(0, 75, 50, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD)), true),
    WITHERED(new ArrayList<>(Arrays.asList(new Stats(0, 60, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 75, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 90, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 110, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 135, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 170, 0, 0, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD)), true),

    // Reforges
    // Swords & Fishing Rods
    GENTLE(new ArrayList<>(Arrays.asList(new Stats(0, 3, 0, 0, 8, 0, 0, 0, 0, 0), new Stats(0, 5, 0, 0, 10, 0, 0, 0, 0, 0), new Stats(0, 7, 0, 0, 15, 0, 0, 0, 0, 0), new Stats(0, 10, 0, 0, 20, 0, 0, 0, 0, 0), new Stats(0, 15, 0, 0, 25, 0, 0, 0, 0, 0), new Stats(0, 20, 0, 0, 30, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    ODD(new ArrayList<>(Arrays.asList(new Stats(0, 0, 10, 12, 0, 0, 0, 0, 0, -5), new Stats(0, 0, 15, 15, 0, 0, 0, 0, 0, -10), new Stats(0, 0, 15, 15, 0, 0, 0, 0, 0, -18), new Stats(0, 0, 22, 20, 0, 0, 0, 0, 0, -32), new Stats(0, 0, 30, 25, 0, 0, 0, 0, 0, -50), new Stats(0, 0, 40, 30, 0, 0, 0, 0, 0, -75))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    FAST(new ArrayList<>(Arrays.asList(new Stats(0, 0, 0, 0, 10, 0, 0, 0, 0, 0), new Stats(0, 0, 0, 0, 20, 0, 0, 0, 0, 0), new Stats(0, 0, 0, 0, 30, 0, 0, 0, 0, 0), new Stats(0, 0, 0, 0, 40, 0, 0, 0, 0, 0), new Stats(0, 0, 0, 0, 50, 0, 0, 0, 0, 0), new Stats(0, 0, 0, 0, 40, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    FAIR(new ArrayList<>(Arrays.asList(new Stats(0, 2, 2, 2, 2, 0, 0, 0, 0, 2), new Stats(0, 3, 3, 3, 3, 0, 0, 0, 0, 3), new Stats(0, 4, 4, 4, 4, 0, 0, 0, 0, 4), new Stats(0, 7, 7, 7, 7, 0, 0, 0, 0, 7), new Stats(0, 10, 10, 10, 10, 0, 0, 0, 0, 10), new Stats(0, 12, 12, 12, 12, 0, 0, 0, 0, 12))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    EPIC(new ArrayList<>(Arrays.asList(new Stats(0, 15, 10, 0, 1, 0, 0, 0, 0, 0), new Stats(0, 20, 15, 0, 2, 0, 0, 0, 0, 0), new Stats(0, 25, 20, 0, 4, 0, 0, 0, 0, 0), new Stats(0, 32, 27, 0, 7, 0, 0, 0, 0, 0), new Stats(0, 40, 35, 0, 10, 0, 0, 0, 0, 0), new Stats(0, 50, 45, 0, 15, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    SHARP(new ArrayList<>(Arrays.asList(new Stats(0, 0, 20, 10, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 30, 12, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 40, 14, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 55, 17, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 75, 20, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 90, 25, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    HEROIC(new ArrayList<>(Arrays.asList(new Stats(0, 15, 0, 0, 1, 0, 0, 0, 0, 40), new Stats(0, 20, 0, 0, 2, 0, 0, 0, 0, 50), new Stats(0, 25, 0, 0, 2, 0, 0, 0, 0, 65), new Stats(0, 32, 0, 0, 3, 0, 0, 0, 0, 80), new Stats(0, 40, 0, 0, 5, 0, 0, 0, 0, 100), new Stats(0, 50, 0, 0, 7, 0, 0, 0, 0, 125))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    SPICY(new ArrayList<>(Arrays.asList(new Stats(0, 2, 25, 1, 2, 0, 0, 0, 0, 0), new Stats(0, 3, 35, 1, 2, 0, 0, 0, 0, 0), new Stats(0, 4, 45, 1, 2, 0, 0, 0, 0, 0), new Stats(0, 7, 60, 1, 7, 0, 0, 0, 0, 0), new Stats(0, 10, 80, 1, 10, 0, 0, 0, 0, 0), new Stats(0, 15, 100, 1, 15, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    LEGENDARY(new ArrayList<>(Arrays.asList(new Stats(0, 3, 5, 5, 2, 0, 0, 0, 0, 5), new Stats(0, 7, 10, 7, 3, 0, 0, 0, 0, 8), new Stats(0, 12, 15, 9, 5, 0, 0, 0, 0, 12), new Stats(0, 18, 22, 12, 7, 0, 0, 0, 0, 18), new Stats(0, 25, 28, 15, 10, 0, 0, 0, 0, 25), new Stats(0, 32, 36, 18, 15, 0, 0, 0, 0, 35))), new ArrayList<>(Arrays.asList(ItemType.SWORD, ItemType.ROD)), false),
    // Bows
    DEADLY(new ArrayList<>(Arrays.asList(new Stats(0, 0, 5, 10, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 10, 13, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 18, 16, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 32, 19, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 50, 22, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 78, 25, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    FINE(new ArrayList<>(Arrays.asList(new Stats(0, 3, 2, 5, 0, 0, 0, 0, 0, 0), new Stats(0, 7, 4, 7, 0, 0, 0, 0, 0, 0), new Stats(0, 12, 7, 9, 0, 0, 0, 0, 0, 0), new Stats(0, 18, 10, 12, 0, 0, 0, 0, 0, 0), new Stats(0, 25, 15, 25, 0, 0, 0, 0, 0, 0), new Stats(0, 33, 20, 18, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    GRAND(new ArrayList<>(Arrays.asList(new Stats(0, 25, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 32, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 40, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 50, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 60, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 75, 0, 0, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    HASTY(new ArrayList<>(Arrays.asList(new Stats(0, 3, 0, 20, 0, 0, 0, 0, 0, 0), new Stats(0, 5, 0, 25, 0, 0, 0, 0, 0, 0), new Stats(0, 7, 0, 30, 0, 0, 0, 0, 0, 0), new Stats(0, 10, 0, 40, 0, 0, 0, 0, 0, 0), new Stats(0, 15, 0, 50, 0, 0, 0, 0, 0, 0), new Stats(0, 20, 0, 75, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    NEAT(new ArrayList<>(Arrays.asList(new Stats(0, 0, 4, 10, 0, 0, 0, 0, 0, 3), new Stats(0, 0, 8, 12, 0, 0, 0, 0, 0, 6), new Stats(0, 0, 14, 14, 0, 0, 0, 0, 0, 10), new Stats(0, 0, 20, 17, 0, 0, 0, 0, 0, 15), new Stats(0, 0, 30, 20, 0, 0, 0, 0, 0, 15), new Stats(0, 0, 40, 25, 0, 0, 0, 0, 0, 25))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    RAPID(new ArrayList<>(Arrays.asList(new Stats(0, 2, 35, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 3, 45, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 4, 55, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 7, 65, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 10, 75, 20, 0, 0, 0, 0, 0, 0), new Stats(0, 15, 90, 0, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    UNREAL(new ArrayList<>(Arrays.asList(new Stats(0, 3, 5, 8, 0, 0, 0, 0, 0, 0), new Stats(0, 7, 10, 9, 0, 0, 0, 0, 0, 0), new Stats(0, 12, 18, 10, 0, 0, 0, 0, 0, 0), new Stats(0, 18, 32, 11, 0, 0, 0, 0, 0, 0), new Stats(0, 25, 50, 13, 0, 0, 0, 0, 0, 0), new Stats(0, 34, 70, 15, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    AWKWARD(new ArrayList<>(Arrays.asList(new Stats(0, 0, 5, 10, 0, 0, 0, 0, 0, -5), new Stats(0, 0, 10, 12, 0, 0, 0, 0, 0, -10), new Stats(0, 0, 15, 15, 0, 0, 0, 0, 0, -18), new Stats(0, 0, 22, 20, 0, 0, 0, 0, 0, -32), new Stats(0, 0, 30, 25, 0, 0, 0, 0, 0, -50), new Stats(0, 0, 35, 30, 0, 0, 0, 0, 0, -72))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    RICH(new ArrayList<>(Arrays.asList(new Stats(0, 2, 1, 10, 0, 0, 0, 0, 0, 20), new Stats(0, 3, 2, 12, 0, 0, 0, 0, 0, 25), new Stats(0, 4, 4, 14, 0, 0, 0, 0, 0, 30), new Stats(0, 7, 7, 17, 0, 0, 0, 0, 0, 40), new Stats(0, 10, 10, 20, 0, 0, 0, 0, 0, 50), new Stats(0, 15, 15, 25, 0, 0, 0, 0, 0, 60))), new ArrayList<>(Arrays.asList(ItemType.BOW)), false),
    // Armors
    CLEAN(new ArrayList<>(Arrays.asList(new Stats(0, 0, 0, 2, 0, 0, 5, 5, 0, 0), new Stats(0, 0, 0, 4, 0, 0, 7, 7, 0, 0), new Stats(0, 0, 0, 6, 0, 0, 10, 10, 0, 0), new Stats(0, 0, 0, 8, 0, 0, 15, 15, 0, 0), new Stats(0, 0, 0, 10, 0, 0, 20, 20, 0, 0), new Stats(0, 0, 0, 12, 0, 0, 25, 25, 0, 0))), ItemTypeGroup.ARMOR.toType(), false),
    FIERCE(new ArrayList<>(Arrays.asList(new Stats(0, 2, 4, 2, 0, 0), new Stats(0, 4, 7, 3, 0, 0), new Stats(0, 6, 10, 4, 0, 0), new Stats(0, 8, 14, 5, 0, 0), new Stats(0, 10, 18, 6, 0, 0), new Stats(0, 12, 24, 8, 0, 0))), ItemTypeGroup.ARMOR.toType(), false),
    HEAVY(new ArrayList<>(Arrays.asList(new Stats(0, 0, -1, 0, 0, 0, 0, 25, -1, 0), new Stats(0, 0, -2, 0, 0, 0, 0, 35, -1, 0), new Stats(0, 0, -2, 0, 0, 0, 0, 50, -1, 0), new Stats(0, 0, -3, 0, 0, 0, 0, 65, -1, 0), new Stats(0, 0, -5, 0, 0, 0, 0, 80, -1, 0), new Stats(0, 0, -7, 0, 0, 0, 0, 110, -1, 0))), ItemTypeGroup.ARMOR.toType(), false),
    LIGHT(new ArrayList<>(Arrays.asList(new Stats(0, 0, 1, 1, 1, 0, 5, 1, 1, 0), new Stats(0, 0, 2, 1, 2, 0, 7, 2, 2, 0), new Stats(0, 0, 3, 2, 0, 0, 10, 3, 3, 0), new Stats(0, 0, 4, 2, 0, 0, 15, 4, 4, 0), new Stats(0, 0, 5, 3, 0, 0, 20, 5, 5, 0), new Stats(0, 0, 6, 3, 0, 0, 25, 6, 6, 0))), ItemTypeGroup.ARMOR.toType(), false),
    MYTHIC(new ArrayList<>(Arrays.asList(new Stats(0, 2, 0, 1, 0, 0, 2, 2, 2, 20), new Stats(0, 4, 0, 2, 0, 0, 4, 3, 2, 25), new Stats(0, 6, 0, 3, 0, 0, 6, 6, 2, 30), new Stats(0, 8, 0, 4, 0, 0, 8, 8, 2, 40), new Stats(0, 10, 0, 5, 0, 0, 10, 10, 2, 50), new Stats(0, 12, 0, 6, 0, 0, 12, 12, 2, 60))), ItemTypeGroup.ARMOR.toType(), false),
    PURE(new ArrayList<>(Arrays.asList(new Stats(0, 2, 2, 2, 1, 0, 2, 2, 1, 1), new Stats(0, 3, 3, 4, 1, 0, 3, 3, 1, 3), new Stats(0, 4, 4, 6, 2, 0, 4, 4, 1, 4), new Stats(0, 6, 6, 8, 3, 0, 6, 6, 10, 6), new Stats(0, 8, 8, 10, 4, 0, 8, 8, 1, 8), new Stats(0, 10, 10, 12, 5, 0, 10, 10, 1, 10))), ItemTypeGroup.ARMOR.toType(), false),
    TITANIC(new ArrayList<>(Arrays.asList(new Stats(10, 10, 0, 0), new Stats(15, 15, 0, 0), new Stats(20, 20, 0, 0), new Stats(25, 25, 0, 0), new Stats(35, 35, 0, 0), new Stats(50, 50, 0, 0))), ItemTypeGroup.ARMOR.toType(), false),
    SMART(new ArrayList<>(Arrays.asList(new Stats(4, 4, 0, 20), new Stats(6, 6, 0, 40), new Stats(9, 9, 0, 60), new Stats(12, 12, 0, 80), new Stats(15, 15, 0, 100), new Stats(20, 20, 0, 120))), ItemTypeGroup.ARMOR.toType(), false),
    WISE(new ArrayList<>(Arrays.asList(new Stats(6, 0, 1, 25), new Stats(8, 0, 1, 50), new Stats(10, 0, 1, 75), new Stats(12, 0, 2, 100), new Stats(15, 0, 2, 12), new Stats(2, 0, 3, 150))), ItemTypeGroup.ARMOR.toType(), false),
    // Accessories
    BIZARRE(new ArrayList<>(Arrays.asList(new Stats(0, 1, -1, 0, 0, 0, 1, 0, 0, 6), new Stats(0, 2, -2, 0, 0, 0, 1, 0, 0, 25), new Stats(0, 2, -2, 0, 0, 0, 1, 0, 0, 10), new Stats(0, 3, -3, 0, 0, 0, 1, 0, 0, 14), new Stats(0, 5, -5, 0, 0, 0, 1, 0, 0, 20), new Stats(0, 7, -7, 0, 0, 0, 2, 0, 0, 30))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    FORCEFUL(new ArrayList<>(Arrays.asList(new Stats(0, 4, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 5, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 7, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 10, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 15, 0, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 20, 0, 0, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    HURTFUL(new ArrayList<>(Arrays.asList(new Stats(0, 0, 4, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 5, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 7, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 10, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 15, 0, 0, 0, 0, 0, 0, 0), new Stats(0, 0, 20, 0, 0, 0, 0, 0, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    ITCHY(new ArrayList<>(Arrays.asList(new Stats(0, 1, 3, 0, 0, 0), new Stats(0, 1, 4, 0, 0, 0), new Stats(0, 1, 5, 0, 1, 0), new Stats(0, 2, 7, 0, 1, 0), new Stats(0, 3, 10, 0, 1, 0), new Stats(0, 4, 15, 0, 1, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    PLEASANT(new ArrayList<>(Arrays.asList(new Stats(0, 4, 0, 0), new Stats(0, 5, 0, 0), new Stats(0, 7, 0, 0), new Stats(0, 10, 0, 0), new Stats(0, 15, 0, 0), new Stats(0, 20, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    STRANGE(new ArrayList<>(Arrays.asList(new Stats(0, 2, 1, 0, -1, 0, 1, 0, 1, 1), new Stats(0, 1, 2, 0, 2, 0, 0, 3, 0, -1), new Stats(0, -1, 0, 0, 0, 0, 0, 2, 1, 2), new Stats(0, 3, 1, 0, 4, 0, 0, -1, 0, 0), new Stats(0, 0, 7, 0, 0, 0, 0, 1, 3, 8), new Stats(0, 4, 9, 0, 5, 0, 0, 1, 3, 11))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    STRONG(new ArrayList<>(Arrays.asList(new Stats(0, 1, 1, 0, 0, 0), new Stats(0, 2, 2, 0, 0, 0), new Stats(0, 3, 3, 0, 1, 0, 0, 1, 0, 0), new Stats(0, 5, 5, 0, 0, 0, 0, 2, 0, 0), new Stats(0, 8, 8, 0, 0, 0, 0, 3, 0, 0), new Stats(0, 12, 12, 0, 0, 0, 0, 4, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),
    UNPLEASANT(new ArrayList<>(Arrays.asList(new Stats(0, 0, 0, 1, 0, 0), new Stats(0, 0, 0, 1, 0, 0), new Stats(0, 0, 0, 1, 0, 0), new Stats(0, 0, 0, 2, 0, 0), new Stats(0, 0, 0, 2, 0, 0), new Stats(0, 0, 0, 3, 0, 0))), new ArrayList<>(Arrays.asList(ItemType.ACCESSORY)), false),

    // Null
    NULL(new ArrayList<>(Arrays.asList(new Stats())), new ArrayList<>(Arrays.asList(ItemType.NULL)), false);

    private final ArrayList<Stats> stats;
    private final ArrayList<ItemType> types;
    private final boolean reforgeStone;

    ReforgeType(ArrayList<Stats> stats, ArrayList<ItemType> types, boolean reforgeStone) {
        this.stats = stats;
        this.types = types;
        this.reforgeStone = reforgeStone;
    }

    @Override
    public String toString() {
        return Functions.setTitleCase(this.name());
    }
}
