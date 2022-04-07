package me.maxiiiiii.skyblockdragons.player.pet;

import com.comphenix.protocol.wrappers.EnumWrappers;
import lombok.Getter;
import lombok.Setter;
import me.maxiiiiii.skyblockdragons.util.Functions;
import me.maxiiiiii.skyblockdragons.item.objects.Rarity;
import me.maxiiiiii.skyblockdragons.item.objects.StatType;
import me.maxiiiiii.skyblockdragons.player.skill.SkillType;
import me.maxiiiiii.skyblockdragons.util.objects.ParticlePacketUtil;
import me.maxiiiiii.skyblockdragons.util.objects.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Getter
@Setter
public class PetMaterial {
    //        Items.put("ENDER_DRAGON_PET", new PetMaterial("Ender Dragon", Rarity.EPIC, "", "", new ArrayList<>(Arrays.asList(0d, 0.5d, 0.5d, 0.1d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList()))));
    public static final HashMap<String, PetMaterial> Pets = new HashMap<>();

    public static PetMaterial NULL = null;

    public String name;
    public ArrayList<Rarity> rarities;
    public String id;
    public String nbt;
    public ArrayList<Double> stats;
    public ArrayList<PetAbility> abilities;
    public SkillType skill;
    public int maxLevel;
    public ParticlePacketUtil[] particles;
    public SoundUtil[] sounds;

    PetMaterial(String name, ArrayList<Rarity> rarities, String id, String nbt, ArrayList<Double> stats, ArrayList<PetAbility> abilities, SkillType skill, int maxLevel, Object... array) {
        this.name = name;
        this.rarities = rarities;
        this.id = id;
        this.nbt = nbt;
        this.stats = stats;
        this.abilities = abilities;
        this.skill = skill;
        ArrayList<ParticlePacketUtil> particles = new ArrayList<>();
        ArrayList<SoundUtil> sounds = new ArrayList<>();
        for (Object object : array) {
            if (object instanceof ParticlePacketUtil) {
                particles.add((ParticlePacketUtil) object);
            } else if (object instanceof EnumWrappers.Particle) {
                particles.add(new ParticlePacketUtil((EnumWrappers.Particle) object));
            } else if (object instanceof SoundUtil) {
                sounds.add((SoundUtil) object);
            } else if (object instanceof Sound) {
                sounds.add(new SoundUtil((Sound) object, 1f));
            }
        }
        this.particles = particles.toArray(new ParticlePacketUtil[0]);
        this.sounds = sounds.toArray(new SoundUtil[0]);
        this.maxLevel = maxLevel;
    }

    PetMaterial(String name, ArrayList<Rarity> rarities, String id, String nbt, ArrayList<Double> stats, ArrayList<PetAbility> abilities, SkillType skill, Object... array) {
        this(name, rarities, id, nbt, stats, abilities, skill, 100, array);
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
                new ArrayList<>(Arrays.asList(new PetAbility("End Strike", "Deal " + ChatColor.GREEN + "LEVEL*0.2% " + ChatColor.GRAY + "more damage to end mobs", new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("One with the Dragons", "Buffs the Aspect of the Dragons sword by " + ChatColor.GREEN + "LEVEL*0.5 " + StatType.DAMAGE.getIconAndText() + " " + ChatColor.GRAY + "and " + ChatColor.GREEN + "LEVEL*0.3 " + StatType.STRENGTH.getIconAndText(), new ArrayList<>(Arrays.asList(Rarity.EPIC, Rarity.LEGENDARY))), new PetAbility("Superior", "Increases most stats by " + ChatColor.GREEN + "LEVEL*0.1%", new ArrayList<>(Arrays.asList(Rarity.LEGENDARY))))),
                SkillType.COMBAT,
                EnumWrappers.Particle.SMOKE_NORMAL, EnumWrappers.Particle.SPELL_WITCH,
                Sound.ENTITY_ENDERDRAGON_FLAP
        ));

        NULL = new PetMaterial("Null", new ArrayList<>(Arrays.asList(Rarity.SPECIAL)), "", "", new ArrayList<>(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d)), new ArrayList<>(Arrays.asList(new PetAbility("Null", "", new ArrayList<>(Arrays.asList(Rarity.SPECIAL))))), SkillType.COMBAT, new ParticlePacketUtil(EnumWrappers.Particle.REDSTONE, 1, 0, 0, 1f, 5));
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
