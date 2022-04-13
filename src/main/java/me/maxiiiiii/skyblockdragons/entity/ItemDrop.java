package me.maxiiiiii.skyblockdragons.entity;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.pet.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ItemDrop extends Item {
    public int minAmount;
    public int maxAmount;
    public double chance;

    public ItemDrop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        super(material, minAmount);

        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.chance = chance;
    }

    public ItemDrop(ItemMaterial material, int minAmount, int maxAmount) {
        this(material, minAmount, maxAmount, 100);
    }

    public ItemDrop(ItemMaterial material, int amount, double chance) {
        this(material, amount, amount, chance);
    }

    public ItemDrop(ItemMaterial material, int amount) {
        this(material, amount, amount, 100);
    }

    @Nullable
    public ItemStack generate(PlayerSD player) {
        double chance = this.chance;
        if (player.getPetActive().getPetMaterial() == PetMaterial.get("ENDERMAN") && this.getMaterial().getFamily() == ItemFamily.ENDERMAN)
            chance *= 1 + (player.getPetActive().getLevel() / 200d);

        if (Functions.randomDouble(0, 100) > chance)
            return null;
        int amount = Functions.randomInt(this.minAmount, this.maxAmount + player.getEnchantLevel(EnchantType.LOOTING) + player.getEnchantLevel(EnchantType.CHANCE));
        return new Item(this.getMaterial(), amount);
    }
}
