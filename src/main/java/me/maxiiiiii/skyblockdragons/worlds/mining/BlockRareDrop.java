package me.maxiiiiii.skyblockdragons.worlds.mining;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.enchants.EnchantType;
import me.maxiiiiii.skyblockdragons.item.material.types.ItemMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.player.PlayerSD;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

@Getter
public class BlockRareDrop extends Drop {
    public BlockRareDrop(ItemMaterial material, int minAmount, int maxAmount, double chance) {
        super(material, minAmount, maxAmount, chance);
    }

    public BlockRareDrop(ItemMaterial material, int minAmount, int maxAmount) {
        this(material, minAmount, maxAmount, 100);
    }

    public BlockRareDrop(ItemMaterial material, int amount, double chance) {
        this(material, amount, amount, chance);
    }

    public BlockRareDrop(ItemMaterial material, int amount) {
        this(material, amount, amount, 100);
    }

    @Nullable
    public ItemStack generate(PlayerSD player, Block source) {
        double chance = this.chance;

        if (Functions.randomDouble(0, 100) > chance)
            return null;
        int amount = Functions.randomInt(this.minAmount, this.maxAmount + player.getEnchantLevel(EnchantType.FORTUNE, () -> source.getType().name().contains("ORE")));
        return new Item(this.getMaterial(), amount);
    }
}
