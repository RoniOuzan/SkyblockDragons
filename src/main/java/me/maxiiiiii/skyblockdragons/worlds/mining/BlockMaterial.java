package me.maxiiiiii.skyblockdragons.worlds.mining;

import org.bukkit.Material;

public enum BlockMaterial {
    STONE(15, 1, 1),
    COAL_ORE(25, 1, 4),
    IRON_ORE(25, 2, 5),
    GOLD_ORE(25, 2, 5),
    LAPIS_ORE(25, 2, 3, 8),
    REDSTONE_ORE(25, 3, 1.5, 8),
    EMERALD_ORE(25, 3, 1.5, 8),
    DIAMOND_ORE(30, 3, 12),
    DIAMOND_BLOCK(50, 4, 16),
    OBSIDIAN(500, 4, 20),
    ;

    public Material material;
    public final int blockStrength;
    public final int breakingPower;
    public final double miningXp;
    public final double dropsMultiplayer;

    BlockMaterial(Material material, int blockStrength, int breakingPower, double miningXp, double dropsMultiplayer) {
        this.material = material;
        this.blockStrength = blockStrength;
        this.breakingPower = breakingPower;
        this.miningXp = miningXp;
        this.dropsMultiplayer = dropsMultiplayer;
    }

    BlockMaterial(int blockStrength, int breakingPower, double miningXp, double dropsMultiplayer) {
        this(null, blockStrength, breakingPower, miningXp, dropsMultiplayer);
        this.material = Material.getMaterial(this.name());
    }

    BlockMaterial(int blockStrength, int breakingPower, double miningXp) {
        this(null, blockStrength, breakingPower, miningXp, 1);
        this.material = Material.getMaterial(this.name());
    }

    public static BlockMaterial get(String name) {
        if (name.contains("GLOWING_REDSTONE"))
            name = name.replace("GLOWING_", "");
        return BlockMaterial.valueOf(name);
    }
}
