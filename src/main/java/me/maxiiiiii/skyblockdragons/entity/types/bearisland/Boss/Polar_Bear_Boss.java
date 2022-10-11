package me.maxiiiiii.skyblockdragons.entity.types.bearisland.Boss;

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

public class Polar_Bear_Boss extends EntityMaterial {
    public Polar_Bear_Boss() {
        super(
                EntityType.IRON_GOLEM,
                ChatColor.GRAY + "Polar Bear Boss",
                100,
                10_000_000,
                0,
                500,
                10,
                new Equipment(new Item(Items.get("POLAR_BEAR_HELMET")), Functions.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromBGR(255, 255, 255)), Functions.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromBGR(255, 255, 255)), Functions.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.fromBGR(255, 255, 255)), null, null),
                120,
                0,
                250,
                5,
                new Drop(Items.get("BEAR_PET"), 1, 0.05)
        );
    }

    @Override
    public void onSpawn(EntitySD entity) {
        DisguiseAPI.disguiseToAll(entity.entity, new PlayerDisguise("LidanTheGamer_"));
    }
}