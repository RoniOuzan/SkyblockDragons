package me.maxiiiiii.skyblockdragons.item.material.materials.deepmines.accessories.lapis;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.AccessoryMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.Stats;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class LapisArtifact extends AccessoryMaterial {
    public LapisArtifact() {
        super("LAPIS_ARTIFACT",
                Material.SKULL_ITEM,
                ItemFamily.LAPIS,
                "Lapis Artifact",
                Rarity.RARE,
                new Stats(0, 8, 4, 1, 0, 0),
                ""
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("cc92a530-e118-444b-8913-294229ed55bc",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTUxODliMzc5YTc4ODBmZjlhNGJiZDU4OGFkYjRlMWI3YjljMzM0MWRlN2Q2ZDAwNmQzNjJhZTU0NTBkYTk4NiJ9fX0="
        );
    }
}
