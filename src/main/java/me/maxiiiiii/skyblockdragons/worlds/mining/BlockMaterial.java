package me.maxiiiiii.skyblockdragons.worlds.mining;

import org.bukkit.Material;

public enum BlockMaterial {
    STONE(Material.STONE, 15, 1),
    COAL_ORE(25, 1),
    IRON_ORE(25, 2),
    GOLD_ORE(25, 2),
    LAPIS_ORE(25, 2),
    REDSTONE_ORE(25, 3),
    EMERALD_ORE(25, 3),
    DIAMOND_ORE(30, 3),
    DIAMOND_BLOCK(50, 4),
    OBSIDIAN(500, 4),
    ;

    public Material material;
    public final int blockStrength;
    public final int breakingPower;

    BlockMaterial(Material material, int blockStrength, int breakingPower) {
        this.material = material;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
    }

    BlockMaterial(int blockStrength, int breakingPower) {
        this(null, blockStrength, breakingPower);
        this.material = Material.getMaterial(this.name());
    }
}
