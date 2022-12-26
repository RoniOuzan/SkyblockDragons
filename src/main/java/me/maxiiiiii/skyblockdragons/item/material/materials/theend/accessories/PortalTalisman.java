package me.maxiiiiii.skyblockdragons.item.material.materials.theend.accessories;

import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.stats.constructors.DamageStats;
import org.bukkit.Material;

public class PortalTalisman extends AccessoryMaterial {
    public PortalTalisman() {
        super("PORTAL_TALISMAN",
                Material.SKULL_ITEM,
                ItemFamily.PORTAL,
                "Portal Talisman",
                Rarity.UNCOMMON,
                new DamageStats(0, 2, 2, 0, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("f9f3f95f-25cb-47b0-b460-6037a25a24be",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmY5MjA5ODZjNTcyMzg5YTIzYzhhYzA0NzFiODhkZjQ0YTQ3NzY4MDNiZTY0NDRmNWZmMGI5ZDE1NTJjMjc0ZSJ9fX0="
        );
    }
}
