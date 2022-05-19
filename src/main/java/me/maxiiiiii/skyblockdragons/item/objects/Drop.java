package me.maxiiiiii.skyblockdragons.item.objects;

import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.material.types.PetMaterial;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class Drop extends Item {
    public int minAmount;
    public int maxAmount;
    public double chance;

    public Drop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        super(material, minAmount);

        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.chance = chance;
    }

    public Drop(ItemMaterial material, int amount, double chance) {
        this(material, amount, amount, chance);
    }

    public Drop(ItemMaterial material, int amount) {
        this(material, amount, amount, 100);
    }

    @Nullable
    public ItemStack generate(PlayerSD player, Object source) {
        double[] multipliers = this.calculateMultiplayers(player, source);
        return this.calculateChances(this.chance, multipliers[0], (int) multipliers[1]);
    }

    protected double[] calculateMultiplayers(PlayerSD player, Object source) {
        double chanceMultiplier = 1;
        int dropAdder = 1;
        if (source instanceof LivingEntity) {
            if (player.getPlayerPet().getActivePet() >= 0) {
                if (player.getPetActive().getPetMaterial() == PetMaterial.get("ENDERMAN") && this.getMaterial().getFamily() == ItemFamily.ENDERMAN)
                    chanceMultiplier *= (player.getPetActive().getLevel() / 200d);
            }
        } else if (source instanceof Block) {
            dropAdder += Math.floor(player.getStats().getMiningFortune().amount) / 100 + (Functions.chanceOf(player.getStats().getMiningFortune().amount % 100) ? 1 : 0);
        }
        return new double[]{chanceMultiplier, dropAdder};
    }

    protected ItemStack calculateChances(double chance, double chanceMultiplier, int dropAdder) {
        chance *= chanceMultiplier;

        if (Functions.randomDouble(0, 100) > chance)
            return null;
        int amount = Functions.randomInt(this.minAmount, this.maxAmount + dropAdder);
        return new Item(this.getMaterial(), amount);
    }
}
