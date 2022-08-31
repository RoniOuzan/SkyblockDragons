package me.maxiiiiii.skyblockdragons.entity.types.eternity;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.objects.Drop;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Infinity extends EntityMaterial {
    public Infinity() {
        super(
                EntityType.SLIME,
                ChatColor.AQUA + "Infinty ",
                80,
                500_000,
                100,
                200,
                10,
                new Equipment(new Item(Items.get("RED_STAINED_GLASS")), Functions.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromBGR(67, 143, 167)), Functions.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromBGR(191, 95, 194)), Functions.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.fromBGR(210, 213, 35)), null, null),
                20,
                0.05,
                50000,
                0,
                new Drop(Items.get("INFINITE_SHARD"), 1, 1)
        );

    }

    @Override
    public void onSpawn(EntitySD entity) {
        DisguiseAPI.disguiseToAll(entity.entity, new PlayerDisguise("XxXofirXxX"));

    }
}