package me.maxiiiiii.skyblockdragons.item.material.materials.nfa.reforgestones;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.ReforgeMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.ItemFamily;
import me.maxiiiiii.skyblockdragons.item.objects.ItemSkull;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.Material;

public class WitherBlood extends ReforgeMaterial {
    public WitherBlood() {
        super("WITHER_BLOOD",
                Material.SKULL_ITEM,
                ItemFamily.REFORGE_STONE,
                "Wither's Blood",
                Rarity.EPIC,
                "Withered"
        );
    }

    @Override
    public ItemSkull getItemSkull() {
        return new ItemSkull("d3323a7b-65e3-394a-95da-018d2f52f917",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNTU0MzQ1NTU3OCwKICAicHJvZmlsZUlkIiA6ICI0ZWQ4MjMzNzFhMmU0YmI3YTVlYWJmY2ZmZGE4NDk1NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJGaXJlYnlyZDg4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2JmNWQyMGUwNjAwMTc0ZGNiYWI3NzQ1ZDk0NDgzMmZiMjA2M2MyYmQxNDkwYzY1MDU5MDFiMjhiZmFhY2Q4ZTUiCiAgICB9CiAgfQp9"
        );
    }
}
