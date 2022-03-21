package me.maxiiiiii.skyblockdragons.pet;

import lombok.Getter;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import me.maxiiiiii.skyblockdragons.itemcreator.Stat;
import me.maxiiiiii.skyblockdragons.skill.SkillType;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public enum PetMaterial {
    //        Items.put("ENDER_DRAGON_PET", new PetMaterial("Ender Dragon", Rarity.EPIC, "", "", new ArrayList<>(Arrays.asList(0d, 0.5d, 0.5d, 0.1d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList()))));

    ENDER_DRAGON("Ender Dragon", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY)), "083a89e8-c8b9-4c15-bccb-7b4af8d31b20", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFkMDhjMDI4OWQ5ZWZlNTE5ZTg3ZjdiODE0Y2IyMzQ5ZjQ0NzViZDNjMzdkNDRmOWM0ZjBlNTA4ZTc3OTgxZSJ9fX0=", new ArrayList<>(Arrays.asList(0d, 0.5d, 0.5d, 0.1d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList(new PetAbility("End Strike", "Deal " + ChatColor.GREEN + "0.2% " + ChatColor.GRAY + "more damage to end mobs", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("One with the Dragons", "Buffs the Aspect of the Dragons sword by " + ChatColor.GREEN + "0.5 " + Stat.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + "0.3 " + Stat.STRENGTH.getIconAndText(), new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("Superior", "Increases most stats by " + ChatColor.GREEN + "0.1%", new ArrayList<>(Arrays.asList(Rarity.LEGENDARY))))), SkillType.COMBAT),
    NULL("Null", new ArrayList<>(Arrays.asList(Rarity.SPECIAL)), "", "", new ArrayList<>(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList(new PetAbility("Null", "", new ArrayList<>(Arrays.asList(Rarity.SPECIAL))))), SkillType.COMBAT);

    private String name;
    private ArrayList<Rarity> rarities;
    private String id;
    private String nbt;
    private ArrayList<Double> stats;
    private ArrayList<PetAbility> abilities;
    private SkillType skill;

    PetMaterial(String name, ArrayList<Rarity> rarities, String id, String nbt, ArrayList<Double> stats, ArrayList<PetAbility> abilities, SkillType skill) {
        this.name = name;
        this.rarities = rarities;
        this.id = id;
        this.nbt = nbt;
        this.stats = stats;
        this.abilities = abilities;
        this.skill = skill;
    }

    public static boolean isPetMaterial(String name) {
        for (PetMaterial pet : PetMaterial.values()) {
            if (pet.name().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}