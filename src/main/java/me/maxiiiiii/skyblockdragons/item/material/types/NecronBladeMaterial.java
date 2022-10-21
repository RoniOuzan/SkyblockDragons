package me.maxiiiiii.skyblockdragons.item.material.types;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.damage.EntityDamage;
import me.maxiiiiii.skyblockdragons.item.objects.abilities.ItemAbility;
import me.maxiiiiii.skyblockdragons.item.objects.*;
import me.maxiiiiii.skyblockdragons.player.stats.PlayerStats;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Getter
@Setter
public abstract class NecronBladeMaterial extends SwordMaterial {
    private NecronBladeType bladeType;

    public NecronBladeMaterial(String itemID, Material material, ItemFamily family, String name, Rarity rarity, Stats stats, NecronBladeType bladeType) {
        super(itemID, material, family, name, rarity, stats, "Deals +" + ChatColor.GREEN + "50% " + ChatColor.GRAY + "damage to Withers. Grants " + ChatColor.RED + "+1" + StatType.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.RED + "+1" + StatType.STRENGTH.getIconAndText() + " " + ChatColor.GRAY + "per " + ChatColor.RED + "Catacombs " + ChatColor.GRAY + "level." + " NEW_LINE " + ChatColor.GRAY + "Your Catacombs Level: " + ChatColor.RED + "0", new ItemAbility(AbilityAction.NULL, "", "", 0, true, 0));
        this.bladeType = bladeType;
    }

    public enum NecronBladeType {
        VALKYRIE, HYPERION, SCYLLA, ASTRAEA, NECRON_BLADE
    }

    @Getter
    public enum NecronBladeAbility { // TODO: change this item abilities to the new system
        IMPLOSION(new ItemAbility(AbilityAction.RIGHT_CLICK, "Implosion", "Deals " + ChatColor.RED + "10,000 " + ChatColor.GRAY + "damage to nearby enemies.", 300, true, 10)),
        WITHER_SHIELD(new ItemAbility(AbilityAction.RIGHT_CLICK, "Wither Shield", "Reduces damage taken by " + ChatColor.RED + "10% " + ChatColor.GRAY + " for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds. Also grants an absorption shield that gives " + ChatColor.GRAY + "150% " + ChatColor.GRAY + "of your Critical Damage as health, after " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds " + ChatColor.GREEN + "50% " + ChatColor.GRAY + "of the shield is converted into healing.", 150, true, 10)),
        SHADOW_WARP(new ItemAbility(AbilityAction.RIGHT_CLICK, "Shadow Warp", "Creates a special distortion " + ChatColor.YELLOW + "10 " + ChatColor.GRAY + "block ahead of you that sucks all enemies enemies around it. Using this ability again within " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds to detonate the warp and deal damage to enemies near it.", 300, true, 10));

        private final ItemAbility ability;

        NecronBladeAbility(ItemAbility ability) {
            this.ability = ability;
        }
    }

    public static class NecronBladeScroll extends ToolMaterial {
        public NecronBladeScroll(String itemID, Material material, String name, Rarity rarity, String description, MaterialModifier... modifiers) {
            super(itemID, material, ItemFamily.SCROLL, name, ItemType.ITEM, rarity, description, modifiers);
        }

        @Override
        public void updateStats(PlayerStats stats) {

        }

        @Override
        public void updateDamage(EntityDamage<?, ?> entityDamage) {

        }
    }
}
