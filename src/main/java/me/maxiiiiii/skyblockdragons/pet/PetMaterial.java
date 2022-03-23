package me.maxiiiiii.skyblockdragons.pet;

import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.Functions;
import me.maxiiiiii.skyblockdragons.itemcreator.Rarity;
import me.maxiiiiii.skyblockdragons.itemcreator.Stat;
import me.maxiiiiii.skyblockdragons.skill.SkillType;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter
@Setter
public class PetMaterial {
    //        Items.put("ENDER_DRAGON_PET", new PetMaterial("Ender Dragon", Rarity.EPIC, "", "", new ArrayList<>(Arrays.asList(0d, 0.5d, 0.5d, 0.1d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList()))));
    public static final HashMap<String, PetMaterial> Pets = new HashMap<>();

    public static PetMaterial ENDER_DRAGON = null;

    public static PetMaterial NULL = null;

    private String name;
    private ArrayList<Rarity> rarities;
    private String id;
    private String nbt;
    private ArrayList<Double> stats;
    private ArrayList<PetAbility> abilities;
    private SkillType skill;
    private int maxLevel;

    PetMaterial(String name, ArrayList<Rarity> rarities, String id, String nbt, ArrayList<Double> stats, ArrayList<PetAbility> abilities, SkillType skill, int maxLevel) {
        this.name = name;
        this.rarities = rarities;
        this.id = id;
        this.nbt = nbt;
        this.stats = stats;
        this.abilities = abilities;
        this.skill = skill;
        this.maxLevel = maxLevel;
    }

    PetMaterial(String name, ArrayList<Rarity> rarities, String id, String nbt, ArrayList<Double> stats, ArrayList<PetAbility> abilities, SkillType skill) {
        this(name, rarities, id, nbt, stats, abilities, skill, 100);
    }

    public static boolean isPetMaterial(String name) {
        for (String pet : Pets.keySet()) {
            if (pet.equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static void registerItems() {
        Pets.put("ENDER_DRAGON", new PetMaterial("Ender Dragon",
                new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY)),
                "083a89e8-c8b9-4c15-bccb-7b4af8d31b20",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFkMDhjMDI4OWQ5ZWZlNTE5ZTg3ZjdiODE0Y2IyMzQ5ZjQ0NzViZDNjMzdkNDRmOWM0ZjBlNTA4ZTc3OTgxZSJ9fX0=",
                new ArrayList<>(Arrays.asList(0d, 0.5d, 0.5d, 0.1d, 0d, 0d, 0d, 0d, 0d, 0d)),
                new ArrayList<>(Arrays.asList(new PetAbility("End Strike", "Deal " + ChatColor.GREEN + "LEVEL*0.2% " + ChatColor.GRAY + "more damage to end mobs", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("One with the Dragons", "Buffs the Aspect of the Dragons sword by " + ChatColor.GREEN + "LEVEL*0.5 " + Stat.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + "LEVEL*0.3 " + Stat.STRENGTH.getIconAndText(), new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("Superior", "Increases most stats by " + ChatColor.GREEN + "LEVEL*0.1%", new ArrayList<>(Arrays.asList(Rarity.LEGENDARY))))),
                SkillType.COMBAT));

        ENDER_DRAGON = Pets.get("ENDER_DRAGON");

        NULL = new PetMaterial("Null", new ArrayList<>(Arrays.asList(Rarity.SPECIAL)), "", "", new ArrayList<>(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList(new PetAbility("Null", "", new ArrayList<>(Arrays.asList(Rarity.SPECIAL))))), SkillType.COMBAT);
    }

    public String name() {
        for (String key : Pets.keySet()) {
            if (Pets.get(key) == this) {
                return key;
            }
        }
        return "";
    }

    public static String stringWithMath(String string, int level) {
        String[] strings = string.split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].contains("LEVEL")) continue;

            String value = strings[i];
            boolean percent = value.contains("%");
            value = value.replaceAll("%", "");
            value = value.replaceAll("LEVEL", level + "");
            value = Functions.stringCalculator(ChatColor.stripColor(value)) + "";

            strings[i] = ChatColor.GREEN + value + (percent ? "%" : "") + ChatColor.GRAY;
        }
        string = " ";
        for (String s : strings) {
            string += " " + s;
        }
        return string.replaceAll(" {2}", "");
    }
}
