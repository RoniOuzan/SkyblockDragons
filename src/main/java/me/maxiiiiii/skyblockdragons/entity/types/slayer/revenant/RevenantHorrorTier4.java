package me.maxiiiiii.skyblockdragons.entity.types.slayer.revenant;

import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.util.objects.Equipment;
import org.bukkit.Material;

public class RevenantHorrorTier4 extends RevenantHorror {
    public RevenantHorrorTier4() {
        super(520,
                1_000_000,
                0,
                400,
                0,
                new Equipment(new ItemSkull("ef8fd8b7-2856-4db0-a656-c8da354e0645", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhiZWUyM2I1YzcyNmFlOGUzZDAyMWU4YjRmNzUyNTYxOWFiMTAyYTRlMDRiZTk4M2I2MTQxNDM0OWFhYWM2NyJ9fX0="), Material.DIAMOND_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_HOE),
                100,
                0.1,
                1200,
                1,
                4
        );
    }
}
