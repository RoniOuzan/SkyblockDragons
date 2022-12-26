package me.maxiiiiii.skyblockdragons.entity.types.deepmines;

import me.maxiiiiii.skyblockdragons.entity.EntityMaterial;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemDrop;
import me.maxiiiiii.skyblockdragons.item.drops.types.entity.EntityItemRareDrop;
import me.maxiiiiii.skyblockdragons.item.material.Items;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class ObsidianZombie extends EntityMaterial {
    public ObsidianZombie() {
        super(
                EntityType.ZOMBIE,
                ChatColor.DARK_GRAY + "Obsidian Zombie",
                11,
                500,
                20,
                300,
                0,
                new Equipment(Material.OBSIDIAN, null, null, null, Material.STONE_SWORD, null),
                100,
                0.2,
                30,
                4,
                new EntityItemDrop(Items.get("OBSIDIAN"), 1, 50),
                new EntityItemRareDrop(Items.get("ENCHANTED_OBSIDIAN"), 1, 2d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_HELMET"), 1, 1d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_CHESTPLATE"), 1, 1d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_LEGGINGS"), 1, 1d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_BOOTS"), 1, 1d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_TALISMAN"), 1, 0.5d),
                new EntityItemRareDrop(Items.get("OBSIDIAN_RING"), 1, 0.1d)
        );
    }
}
