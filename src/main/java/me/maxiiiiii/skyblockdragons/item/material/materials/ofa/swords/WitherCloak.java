package me.maxiiiiii.skyblockdragons.item.material.materials.ofa.swords;

import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.material.types.SwordMaterial;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.abilties.PlayerAbilityRunnable;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WitherCloak extends SwordMaterial {
    public WitherCloak() {
        super("WITHER_CLOAK",
                Material.STONE_SWORD,
                ItemFamily.NULL,
                "Wither Cloak Sword",
                Rarity.EPIC,
                new Stats(190, 135, 0, 0, 0, 0, 0, 250, 0, 0),
                "",
                new CreeperVeil()
        );
    }

    @Override
    public void updateStats(PlayerStats stats) {

    }

    @Override
    public void updateDamage(EntityDamage<?, ?> entityDamage) {

    }

    public static class CreeperVeil extends ItemAbility {
        public CreeperVeil() {
            super(AbilityAction.RIGHT_CLICK,
                    "Creeper Veil",
                    "Spawns a veil around you that grants you immunity from damage. Costs " + ChatColor.RED + "10% " + ChatColor.GRAY + "of your maximum mana each time you block a hit. Click again to de-activate."
            );
        }

        @Override
        public PlayerAbilityRunnable setupAbility() {
            return e -> {

            };
        }
    }
}
