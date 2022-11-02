package me.maxiiiiii.skyblockdragons.entity.types.bearisland.Boss;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.entity.EntitySD;
import me.maxiiiiii.skyblockdragons.item.Item;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Koala_Bear_Boss extends EntityMaterial {
    public Koala_Bear_Boss() {
        super(
                EntityType.IRON_GOLEM,
                ChatColor.GRAY + "Koala Bear Boss",
                100,
                2_000_000,
                0,
                500,
                10,
                new Equipment(new Item(Items.get("KOALA_BEAR_HELMET")), Functions.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromBGR(45, 71, 106)), Functions.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromBGR(62, 75, 94)), Functions.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.fromBGR(43, 72, 117)), null, null),
                120,
                500,
                250,
                5,
                new EntityItemDrop(Items.get("BEAR_PET"), 1, 0.05)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {
        DisguiseAPI.disguiseToAll(entity.entity, new PlayerDisguise("hypixel"));
    }
}