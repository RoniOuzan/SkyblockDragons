package me.maxiiiiii.skyblockdragons.entity.types.bearisland.Normal;

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

public class Red_Panda_Bear extends EntityMaterial {
    public Red_Panda_Bear() {
        super(
                EntityType.IRON_GOLEM,
                ChatColor.GRAY + "Red Panda Bear",
                100,
                100_000,
                500,
                500,
                50,
                new Equipment(new Item(Items.get("RED_PANDA_BEAR_HELMET")), Functions.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromBGR(45, 71, 106)), Functions.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromBGR(62, 75, 94)), Functions.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.fromBGR(43, 72, 117)), null, null),
                120,
                10,
                250,
                5,
                new Drop(Items.get("BEAR"), 1, 0.05)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {
        DisguiseAPI.disguiseToAll(entity.entity, new PlayerDisguise("hypixel"));
    }
}