package me.maxiiiiii.skyblockdragons.item.objects;

public enum ItemType {
    SWORD,
    RANGE_WEAPON,
    BOW,
    SHORT_BOW,
    WAND,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    ITEM,
    AXE,
    PICKAXE,
    DRILL,
    SPADE,
    HOE,
    ROD,
    REFORGE_STONE,
    SKIN,
    BOOK,
    ACCESSORY,
    PET,
    POWER_ORB,
    CRYSTAL,
    NULL;

    @Override
    public String toString() {
        return this.name().replaceAll("_", " ");
    }

    public boolean isWeapon() {
        return this == ItemType.SWORD || this == ItemType.BOW;
    }

    public boolean isTool() {
        return this == ItemType.SWORD || this == ItemType.BOW || this == ItemType.WAND || this == ItemType.ITEM || this == ItemType.AXE || this == ItemType.PICKAXE || this == ItemType.ROD || this == ItemType.DRILL;
    }

    public boolean isReforgeable() {
        return this == ItemType.SWORD || this == ItemType.BOW || this == ItemType.ACCESSORY || this == ItemType.HELMET || this == ItemType.CHESTPLATE || this == ItemType.LEGGINGS || this == ItemType.BOOTS;
    }

    public boolean isArmor() {
        return this == ItemType.HELMET || this == ItemType.CHESTPLATE || this == ItemType.LEGGINGS || this == ItemType.BOOTS;
    }
    public boolean isPickaxe() {
        return this == ItemType.PICKAXE || this == ItemType.DRILL;
    }
}
