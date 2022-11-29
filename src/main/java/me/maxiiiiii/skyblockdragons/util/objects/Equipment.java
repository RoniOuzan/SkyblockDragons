package me.maxiiiiii.skyblockdragons.util.objects;

import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.util.Functions;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Equipment implements ConfigurationSerializable {
    public ItemStack helmet;
    public ItemStack chestplate;
    public ItemStack leggings;
    public ItemStack boots;
    public ItemStack hand;
    public ItemStack offHand;

    public Equipment(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack hand, ItemStack offHand) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.hand = hand;
        this.offHand = offHand;
    }

    public Equipment(Material helmet, Material chestplate, Material leggings, Material boots, Material hand, Material offHand) {
        this(helmet == null ? null : new ItemStack(helmet),
                chestplate == null ? null : new ItemStack(chestplate),
                leggings == null ? null : new ItemStack(leggings),
                boots == null ? null : new ItemStack(boots),
                hand == null ? null : new ItemStack(hand),
                offHand == null ? null : new ItemStack(offHand)
        );
    }

    public Equipment(ItemSkull itemSkull, Material chestplate, Material leggings, Material boots, Material hand) {
        this(Functions.applySkull(new ItemStack(Material.SKULL_ITEM, 1, (short) 3), itemSkull.getId(), itemSkull.getValue()),
                new ItemStack(chestplate),
                new ItemStack(leggings),
                new ItemStack(boots),
                new ItemStack(hand),
                null
        );
    }

    public Equipment() {
        this((Material) null, null, null, null, null, null);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        if (helmet != null)
            map.put("helmet", helmet);
        if (chestplate != null)
            map.put("chestplate", chestplate);
        if (leggings != null)
            map.put("leggings", leggings);
        if (boots != null)
            map.put("boots", boots);
        if (hand != null)
            map.put("hand", hand);
        return map;
    }

    public static Equipment deserialize(Map<String, Object> args) {
        return new Equipment((Material) args.get("helmet"), (Material) args.get("chestplate"), (Material) args.get("leggings"), (Material) args.get("boots"), (Material) args.get("hand"), (Material) args.get("offHand"));
    }
}
