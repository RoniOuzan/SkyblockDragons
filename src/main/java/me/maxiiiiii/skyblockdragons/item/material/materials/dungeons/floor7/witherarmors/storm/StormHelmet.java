package me.maxiiiiii.skyblockdragons.item.material.materials.dungeons.floor7.witherarmors.storm;

import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemFullSetBonus;
import me.maxiiiiii.skyblockdragons.item.material.types.ArmorMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.stats.Stats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StormHelmet extends ArmorMaterial {
    public StormHelmet() {
        super("STORM_HELMET",
                Material.SKULL_ITEM,
                ItemFamily.STORM_ARMOR,
                "Storm's Helmet",
                ItemType.HELMET,
                Rarity.LEGENDARY,
                new Stats(0, 0, 0, 0, 0, 0, 180, 80, 0, 400),
                "Reduces the damage you take from withers by " + ChatColor.RED + "10%" + ChatColor.GRAY + ".",
                ItemFullSetBonus.WITHER_ARMOR_FULL_SET
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("a3552cdc-39ab-3f9f-9373-61c035f61b7d",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNTYyMzM2NjgxNiwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mODMxNTljNWJiN2Q4MDQ1NGQyZDY0NmIxNTc3NGI3MTE2YWFiY2IzYWY1YjY3NzdhMjc1NzNmYzQ1Zjc4NTRmIgogICAgfQogIH0KfQ"
        );
    }
}
